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
import io.smallrye.asyncapi.spec.models.Extensible;

/**
 * Last Will and Testament configuration object
 *
 * @see "https://github.com/asyncapi/bindings/tree/master/mqtt#server"
 */
public interface LastWill extends Constructible, Extensible<LastWill> {

    /**
     * Returns the topic where the Last Will and Testament message will be sent
     *
     * @return topic of the Last Will
     */
    String getTopic();

    /**
     * Sets the topic where the Last Will and Testament message will be sent
     *
     * @param topic topic of the Last Will
     */
    void setTopic(String topic);

    /**
     * Sets the topic where the Last Will and Testament message will be sent
     *
     * @param topic topic of the Last Will
     * @return topic of the Last Will
     */
    default LastWill topic(String topic) {
        setTopic(topic);
        return this;
    }

    /**
     * Returns how hard the broker/client will try to ensure that the Last Will and Testament message is received.
     *
     * @return level of retry
     */
    Integer getQos();

    /**
     * Sets how hard the broker/client will try to ensure that the Last Will and Testament message is received.
     *
     * @param qos level of retry
     */
    void setQos(Integer qos);

    /**
     * Sets the topic where the Last Will and Testament message will be sent
     *
     * @param qos level of retry
     * @return level of retry
     */
    default LastWill qos(Integer qos) {
        setQos(qos);
        return this;
    }

    /**
     * Returns the message of the Last Will
     *
     * @return message of the Last Will
     */
    String getMessage();

    /**
     * Sets the message of the Last Will
     *
     * @param message of the Last Will
     */
    void setMessage(String message);

    /**
     * Sets the message of the Last Will
     *
     * @param message of the Last Will
     * @return the Last Will
     */
    default LastWill message(String message) {
        setMessage(message);
        return this;
    }

    /**
     * @return whether the broker should retain the Last Will and Testament message or not.
     */
    Boolean isRetain();

    /**
     * Sets whether the broker should retain the Last Will and Testament message or not.
     *
     * @param retain whether the broker should retain the Last Will and Testament message or not.
     */
    void setRetain(Boolean retain);

    /**
     * Sets whether the broker should retain the Last Will and Testament message or not.
     *
     * @param retain whether the broker should retain the Last Will and Testament message or not.
     * @return whether the broker should retain the Last Will and Testament message or not.
     */
    default LastWill retain(Boolean retain) {
        setRetain(retain);
        return this;
    }
}
