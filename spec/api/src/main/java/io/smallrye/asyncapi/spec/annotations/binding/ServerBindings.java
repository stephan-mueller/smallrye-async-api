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

import io.smallrye.asyncapi.spec.annotations.binding.mqtt.MQTTServerBinding;

/**
 * This object represents an array of ServerBinding annotations that can be specified at the definition level.
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0/#a-name-serverbindingsobject-a-server-bindings-object"
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ServerBindings {

    /**
     * A array where the items describe protocol-specific definitions for the server.
     *
     * @return bindings of the server
     */
    ServerBinding[] binding() default {};

    /**
     * mqtt-specific definitions for the server.
     *
     * @return mqtt bindings of the server
     */
    MQTTServerBinding mqttBinding() default @MQTTServerBinding();
}
