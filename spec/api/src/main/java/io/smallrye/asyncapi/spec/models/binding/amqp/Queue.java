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
import io.smallrye.asyncapi.spec.models.Extensible;

/**
 * the object defines the queue properties
 *
 * @see "https://github.com/asyncapi/bindings/blob/master/amqp/README.md#channel"
 */
public interface Queue extends Constructible, Extensible<Queue> {

    /**
     * Returns the name of the queue
     *
     * @return the name of the queue
     */
    String getName();

    /**
     * Sets the name of the queue
     *
     * @param name the name of the queue
     */
    void setName(String name);

    /**
     * Sets the name of the queue
     *
     * @param name the name of the queue
     * @return this Exchange instance
     */
    default Queue name(String name) {
        setName(name);
        return this;
    }

    /**
     * @return whether the queue is durable
     */
    Boolean getDurable();

    /**
     * Sets durability of the queue
     *
     * @param durable the durability of the queue
     */
    void setDurable(Boolean durable);

    /**
     * Sets durability of the queue
     *
     * @param durable the durability of the queue
     * @return this Exchange instance
     */
    default Queue durable(Boolean durable) {
        setDurable(durable);
        return this;
    }

    /**
     * @return whether the queue should be used only by one connection or not.
     */
    Boolean isExclusive();

    /**
     * Sets the exclusive property of the queue
     *
     * @param exclusive the exclusive property of the queue
     */
    void setExclusive(Boolean exclusive);

    /**
     * Sets the exclusive property of the queue
     *
     * @param exclusive the exclusive property of the queue
     * @return this Queue instance
     */
    default Queue exclusive(Boolean exclusive) {
        setExclusive(exclusive);
        return this;
    }

    /**
     * @return whether the queue should be deleted when the last consumer unsubscribes.
     */
    Boolean isAutoDelete();

    /**
     * Sets the autoDelete property of the queue
     *
     * @param autoDelete the autoDelete property of the queue
     */
    void setAutoDelete(Boolean autoDelete);

    /**
     * Sets the autoDelete property of the queue
     *
     * @param autoDelete the autoDelete property of the queue
     * @return this Queue instance
     */
    default Queue autoDelete(Boolean autoDelete) {
        setAutoDelete(autoDelete);
        return this;
    }

    /**
     * Returns the virtual host of the queue
     *
     * @return the virtual host of the queue
     */
    String getVirtualHost();

    /**
     * Sets the virtual host of the queue
     *
     * @param virtualHost the virtual host of the queue
     */
    void setVirtualHost(String virtualHost);

    /**
     * Sets the virtual host of the queue
     *
     * @param virtualHost the virtual host of the queue
     * @return this Queue instance
     */
    default Queue virtualHost(String virtualHost) {
        setVirtualHost(virtualHost);
        return this;
    }
}
