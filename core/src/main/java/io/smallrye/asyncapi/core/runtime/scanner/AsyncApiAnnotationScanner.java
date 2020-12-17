/*
 * Copyright 2019 Red Hat
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.smallrye.asyncapi.core.runtime.scanner;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.IndexView;

import io.smallrye.asyncapi.core.api.AsyncApiConfig;
import io.smallrye.asyncapi.core.api.constants.AsyncApiConstants;
import io.smallrye.asyncapi.core.api.models.AsyncAPIImpl;
import io.smallrye.asyncapi.core.api.util.ClassLoaderUtil;
import io.smallrye.asyncapi.core.api.util.MergeUtil;
import io.smallrye.asyncapi.core.runtime.io.CurrentScannerInfo;
import io.smallrye.asyncapi.core.runtime.io.definition.DefinitionConstant;
import io.smallrye.asyncapi.core.runtime.io.definition.DefinitionReader;
import io.smallrye.asyncapi.core.runtime.scanner.spi.AnnotationScanner;
import io.smallrye.asyncapi.core.runtime.scanner.spi.AnnotationScannerContext;
import io.smallrye.asyncapi.core.runtime.scanner.spi.AnnotationScannerFactory;
import io.smallrye.asyncapi.spec.models.AsyncAPI;

/**
 * Scans a deployment (using the archive and jandex annotation index) for AsyncAPI annotations. Also delegate to all other
 * scanners. These
 * annotations, if found, are used to generate a valid AsyncAPI model.
 */
public class AsyncApiAnnotationScanner {

    private final AnnotationScannerContext annotationScannerContext;

    private final AnnotationScannerFactory annotationScannerFactory;

    /**
     * Maven plugin
     *
     * @param config AsyncApiConfig instance
     * @param index IndexView of deployment
     */
    public AsyncApiAnnotationScanner(AsyncApiConfig config, IndexView index) {
        this(config, ClassLoaderUtil.getDefaultClassLoader(), index,
                Collections.singletonList(new AnnotationScannerExtension() {
                }));
    }

    /**
     * Quarkus extension
     *
     * @param config AsyncApiConfig instance
     * @param index IndexView of deployment
     * @param extensions A set of extensions to scanning
     */
    public AsyncApiAnnotationScanner(AsyncApiConfig config, IndexView index, List<AnnotationScannerExtension> extensions) {
        this(config, ClassLoaderUtil.getDefaultClassLoader(), index, extensions);
    }

    /**
     * @param config AsyncApiConfig instance
     * @param index IndexView of deployment
     */
    public AsyncApiAnnotationScanner(AsyncApiConfig config, ClassLoader loader, IndexView index) {
        this(config, loader, index, Collections.singletonList(new AnnotationScannerExtension() {
        }));
    }

    /**
     * @param config AsyncApiConfig instance
     * @param index IndexView of deployment
     * @param extensions A set of extensions to scanning
     */
    public AsyncApiAnnotationScanner(AsyncApiConfig config, ClassLoader loader, IndexView index,
            List<AnnotationScannerExtension> extensions) {
        FilteredIndexView filteredIndexView;

        if (index instanceof FilteredIndexView) {
            filteredIndexView = (FilteredIndexView) index;
        } else {
            filteredIndexView = new FilteredIndexView(index, config);
        }

        this.annotationScannerContext = new AnnotationScannerContext(filteredIndexView, loader, extensions, config,
                new AsyncAPIImpl());
        this.annotationScannerFactory = new AnnotationScannerFactory(loader);
    }

    /**
     * Scan the deployment for relevant annotations. Returns an AsyncAPI data model that was built from those found annotations.
     *
     * @param filter Filter to only include certain scanners. Based on the scanner name. (JAX-RS, Spring, Vert.x)
     * @return AsyncAPI generated from scanning annotations
     */
    public AsyncAPI scan(String... filter) {
        // First scan the AsyncAPI Annotations. Maybe later we can load this with SPI as well, and allow other Annotation sets.
        AsyncAPI asyncAPI = scanOKProfileAsyncApiAnnotations();

        // Now load all entry points with SPI and scan those
        List<AnnotationScanner> annotationScanners = annotationScannerFactory.getAnnotationScanners();
        for (AnnotationScanner annotationScanner : annotationScanners) {
            if (filter == null || filter.length == 0 || Arrays.asList(filter)
                    .contains(annotationScanner.getName())) {

                ScannerLogging.logger.scanning(annotationScanner.getName());
                CurrentScannerInfo.register(annotationScanner);
                asyncAPI = annotationScanner.scan(annotationScannerContext, asyncAPI);
            }
        }

        return asyncAPI;
    }

    private AsyncAPI scanOKProfileAsyncApiAnnotations() {

        // Initialize a new AAI document.  Even if nothing is found, this will be returned.
        AsyncAPI asyncAPI = this.annotationScannerContext.getAsyncAPI();
        asyncAPI.setAsyncapi(AsyncApiConstants.ASYNC_API_VERSION);

        // Creating a new instance of a registry which will be set on the thread context.
        SchemaRegistry schemaRegistry = SchemaRegistry.newInstance(annotationScannerContext.getConfig(), asyncAPI,
                annotationScannerContext.getIndex());

        // Register custom schemas if available
        getCustomSchemaRegistry(annotationScannerContext.getConfig()).registerCustomSchemas(schemaRegistry);

        // Find all AsyncAPI annotations at the package level
        ScannerLogging.logger.scanning("AsyncAPI");
        processAsyncAPIDefinitions(annotationScannerContext, asyncAPI);

        return asyncAPI;
    }

    /**
     * Scans all <code>@AsyncAPI</code> annotations present classes known to the scanner's index.
     *
     * @param context scanning context
     * @param aai the current AsyncAPI result
     * @return the created AsyncAPI
     */
    private AsyncAPI processAsyncAPIDefinitions(final AnnotationScannerContext context, AsyncAPI aai) {
        List<AnnotationInstance> packageDefs = new ArrayList<>(context.getIndex()
                .getAnnotations(DefinitionConstant.DOTNAME_ASYNC_API));

        for (AnnotationInstance packageDef : packageDefs) {
            AsyncAPI packageAai = new AsyncAPIImpl();
            DefinitionReader.processDefinition(context, packageAai, packageDef);
            aai = MergeUtil.merge(aai, packageAai);
        }

        return aai;
    }

    private CustomSchemaRegistry getCustomSchemaRegistry(final AsyncApiConfig config) {
        if (config == null || config.customSchemaRegistryClass() == null) {
            // Provide default implementation that does nothing
            return type -> {
            };
        } else {
            try {
                return (CustomSchemaRegistry) Class
                        .forName(config.customSchemaRegistryClass(), true, annotationScannerContext.getClassLoader())
                        .getDeclaredConstructor()
                        .newInstance();
            } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
                    | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                throw ScannerMessages.msg.failedCreateInstance(config.customSchemaRegistryClass(), ex);
            }
        }
    }
}
