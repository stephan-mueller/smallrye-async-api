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
package io.smallrye.asyncapi.spec.models;

import java.util.Map;

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
 * Holds a set of reusable objects for different aspects of the AsyncAPI specification.
 *
 * All objects defined within the components object will have no effect on the API unless they are explicitly referenced from
 * properties
 * outside the components object.
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0/#componentsObject"
 */
public interface Components extends Constructible, Extensible<Components> {

    /**
     * Returns the schema of the components
     *
     * @return the schema of the components
     */
    Map<String, Schema> getSchemas();

    /**
     * Sets the schema of the components
     *
     * @param schemas the schema of the components
     */
    void setSchemas(Map<String, Schema> schemas);

    /**
     * Sets the schema of the components
     *
     * @param schemas the schema of the components
     * @return this Components instance
     */
    default Components schemas(Map<String, Schema> schemas) {
        setSchemas(schemas);
        return this;
    }

    /**
     * Adds the given schema to this Components' map of schemas with the given string as its key.
     *
     * @param key a key conforming to the format required for this object
     * @param schema a reusable schema object. null values will be rejected (implementation will throw an exception) or ignored.
     * @return the current Components object
     */
    Components addSchema(String key, Schema schema);

    /**
     * Removes the given schema to this Components' map of schemas with the given string as its key.
     *
     * @param key a key conforming to the format required for this object
     */
    void removeSchema(String key);

    /**
     * Returns the messages of the components
     *
     * @return the messages of the components
     */
    Map<String, Message> getMessages();

    /**
     * Sets the messages of the components
     *
     * @param messages the messages of the components
     */
    void setMessages(Map<String, Message> messages);

    /**
     * Sets the messages of the components
     *
     * @param messages the messages of the components
     * @return this Components instance
     */
    default Components messages(Map<String, Message> messages) {
        setMessages(messages);
        return this;
    }

    /**
     * Adds the given message to this Components' map of message with the given string as its key.
     *
     * @param key a key conforming to the format required for this object
     * @param message a reusable message object. null values will be rejected (implementation will throw an exception) or
     *        ignored.
     * @return the current Components object
     */
    Components addMessage(String key, Message message);

    /**
     * Removes the given message to this Components' map of messages with the given string as its key.
     *
     * @param key a key conforming to the format required for this object
     */
    void removeMessage(String key);

    /**
     * Returns the securitySchemes of the components
     *
     * @return the securitySchemes of the components
     */
    Map<String, SecurityScheme> getSecuritySchemes();

    /**
     * Sets the securitySchemes of the components
     *
     * @param securitySchemes the messages of the components
     */
    void setSecuritySchemes(Map<String, SecurityScheme> securitySchemes);

    /**
     * Sets the securitySchemes of the components
     *
     * @param securitySchemes the securitySchemes of the components
     * @return this Components instance
     */
    default Components securitySchemes(Map<String, SecurityScheme> securitySchemes) {
        setSecuritySchemes(securitySchemes);
        return this;
    }

    /**
     * Adds the given SecurityScheme to this Components' map of SecurityScheme with the given string as its key.
     *
     * @param key a key conforming to the format required for this object
     * @param securityScheme a reusable SecurityScheme object. null values will be rejected (implementation will throw an
     *        exception) or
     *        ignored.
     * @return the current Components object
     */
    Components addSecurityScheme(String key, SecurityScheme securityScheme);

    /**
     * Removes the given SecurityScheme to this Components' map of SecuritySchemes with the given string as its key.
     *
     * @param key a key conforming to the format required for this object
     */
    void removeSecurityScheme(String key);

    /**
     * Returns the parameters of the components
     *
     * @return the parameters of the components
     */
    Map<String, Parameter> getParameters();

    /**
     * Sets the parameters of the components
     *
     * @param parameters the parameters of the components
     */
    void setParameters(Map<String, Parameter> parameters);

