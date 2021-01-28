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

package io.smallrye.asyncapi.bindings.kafka;

import io.smallrye.asyncapi.spec.annotations.binding.MessageBindings;
import io.smallrye.asyncapi.spec.annotations.binding.OperationBindings;
import io.smallrye.asyncapi.spec.annotations.binding.kafka.KafkaMessageBinding;
import io.smallrye.asyncapi.spec.annotations.binding.kafka.KafkaOperationBinding;
import io.smallrye.asyncapi.spec.annotations.channel.ChannelItem;
import io.smallrye.asyncapi.spec.annotations.message.Message;
import io.smallrye.asyncapi.spec.annotations.operation.Operation;
import io.smallrye.asyncapi.spec.annotations.schema.Schema;
import io.smallrye.asyncapi.spec.annotations.schema.SchemaType;

public class KafkaService {

    @ChannelItem(channel = "kafka-test1", subscribe = @Operation(message = @Message(bindings = @MessageBindings(kafkaBinding = @KafkaMessageBinding(key = @Schema(type = SchemaType.STRING, enumeration = {
            "myKey" }), bindingVersion = "0.1.0")))))
    public void kafkaTest1() {
    }

    @ChannelItem(channel = "kafka-test2", subscribe = @Operation(bindings = @OperationBindings(kafkaBinding = @KafkaOperationBinding(groupId = @Schema(type = SchemaType.STRING, enumeration = {
            "myGroupId" }), clientId = @Schema(type = SchemaType.STRING, enumeration = {
                    "myClientId" }), bindingVersion = "0.1.0"))))
    public void kafkaTest2() {
    }
}
