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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.AnnotationValue;
import org.jboss.jandex.MethodInfo;

import com.fasterxml.jackson.databind.JsonNode;

import io.smallrye.asyncapi.core.api.models.message.MessageImpl;
import io.smallrye.asyncapi.core.runtime.io.IoLogging;
import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.io.Referenceable;
import io.smallrye.asyncapi.core.runtime.io.bindings.MessageBindingsReader;
import io.smallrye.asyncapi.core.runtime.io.correlationId.CorrelationIdReader;
import io.smallrye.asyncapi.core.runtime.io.extension.ExtensionConstant;
import io.smallrye.asyncapi.core.runtime.io.externaldocs.ExternalDocsReader;
import io.smallrye.asyncapi.core.runtime.io.schema.SchemaFactory;
import io.smallrye.asyncapi.core.runtime.io.schema.SchemaReader;
import io.smallrye.asyncapi.core.runtime.io.tag.TagReader;
import io.smallrye.asyncapi.core.runtime.scanner.spi.AnnotationScannerContext;
import io.smallrye.asyncapi.core.runtime.util.JandexUtil;
import io.smallrye.asyncapi.spec.models.message.Message;

public class MessageReader {

    public MessageReader() {
    }

    public static AnnotationInstance getMessageAnnotation(final MethodInfo method) {
        return method.annotation(MessageConstant.DOTNAME_MESSAGE);
    }

    public static Optional<Map<String, Message>> readMessages(final AnnotationScannerContext context,
            final AnnotationValue annotationValue) {
        if (annotationValue != null) {
            IoLogging.logger.annotationsArray("Message");

            AnnotationInstance[] nestedArray = annotationValue.asNestedArray();

            HashMap<String, Message> messages = new HashMap<>();
            for (AnnotationInstance annotationInstance : nestedArray) {
                Message message = readMessage(context, annotationInstance);
                String name = JandexUtil.stringValue(annotationInstance, MessageConstant.PROP_NAME);

                if (name == null && JandexUtil.isRef(annotationInstance)) {
                    name = JandexUtil.nameFromRef(annotationInstance);
                }
                if (name != null) {
                    messages.put(message.getName(), message);
                }
            }
            return Optional.of(messages);
        }
        return Optional.empty();
    }

    public static Message readMessage(final AnnotationScannerContext context, final AnnotationInstance nested) {
        if (nested == null) {
            return null;
        }
        IoLogging.logger.annotation("@Message");

        Message message = createMessage(context, nested);

        return message;
    }

    public static Message readMessage(final AnnotationScannerContext context, final AnnotationValue annotationValue) {
        if (annotationValue == null) {
            return null;
        }
        IoLogging.logger.annotation("@Message");
        AnnotationInstance nested = annotationValue.asNested();

        Message message = createMessage(context, nested);

        return message;
    }

    private static Message createMessage(final AnnotationScannerContext context, final AnnotationInstance nested) {
        Message message = new MessageImpl();
        message.setHeaders(SchemaFactory.readSchema(context, nested.value(MessageConstant.PROP_HEADERS)));
        message.setPayload(SchemaFactory.readSchema(context, nested.value(MessageConstant.PROP_PAYLOAD)));
        message.setCorrelationID(CorrelationIdReader.readCorrelationID(nested.value(MessageConstant.PROP_CORRELATION_ID)));
        message.setSchemaFormat(JandexUtil.stringValue(nested, MessageConstant.PROP_SCHEME_FORMAT));
        message.setContentType(JandexUtil.stringValue(nested, MessageConstant.PROP_CONTENT_TYPE));
        message.setName(JandexUtil.stringValue(nested, MessageConstant.PROP_NAME));
        message.setTitle(JandexUtil.stringValue(nested, MessageConstant.PROP_TITLE));
        message.setSummary(JandexUtil.stringValue(nested, MessageConstant.PROP_SUMMARY));
        message.setDescription(JandexUtil.stringValue(nested, MessageConstant.PROP_DESCRIPTION));
        message.setTraits(
                MessageTraitReader.readMessageTraits(context, nested.value(MessageConstant.PROP_TRAITS)).orElse(null));
        message.setTags(TagReader.readTags(context, nested.value(MessageConstant.PROP_TAGS)).orElse(null));
        message.setBindings(MessageBindingsReader.readMessageBindings(context, nested.value(MessageConstant.PROP_BINDINGS)));
        message.setExample(JandexUtil.stringListValue(nested, MessageConstant.PROP_EXAMPLE).orElse(null));
        message.setExternalDocumentation(
                ExternalDocsReader.readExternalDocs(context, nested.value(MessageConstant.PROP_EXTERNAL_DOCS)));
        message.setRef(JandexUtil.refValue(nested, JandexUtil.RefType.MESSAGE));

        return message;
    }

    public static Map<String, Message> readMessages(final JsonNode node) {
        if (node == null) {
            return null;
        }
        IoLogging.logger.singleJsonNode("Message");

        HashMap<String, Message> messages = new HashMap<>();
        for (Iterator<String> iterator = node.fieldNames(); iterator.hasNext();) {
            String fieldName = iterator.next();
            if (!ExtensionConstant.isExtensionField(fieldName)) {
                JsonNode varNode = node.get(fieldName);
                messages.put(fieldName, readMessage(varNode));
            }
        }

        return messages;
    }

    public static Message readMessage(final JsonNode node) {
        if (node == null || !node.isObject()) {
            return null;
        }
        IoLogging.logger.singleJsonNode("Message");

        Message message = new MessageImpl();
        message.setRef(JsonUtil.stringProperty(node, Referenceable.PROP_$REF));
        message.setHeaders(SchemaReader.readSchema(node.get(MessageConstant.PROP_HEADERS)));
        message.setPayload(SchemaReader.readSchema(node.get(MessageConstant.PROP_PAYLOAD)));
        message.setCorrelationID(CorrelationIdReader.readCorrelationID(node.get(MessageConstant.PROP_CORRELATION_ID)));
        message.setSchemaFormat(JsonUtil.stringProperty(node, MessageConstant.PROP_SCHEME_FORMAT));
        message.setContentType(JsonUtil.stringProperty(node, MessageConstant.PROP_CONTENT_TYPE));
        message.setName(JsonUtil.stringProperty(node, MessageConstant.PROP_NAME));
        message.setTitle(JsonUtil.stringProperty(node, MessageConstant.PROP_TITLE));
        message.setSummary(JsonUtil.stringProperty(node, MessageConstant.PROP_SUMMARY));
        message.setDescription(JsonUtil.stringProperty(node, MessageConstant.PROP_DESCRIPTION));
        message.setTraits(MessageTraitReader.readMessageTraits(node.get(MessageConstant.PROP_TRAITS))
                .orElse(null));

        JsonNode exampleNode = node.get(MessageConstant.PROP_EXAMPLE);
        if (exampleNode != null && exampleNode.isArray()) {
            List<String> examples = new ArrayList<>(exampleNode.size());
            for (JsonNode n : exampleNode) {
                examples.add(n.asText());
            }
            message.setExample(examples);
        }

        message.setBindings(MessageBindingsReader.readMessageBindings(node.get(MessageConstant.PROP_BINDINGS)));
        message.setTags(TagReader.readTags(node.get(MessageConstant.PROP_TAGS))
                .orElse(null));
        message.setExternalDocumentation(ExternalDocsReader.readExternalDocs(node.get(MessageConstant.PROP_EXTERNAL_DOCS)));

        return message;
    }
}
