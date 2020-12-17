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

import io.smallrye.asyncapi.core.api.models.binding.ChannelBindingsImpl;
import io.smallrye.asyncapi.core.runtime.io.IoLogging;
import io.smallrye.asyncapi.core.runtime.io.bindings.amqp.channel.AMQPChannelBindingReader;
import io.smallrye.asyncapi.core.runtime.io.bindings.ws.WebSocketChannelBindingReader;
import io.smallrye.asyncapi.core.runtime.scanner.spi.AnnotationScannerContext;
import io.smallrye.asyncapi.spec.models.binding.ChannelBindings;

public class ChannelBindingsReader {

    public ChannelBindingsReader() {
    }

    public static ChannelBindings readChannelBindings(final AnnotationScannerContext context,
            final AnnotationValue annotationValue) {
        if (annotationValue == null) {
            return null;
        }
        IoLogging.logger.annotationsArray("@ChannelBindings");
        AnnotationInstance nested = annotationValue.asNested();

        ChannelBindings bindings = new ChannelBindingsImpl();
        bindings.setAMQPBinding(
                AMQPChannelBindingReader.readChannelBinding(nested.value(ChannelBindingsConstants.PROP_AMQP_BINDING)));
        bindings.setWebSocketBinding(
                WebSocketChannelBindingReader.readChannelBinding(nested.value(ChannelBindingsConstants.PROP_WS_BINDING)));

        return bindings;
    }

    public static ChannelBindings readChannelBindings(final JsonNode node) {
        if (node == null) {
            return null;
        }

        IoLogging.logger.singleJsonNode("ChannelBindings");

        ChannelBindings bindings = new ChannelBindingsImpl();
        bindings.setAMQPBinding(
                AMQPChannelBindingReader.readChannelBinding(node.get(ChannelBindingsConstants.PROP_AMQP_BINDING)));
        bindings.setWebSocketBinding(
                WebSocketChannelBindingReader.readChannelBinding(node.get(ChannelBindingsConstants.PROP_WS_BINDING)));

        return bindings;
    }
}
