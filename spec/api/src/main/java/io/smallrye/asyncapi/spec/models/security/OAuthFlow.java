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

import java.util.List;

import io.smallrye.asyncapi.spec.models.Constructible;
import io.smallrye.asyncapi.spec.models.Extensible;

/**
 * Allows configuration of the supported OAuth Flows.
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0/#oauthFlowsObject"
 */
public interface OAuthFlow extends Constructible, Extensible<OAuthFlow> {

    /**
     * Returns the authorizationUrl of the OAuthFlow
     *
     * @return the authorizationUrl of the OAuthFlow
     */
    String getAuthorizationUrl();

    /**
     * Sets the authorizationUrl of the OAuthFlow
     *
     * @param authorizationUrl the authorizationUrl of the OAuthFlow
     */
    void setAuthorizationUrl(String authorizationUrl);

    /**
     * Sets the authorizationUrl of the OAuthFlow
     *
     * @param authorizationUrl the authorizationUrl of the OAuthFlow
     * @return this OAuthFlow instance
     */
    default OAuthFlow authorizationUrl(String authorizationUrl) {
        setAuthorizationUrl(authorizationUrl);
        return this;
    }

    /**
     * Returns the tokenUrl of the OAuthFlow
     *
     * @return the tokenUrl of the OAuthFlow
     */
    String getTokenUrl();

    /**
     * Sets the tokenUrl of the OAuthFlow
     *
     * @param tokenUrl the tokenUrl of the OAuthFlow
     */
    void setTokenUrl(String tokenUrl);

    /**
     * Sets the tokenUrl of the OAuthFlow
     *
     * @param tokenUrl the tokenUrl of the OAuthFlow
     * @return this OAuthFlow instance
     */
    default OAuthFlow tokenUrl(String tokenUrl) {
        setTokenUrl(tokenUrl);
        return this;
    }

    /**
     * Returns the refreshUrl of the OAuthFlow
     *
     * @return the refreshUrl of the OAuthFlow
     */
    String getRefreshUrl();

    /**
     * Sets the refreshUrl of the OAuthFlow
     *
     * @param refreshUrl the refreshUrl of the OAuthFlow
     */
    void setRefreshUrl(String refreshUrl);

    /**
     * Sets the refreshUrl of the OAuthFlow
     *
     * @param refreshUrl the refreshUrl of the OAuthFlow
     * @return this OAuthFlow instance
     */
    default OAuthFlow refreshUrl(String refreshUrl) {
        setRefreshUrl(refreshUrl);
        return this;
    }

    /**
     * Returns the scopes of the OAuthFlow
     *
     * @return the scopes of the OAuthFlow
     */
    List<OAuthScope> getScopes();

    /**
     * Sets the scopes of the OAuthFlow
     *
     * @param scopes the scopes of the OAuthFlow
     */
    void setScopes(List<OAuthScope> scopes);

    /**
     * Sets the scopes of the OAuthFlow
     *
     * @param scopes the scopes of the OAuthFlow
     * @return this OAuthFlow instance
     */
    default OAuthFlow scopes(List<OAuthScope> scopes) {
        setScopes(scopes);
        return this;
    }

    /**
     * Adds the given OAuthScope property to this OAuthFlow' list of properties.
     *
     * @param scope to be added to the OAuthFlow
     * @return the current OAuthFlow object
     */
    OAuthFlow addScope(OAuthScope scope);

    /**
     * Removes the given OAuthScope property to this OAuthFlow' list of properties.
     *
     * @param scope to be removed to the OAuthFlow
     */
    void removeScope(OAuthScope scope);
}
