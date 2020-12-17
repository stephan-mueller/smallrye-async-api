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
package io.smallrye.asyncapi.spec.models.channel;

import java.util.Map;

import io.smallrye.asyncapi.spec.models.Constructible;
import io.smallrye.asyncapi.spec.models.Extensible;

/**
 * Holds the relative paths to the individual channel and their operations. Channel paths are relative to servers.
 *
 * Channels are also known as "topics", "routing keys", "event types" or "paths".
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0#channelsObject"
 */
public interface Channels extends Constructible, Extensible<Channels> {

    /**
     * Returns the channelitems of the Channel's
     *
     * @return the channelitems of the Channel's
     */
    Map<String, ChannelItem> getChannels();

    /**
     * Sets this Channel's channelitems
     *
     * @param channels channelItems of this Channel
     */
    void setChannels(Map<String, ChannelItem> channels);

    /**
     * Adds a channelItem to the Channel map
     *
     * @param channelItem channelItems of this Channel
     * @param key name of the channelItem to add
     * @return this Channel instance
     */
    Channels addChannel(String key, ChannelItem channelItem);

    /**
     * Removes a channelItem from the Channel map
     *
     * @param name the name of the channelItem
     */
    void removeChannel(String name);
}
