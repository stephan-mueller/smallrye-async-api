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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.AnnotationValue;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import io.smallrye.asyncapi.core.api.models.operation.OperationTraitImpl;
import io.smallrye.asyncapi.core.runtime.io.IoLogging;
import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.io.Referenceable;
import io.smallrye.asyncapi.core.runtime.io.bindings.OperationBindingsReader;
import io.smallrye.asyncapi.core.runtime.io.externaldocs.ExternalDocsReader;
import io.smallrye.asyncapi.core.runtime.io.message.MessageReader;
import io.smallrye.asyncapi.core.runtime.io.tag.TagReader;
import io.smallrye.asyncapi.core.runtime.scanner.spi.AnnotationScannerContext;
import io.smallrye.asyncapi.core.runtime.util.JandexUtil;
import io.smallrye.asyncapi.spec.models.operation.OperationTrait;

public class OperationTraitReader {

    public OperationTraitReader() {
    }

    public static Optional<List<OperationTrait>> readOperationTraits(final AnnotationScannerContext context,
            final AnnotationValue annotationValue) {
        if (annotationValue == null) {
            return Optional.empty();
        }
        IoLogging.logger.annotation("OperationTraits");

        AnnotationInstance[] nestedArray = annotationValue.asNestedArray();
        List<OperationTrait> traits = new ArrayList<>();
        for (AnnotationInstance annotationInstance : nestedArray) {
            OperationTrait messageTrait = readOperationTrait(context, annotationInstance);
            String id = JandexUtil.stringValue(annotationInstance, OperationConstant.PROP_OPERATION_ID);

            if (id == null && JandexUtil.isRef(annotationInstance)) {
                id = JandexUtil.nameFromRef(annotationInstance);
            }
            if (id != null) {
                traits.add(messageTrait);
            }
        }

        return Optional.of(traits);
    }

    public static OperationTrait readOperationTrait(final AnnotationScannerContext context,
            final AnnotationInstance annotationInstance) {
        if (annotationInstance == null) {
            return null;
        }
        IoLogging.logger.annotation("@Operation");

        OperationTrait operationTrait = new OperationTraitImpl();
        operationTrait.setOperationId(JandexUtil.stringValue(annotationInstance, OperationConstant.PROP_OPERATION_ID));
        operationTrait.setSummary(JandexUtil.stringValue(annotationInstance, OperationConstant.PROP_SUMMARY));
        operationTrait.setDescription(JandexUtil.stringValue(annotationInstance, OperationConstant.PROP_DESCRIPTION));
        operationTrait.setTags(TagReader.readTags(context, annotationInstance.value(OperationConstant.PROP_TAGS)).orElse(null));
        operationTrait.setMessage(MessageReader.readMessage(context, annotationInstance.value(OperationConstant.PROP_MESSAGE)));
        operationTrait.setBindings(OperationBindingsReader.readOperationBindings(context,
                annotationInstance.value(OperationConstant.PROP_BINDINGS)));
        operationTrait.setExternalDocumentation(
                ExternalDocsReader.readExternalDocs(context, annotationInstance.value(OperationConstant.PROP_EXTERNAL_DOCS)));
        operationTrait.setRef(JandexUtil.stringValue(annotationInstance, OperationConstant.PROP_REF));

        return operationTrait;
    }

    public static Optional<List<OperationTrait>> readOperationTraits(final JsonNode node) {
        if (node != null && node.isArray()) {
            IoLogging.logger.singleJsonNode("OperationTraits");

            List<OperationTrait> rval = new ArrayList<>(node.size());
            ArrayNode nodes = (ArrayNode) node;
            for (JsonNode operationTraitNode : nodes) {
                rval.add(readOperationTrait(operationTraitNode));
            }

            return Optional.of(rval);
        }

        return Optional.empty();
    }

    public static OperationTrait readOperationTrait(final JsonNode node) {
        if (node == null) {
            return null;
        }
        IoLogging.logger.singleJsonNode("Operation");

        OperationTrait operationTrait = new OperationTraitImpl();
        operationTrait.setOperationId(JsonUtil.stringProperty(node, OperationConstant.PROP_OPERATION_ID));
        operationTrait.setSummary(JsonUtil.stringProperty(node, OperationConstant.PROP_SUMMARY));
        operationTrait.setDescription(JsonUtil.stringProperty(node, OperationConstant.PROP_DESCRIPTION));
        operationTrait.setTags(TagReader.readTags(node.get(OperationConstant.PROP_TAGS)).orElse(null));
        operationTrait.setMessage(MessageReader.readMessage(node.get(OperationConstant.PROP_MESSAGE)));
        operationTrait.setBindings(OperationBindingsReader.readOperationBindings(node.get(OperationConstant.PROP_BINDINGS)));
        operationTrait
                .setExternalDocumentation(ExternalDocsReader.readExternalDocs(node.get(OperationConstant.PROP_EXTERNAL_DOCS)));
        operationTrait.setRef(JsonUtil.stringProperty(node, Referenceable.PROP_$REF));

        return operationTrait;
    }
}
