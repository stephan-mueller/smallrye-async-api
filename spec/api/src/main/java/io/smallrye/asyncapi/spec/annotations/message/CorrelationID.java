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
package io.smallrye.asyncapi.spec.annotations.message;

/**
 * An object that specifies an identifier at design time that can used for message tracing and correlation.
 *
 * For specifying and computing the location of a Correlation ID, a runtime expression is used.
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0/#correlationIdObject"
 */
public @interface CorrelationID {

    /**
     * An optional description of the identifier. CommonMark syntax can be used for rich text representation.
     *
     * @return description of the correlation id
     */
    String description() default "";

    /**
     * <strong>REQUIRED</strong>. A runtime expression that specifies the location of the correlation ID.
     *
     * @return location of the correlation id
     */
    String location() default "";
}
