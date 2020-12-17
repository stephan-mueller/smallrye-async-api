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
package io.smallrye.asyncapi.core.api.models.binding.kafka;

import io.smallrye.asyncapi.core.api.models.ExtensibleImpl;
import io.smallrye.asyncapi.core.api.models.ModelImpl;
import io.smallrye.asyncapi.spec.models.binding.OperationBinding;
import io.smallrye.asyncapi.spec.models.binding.kafka.KafkaOperationBinding;
import io.smallrye.asyncapi.spec.models.schema.Schema;

/**
 * An implementation of the {@link KafkaOperationBinding} AsyncAPI model interface.
 */
public class KafkaOperationBindingImpl extends ExtensibleImpl<OperationBinding> implements KafkaOperationBinding, ModelImpl {

    private Schema groupId;

    private Schema clientId;

    private String bindingVersion;

    /**
     * @see KafkaOperationBinding#getGroupId()
     */
    @Override
    public Schema getGroupId() {
        return groupId;
    }

    /**
     * @see KafkaOperationBinding#setGroupId(Schema groupId)
     */
    @Override
    public void setGroupId(final Schema groupId) {
        this.groupId = groupId;
    }

    /**
     * @see KafkaOperationBinding#getClientId()
     */
    @Override
    public Schema getClientId() {
        return clientId;
    }

    /**
     * @see KafkaOperationBinding#setClientId(Schema clientId)
     */
    @Override
    public void setClientId(final Schema clientId) {
        this.clientId = clientId;
    }

    /**
     * @see KafkaOperationBinding#getBindingVersion()
     */
    @Override
    public String getBindingVersion() {
        return bindingVersion;
    }

    /**
     * @see KafkaOperationBinding#setBindingVersion(String bindingVersion)
     */
    @Override
    public void setBindingVersion(final String bindingVersion) {
        this.bindingVersion = bindingVersion;
    }
}
