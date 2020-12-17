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
 * Allows configuration of the supported OAuth Flows.
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0/#oauthFlowsObject"
 */
public interface OAuthFlows extends Constructible, Extensible<OAuthFlows> {

    /**
     * Returns the OAuth Implicit flow
     *
     * @return the OAuth Implicit flow
     */
    OAuthFlow getImplicit();

    /**
     * Sets the OAuth Implicit flow
     *
     * @param implicit the OAuth Implicit flow
     */
    void setImplicit(OAuthFlow implicit);

    /**
     * Sets the name of the OAuthScope
     *
     * @param implicit the OAuth Implicit flow
     * @return this OAuthScope instance
     */
    default OAuthFlows implicit(OAuthFlow implicit) {
        setImplicit(implicit);
        return this;
    }

    /**
     * Returns the configuration for the OAuth Resource Owner Password flow.
     *
     * @return the configuration for the OAuth Resource Owner Password flow.
     */
    OAuthFlow getPassword();

    /**
     * Sets the configuration for the OAuth Resource Owner Password flow.
     *
     * @param password the configuration for the OAuth Resource Owner Password flow.
     */
    void setPassword(OAuthFlow password);

    /**
     * Sets the configuration for the OAuth Resource Owner Password flow.
     *
     * @param password the configuration for the OAuth Resource Owner Password flow.
     * @return this OAuthScope instance
     */
    default OAuthFlows password(OAuthFlow password) {
        setPassword(password);
        return this;
    }

    /**
     * Returns the configuration for the OAuth Client Credentials flow.
     *
     * @return the configuration for the OAuth Client Credentials flow.
     */
    OAuthFlow getClientCredentials();

    /**
     * Sets the configuration for the OAuth Client Credentials flow.
     *
     * @param clientCredentials the configuration for the OAuth Client Credentials flow.
     */
    void setClientCredentials(OAuthFlow clientCredentials);

    /**
     * Sets the configuration for the OAuth Client Credentials flow.
     *
     * @param clientCredentials the configuration for the OAuth Client Credentials flow.
     * @return this OAuthScope instance
     */
    default OAuthFlows clientCredentials(OAuthFlow clientCredentials) {
        setClientCredentials(clientCredentials);
        return this;
    }

    /**
     * Returns the configuration for the OAuth Authorization Code flow.
     *
     * @return the configuration for the OAuth Authorization Code flow.
     */
    OAuthFlow getAuthorizationCode();

    /**
     * Sets the configuration for the OAuth Authorization Code flow.
     *
     * @param authorizationCode the configuration for the OAuth Authorization Code flow.
     */
    void setAuthorizationCode(OAuthFlow authorizationCode);

    /**
     * Sets the configuration for the OAuth Authorization Code flow.
     *
     * @param authorizationCode the configuration for the OAuth Authorization Code flow.
     * @return this OAuthScope instance
     */
    default OAuthFlows authorizationCode(OAuthFlow authorizationCode) {
        setAuthorizationCode(authorizationCode);
        return this;
    }
}
