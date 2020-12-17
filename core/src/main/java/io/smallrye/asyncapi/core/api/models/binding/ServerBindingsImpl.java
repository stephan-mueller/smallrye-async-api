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
import io.smallrye.asyncapi.spec.models.binding.ServerBinding;
import io.smallrye.asyncapi.spec.models.binding.ServerBindings;
import io.smallrye.asyncapi.spec.models.binding.mqtt.MQTTServerBinding;

/**
 * An implementation of the {@link ServerBindings} AsyncAPI model interface.
 */
public class ServerBindingsImpl extends ExtensibleImpl<ServerBindings> implements ServerBindings, ModelImpl {

    private List<ServerBinding> bindings;

    private MQTTServerBinding mqttBindings;

    /**
     * @see ServerBindings#getBindings()
     */
    @Override
    public List<ServerBinding> getBindings() {
        return ModelUtil.unmodifiableList(this.bindings);
    }

    /**
     * @see ServerBindings#setBindings(List<ServerBinding>)
     */
    @Override
    public void setBindings(final List<ServerBinding> bindings) {
        this.bindings = ModelUtil.replace(bindings, ArrayList::new);
    }

    /**
     * @see ServerBindings#addBinding(ServerBinding binding)
     */
    @Override
    public ServerBindings addBinding(final ServerBinding binding) {
        this.bindings = ModelUtil.add(binding, this.bindings, ArrayList::new);
        return this;
    }

    /**
     * @see ServerBindings#removeBinding(ServerBinding binding)
     */
    @Override
    public void removeBinding(final ServerBinding binding) {
        ModelUtil.remove(this.bindings, binding);
    }

    /**
     * @see ServerBindings#getMQTTBinding()
     */
    @Override
    public MQTTServerBinding getMQTTBinding() {
        return this.mqttBindings;
    }

    /**
     * @see ServerBindings#setMQTTBinding(MQTTServerBinding mqttBinding)
     */
    @Override
    public void setMQTTBinding(final MQTTServerBinding mqttBinding) {
        this.mqttBindings = mqttBinding;
    }
}
