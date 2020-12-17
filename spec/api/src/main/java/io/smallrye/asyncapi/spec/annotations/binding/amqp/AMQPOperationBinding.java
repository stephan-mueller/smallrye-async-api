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
 * Protocol-specific information for an AMQP 0-9-1 operation.
 *
 * @see "https://github.com/asyncapi/bindings/blob/master/amqp1/README.md#operation-binding-object"
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface AMQPOperationBinding {

    /**
     * TTL (Time-To-Live) for the message. It MUST be greater than or equal to zero.
     *
     * @return expiration of the operation
     */
    int expiration() default 0;

    /**
     * Identifies the user who has sent the message.
     *
     * @return id of the user
     */
    String userId() default "";

    /**
     * The routing keys the message should be routed to at the time of publishing.
     *
     * @return array of routing keys
     */
    String[] cc() default {};

    /**
     * A priority for the message.
     *
     * @return priority of the message
     */
    int priority() default 0;

    /**
     * Delivery mode of the message. Its value MUST be either 1 (transient) or 2 (persistent).
     *
     * @return mode of the message
     */
    int deliveryMode() default 0;

    /**
     * @return whether the message is mandatory or not.
     */
    boolean mandatory() default false;

    /**
     * Like {@link AMQPOperationBinding#cc} but consumers will not receive this information.
     *
     * @return array of routing keys
     */
    String[] bcc() default {};

    /**
     * Name of the queue where the consumer should send the response.
     *
     * @return name of the queue
     */
    String replyTo() default "";

    /**
     * @return whether the message should include a timestamp or not.
     */
    boolean timeStamp() default false;

    /**
     * @return whether the consumer should ack the message or not.
     */
    boolean ack() default false;

    /**
     * The version of this binding. If omitted, "latest" MUST be assumed.
     *
     * @return version of the binding
     */
    String bindingVersion() default "latest";
}
