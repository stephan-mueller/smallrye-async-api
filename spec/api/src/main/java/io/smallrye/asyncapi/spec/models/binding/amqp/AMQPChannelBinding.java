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
import io.smallrye.asyncapi.spec.models.binding.ChannelBinding;

/**
 * Protocol-specific information for an AMQP 0-9-1 channel.
 *
 * @see "https://github.com/asyncapi/bindings/blob/master/amqp1/README.md#channel"
 */
public interface AMQPChannelBinding extends ChannelBinding, Constructible {

    /**
     * Returns the type of the channel
     *
     * @return the type of the channel
     */
    String getIs();

    /**
     * Sets the type of the channel
     *
     * @param is the type of the channel
     */
    void setIs(String is);

    /**
     * Sets the type of the channel
     *
     * @param is the type of the channel
     * @return this AMQPChannelBinding instance
     */
    default AMQPChannelBinding is(String is) {
        setIs(is);
        return this;
    }

    /**
     * Returns the exchange properties.
     *
     * @return the exchange properties.
     */
    Exchange getExchange();

    /**
     * Sets the exchange properties.
     *
     * @param exchange the exchange properties.
     */
    void setExchange(Exchange exchange);

    /**
     * Sets the exchange properties.
     *
     * @param exchange the exchange properties.
     * @return this AMQPChannelBinding instance
     */
    default AMQPChannelBinding is(Exchange exchange) {
        setExchange(exchange);
        return this;
    }

    /**
     * Returns the queue properties.
     *
     * @return the queue properties.
     */
    Queue getQueue();

    /**
     * Sets the queue properties.
     *
     * @param queue the queue properties.
     */
    void setQueue(Queue queue);

    /**
     * Sets the queue properties.
     *
     * @param queue the queue properties.
     * @return this AMQPChannelBinding instance
     */
    default AMQPChannelBinding is(Queue queue) {
        setQueue(queue);
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
    default AMQPChannelBinding bindingVersion(String bindingVersion) {
        setBindingVersion(bindingVersion);
        return this;
    }
}
