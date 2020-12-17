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
package io.smallrye.asyncapi.core.api.models.binding;

import java.util.ArrayList;
import java.util.List;

import io.smallrye.asyncapi.core.api.models.ExtensibleImpl;
import io.smallrye.asyncapi.core.api.models.ModelImpl;
import io.smallrye.asyncapi.core.runtime.util.ModelUtil;
import io.smallrye.asyncapi.spec.models.binding.ChannelBinding;
import io.smallrye.asyncapi.spec.models.binding.ChannelBindings;
import io.smallrye.asyncapi.spec.models.binding.amqp.AMQPChannelBinding;
import io.smallrye.asyncapi.spec.models.binding.ws.WebSocketChannelBinding;

/**
 * An implementation of the {@link ChannelBindings} AsyncAPI model interface.
 */
public class ChannelBindingsImpl extends ExtensibleImpl<ChannelBindings> implements ChannelBindings, ModelImpl {

    private List<ChannelBinding> bindings;

    private AMQPChannelBinding amqpBinding;

    private WebSocketChannelBinding wsBinding;

    /**
     * @see ChannelBindings#getBindings()
     */
    @Override
    public List<ChannelBinding> getBindings() {
        return ModelUtil.unmodifiableList(this.bindings);
    }

    /**
     * @see ChannelBindings#setBindings(List<ChannelBinding>)
     */
    @Override
    public void setBindings(final List<ChannelBinding> bindings) {
        this.bindings = ModelUtil.replace(bindings, ArrayList::new);
    }

    /**
     * @see ChannelBindings#addBinding(ChannelBinding binding)
     */
    @Override
    public ChannelBindings addBinding(final ChannelBinding binding) {
        this.bindings = ModelUtil.add(binding, this.bindings, ArrayList::new);
        return this;
    }

    /**
     * @see ChannelBindings#removeBinding(ChannelBinding binding)
     */
    @Override
    public void removeBinding(final ChannelBinding binding) {
        ModelUtil.remove(this.bindings, binding);
    }

    /**
     * @see ChannelBindings#getAMQPBinding()
     */
    @Override
    public AMQPChannelBinding getAMQPBinding() {
        return this.amqpBinding;
    }

    /**
     * @see ChannelBindings#setAMQPBinding(AMQPChannelBinding amqpBinding)
     */
    @Override
    public void setAMQPBinding(final AMQPChannelBinding amqpBinding) {
        this.amqpBinding = amqpBinding;
    }

    /**
     * @see ChannelBindings#getWebSocketBinding()
     */
    @Override
    public WebSocketChannelBinding getWebSocketBinding() {
        return this.wsBinding;
    }

    /**
     * @see ChannelBindings#setWebSocketBinding(WebSocketChannelBinding wsBinding)
     */
    @Override
    public void setWebSocketBinding(final WebSocketChannelBinding wsBinding) {
        this.wsBinding = wsBinding;
    }
}
