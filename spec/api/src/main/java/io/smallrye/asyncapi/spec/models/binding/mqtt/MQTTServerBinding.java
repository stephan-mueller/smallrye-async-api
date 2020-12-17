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
package io.smallrye.asyncapi.spec.models.binding.mqtt;

import io.smallrye.asyncapi.spec.models.Constructible;
import io.smallrye.asyncapi.spec.models.binding.ServerBinding;

/**
 * Protocol-specific information for an MQTT server.
 *
 * @see "https://github.com/asyncapi/bindings/tree/master/mqtt#server-binding-object"
 */
public interface MQTTServerBinding extends ServerBinding, Constructible {

    /**
     * Returns the client identifier.
     *
     * @return client identifier.
     */
    String getClientId();

    /**
     * Sets the client identifier.
     *
     * @param clientId client identifier.
     */
    void setClientId(String clientId);

    /**
     * Sets the client identifier.
     *
     * @param clientId client identifier.
     * @return this MQTTServerBinding instance
     */
    default MQTTServerBinding clientId(String clientId) {
        setClientId(clientId);
        return this;
    }

    /**
     * @return whether to create a persistent connection or not. When false, the connection will be persistent.
     */
    Boolean getCleanSession();

    /**
     * Sets whether to create a persistent connection or not. When false, the connection will be persistent.
     *
     * @param cleanSession whether to create a persistent connection or not.
     */
    void setCleanSession(Boolean cleanSession);

    /**
     * Sets whether to create a persistent connection or not. When false, the connection will be persistent.
     *
     * @param cleanSession whether to create a persistent connection or not.
     * @return this MQTTServerBinding instance
     */
    default MQTTServerBinding cleanSession(Boolean cleanSession) {
        setCleanSession(cleanSession);
        return this;
    }

    /**
     * Returns the Last Will and Testament configuration.
     *
     * @return the Last Will and Testament configuration.
     */
    LastWill getLastWill();

    /**
     * Sets the Last Will and Testament configuration.
     *
     * @param lastWill the Last Will and Testament configuration.
     */
    void setLastWill(LastWill lastWill);

    /**
     * Sets the Last Will and Testament configuration.
     *
     * @param lastWill the Last Will and Testament configuration.
     * @return this MQTTServerBinding instance
     */
    default MQTTServerBinding lastWill(LastWill lastWill) {
        setLastWill(lastWill);
        return this;
    }

    /**
     * Returns the version of this binding
     *
     * @return the version of this binding
     */
    String getBindingVersion();

    /**
     * Sets the version of this binding
     *
     * @param bindingVersion the version of this binding
     */
    void setBindingVersion(String bindingVersion);

    /**
     * Sets the version of this binding
     *
     * @param bindingVersion the version of this binding
     * @return this MQTTServerBinding instance
     */
    default MQTTServerBinding bindingVersion(String bindingVersion) {
        setBindingVersion(bindingVersion);
        return this;
    }
}
