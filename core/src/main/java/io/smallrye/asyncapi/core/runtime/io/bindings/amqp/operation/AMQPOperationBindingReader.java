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
package io.smallrye.asyncapi.core.runtime.io.bindings.amqp.operation;

import java.util.ArrayList;
import java.util.List;

import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.AnnotationValue;

import com.fasterxml.jackson.databind.JsonNode;

import io.smallrye.asyncapi.core.api.models.binding.amqp.AMQPOperationBindingImpl;
import io.smallrye.asyncapi.core.runtime.io.IoLogging;
import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.io.bindings.amqp.channel.AMQPChannelBindingConstants;
import io.smallrye.asyncapi.core.runtime.util.JandexUtil;
import io.smallrye.asyncapi.spec.models.binding.amqp.AMQPOperationBinding;

public class AMQPOperationBindingReader {

    public AMQPOperationBindingReader() {
    }

    public static AMQPOperationBinding readOperationBinding(final AnnotationValue annotationValue) {
        if (annotationValue == null) {
            return null;
        }

        IoLogging.logger.singleAnnotation("@AMQPOperationBinding");

        AnnotationInstance annotationInstance = annotationValue.asNested();

        AMQPOperationBinding binding = new AMQPOperationBindingImpl();
        binding.setExpiration(JandexUtil.intValue(annotationInstance, AMQPOperationBindingConstants.PROP_USER_ID));
        binding.setUserId(JandexUtil.stringValue(annotationInstance, AMQPOperationBindingConstants.PROP_USER_ID));
        binding.setCc(JandexUtil.stringListValue(annotationInstance, AMQPOperationBindingConstants.PROP_CC).orElse(null));
        binding.setPriority(JandexUtil.intValue(annotationInstance, AMQPOperationBindingConstants.PROP_PRIORITY));
        binding.setDeliveryMode(JandexUtil.intValue(annotationInstance, AMQPOperationBindingConstants.PROP_DELIVERY_MODE));
        binding.setMandatory(
                JandexUtil.booleanValue(annotationInstance, AMQPOperationBindingConstants.PROP_MANDATORY).orElse(false));
        binding.setBcc(JandexUtil.stringListValue(annotationInstance, AMQPOperationBindingConstants.PROP_BCC).orElse(null));
        binding.setReplyTo(JandexUtil.stringValue(annotationInstance, AMQPOperationBindingConstants.PROP_REPLY_TO));
        binding.setTimeStamp(
                JandexUtil.booleanValue(annotationInstance, AMQPOperationBindingConstants.PROP_TIME_STAMP).orElse(false));
        binding.setAck(JandexUtil.booleanValue(annotationInstance, AMQPOperationBindingConstants.PROP_ACK).orElse(false));
        binding.setBindingVersion(
                JandexUtil.stringValue(annotationInstance, AMQPOperationBindingConstants.PROP_BINDING_VERSION));

        return binding;
    }

    public static AMQPOperationBinding readOperationBinding(final JsonNode node) {
        if (node == null) {
            return null;
        }

        IoLogging.logger.singleJsonNode("AMQPChannelBinding");

        AMQPOperationBinding binding = new AMQPOperationBindingImpl();
        binding.setExpiration(JsonUtil.intProperty(node, AMQPOperationBindingConstants.PROP_EXPIRATION));
        binding.setUserId(JsonUtil.stringProperty(node, AMQPOperationBindingConstants.PROP_USER_ID));

        JsonNode ccNode = node.get(AMQPOperationBindingConstants.PROP_CC);
        if (ccNode != null && ccNode.isArray()) {
            List<String> ccs = new ArrayList<>(ccNode.size());
            for (JsonNode n : ccNode) {
                ccs.add(n.asText());
            }
            binding.setCc(ccs);
        }

        binding.setPriority(JsonUtil.intProperty(node, AMQPOperationBindingConstants.PROP_PRIORITY));
        binding.setDeliveryMode(JsonUtil.intProperty(node, AMQPOperationBindingConstants.PROP_DELIVERY_MODE));
        binding.setMandatory(JsonUtil.booleanProperty(node, AMQPOperationBindingConstants.PROP_DELIVERY_MODE).orElse(false));

        JsonNode bccNode = node.get(AMQPOperationBindingConstants.PROP_BCC);
        if (bccNode != null && bccNode.isArray()) {
            List<String> bccs = new ArrayList<>(bccNode.size());
            for (JsonNode n : bccNode) {
                bccs.add(n.asText());
            }
            binding.setBcc(bccs);
        }

        binding.setReplyTo(JsonUtil.stringProperty(node, AMQPOperationBindingConstants.PROP_REPLY_TO));
        binding.setTimeStamp(JsonUtil.booleanProperty(node, AMQPOperationBindingConstants.PROP_TIME_STAMP).orElse(false));
        binding.setAck(JsonUtil.booleanProperty(node, AMQPOperationBindingConstants.PROP_ACK).orElse(false));
        binding.setBindingVersion(JsonUtil.stringProperty(node, AMQPChannelBindingConstants.PROP_BINDING_VERSION));

        return binding;
    }
}
