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
package io.smallrye.asyncapi.core.api.models.binding.amqp;

import java.util.ArrayList;
import java.util.List;

import io.smallrye.asyncapi.core.api.models.ExtensibleImpl;
import io.smallrye.asyncapi.core.api.models.ModelImpl;
import io.smallrye.asyncapi.core.runtime.util.ModelUtil;
import io.smallrye.asyncapi.spec.models.binding.OperationBinding;
import io.smallrye.asyncapi.spec.models.binding.amqp.AMQPOperationBinding;

/**
 * An implementation of the {@link AMQPOperationBinding} AsyncAPI model interface.
 */
public class AMQPOperationBindingImpl extends ExtensibleImpl<OperationBinding> implements AMQPOperationBinding, ModelImpl {

    private Integer expiration;

    private String userID;

    private List<String> cc;

    private Integer priority;

    private Integer deliveryMode;

    private Boolean mandatory;

    private List<String> bcc;

    private String replyTo;

    private Boolean timeStamp;

    private Boolean ack;

    private String bindingVersion;

    /**
     * @see AMQPOperationBinding#getExpiration()
     */
    @Override
    public Integer getExpiration() {
        return this.expiration;
    }

    /**
     * @see AMQPOperationBinding#setExpiration(Integer expiration)
     */
    @Override
    public void setExpiration(final Integer expiration) {
        this.expiration = expiration;
    }

    /**
     * @see AMQPOperationBinding#getUserId()
     */
    @Override
    public String getUserId() {
        return this.userID;
    }

    /**
     * @see AMQPOperationBinding#setUserId(String userID)
     */
    @Override
    public void setUserId(final String userID) {
        this.userID = userID;
    }

    /**
     * @see AMQPOperationBinding#getCc()
     */
    @Override
    public List<String> getCc() {
        return ModelUtil.unmodifiableList(this.cc);
    }

    /**
     * @see AMQPOperationBinding#setCc(List<String>)
     */
    @Override
    public void setCc(final List<String> cc) {
        this.cc = ModelUtil.replace(cc, ArrayList::new);
    }

    /**
     * @see AMQPOperationBinding#addCc(String cc)
     */
    @Override
    public AMQPOperationBinding addCc(final String cc) {
        this.cc = ModelUtil.add(cc, this.cc, ArrayList::new);
        return this;
    }

    /**
     * @see AMQPOperationBinding#removeCc(String cc)
     */
    @Override
    public void removeCc(final String cc) {
        ModelUtil.remove(this.cc, cc);
    }

    /**
     * @see AMQPOperationBinding#getPriority()
     */
    @Override
    public Integer getPriority() {
        return this.priority;
    }

    /**
     * @see AMQPOperationBinding#setPriority(Integer priority)
     */
    @Override
    public void setPriority(final Integer priority) {
        this.priority = priority;
    }

    /**
     * @see AMQPOperationBinding#getDeliveryMode()
     */
    @Override
    public Integer getDeliveryMode() {
        return this.deliveryMode;
    }

    /**
     * @see AMQPOperationBinding#setDeliveryMode(Integer deliveryMode)
     */
    @Override
    public void setDeliveryMode(final Integer deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

    /**
     * @see AMQPOperationBinding#isMandatory()
     */
    @Override
    public Boolean isMandatory() {
        return this.mandatory;
    }

    /**
     * @see AMQPOperationBinding#setMandatory(Boolean mandatory)
     */
    @Override
    public void setMandatory(final Boolean mandatory) {
        this.mandatory = mandatory;
    }

    /**
     * @see AMQPOperationBinding#getBcc()
     */
    @Override
    public List<String> getBcc() {
        return ModelUtil.unmodifiableList(this.bcc);
    }

    /**
     * @see AMQPOperationBinding#setBcc(List<String>)
     */
    @Override
    public void setBcc(final List<String> bcc) {
        this.bcc = ModelUtil.replace(bcc, ArrayList::new);
    }

    /**
     * @see AMQPOperationBinding#addBcc(String bcc)
     */
    @Override
    public AMQPOperationBinding addBcc(final String bcc) {
        this.bcc = ModelUtil.add(bcc, this.bcc, ArrayList::new);
        return this;
    }

    /**
     * @see AMQPOperationBinding#removeBcc(String bcc)
     */
    @Override
    public void removeBcc(final String bcc) {
        ModelUtil.remove(this.bcc, bcc);
    }

    /**
     * @see AMQPOperationBinding#getReplyTo()
     */
    @Override
    public String getReplyTo() {
        return this.replyTo;
    }

    /**
     * @see AMQPOperationBinding#setReplyTo(String replyTo)
     */
    @Override
    public void setReplyTo(final String replyTo) {
        this.replyTo = replyTo;
    }

    /**
     * @see AMQPOperationBinding#getTimeStamp()
     */
    @Override
    public Boolean getTimeStamp() {
        return this.timeStamp;
    }

    /**
     * @see AMQPOperationBinding#setTimeStamp(Boolean timeStamp)
     */
    @Override
    public void setTimeStamp(final Boolean timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * @see AMQPOperationBinding#getAck()
     */
    @Override
    public Boolean getAck() {
        return this.ack;
    }

    /**
     * @see AMQPOperationBinding#setAck(Boolean ack)
     */
    @Override
    public void setAck(final Boolean ack) {
        this.ack = ack;
    }

    /**
     * @see AMQPOperationBinding#getBindingVersion()
     */
    @Override
    public String getBindingVersion() {
        return this.bindingVersion;
    }

    /**
     * @see AMQPOperationBinding#setBindingVersion(String bindingVersion)
     */
    @Override
    public void setBindingVersion(final String bindingVersion) {
        this.bindingVersion = bindingVersion;
    }
}
