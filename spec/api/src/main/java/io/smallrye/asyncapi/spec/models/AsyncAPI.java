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

import java.util.List;

import io.smallrye.asyncapi.spec.models.channel.ChannelItem;
import io.smallrye.asyncapi.spec.models.channel.Channels;
import io.smallrye.asyncapi.spec.models.info.Info;
import io.smallrye.asyncapi.spec.models.server.Server;
import io.smallrye.asyncapi.spec.models.tag.Tag;

/**
 * AsyncAPI
 *
 * This is the root document object of the AsyncAPI document. It contains required and optional fields.
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0/#A2SObject"
 */
public interface AsyncAPI extends Constructible, Extensible<AsyncAPI> {

    /**
     * Returns the io.smallrye.asyncapi.spec property from an AsyncAPI instance.
     *
     * @return the semantic version number of the AsyncAPI Specification version that the AsyncAPI document uses
     **/
    String getAsyncapi();

    /**
     * Sets this AsyncAPI instance's io.smallrye.asyncapi.spec property to the given string.
     *
     * @param asyncapi the semantic version number of the AsyncAPI Specification version that the AsyncAPI document uses
     */
    void setAsyncapi(String asyncapi);

    /**
     * Sets this AsyncAPI instance's io.smallrye.asyncapi.spec property to the given string.
     *
     * @param asyncapi the semantic version number of the AsyncAPI Specification version that the AsyncAPI document uses
     * @return the current AsyncAPI object
     */
    default AsyncAPI asyncapi(String asyncapi) {
        setAsyncapi(asyncapi);
        return this;
    }

    /**
     * Returns the defaultContentType property from an AsyncAPI instance.
     *
     * @return the content type of the message's payload
     **/
    String getDefaultContentType();

    /**
     * Sets this AsyncAPI instance's defaultContentType property to the given string.
     *
     * @param defaultContentType the semantic version number of the AsyncAPI Specification version that the AsyncAPI document
     *        uses
     */
    void setDefaultContentType(String defaultContentType);

    /**
     * Sets this AsyncAPI instance's io.smallrye.asyncapi.spec property to the given string.
     *
     * @param defaultContentType the semantic version number of the AsyncAPI Specification version that the AsyncAPI document
     *        uses
     * @return the current AsyncAPI object
     */
    default AsyncAPI defaultContentType(String defaultContentType) {
        setDefaultContentType(defaultContentType);
        return this;
    }

    /**
     * Returns the identifier from an AsyncAPI instance.
     *
     * @return Identifier of the API
     **/
    String getIdentifier();

    /**
     * Sets this AsyncAPI instance's identifier property to the given object.
     *
     * @param identifier Identifier of the API
     */
    void setIdentifier(String identifier);

    /**
     * Sets this AsyncAPI instance's identifier property to the given object.
     *
     * @param identifier Identifier of the API
     * @return the current AsyncAPI object
     */
    default AsyncAPI identifier(String identifier) {
        setIdentifier(identifier);
        return this;
    }

    /**
     * Returns the info property from an AsyncAPI instance.
     *
     * @return metadata about the API
     **/
    Info getInfo();

    /**
     * Sets this AsyncAPI instance's info property to the given object.
     *
     * @param info metadata about the API
     */
    void setInfo(Info info);

    /**
     * Sets this AsyncAPI instance's info property to the given object.
     *
     * @param info metadata about the API
     * @return the current AsyncAPI object
     */
    default AsyncAPI info(Info info) {
        setInfo(info);
        return this;
    }

    /**
     * Returns the Servers defined in the API
     *
     * @return a copy List (potentially immutable) of Server objects which provide connectivity information to target servers
     **/
    List<Server> getServers();

    /**
     * Sets this AsyncAPI instance's servers property to the given servers.
     *
     * @param servers Server objects which provide connectivity information to target servers
     */
    void setServers(List<Server> servers);

    /**
     * Sets this AsyncAPI instance's servers property to the given servers.
     *
     * @param servers Server objects which provide connectivity information to target servers
     * @return the current AsyncAPI object
     */
    default AsyncAPI servers(List<Server> servers) {
        setServers(servers);
        return this;
    }

    /**
     * Adds the given server to this AsyncAPI instance's list of servers.
     *
     * @param server Server object which provides connectivity information to a target server
     * @return the current AsyncAPI object
     */
    AsyncAPI addServer(Server server);

    /**
     * Removes the given server to this AsyncAPI instance's list of servers.
     *
     * @param server Server object which provides connectivity information to a target server
     */
    void removeServer(Server server);

    /**
     * Returns this channels defined in the API
     *
     * @return this available channels and messages for the API.
     **/
    Channels getChannels();

    /**
     * Sets this available channels and messages for the API.
     *
     * @param channels Server objects which provide connectivity information to target servers
     */
    void setChannels(Channels channels);

    /**
     * Sets this available channels and messages for the API.
     *
     * @param channels this available channels and messages for the API.
     * @return this AsyncAPI object
     */
    default AsyncAPI channels(Channels channels) {
        setChannels(channels);
        return this;
    }

    /**
     * Adds the given channelItem to this AsyncAPI instance's list of channel items.
     *
     * @param key name of the channel
     * @param channelItem ChannelItem object which provides information to a channel
     * @return the current AsyncAPI object
     */
    AsyncAPI addChannelItem(String key, ChannelItem channelItem);

    /**
     * Removes the given channelItem to this AsyncAPI instance's list of channel items.
     *
     * @param key name of the channel
     */
    void removeChannelItem(String key);

    /**
     * Returns the components of the API
     *
     * @return the components of the API
     */
    Components getComponents();

    /**
     * Sets the components of the API
     *
     * @param components the components of the API
     */
    void setComponents(Components components);

    /**
     * Sets the components of the API
     *
     * @param components the tags of the API
     * @return this AsyncAPI instance
     */
    default AsyncAPI components(Components components) {
        setComponents(components);
        return this;
    }

    /**
     * Returns the tags of the API
     *
     * @return the tags of the API
     */
    List<Tag> getTags();

    /**
     * Sets the tags of the API
     *
     * @param tags the tags of the API
     */
    void setTags(List<Tag> tags);

    /**
     * Adds the given Tag to this AsyncAPI instance's list of tags items.
     *
     * @param tag Tag object which provides more information
     * @return the current AsyncAPI object
     */
    AsyncAPI addTag(Tag tag);

    /**
     * Removes the given Tag to this AsyncAPI instance's list of tags items.
     *
     * @param tag Tag object which provides more information
     */
    void removeTag(Tag tag);

    /**
     * Sets the tags of the API
     *
     * @param tags the tags of the API
     * @return this AsyncAPI instance
     */
    default AsyncAPI tags(List<Tag> tags) {
        setTags(tags);
        return this;
    }

    /**
     * Returns the external documentation of the API
     *
     * @return the external documentation of the API
     */
    ExternalDocumentation getExternalDocs();

    /**
     * Sets the external documentation of the API
     *
     * @param externalDocs the external documentation of the API
     */
    void setExternalDocs(ExternalDocumentation externalDocs);

    /**
     * Sets the external documentation of the API
     *
     * @param externalDocs the external documentation of the API
     * @return this AsyncAPI instance
     */
    default AsyncAPI externalDocs(ExternalDocumentation externalDocs) {
        setExternalDocs(externalDocs);
        return this;
    }
}
