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
package io.smallrye.asyncapi.core.runtime.io.bindings.mqtt.server;

import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.AnnotationValue;

import com.fasterxml.jackson.databind.JsonNode;

import io.smallrye.asyncapi.core.api.models.binding.mqtt.MQTTServerBindingImpl;
import io.smallrye.asyncapi.core.runtime.io.IoLogging;
import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.util.JandexUtil;
import io.smallrye.asyncapi.spec.models.binding.mqtt.MQTTServerBinding;

public class MQTTServerBindingReader {

    public MQTTServerBindingReader() {
    }

    public static MQTTServerBinding readServerBinding(final AnnotationValue annotationValue) {
        if (annotationValue == null) {
            return null;
        }

        IoLogging.logger.singleAnnotation("@MQTTServerBinding");

        AnnotationInstance annotationInstance = annotationValue.asNested();

        MQTTServerBinding binding = new MQTTServerBindingImpl();
        binding.setClientId(JandexUtil.stringValue(annotationInstance, MQTTServerBindingConstant.PROP_CLIENT_ID));
        binding.setCleanSession(
                JandexUtil.booleanValue(annotationInstance, MQTTServerBindingConstant.PROP_CLEAN_SESSION).orElse(false));
        binding.setBindingVersion(JandexUtil.stringValue(annotationInstance, MQTTServerBindingConstant.PROP_BINDING_VERSION));
        binding.setLastWill(LastWillReader.readLastWill(annotationInstance.value(MQTTServerBindingConstant.PROP_LAST_WILL)));

        return binding;
    }

    public static MQTTServerBinding readServerBinding(final JsonNode node) {
        if (node == null) {
            return null;
        }

        IoLogging.logger.singleJsonNode("MQTTServerBinding");

        MQTTServerBinding binding = new MQTTServerBindingImpl();
        binding.setBindingVersion(JsonUtil.stringProperty(node, MQTTServerBindingConstant.PROP_BINDING_VERSION));
        binding.setClientId(JsonUtil.stringProperty(node, MQTTServerBindingConstant.PROP_CLIENT_ID));
        binding.setCleanSession(JsonUtil.booleanProperty(node, MQTTServerBindingConstant.PROP_CLEAN_SESSION).orElse(false));
        binding.setLastWill(LastWillReader.readLastWill(node.get(MQTTServerBindingConstant.PROP_LAST_WILL)));

        return binding;
    }
}
