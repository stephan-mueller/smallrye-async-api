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

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.smallrye.asyncapi.spec.annotations.ExternalDocumentation;
import io.smallrye.asyncapi.spec.annotations.binding.OperationBindings;
import io.smallrye.asyncapi.spec.annotations.message.Message;
import io.smallrye.asyncapi.spec.annotations.tag.Tag;

/**
 * Describes a publish or a subscribe operation.
 *
 * This provides a place to document how and why messages are sent and received. For example, an operation might describe a chat
 * application
 * use case where a user sends a text message to a group.
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0/#operationObject"
 */
@Target({})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Operation {

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
     * @return array of tags of the operation
     */
    Tag[] tags() default {};

    /**
     * Additional external documentation for this operation.
     *
     * @return external documentation of the operation.
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
     * A list of traits to apply to the operation object. Traits MUST be merged into the operation object using the JSON Merge
     * Patch algorithm
     * in the same order they are defined here.
     *
     * @return traits of the operation
     */
    OperationTrait[] traits() default {};

    /**
     * A definition of the message that will be published or received on this channel. oneOf is allowed here to specify multiple
     * messages,
     * however, a message MUST be valid only against one of the referenced message objects.
     *
     * @return message of the operation
     */
    Message message() default @Message(externalDocs = @ExternalDocumentation(url = ""));
}
