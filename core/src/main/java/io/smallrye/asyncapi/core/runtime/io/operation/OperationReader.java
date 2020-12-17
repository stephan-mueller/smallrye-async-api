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
package io.smallrye.asyncapi.core.runtime.io.operation;

import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.AnnotationValue;

import com.fasterxml.jackson.databind.JsonNode;

import io.smallrye.asyncapi.core.api.models.operation.OperationImpl;
import io.smallrye.asyncapi.core.runtime.io.IoLogging;
import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.io.bindings.OperationBindingsReader;
import io.smallrye.asyncapi.core.runtime.io.externaldocs.ExternalDocsReader;
import io.smallrye.asyncapi.core.runtime.io.message.MessageReader;
import io.smallrye.asyncapi.core.runtime.io.tag.TagReader;
import io.smallrye.asyncapi.core.runtime.scanner.spi.AnnotationScannerContext;
import io.smallrye.asyncapi.core.runtime.util.JandexUtil;
import io.smallrye.asyncapi.spec.models.operation.Operation;

public class OperationReader {

    public OperationReader() {
    }

    public static Operation readOperation(final AnnotationScannerContext context, final AnnotationValue annotationValue) {
        if (annotationValue == null) {
            return null;
        }
        IoLogging.logger.annotation("@Operation");
        AnnotationInstance nested = annotationValue.asNested();

        Operation operation = new OperationImpl();
        operation.setOperationId(JandexUtil.stringValue(nested, OperationConstant.PROP_OPERATION_ID));
        operation.setSummary(JandexUtil.stringValue(nested, OperationConstant.PROP_SUMMARY));
        operation.setDescription(JandexUtil.stringValue(nested, OperationConstant.PROP_DESCRIPTION));
        operation.setTags(TagReader.readTags(context, nested.value(OperationConstant.PROP_TAGS)).orElse(null));
        operation.setBindings(
                OperationBindingsReader.readOperationBindings(context, nested.value(OperationConstant.PROP_BINDINGS)));
        operation.setOperationTraits(
                OperationTraitReader.readOperationTraits(context, nested.value(OperationConstant.PROP_TRAITS)).orElse(null));
        operation.setMessage(MessageReader.readMessage(context, nested.value(OperationConstant.PROP_MESSAGE)));
        operation.setExternalDocumentation(
                ExternalDocsReader.readExternalDocs(context, nested.value(OperationConstant.PROP_EXTERNAL_DOCS)));

        return operation;
    }

    public static Operation readOperation(final JsonNode node) {
        if (node == null) {
            return null;
        }
        IoLogging.logger.singleJsonNode("Operation");

        Operation operation = new OperationImpl();
        operation.setOperationId(JsonUtil.stringProperty(node, OperationConstant.PROP_OPERATION_ID));
        operation.setSummary(JsonUtil.stringProperty(node, OperationConstant.PROP_SUMMARY));
        operation.setDescription(JsonUtil.stringProperty(node, OperationConstant.PROP_DESCRIPTION));
        operation.setTags(TagReader.readTags(node.get(OperationConstant.PROP_TAGS)).orElse(null));
        operation.setBindings(OperationBindingsReader.readOperationBindings(node.get(OperationConstant.PROP_BINDINGS)));
        operation.setOperationTraits(
                OperationTraitReader.readOperationTraits(node.get(OperationConstant.PROP_TRAITS)).orElse(null));
        operation.setMessage(MessageReader.readMessage(node.get(OperationConstant.PROP_MESSAGE)));
        operation.setExternalDocumentation(ExternalDocsReader.readExternalDocs(node.get(OperationConstant.PROP_EXTERNAL_DOCS)));

        return operation;
    }
}
