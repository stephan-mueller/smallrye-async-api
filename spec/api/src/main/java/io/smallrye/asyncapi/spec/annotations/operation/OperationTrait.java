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
package io.smallrye.asyncapi.spec.annotations.operation;

import io.smallrye.asyncapi.spec.annotations.ExternalDocumentation;
import io.smallrye.asyncapi.spec.annotations.binding.OperationBindings;
import io.smallrye.asyncapi.spec.annotations.tag.Tag;

/**
 * Describes a trait that MAY be applied to an Operation Object.
 *
 * This object MAY contain any property from the Operation Object, except message and traits.
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0/#operationTraitObject"
 */
public @interface OperationTrait {

    /**
     * Unique string used to identify the operation.
     *
     * The id MUST be unique among all operations described in the API. The operationId value is case-sensitive. Tools and
     * libraries MAY use
     * the operationId to uniquely identify an operation, therefore, it is RECOMMENDED to follow common programming naming
     * conventions.
     *
     * @return id of the operation
     */
    String operationId() default "";

    /**
     * A short summary of what the operation is about.
     *
     * @return summary of the operation
     */
    String summary() default "";

    /**
     * A verbose explanation of the operation. CommonMark syntax can be used for rich text representation.
     *
     * @return description of the operation
     */
    String description() default "";

    /**
     * A list of tags for API documentation control. Tags can be used for logical grouping of operations.
     *
     * @return tags of the operation
     */
    Tag[] tags() default {};

    /**
     * Additional external documentation for this operation.
     *
     * @return external documentation of the operation
     */
    ExternalDocumentation externalDocs() default @ExternalDocumentation(url = "");

    /**
     * A free-form map where the keys describe the name of the protocol and the values describe protocol-specific definitions
     * for the
     * operation.
     *
     * @return bindings of the operation
     */
    OperationBindings bindings() default @OperationBindings();

    /**
     * Reference value to a OperationTraits definition.
     *
     * @return reference value to a OperationTraits definition.
     */
    String ref() default "";
}
