/*
 * Copyright (C) open knowledge GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */
package io.quarkus.asyncapi.deployment;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.jboss.jandex.CompositeIndex;
import org.jboss.jandex.IndexView;

import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.arc.deployment.BeanArchiveIndexBuildItem;
import io.quarkus.asyncapi.runtime.AsyncApiConstants;
import io.quarkus.asyncapi.runtime.AsyncApiDocumentService;
import io.quarkus.asyncapi.runtime.AsyncApiHandler;
import io.quarkus.asyncapi.runtime.AsyncApiRecorder;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.ExecutionTime;
import io.quarkus.deployment.annotations.Record;
import io.quarkus.deployment.builditem.ApplicationArchivesBuildItem;
import io.quarkus.deployment.builditem.CombinedIndexBuildItem;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.deployment.builditem.GeneratedResourceBuildItem;
import io.quarkus.deployment.builditem.HotDeploymentWatchedFileBuildItem;
import io.quarkus.deployment.builditem.nativeimage.NativeImageResourceBuildItem;
import io.quarkus.deployment.pkg.builditem.OutputTargetBuildItem;
import io.quarkus.vertx.http.deployment.HttpRootPathBuildItem;
import io.quarkus.vertx.http.deployment.RouteBuildItem;
import io.quarkus.vertx.http.runtime.HandlerType;
import io.smallrye.asyncapi.core.api.AsyncApiConfig;
import io.smallrye.asyncapi.core.api.AsyncApiConfigImpl;
import io.smallrye.asyncapi.core.api.AsyncApiDocument;
import io.smallrye.asyncapi.core.runtime.AsyncApiFormat;
import io.smallrye.asyncapi.core.runtime.AsyncApiProcessor;
import io.smallrye.asyncapi.core.runtime.AsyncApiStaticFile;
import io.smallrye.asyncapi.core.runtime.io.AsyncApiSerializer;
import io.smallrye.asyncapi.core.runtime.scanner.AnnotationScannerExtension;
import io.smallrye.asyncapi.core.runtime.scanner.AsyncApiAnnotationScanner;
import io.smallrye.asyncapi.core.runtime.scanner.FilteredIndexView;
import io.smallrye.asyncapi.spec.AASFilter;
import io.smallrye.asyncapi.spec.models.AsyncAPI;

/**
 * The main AsyncAPI Processor. The result is added to the deployable unit to be loaded at runtime.
 */
public class AsyncApiVertxProcessor {

    private static final String FEATURE = "smallrye-asyncapi";

    private static final String META_INF_ASYNCAPI_YAML = "META-INF/asyncapi.yaml";

    private static final String WEB_INF_CLASSES_META_INF_ASYNCAPI_YAML = "WEB-INF/classes/META-INF/asyncapi.yaml";

    private static final String META_INF_ASYNCAPI_YML = "META-INF/asyncapi.yml";

    private static final String WEB_INF_CLASSES_META_INF_ASYNCAPI_YML = "WEB-INF/classes/META-INF/asyncapi.yml";

    private static final String META_INF_ASYNCAPI_JSON = "META-INF/asyncapi.json";

    private static final String WEB_INF_CLASSES_META_INF_ASYNCAPI_JSON = "WEB-INF/classes/META-INF/asyncapi.json";

    SmallRyeAsyncApiConfig asyncApiConfig;

    public AsyncApiVertxProcessor() {
    }

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    List<HotDeploymentWatchedFileBuildItem> configFiles() {
        return Stream
                .of(META_INF_ASYNCAPI_YAML, WEB_INF_CLASSES_META_INF_ASYNCAPI_YAML, META_INF_ASYNCAPI_YML,
                        WEB_INF_CLASSES_META_INF_ASYNCAPI_YML, META_INF_ASYNCAPI_JSON, WEB_INF_CLASSES_META_INF_ASYNCAPI_JSON)
                .map(HotDeploymentWatchedFileBuildItem::new)
                .collect(Collectors.toList());
    }

