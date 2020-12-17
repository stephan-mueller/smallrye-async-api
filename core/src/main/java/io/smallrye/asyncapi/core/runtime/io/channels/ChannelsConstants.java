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
package io.smallrye.asyncapi.core.runtime.io.channels;

import org.jboss.jandex.DotName;

import io.smallrye.asyncapi.spec.annotations.channel.ChannelItem;

/**
 * Constants related to Channels
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0#channelsObject"
 */
public class ChannelsConstants {

    static final DotName DOTNAME_CHANNEL_ITEM = DotName.createSimple(ChannelItem.class.getName());

    public static final String PROP_CHANNEL = "channel";

    public static final String PROP_DESCRIPTION = "description";

    public static final String PROP_SUBSCRIBE = "subscribe";

    public static final String PROP_PUBLISH = "publish";

    public static final String PROP_PARAMETERS = "parameters";

    public static final String PROP_BINDING = "bindings";

    public static final String PROP_REF = "ref";

    public ChannelsConstants() {
    }
}
