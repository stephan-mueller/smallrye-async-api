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

import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.AnnotationValue;

import com.fasterxml.jackson.databind.JsonNode;

import io.smallrye.asyncapi.core.api.models.binding.amqp.QueueImpl;
import io.smallrye.asyncapi.core.runtime.io.IoLogging;
import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.util.JandexUtil;
import io.smallrye.asyncapi.spec.models.binding.amqp.Queue;

public class QueueReader {

    public QueueReader() {
    }

    public static Queue readQueue(final AnnotationValue annotationValue) {
        if (annotationValue == null) {
            return null;
        }

        IoLogging.logger.singleAnnotation("@Queue");
        AnnotationInstance nested = annotationValue.asNested();

        Queue queue = new QueueImpl();
        queue.setName(JandexUtil.stringValue(nested, AMQPChannelBindingConstants.PROP_NAME));
        queue.setDurable(JandexUtil.booleanValue(nested, AMQPChannelBindingConstants.PROP_DURABLE).orElse(false));
        queue.setExclusive(JandexUtil.booleanValue(nested, AMQPChannelBindingConstants.PROP_EXCLUSIVE).orElse(false));
        queue.setAutoDelete(JandexUtil.booleanValue(nested, AMQPChannelBindingConstants.PROP_AUTO_DELETE).orElse(false));
        queue.setVirtualHost(JandexUtil.stringValue(nested, AMQPChannelBindingConstants.PROP_VIRTUAL_HOST));

        return queue;
    }

    public static Queue readQueue(final JsonNode node) {
        if (node == null) {
            return null;
        }

        IoLogging.logger.singleJsonNode("Exchange");

        Queue queue = new QueueImpl();
        queue.setName(JsonUtil.stringProperty(node, AMQPChannelBindingConstants.PROP_NAME));
        queue.setDurable(JsonUtil.booleanProperty(node, AMQPChannelBindingConstants.PROP_DURABLE).orElse(false));
        queue.setExclusive(JsonUtil.booleanProperty(node, AMQPChannelBindingConstants.PROP_EXCLUSIVE).orElse(false));
        queue.setAutoDelete(JsonUtil.booleanProperty(node, AMQPChannelBindingConstants.PROP_AUTO_DELETE).orElse(false));
        queue.setVirtualHost(JsonUtil.stringProperty(node, AMQPChannelBindingConstants.PROP_VIRTUAL_HOST));

        return queue;
    }
}
