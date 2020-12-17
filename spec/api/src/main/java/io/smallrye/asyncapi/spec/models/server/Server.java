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
package io.smallrye.asyncapi.spec.models.server;

import java.util.List;
import java.util.Map;

import io.smallrye.asyncapi.spec.models.Constructible;
import io.smallrye.asyncapi.spec.models.Extensible;
import io.smallrye.asyncapi.spec.models.binding.ServerBindings;
import io.smallrye.asyncapi.spec.models.security.SecurityRequirement;

/**
 * An object representing a message broker, a server or any other kind of computer program capable of sending and/or receiving
 * data.
 * <p>
 * This object is used to capture details such as URIs, protocols and security configuration. Variable substitution can be used
 * so that some
 * details, for example usernames and passwords, can be injected by code generation tools.
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0/#a-name-serverobject-a-server-object"
 */
public interface Server extends Constructible, Extensible<Server> {

    /**
     * Returns the URL of the Server
     *
     * @return the URL
     */
    String getUrl();

    /**
     * Sets the URL of the Server
     *
     * @param url URL of the Server
     */
    void setUrl(String url);

    /**
     * Sets this Server instance's URL
     *
     * @param url URL of the Server
     * @return the current Server object
     */
    default Server url(String url) {
        setUrl(url);
        return this;
    }

    /**
     * Returns the protocol of the Server
     *
     * @return the protocol
     */
    String getProtocol();

    /**
     * Sets the protocol of the Server
     *
     * @param protocol protocol of the Server
     */
    void setProtocol(String protocol);

    /**
     * Sets this Server instance's protocol
     *
     * @param protocol protocol of the Server
     * @return the current Server object
     */
    default Server protocol(String protocol) {
        setProtocol(protocol);
        return this;
    }

    /**
     * Returns the protocol version of the Server
     *
     * @return the protocol version
     */
    String getProtocolVersion();

    /**
     * Sets the protocol version of the Server
     *
     * @param protocolVersion protocol version of the Server
     */
    void setProtocolVersion(String protocolVersion);

    /**
     * Sets this Server instance's protocol version
     *
     * @param protocolVersion protocol version of the Server
     * @return the current Server object
     */
    default Server protocolVersion(String protocolVersion) {
        setProtocolVersion(protocolVersion);
        return this;
    }

    /**
     * Returns the description of the Server
     *
     * @return the description
     */
    String getDescription();

    /**
     * Sets the description of the Server
     *
     * @param description description version of the Server
     */
    void setDescription(String description);

    /**
     * Sets this Server instance's description
     *
     * @param description description of the Server
     * @return the current Server object
     */
    default Server description(String description) {
        setProtocolVersion(description);
        return this;
    }

    /**
     * Returns the server variables of the Server
     *
     * @return the server variables
     */
    Map<String, ServerVariable> getVariables();

    /**
     * Sets the server variables of the Server
     *
     * @param variables server variables of the Server
     */
    void setVariables(Map<String, ServerVariable> variables);

    /**
     * Sets this Server instance's server variables
     *
     * @param variables server variables of the Server
     * @return the current Server object
     */
    default Server variables(Map<String, ServerVariable> variables) {
        setVariables(variables);
        return this;
    }

    /**
     * Adds the given server variable item to the Server's map of variables.
     *
     * @param variableName the name the variable to add
     * @param variable a server variable used for substitution in the server's URL template.
     * @return the current Server object
     **/
    Server addVariable(String variableName, ServerVariable variable);

    /**
     * Removes the given server variable item from the Server's map of variables
     *
     * @param variableName a server variable used for substitution in the server's URL template.
     */
    void removeVariable(String variableName);

    /**
     * Returns the security requirement of the Server
     *
     * @return the security requirement
     */
    List<SecurityRequirement> getSecurityRequirements();

    /**
     * Sets the security requirement of the Server
     *
     * @param securityRequirements security requirement of the Server
     */
    void setSecurityRequirements(List<SecurityRequirement> securityRequirements);

    /**
     * Sets this Server instance's security requirement
     *
     * @param securityRequirements security requirement of the Server
     * @return the current Server object
     */
    default Server securityRequirements(List<SecurityRequirement> securityRequirements) {
        setSecurityRequirements(securityRequirements);
        return this;
    }

    /**
     * Adds the given SecurityRequirement to the Server's map of SecurityRequirements.
     *
     * @param securityRequirement the securityRequirement to add
     * @return the current Server object
     **/
    Server addSecurityRequirements(SecurityRequirement securityRequirement);

    /**
     * Removes the given SecurityRequirement to the Server's map of SecurityRequirements.
     *
     * @param securityRequirement the securityRequirement to remove
     */
    void removeSecurityRequirements(SecurityRequirement securityRequirement);

    /**
     * Returns the server bindings of the Server
     *
     * @return the server bindings of the Server
     */
    ServerBindings getServerBindings();

    /**
     * Sets the server bindings of the Server
     *
     * @param bindings server bindings of the Server
     */
    void setServerBindings(ServerBindings bindings);

    /**
     * Sets the server bindings of the Server
     *
     * @param bindings server bindings of the Server
     * @return the current Server object
     */
    default Server serverBindings(ServerBindings bindings) {
        setServerBindings(bindings);
        return this;
    }
}