    @BuildStep
    @Record(ExecutionTime.STATIC_INIT)
    RouteBuildItem createHandler(AsyncApiRecorder recorder) {
        return new RouteBuildItem("/asyncapi", new AsyncApiHandler(), HandlerType.BLOCKING);
    }

    @BuildStep
    AsyncApiFilteredIndexViewBuildItem okAsyncApiIndex(CombinedIndexBuildItem indexBuildItem,
            BeanArchiveIndexBuildItem beanBuildItem) {
        CompositeIndex index = CompositeIndex.create(indexBuildItem.getIndex(), beanBuildItem.getIndex());
        return new AsyncApiFilteredIndexViewBuildItem(
                new FilteredIndexView(index, new AsyncApiConfigImpl(ConfigProvider.getConfig())));
    }

    @BuildStep
    void additionalBean(BuildProducer<AdditionalBeanBuildItem> additionalBeanProducer) {
        additionalBeanProducer.produce(AdditionalBeanBuildItem.builder()
                .addBeanClass(AsyncApiDocumentService.class)
                .setUnremovable()
                .build());
    }

    @BuildStep
    public void build(ApplicationArchivesBuildItem archivesBuildItem,
            BuildProducer<GeneratedResourceBuildItem> resourceBuildItemBuildProducer,
            BuildProducer<NativeImageResourceBuildItem> nativeImageResources,
            AsyncApiFilteredIndexViewBuildItem asyncApiFilteredIndexViewBuildItem,
            List<AddToAsyncAPIDefinitionBuildItem> asyncAPIBuildItems,
            HttpRootPathBuildItem httpRootPathBuildItem, OutputTargetBuildItem out) throws Exception {

        FilteredIndexView index = asyncApiFilteredIndexViewBuildItem.getIndex();

        AsyncAPI staticModel = generateStaticModel(archivesBuildItem);
        AsyncAPI annotationModel = generateAnnotationModel(index, httpRootPathBuildItem);

        AsyncApiDocument finalDocument = loadDocument(staticModel, annotationModel, asyncAPIBuildItems);

        boolean shouldStore = asyncApiConfig.storeSchemaDirectory.isPresent();
        for (AsyncApiFormat format : AsyncApiFormat.values()) {
            String name = AsyncApiConstants.BASE_NAME + format;

            byte[] schemaDocument = AsyncApiSerializer.serialize(finalDocument.get(), format)
                    .getBytes(StandardCharsets.UTF_8);

            resourceBuildItemBuildProducer.produce(new GeneratedResourceBuildItem(name, schemaDocument));
            nativeImageResources.produce(new NativeImageResourceBuildItem(name));

            if (shouldStore) {
                storeGeneratedSchema(out, schemaDocument, format);
            }
        }
    }

    private void storeGeneratedSchema(OutputTargetBuildItem out, byte[] schemaDocument, AsyncApiFormat format)
            throws IOException {
        Path directory = asyncApiConfig.storeSchemaDirectory.get();

        Path outputDirectory = out.getOutputDirectory();

        if (!directory.isAbsolute() && outputDirectory != null) {
            directory = Paths.get(outputDirectory.getParent()
                    .toString(), directory.toString());
        }

        if (!Files.exists(directory)) {
            Files.createDirectories(directory);
        }

        Path file = Paths.get(directory.toString(), "asyncapi." + format.toString()
                .toLowerCase());
        if (!Files.exists(file)) {
            Files.createFile(file);
        }
        Files.write(file, schemaDocument, StandardOpenOption.WRITE);
    }

    private AsyncAPI generateStaticModel(ApplicationArchivesBuildItem archivesBuildItem) throws IOException {
        Result result = findStaticModel(archivesBuildItem);
        if (result != null) {
            try (InputStream is = Files.newInputStream(result.path);
                    AsyncApiStaticFile staticFile = new AsyncApiStaticFile(is, result.format)) {
                return AsyncApiProcessor.modelFromStaticFile(staticFile);
            }
        }
        return null;
    }

