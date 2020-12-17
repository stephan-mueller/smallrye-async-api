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
import io.smallrye.asyncapi.spec.models.binding.amqp.Queue;

/**
 * An implementation of the {@link Queue} AsyncAPI model interface.
 */
public class QueueImpl extends ExtensibleImpl<Queue> implements Queue, ModelImpl {

    private String name;

    private Boolean durable;

    private Boolean exclusive;

    private Boolean autoDelete;

    private String virtualHost;

    /**
     * @see Queue#getName()
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * @see Queue#setName(String name)
     */
    @Override
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @see Queue#getDurable()
     */
    @Override
    public Boolean getDurable() {
        return this.durable;
    }

    /**
     * @see Queue#setDurable(Boolean durable)
     */
    @Override
    public void setDurable(final Boolean durable) {
        this.durable = durable;
    }

    /**
     * @see Queue#isExclusive()
     */
    @Override
    public Boolean isExclusive() {
        return this.exclusive;
    }

    /**
     * @see Queue#setExclusive(Boolean exclusive)
     */
    @Override
    public void setExclusive(final Boolean exclusive) {
        this.exclusive = exclusive;
    }

    /**
     * @see Queue#isAutoDelete()
     */
    @Override
    public Boolean isAutoDelete() {
        return this.autoDelete;
    }

    /**
     * @see Queue#setAutoDelete(Boolean autoDelete)
     */
    @Override
    public void setAutoDelete(final Boolean autoDelete) {
        this.autoDelete = autoDelete;
    }

    /**
     * @see Queue#getVirtualHost()
     */
    @Override
    public String getVirtualHost() {
        return this.virtualHost;
    }

    /**
     * @see Queue#setVirtualHost(String virtualHost)
     */
    @Override
    public void setVirtualHost(final String virtualHost) {
        this.virtualHost = virtualHost;
    }

    @Override
    public String toString() {
        return "QueueImpl{" + "name='" + name + '\'' + ", durable=" + durable + ", exclusive=" + exclusive + ", autoDelete="
                + autoDelete
                + ", virtualHost='" + virtualHost + '\'' + '}';
    }
}
