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
package io.smallrye.asyncapi.spec.models.binding.kafka;

import io.smallrye.asyncapi.spec.models.Constructible;
import io.smallrye.asyncapi.spec.models.binding.MessageBinding;
import io.smallrye.asyncapi.spec.models.schema.Schema;

/**
 * Protocol-specific information for a Kafka message.
 *
 * @see "https://github.com/asyncapi/bindings/blob/master/kafka/README.md#message-binding-object"
 */
public interface KafkaMessageBinding extends MessageBinding, Constructible {

    /**
     * Returns the message key
     *
     * @return the message key
     */
    Schema getKey();

    /**
     * Sets the message key
     *
     * @param key the message key
     */
    void setKey(Schema key);

    /**
     * Sets the message key
     *
     * @param key the message key
     * @return this KafkaMessageBinding instance
     */
    default KafkaMessageBinding bindingVersion(Schema key) {
        setKey(key);
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
     * @return this KafkaMessageBinding instance
     */
    default KafkaMessageBinding bindingVersion(String bindingVersion) {
        setBindingVersion(bindingVersion);
        return this;
    }
}
