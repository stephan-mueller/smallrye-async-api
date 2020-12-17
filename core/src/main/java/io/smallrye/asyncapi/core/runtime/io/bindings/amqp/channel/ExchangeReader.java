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

import java.util.LinkedHashMap;
import java.util.Map;

import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.AnnotationValue;

import com.fasterxml.jackson.databind.JsonNode;

import io.smallrye.asyncapi.core.api.models.binding.amqp.ExchangeImpl;
import io.smallrye.asyncapi.core.runtime.io.IoLogging;
import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.util.JandexUtil;
import io.smallrye.asyncapi.spec.annotations.binding.amqp.ExchangeType;
import io.smallrye.asyncapi.spec.models.binding.amqp.Exchange;

public class ExchangeReader {

    public ExchangeReader() {
    }

    public static Exchange readExchange(final AnnotationValue annotationValue) {
        if (annotationValue == null) {
            return null;
        }

        IoLogging.logger.singleAnnotation("@Exchange");
        AnnotationInstance nested = annotationValue.asNested();

        Exchange exchange = new ExchangeImpl();
        exchange.setName(JandexUtil.stringValue(nested, AMQPChannelBindingConstants.PROP_NAME));
        exchange.setExchangeType(
                JandexUtil.enumValue(nested, AMQPChannelBindingConstants.PROP_EXCHANGE_TYPE, ExchangeType.class));
        exchange.setDurable(JandexUtil.booleanValue(nested, AMQPChannelBindingConstants.PROP_DURABLE).orElse(false));
        exchange.setAutoDelete(JandexUtil.booleanValue(nested, AMQPChannelBindingConstants.PROP_AUTO_DELETE).orElse(false));
        exchange.setVirtualHost(JandexUtil.stringValue(nested, AMQPChannelBindingConstants.PROP_VIRTUAL_HOST));

        return exchange;
    }

    public static Exchange readExchange(final JsonNode node) {
        if (node == null) {
            return null;
        }

        IoLogging.logger.singleJsonNode("Exchange");

        Exchange exchange = new ExchangeImpl();
        exchange.setName(JsonUtil.stringProperty(node, AMQPChannelBindingConstants.PROP_NAME));
        exchange.setExchangeType(readExchangeType(node.get(AMQPChannelBindingConstants.PROP_EXCHANGE_TYPE)));
        exchange.setDurable(JsonUtil.booleanProperty(node, AMQPChannelBindingConstants.PROP_DURABLE).orElse(false));
        exchange.setAutoDelete(JsonUtil.booleanProperty(node, AMQPChannelBindingConstants.PROP_AUTO_DELETE).orElse(false));
        exchange.setVirtualHost(JsonUtil.stringProperty(node, AMQPChannelBindingConstants.PROP_VIRTUAL_HOST));

        return exchange;
    }

    private static ExchangeType readExchangeType(final JsonNode node) {
        if (node == null || !node.isTextual()) {
            return null;
        }
        return EXCHANGE_TYPE_LOOKUP.get(node.asText());
    }

    private static final Map<String, ExchangeType> EXCHANGE_TYPE_LOOKUP = new LinkedHashMap<>();

    static {
        ExchangeType[] exchangeTypes = ExchangeType.values();
        for (ExchangeType type : exchangeTypes) {
            EXCHANGE_TYPE_LOOKUP.put(type.toString(), type);
        }
    }

}
