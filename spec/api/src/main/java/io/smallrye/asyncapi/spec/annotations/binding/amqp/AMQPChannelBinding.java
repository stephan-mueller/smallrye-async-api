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
package io.smallrye.asyncapi.spec.annotations.binding.amqp;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Protocol-specific information for an AMQP 0-9-1 channel.
 *
 * @see "https://github.com/asyncapi/bindings/blob/master/amqp1/README.md#channel"
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface AMQPChannelBinding {

    /**
     * Defines what type of channel is it. Can be either queue or routingKey (default).
     *
     * @return type of the channel
     */
    String is() default "routingKey";

    /**
     * When is=routingKey, this object defines the exchange properties.
     *
     * @return exchange properties
     */
    Exchange exchange() default @Exchange;

    /**
     * When is=queue, this object defines the queue properties.
     *
     * @return queue properties
     */
    Queue queue() default @Queue;

    /**
     * The version of this binding. If omitted, "latest" MUST be assumed.
     *
     * @return version of the channel
     */
    String bindingVersion() default "latest";
}
