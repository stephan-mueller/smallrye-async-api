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

import io.smallrye.asyncapi.spec.annotations.binding.http.Method;
import io.smallrye.asyncapi.spec.models.Constructible;
import io.smallrye.asyncapi.spec.models.binding.OperationBinding;
import io.smallrye.asyncapi.spec.models.schema.Schema;

/**
 * Protocol-specific information for an HTTP operation.
 *
 * @see "https://github.com/asyncapi/bindings/blob/master/http/README.md#operation-binding-object"
 */
public interface HTTPOperationBinding extends OperationBinding, Constructible {

    /**
     * Returns the type of the operation
     *
     * @return the type of the operation
     */
    String getType();

    /**
     * Sets the type of the operation
     *
     * @param type type of the operation
     */
    void setType(String type);

    /**
     * Sets the type of the operation
     *
     * @param type type of the operation
     * @return this HTTPMessageBinding instance
     */
    default HTTPOperationBinding type(String type) {
        setType(type);
        return this;
    }

    /**
     * Returns the method of the operation
     *
     * @return the method of the operation
     */
    Method getMethod();

    /**
     * Sets the method of the operation
     *
     * @param method type of the operation
     */
    void setMethod(Method method);

    /**
     * Sets the method of the operation
     *
     * @param method method of the operation
     * @return this HTTPMessageBinding instance
     */
    default HTTPOperationBinding method(Method method) {
        setMethod(method);
        return this;
    }

    /**
     * Returns the schema object containing the definitions for each query parameter.
     *
     * @return the query of the operation
     */
    Schema getQuery();

    /**
     * Sets the schema object containing the definitions for each query parameter.
     *
     * @param query type of the operation
     */
    void setQuery(Schema query);

    /**
     * Sets the query of the operation
     *
     * @param query query of the operation
     * @return this HTTPMessageBinding instance
     */
    default HTTPOperationBinding query(Schema query) {
        setQuery(query);
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
    default HTTPOperationBinding bindingVersion(String bindingVersion) {
        setBindingVersion(bindingVersion);
        return this;
    }
}
