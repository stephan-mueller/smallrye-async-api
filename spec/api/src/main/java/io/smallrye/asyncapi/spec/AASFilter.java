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
import io.smallrye.asyncapi.spec.models.channel.ChannelItem;
import io.smallrye.asyncapi.spec.models.message.Message;
import io.smallrye.asyncapi.spec.models.schema.Schema;
import io.smallrye.asyncapi.spec.models.security.SecurityScheme;
import io.smallrye.asyncapi.spec.models.server.Server;
import io.smallrye.asyncapi.spec.models.tag.Tag;

/**
 * This interface allows application developers to filter different parts of the AsyncAPI model tree.
 *
 * A common scenario is to dynamically augment (update or remove) AsyncAPI elements based on the environment that the
 * application is
 * currently in.
 *
 * The registration of this filter is controlled by setting the key <b>mp.io.smallrye.asyncapi.spec.filter</b> using one of the
 * configuration sources specified in <a href="https://github.com/eclipse/microprofile-config">MicroProfile Config</a>. The
 * value is the
 * fully qualified name of the filter implementation, which needs to be visible to the application's classloader.
 */
public interface AASFilter {

    /**
     * Allows filtering of the singleton AsyncAPI element.
     *
     * Implementers of this method can choose to update this element, or do nothing if no change is required. Note that one
     * cannot remove
     * this element from the model tree, hence the return type of void. This is the last method called for a given filter,
     * therefore it
     * symbolizes the end of processing by the vendor framework.
     *
     * @param asyncAPI the current AsyncAPI element
     */
    default void filterAsyncAPI(AsyncAPI asyncAPI) {
    }

    /**
     * Allows filtering of a particular Tag.
     *
     * Implementers of this method can choose to update the given Tag, pass it back as-is, or return null if removing this Tag.
     *
     * @param tag the current Tag element
     * @return the Tag to be used or null
     */
    default Tag filterTag(Tag tag) {
        return tag;
    }

    /**
     * Allows filtering of a particular Server.
     *
     * Implementers of this method can choose to update the given Server, pass it back as-is, or return null if removing this
     * Server.
     *
     * @param server the current Server element
     * @return the Server to be used or null
     */
    default Server filterServer(Server server) {
        return server;
    }

    /**
     * Allows filtering of a particular SecurityScheme.
     *
     * Implementers of this method can choose to update the given SecurityScheme, pass it back as-is, or return null if removing
     * this
     * SecurityScheme.
     *
     * @param securityScheme the current SecurityScheme element
     * @return the SecurityScheme to be used or null
     */
    default SecurityScheme filterSecurityScheme(SecurityScheme securityScheme) {
        return securityScheme;
    }

    /**
     * Allows filtering of a particular Schema.
     *
     * Implementers of this method can choose to update the given Schema, pass it back as-is, or return null if removing this
     * Schema.
     *
     * @param schema the current Schema element
     * @return the Schema to be used or null
     */
    default Schema filterSchema(Schema schema) {
        return schema;
    }

    /**
     * Allows filtering of a particular Message.
     *
     * Implementers of this method can choose to update the given Message, pass it back as-is, or return null if removing this
     * Message.
     *
     * @param message the current Schema element
     * @return the Schema to be used or null
     */
    default Message filterMessage(Message message) {
        return message;
    }

    /**
     * Allows filtering of a particular ChannelItem.
     *
     * Implementers of this method can choose to update the given ChannelItem, pass it back as-is, or return null if removing
     * this
     * ChannelItem.
     *
     * @param channelItem the current ChannelItem element
     * @return the ChannelItem to be used or null
     */
    default ChannelItem filterChannelItem(ChannelItem channelItem) {
        return channelItem;
    }
}
