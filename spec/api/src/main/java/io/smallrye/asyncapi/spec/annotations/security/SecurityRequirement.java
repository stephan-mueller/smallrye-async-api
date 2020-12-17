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
package io.smallrye.asyncapi.spec.annotations.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Lists the required security schemes to execute this operation.
 *
 * The name used for each property MUST correspond to a security scheme declared in the Security Schemes under the Components
 * Object.
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0/#securityRequirementObject"
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface SecurityRequirement {

    /**
     * Name of the SecurityRequirement
     *
     * @return name of the SecurityRequirement
     */
    String name() default "";

    /**
     * Each name MUST correspond to a security scheme which is declared in the Security Schemes under the components Object.
     *
     * If the security scheme is of type "oauth2" or "openIdConnect", then the value is a list of scope names required for the
     * execution. For
     * other security scheme types, the array MUST be empty.
     *
     * @return name of the SecurityRequirement
     */
    String[] values() default {};
}
