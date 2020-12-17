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
import io.smallrye.asyncapi.spec.models.binding.ServerBinding;
import io.smallrye.asyncapi.spec.models.binding.mqtt.LastWill;
import io.smallrye.asyncapi.spec.models.binding.mqtt.MQTTServerBinding;

/**
 * An implementation of the {@link MQTTServerBinding} AsyncAPI model interface.
 */
public class MQTTServerBindingImpl extends ExtensibleImpl<ServerBinding> implements MQTTServerBinding, ModelImpl {

    private String clientId;

    private Boolean cleanSession;

    private LastWill lastWill;

    private String bindingVersion;

    /**
     * @see MQTTServerBinding#getClientId()
     */
    @Override
    public String getClientId() {
        return clientId;
    }

    /**
     * @see MQTTServerBinding#setClientId(String clientId)
     */
    @Override
    public void setClientId(final String clientId) {
        this.clientId = clientId;
    }

    /**
     * @see MQTTServerBinding#getCleanSession()
     */
    @Override
    public Boolean getCleanSession() {
        return cleanSession;
    }

    /**
     * @see MQTTServerBinding#setCleanSession(Boolean cleanSession)
     */
    @Override
    public void setCleanSession(final Boolean cleanSession) {
        this.cleanSession = cleanSession;
    }

    /**
     * @see MQTTServerBinding#getLastWill()
     */
    @Override
    public LastWill getLastWill() {
        return lastWill;
    }

    /**
     * @see MQTTServerBinding#setLastWill(LastWill lastWill)
     */
    @Override
    public void setLastWill(final LastWill lastWill) {
        this.lastWill = lastWill;
    }

    /**
     * @see MQTTServerBinding#getBindingVersion()
     */
    @Override
    public String getBindingVersion() {
        return bindingVersion;
    }

    /**
     * @see MQTTServerBinding#setBindingVersion(String bindingVersion)
     */
    @Override
    public void setBindingVersion(final String bindingVersion) {
        this.bindingVersion = bindingVersion;
    }
}
