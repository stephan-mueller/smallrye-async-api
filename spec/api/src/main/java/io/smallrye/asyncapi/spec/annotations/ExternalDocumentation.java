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
package io.smallrye.asyncapi.spec.annotations;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Allows referencing an external resource for extended documentation.
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0/#a-name-externaldocumentationobject-a-external-documentation-object"
 */
@Target({})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ExternalDocumentation {

    /**
     * A short description of the target documentation. CommonMark syntax can be used for rich text representation.
     *
     * @return description of the documentation
     */
    String description() default "";

    /**
     * <strong>Required</strong>. The URL for the target documentation. Value MUST be in the format of a URL.
     *
     * @return url of the external documentation
     */
    String url();
}
