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
package io.smallrye.asyncapi.core.api.models.binding.mqtt;

import io.smallrye.asyncapi.core.api.models.ExtensibleImpl;
import io.smallrye.asyncapi.core.api.models.ModelImpl;
import io.smallrye.asyncapi.spec.models.binding.OperationBinding;
import io.smallrye.asyncapi.spec.models.binding.mqtt.MQTTOperationBinding;

/**
 * An implementation of the {@link MQTTOperationBinding} AsyncAPI model interface.
 */
public class MQTTOperationBindingImpl extends ExtensibleImpl<OperationBinding> implements MQTTOperationBinding, ModelImpl {

    private Integer qos;

    private Boolean retain;

    private String bindingVersion;

    /**
     * @see MQTTOperationBinding#getQos()
     */
    @Override
    public Integer getQos() {
        return qos;
    }

    /**
     * @see MQTTOperationBinding#setQos(Integer qos)
     */
    @Override
    public void setQos(final Integer qos) {
        this.qos = qos;
    }

    /**
     * @see MQTTOperationBinding#getRetain()
     */
    @Override
    public Boolean getRetain() {
        return retain;
    }

    /**
     * @see MQTTOperationBinding#setRetain(Boolean retain)
     */
    @Override
    public void setRetain(final Boolean retain) {
        this.retain = retain;
    }

    /**
     * @see MQTTOperationBinding#getBindingVersion()
     */
    @Override
    public String getBindingVersion() {
        return bindingVersion;
    }

    /**
     * @see MQTTOperationBinding#setBindingVersion(String bindingVersion)
     */
    @Override
    public void setBindingVersion(final String bindingVersion) {
        this.bindingVersion = bindingVersion;
    }
}
