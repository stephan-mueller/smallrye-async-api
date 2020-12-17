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
import io.smallrye.asyncapi.spec.models.binding.OperationBinding;
import io.smallrye.asyncapi.spec.models.schema.Schema;

/**
 * Protocol-specific information for a Kafka operation.
 *
 * @see "https://github.com/asyncapi/bindings/blob/master/kafka/README.md#operation-binding-object"
 */
public interface KafkaOperationBinding extends OperationBinding, Constructible {

    /**
     * Returns the id of the consumer group.
     *
     * @return the id of the consumer group.
     */
    Schema getGroupId();

    /**
     * Sets the id of the consumer group.
     *
     * @param groupId the id of the consumer group.
     */
    void setGroupId(Schema groupId);

    /**
     * Sets the id of the consumer group.
     *
     * @param groupId the id of the consumer group.
     * @return this KafkaOperationBinding instance
     */
    default KafkaOperationBinding groupId(Schema groupId) {
        setGroupId(groupId);
        return this;
    }

    /**
     * Returns the id of the consumer inside a consumer group.
     *
     * @return the id of the consumer inside a consumer group.
     */
    Schema getClientId();

    /**
     * Sets the id of the consumer inside a consumer group.
     *
     * @param clientId the id of the consumer inside a consumer group.
     */
    void setClientId(Schema clientId);

    /**
     * Sets the id of the consumer inside a consumer group.
     *
     * @param clientId the id of the consumer inside a consumer group.
     * @return this KafkaOperationBinding instance
     */
    default KafkaOperationBinding clientId(Schema clientId) {
        setClientId(clientId);
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
     * @return this KafkaOperationBinding instance
     */
    default KafkaOperationBinding bindingVersion(String bindingVersion) {
        setBindingVersion(bindingVersion);
        return this;
    }
}
