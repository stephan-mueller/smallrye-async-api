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
package io.smallrye.asyncapi.spec.models.binding.amqp;

import io.smallrye.asyncapi.spec.models.Constructible;
import io.smallrye.asyncapi.spec.models.binding.MessageBinding;

/**
 * Protocol-specific information for an AMQP 0-9-1 message.
 *
 * @see "https://github.com/asyncapi/bindings/blob/master/amqp/README.md#message-binding-object"
 */
public interface AMQPMessageBinding extends MessageBinding, Constructible {

    /**
     * Returns the content encoding of the channel
     *
     * @return the content encoding of the channel
     */
    String getContentEncoding();

    /**
     * Sets the content encoding of the channel
     *
     * @param contentType the content encoding of the channel
     */
    void setContentEncoding(String contentType);

    /**
     * Sets the content encoding of the channel
     *
     * @param contentType the content encoding of the channel
     * @return this AMQPChannelBinding instance
     */
    default AMQPMessageBinding contentType(String contentType) {
        setContentEncoding(contentType);
        return this;
    }

    /**
     * Returns the message type of the channel
     *
     * @return the message type of the channel
     */
    String getMessageType();

    /**
     * Sets the message type of the channel
     *
     * @param messageType the message type of the channel
     */
    void setMessageType(String messageType);

    /**
     * Sets the message type of the channel
     *
     * @param messageType the message type of the channel
     * @return this AMQPChannelBinding instance
     */
    default AMQPMessageBinding messageType(String messageType) {
        setMessageType(messageType);
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
     * @return this AMQPChannelBinding instance
     */
    default AMQPMessageBinding bindingVersion(String bindingVersion) {
        setBindingVersion(bindingVersion);
        return this;
    }
}
