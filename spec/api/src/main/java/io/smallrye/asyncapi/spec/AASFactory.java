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
package io.smallrye.asyncapi.spec;

import io.smallrye.asyncapi.spec.models.AsyncAPI;
import io.smallrye.asyncapi.spec.models.Components;
import io.smallrye.asyncapi.spec.models.Constructible;
import io.smallrye.asyncapi.spec.models.ExternalDocumentation;
import io.smallrye.asyncapi.spec.models.binding.ChannelBinding;
import io.smallrye.asyncapi.spec.models.binding.ChannelBindings;
import io.smallrye.asyncapi.spec.models.binding.MessageBinding;
import io.smallrye.asyncapi.spec.models.binding.MessageBindings;
import io.smallrye.asyncapi.spec.models.binding.OperationBinding;
import io.smallrye.asyncapi.spec.models.binding.OperationBindings;
import io.smallrye.asyncapi.spec.models.binding.ServerBinding;
import io.smallrye.asyncapi.spec.models.binding.ServerBindings;
import io.smallrye.asyncapi.spec.models.channel.ChannelItem;
import io.smallrye.asyncapi.spec.models.info.Contact;
import io.smallrye.asyncapi.spec.models.info.Info;
import io.smallrye.asyncapi.spec.models.info.License;
import io.smallrye.asyncapi.spec.models.message.CorrelationID;
import io.smallrye.asyncapi.spec.models.message.Message;
import io.smallrye.asyncapi.spec.models.message.MessageTrait;
import io.smallrye.asyncapi.spec.models.operation.Operation;
import io.smallrye.asyncapi.spec.models.operation.OperationTrait;
import io.smallrye.asyncapi.spec.models.parameter.Parameter;
import io.smallrye.asyncapi.spec.models.parameter.Parameters;
import io.smallrye.asyncapi.spec.models.schema.Schema;
import io.smallrye.asyncapi.spec.models.security.OAuthFlow;
import io.smallrye.asyncapi.spec.models.security.OAuthFlows;
import io.smallrye.asyncapi.spec.models.security.OAuthScope;
import io.smallrye.asyncapi.spec.models.security.SecurityRequirement;
import io.smallrye.asyncapi.spec.models.security.SecurityScheme;
import io.smallrye.asyncapi.spec.models.server.Server;
import io.smallrye.asyncapi.spec.models.server.ServerVariable;
import io.smallrye.asyncapi.spec.models.tag.Tag;
import io.smallrye.asyncapi.spec.spi.AASFactoryResolver;

/**
 * This class allows application developers to build new AsyncAPI model elements.
 *
 * For example, to start a new top-level AsyncAPI element with an ExternalDocument inside of it an application developer would
 * write:
 */
public final class AASFactory {

    private AASFactory() {
    }

    /**
     * This method creates a new instance of a constructible element from the AsyncAPI model tree.
     *
     * @param clazz represents a model which extends the {@link Constructible} interface
     * @param <T> describes the type parameter
     * @return a new instance of the requested model class
     *         <br>
     * @throws NullPointerException if the specified class is null
     * @throws IllegalArgumentException if an instance could not be created, most likely, due to an illegal or inappropriate
     *         class
     */
    public static <T extends Constructible> T createObject(Class<T> clazz) {
        return AASFactoryResolver.instance().createObject(clazz);
    }

    /**
     * This method creates a new {@link Components} instance.
     *
     * @return a new Components instance
     */
    public static Components createComponents() {
        return createObject(Components.class);
    }

    /**
     * This method creates a new {@link AsyncAPI} instance.
     *
     * @return a new AsyncAPI instance
     */
    public static AsyncAPI createAsyncAPI() {
        return createObject(AsyncAPI.class);
    }

    /**
     * This method creates a new {@link ExternalDocumentation} instance.
     *
     * @return a new ExternalDocumentation instance
     */
    public static ExternalDocumentation createExternalDocumentation() {
        return createObject(ExternalDocumentation.class);
    }

    /**
     * This method creates a new {@link Tag} instance.
     *
     * @return a new Tag instance
     */
    public static Tag createTag() {
        return createObject(Tag.class);
    }

    /**
     * This method creates a new {@link ServerVariable} instance.
     *
     * @return a new ServerVariable instance
     */
    public static ServerVariable createServerVariable() {
        return createObject(ServerVariable.class);
    }

    /**
     * This method creates a new {@link Server} instance.
     *
     * @return a new Server instance
     */
    public static Server createServer() {
        return createObject(Server.class);
    }

    /**
     * This method creates a new {@link SecurityScheme} instance.
     *
     * @return a new SecurityScheme instance
     */
    public static SecurityScheme createSecurityScheme() {
        return createObject(SecurityScheme.class);
    }