    private AsyncAPI generateAnnotationModel(IndexView indexView, HttpRootPathBuildItem httpRootPathBuildItem) {
        Config config = ConfigProvider.getConfig();
        AsyncApiConfig asyncApiConfig = new AsyncApiConfigImpl(config);

        List<AnnotationScannerExtension> extensions = new ArrayList<>();

        String defaultPath = httpRootPathBuildItem.getRootPath();

        if (defaultPath != null && !"/".equals(defaultPath)) {
            extensions.add(new CustomPathExtension(defaultPath));
        }

        AsyncApiAnnotationScanner asyncApiAnnotationScanner = new AsyncApiAnnotationScanner(asyncApiConfig, indexView,
                extensions);
        return asyncApiAnnotationScanner.scan(getScanners());
    }

    private Result findStaticModel(ApplicationArchivesBuildItem archivesBuildItem) {
        // Check for the file in both META-INF and WEB-INF/classes/META-INF
        AsyncApiFormat format = AsyncApiFormat.YAML;
        Path resourcePath = archivesBuildItem.getRootArchive()
                .getChildPath(META_INF_ASYNCAPI_YAML);

        if (resourcePath == null) {
            resourcePath = archivesBuildItem.getRootArchive()
                    .getChildPath(WEB_INF_CLASSES_META_INF_ASYNCAPI_YAML);
        }

        if (resourcePath == null) {
            resourcePath = archivesBuildItem.getRootArchive()
                    .getChildPath(META_INF_ASYNCAPI_YML);
        }

        if (resourcePath == null) {
            resourcePath = archivesBuildItem.getRootArchive()
                    .getChildPath(WEB_INF_CLASSES_META_INF_ASYNCAPI_YML);
        }

        if (resourcePath == null) {
            resourcePath = archivesBuildItem.getRootArchive()
                    .getChildPath(META_INF_ASYNCAPI_JSON);
            format = AsyncApiFormat.JSON;
        }

        if (resourcePath == null) {
            resourcePath = archivesBuildItem.getRootArchive()
                    .getChildPath(WEB_INF_CLASSES_META_INF_ASYNCAPI_JSON);
            format = AsyncApiFormat.JSON;
        }

        if (resourcePath == null) {
            return null;
        }

        return new Result(format, resourcePath);
    }

    static class Result {

        final AsyncApiFormat format;

        final Path path;

        Result(final AsyncApiFormat format, final Path path) {
            this.format = format;
            this.path = path;
        }
    }

    private AsyncApiDocument loadDocument(AsyncAPI staticModel, AsyncAPI annotationModel,
            List<AddToAsyncAPIDefinitionBuildItem> asyncAPIBuildItems) {
        Config config = ConfigProvider.getConfig();
        AsyncApiConfig openApiConfig = new AsyncApiConfigImpl(config);

        AsyncAPI readerModel = AsyncApiProcessor.modelFromReader(openApiConfig, Thread.currentThread()
                .getContextClassLoader());

        AsyncApiDocument document = createDocument(openApiConfig);
        if (annotationModel != null) {
            document.modelFromAnnotations(annotationModel);
        }
        document.modelFromReader(readerModel);
        document.modelFromStaticFile(staticModel);

        document.filter(filter(openApiConfig));
        for (AddToAsyncAPIDefinitionBuildItem asyncAPIBuildItem : asyncAPIBuildItems) {
            AASFilter otherExtensionFilter = asyncAPIBuildItem.getAASFilter();
            document.filter(otherExtensionFilter);
        }
        document.initialize();

        return document;
    }

    private AsyncApiDocument createDocument(AsyncApiConfig openApiConfig) {
        AsyncApiDocument document = AsyncApiDocument.INSTANCE;
        document.reset();
        document.config(openApiConfig);
        return document;
    }

    private AASFilter filter(AsyncApiConfig openApiConfig) {
        return AsyncApiProcessor.getFilter(openApiConfig, Thread.currentThread()
                .getContextClassLoader());
    }

    private String[] getScanners() {
        List<String> scanners = new ArrayList<>();
        // scanners.add(REACTIVE_MESSAGING);
        return scanners.toArray(new String[] {});
    }
}
