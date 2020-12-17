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
import io.smallrye.asyncapi.spec.annotations.binding.amqp.ExchangeType;
import io.smallrye.asyncapi.spec.models.binding.amqp.Exchange;

/**
 * An implementation of the {@link Exchange} AsyncAPI model interface.
 */
public class ExchangeImpl extends ExtensibleImpl<Exchange> implements Exchange, ModelImpl {

    private String name;

    private ExchangeType exchangeType;

    private Boolean durable;

    private Boolean autoDelete;

    private String virtualHost;

    /**
     * @see Exchange#getName()
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * @see Exchange#setName(String name)
     */
    @Override
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @see Exchange#getExchangeType()
     */
    @Override
    public ExchangeType getExchangeType() {
        return this.exchangeType;
    }

    /**
     * @see Exchange#setExchangeType(ExchangeType exchangeType)
     */
    @Override
    public void setExchangeType(final ExchangeType exchangeType) {
        this.exchangeType = exchangeType;
    }

    /**
     * @see Exchange#isDurable()
     */
    @Override
    public Boolean isDurable() {
        return this.durable;
    }

    /**
     * @see Exchange#setDurable(Boolean durable)
     */
    @Override
    public void setDurable(final Boolean durable) {
        this.durable = durable;
    }

    /**
     * @see Exchange#isAutoDelete()
     */
    @Override
    public Boolean isAutoDelete() {
        return this.autoDelete;
    }

    /**
     * @see Exchange#setAutoDelete(Boolean autoDelete)
     */
    @Override
    public void setAutoDelete(final Boolean autoDelete) {
        this.autoDelete = autoDelete;
    }

    /**
     * @see Exchange#getVirtualHost()
     */
    @Override
    public String getVirtualHost() {
        return this.virtualHost;
    }

    /**
     * @see Exchange#setVirtualHost(String virtualHost)
     */
    @Override
    public void setVirtualHost(final String virtualHost) {
        this.virtualHost = virtualHost;
    }

    @Override
    public String toString() {
        return "ExchangeImpl{" + "name='" + name + '\'' + ", exchangeType=" + exchangeType + ", durable=" + durable
                + ", autoDelete="
                + autoDelete + ", virtualHost='" + virtualHost + '\'' + '}';
    }
}
