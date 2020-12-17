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
package io.smallrye.asyncapi.spec.annotations.binding.http;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.smallrye.asyncapi.spec.annotations.schema.Schema;

/**
 * Protocol-specific information for an HTTP operation.
 *
 * @see "https://github.com/asyncapi/bindings/blob/master/http/README.md#operation-binding-object"
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface HTTPOperationBinding {

    /**
     * <strong>Required</strong>. Type of operation. Its value MUST be either request or response.
     *
     * @return type of the operation
     */
    String type();

    /**
     * When type is request, this is the HTTP method, otherwise it MUST be ignored.
     * Its value MUST be one of GET, POST, PUT, PATCH, DELETE, HEAD, OPTIONS, CONNECT, and TRACE.
     *
     * @return method of the http request
     */
    Method method();

    /**
     * A Schema object containing the definitions for each query parameter. This schema MUST be of type object and have a
     * properties key.
     *
     * @return query schema of the request
     */
    Schema query();

    /**
     * The version of this binding. If omitted, "latest" MUST be assumed.
     *
     * @return version of the binding
     */
    String bindingVersion() default "latest";
}
