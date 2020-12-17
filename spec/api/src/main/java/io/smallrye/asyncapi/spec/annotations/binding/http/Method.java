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

public enum Method {

    GET("GET"),

    POST("POST"),

    PUT("PUT"),

    PATCH("PATCH"),

    DELETE("DELETE"),

    HEAD("HEAD"),

    OPTIONS("OPTIONS"),

    CONNECT("CONNECT"),

    TRACE("TRACE");

    private String value;

    Method(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