    /**
     * This method creates a new {@link SecurityRequirement} instance.
     *
     * @return a new SecurityRequirement instance
     */
    public static SecurityRequirement createSecurityRequirement() {
        return createObject(SecurityRequirement.class);
    }

    /**
     * This method creates a new {@link OAuthScope} instance.
     *
     * @return a new OAuthScope instance
     */
    public static OAuthScope createOAuthScope() {
        return createObject(OAuthScope.class);
    }

    /**
     * This method creates a new {@link OAuthFlows} instance.
     *
     * @return a new OAuthFlows instance
     */
    public static OAuthFlows createOAuthFlows() {
        return createObject(OAuthFlows.class);
    }

    /**
     * This method creates a new {@link OAuthFlow} instance.
     *
     * @return a new OAuthFlow instance
     */
    public static OAuthFlow createOAuthFlow() {
        return createObject(OAuthFlow.class);
    }

    /**
     * This method creates a new {@link Schema} instance.
     *
     * @return a new Schema instance
     */
    public static Schema createSchema() {
        return createObject(Schema.class);
    }

    /**
     * This method creates a new {@link Parameters} instance.
     *
     * @return a new Parameters instance
     */
    public static Parameters createParameters() {
        return createObject(Parameters.class);
    }

    /**
     * This method creates a new {@link Parameter} instance.
     *
     * @return a new Parameter instance
     */
    public static Parameter createParameter() {
        return createObject(Parameter.class);
    }

    /**
     * This method creates a new {@link License} instance.
     *
     * @return a new License instance
     */
    public static License createLicense() {
        return createObject(License.class);
    }

    /**
     * This method creates a new {@link Info} instance.
     *
     * @return a new Info instance
     */
    public static Info createInfo() {
        return createObject(Info.class);
    }

    /**
     * This method creates a new {@link Contact} instance.
     *
     * @return a new Contact instance
     */
    public static Contact createContact() {
        return createObject(Contact.class);
    }

    /**
     * This method creates a new {@link ChannelItem} instance.
     *
     * @return a new ChannelItem instance
     */
    public static ChannelItem createChannelItem() {
        return createObject(ChannelItem.class);
    }

    /**
     * This method creates a new {@link OperationTrait} instance.
     *
     * @return a new OperationTrait instance
     */
    public static OperationTrait createOperationTrait() {
        return createObject(OperationTrait.class);
    }

    /**
     * This method creates a new {@link Operation} instance.
     *
     * @return a new Operation instance
     */
    public static Operation createOperation() {
        return createObject(Operation.class);
    }

    /**
     * This method creates a new {@link MessageTrait} instance.
     *
     * @return a new MessageTrait instance
     */
    public static MessageTrait createMessageTrait() {
        return createObject(MessageTrait.class);
    }

    /**
     * This method creates a new {@link Message} instance.
     *
     * @return a new Message instance
     */
    public static Message createMessage() {
        return createObject(Message.class);
    }

    /**
     * This method creates a new {@link CorrelationID} instance.
     *
     * @return a new CorrelationID instance
     */
    public static CorrelationID createCorrelationID() {
        return createObject(CorrelationID.class);
    }

    /**
     * This method creates a new {@link ServerBindings} instance.
     *
     * @return a new ServerBindings instance
     */
    public static ServerBindings createServerBindings() {
        return createObject(ServerBindings.class);
    }

    /**
     * This method creates a new {@link ServerBinding} instance.
     *
     * @return a new ServerBinding instance
     */
    public static ServerBinding createServerBinding() {
        return createObject(ServerBinding.class);
    }

    /**
     * This method creates a new {@link OperationBindings} instance.
     *
     * @return a new OperationBindings instance
     */
    public static OperationBindings createOperationBindings() {
        return createObject(OperationBindings.class);
    }

    /**
     * This method creates a new {@link OperationBinding} instance.
     *
     * @return a new OperationBinding instance
     */
    public static OperationBinding createOperationBinding() {
        return createObject(OperationBinding.class);
    }

    /**
     * This method creates a new {@link MessageBindings} instance.
     *
     * @return a new MessageBindings instance
     */
    public static MessageBindings createMessageBindings() {
        return createObject(MessageBindings.class);
    }

    /**
     * This method creates a new {@link MessageBinding} instance.
     *
     * @return a new MessageBinding instance
     */
    public static MessageBinding createMessageBinding() {
        return createObject(MessageBinding.class);
    }

    /**
     * This method creates a new {@link ChannelBindings} instance.
     *
     * @return a new ChannelBindings instance
     */
    public static ChannelBindings createChannelBindings() {
        return createObject(ChannelBindings.class);
    }

    /**
     * This method creates a new {@link ChannelBinding} instance.
     *
     * @return a new ChannelBinding instance
     */
    public static ChannelBinding createChannelBinding() {
        return createObject(ChannelBinding.class);
    }

    // TODO: specific bindings
}
