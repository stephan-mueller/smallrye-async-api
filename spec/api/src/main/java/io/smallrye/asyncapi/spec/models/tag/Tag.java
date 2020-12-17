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
package io.smallrye.asyncapi.spec.models.tag;

import io.smallrye.asyncapi.spec.models.Constructible;
import io.smallrye.asyncapi.spec.models.Extensible;
import io.smallrye.asyncapi.spec.models.ExternalDocumentation;

/**
 * Allows adding meta data to a single tag.
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0/#tagsObject"
 */
public interface Tag extends Constructible, Extensible<Tag> {

    /**
     * Returns the name of the tag
     *
     * @return the name of the tag
     */
    String getName();

    /**
     * Sets the name of the tag
     *
     * @param name the name of the tag
     */
    void setName(String name);

    /**
     * Sets the name of the tag
     *
     * @param name the name of the tag
     * @return this Tag instance
     */
    default Tag name(String name) {
        setName(name);
        return this;
    }

    /**
     * Returns the description of the tag
     *
     * @return the description of the tag
     */
    String getDescription();

    /**
     * Sets the description of the tag
     *
     * @param description the description of the tag
     */
    void setDescription(String description);

    /**
     * Sets the description of the tag
     *
     * @param description the description of the tag
     * @return this Tag instance
     */
    default Tag description(String description) {
        setDescription(description);
        return this;
    }

    /**
     * Returns the external documentation of the tag
     *
     * @return the external documentation of the tag
     */
    ExternalDocumentation getExternalDocumentation();

    /**
     * Sets the external documentation of the tag
     *
     * @param externalDocumentation the external documentation of the tag
     */
    void setExternalDocumentation(ExternalDocumentation externalDocumentation);

    /**
     * Sets the external documentation of the tag
     *
     * @param externalDocumentation the external documentation of the tag
     * @return this Tag instance
     */
    default Tag externalDocumentation(ExternalDocumentation externalDocumentation) {
        setExternalDocumentation(externalDocumentation);
        return this;
    }
}
