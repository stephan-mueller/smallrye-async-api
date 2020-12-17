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
package io.smallrye.asyncapi.spec.models.binding.ws;

import io.smallrye.asyncapi.spec.models.Constructible;
import io.smallrye.asyncapi.spec.models.binding.ChannelBinding;
import io.smallrye.asyncapi.spec.models.schema.Schema;

/**
 * Protocol-specific information for a WebSockets channel.
 *
 * @see "https://github.com/asyncapi/bindings/blob/master/websockets/README.md#channel"
 */
public interface WebSocketChannelBinding extends ChannelBinding, Constructible {

    /**
     * Returns the HTTP method to use when establishing the connection
     *
     * @return HTTP method of the connection
     */
    String getMethod();

    /**
     * Sets the HTTP method to use when establishing the connection
     *
     * @param method HTTP method of the connection
     */
    void setMethod(String method);

    /**
     * Sets the HTTP method to use when establishing the connection
     *
     * @param method query object of the connection
     * @return this WebSocketChannelBinding instance
     */
    default WebSocketChannelBinding method(String method) {
        setMethod(method);
        return this;
    }

    /**
     * Returns the schema object containing the definitions for each query parameter.
     *
     * @return query object of the connection
     */
    Schema getQuery();

    /**
     * Sets the schema object containing the definitions for each query parameter.
     *
     * @param query query object of the connection
     */
    void setQuery(Schema query);

    /**
     * Sets the schema object containing the definitions for each query parameter.
     *
     * @param query query object of the connection
     * @return this WebSocketChannelBinding instance
     */
    default WebSocketChannelBinding query(Schema query) {
        setQuery(query);
        return this;
    }

    /**
     * Returns the schema object containing the definitions of the HTTP headers to use when establishing the connection
     *
     * @return header object of the connection
     */
    Schema getHeaders();

    /**
     * Sets schema object containing the definitions of the HTTP headers to use when establishing the connection
     *
     * @param headers header object of the connection
     */
    void setHeaders(Schema headers);

    /**
     * Sets schema object containing the definitions of the HTTP headers to use when establishing the connection
     *
     * @param headers header object of the connection
     * @return this WebSocketChannelBinding instance
     */
    default WebSocketChannelBinding headers(Schema headers) {
        setHeaders(headers);
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
     * @return this WebSocketChannelBinding instance
     */
    default WebSocketChannelBinding bindingVersion(String bindingVersion) {
        setBindingVersion(bindingVersion);
        return this;
    }
}
