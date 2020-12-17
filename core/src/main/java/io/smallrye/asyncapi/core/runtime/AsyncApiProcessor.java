/*
 * Copyright 2019 Red Hat, Inc, and individual contributors.
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
package io.smallrye.asyncapi.core.runtime;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.jboss.jandex.IndexView;

import io.smallrye.asyncapi.core.api.AsyncApiConfig;
import io.smallrye.asyncapi.core.api.AsyncApiConfigImpl;
import io.smallrye.asyncapi.core.api.AsyncApiDocument;
import io.smallrye.asyncapi.core.api.util.ClassLoaderUtil;
import io.smallrye.asyncapi.core.runtime.io.AsyncApiParser;
import io.smallrye.asyncapi.core.runtime.scanner.AsyncApiAnnotationScanner;
import io.smallrye.asyncapi.spec.AASFilter;
import io.smallrye.asyncapi.spec.AASModelReader;
import io.smallrye.asyncapi.spec.models.AsyncAPI;

/**
 * Provides some core archive processing functionality.
 */
public class AsyncApiProcessor {

    private AsyncApiProcessor() {
    }

    public static AsyncAPI bootstrap(IndexView index) {
        Config config = ConfigProvider.getConfig();
        AsyncApiConfig openApiConfig = AsyncApiConfigImpl.fromConfig(config);
        return bootstrap(openApiConfig, index);
    }

    public static AsyncAPI bootstrap(AsyncApiConfig config, IndexView index) {
        ClassLoader defaultClassLoader = ClassLoaderUtil.getDefaultClassLoader();
        return bootstrap(config, index, defaultClassLoader);
    }

    public static AsyncAPI bootstrap(AsyncApiConfig config, IndexView index, AsyncApiStaticFile... staticFiles) {
        ClassLoader defaultClassLoader = ClassLoaderUtil.getDefaultClassLoader();
        return bootstrap(config, index, defaultClassLoader, staticFiles);
    }

    public static AsyncAPI bootstrap(AsyncApiConfig config, IndexView index, ClassLoader classLoader) {
        List<AsyncApiStaticFile> staticfiles = loadAsyncApiStaticFiles(classLoader);
        return bootstrap(config, index, classLoader, staticfiles.toArray(new AsyncApiStaticFile[] {}));
    }

    public static AsyncAPI bootstrap(AsyncApiConfig config, IndexView index, ClassLoader classLoader,
            AsyncApiStaticFile... staticFiles) {
        AsyncApiDocument.INSTANCE.reset();

        // Set the config
        if (config != null) {
            AsyncApiDocument.INSTANCE.config(config);
        }
        // Load all static files
        if (staticFiles != null && staticFiles.length > 0) {
            for (AsyncApiStaticFile staticFile : staticFiles) {
                AsyncApiDocument.INSTANCE.modelFromStaticFile(modelFromStaticFile(staticFile));
            }
        }
        // Scan annotations
        if (config != null && index != null) {
            AsyncAPI model = modelFromAnnotations(config, classLoader, index);
            AsyncApiDocument.INSTANCE.modelFromAnnotations(model);
        }
        // Filter and model
        if (config != null && classLoader != null) {
            AsyncApiDocument.INSTANCE.modelFromReader(modelFromReader(config, classLoader));
            AsyncApiDocument.INSTANCE.filter(getFilter(config, classLoader));
        }

        AsyncApiDocument.INSTANCE.initialize();

        AsyncAPI asyncAPI = AsyncApiDocument.INSTANCE.get();

        AsyncApiDocument.INSTANCE.reset();

        return asyncAPI;
    }

    /**
     * Parse the static file content and return the resulting model. Note that this
     * method does NOT close the resources in the static file. The caller is
     * responsible for that.
     *
     * @param staticFile OpenApiStaticFile to be parsed
     * @return OpenApiImpl
     */
    public static AsyncAPI modelFromStaticFile(AsyncApiStaticFile staticFile) {
        if (staticFile == null) {
            return null;
        }
        try {
            return AsyncApiParser.parse(staticFile.getContent(), staticFile.getFormat());
        } catch (IOException e) {
            throw new AsyncApiRuntimeException(e);
        }
    }

