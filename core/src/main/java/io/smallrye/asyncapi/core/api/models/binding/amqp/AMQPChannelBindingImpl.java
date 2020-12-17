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
import io.smallrye.asyncapi.spec.models.binding.ChannelBinding;
import io.smallrye.asyncapi.spec.models.binding.amqp.AMQPChannelBinding;
import io.smallrye.asyncapi.spec.models.binding.amqp.Exchange;
import io.smallrye.asyncapi.spec.models.binding.amqp.Queue;

/**
 * An implementation of the {@link AMQPChannelBinding} AsyncAPI model interface.
 */
public class AMQPChannelBindingImpl extends ExtensibleImpl<ChannelBinding> implements AMQPChannelBinding, ModelImpl {

    private String is;

    private Exchange exchange;

    private Queue queue;

    private String bindingVersion;

    /**
     * @see AMQPChannelBinding#getIs()
     */
    @Override
    public String getIs() {
        return this.is;
    }

    /**
     * @see AMQPChannelBinding#setIs(String is)
     */
    @Override
    public void setIs(final String is) {
        this.is = is;
    }

    /**
     * @see AMQPChannelBinding#getExchange()
     */
    @Override
    public Exchange getExchange() {
        return this.exchange;
    }

    /**
     * @see AMQPChannelBinding#setExchange(Exchange exchange)
     */
    @Override
    public void setExchange(final Exchange exchange) {
        this.exchange = exchange;
    }

    /**
     * @see AMQPChannelBinding#getQueue()
     */
    @Override
    public Queue getQueue() {
        return this.queue;
    }

    /**
     * @see AMQPChannelBinding#setQueue(Queue queue)
     */
    @Override
    public void setQueue(final Queue queue) {
        this.queue = queue;
    }

    /**
     * @see AMQPChannelBinding#getBindingVersion()
     */
    @Override
    public String getBindingVersion() {
        return this.bindingVersion;
    }

    /**
     * @see AMQPChannelBinding#setBindingVersion(String bindingVersion)
     */
    @Override
    public void setBindingVersion(final String bindingVersion) {
        this.bindingVersion = bindingVersion;
    }

    @Override
    public String toString() {
        return "AMQPChannelBindingImpl{" + "is='" + is + '\'' + ", exchange=" + exchange + ", queue=" + queue
                + ", bindingVersion='"
                + bindingVersion + '\'' + '}';
    }
}
