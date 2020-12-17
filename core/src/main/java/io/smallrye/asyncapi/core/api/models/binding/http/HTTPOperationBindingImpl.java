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
package io.smallrye.asyncapi.core.api.models.binding.http;

import io.smallrye.asyncapi.core.api.models.ExtensibleImpl;
import io.smallrye.asyncapi.core.api.models.ModelImpl;
import io.smallrye.asyncapi.spec.annotations.binding.http.Method;
import io.smallrye.asyncapi.spec.models.binding.OperationBinding;
import io.smallrye.asyncapi.spec.models.binding.http.HTTPOperationBinding;
import io.smallrye.asyncapi.spec.models.schema.Schema;

/**
 * An implementation of the {@link HTTPOperationBinding} AsyncAPI model interface.
 */
public class HTTPOperationBindingImpl extends ExtensibleImpl<OperationBinding> implements HTTPOperationBinding, ModelImpl {

    private String type;

    private Method method;

    private Schema query;

    private String bindingVersion;

    /**
     * @see HTTPOperationBinding#getType()
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * @see HTTPOperationBinding#setType(String type)
     */
    @Override
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * @see HTTPOperationBinding#getMethod()
     */
    @Override
    public Method getMethod() {
        return method;
    }

    /**
     * @see HTTPOperationBinding#setMethod(Method method)
     */
    @Override
    public void setMethod(final Method method) {
        this.method = method;
    }

    /**
     * @see HTTPOperationBinding#getQuery()
     */
    @Override
    public Schema getQuery() {
        return query;
    }

    /**
     * @see HTTPOperationBinding#setQuery(Schema query)
     */
    @Override
    public void setQuery(final Schema query) {
        this.query = query;
    }

    /**
     * @see HTTPOperationBinding#getBindingVersion()
     */
    @Override
    public String getBindingVersion() {
        return bindingVersion;
    }

    /**
     * @see HTTPOperationBinding#setBindingVersion(String bindingVersion)
     */
    @Override
    public void setBindingVersion(final String bindingVersion) {
        this.bindingVersion = bindingVersion;
    }
}
