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
import io.smallrye.asyncapi.spec.models.binding.OperationBinding;

/**
 * Protocol-specific information for an MQTT operation.
 *
 * @see "https://github.com/asyncapi/bindings/blob/master/mqtt/README.md#operation-binding-object"
 */
public interface MQTTOperationBinding extends OperationBinding, Constructible {

    /**
     * @return how hard the broker/client will try to ensure that a message is received.
     */
    Integer getQos();

    /**
     * Sets how hard the broker/client will try to ensure that a message is received.
     *
     * @param qos how hard the broker/client will try to ensure that a message is received.
     */
    void setQos(Integer qos);

    /**
     * Sets how hard the broker/client will try to ensure that a message is received.
     *
     * @param qos how hard the broker/client will try to ensure that a message is received.
     * @return this MQTTOperationBinding instance
     */
    default MQTTOperationBinding qos(Integer qos) {
        setQos(qos);
        return this;
    }

    /**
     * @return whether the broker should retain the message or not.
     */
    Boolean getRetain();

    /**
     * Sets whether the broker should retain the message or not.
     *
     * @param retain whether the broker should retain the message or not.
     */
    void setRetain(Boolean retain);

    /**
     * Sets whether the broker should retain the message or not.
     *
     * @param retain whether the broker should retain the message or not.
     * @return this MQTTOperationBinding instance
     */
    default MQTTOperationBinding retain(Boolean retain) {
        setRetain(retain);
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
     * @return this MQTTOperationBinding instance
     */
    default MQTTOperationBinding bindingVersion(String bindingVersion) {
        setBindingVersion(bindingVersion);
        return this;
    }
}
