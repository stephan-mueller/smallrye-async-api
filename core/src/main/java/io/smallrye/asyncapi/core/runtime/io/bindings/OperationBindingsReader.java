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

import io.smallrye.asyncapi.core.api.models.binding.OperationBindingsImpl;
import io.smallrye.asyncapi.core.runtime.io.IoLogging;
import io.smallrye.asyncapi.core.runtime.io.bindings.amqp.operation.AMQPOperationBindingReader;
import io.smallrye.asyncapi.core.runtime.io.bindings.http.operation.HTTPOperationBindingReader;
import io.smallrye.asyncapi.core.runtime.io.bindings.kafka.operation.KafkaOperationBindingReader;
import io.smallrye.asyncapi.core.runtime.io.bindings.mqtt.operation.MQTTOperationBindingReader;
import io.smallrye.asyncapi.core.runtime.scanner.spi.AnnotationScannerContext;
import io.smallrye.asyncapi.spec.models.binding.OperationBindings;

public class OperationBindingsReader {

    public OperationBindingsReader() {
    }

    public static OperationBindings readOperationBindings(final AnnotationScannerContext context,
            final AnnotationValue annotationValue) {
        if (annotationValue == null) {
            return null;
        }
        IoLogging.logger.annotationsArray("@OperationBindings");
        AnnotationInstance nested = annotationValue.asNested();

        OperationBindings bindings = new OperationBindingsImpl();
        bindings.setAMQPBinding(
                AMQPOperationBindingReader.readOperationBinding(nested.value(OperationBindingsConstants.PROP_AMQP_BINDING)));
        bindings.setHTTPBinding(
                HTTPOperationBindingReader.readOperationBinding(nested.value(OperationBindingsConstants.PROP_HTTP_BINDING)));
        bindings.setKafkaBinding(
                KafkaOperationBindingReader.readOperationBinding(nested.value(OperationBindingsConstants.PROP_KAFKA_BINDING)));
        bindings.setMQTTBinding(
                MQTTOperationBindingReader.readOperationBinding(nested.value(OperationBindingsConstants.PROP_MQTT_BINDING)));

        return bindings;
    }

    public static OperationBindings readOperationBindings(final JsonNode node) {
        if (node == null) {
            return null;
        }

        IoLogging.logger.singleJsonNode("OperationBindings");

        OperationBindings bindings = new OperationBindingsImpl();
        bindings.setAMQPBinding(
                AMQPOperationBindingReader.readOperationBinding(node.get(OperationBindingsConstants.PROP_AMQP_BINDING)));
        bindings.setHTTPBinding(
                HTTPOperationBindingReader.readOperationBinding(node.get(OperationBindingsConstants.PROP_HTTP_BINDING)));
        bindings.setKafkaBinding(
                KafkaOperationBindingReader.readOperationBinding(node.get(OperationBindingsConstants.PROP_KAFKA_BINDING)));
        bindings.setMQTTBinding(
                MQTTOperationBindingReader.readOperationBinding(node.get(OperationBindingsConstants.PROP_MQTT_BINDING)));

        return bindings;
    }
}