    /**
     * Sets the parameters of the components
     *
     * @param parameters the parameters of the components
     * @return this Components instance
     */
    default Components parameters(Map<String, Parameter> parameters) {
        setParameters(parameters);
        return this;
    }

    /**
     * Adds the given Parameter to this Components' map of Parameter with the given string as its key.
     *
     * @param key a key conforming to the format required for this object
     * @param parameter a reusable Parameter object. null values will be rejected (implementation will throw an exception) or
     *        ignored.
     * @return the current Components object
     */
    Components addParameter(String key, Parameter parameter);

    /**
     * Removes the given Parameter to this Components' map of SecuritySchemes with the given string as its key.
     *
     * @param key a key conforming to the format required for this object
     */
    void removeParameter(String key);

    /**
     * Returns the correlationIDs of the components
     *
     * @return the correlationIDs of the components
     */
    Map<String, CorrelationID> getCorrelationIds();

    /**
     * Sets the correlationIds of the components
     *
     * @param correlationIds the correlationIds of the components
     */
    void setCorrelationIds(Map<String, CorrelationID> correlationIds);

    /**
     * Sets the correlationIds of the components
     *
     * @param correlationIds the correlationIds of the components
     * @return this Components instance
     */
    default Components correlationIds(Map<String, CorrelationID> correlationIds) {
        setCorrelationIds(correlationIds);
        return this;
    }

    /**
     * Adds the given CorrelationID to this Components' map of CorrelationIDs with the given string as its key.
     *
     * @param key a key conforming to the format required for this object
     * @param trait a reusable OperationTrait object. null values will be rejected (implementation will throw an exception) or
     *        ignored.
     * @return the current Components object
     */
    Components addCorrelationID(String key, CorrelationID trait);

    /**
     * Removes the given CorrelationID to this Components' map of CorrelationIDs with the given string as its key.
     *
     * @param key a key conforming to the format required for this object
     */
    void removeCorrelationID(String key);

    /**
     * Returns the operationTraits of the components
     *
     * @return the operationTraits of the components
     */
    Map<String, OperationTrait> getOperationTraits();

    /**
     * Sets the operationTraits of the components
     *
     * @param operationTraits the operationTraits of the components
     */
    void setOperationTraits(Map<String, OperationTrait> operationTraits);

    /**
     * Sets the operationTraits of the components
     *
     * @param operationTraits the operationTraits of the components
     * @return this Components instance
     */
    default Components operationTraits(Map<String, OperationTrait> operationTraits) {
        setOperationTraits(operationTraits);
        return this;
    }

    /**
     * Adds the given OperationTrait to this Components' map of OperationTraits with the given string as its key.
     *
     * @param key a key conforming to the format required for this object
     * @param trait a reusable OperationTrait object. null values will be rejected (implementation will throw an exception) or
     *        ignored.
     * @return the current Components object
     */
    Components addOperationTrait(String key, OperationTrait trait);

    /**
     * Removes the given OperationTrait to this Components' map of OperationTrait with the given string as its key.
     *
     * @param key a key conforming to the format required for this object
     */
    void removeOperationTrait(String key);

    /**
     * Returns the messageTraits of the components
     *
     * @return the messageTraits of the components
     */
    Map<String, MessageTrait> getMessageTraits();

    /**
     * Sets the messageTraits of the components
     *
     * @param messageTraits the messageTraits of the components
     */
    void setMessageTraits(Map<String, MessageTrait> messageTraits);

    /**
     * Sets the messageTraits of the components
     *
     * @param messageTraits the messageTraits of the components
     * @return this Components instance
     */
    default Components messageTraits(Map<String, MessageTrait> messageTraits) {
        setMessageTraits(messageTraits);
        return this;
    }

