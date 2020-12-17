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
package io.smallrye.asyncapi.spec.annotations.parameter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.smallrye.asyncapi.spec.annotations.schema.Schema;

/**
 * Describes a parameter included in a channel name.
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0/#a-name-parameterobject-a-parameter-object"
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Parameters.class)
@Inherited
public @interface Parameter {

    /**
     * The name of the parameter
     *
     * @return name of the parameter
     */
    String name() default "";

    /**
     * A verbose explanation of the parameter. CommonMark syntax can be used for rich text representation.
     *
     * @return description of the parameter
     */
    String description() default "";

    /**
     * Definition of the parameter.
     *
     * @return Schema of the parameter
     */
    Schema schema() default @Schema();

    /**
     * A runtime expression that specifies the location of the parameter value.
     *
     * Even when a definition for the target field exists, it MUST NOT be used to validate this parameter but, instead, the
     * schema property MUST be
     * used.
     *
     * @return location of the parameter
     */
    String location() default "";

    /**
     * This property provides a reference to an object defined elsewhere.
     *
     * This property and all other properties are mutually exclusive. If other properties are defined in addition to the ref
     * property then the
     * result is undefined.
     *
     * @return a reference to a schema definition
     **/
    String ref() default "";
}
