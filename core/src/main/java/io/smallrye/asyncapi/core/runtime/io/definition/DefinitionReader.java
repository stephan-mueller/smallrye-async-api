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
package io.smallrye.asyncapi.core.runtime.io.definition;

import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.ClassInfo;

import com.fasterxml.jackson.databind.JsonNode;

import io.smallrye.asyncapi.core.runtime.io.IoLogging;
import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.io.channels.ChannelsReader;
import io.smallrye.asyncapi.core.runtime.io.components.ComponentsReader;
import io.smallrye.asyncapi.core.runtime.io.extension.ExtensionReader;
import io.smallrye.asyncapi.core.runtime.io.externaldocs.ExternalDocsConstant;
import io.smallrye.asyncapi.core.runtime.io.externaldocs.ExternalDocsReader;
import io.smallrye.asyncapi.core.runtime.io.info.InfoReader;
import io.smallrye.asyncapi.core.runtime.io.server.ServerReader;
import io.smallrye.asyncapi.core.runtime.io.tag.TagReader;
import io.smallrye.asyncapi.core.runtime.scanner.spi.AnnotationScannerContext;
import io.smallrye.asyncapi.core.runtime.util.JandexUtil;
import io.smallrye.asyncapi.spec.models.AsyncAPI;

/**
 * Reading the OpenAPIDefinition from an annotation or json
 */
public class DefinitionReader {

    private DefinitionReader() {
    }

    /**
     * Reads a AsyncAPI annotation.
     *
     * @param context the scanning context
     * @param asyncAPI AsyncAPIImpl
     * @param annotationInstance {@literal @}AsyncAPI annotation
     */
    public static void processDefinition(final AnnotationScannerContext context, final AsyncAPI asyncAPI,
            final AnnotationInstance annotationInstance) {
        IoLogging.logger.annotation("@AsyncAPI");

        asyncAPI.setAsyncapi(JandexUtil.stringValue(annotationInstance, DefinitionConstant.PROP_ASYNCAPI));
        asyncAPI.setIdentifier(JandexUtil.stringValue(annotationInstance, DefinitionConstant.PROP_IDENTIFIER));
        asyncAPI.setDefaultContentType(JandexUtil.stringValue(annotationInstance, DefinitionConstant.PROP_CONTENT_TYPE));
        asyncAPI.setInfo(InfoReader.readInfo(annotationInstance.value(DefinitionConstant.PROP_INFO)));
        asyncAPI.setServers(ServerReader.readServers(annotationInstance.value(DefinitionConstant.PROP_SERVERS)).orElse(null));
        asyncAPI.setChannels(ChannelsReader.readChannels(context, annotationInstance.value(DefinitionConstant.PROP_CHANNELS)));
        asyncAPI.setComponents(
                ComponentsReader.readComponents(context, annotationInstance.value(DefinitionConstant.PROP_COMPONENTS)));
        asyncAPI.setExternalDocs(ExternalDocsReader.readExternalDocs(context,
                annotationInstance.value(ExternalDocsConstant.PROP_EXTERNAL_DOCS)));
        asyncAPI.setTags(TagReader.readTags(context, annotationInstance.value(DefinitionConstant.PROP_TAGS)).orElse(null));
        asyncAPI.setExtensions(ExtensionReader.readExtensions(context, annotationInstance));
    }

    /**
     * Reads a AsyncAPI Json node.
     *
     * @param asyncAPI the AsyncAPI model
     * @param node the Json node
     */
    public static void processDefinition(final AsyncAPI asyncAPI, final JsonNode node) {
        IoLogging.logger.jsonNode("AsyncAPI");

        asyncAPI.setAsyncapi(JsonUtil.stringProperty(node, DefinitionConstant.PROP_ASYNCAPI));
        asyncAPI.setIdentifier(JsonUtil.stringProperty(node, DefinitionConstant.PROP_IDENTIFIER));
        asyncAPI.setDefaultContentType(JsonUtil.stringProperty(node, DefinitionConstant.PROP_CONTENT_TYPE));
        asyncAPI.setInfo(InfoReader.readInfo(node.get(DefinitionConstant.PROP_INFO)));
        asyncAPI.setServers(ServerReader.readServers(node.get(DefinitionConstant.PROP_SERVERS)).orElse(null));
        asyncAPI.setChannels(ChannelsReader.readChannelItems(node.get(DefinitionConstant.PROP_CHANNELS)));
        asyncAPI.setExternalDocs(ExternalDocsReader.readExternalDocs(node.get(ExternalDocsConstant.PROP_EXTERNAL_DOCS)));
        asyncAPI.setComponents(ComponentsReader.readComponents(node.get(DefinitionConstant.PROP_COMPONENTS)));
        asyncAPI.setTags(TagReader.readTags(node.get(DefinitionConstant.PROP_TAGS)).orElse(null));
        ExtensionReader.readExtensions(node, asyncAPI);
    }

    // helper methods for scanners
    public static AnnotationInstance getDefinitionAnnotation(final ClassInfo targetClass) {
        return JandexUtil.getClassAnnotation(targetClass, DefinitionConstant.DOTNAME_ASYNC_API);
    }
}
