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
package io.smallrye.asyncapi.spec.annotations.server;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.smallrye.asyncapi.spec.annotations.binding.ServerBindings;
import io.smallrye.asyncapi.spec.annotations.security.SecurityRequirement;

/**
 * An object representing a message broker, a server or any other kind of computer program capable of sending and/or receiving
 * data.
 *
 * This object is used to capture details such as URIs, protocols and security configuration. Variable substitution can be used
 * so that some
 * details, for example usernames and passwords, can be injected by code generation tools.
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0/#a-name-serverobject-a-server-object"
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Servers.class)
@Inherited
public @interface Server {

    /**
     * <strong>Required</strong>. A URL to the target host.
     *
     * This URL supports Server Variables and MAY be relative, to indicate that the host location is relative to the location
     * where the
     * AsyncAPI document is being served. ServerVariables substitutions will be made when a variable is named in {brackets}.
     *
     * @return URL to the target host
     **/
    String url();

    /**
     * <strong>Required</strong>. The protocol this URL supports for connection.
     *
     * Supported protocol include, but are not limited to: amqp, amqps, http, https, jms, kafka, kafka-secure, mqtt,
     * secure-mqtt, stomp,
     * stomps, ws, wss.
     *
     * @return protocol of the target host
     */
    String protocol();

    /**
     * The version of the protocol used for connection. For instance: AMQP 0.9.1, HTTP 2.0, Kafka 1.0.0, etc.
     *
     * @return version of the host designated by protocol
     */
    String protocolVersion() default "";

    /**
     * An optional string describing the host designated by the URL. CommonMark syntax MAY be used for rich text representation.
     *
     * @return description of the host designated by URL
     */
    String description() default "";

    /**
     * A map between a variable name and its value.
     *
     * The value is used for substitution in the server’s URL template.
     *
     * @return variables of the server
     */
    ServerVariable[] variables() default {};

    /**
     * A declaration of which security mechanisms can be used with this server.
     *
     * The list of values includes alternative security requirement objects that can be used. Only one of the security
     * requirement objects
     * need to be satisfied to authorize a connection or operation.
     *
     * @return security requirements of the server
     */
    SecurityRequirement[] security() default {};

    /**
     * A free-form map where the keys describe the name of the protocol and the values describe protocol-specific definitions
     * for the server.
     *
     * @return bindings of the server
     */
    ServerBindings bindings() default @ServerBindings;
}
