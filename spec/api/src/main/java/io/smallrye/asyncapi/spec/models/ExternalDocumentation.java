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
package io.smallrye.asyncapi.spec.models;

/**
 * Allows referencing an external resource for extended documentation.
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0/#a-name-externaldocumentationobject-a-external-documentation-object"
 */
public interface ExternalDocumentation extends Constructible, Extensible<ExternalDocumentation> {

    /**
     * Returns the description of the external documentation
     *
     * @return the description of the external documentation
     */
    String getDescription();

    /**
     * Sets the description of the external documentation
     *
     * @param description the description of the external documentation
     */
    void setDescription(String description);

    /**
     * Sets the description of the external documentation
     *
     * @param description the description of the external documentation
     * @return this ExternalDocumentation instance
     */
    default ExternalDocumentation description(String description) {
        setDescription(description);
        return this;
    }

    /**
     * Returns the url of the external documentation
     *
     * @return the url of the external documentation
     */
    String getUrl();

    /**
     * Sets the url of the external documentation
     *
     * @param url the url of the external documentation
     */
    void setUrl(String url);

    /**
     * Sets the url of the external documentation
     *
     * @param url the url of the external documentation
     * @return this ExternalDocumentation instance
     */
    default ExternalDocumentation url(String url) {
        setUrl(url);
        return this;
    }
}
