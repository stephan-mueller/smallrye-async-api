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
import io.smallrye.asyncapi.spec.models.binding.amqp.AMQPMessageBinding;
import io.smallrye.asyncapi.spec.models.binding.http.HTTPMessageBinding;
import io.smallrye.asyncapi.spec.models.binding.kafka.KafkaMessageBinding;
import io.smallrye.asyncapi.spec.models.binding.mqtt.MQTTMessageBinding;

public interface MessageBindings extends Constructible, Extensible<MessageBindings> {

    /**
     * Returns the bindings of the message
     *
     * @return the bindings of the message
     */
    List<MessageBinding> getBindings();

    /**
     * Sets the bindings of the message
     *
     * @param bindings the bindings of the message
     */
    void setBindings(List<MessageBinding> bindings);

    /**
     * Sets the bindings of the message
     *
     * @param bindings the bindings of the message
     * @return this MessageBindings instance
     */
    default MessageBindings bindings(List<MessageBinding> bindings) {
        setBindings(bindings);
        return this;
    }

    /**
     * Adds the given binding to this MessageBindings list of message bindings.
     *
     * @param binding to be added to the MessageBindings list
     * @return the current ServerBindings object
     */
    MessageBindings addBinding(MessageBinding binding);

    /**
     * Removes the given binding to this MessageBindings list of message bindings.
     *
     * @param binding to be added to the MessageBindings list
     */
    void removeBinding(MessageBinding binding);

    /**
     * Returns the amqp bindings of the message
     *
     * @return the amqp bindings of the message
     */
    AMQPMessageBinding getAMQPBinding();

    /**
     * Sets the amqp bindings of the message
     *
     * @param amqpBinding the amqp bindings of the message
     */
    void setAMQPBinding(AMQPMessageBinding amqpBinding);

    /**
     * Sets the amqp bindings of the message
     *
     * @param amqpBinding the amqp bindings of the message
     * @return this MessageBindings instance
     */
    default MessageBindings amqpBindings(AMQPMessageBinding amqpBinding) {
        setAMQPBinding(amqpBinding);
        return this;
    }

    /**
     * Returns the http bindings of the message
     *
     * @return the http bindings of the message
     */
    HTTPMessageBinding getHTTPBinding();

    /**
     * Sets the http bindings of the message
     *
     * @param httpBinding the http bindings of the message
     */
    void setHTTPBinding(HTTPMessageBinding httpBinding);

    /**
     * Sets the http bindings of the message
     *
     * @param httpBinding the http bindings of the message
     * @return this MessageBindings instance
     */
    default MessageBindings httpBindings(HTTPMessageBinding httpBinding) {
        setHTTPBinding(httpBinding);
        return this;
    }

    /**
     * Returns the kafka bindings of the message
     *
     * @return the kafka bindings of the message
     */
    KafkaMessageBinding getKafkaBinding();

    /**
     * Sets the kafka bindings of the message
     *
     * @param kafkaBinding the kafka bindings of the message
     */
    void setKafkaBinding(KafkaMessageBinding kafkaBinding);

    /**
     * Sets the kafka bindings of the message
     *
     * @param kafkaBinding the kafka bindings of the message
     * @return this MessageBindings instance
     */
    default MessageBindings kafkaBindings(KafkaMessageBinding kafkaBinding) {
        setKafkaBinding(kafkaBinding);
        return this;
    }

    /**
     * Returns the mqtt bindings of the message
     *
     * @return the mqtt bindings of the message
     */
    MQTTMessageBinding getMQTTBinding();

    /**
     * Sets the mqtt bindings of the message
     *
     * @param mqttBinding the mqtt bindings of the message
     */
    void setMQTTBinding(MQTTMessageBinding mqttBinding);

    /**
     * Sets the mqtt bindings of the message
     *
     * @param mqttBinding the mqtt bindings of the message
     * @return this MessageBindings instance
     */
    default MessageBindings kafkaBindings(MQTTMessageBinding mqttBinding) {
        setMQTTBinding(mqttBinding);
        return this;
    }
}
