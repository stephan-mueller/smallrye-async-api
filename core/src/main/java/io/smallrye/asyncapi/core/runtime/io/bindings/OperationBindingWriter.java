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

import com.fasterxml.jackson.databind.node.ObjectNode;

import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.io.bindings.amqp.operation.AMQPOperationBindingWriter;
import io.smallrye.asyncapi.core.runtime.io.bindings.http.operation.HTTPOperationBindingWriter;
import io.smallrye.asyncapi.core.runtime.io.bindings.kafka.operation.KafkaOperationBindingWriter;
import io.smallrye.asyncapi.core.runtime.io.bindings.mqtt.operation.MQTTOperationBindingWriter;
import io.smallrye.asyncapi.spec.models.binding.OperationBindings;

public class OperationBindingWriter {

    public OperationBindingWriter() {
    }

    public static void writeOperationBindings(final ObjectNode parent, final OperationBindings model) {
        if (model == null) {
            return;
        }
        ObjectNode node = JsonUtil.objectNode();
        parent.set(OperationBindingsConstants.PROP_BINDINGS, node);

        AMQPOperationBindingWriter.writeAMQPOperationBinding(node, model.getAMQPBinding());
        HTTPOperationBindingWriter.writeHTTPOperationBinding(node, model.getHTTPBinding());
        KafkaOperationBindingWriter.writeKafkaOperationBinding(node, model.getKafkaBinding());
        MQTTOperationBindingWriter.writeMQTTOperationBinding(node, model.getMQTTBinding());
    }
}
