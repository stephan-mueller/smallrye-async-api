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
package io.smallrye.asyncapi.core.runtime.io.bindings.kafka.operation;

import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.AnnotationValue;

import com.fasterxml.jackson.databind.JsonNode;

import io.smallrye.asyncapi.core.api.models.binding.kafka.KafkaOperationBindingImpl;
import io.smallrye.asyncapi.core.runtime.io.IoLogging;
import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.io.schema.SchemaFactory;
import io.smallrye.asyncapi.core.runtime.io.schema.SchemaReader;
import io.smallrye.asyncapi.core.runtime.util.JandexUtil;
import io.smallrye.asyncapi.spec.models.binding.kafka.KafkaOperationBinding;

public class KafkaOperationBindingReader {

    public KafkaOperationBindingReader() {
    }

    public static KafkaOperationBinding readOperationBinding(final AnnotationValue annotationValue) {
        if (annotationValue == null) {
            return null;
        }

        IoLogging.logger.singleAnnotation("@KafkaOperationBinding");

        AnnotationInstance annotationInstance = annotationValue.asNested();

        KafkaOperationBinding binding = new KafkaOperationBindingImpl();
        binding.setGroupId(
                SchemaFactory.readSchema(null, annotationInstance.value(KafkaOperationBindingConstant.PROP_GROUP_ID)));
        binding.setClientId(
                SchemaFactory.readSchema(null, annotationInstance.value(KafkaOperationBindingConstant.PROP_CLIENT_ID)));
        binding.setBindingVersion(
                JandexUtil.stringValue(annotationInstance, KafkaOperationBindingConstant.PROP_BINDING_VERSION));

        return binding;
    }

    public static KafkaOperationBinding readOperationBinding(final JsonNode node) {
        if (node == null) {
            return null;
        }

        IoLogging.logger.singleJsonNode("KafkaOperationBinding");

        KafkaOperationBinding binding = new KafkaOperationBindingImpl();
        binding.setGroupId(SchemaReader.readSchema(node.get(KafkaOperationBindingConstant.PROP_GROUP_ID)));
        binding.setClientId(SchemaReader.readSchema(node.get(KafkaOperationBindingConstant.PROP_CLIENT_ID)));
        binding.setBindingVersion(JsonUtil.stringProperty(node, KafkaOperationBindingConstant.PROP_BINDING_VERSION));

        return binding;
    }
}
