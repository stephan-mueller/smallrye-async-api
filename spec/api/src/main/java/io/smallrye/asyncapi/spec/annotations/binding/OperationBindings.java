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
package io.smallrye.asyncapi.spec.annotations.binding;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.smallrye.asyncapi.spec.annotations.binding.amqp.AMQPOperationBinding;
import io.smallrye.asyncapi.spec.annotations.binding.http.HTTPOperationBinding;
import io.smallrye.asyncapi.spec.annotations.binding.http.Method;
import io.smallrye.asyncapi.spec.annotations.binding.kafka.KafkaOperationBinding;
import io.smallrye.asyncapi.spec.annotations.binding.mqtt.MQTTOperationBinding;
import io.smallrye.asyncapi.spec.annotations.binding.ws.WebSocketOperationBinding;
import io.smallrye.asyncapi.spec.annotations.schema.Schema;

/**
 * Map describing protocol-specific definitions for an operation.
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0/#operationBindingsObject"
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface OperationBindings {

    /**
     * A array where the items describe protocol-specific definitions for the operation.
     *
     * @return bindings of the operation
     */
    OperationBinding[] binding() default {};

    /**
     * amqp-specific definitions for the channel.
     *
     * @return amqp bindings of the channel
     */
    AMQPOperationBinding amqpBinding() default @AMQPOperationBinding();

    /**
     * http-specific definitions for the channel.
     *
     * @return http bindings of the channel
     */
    HTTPOperationBinding httpBinding() default @HTTPOperationBinding(type = "", query = @Schema, method = Method.GET);

    /**
     * kafka-specific definitions for the channel.
     *
     * @return kafka bindings of the channel
     */
    KafkaOperationBinding kafkaBinding() default @KafkaOperationBinding(clientId = @Schema, groupId = @Schema);

    /**
     * mqtt-specific definitions for the channel.
     *
     * @return amqp bindings of the channel
     */
    MQTTOperationBinding mqttBinding() default @MQTTOperationBinding(qos = 0, retain = false);

    /**
     * ws-specific definitions for the channel.
     *
     * @return ws bindings of the channel
     */
    WebSocketOperationBinding wsBinding() default @WebSocketOperationBinding();
}
