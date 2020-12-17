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
package io.smallrye.asyncapi.core.api.models;

import java.util.LinkedHashMap;
import java.util.Map;

import io.smallrye.asyncapi.core.runtime.util.ModelUtil;
import io.smallrye.asyncapi.spec.models.Components;
import io.smallrye.asyncapi.spec.models.binding.MessageBinding;
import io.smallrye.asyncapi.spec.models.binding.OperationBinding;
import io.smallrye.asyncapi.spec.models.binding.ServerBinding;
import io.smallrye.asyncapi.spec.models.message.CorrelationID;
import io.smallrye.asyncapi.spec.models.message.Message;
import io.smallrye.asyncapi.spec.models.message.MessageTrait;
import io.smallrye.asyncapi.spec.models.operation.OperationTrait;
import io.smallrye.asyncapi.spec.models.parameter.Parameter;
import io.smallrye.asyncapi.spec.models.schema.Schema;
import io.smallrye.asyncapi.spec.models.security.SecurityScheme;

/**
 * An implementation of the {@link Components} AsyncAPI model interface.
 */
public class ComponentsImpl extends ExtensibleImpl<Components> implements Components, ModelImpl {

    private Map<String, Schema> schemas;

    private Map<String, Message> messages;

    private Map<String, SecurityScheme> securitySchemes;

    private Map<String, Parameter> parameters;

    private Map<String, CorrelationID> correlationIDs;

    private Map<String, OperationTrait> operationTraits;

    private Map<String, MessageTrait> messageTraits;

    private Map<String, ServerBinding> serverBindings;

    private Map<String, OperationBinding> operationBindings;

    private Map<String, MessageBinding> messageBindings;

    /**
     * @see Components#getSchemas()
     */
    @Override
    public Map<String, Schema> getSchemas() {
        return ModelUtil.unmodifiableMap(this.schemas);
    }

    /**
     * @see Components#setSchemas(Map schemas)
     */
    @Override
    public void setSchemas(final Map<String, Schema> schemas) {
        this.schemas = ModelUtil.replace(schemas, LinkedHashMap::new);
    }

    /**
     * @see Components#addSchema(String key, Schema schema)
     */
    @Override
    public Components addSchema(final String key, final Schema schema) {
        this.schemas = ModelUtil.add(key, schema, this.schemas, LinkedHashMap<String, Schema>::new);
        return this;
    }

    /**
     * @see Components#removeSchema(String key)
     */
    @Override
    public void removeSchema(final String key) {
        ModelUtil.remove(this.schemas, key);
    }

    /**
     * @see Components#getMessages()
     */
    @Override
    public Map<String, Message> getMessages() {
        return ModelUtil.unmodifiableMap(this.messages);
    }

    /**
     * @see Components#setMessages(Map messages)
     */
    @Override
    public void setMessages(final Map<String, Message> messages) {
        this.messages = ModelUtil.replace(messages, LinkedHashMap::new);
    }

    /**
     * @see Components#addMessage(String key, Message message)
     */
    @Override
    public Components addMessage(final String key, final Message message) {
        this.messages = ModelUtil.add(key, message, this.messages, LinkedHashMap<String, Message>::new);
        return this;
    }

    /**
     * @see Components#removeMessage(String key)
     */
    @Override
    public void removeMessage(final String key) {
        ModelUtil.remove(this.messages, key);
    }

    /**
     * @see Components#getSecuritySchemes()
     */
    @Override
    public Map<String, SecurityScheme> getSecuritySchemes() {
        return ModelUtil.unmodifiableMap(this.securitySchemes);
    }

    /**
     * @see Components#setSecuritySchemes(Map securitySchemes)
     */
    @Override
    public void setSecuritySchemes(final Map<String, SecurityScheme> securitySchemes) {
        this.securitySchemes = ModelUtil.replace(securitySchemes, LinkedHashMap::new);
    }

