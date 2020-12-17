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
 * The object defines the queue properties
 *
 * @see "https://github.com/asyncapi/bindings/blob/master/amqp/README.md#channel"
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Queue {

    /**
     * The name of the queue
     *
     * @return name of the queue
     */
    String name() default "";

    /**
     * @return whether the queue should survive broker restarts or not.
     */
    boolean durable() default false;

    /**
     * @return whether the queue should be used only by one connection or not.
     */
    boolean exclusive() default false;

    /**
     * @return whether the queue should be deleted when the last consumer unsubscribes.
     */
    boolean autoDelete() default false;

    /**
     * The virtual host of the queue. Defaults to /.
     *
     * @return virtual host of the queue
     */
    String vhost() default "/";
}
