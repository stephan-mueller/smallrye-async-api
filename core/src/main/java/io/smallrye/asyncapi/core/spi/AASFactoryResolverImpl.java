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
package io.smallrye.asyncapi.core.spi;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import io.smallrye.asyncapi.core.api.models.AsyncAPIImpl;
import io.smallrye.asyncapi.core.api.models.ComponentsImpl;
import io.smallrye.asyncapi.core.api.models.ExternalDocumentationImpl;
import io.smallrye.asyncapi.core.api.models.channel.ChannelItemImpl;
import io.smallrye.asyncapi.core.api.models.channel.ChannelsImpl;
import io.smallrye.asyncapi.core.api.models.info.ContactImpl;
import io.smallrye.asyncapi.core.api.models.info.InfoImpl;
import io.smallrye.asyncapi.core.api.models.info.LicenseImpl;
import io.smallrye.asyncapi.core.api.models.message.CorrelationIDImpl;
import io.smallrye.asyncapi.core.api.models.message.MessageImpl;
import io.smallrye.asyncapi.core.api.models.message.MessageTraitImpl;
import io.smallrye.asyncapi.core.api.models.operation.OperationImpl;
import io.smallrye.asyncapi.core.api.models.operation.OperationTraitImpl;
import io.smallrye.asyncapi.core.api.models.parameter.ParameterImpl;
import io.smallrye.asyncapi.core.api.models.parameter.ParametersImpl;
import io.smallrye.asyncapi.core.api.models.schema.SchemaImpl;
import io.smallrye.asyncapi.core.api.models.schema.SchemaPropertyImpl;
import io.smallrye.asyncapi.core.api.models.security.OAuthFlowImpl;
import io.smallrye.asyncapi.core.api.models.security.OAuthFlowsImpl;
import io.smallrye.asyncapi.core.api.models.security.OAuthScopeImpl;
import io.smallrye.asyncapi.core.api.models.security.SecurityRequirementImpl;
import io.smallrye.asyncapi.core.api.models.security.SecuritySchemeImpl;
import io.smallrye.asyncapi.core.api.models.server.ServerImpl;
import io.smallrye.asyncapi.core.api.models.server.ServerVariableImpl;
import io.smallrye.asyncapi.core.api.models.tag.TagImpl;
import io.smallrye.asyncapi.spec.models.AsyncAPI;
import io.smallrye.asyncapi.spec.models.Components;
import io.smallrye.asyncapi.spec.models.Constructible;
import io.smallrye.asyncapi.spec.models.ExternalDocumentation;
import io.smallrye.asyncapi.spec.models.channel.ChannelItem;
import io.smallrye.asyncapi.spec.models.channel.Channels;
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
import io.smallrye.asyncapi.spec.models.schema.SchemaProperty;
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
 * An implementation of the AsyncAPI spec's {@link AASFactoryResolverImpl}. This class
 * is responsible for constructing vendor specific models given a {@link Constructible}
 * model interface.
 */
public class AASFactoryResolverImpl extends AASFactoryResolver {

    private static final Map<Class<? extends Constructible>, Class<? extends Constructible>> registry = new HashMap<>();

    static {
        registry.put(AsyncAPI.class, AsyncAPIImpl.class);
        registry.put(ChannelItem.class, ChannelItemImpl.class);
        registry.put(Channels.class, ChannelsImpl.class);
        registry.put(Components.class, ComponentsImpl.class);
        registry.put(Contact.class, ContactImpl.class);
        registry.put(CorrelationID.class, CorrelationIDImpl.class);
        registry.put(ExternalDocumentation.class, ExternalDocumentationImpl.class);
        registry.put(Info.class, InfoImpl.class);
        registry.put(License.class, LicenseImpl.class);
        registry.put(Message.class, MessageImpl.class);
        registry.put(MessageTrait.class, MessageTraitImpl.class);
        registry.put(OAuthFlow.class, OAuthFlowImpl.class);
        registry.put(OAuthFlows.class, OAuthFlowsImpl.class);
        registry.put(OAuthScope.class, OAuthScopeImpl.class);
        registry.put(Operation.class, OperationImpl.class);
        registry.put(OperationTrait.class, OperationTraitImpl.class);
        registry.put(Parameter.class, ParameterImpl.class);
        registry.put(Parameters.class, ParametersImpl.class);
        registry.put(Schema.class, SchemaImpl.class);
        registry.put(SchemaProperty.class, SchemaPropertyImpl.class);
        registry.put(SecurityRequirement.class, SecurityRequirementImpl.class);
        registry.put(SecurityScheme.class, SecuritySchemeImpl.class);
        registry.put(Server.class, ServerImpl.class);
        registry.put(ServerVariable.class, ServerVariableImpl.class);
        registry.put(Tag.class, TagImpl.class);
    }

    /**
     * @see AASFactoryResolver#createObject(Class)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Constructible> T createObject(Class<T> clazz) {
        if (clazz == null) {
            throw new NullPointerException();
        }

        Class<? extends Constructible> implClass = registry.get(clazz);

        if (implClass == null) {
            throw SpiMessages.msg.classNotConstructible(clazz.getName());
        }

        try {
            return (T) implClass.getConstructor()
                    .newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
