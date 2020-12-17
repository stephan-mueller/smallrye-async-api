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

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.smallrye.asyncapi.spec.annotations.channel.Channels;
import io.smallrye.asyncapi.spec.annotations.info.Info;
import io.smallrye.asyncapi.spec.annotations.server.Server;
import io.smallrye.asyncapi.spec.annotations.tag.Tag;

@Target({ ElementType.TYPE, ElementType.PACKAGE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface AsyncAPI {

    /**
     * <strong>Required</strong>. Specifies the AsyncAPI Specification version being used.
     *
     * It can be used by tooling Specifications and clients to interpret the version. The structure shall be major.minor.patch,
     * where patch
     * versions must be compatible with the existing major.minor tooling. Typically patch versions will be introduced to address
     * errors in the
     * documentation, and tooling should typically be compatible with the corresponding major.minor (1.0.*).
     *
     * Patch versions will correspond to patches of this document.
     *
     * @return version of io.smallrye.asyncapi.spec
     */
    String asyncapi();

    /**
     * A string representing the default content type to use when encoding/decoding a message's payload.
     *
     * The value MUST be a specific media type (e.g. application/json). This value MUST be used by schema parsers when the
     * contentType
     * property is omitted.
     *
     * @return content type of the message's payload
     */
    String defaultContentType();

    /**
     * Identifier of the application the AsyncAPI document is defining.
     *
     * @return version of the application
     */
    String id() default "";

    /**
     * <strong>Required</strong>. Provides metadata about the API. The metadata can be used by the clients if needed.
     *
     * @return Info Object of the application
     */
    Info info();

    /**
     * Provides connection details of servers.
     *
     * @return servers the application is connected to
     */
    Server[] servers() default {};

    /**
     * <strong>Required</strong>. The available channels and messages for the API.
     *
     * @return Channels of the application
     */
    Channels channels() default @Channels();

    /**
     * An element to hold various schemas for the specification.
     *
     * @return components of the application
     */
    Components components() default @Components();

    /**
     * A list of tags used by the specification with additional metadata. Each tag name in the list MUST be unique.
     *
     * @return tags of the application
     */
    Tag[] tags() default {};

    /**
     * Additional external documentation.
     *
     * @return external documentation of the application
     */
    ExternalDocumentation externalDocs() default @ExternalDocumentation(url = "");
}
