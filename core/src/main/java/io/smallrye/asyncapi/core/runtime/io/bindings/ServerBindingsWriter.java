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
import io.smallrye.asyncapi.core.runtime.io.bindings.mqtt.server.MQTTServerBindingWriter;
import io.smallrye.asyncapi.spec.models.binding.ServerBindings;

public class ServerBindingsWriter {

    public ServerBindingsWriter() {
    }

    public static void writeServerBindings(final ObjectNode parent, final ServerBindings model) {
        if (model == null) {
            return;
        }
        ObjectNode node = JsonUtil.objectNode();
        parent.set(ServerBindingsConstants.PROP_BINDINGS, node);

        MQTTServerBindingWriter.writeMQTTServerBinding(node, model.getMQTTBinding());
    }
}
