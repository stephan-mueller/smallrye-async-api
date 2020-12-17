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

import io.smallrye.asyncapi.spec.annotations.binding.amqp.ExchangeType;
import io.smallrye.asyncapi.spec.models.Constructible;
import io.smallrye.asyncapi.spec.models.Extensible;

/**
 * the object defines the exchange properties.
 *
 * @see "https://github.com/asyncapi/bindings/blob/master/amqp/README.md#channel"
 */
public interface Exchange extends Constructible, Extensible<Exchange> {

    /**
     * Returns the name of the exchange
     *
     * @return the name of the exchange
     */
    String getName();

    /**
     * Sets the name of the exchange
     *
     * @param name the name of the exchange
     */
    void setName(String name);

    /**
     * Sets the name of the exchange
     *
     * @param name the name of the exchange
     * @return this Exchange instance
     */
    default Exchange name(String name) {
        setName(name);
        return this;
    }

    /**
     * Returns the type of the exchange
     *
     * @return the type of the exchange
     */
    ExchangeType getExchangeType();

    /**
     * Sets the type of the exchange
     *
     * @param exchangeType the type of the exchange
     */
    void setExchangeType(ExchangeType exchangeType);

    /**
     * Sets the type of the exchange
     *
     * @param exchangeType the type of the exchange
     * @return this Exchange instance
     */
    default Exchange exchangeType(ExchangeType exchangeType) {
        setExchangeType(exchangeType);
        return this;
    }

    /**
     * @return whether the exchange is durable
     */
    Boolean isDurable();

    /**
     * Sets durability of the exchange
     *
     * @param durable the durability of the exchange
     */
    void setDurable(Boolean durable);

    /**
     * Sets durability of the exchange
     *
     * @param durable the durability of the exchange
     * @return this Exchange instance
     */
    default Exchange durable(Boolean durable) {
        setDurable(durable);
        return this;
    }

    /**
     * @return whether the exchange should be deleted when the last queue is unbound from it.
     */
    Boolean isAutoDelete();

    /**
     * Sets the autoDelete property of the exchange
     *
     * @param autoDelete the autoDelete property of the exchange
     */
    void setAutoDelete(Boolean autoDelete);

    /**
     * Sets the autoDelete property of the exchange
     *
     * @param autoDelete the autoDelete property of the exchange
     * @return this Exchange instance
     */
    default Exchange autoDelete(Boolean autoDelete) {
        setAutoDelete(autoDelete);
        return this;
    }

    /**
     * Returns the virtual host of the exchange
     *
     * @return the virtual host of the exchange
     */
    String getVirtualHost();

    /**
     * Sets the virtual host of the exchange
     *
     * @param virtualHost the virtual host of the exchange
     */
    void setVirtualHost(String virtualHost);

    /**
     * Sets the virtual host of the exchange
     *
     * @param virtualHost the virtual host of the exchange
     * @return this Exchange instance
     */
    default Exchange virtualHost(String virtualHost) {
        setVirtualHost(virtualHost);
        return this;
    }
}
