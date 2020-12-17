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
package io.smallrye.asyncapi.core.runtime.io.bindings.mqtt.operation;

import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.AnnotationValue;

import com.fasterxml.jackson.databind.JsonNode;

import io.smallrye.asyncapi.core.api.models.binding.mqtt.MQTTOperationBindingImpl;
import io.smallrye.asyncapi.core.runtime.io.IoLogging;
import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.util.JandexUtil;
import io.smallrye.asyncapi.spec.models.binding.mqtt.MQTTOperationBinding;

public class MQTTOperationBindingReader {

    public MQTTOperationBindingReader() {
    }

    public static MQTTOperationBinding readOperationBinding(final AnnotationValue annotationValue) {
        if (annotationValue == null) {
            return null;
        }

        IoLogging.logger.singleAnnotation("@MQTTOperationBinding");

        AnnotationInstance annotationInstance = annotationValue.asNested();

        MQTTOperationBinding binding = new MQTTOperationBindingImpl();
        binding.setQos(JandexUtil.intValue(annotationInstance, MQTTOperationBindingConstant.PROP_QOS));
        binding.setRetain(JandexUtil.booleanValue(annotationInstance, MQTTOperationBindingConstant.PROP_RETAIN).orElse(false));
        binding.setBindingVersion(
                JandexUtil.stringValue(annotationInstance, MQTTOperationBindingConstant.PROP_BINDING_VERSION));

        return binding;
    }

    public static MQTTOperationBinding readOperationBinding(final JsonNode node) {
        if (node == null) {
            return null;
        }

        IoLogging.logger.singleJsonNode("MQTTOperationBinding");

        MQTTOperationBinding binding = new MQTTOperationBindingImpl();
        binding.setQos(JsonUtil.intProperty(node, MQTTOperationBindingConstant.PROP_QOS));
        binding.setRetain(JsonUtil.booleanProperty(node, MQTTOperationBindingConstant.PROP_RETAIN).orElse(false));
        binding.setBindingVersion(JsonUtil.stringProperty(node, MQTTOperationBindingConstant.PROP_BINDING_VERSION));

        return binding;
    }
}
