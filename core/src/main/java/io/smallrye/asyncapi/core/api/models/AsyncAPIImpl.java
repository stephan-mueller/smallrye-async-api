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

import java.util.ArrayList;
import java.util.List;

import io.smallrye.asyncapi.core.api.models.channel.ChannelsImpl;
import io.smallrye.asyncapi.core.runtime.util.ModelUtil;
import io.smallrye.asyncapi.spec.models.AsyncAPI;
import io.smallrye.asyncapi.spec.models.Components;
import io.smallrye.asyncapi.spec.models.ExternalDocumentation;
import io.smallrye.asyncapi.spec.models.channel.ChannelItem;
import io.smallrye.asyncapi.spec.models.channel.Channels;
import io.smallrye.asyncapi.spec.models.info.Info;
import io.smallrye.asyncapi.spec.models.server.Server;
import io.smallrye.asyncapi.spec.models.tag.Tag;

/**
 * An implementation of the {@link AsyncAPI} AsyncAPI model interface.
 */
public class AsyncAPIImpl extends ExtensibleImpl<AsyncAPI> implements AsyncAPI, ModelImpl {

    private String asyncapi;

    private String defaultContentType;

    private String identifier;

    private Info info;

    private List<Server> servers;

    private Channels channels;

    private Components components;

    private List<Tag> tags;

    private ExternalDocumentation externalDocs;

    public AsyncAPIImpl() {
        this.channels = new ChannelsImpl();
    }

    /**
     * @see AsyncAPI#getAsyncapi()
     */
    @Override
    public String getAsyncapi() {
        return asyncapi;
    }

    /**
     * @see AsyncAPI#setAsyncapi(String asyncapi)
     */
    @Override
    public void setAsyncapi(final String asyncapi) {
        this.asyncapi = asyncapi;
    }

    /**
     * @see AsyncAPI#getDefaultContentType()
     */
    @Override
    public String getDefaultContentType() {
        return defaultContentType;
    }

    /**
     * @see AsyncAPI#setDefaultContentType(String defaultContentType)
     */
    @Override
    public void setDefaultContentType(final String defaultContentType) {
        this.defaultContentType = defaultContentType;
    }

    /**
     * @see AsyncAPI#getIdentifier()
     */
    @Override
    public String getIdentifier() {
        return identifier;
    }

    /**
     * @see AsyncAPI#setIdentifier(String identifier)
     */
    @Override
    public void setIdentifier(final String identifier) {
        this.identifier = identifier;
    }

    /**
     * @see AsyncAPI#getInfo()
     */
    @Override
    public Info getInfo() {
        return info;
    }

    /**
     * @see AsyncAPI#setInfo(Info info)
     */
    @Override
    public void setInfo(final Info info) {
        this.info = info;
    }

    /**
     * @see AsyncAPI#getServers()
     */
    @Override
    public List<Server> getServers() {
        return ModelUtil.unmodifiableList(this.servers);
    }

    /**
     * @see AsyncAPI#setServers(List<Server>)
     */
    @Override
    public void setServers(final List<Server> servers) {
        this.servers = ModelUtil.replace(servers, ArrayList::new);
    }

    /**
     * @see AsyncAPI#addServer(Server server)
     */
    @Override
    public AsyncAPI addServer(final Server server) {
        this.servers = ModelUtil.add(server, this.servers, ArrayList::new);
        return this;
    }

    /**
     * @see AsyncAPI#removeServer(Server server)
     */
    @Override
    public void removeServer(final Server server) {
        ModelUtil.remove(this.servers, server);
    }

    /**
     * @see AsyncAPI#getChannels()
     */
    @Override
    public Channels getChannels() {
        return this.channels;
    }

    /**
     * @see AsyncAPI#setChannels(Channels channels)
     */
    @Override
    public void setChannels(final Channels channels) {
        this.channels = channels;
    }

    /**
     * @see AsyncAPI#addChannelItem(String key, ChannelItem channelItem)
     */
    @Override
    public AsyncAPI addChannelItem(final String key, final ChannelItem channelItem) {
        this.channels.addChannel(key, channelItem);
        return this;
    }

    /**
     * @see AsyncAPI#removeChannelItem(String key)
     */
    @Override
    public void removeChannelItem(final String key) {
        this.channels.removeChannel(key);
    }

    /**
     * @see AsyncAPI#getComponents()
     */
    @Override
    public Components getComponents() {
        return components;
    }

    /**
     * @see AsyncAPI#setComponents(Components components)
     */
    @Override
    public void setComponents(final Components components) {
        this.components = components;
    }

    /**
     * @see AsyncAPI#getTags()
     */
    @Override
    public List<Tag> getTags() {
        return ModelUtil.unmodifiableList(this.tags);
    }

    /**
     * @see AsyncAPI#setTags(List<Tag>)
     */
    @Override
    public void setTags(final List<Tag> tags) {
        this.tags = ModelUtil.replace(tags, ArrayList::new);
    }

    /**
     * @see AsyncAPI#addTag(Tag tag)
     */
    @Override
    public AsyncAPI addTag(final Tag tag) {
        this.tags = ModelUtil.add(tag, this.tags, ArrayList::new);
        return this;
    }

    /**
     * @see AsyncAPI#removeTag(Tag tag)
     */
    @Override
    public void removeTag(final Tag tag) {
        ModelUtil.remove(this.tags, tag);
    }

    /**
     * @see AsyncAPI#getExternalDocs()
     */
    @Override
    public ExternalDocumentation getExternalDocs() {
        return externalDocs;
    }

    /**
     * @see AsyncAPI#setExternalDocs(ExternalDocumentation externalDocs)
     */
    @Override
    public void setExternalDocs(final ExternalDocumentation externalDocs) {
        this.externalDocs = externalDocs;
    }

    @Override
    public String toString() {
        return "AsyncAPIImpl{" + "asyncapi='" + asyncapi + '\'' + ", identifier=" + identifier + ", info=" + info + ", servers="
                + servers + ", channels="
                + channels + ", components=" + components + ", tags=" + tags + ", externalDocs=" + externalDocs + '}';
    }
}
