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
import io.smallrye.asyncapi.spec.models.binding.MessageBinding;
import io.smallrye.asyncapi.spec.models.binding.MessageBindings;
import io.smallrye.asyncapi.spec.models.binding.amqp.AMQPMessageBinding;
import io.smallrye.asyncapi.spec.models.binding.http.HTTPMessageBinding;
import io.smallrye.asyncapi.spec.models.binding.kafka.KafkaMessageBinding;
import io.smallrye.asyncapi.spec.models.binding.mqtt.MQTTMessageBinding;

/**
 * An implementation of the {@link MessageBindings} AsyncAPI model interface.
 */
public class MessageBindingsImpl extends ExtensibleImpl<MessageBindings> implements MessageBindings, ModelImpl {

    private List<MessageBinding> bindings;

    private AMQPMessageBinding amqpBinding;

    private HTTPMessageBinding httpBinding;

    private KafkaMessageBinding kafkaBinding;

    private MQTTMessageBinding mqttBinding;

    /**
     * @see MessageBindings#getBindings()
     */
    @Override
    public List<MessageBinding> getBindings() {
        return ModelUtil.unmodifiableList(this.bindings);
    }

    /**
     * @see MessageBindings#setBindings(List<MessageBinding>)
     */
    @Override
    public void setBindings(final List<MessageBinding> bindings) {
        this.bindings = ModelUtil.replace(bindings, ArrayList::new);
    }

    /**
     * @see MessageBindings#addBinding(MessageBinding binding)
     */
    @Override
    public MessageBindings addBinding(final MessageBinding binding) {
        this.bindings = ModelUtil.add(binding, this.bindings, ArrayList::new);
        return this;
    }

    /**
     * @see MessageBindings#removeBinding(MessageBinding binding)
     */
    @Override
    public void removeBinding(final MessageBinding binding) {
        ModelUtil.remove(this.bindings, binding);
    }

    /**
     * @see MessageBindings#getAMQPBinding()
     */
    @Override
    public AMQPMessageBinding getAMQPBinding() {
        return this.amqpBinding;
    }

    /**
     * @see MessageBindings#setAMQPBinding(AMQPMessageBinding amqpBinding)
     */
    @Override
    public void setAMQPBinding(final AMQPMessageBinding amqpBinding) {
        this.amqpBinding = amqpBinding;
    }

    /**
     * @see MessageBindings#getHTTPBinding()
     */
    @Override
    public HTTPMessageBinding getHTTPBinding() {
        return this.httpBinding;
    }

    /**
     * @see MessageBindings#setHTTPBinding(HTTPMessageBinding httpBinding)
     */
    @Override
    public void setHTTPBinding(final HTTPMessageBinding httpBinding) {
        this.httpBinding = httpBinding;
    }

    /**
     * @see MessageBindings#getKafkaBinding()
     */
    @Override
    public KafkaMessageBinding getKafkaBinding() {
        return this.kafkaBinding;
    }

    /**
     * @see MessageBindings#setKafkaBinding(KafkaMessageBinding kafkaBinding)
     */
    @Override
    public void setKafkaBinding(final KafkaMessageBinding kafkaBinding) {
        this.kafkaBinding = kafkaBinding;
    }

    /**
     * @see MessageBindings#getMQTTBinding()
     */
    @Override
    public MQTTMessageBinding getMQTTBinding() {
        return this.mqttBinding;
    }

    /**
     * @see MessageBindings#setMQTTBinding(MQTTMessageBinding mqttBinding)
     */
    @Override
    public void setMQTTBinding(final MQTTMessageBinding mqttBinding) {
        this.mqttBinding = mqttBinding;
    }
}
