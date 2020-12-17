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
package io.smallrye.asyncapi.spec.annotations.server;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An object representing a Server Variable for server URL template substitution.
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0/#a-name-servervariableobject-a-server-variable-object"
 */
@Target({})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ServerVariable {

    /**
     * <strong>Required</strong>. Name of the variable
     *
     * @return name of the variable
     */
    String name();

    /**
     * An enumeration of string values to be used if the substitution options are from a limited set.
     *
     * @return array of possible values for this ServerVariable
     **/
    String[] enumeration() default {};

    /**
     * The default value to use for substitution, and to send, if an alternate value is not supplied.
     *
     * @return the default value of this server variable
     **/
    String defaultValue();

    /**
     * An optional description for the server variable. CommonMark syntax MAY be used for rich text representation.
     *
     * @return the description of this server variable
     **/
    String description() default "";

    /**
     * An array of examples of the server variable.
     *
     * @return examples of the server variables
     */
    String[] examples() default {};
}
