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
package io.smallrye.asyncapi.core.runtime.io.message;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.AnnotationValue;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import io.smallrye.asyncapi.core.api.models.message.MessageTraitImpl;
import io.smallrye.asyncapi.core.runtime.io.IoLogging;
import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.io.Referenceable;
import io.smallrye.asyncapi.core.runtime.io.bindings.MessageBindingsReader;
import io.smallrye.asyncapi.core.runtime.io.correlationId.CorrelationIdReader;
import io.smallrye.asyncapi.core.runtime.io.externaldocs.ExternalDocsReader;
import io.smallrye.asyncapi.core.runtime.io.schema.SchemaFactory;
import io.smallrye.asyncapi.core.runtime.io.schema.SchemaReader;
import io.smallrye.asyncapi.core.runtime.io.tag.TagReader;
import io.smallrye.asyncapi.core.runtime.scanner.spi.AnnotationScannerContext;
import io.smallrye.asyncapi.core.runtime.util.JandexUtil;
import io.smallrye.asyncapi.spec.models.message.MessageTrait;

public class MessageTraitReader {

    public static Optional<List<MessageTrait>> readMessageTraits(final AnnotationScannerContext context,
            final AnnotationValue annotationValue) {
        if (annotationValue == null) {
            return Optional.empty();
        }
        IoLogging.logger.annotation("MessageTraits");

        AnnotationInstance[] nestedArray = annotationValue.asNestedArray();
        List<MessageTrait> traits = new ArrayList<>();
        for (AnnotationInstance messageTraitAnno : nestedArray) {
            MessageTrait messageTrait = readMessageTrait(context, messageTraitAnno);
            String name = JandexUtil.stringValue(messageTraitAnno, MessageConstant.PROP_NAME);

            if (name == null && JandexUtil.isRef(messageTraitAnno)) {
                name = JandexUtil.nameFromRef(messageTraitAnno);
            }
            if (name != null) {
                traits.add(messageTrait);
            }
        }

        return Optional.of(traits);
    }

    public static MessageTrait readMessageTrait(final AnnotationScannerContext context, final AnnotationInstance nested) {
        if (nested == null) {
            return null;
        }
        IoLogging.logger.annotation("@MessageTrait");

        MessageTrait messageTrait = new MessageTraitImpl();
        messageTrait.setHeaders(SchemaFactory.readSchema(context, nested.value(MessageConstant.PROP_HEADERS)));
        messageTrait.setCorrelationID(CorrelationIdReader.readCorrelationID(nested.value(MessageConstant.PROP_CORRELATION_ID)));
        messageTrait.setSchemaFormat(JandexUtil.stringValue(nested, MessageConstant.PROP_SCHEME_FORMAT));
        messageTrait.setContentType(JandexUtil.stringValue(nested, MessageConstant.PROP_CONTENT_TYPE));
        messageTrait.setName(JandexUtil.stringValue(nested, MessageConstant.PROP_NAME));
        messageTrait.setTitle(JandexUtil.stringValue(nested, MessageConstant.PROP_TITLE));
        messageTrait.setSummary(JandexUtil.stringValue(nested, MessageConstant.PROP_SUMMARY));
        messageTrait.setDescription(JandexUtil.stringValue(nested, MessageConstant.PROP_DESCRIPTION));
        messageTrait.setTags(TagReader.readTags(context, nested.value(MessageConstant.PROP_TAGS)).orElse(null));
        messageTrait
                .setBindings(MessageBindingsReader.readMessageBindings(context, nested.value(MessageConstant.PROP_BINDINGS)));
        messageTrait.setExample(JandexUtil.stringListValue(nested, MessageConstant.PROP_EXAMPLE).orElse(null));
        messageTrait.setExternalDocumentation(
                ExternalDocsReader.readExternalDocs(context, nested.value(MessageConstant.PROP_EXTERNAL_DOCS)));
        messageTrait.setRef(JandexUtil.stringValue(nested, MessageConstant.PROP_REF));

        return messageTrait;
    }

    public static Optional<List<MessageTrait>> readMessageTraits(final JsonNode node) {
        if (node != null && node.isArray()) {
            IoLogging.logger.singleJsonNode("MessageTraits");

            List<MessageTrait> rval = new ArrayList<>(node.size());
            ArrayNode nodes = (ArrayNode) node;
            for (JsonNode messageTraitNode : nodes) {
                rval.add(readMessageTrait(messageTraitNode));
            }

            return Optional.of(rval);
        }

        return Optional.empty();
    }

    public static MessageTrait readMessageTrait(final JsonNode node) {
        if (node == null) {
            return null;
        }
        IoLogging.logger.singleJsonNode("MessageTrait");

        MessageTrait messageTrait = new MessageTraitImpl();
        messageTrait.setHeaders(SchemaReader.readSchema(node.get(MessageConstant.PROP_HEADERS)));
        messageTrait.setCorrelationID(CorrelationIdReader.readCorrelationID(node.get(MessageConstant.PROP_CORRELATION_ID)));
        messageTrait.setSchemaFormat(JsonUtil.stringProperty(node, MessageConstant.PROP_SCHEME_FORMAT));
        messageTrait.setContentType(JsonUtil.stringProperty(node, MessageConstant.PROP_CONTENT_TYPE));
        messageTrait.setName(JsonUtil.stringProperty(node, MessageConstant.PROP_NAME));
        messageTrait.setTitle(JsonUtil.stringProperty(node, MessageConstant.PROP_TITLE));
        messageTrait.setSummary(JsonUtil.stringProperty(node, MessageConstant.PROP_SUMMARY));
        messageTrait.setDescription(JsonUtil.stringProperty(node, MessageConstant.PROP_DESCRIPTION));
        messageTrait.setTags(TagReader.readTags(node.get(MessageConstant.PROP_TAGS)).orElse(null));

        JsonNode exampleNode = node.get(MessageConstant.PROP_EXAMPLE);
        if (exampleNode != null && exampleNode.isArray()) {
            List<String> examples = new ArrayList<>(exampleNode.size());
            for (JsonNode n : exampleNode) {
                examples.add(n.asText());
            }
            messageTrait.setExample(examples);
        }

        messageTrait.setBindings(MessageBindingsReader.readMessageBindings(node.get(MessageConstant.PROP_BINDINGS)));
        messageTrait
                .setExternalDocumentation(ExternalDocsReader.readExternalDocs(node.get(MessageConstant.PROP_EXTERNAL_DOCS)));
        messageTrait.setRef(JsonUtil.stringProperty(node, Referenceable.PROP_$REF));

        return messageTrait;
    }

}
