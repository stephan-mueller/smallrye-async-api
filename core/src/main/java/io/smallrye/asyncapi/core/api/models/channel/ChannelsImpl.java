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
package io.smallrye.asyncapi.core.api.models.channel;

import java.util.LinkedHashMap;
import java.util.Map;

import io.smallrye.asyncapi.core.api.models.ExtensibleImpl;
import io.smallrye.asyncapi.core.api.models.MapModel;
import io.smallrye.asyncapi.core.api.models.ModelImpl;
import io.smallrye.asyncapi.core.runtime.util.ModelUtil;
import io.smallrye.asyncapi.spec.models.channel.ChannelItem;
import io.smallrye.asyncapi.spec.models.channel.Channels;

/**
 * Holds the relative paths to the individual channel and their operations. Channel paths are relative to servers.
 * <p>
 * Channels are also known as "topics", "routing keys", "event types" or "paths".
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0#channelsObject"
 */
public class ChannelsImpl extends ExtensibleImpl<Channels> implements Channels, ModelImpl, MapModel<ChannelItem> {

    private Map<String, ChannelItem> channelItems;

    /**
     * @see Channels#getChannels()
     */
    @Override
    public Map<String, ChannelItem> getChannels() {
        return ModelUtil.unmodifiableMap(this.channelItems);
    }

    /**
     * @see Channels#setChannels(Map<String, ChannelItem>)
     */
    @Override
    public void setChannels(final Map<String, ChannelItem> channelItems) {
        this.channelItems = ModelUtil.replace(channelItems, LinkedHashMap::new);
    }

    /**
     * @see Channels#addChannel(String key, ChannelItem channelItem)
     */
    @Override
    public Channels addChannel(final String key, final ChannelItem channelItem) {
        this.channelItems = ModelUtil.add(key, channelItem, this.channelItems, LinkedHashMap<String, ChannelItem>::new);
        return this;
    }

    /**
     * @see Channels#removeChannel(String key)
     */
    @Override
    public void removeChannel(final String key) {
        ModelUtil.remove(this.channelItems, key);
    }

    @Override
    public Map<String, ChannelItem> getMap() {
        return this.channelItems;
    }

    @Override
    public void setMap(final Map<String, ChannelItem> map) {
        this.channelItems = map;
    }

    @Override
    public String toString() {
        return "ChannelsImpl{" + "channelItems=" + channelItems + '}';
    }
}
