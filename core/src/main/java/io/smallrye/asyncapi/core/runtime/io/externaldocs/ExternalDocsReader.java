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
package io.smallrye.asyncapi.core.runtime.io.externaldocs;

import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.AnnotationValue;

import com.fasterxml.jackson.databind.JsonNode;

import io.smallrye.asyncapi.core.api.models.ExternalDocumentationImpl;
import io.smallrye.asyncapi.core.runtime.io.IoLogging;
import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.io.extension.ExtensionReader;
import io.smallrye.asyncapi.core.runtime.scanner.spi.AnnotationScannerContext;
import io.smallrye.asyncapi.core.runtime.util.JandexUtil;
import io.smallrye.asyncapi.spec.models.ExternalDocumentation;

/**
 * An implementation of the {@link ExternalDocumentation} AsyncAPI model interface.
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0#externalDocumentationObject"
 */
public class ExternalDocsReader {

    private ExternalDocsReader() {
    }

    /**
     * Reads an ExternalDocumentation annotation.
     *
     * @param context scanning context
     * @param annotationValue the {@literal @}ExternalDocumentation annotation
     * @return ExternalDocumentation model
     */
    public static ExternalDocumentation readExternalDocs(final AnnotationScannerContext context,
            final AnnotationValue annotationValue) {
        if (annotationValue == null) {
            return null;
        }
        return readExternalDocs(context, annotationValue.asNested());
    }

    /**
     * Reads an ExternalDocumentation annotation.
     *
     * @param context scanning context
     * @param annotationInstance the {@literal @}ExternalDocumentation annotation
     * @return ExternalDocumentation model
     */
    public static ExternalDocumentation readExternalDocs(AnnotationScannerContext context,
            AnnotationInstance annotationInstance) {
        if (annotationInstance == null) {
            return null;
        }
        IoLogging.logger.annotation("@ExternalDocumentation");
        ExternalDocumentation externalDoc = new ExternalDocumentationImpl();
        externalDoc.setDescription(JandexUtil.stringValue(annotationInstance, ExternalDocsConstant.PROP_DESCRIPTION));
        externalDoc.setUrl(JandexUtil.stringValue(annotationInstance, ExternalDocsConstant.PROP_URL));
        externalDoc.setExtensions(ExtensionReader.readExtensions(context, annotationInstance));
        return externalDoc;
    }

    /**
     * Reads an {@link ExternalDocumentation} AsyncAPI node.
     *
     * @param node the json node
     * @return ExternalDocumentation model
     */
    public static ExternalDocumentation readExternalDocs(final JsonNode node) {
        if (node == null) {
            return null;
        }
        ExternalDocumentation externalDoc = new ExternalDocumentationImpl();
        externalDoc.setDescription(JsonUtil.stringProperty(node, ExternalDocsConstant.PROP_DESCRIPTION));
        externalDoc.setUrl(JsonUtil.stringProperty(node, ExternalDocsConstant.PROP_URL));
        ExtensionReader.readExtensions(node, externalDoc);
        return externalDoc;
    }
}
