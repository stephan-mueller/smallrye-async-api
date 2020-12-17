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
package io.smallrye.asyncapi.core.runtime.io.bindings.kafka.message;

import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.AnnotationValue;

import com.fasterxml.jackson.databind.JsonNode;

import io.smallrye.asyncapi.core.api.models.binding.kafka.KafkaMessageBindingImpl;
import io.smallrye.asyncapi.core.runtime.io.IoLogging;
import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.io.schema.SchemaFactory;
import io.smallrye.asyncapi.core.runtime.io.schema.SchemaReader;
import io.smallrye.asyncapi.core.runtime.util.JandexUtil;
import io.smallrye.asyncapi.spec.models.binding.kafka.KafkaMessageBinding;

public class KafkaMessageBindingReader {

    public KafkaMessageBindingReader() {
    }

    public static KafkaMessageBinding readMessageBinding(final AnnotationValue annotationValue) {
        if (annotationValue == null) {
            return null;
        }

        IoLogging.logger.singleAnnotation("@KafkaMessageBinding");

        AnnotationInstance annotationInstance = annotationValue.asNested();

        KafkaMessageBinding binding = new KafkaMessageBindingImpl();
        binding.setKey(SchemaFactory.readSchema(null, annotationInstance.value(KafkaMessageBindingConstant.PROP_KEY)));
        binding.setBindingVersion(JandexUtil.stringValue(annotationInstance, KafkaMessageBindingConstant.PROP_BINDING_VERSION));

        return binding;
    }

    public static KafkaMessageBinding readMessageBinding(final JsonNode node) {
        if (node == null) {
            return null;
        }

        IoLogging.logger.singleJsonNode("KafkaMessageBinding");

        KafkaMessageBinding binding = new KafkaMessageBindingImpl();
        binding.setKey(SchemaReader.readSchema(node.get(KafkaMessageBindingConstant.PROP_KEY)));
        binding.setBindingVersion(JsonUtil.stringProperty(node, KafkaMessageBindingConstant.PROP_BINDING_VERSION));

        return binding;
    }
}
