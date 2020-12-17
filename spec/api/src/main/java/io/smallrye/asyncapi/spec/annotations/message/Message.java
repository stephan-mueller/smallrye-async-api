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

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.smallrye.asyncapi.spec.annotations.ExternalDocumentation;
import io.smallrye.asyncapi.spec.annotations.binding.MessageBindings;
import io.smallrye.asyncapi.spec.annotations.schema.Schema;
import io.smallrye.asyncapi.spec.annotations.tag.Tag;

/**
 * Describes a message received on a given channel and operation.
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0/#messageObject"
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Message {

    /**
     * Schema definition of the application headers. Schema MUST be of type “object”. It MUST NOT define the protocol headers.
     *
     * @return headers of the message
     */
    Schema headers() default @Schema();

    /**
     * Definition of the message payload. It can be of any type but defaults to Schema object.
     *
     * @return payload of the message
     */
    Schema payload() default @Schema();

    /**
     * Definition of the correlation ID used for message tracing or matching.
     *
     * @return correlation id of the message
     */
    CorrelationID correlationID() default @CorrelationID();

    /**
     * A string containing the name of the schema format used to define the message payload.
     *
     * If omitted, implementations should parse the payload as a Schema object.
     * Check out the supported schema formats table for more information.
     * Custom values are allowed but their implementation is OPTIONAL.
     * A custom value MUST NOT refer to one of the schema formats listed in the table.
     *
     * @return format of the message
     */
    String schemaFormat() default "";

    /**
     * The content type to use when encoding/decoding a message’s payload.
     *
     * The value MUST be a specific media type (e.g. application/json).
     * When omitted, the value MUST be the one specified on the defaultContentType field.
     *
     * @return content type of the message
     */
    String contentType() default "";

    /**
     * A machine-friendly name for the message.
     *
     * @return name of the message
     */
    String name() default "";

    /**
     * A human-friendly title for the message.
     *
     * @return title of the message
     */
    String title() default "";

    /**
     * A short summary of what the message is about.
     *
     * @return summary of the message
     */
    String summary() default "";

    /**
     * A verbose explanation of the message. CommonMark syntax can be used for rich text representation.
     *
     * @return description of the message
     */
    String description() default "";

    /**
     * A list of tags for API documentation control. Tags can be used for logical grouping of messages.
     *
     * @return all tags of the messages
     */
    Tag[] tags() default {};

    /**
     * Additional external documentation for this message.
     *
     * @return external documentation for this message.
     */
    ExternalDocumentation externalDocs() default @ExternalDocumentation(url = "");

    /**
     * A free-form map where the keys describe the name of the protocol and the values describe protocol-specific definitions
     * for the message.
     *
     * @return bindings of the message
     */
    MessageBindings bindings() default @MessageBindings();

    /**
     * An array with examples of valid message objects.
     *
     * @return examples of the message
     */
    String[] example() default "";

    /**
     * A list of traits to apply to the message object. Traits MUST be merged into the message object using the JSON Merge Patch
     * algorithm
     * in the same order they are defined here. The resulting object MUST be a valid Message Object.
     *
     * @return traits of the message
     */
    MessageTrait[] traits() default {};

    /**
     * Reference value to a Schema definition.
     *
     * @return reference value to a Schema definition.
     */
    String ref() default "";
}
