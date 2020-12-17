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

import io.smallrye.asyncapi.spec.models.Constructible;
import io.smallrye.asyncapi.spec.models.Extensible;
import io.smallrye.asyncapi.spec.models.Reference;
import io.smallrye.asyncapi.spec.models.binding.ChannelBindings;
import io.smallrye.asyncapi.spec.models.operation.Operation;
import io.smallrye.asyncapi.spec.models.parameter.Parameters;

/**
 * Describes the operations available on a single channel.
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0/#channelsObject"
 */
public interface ChannelItem extends Constructible, Extensible<ChannelItem>, Reference<ChannelItem> {

    /**
     * Returns the channel of the ChannelItem
     *
     * @return the channel of the ChannelItem
     */
    String getChannel();

    /**
     * Sets the channel of the ChannelItem
     *
     * @param channel the channel of the ChannelItem
     */
    void setChannel(String channel);

    /**
     * Sets the channel of the ChannelItem
     *
     * @param channel the channel of the ChannelItem
     * @return this ChannelItem instance
     */
    default ChannelItem channel(String channel) {
        setChannel(channel);
        return this;
    }

    /**
     * Returns the description of the ChannelItem
     *
     * @return the description of the ChannelItem
     */
    String getDescription();

    /**
     * Sets the description of the ChannelItem
     *
     * @param description the description of the ChannelItem
     */
    void setDescription(String description);

    /**
     * Sets the description of the ChannelItem
     *
     * @param description the description of the ChannelItem
     * @return this ChannelItem instance
     */
    default ChannelItem description(String description) {
        setDescription(description);
        return this;
    }

    /**
     * Returns the subscribe operation of the ChannelItem
     *
     * @return the subscribe operation of the ChannelItem
     */
    Operation getSubscribe();

    /**
     * Sets the subscribe operation of the ChannelItem
     *
     * @param operation the subscribe operation of the ChannelItem
     */
    void setSubscribe(Operation operation);

    /**
     * Sets the subscribe operation of the ChannelItem
     *
     * @param operation the subscribe operation of the ChannelItem
     * @return this ChannelItem instance
     */
    default ChannelItem subscribe(Operation operation) {
        setSubscribe(operation);
        return this;
    }

    /**
     * Returns the publish operation of the ChannelItem
     *
     * @return the publish operation of the ChannelItem
     */
    Operation getPublish();

    /**
     * Sets the publish operation of the ChannelItem
     *
     * @param operation the publish operation of the ChannelItem
     */
    void setPublish(Operation operation);

    /**
     * Sets the publish operation of the ChannelItem
     *
     * @param publish the publish operation of the ChannelItem
     * @return this ChannelItem instance
     */
    default ChannelItem publish(Operation publish) {
        setPublish(publish);
        return this;
    }

    /**
     * Returns the parameters of the ChannelItem
     *
     * @return the parameters of the ChannelItem
     */
    Parameters getParameters();

    /**
     * Sets the parameters of the ChannelItem
     *
     * @param parameters the parameters of the ChannelItem
     */
    void setParameters(Parameters parameters);

    /**
     * Sets the parameters of the ChannelItem
     *
     * @param parameters the parameters of the ChannelItem
     * @return this ChannelItem instance
     */
    default ChannelItem parameters(Parameters parameters) {
        setParameters(parameters);
        return this;
    }

    /**
     * Returns the bindings of the ChannelItem
     *
     * @return the bindings of the ChannelItem
     */
    ChannelBindings getBindings();

    /**
     * Sets the bindings of the ChannelItem
     *
     * @param bindings the bindings of the ChannelItem
     */
    void setBindings(ChannelBindings bindings);

    /**
     * Sets the bindings of the ChannelItem
     *
     * @param bindings the bindings of the ChannelItem
     * @return this ChannelItem instance
     */
    default ChannelItem bindings(ChannelBindings bindings) {
        setBindings(bindings);
        return this;
    }
}
