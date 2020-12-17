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
import io.smallrye.asyncapi.spec.models.binding.MessageBinding;
import io.smallrye.asyncapi.spec.models.binding.kafka.KafkaMessageBinding;
import io.smallrye.asyncapi.spec.models.schema.Schema;

/**
 * An implementation of the {@link KafkaMessageBinding} AsyncAPI model interface.
 */
public class KafkaMessageBindingImpl extends ExtensibleImpl<MessageBinding> implements KafkaMessageBinding, ModelImpl {

    private Schema key;

    private String bindingVersion;

    /**
     * @see KafkaMessageBinding#getKey()
     */
    @Override
    public Schema getKey() {
        return key;
    }

    /**
     * @see KafkaMessageBinding#setKey(Schema key)
     */
    @Override
    public void setKey(final Schema key) {
        this.key = key;
    }

    /**
     * @see KafkaMessageBinding#getBindingVersion()
     */
    @Override
    public String getBindingVersion() {
        return bindingVersion;
    }

    /**
     * @see KafkaMessageBinding#setBindingVersion(String bindingVersion)
     */
    @Override
    public void setBindingVersion(final String bindingVersion) {
        this.bindingVersion = bindingVersion;
    }

    @Override
    public String toString() {
        return "KafkaMessageBindingImpl{" + "key=" + key + ", bindingVersion='" + bindingVersion + '\'' + '}';
    }
}
