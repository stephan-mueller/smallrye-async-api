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
import io.smallrye.asyncapi.spec.models.binding.mqtt.MQTTServerBinding;

/**
 * This object represents an array of ServerBinding annotations that can be specified at the definition level.
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0/#a-name-serverbindingsobject-a-server-bindings-object"
 */
public interface ServerBindings extends Constructible, Extensible<ServerBindings> {

    /**
     * Returns the default values of the ServerBindings
     *
     * @return the default values
     */
    List<ServerBinding> getBindings();

    /**
     * Sets the default values of the ServerBindings
     *
     * @param bindings default values
     */
    void setBindings(List<ServerBinding> bindings);

    /**
     * Sets this ServerBindings instance's default values
     *
     * @param bindings default values
     * @return this ServerBindings instance
     */
    default ServerBindings serverBindings(List<ServerBinding> bindings) {
        setBindings(bindings);
        return this;
    }

    /**
     * Adds the given binding to this ServerBindings list of server bindings.
     *
     * @param binding to be added to the ServerBindings list
     * @return the current ServerBindings object
     */
    ServerBindings addBinding(ServerBinding binding);

    /**
     * Removes the given binding to this ServerBindings list of server bindings.
     *
     * @param binding to be added to the ServerBindings list
     */
    void removeBinding(ServerBinding binding);

    /**
     * Returns the mqtt bindings of the server
     *
     * @return the mqtt bindings of the server
     */
    MQTTServerBinding getMQTTBinding();

    /**
     * Sets the mqtt bindings of the server
     *
     * @param mqttBinding the kafka mqtt of the server
     */
    void setMQTTBinding(MQTTServerBinding mqttBinding);

    /**
     * Sets the mqtt bindings of the server
     *
     * @param mqttBinding the mqtt bindings of the server
     * @return this ServerBindings instance
     */
    default ServerBindings mqttBindings(MQTTServerBinding mqttBinding) {
        setMQTTBinding(mqttBinding);
        return this;
    }
}