    /**
     * Adds the given MessageTrait to this Components' map of MessageTraits with the given string as its key.
     *
     * @param key a key conforming to the format required for this object
     * @param trait a reusable MessageTrait object. null values will be rejected (implementation will throw an exception) or
     *        ignored.
     * @return the current Components object
     */
    Components addMessageTrait(String key, MessageTrait trait);

    /**
     * Removes the given MessageTrait to this Components' map of SecuritySchemes with the given string as its key.
     *
     * @param key a key conforming to the format required for this object
     */
    void removeMessageTrait(String key);

    /**
     * Returns the serverBindings of the components
     *
     * @return the serverBindings of the components
     */
    Map<String, ServerBinding> getServerBindings();

    /**
     * Sets the serverBindings of the components
     *
     * @param serverBindings the serverBindings of the components
     */
    void setServerBindings(Map<String, ServerBinding> serverBindings);

    /**
     * Sets the serverBindings of the components
     *
     * @param serverBindings the serverBindings of the components
     * @return this Components instance
     */
    default Components serverBindings(Map<String, ServerBinding> serverBindings) {
        setServerBindings(serverBindings);
        return this;
    }

    /**
     * Adds the given ServerBinding to this Components' map of ServerBindings with the given string as its key.
     *
     * @param key a key conforming to the format required for this object
     * @param binding a reusable ServerBinding object. null values will be rejected (implementation will throw an exception) or
     *        ignored.
     * @return the current Components object
     */
    Components addServerBinding(String key, ServerBinding binding);

    /**
     * Removes the given ServerBinding to this Components' map of ServerBinding with the given string as its key.
     *
     * @param key a key conforming to the format required for this object
     */
    void removeServerBinding(String key);

    /**
     * Returns the operationBindings of the components
     *
     * @return the operationBindings of the components
     */
    Map<String, OperationBinding> getOperationBindings();

    /**
     * Sets the operationBindings of the components
     *
     * @param binding the operationBindings of the components
     */
    void setOperationBindings(Map<String, OperationBinding> binding);

    /**
     * Sets the operationBindings of the components
     *
     * @param binding the operationBindings of the components
     * @return this Components instance
     */
    default Components operationBindings(Map<String, OperationBinding> binding) {
        setOperationBindings(binding);
        return this;
    }

    /**
     * Adds the given OperationBinding to this Components' map of OperationBindings with the given string as its key.
     *
     * @param key a key conforming to the format required for this object
     * @param binding a reusable OperationBinding object. null values will be rejected (implementation will throw an exception)
     *        or ignored.
     * @return the current Components object
     */
    Components addOperationBinding(String key, OperationBinding binding);

    /**
     * Removes the given OperationBinding to this Components' map of OperationBinding with the given string as its key.
     *
     * @param key a key conforming to the format required for this object
     */
    void removeOperationBinding(String key);

    /**
     * Returns the messageBindings of the components
     *
     * @return the messageBindings of the components
     */
    Map<String, MessageBinding> getMessageBindings();

    /**
     * Sets the messageBindings of the components
     *
     * @param messageBindings the messageBindings of the components
     */
    void setMessageBindings(Map<String, MessageBinding> messageBindings);

    /**
     * Sets the messageBindings of the components
     *
     * @param messageBindings the messageBindings of the components
     * @return this Components instance
     */
    default Components messageBindings(Map<String, MessageBinding> messageBindings) {
        setMessageBindings(messageBindings);
        return this;
    }

    /**
     * Adds the given MessageBinding to this Components' map of MessageBindings with the given string as its key.
     *
     * @param key a key conforming to the format required for this object
     * @param binding a reusable MessageBinding object. null values will be rejected (implementation will throw an exception) or
     *        ignored.
     * @return the current Components object
     */
    Components addMessageBinding(String key, MessageBinding binding);

    /**
     * Removes the given MessageBinding to this Components' map of MessageBindings with the given string as its key.
     *
     * @param key a key conforming to the format required for this object
     */
    void removeMessageBinding(String key);
}
