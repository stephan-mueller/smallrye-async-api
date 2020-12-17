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
package io.smallrye.asyncapi.core.api.models.binding.amqp;

import io.smallrye.asyncapi.core.api.models.ExtensibleImpl;
import io.smallrye.asyncapi.core.api.models.ModelImpl;
import io.smallrye.asyncapi.spec.models.binding.MessageBinding;
import io.smallrye.asyncapi.spec.models.binding.amqp.AMQPMessageBinding;

/**
 * An implementation of the {@link AMQPMessageBinding} AsyncAPI model interface.
 */
public class AMQPMessageBindingImpl extends ExtensibleImpl<MessageBinding> implements AMQPMessageBinding, ModelImpl {

    private String contentEncoding;

    private String messageType;

    private String bindingVersion;

    /**
     * @see AMQPMessageBinding#getContentEncoding()
     */
    @Override
    public String getContentEncoding() {
        return this.contentEncoding;
    }

    /**
     * @see AMQPMessageBinding#setContentEncoding(String contentEncoding)
     */
    @Override
    public void setContentEncoding(final String contentEncoding) {
        this.contentEncoding = contentEncoding;
    }

    /**
     * @see AMQPMessageBinding#getMessageType()
     */
    @Override
    public String getMessageType() {
        return this.messageType;
    }

    /**
     * @see AMQPMessageBinding#setMessageType(String messageType)
     */
    @Override
    public void setMessageType(final String messageType) {
        this.messageType = messageType;
    }

    /**
     * @see AMQPMessageBinding#getBindingVersion()
     */
    @Override
    public String getBindingVersion() {
        return this.bindingVersion;
    }

    /**
     * @see AMQPMessageBinding#setBindingVersion(String bindingVersion)
     */
    @Override
    public void setBindingVersion(final String bindingVersion) {
        this.bindingVersion = bindingVersion;
    }
}