    /**
     * @see Components#addSecurityScheme(String key, SecurityScheme securityScheme)
     */
    @Override
    public Components addSecurityScheme(final String key, final SecurityScheme securityScheme) {
        this.securitySchemes = ModelUtil.add(key, securityScheme, this.securitySchemes,
                LinkedHashMap<String, SecurityScheme>::new);
        return this;
    }

    /**
     * @see Components#removeSecurityScheme(String key)
     */
    @Override
    public void removeSecurityScheme(final String key) {
        ModelUtil.remove(this.securitySchemes, key);
    }

    /**
     * @see Components#getParameters()
     */
    @Override
    public Map<String, Parameter> getParameters() {
        return ModelUtil.unmodifiableMap(this.parameters);
    }

    /**
     * @see Components#setParameters(Map parameters)
     */
    @Override
    public void setParameters(final Map<String, Parameter> parameters) {
        this.parameters = ModelUtil.replace(parameters, LinkedHashMap::new);
    }

    /**
     * @see Components#addParameter(String key, Parameter parameter)
     */
    @Override
    public Components addParameter(final String key, final Parameter parameter) {
        this.parameters = ModelUtil.add(key, parameter, this.parameters, LinkedHashMap<String, Parameter>::new);
        return this;
    }

    /**
     * @see Components#removeParameter(String key)
     */
    @Override
    public void removeParameter(final String key) {
        ModelUtil.remove(this.parameters, key);
    }

    /**
     * @see Components#getCorrelationIds()
     */
    public Map<String, CorrelationID> getCorrelationIds() {
        return ModelUtil.unmodifiableMap(this.correlationIDs);
    }

    /**
     * @see Components#setCorrelationIds(Map correlationID)
     */
    public void setCorrelationIds(final Map<String, CorrelationID> correlationID) {
        this.correlationIDs = ModelUtil.replace(correlationID, LinkedHashMap::new);
    }

    /**
     * @see Components#addCorrelationID(String key, CorrelationID correlationID)
     */
    @Override
    public Components addCorrelationID(final String key, final CorrelationID correlationID) {
        this.correlationIDs = ModelUtil.add(key, correlationID, this.correlationIDs, LinkedHashMap<String, CorrelationID>::new);
        return this;
    }

    /**
     * @see Components#removeCorrelationID(String key)
     */
    @Override
    public void removeCorrelationID(final String key) {
        ModelUtil.remove(this.correlationIDs, key);
    }

    /**
     * @see Components#getOperationTraits()
     */
    @Override
    public Map<String, OperationTrait> getOperationTraits() {
        return operationTraits;
    }

    /**
     * @see Components#setOperationTraits(Map operationTrait)
     */
    @Override
    public void setOperationTraits(final Map<String, OperationTrait> operationTraits) {
        this.operationTraits = ModelUtil.replace(operationTraits, LinkedHashMap::new);
    }

    /**
     * @see Components#addOperationTrait(String key, OperationTrait operationTrait)
     */
    @Override
    public Components addOperationTrait(final String key, final OperationTrait operationTrait) {
        this.operationTraits = ModelUtil.add(key, operationTrait, this.operationTraits,
                LinkedHashMap<String, OperationTrait>::new);
        return this;
    }

    /**
     * @see Components#removeOperationTrait(String key)
     */
    @Override
    public void removeOperationTrait(final String key) {
        ModelUtil.remove(this.operationTraits, key);
    }

    /**
     * @see Components#getMessageTraits()
     */
    @Override
    public Map<String, MessageTrait> getMessageTraits() {
        return ModelUtil.unmodifiableMap(this.messageTraits);
    }

    /**
     * @see Components#setMessageTraits(Map messageTraits)
     */
    @Override
    public void setMessageTraits(final Map<String, MessageTrait> messageTraits) {
        this.messageTraits = ModelUtil.replace(messageTraits, LinkedHashMap::new);
    }

