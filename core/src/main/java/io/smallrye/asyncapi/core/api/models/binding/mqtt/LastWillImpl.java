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
package io.smallrye.asyncapi.core.api.models.binding.mqtt;

import io.smallrye.asyncapi.core.api.models.ExtensibleImpl;
import io.smallrye.asyncapi.core.api.models.ModelImpl;
import io.smallrye.asyncapi.spec.models.binding.mqtt.LastWill;

/**
 * An implementation of the {@link LastWill} AsyncAPI model interface.
 */
public class LastWillImpl extends ExtensibleImpl<LastWill> implements LastWill, ModelImpl {

    private String topic;

    private Integer qos;

    private String message;

    private Boolean retain;

    /**
     * @see LastWill#getTopic()
     */
    @Override
    public String getTopic() {
        return topic;
    }

    /**
     * @see LastWill#setTopic(String topic)
     */
    @Override
    public void setTopic(final String topic) {
        this.topic = topic;
    }

    /**
     * @see LastWill#getQos()
     */
    @Override
    public Integer getQos() {
        return qos;
    }

    /**
     * @see LastWill#setQos(Integer qos)
     */
    @Override
    public void setQos(final Integer qos) {
        this.qos = qos;
    }

    /**
     * @see LastWill#getMessage()
     */
    @Override
    public String getMessage() {
        return this.message;
    }

    /**
     * @see LastWill#setMessage(String message)
     */
    @Override
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * @see LastWill#isRetain()
     */
    @Override
    public Boolean isRetain() {
        return retain;
    }

    /**
     * @see LastWill#setRetain(Boolean retain)
     */
    @Override
    public void setRetain(final Boolean retain) {
        this.retain = retain;
    }
}
