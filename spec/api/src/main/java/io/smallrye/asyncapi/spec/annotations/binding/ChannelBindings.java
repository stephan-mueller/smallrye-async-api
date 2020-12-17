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

import io.smallrye.asyncapi.spec.annotations.binding.amqp.AMQPChannelBinding;
import io.smallrye.asyncapi.spec.annotations.binding.http.HTTPChannelBinding;
import io.smallrye.asyncapi.spec.annotations.binding.ws.WebSocketChannelBinding;
import io.smallrye.asyncapi.spec.annotations.schema.Schema;

/**
 * This object represents an array of ChannelBindings annotations that can be specified at the definition level.
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0/#channelBindingsObject"
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ChannelBindings {

    /**
     * A array where the items describe protocol-specific definitions for the channel.
     *
     * @return bindings of the channel
     */
    ChannelBinding[] binding() default {};

    /**
     * amqp-specific definitions for the channel.
     *
     * @return amqp bindings of the channel
     */
    AMQPChannelBinding amqpBinding() default @AMQPChannelBinding();

    /**
     * http-specific definitions for the channel.
     *
     * @return http bindings of the channel
     */
    HTTPChannelBinding httpBinding() default @HTTPChannelBinding();

    /**
     * ws-specific definitions for the channel.
     *
     * @return ws bindings of the channel
     */
    WebSocketChannelBinding wsBinding() default @WebSocketChannelBinding(headers = @Schema, query = @Schema);
}
