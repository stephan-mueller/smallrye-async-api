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

package io.smallrye.asyncapi.bindings.mqtt;

import io.smallrye.asyncapi.spec.annotations.AsyncAPI;
import io.smallrye.asyncapi.spec.annotations.binding.MessageBindings;
import io.smallrye.asyncapi.spec.annotations.binding.OperationBindings;
import io.smallrye.asyncapi.spec.annotations.binding.ServerBindings;
import io.smallrye.asyncapi.spec.annotations.binding.mqtt.LastWill;
import io.smallrye.asyncapi.spec.annotations.binding.mqtt.MQTTMessageBinding;
import io.smallrye.asyncapi.spec.annotations.binding.mqtt.MQTTOperationBinding;
import io.smallrye.asyncapi.spec.annotations.binding.mqtt.MQTTServerBinding;
import io.smallrye.asyncapi.spec.annotations.channel.ChannelItem;
import io.smallrye.asyncapi.spec.annotations.info.Info;
import io.smallrye.asyncapi.spec.annotations.message.Message;
import io.smallrye.asyncapi.spec.annotations.operation.Operation;
import io.smallrye.asyncapi.spec.annotations.server.Server;

@AsyncAPI(asyncapi = "2.0.0", info = @Info(version = "1.0.0-SNAPSHOT", title = "MQTTService"), defaultContentType = "application/json", servers = {
        @Server(protocol = "mqtt", url = "mqtt://example.com", bindings = @ServerBindings(mqttBinding = @MQTTServerBinding(clientId = "guest", cleanSession = true, lastWill = @LastWill(topic = "/last-will", qos = 2, message = "Guest gone offline.", retain = false))))
})
public class MQTTService {

    @ChannelItem(channel = "mqtt-test1", subscribe = @Operation(message = @Message(bindings = @MessageBindings(mqttBinding = @MQTTMessageBinding(bindingVersion = "0.1.0")))))
    public void mqttTest1() {
    }

    @ChannelItem(channel = "mqtt-test2", subscribe = @Operation(bindings = @OperationBindings(mqttBinding = @MQTTOperationBinding(qos = 2, retain = true, bindingVersion = "0.1.0"))))
    public void mqttTest2() {
    }
}
