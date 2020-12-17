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
import io.smallrye.asyncapi.spec.models.binding.amqp.AMQPOperationBinding;
import io.smallrye.asyncapi.spec.models.binding.http.HTTPOperationBinding;
import io.smallrye.asyncapi.spec.models.binding.kafka.KafkaOperationBinding;
import io.smallrye.asyncapi.spec.models.binding.mqtt.MQTTOperationBinding;

/**
 * Map describing protocol-specific definitions for an operation.
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0/#operationBindingsObject"
 */
public interface OperationBindings extends Constructible, Extensible<OperationBindings> {

    /**
     * Returns the bindings of the operation
     *
     * @return the bindings of the operation
     */
    List<OperationBinding> getBindings();

    /**
     * Sets the bindings of the operation
     *
     * @param bindings the bindings of the operation
     */
    void setBindings(List<OperationBinding> bindings);

    /**
     * Sets the bindings of the operation
     *
     * @param bindings the bindings of the operation
     * @return this OperationBindings instance
     */
    default OperationBindings bindings(List<OperationBinding> bindings) {
        setBindings(bindings);
        return this;
    }

    /**
     * Adds the given binding to this OperationBindings list of operation bindings.
     *
     * @param binding to be added to the OperationBindings list
     * @return the current ServerBindings object
     */
    OperationBindings addBinding(OperationBinding binding);

    /**
     * Removes the given binding to this OperationBindings list of operation bindings.
     *
     * @param binding to be added to the OperationBindings list
     */
    void removeBinding(OperationBinding binding);

    /**
     * Returns the amqp bindings of the operation
     *
     * @return the amqp bindings of the operation
     */
    AMQPOperationBinding getAMQPBinding();

    /**
     * Sets the amqp bindings of the operation
     *
     * @param amqpBinding the amqp bindings of the operation
     */
    void setAMQPBinding(AMQPOperationBinding amqpBinding);

    /**
     * Sets the amqp bindings of the operation
     *
     * @param amqpBinding the amqp bindings of the operation
     * @return this MessageBindings instance
     */
    default OperationBindings httpBindings(AMQPOperationBinding amqpBinding) {
        setAMQPBinding(amqpBinding);
        return this;
    }

    /**
     * Returns the http bindings of the operation
     *
     * @return the http bindings of the operation
     */
    HTTPOperationBinding getHTTPBinding();

    /**
     * Sets the http bindings of the operation
     *
     * @param httpBinding the http bindings of the operation
     */
    void setHTTPBinding(HTTPOperationBinding httpBinding);

    /**
     * Sets the http bindings of the operation
     *
     * @param httpBinding the http bindings of the operation
     * @return this OperationBindings instance
     */
    default OperationBindings httpBindings(HTTPOperationBinding httpBinding) {
        setHTTPBinding(httpBinding);
        return this;
    }

    /**
     * Returns the kafka bindings of the operation
     *
     * @return the kafka bindings of the operation
     */
    KafkaOperationBinding getKafkaBinding();

    /**
     * Sets the kafka bindings of the operation
     *
     * @param kafkaBinding the kafka bindings of the operation
     */
    void setKafkaBinding(KafkaOperationBinding kafkaBinding);

    /**
     * Sets the kafka bindings of the operation
     *
     * @param kafkaBinding the kafka bindings of the operation
     * @return this OperationBindings instance
     */
    default OperationBindings kafkaBindings(KafkaOperationBinding kafkaBinding) {
        setKafkaBinding(kafkaBinding);
        return this;
    }

    /**
     * Returns the mqtt bindings of the operation
     *
     * @return the mqtt bindings of the operation
     */
    MQTTOperationBinding getMQTTBinding();

    /**
     * Sets the mqtt bindings of the operation
     *
     * @param mqttBinding the kafka mqtt of the operation
     */
    void setMQTTBinding(MQTTOperationBinding mqttBinding);

    /**
     * Sets the mqtt bindings of the operation
     *
     * @param mqttBinding the mqtt bindings of the operation
     * @return this OperationBindings instance
     */
    default OperationBindings mqttBindings(MQTTOperationBinding mqttBinding) {
        setMQTTBinding(mqttBinding);
        return this;
    }
}
