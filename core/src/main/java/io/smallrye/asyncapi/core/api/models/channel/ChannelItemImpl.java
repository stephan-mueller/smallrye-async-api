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

import io.smallrye.asyncapi.core.api.models.ExtensibleImpl;
import io.smallrye.asyncapi.core.api.models.ModelImpl;
import io.smallrye.asyncapi.spec.models.binding.ChannelBindings;
import io.smallrye.asyncapi.spec.models.channel.ChannelItem;
import io.smallrye.asyncapi.spec.models.operation.Operation;
import io.smallrye.asyncapi.spec.models.parameter.Parameters;

/**
 * An implementation of the {@link ChannelItem} AsyncAPI model interface.
 */
public class ChannelItemImpl extends ExtensibleImpl<ChannelItem> implements ChannelItem, ModelImpl {

    private String channel;

    private String description;

    private Operation subscribe;

    private Operation publish;

    private Parameters parameters;

    private ChannelBindings bindings;

    private String ref;

    /**
     * @see ChannelItem#getChannel()
     */
    @Override
    public String getChannel() {
        return this.channel;
    }

    /**
     * @see ChannelItem#setChannel(String channel)
     */
    @Override
    public void setChannel(final String channel) {
        this.channel = channel;
    }

    /**
     * @see ChannelItem#getDescription()
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * @see ChannelItem#setDescription(String description)
     */
    @Override
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @see ChannelItem#getSubscribe()
     */
    @Override
    public Operation getSubscribe() {
        return subscribe;
    }

    /**
     * @see ChannelItem#setSubscribe(Operation subscribe)
     */
    @Override
    public void setSubscribe(final Operation subscribe) {
        this.subscribe = subscribe;
    }

    /**
     * @see ChannelItem#getPublish()
     */
    @Override
    public Operation getPublish() {
        return publish;
    }

    /**
     * @see ChannelItem#setPublish(Operation publish)
     */
    @Override
    public void setPublish(final Operation publish) {
        this.publish = publish;
    }

    /**
     * @see ChannelItem#getParameters()
     */
    @Override
    public Parameters getParameters() {
        return parameters;
    }

    /**
     * @see ChannelItem#setParameters(Parameters parameters)
     */
    @Override
    public void setParameters(final Parameters parameters) {
        this.parameters = parameters;
    }

    /**
     * @see ChannelItem#getBindings()
     */
    @Override
    public ChannelBindings getBindings() {
        return bindings;
    }

    /**
     * @see ChannelItem#setBindings(ChannelBindings bindings)
     */
    @Override
    public void setBindings(final ChannelBindings bindings) {
        this.bindings = bindings;
    }

    /**
     * @see ChannelItem#getRef()
     */
    @Override
    public String getRef() {
        return this.ref;
    }

    /**
     * @see ChannelItem#setRef(String ref)
     */
    @Override
    public void setRef(final String ref) {
        this.ref = ref;
    }

    @Override
    public String toString() {
        return "ChannelItemImpl{" + "channel='" + channel + '\'' + ", description='" + description + '\'' + ", subscribe="
                + subscribe
                + ", publish=" + publish + ", parameters=" + parameters + ", bindings=" + bindings + ", ref='" + ref + '\''
                + '}';
    }
}
