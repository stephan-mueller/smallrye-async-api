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
package io.smallrye.asyncapi.core.runtime.io.bindings.amqp.message;

import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.AnnotationValue;

import com.fasterxml.jackson.databind.JsonNode;

import io.smallrye.asyncapi.core.api.models.binding.amqp.AMQPMessageBindingImpl;
import io.smallrye.asyncapi.core.runtime.io.IoLogging;
import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.util.JandexUtil;
import io.smallrye.asyncapi.spec.models.binding.amqp.AMQPMessageBinding;

public class AMQPMessageBindingReader {

    public AMQPMessageBindingReader() {
    }

    public static AMQPMessageBinding readMessageBinding(final AnnotationValue annotationValue) {
        if (annotationValue == null) {
            return null;
        }

        IoLogging.logger.singleAnnotation("@AMQPMessageBinding");

        AnnotationInstance annotationInstance = annotationValue.asNested();

        AMQPMessageBinding binding = new AMQPMessageBindingImpl();

        binding.setContentEncoding(
                JandexUtil.stringValue(annotationInstance, AMQPMessageBindingConstant.PROP_CONTENT_ENCODING));
        binding.setMessageType(JandexUtil.stringValue(annotationInstance, AMQPMessageBindingConstant.PROP_MESSAGE_TYPE));
        binding.setBindingVersion(JandexUtil.stringValue(annotationInstance, AMQPMessageBindingConstant.PROP_BINDING_VERSION));

        return binding;
    }

    public static AMQPMessageBinding readMessageBinding(final JsonNode node) {
        if (node == null) {
            return null;
        }

        IoLogging.logger.singleJsonNode("AMQPChannelBinding");

        AMQPMessageBinding binding = new AMQPMessageBindingImpl();
        binding.setContentEncoding(JsonUtil.stringProperty(node, AMQPMessageBindingConstant.PROP_CONTENT_ENCODING));
        binding.setMessageType(JsonUtil.stringProperty(node, AMQPMessageBindingConstant.PROP_MESSAGE_TYPE));
        binding.setBindingVersion(JsonUtil.stringProperty(node, AMQPMessageBindingConstant.PROP_BINDING_VERSION));

        return binding;
    }
}
