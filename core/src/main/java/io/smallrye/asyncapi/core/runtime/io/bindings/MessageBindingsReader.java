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
package io.smallrye.asyncapi.core.runtime.io.bindings;

import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.AnnotationValue;

import com.fasterxml.jackson.databind.JsonNode;

import io.smallrye.asyncapi.core.api.models.binding.MessageBindingsImpl;
import io.smallrye.asyncapi.core.runtime.io.IoLogging;
import io.smallrye.asyncapi.core.runtime.io.bindings.amqp.message.AMQPMessageBindingReader;
import io.smallrye.asyncapi.core.runtime.io.bindings.http.message.HTTPMessageBindingReader;
import io.smallrye.asyncapi.core.runtime.io.bindings.kafka.message.KafkaMessageBindingReader;
import io.smallrye.asyncapi.core.runtime.io.bindings.mqtt.message.MQTTMessageBindingReader;
import io.smallrye.asyncapi.core.runtime.scanner.spi.AnnotationScannerContext;
import io.smallrye.asyncapi.spec.models.binding.MessageBindings;

public class MessageBindingsReader {

    public MessageBindingsReader() {
    }

    public static MessageBindings readMessageBindings(final AnnotationScannerContext context,
            final AnnotationValue annotationValue) {
        if (annotationValue == null) {
            return null;
        }

        IoLogging.logger.annotationsArray("@MessageBindings");
        AnnotationInstance nested = annotationValue.asNested();

        MessageBindings bindings = new MessageBindingsImpl();
        bindings.setAMQPBinding(
                AMQPMessageBindingReader.readMessageBinding(nested.value(MessageBindingsConstants.PROP_AMQP_BINDING)));
        bindings.setHTTPBinding(
                HTTPMessageBindingReader.readMessageBinding(nested.value(MessageBindingsConstants.PROP_HTTP_BINDING)));
        bindings.setKafkaBinding(
                KafkaMessageBindingReader.readMessageBinding(nested.value(MessageBindingsConstants.PROP_KAFKA_BINDING)));
        bindings.setMQTTBinding(
                MQTTMessageBindingReader.readMessageBinding(nested.value(MessageBindingsConstants.PROP_MQTT_BINDING)));

        return bindings;
    }

    public static MessageBindings readMessageBindings(final JsonNode node) {
        if (node == null) {
            return null;
        }

        IoLogging.logger.singleJsonNode("ChannelBindings");

        MessageBindings bindings = new MessageBindingsImpl();
        bindings.setAMQPBinding(
                AMQPMessageBindingReader.readMessageBinding(node.get(MessageBindingsConstants.PROP_AMQP_BINDING)));
        bindings.setHTTPBinding(
                HTTPMessageBindingReader.readMessageBinding(node.get(MessageBindingsConstants.PROP_HTTP_BINDING)));
        bindings.setKafkaBinding(
                KafkaMessageBindingReader.readMessageBinding(node.get(MessageBindingsConstants.PROP_KAFKA_BINDING)));
        bindings.setMQTTBinding(
                MQTTMessageBindingReader.readMessageBinding(node.get(MessageBindingsConstants.PROP_MQTT_BINDING)));

        return bindings;
    }
}
