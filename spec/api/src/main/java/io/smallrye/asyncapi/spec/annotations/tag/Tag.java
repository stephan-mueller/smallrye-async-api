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
package io.smallrye.asyncapi.spec.annotations.tag;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.smallrye.asyncapi.spec.annotations.ExternalDocumentation;

/**
 * Allows adding meta data to a single tag.
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0/#tagsObject"
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Repeatable(Tags.class)
public @interface Tag {

    /**
     * <strong>Required</strong>. The name of the tag
     *
     * @return name of the tag
     */
    String name();

    /**
     * A short description for the tag. CommonMark syntax can be used for rich text representation.
     *
     * @return description of the tag
     */
    String description() default "";

    /**
     * Additional external documentation for this tag.
     *
     * @return external documentation of the tag
     */
    ExternalDocumentation externalDocs() default @ExternalDocumentation(url = "");
}