    /**
     * Create an {@link AsyncAPI} model by scanning the deployment for relevant JAX-RS and
     * AsyncAPI annotations. If scanning is disabled, this method returns null. If scanning
     * is enabled but no relevant annotations are found, an empty AsyncAPI model is returned.
     *
     * @param config OpenApiConfig
     * @param index IndexView of Archive
     * @return AsyncAPIImpl generated from annotations
     */
    public static AsyncAPI modelFromAnnotations(AsyncApiConfig config, IndexView index) {
        return modelFromAnnotations(config, ClassLoaderUtil.getDefaultClassLoader(), index);
    }

    /**
     * Create an {@link AsyncAPI} model by scanning the deployment for relevant JAX-RS and
     * AsyncAPI annotations. If scanning is disabled, this method returns null. If scanning
     * is enabled but no relevant annotations are found, an empty AsyncAPI model is returned.
     *
     * @param config OpenApiConfig
     * @param loader ClassLoader
     * @param index IndexView of Archive
     * @return AsyncAPIImpl generated from annotations
     */
    public static AsyncAPI modelFromAnnotations(AsyncApiConfig config, ClassLoader loader, IndexView index) {
        if (config.scanDisable()) {
            return null;
        }

        AsyncApiAnnotationScanner scanner = new AsyncApiAnnotationScanner(config, loader, index);
        return scanner.scan();
    }

    /**
     * Instantiate the configured {@link AASModelReader} and invoke it. If no reader is configured,
     * then return null. If a class is configured but there is an error either instantiating or invoking
     * it, a {@link AsyncApiRuntimeException} is thrown.
     *
     * @param config OpenApiConfig
     * @param loader ClassLoader
     * @return OpenApiImpl created from AASModelReader
     */
    public static AsyncAPI modelFromReader(AsyncApiConfig config, ClassLoader loader) {
        String readerClassName = config.modelReader();
        if (readerClassName == null) {
            return null;
        }
        try {
            Class<?> c = loader.loadClass(readerClassName);
            AASModelReader reader = (AASModelReader) c.getDeclaredConstructor().newInstance();
            return reader.buildModel();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            throw new AsyncApiRuntimeException(e);
        }
    }

    /**
     * Instantiate the {@link AASFilter} configured by the app.
     *
     * @param config OpenApiConfig
     * @param loader ClassLoader
     * @return OASFilter instance retrieved from loader
     */
    public static AASFilter getFilter(AsyncApiConfig config, ClassLoader loader) {
        String filterClassName = config.filter();
        if (filterClassName == null) {
            return null;
        }
        try {
            Class<?> c = loader.loadClass(filterClassName);
            return (AASFilter) c.getDeclaredConstructor()
                    .newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            throw new AsyncApiRuntimeException(e);
        }
    }

    private static List<AsyncApiStaticFile> loadAsyncApiStaticFiles(ClassLoader classLoader) {
        List<AsyncApiStaticFile> apiStaticFiles = new ArrayList<>();

        loadAsyncApiStaticFile(apiStaticFiles, classLoader, "/META-INF/asyncapi.yaml", AsyncApiFormat.YAML);
        loadAsyncApiStaticFile(apiStaticFiles, classLoader, "/WEB-INF/classes/META-INF/asyncapi.yaml", AsyncApiFormat.YAML);
        loadAsyncApiStaticFile(apiStaticFiles, classLoader, "/META-INF/asyncapi.yml", AsyncApiFormat.YAML);
        loadAsyncApiStaticFile(apiStaticFiles, classLoader, "/WEB-INF/classes/META-INF/asyncapi.yml", AsyncApiFormat.YAML);
        loadAsyncApiStaticFile(apiStaticFiles, classLoader, "/META-INF/asyncapi.json", AsyncApiFormat.JSON);
        loadAsyncApiStaticFile(apiStaticFiles, classLoader, "/WEB-INF/classes/META-INF/asyncapi.json", AsyncApiFormat.JSON);

        return apiStaticFiles;
    }

    private static List<AsyncApiStaticFile> loadAsyncApiStaticFile(List<AsyncApiStaticFile> apiStaticFiles,
            ClassLoader classLoader,
            String path, AsyncApiFormat format) {
        InputStream staticStream = classLoader.getResourceAsStream(path);
        if (staticStream != null) {
            apiStaticFiles.add(new AsyncApiStaticFile(staticStream, format));
        }
        return apiStaticFiles;
    }
}
