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

import io.smallrye.asyncapi.core.api.models.binding.mqtt.LastWillImpl;
import io.smallrye.asyncapi.core.runtime.io.IoLogging;
import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.util.JandexUtil;
import io.smallrye.asyncapi.spec.models.binding.mqtt.LastWill;

public class LastWillReader {

    public LastWillReader() {
    }

    public static LastWill readLastWill(final AnnotationValue annotationValue) {
        if (annotationValue == null) {
            return null;
        }

        IoLogging.logger.singleAnnotation("@MQTTServerBinding");

        AnnotationInstance annotationInstance = annotationValue.asNested();

        LastWill lastWill = new LastWillImpl();
        lastWill.setTopic(JandexUtil.stringValue(annotationInstance, MQTTServerBindingConstant.PROP_TOPIC));
        lastWill.setQos(JandexUtil.intValue(annotationInstance, MQTTServerBindingConstant.PROP_QOS));
        lastWill.setMessage(JandexUtil.stringValue(annotationInstance, MQTTServerBindingConstant.PROP_MESSAGE));
        lastWill.setRetain(JandexUtil.booleanValue(annotationInstance, MQTTServerBindingConstant.PROP_RETAIN).orElse(false));

        return lastWill;
    }

    public static LastWill readLastWill(final JsonNode node) {
        if (node == null) {
            return null;
        }

        IoLogging.logger.singleJsonNode("MQTTServerBinding");

        LastWill lastWill = new LastWillImpl();
        lastWill.setTopic(JsonUtil.stringProperty(node, MQTTServerBindingConstant.PROP_TOPIC));
        lastWill.setQos(JsonUtil.intProperty(node, MQTTServerBindingConstant.PROP_QOS));
        lastWill.setMessage(JsonUtil.stringProperty(node, MQTTServerBindingConstant.PROP_MESSAGE));
        lastWill.setRetain(JsonUtil.booleanProperty(node, MQTTServerBindingConstant.PROP_RETAIN).orElse(false));

        return lastWill;
    }
}
