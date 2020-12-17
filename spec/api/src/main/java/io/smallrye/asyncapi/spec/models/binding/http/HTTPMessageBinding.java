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
package io.smallrye.asyncapi.spec.models.binding.http;

import io.smallrye.asyncapi.spec.models.Constructible;
import io.smallrye.asyncapi.spec.models.binding.MessageBinding;
import io.smallrye.asyncapi.spec.models.schema.Schema;

/**
 * Protocol-specific information for an HTTP message.
 *
 * @see "https://github.com/asyncapi/bindings/blob/master/http/README.md#message-binding-object"
 */
public interface HTTPMessageBinding extends MessageBinding, Constructible {

    /**
     * Returns a Schema object containing the definitions for HTTP-specific headers.
     *
     * @return the HTTP-specific headers
     */
    Schema getHeaders();

    /**
     * Sets a Schema object containing the definitions for HTTP-specific headers.
     *
     * @param headers the HTTP-specific headers
     */
    void setHeaders(Schema headers);

    /**
     * Sets a Schema object containing the definitions for HTTP-specific headers.
     *
     * @param headers the HTTP-specific headers
     * @return http headers of the message
     */
    default HTTPMessageBinding headers(Schema headers) {
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
     * @return this HTTPMessageBinding instance
     */
    default HTTPMessageBinding bindingVersion(String bindingVersion) {
        setBindingVersion(bindingVersion);
        return this;
    }
}
