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
package io.smallrye.asyncapi.core.runtime.io.bindings.amqp.channel;

import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.AnnotationValue;

import com.fasterxml.jackson.databind.JsonNode;

import io.smallrye.asyncapi.core.api.models.binding.amqp.AMQPChannelBindingImpl;
import io.smallrye.asyncapi.core.runtime.io.IoLogging;
import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.util.JandexUtil;
import io.smallrye.asyncapi.spec.models.binding.amqp.AMQPChannelBinding;

public class AMQPChannelBindingReader {

    public AMQPChannelBindingReader() {
    }

    public static AMQPChannelBinding readChannelBinding(final AnnotationValue annotationValue) {
        if (annotationValue == null) {
            return null;
        }

        IoLogging.logger.singleAnnotation("@AMQPChannelBinding");

        AnnotationInstance annotationInstance = annotationValue.asNested();

        AMQPChannelBinding binding = new AMQPChannelBindingImpl();

        String isValue = JandexUtil.stringValue(annotationInstance, AMQPChannelBindingConstants.PROP_IS);
        if (isValue == null || isValue.isEmpty()) {
            isValue = AMQPChannelBindingConstants.DEFAULT_IS;
        }
        binding.setIs(isValue);
        binding.setExchange(ExchangeReader.readExchange(annotationInstance.value(AMQPChannelBindingConstants.PROP_EXCHANGE)));
        binding.setQueue(QueueReader.readQueue(annotationInstance.value(AMQPChannelBindingConstants.PROP_QUEUE)));
        binding.setBindingVersion(JandexUtil.stringValue(annotationInstance, AMQPChannelBindingConstants.PROP_BINDING_VERSION));

        return binding;
    }

    public static AMQPChannelBinding readChannelBinding(final JsonNode node) {
        if (node == null) {
            return null;
        }

        IoLogging.logger.singleJsonNode("AMQPChannelBinding");

        AMQPChannelBinding binding = new AMQPChannelBindingImpl();
        binding.setIs(JsonUtil.stringProperty(node, AMQPChannelBindingConstants.PROP_IS));
        binding.setExchange(ExchangeReader.readExchange(node.get(AMQPChannelBindingConstants.PROP_EXCHANGE)));
        binding.setQueue(QueueReader.readQueue(node.get(AMQPChannelBindingConstants.PROP_QUEUE)));
        binding.setBindingVersion(JsonUtil.stringProperty(node, AMQPChannelBindingConstants.PROP_BINDING_VERSION));

        return binding;
    }
}
