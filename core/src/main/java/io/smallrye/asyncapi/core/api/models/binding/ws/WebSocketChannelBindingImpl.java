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
package io.smallrye.asyncapi.core.api.models.binding.ws;

import io.smallrye.asyncapi.core.api.models.ExtensibleImpl;
import io.smallrye.asyncapi.core.api.models.ModelImpl;
import io.smallrye.asyncapi.spec.models.binding.ChannelBinding;
import io.smallrye.asyncapi.spec.models.binding.ws.WebSocketChannelBinding;
import io.smallrye.asyncapi.spec.models.schema.Schema;

/**
 * An implementation of the {@link WebSocketChannelBinding} AsyncAPI model interface.
 */
public class WebSocketChannelBindingImpl extends ExtensibleImpl<ChannelBinding> implements WebSocketChannelBinding, ModelImpl {

    private String method;

    private Schema query;

    private Schema headers;

    private String bindingVersion;

    /**
     * @see WebSocketChannelBinding#getMethod()
     */
    @Override
    public String getMethod() {
        return method;
    }

    /**
     * @see WebSocketChannelBinding#setMethod(String method)
     */
    public void setMethod(final String method) {
        this.method = method;
    }

    /**
     * @see WebSocketChannelBinding#getQuery()
     */
    @Override
    public Schema getQuery() {
        return query;
    }

    /**
     * @see WebSocketChannelBinding#setQuery(Schema query)
     */
    public void setQuery(final Schema query) {
        this.query = query;
    }

    /**
     * @see WebSocketChannelBinding#getHeaders()
     */
    @Override
    public Schema getHeaders() {
        return headers;
    }

    /**
     * @see WebSocketChannelBinding#setHeaders(Schema headers)
     */
    public void setHeaders(final Schema headers) {
        this.headers = headers;
    }

    /**
     * @see WebSocketChannelBinding#getBindingVersion()
     */
    @Override
    public String getBindingVersion() {
        return bindingVersion;
    }

    /**
     * @see WebSocketChannelBinding#setBindingVersion(String bindingVersion)
     */
    @Override
    public void setBindingVersion(final String bindingVersion) {
        this.bindingVersion = bindingVersion;
    }
}
