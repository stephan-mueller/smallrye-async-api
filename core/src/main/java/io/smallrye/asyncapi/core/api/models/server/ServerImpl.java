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
package io.smallrye.asyncapi.core.api.models.server;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.smallrye.asyncapi.core.api.models.ExtensibleImpl;
import io.smallrye.asyncapi.core.api.models.ModelImpl;
import io.smallrye.asyncapi.core.runtime.util.ModelUtil;
import io.smallrye.asyncapi.spec.models.binding.ServerBindings;
import io.smallrye.asyncapi.spec.models.security.SecurityRequirement;
import io.smallrye.asyncapi.spec.models.server.Server;
import io.smallrye.asyncapi.spec.models.server.ServerVariable;

/**
 * An implementation of the {@link Server} AsyncAPI model interface.
 */
public class ServerImpl extends ExtensibleImpl<Server> implements Server, ModelImpl {

    private String url;

    private String protocol;

    private String protocolVersion;

    private String description;

    private Map<String, ServerVariable> serverVariables;

    private List<SecurityRequirement> securityRequirements;

    private ServerBindings bindings;

    /**
     * @see Server#getUrl()
     */
    @Override
    public String getUrl() {
        return this.url;
    }

    /**
     * @see Server#setUrl(String url)
     */
    @Override
    public void setUrl(final String url) {
        this.url = url;
    }

    /**
     * @see Server#getProtocol()
     */
    @Override
    public String getProtocol() {
        return this.protocol;
    }

    /**
     * @see Server#setProtocol(String protocol)
     */
    @Override
    public void setProtocol(final String protocol) {
        this.protocol = protocol;
    }

    /**
     * @see Server#getProtocolVersion()
     */
    @Override
    public String getProtocolVersion() {
        return this.protocolVersion;
    }

    /**
     * @see Server#setProtocolVersion(String protocolVersion)
     */
    @Override
    public void setProtocolVersion(final String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    /**
     * @see Server#getDescription()
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * @see Server#setDescription(String description)
     */
    @Override
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @see Server#getVariables()
     */
    @Override
    public Map<String, ServerVariable> getVariables() {
        return ModelUtil.unmodifiableMap(this.serverVariables);
    }

    /**
     * @see Server#setVariables(Map<String,ServerVariable>)
     */
    @Override
    public void setVariables(final Map<String, ServerVariable> serverVariables) {
        this.serverVariables = ModelUtil.replace(serverVariables, LinkedHashMap<String, ServerVariable>::new);
    }

    /**
     * @see Server#addVariable(String key, ServerVariable serverVariable)
     */
    @Override
    public Server addVariable(final String variableName, final ServerVariable serverVariable) {
        this.serverVariables = ModelUtil.add(variableName, serverVariable, this.serverVariables,
                LinkedHashMap<String, ServerVariable>::new);
        return this;
    }

    /**
     * @see Server#removeVariable(String variableName)
     */
    @Override
    public void removeVariable(final String variableName) {
        ModelUtil.remove(this.serverVariables, variableName);
    }

    /**
     * @see Server#getSecurityRequirements()
     */
    @Override
    public List<SecurityRequirement> getSecurityRequirements() {
        return ModelUtil.unmodifiableList(this.securityRequirements);
    }

    /**
     * @see Server#setSecurityRequirements(List<SecurityRequirement>)
     */
    @Override
    public void setSecurityRequirements(final List<SecurityRequirement> securityRequirements) {
        this.securityRequirements = ModelUtil.replace(securityRequirements, ArrayList::new);
    }

    /**
     * @see Server#addSecurityRequirements(SecurityRequirement securityRequirement)
     */
    @Override
    public Server addSecurityRequirements(final SecurityRequirement securityRequirement) {
        this.securityRequirements = ModelUtil.add(securityRequirement, this.securityRequirements, ArrayList::new);
        return this;
    }

    /**
     * @see Server#removeSecurityRequirements(SecurityRequirement securityRequirement)
     */
    @Override
    public void removeSecurityRequirements(final SecurityRequirement securityRequirement) {
        ModelUtil.remove(this.securityRequirements, securityRequirement);
    }

    /**
     * @see Server#getServerBindings()
     */
    @Override
    public ServerBindings getServerBindings() {
        return this.bindings;
    }

    /**
     * @see Server#setServerBindings(ServerBindings bindings)
     */
    @Override
    public void setServerBindings(final ServerBindings bindings) {
        this.bindings = bindings;
    }

}
