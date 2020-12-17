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
package io.smallrye.asyncapi.spec.annotations.binding.ws;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.smallrye.asyncapi.spec.annotations.schema.Schema;

/**
 * Protocol-specific information for a WebSockets channel.
 *
 * @see "https://github.com/asyncapi/bindings/blob/master/websockets/README.md#channel"
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface WebSocketChannelBinding {

    /**
     * The HTTP method to use when establishing the connection. Its value MUST be either GET or POST.
     *
     * @return HTTP method of the connection
     */
    String method() default "";

    /**
     * A Schema object containing the definitions for each query parameter.
     *
     * This schema MUST be of type object and have a properties key.
     *
     * @return query object of the connection
     */
    Schema query() default @Schema;

    /**
     * A Schema object containing the definitions of the HTTP headers to use when establishing the connection.
     *
     * This schema MUST be of type object and have a properties key.
     *
     * @return header object of the connection
     */
    Schema headers() default @Schema;

    /**
     * The version of this binding. If omitted, "latest" MUST be assumed.
     *
     * @return version of the binding
     */
    String bindingVersion() default "latest";
}
