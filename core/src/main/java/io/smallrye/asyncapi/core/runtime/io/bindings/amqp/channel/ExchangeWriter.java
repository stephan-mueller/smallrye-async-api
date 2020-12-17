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
package io.smallrye.asyncapi.core.runtime.io.bindings.amqp.channel;

import com.fasterxml.jackson.databind.node.ObjectNode;

import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.spec.models.binding.amqp.Exchange;

public class ExchangeWriter {

    public ExchangeWriter() {
    }

    public static void writeExchange(ObjectNode parent, Exchange model) {
        if (model == null) {
            return;
        }
        ObjectNode node = JsonUtil.objectNode();
        parent.set(AMQPChannelBindingConstants.PROP_EXCHANGE, node);

        JsonUtil.stringProperty(node, AMQPChannelBindingConstants.PROP_NAME, model.getName());
        if (model.getExchangeType() != null && !model.getExchangeType().toString().equals("")) {
            JsonUtil.stringProperty(node, AMQPChannelBindingConstants.PROP_EXCHANGE_TYPE, model.getExchangeType().toString());
        }
        JsonUtil.booleanProperty(node, AMQPChannelBindingConstants.PROP_DURABLE, model.isDurable());
        JsonUtil.booleanProperty(node, AMQPChannelBindingConstants.PROP_AUTO_DELETE, model.isAutoDelete());
        JsonUtil.stringProperty(node, AMQPChannelBindingConstants.PROP_VIRTUAL_HOST, model.getVirtualHost());
    }
}
