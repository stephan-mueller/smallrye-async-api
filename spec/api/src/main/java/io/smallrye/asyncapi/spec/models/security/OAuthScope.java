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
package io.smallrye.asyncapi.spec.models.security;

import io.smallrye.asyncapi.spec.models.Constructible;
import io.smallrye.asyncapi.spec.models.Extensible;

/**
 * The available scopes for the OAuth2 security scheme.
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0/#oauthFlowObject"
 */
public interface OAuthScope extends Constructible, Extensible<OAuthScope> {

    /**
     * Returns the name of the OAuthScope
     *
     * @return the name of the OAuthScope
     */
    String getName();

    /**
     * Sets the name of the OAuthScope
     *
     * @param name the name of the OAuthScope
     */
    void setName(String name);

    /**
     * Sets the name of the OAuthScope
     *
     * @param name the name of the OAuthScope
     * @return this OAuthScope instance
     */
    default OAuthScope name(String name) {
        setName(name);
        return this;
    }

    /**
     * Returns the description of the OAuthScope
     *
     * @return the description of the OAuthScope
     */
    String getDescription();

    /**
     * Sets the description of the OAuthScope
     *
     * @param description the description of the OAuthScope
     */
    void setDescription(String description);

    /**
     * Sets the description of the OAuthScope
     *
     * @param description the description of the OAuthScope
     * @return this OAuthScope instance
     */
    default OAuthScope description(String description) {
        setDescription(description);
        return this;
    }
}
