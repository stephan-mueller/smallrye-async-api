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
 * Protocol-specific information for an MQTT server.
 *
 * @see "https://github.com/asyncapi/bindings/tree/master/mqtt#server-binding-object"
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface MQTTServerBinding {

    /**
     * The client identifier.
     *
     * @return client identifier.
     */
    String clientId() default "";

    /**
     * Whether to create a persistent connection or not. When false, the connection will be persistent.
     *
     * @return whether to create a persistent connection or not.
     */
    boolean cleanSession() default false;

    /**
     * @return Last Will and Testament configuration.
     */
    LastWill lastWill() default @LastWill;

    /**
     * The version of this binding. If omitted, "latest" MUST be assumed.
     *
     * @return version of the binding
     */
    String bindingVersion() default "latest";
}
