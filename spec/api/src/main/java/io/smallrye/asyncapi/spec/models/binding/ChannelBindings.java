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
package io.smallrye.asyncapi.spec.models.binding;

import java.util.List;

import io.smallrye.asyncapi.spec.models.Constructible;
import io.smallrye.asyncapi.spec.models.Extensible;
import io.smallrye.asyncapi.spec.models.binding.amqp.AMQPChannelBinding;
import io.smallrye.asyncapi.spec.models.binding.ws.WebSocketChannelBinding;

public interface ChannelBindings extends Constructible, Extensible<ChannelBindings> {

    /**
     * Returns the bindings of the channel
     *
     * @return the bindings of the channel
     */
    List<ChannelBinding> getBindings();

    /**
     * Sets the bindings of the channel
     *
     * @param bindings the bindings of the channel
     */
    void setBindings(List<ChannelBinding> bindings);

    /**
     * Sets the bindings of the channel
     *
     * @param bindings the bindings of the channel
     * @return this ChannelBindings instance
     */
    default ChannelBindings bindings(List<ChannelBinding> bindings) {
        setBindings(bindings);
        return this;
    }

    /**
     * Adds the given binding to this ChannelBindings list of channel bindings.
     *
     * @param binding to be added to the ChannelBindings list
     * @return the current ServerBindings object
     */
    ChannelBindings addBinding(ChannelBinding binding);

    /**
     * Removes the given binding to this ChannelBindings list of channel bindings.
     *
     * @param binding to be added to the ChannelBindings list
     */
    void removeBinding(ChannelBinding binding);

    /**
     * Returns the amqp bindings of the channel
     *
     * @return the amqp bindings of the channel
     */
    AMQPChannelBinding getAMQPBinding();

    /**
     * Sets the amqp bindings of the channel
     *
     * @param amqpBinding the amqp bindings of the channel
     */
    void setAMQPBinding(AMQPChannelBinding amqpBinding);

    /**
     * Sets the amqp bindings of the channel
     *
     * @param amqpBinding the amqp bindings of the channel
     * @return this ChannelBindings instance
     */
    default ChannelBindings amqpBindings(AMQPChannelBinding amqpBinding) {
        setAMQPBinding(amqpBinding);
        return this;
    }

    /**
     * Returns the ws bindings of the channel
     *
     * @return the ws bindings of the channel
     */
    WebSocketChannelBinding getWebSocketBinding();

    /**
     * Sets the ws bindings of the channel
     *
     * @param wsBinding the ws bindings of the channel
     */
    void setWebSocketBinding(WebSocketChannelBinding wsBinding);

    /**
     * Sets the ws bindings of the channel
     *
     * @param wsBinding the ws bindings of the channel
     * @return this ChannelBindings instance
     */
    default ChannelBindings wsBindings(WebSocketChannelBinding wsBinding) {
        setWebSocketBinding(wsBinding);
        return this;
    }
}
