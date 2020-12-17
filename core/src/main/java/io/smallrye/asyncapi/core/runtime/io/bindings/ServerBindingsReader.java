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

import io.smallrye.asyncapi.core.api.models.binding.ServerBindingsImpl;
import io.smallrye.asyncapi.core.runtime.io.IoLogging;
import io.smallrye.asyncapi.core.runtime.io.bindings.mqtt.server.MQTTServerBindingReader;
import io.smallrye.asyncapi.core.runtime.scanner.spi.AnnotationScannerContext;
import io.smallrye.asyncapi.spec.models.binding.ServerBindings;

public class ServerBindingsReader {

    public ServerBindingsReader() {
    }

    public static ServerBindings readServerBindings(final AnnotationScannerContext context,
            final AnnotationValue annotationValue) {
        if (annotationValue == null) {
            return null;
        }
        IoLogging.logger.annotationsArray("@ServerBindings");
        AnnotationInstance nested = annotationValue.asNested();

        ServerBindings bindings = new ServerBindingsImpl();
        bindings.setMQTTBinding(
                MQTTServerBindingReader.readServerBinding(nested.value(ServerBindingsConstants.PROP_MQTT_BINDING)));

        return bindings;
    }

    public static ServerBindings readServerBindings(final JsonNode node) {
        if (node == null) {
            return null;
        }

        IoLogging.logger.singleJsonNode("ServerBindings");

        ServerBindings bindings = new ServerBindingsImpl();
        bindings.setMQTTBinding(MQTTServerBindingReader.readServerBinding(node.get(ServerBindingsConstants.PROP_MQTT_BINDING)));

        return bindings;
    }
}
