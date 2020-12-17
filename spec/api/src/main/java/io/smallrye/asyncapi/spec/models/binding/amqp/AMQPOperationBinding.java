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

import java.util.List;

import io.smallrye.asyncapi.spec.models.Constructible;
import io.smallrye.asyncapi.spec.models.binding.OperationBinding;

/**
 * Protocol-specific information for an AMQP 0-9-1 operation.
 *
 * @see "https://github.com/asyncapi/bindings/blob/master/amqp1/README.md#operation-binding-object"
 */
public interface AMQPOperationBinding extends OperationBinding, Constructible {

    /**
     * Returns TTL (Time-To-Live) for the operation.
     *
     * @return expiration of the operation
     */
    Integer getExpiration();

    /**
     * Sets TTL (Time-To-Live) for the operation
     *
     * @param expiration expiration of the operation
     */
    void setExpiration(Integer expiration);

    /**
     * Sets TTL (Time-To-Live) for the operation
     *
     * @param expiration expiration of the operation
     * @return this AMQPOperationBinding instance
     */
    default AMQPOperationBinding expiration(Integer expiration) {
        setExpiration(expiration);
        return this;
    }

    /**
     * Returns the user who has sent the message.
     *
     * @return id of the user
     */
    String getUserId();

    /**
     * Sets the user who has sent the message.
     *
     * @param userId id of the user
     */
    void setUserId(String userId);

    /**
     * Sets the user who has sent the message.
     *
     * @param userId id of the user
     * @return this AMQPOperationBinding instance
     */
    default AMQPOperationBinding userId(String userId) {
        setUserId(userId);
        return this;
    }

    /**
     * Returns the routing keys the message should be routed to at the time of publishing.
     *
     * @return list of routing keys
     */
    List<String> getCc();

    /**
     * Sets the routing keys the message should be routed to at the time of publishing.
     *
     * @param cc list of routing keys
     */
    void setCc(List<String> cc);

    /**
     * Sets the routing keys the message should be routed to at the time of publishing.
     *
     * @param cc list of routing keys
     * @return this AMQPOperationBinding instance
     */
    default AMQPOperationBinding cc(List<String> cc) {
        setCc(cc);
        return this;
    }

    /**
     * Adds the given cc to this AMQPOperationBinding list of cc's.
     *
     * @param cc to be added to the cc list
     * @return the current AMQPOperationBinding object
     */
    AMQPOperationBinding addCc(String cc);

    /**
     * Removes the given cc to this AMQPOperationBinding list of cc's.
     *
     * @param cc to be removed from the AMQPOperationBinding list
     */
    void removeCc(String cc);

    /**
     * Returns the priority for the message.
     *
     * @return priority for the message.
     */
    Integer getPriority();

    /**
     * Sets the priority for the message.
     *
     * @param priority priority for the message.
     */
    void setPriority(Integer priority);

    /**
     * Sets the priority for the message.
     *
     * @param priority priority for the message.
     * @return this AMQPOperationBinding instance
     */
    default AMQPOperationBinding priority(Integer priority) {
        setPriority(priority);
        return this;
    }

    /**
     * Returns the delivery mode for the message.
     *
     * @return delivery mode for the message.
     */
    Integer getDeliveryMode();

    /**
     * Sets the delivery mode for the message.
     *
     * @param deliveryMode delivery mode for the message.
     */
    void setDeliveryMode(Integer deliveryMode);

    /**
     * Sets the delivery mode for the message.
     *
     * @param deliveryMode delivery mode for the message.
     * @return this AMQPOperationBinding instance
     */
    default AMQPOperationBinding deliveryMode(Integer deliveryMode) {
        setPriority(deliveryMode);
        return this;
    }

    /**
     * @return whether the message is mandatory or not
     */
    Boolean isMandatory();

    /**
     * Sets whether the message is mandatory or not.
     *
     * @param mandatory whether the message is mandatory or not
     */
    void setMandatory(Boolean mandatory);

    /**
     * Sets whether the message is mandatory or not.
     *
     * @param mandatory whether the message is mandatory or not
     * @return this AMQPOperationBinding instance
     */
    default AMQPOperationBinding mandatory(Boolean mandatory) {
        setMandatory(mandatory);
        return this;
    }

    /**
     * Returns the routing keys the message should be routed to at the time of publishing, but consumers will not receive this
     * information.
     *
     * @return list of routing keys
     */
    List<String> getBcc();

    /**
     * Sets the routing keys the message should be routed to at the time of publishing, but consumers will not receive this
     * information.
     *
     * @param bcc list of routing keys
     */
    void setBcc(List<String> bcc);

    /**
     * Sets the routing keys the message should be routed to at the time of publishing., but consumers will not receive this
     * information.
     *
     * @param bcc list of routing keys
     * @return this AMQPOperationBinding instance
     */
    default AMQPOperationBinding bcc(List<String> bcc) {
        setBcc(bcc);
        return this;
    }

    /**
     * Adds the given bcc to this AMQPOperationBinding list of bcc's.
     *
     * @param bcc to be added to the bcc list
     * @return the current AMQPOperationBinding object
     */
    AMQPOperationBinding addBcc(String bcc);

    /**
     * Removes the given bcc to this AMQPOperationBinding list of bcc's.
     *
     * @param bcc to be removed from the AMQPOperationBinding list
     */
    void removeBcc(String bcc);

    /**
     * Returns the name of the queue where the consumer should send the response.
     *
     * @return name of the queue
     */
    String getReplyTo();

    /**
     * Sets the name of the queue where the consumer should send the response.
     *
     * @param replyTo name of the queue
     */
    void setReplyTo(String replyTo);

    /**
     * Sets the name of the queue where the consumer should send the response.
     *
     * @param replyTo the name of the queue
     * @return this AMQPOperationBinding instance
     */
    default AMQPOperationBinding replyTo(String replyTo) {
        setUserId(replyTo);
        return this;
    }

    /**
     * @return whether the message should include a timestamp or not.
     */
    Boolean getTimeStamp();

    /**
     * Sets whether the message should include a timestamp or not.
     *
     * @param timeStamp whether the message should include a timestamp or not.
     */
    void setTimeStamp(Boolean timeStamp);

    /**
     * Sets whether the message should include a timestamp or not.
     *
     * @param timeStamp whether the message should include a timestamp or not.
     * @return this AMQPOperationBinding instance
     */
    default AMQPOperationBinding timeStamp(Boolean timeStamp) {
        setTimeStamp(timeStamp);
        return this;
    }

    /**
     * @return whether the consumer should ack the message or not.
     */
    Boolean getAck();

    /**
     * Sets whether the consumer should ack the message or not.
     *
     * @param ack whether the consumer should ack the message or not.
     */
    void setAck(Boolean ack);

    /**
     * Sets whether the consumer should ack the message or not.
     *
     * @param ack whether the consumer should ack the message or not.
     * @return this AMQPOperationBinding instance
     */
    default AMQPOperationBinding ack(Boolean ack) {
        setAck(ack);
        return this;
    }

    /**
     * Returns the version of this binding
     *
     * @return the version of this binding
     */
    String getBindingVersion();

    /**
     * Sets the version of this binding
     *
     * @param bindingVersion the version of this binding
     */
    void setBindingVersion(String bindingVersion);

    /**
     * Sets the version of this binding
     *
     * @param bindingVersion the version of this binding
     * @return this AMQPOperationBinding instance
     */
    default AMQPOperationBinding bindingVersion(String bindingVersion) {
        setBindingVersion(bindingVersion);
        return this;
    }
}
