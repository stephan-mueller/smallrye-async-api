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
package io.smallrye.asyncapi.spec.annotations.binding.mqtt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Last Will and Testament configuration object
 *
 * @see "https://github.com/asyncapi/bindings/tree/master/mqtt#server"
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface LastWill {

    /**
     * The topic where the Last Will and Testament message will be sent.
     *
     * @return topic of the Last Will
     */
    String topic() default "";

    /**
     * Defines how hard the broker/client will try to ensure that the Last Will and Testament message is received.
     * Its value MUST be either 0, 1 or 2.
     *
     * @return level of retry
     */
    int qos() default 0;

    /**
     * Last Will message.
     *
     * @return message of the Last Will
     */
    String message() default "";

    /**
     * @return whether the broker should retain the Last Will and Testament message or not.
     */
    boolean retain() default false;
}