    /**
     * @see Components#addMessageTrait(String key, MessageTrait messageTrait)
     */
    @Override
    public Components addMessageTrait(final String key, final MessageTrait messageTrait) {
        this.messageTraits = ModelUtil.add(key, messageTrait, this.messageTraits, LinkedHashMap<String, MessageTrait>::new);
        return this;
    }

    /**
     * @see Components#removeMessageTrait(String key)
     */
    @Override
    public void removeMessageTrait(final String key) {
        ModelUtil.remove(this.messageTraits, key);
    }

    /**
     * @see Components#getServerBindings()
     */
    @Override
    public Map<String, ServerBinding> getServerBindings() {
        return ModelUtil.unmodifiableMap(this.serverBindings);
    }

    /**
     * @see Components#setServerBindings(Map serverBindings)
     */
    @Override
    public void setServerBindings(final Map<String, ServerBinding> serverBindings) {
        this.serverBindings = ModelUtil.replace(serverBindings, LinkedHashMap::new);
    }

    /**
     * @see Components#addServerBinding(String key, ServerBinding serverBinding)
     */
    @Override
    public Components addServerBinding(final String key, final ServerBinding serverBinding) {
        this.serverBindings = ModelUtil.add(key, serverBinding, this.serverBindings, LinkedHashMap<String, ServerBinding>::new);
        return this;
    }

    /**
     * @see Components#removeServerBinding(String key)
     */
    @Override
    public void removeServerBinding(final String key) {
        ModelUtil.remove(this.serverBindings, key);
    }

    /**
     * @see Components#getOperationBindings()
     */
    @Override
    public Map<String, OperationBinding> getOperationBindings() {
        return ModelUtil.unmodifiableMap(this.operationBindings);
    }

    /**
     * @see Components#setOperationBindings(Map operationBindings)
     */
    @Override
    public void setOperationBindings(final Map<String, OperationBinding> operationBindings) {
        this.operationBindings = ModelUtil.replace(operationBindings, LinkedHashMap::new);
    }

    /**
     * @see Components#addOperationBinding(String key, OperationBinding operationBinding)
     */
    @Override
    public Components addOperationBinding(final String key, final OperationBinding operationBinding) {
        this.operationBindings = ModelUtil.add(key, operationBinding, this.operationBindings,
                LinkedHashMap<String, OperationBinding>::new);
        return this;
    }

    /**
     * @see Components#removeOperationBinding(String key)
     */
    @Override
    public void removeOperationBinding(final String key) {
        ModelUtil.remove(this.operationBindings, key);
    }

    /**
     * @see Components#getMessageBindings()
     */
    @Override
    public Map<String, MessageBinding> getMessageBindings() {
        return ModelUtil.unmodifiableMap(this.messageBindings);
    }

    /**
     * @see Components#setMessageBindings(Map messageBindings)
     */
    @Override
    public void setMessageBindings(final Map<String, MessageBinding> messageBindings) {
        this.messageBindings = ModelUtil.replace(messageBindings, LinkedHashMap::new);
    }

    /**
     * @see Components#addMessageBinding(String key, MessageBinding messageBinding)
     */
    @Override
    public Components addMessageBinding(final String key, final MessageBinding messageBinding) {
        this.messageBindings = ModelUtil.add(key, messageBinding, this.messageBindings,
                LinkedHashMap<String, MessageBinding>::new);
        return this;
    }

    /**
     * @see Components#removeMessageBinding(String key)
     */
    @Override
    public void removeMessageBinding(final String key) {
        ModelUtil.remove(this.messageBindings, key);
    }

    @Override
    public String toString() {
        return "ComponentsImpl{" + "schemas=" + schemas + ", messages=" + messages + ", securitySchemes=" + securitySchemes
                + ", parameters=" + parameters
                + ", correlationIDs=" + correlationIDs + ", operationTraits=" + operationTraits + ", messageTraits="
                + messageTraits + ", serverBindings="
                + serverBindings + ", operationBindings=" + operationBindings + ", messageBindings=" + messageBindings + '}';
    }
}
