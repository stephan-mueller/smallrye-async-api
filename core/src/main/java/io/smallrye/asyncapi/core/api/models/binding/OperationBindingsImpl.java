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
package io.smallrye.asyncapi.core.api.models.binding;

import java.util.ArrayList;
import java.util.List;

import io.smallrye.asyncapi.core.api.models.ExtensibleImpl;
import io.smallrye.asyncapi.core.api.models.ModelImpl;
import io.smallrye.asyncapi.core.runtime.util.ModelUtil;
import io.smallrye.asyncapi.spec.models.binding.OperationBinding;
import io.smallrye.asyncapi.spec.models.binding.OperationBindings;
import io.smallrye.asyncapi.spec.models.binding.amqp.AMQPOperationBinding;
import io.smallrye.asyncapi.spec.models.binding.http.HTTPOperationBinding;
import io.smallrye.asyncapi.spec.models.binding.kafka.KafkaOperationBinding;
import io.smallrye.asyncapi.spec.models.binding.mqtt.MQTTOperationBinding;

/**
 * An implementation of the {@link OperationBindings} AsyncAPI model interface.
 */
public class OperationBindingsImpl extends ExtensibleImpl<OperationBindings> implements OperationBindings, ModelImpl {

    private List<OperationBinding> bindings;

    private AMQPOperationBinding amqpBinding;

    private HTTPOperationBinding httpBinding;

    private KafkaOperationBinding kafkaBinding;

    private MQTTOperationBinding mqttBinding;

    /**
     * @see OperationBindings#getBindings()
     */
    @Override
    public List<OperationBinding> getBindings() {
        return ModelUtil.unmodifiableList(this.bindings);
    }

    /**
     * @see OperationBindings#setBindings(List<OperationBinding>)
     */
    @Override
    public void setBindings(final List<OperationBinding> bindings) {
        this.bindings = ModelUtil.replace(bindings, ArrayList::new);
    }

    /**
     * @see OperationBindings#addBinding(OperationBinding binding)
     */
    @Override
    public OperationBindings addBinding(final OperationBinding binding) {
        this.bindings = ModelUtil.add(binding, this.bindings, ArrayList::new);
        return this;
    }

    /**
     * @see OperationBindings#removeBinding(OperationBinding binding)
     */
    @Override
    public void removeBinding(final OperationBinding binding) {
        ModelUtil.remove(this.bindings, binding);
    }

    /**
     * @see OperationBindings#getAMQPBinding()
     */
    @Override
    public AMQPOperationBinding getAMQPBinding() {
        return this.amqpBinding;
    }

    /**
     * @see OperationBindings#setAMQPBinding(AMQPOperationBinding amqpBinding)
     */
    @Override
    public void setAMQPBinding(final AMQPOperationBinding amqpBinding) {
        this.amqpBinding = amqpBinding;
    }

    /**
     * @see OperationBindings#getHTTPBinding()
     */
    @Override
    public HTTPOperationBinding getHTTPBinding() {
        return this.httpBinding;
    }

    /**
     * @see OperationBindings#setHTTPBinding(HTTPOperationBinding httpBinding)
     */
    @Override
    public void setHTTPBinding(final HTTPOperationBinding httpBinding) {
        this.httpBinding = httpBinding;
    }

    /**
     * @see OperationBindings#getKafkaBinding()
     */
    @Override
    public KafkaOperationBinding getKafkaBinding() {
        return this.kafkaBinding;
    }

    /**
     * @see OperationBindings#setKafkaBinding(KafkaOperationBinding kafkaBinding)
     */
    @Override
    public void setKafkaBinding(final KafkaOperationBinding kafkaBinding) {
        this.kafkaBinding = kafkaBinding;
    }

    /**
     * @see OperationBindings#getMQTTBinding()
     */
    @Override
    public MQTTOperationBinding getMQTTBinding() {
        return this.mqttBinding;
    }

    /**
     * @see OperationBindings#setMQTTBinding(MQTTOperationBinding mqttBinding)
     */
    @Override
    public void setMQTTBinding(final MQTTOperationBinding mqttBinding) {
        this.mqttBinding = mqttBinding;
    }
}
