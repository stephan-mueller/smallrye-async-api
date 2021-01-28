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

package io.smallrye.asyncapi.bindings.amqp;

import io.smallrye.asyncapi.spec.annotations.binding.ChannelBindings;
import io.smallrye.asyncapi.spec.annotations.binding.MessageBindings;
import io.smallrye.asyncapi.spec.annotations.binding.OperationBindings;
import io.smallrye.asyncapi.spec.annotations.binding.amqp.AMQPChannelBinding;
import io.smallrye.asyncapi.spec.annotations.binding.amqp.AMQPMessageBinding;
import io.smallrye.asyncapi.spec.annotations.binding.amqp.AMQPOperationBinding;
import io.smallrye.asyncapi.spec.annotations.binding.amqp.Exchange;
import io.smallrye.asyncapi.spec.annotations.binding.amqp.ExchangeType;
import io.smallrye.asyncapi.spec.annotations.binding.amqp.Queue;
import io.smallrye.asyncapi.spec.annotations.channel.ChannelItem;
import io.smallrye.asyncapi.spec.annotations.message.Message;
import io.smallrye.asyncapi.spec.annotations.operation.Operation;

public class AMQPService {

    @ChannelItem(channel = "amqp-test1", bindings = @ChannelBindings(amqpBinding = @AMQPChannelBinding(is = "queue", queue = @Queue(name = "amqp-test"))))
    public void amqpTest1() {
    }

    @ChannelItem(channel = "amqp-test2", bindings = @ChannelBindings(amqpBinding = @AMQPChannelBinding(exchange = @Exchange(name = "amqp-test", type = ExchangeType.TOPIC))), publish = @Operation(message = @Message(bindings = @MessageBindings(amqpBinding = @AMQPMessageBinding(contentEncoding = "gzip", messageType = "test.amqp", bindingVersion = "0.1.0"))), bindings = @OperationBindings(amqpBinding = @AMQPOperationBinding(expiration = 100000, userId = "guest", cc = {
            "test.amqp" }, priority = 10, deliveryMode = 2, mandatory = false, bcc = {
                    "test.amqp" }, replyTo = "test.amqp", timeStamp = true, ack = false, bindingVersion = "0.1.0"))))
    public void amqpTest2() {
    }
}
