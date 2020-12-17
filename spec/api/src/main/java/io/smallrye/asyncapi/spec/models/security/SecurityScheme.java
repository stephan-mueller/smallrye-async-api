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

import io.smallrye.asyncapi.spec.annotations.security.SecuritySchemeIn;
import io.smallrye.asyncapi.spec.annotations.security.SecuritySchemeType;
import io.smallrye.asyncapi.spec.models.Constructible;
import io.smallrye.asyncapi.spec.models.Extensible;

/**
 * Defines a security scheme that can be used by the operations. Supported schemes are:
 *
 * <ul>
 * <li>User/Password.</li>
 * <li>API key (either as user or as password).</li>
 * <li>X.509 certificate.</li>
 * <li>End-to-end encryption (either symmetric or asymmetric).</li>
 * <li>HTTP authentication.</li>
 * <li>HTTP API key.</li>
 * <li>OAuth2’s common flows (Implicit, Resource Owner Protected Credentials, Client Credentials and Authorization Code) as
 * defined in RFC6749.</li>
 * <li>OpenID Connect Discovery.</li>
 * </ul>
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0/#securitySchemeObject"
 */
public interface SecurityScheme extends Constructible, Extensible<SecurityScheme> {

    /**
     * Returns the security scheme type of the SecurityScheme
     *
     * @return the security scheme type of the SecurityScheme
     */
    SecuritySchemeType getType();

    /**
     * Sets the security scheme type of the SecurityScheme
     *
     * @param type the security scheme type of the SecurityScheme
     */
    void setType(SecuritySchemeType type);

    /**
     * Sets the security scheme type of the SecurityScheme
     *
     * @param type the security scheme type of the SecurityScheme
     * @return this SecurityScheme instance
     */
    default SecurityScheme type(SecuritySchemeType type) {
        setType(type);
        return this;
    }

    /**
     * Returns the description of the SecurityScheme
     *
     * @return the description of the SecurityScheme
     */
    String getDescription();

    /**
     * Sets the description of the SecurityScheme
     *
     * @param description the description of the SecurityScheme
     */
    void setDescription(String description);

    /**
     * Sets the description of the SecurityScheme
     *
     * @param description the description of the SecurityScheme
     * @return this SecurityScheme instance
     */
    default SecurityScheme description(String description) {
        setDescription(description);
        return this;
    }

    /**
     * Returns the name of the SecurityScheme
     *
     * @return the name of the SecurityScheme
     */
    String getName();

    /**
     * Sets the name of the SecurityScheme
     *
     * @param name the name of the SecurityScheme
     */
    void setName(String name);

    /**
     * Sets the name of the SecurityScheme
     *
     * @param name the name of the SecurityScheme
     * @return this SecurityScheme instance
     */
    default SecurityScheme name(String name) {
        setName(name);
        return this;
    }

    /**
     * Returns the in of the SecurityScheme
     *
     * @return the in of the SecurityScheme
     */
    SecuritySchemeIn getIn();

    /**
     * Sets the in of the SecurityScheme
     *
     * @param in the in of the SecurityScheme
     */
    void setIn(SecuritySchemeIn in);

    /**
     * Sets the in of the SecurityScheme
     *
     * @param in the in of the SecurityScheme
     * @return this SecurityScheme instance
     */
    default SecurityScheme in(SecuritySchemeIn in) {
        setIn(in);
        return this;
    }

    /**
     * Returns the scheme of the SecurityScheme
     *
     * @return the scheme of the SecurityScheme
     */
    String getScheme();

    /**
     * Sets the scheme of the SecurityScheme
     *
     * @param scheme the scheme of the SecurityScheme
     */
    void setScheme(String scheme);

    /**
     * Sets the scheme of the SecurityScheme
     *
     * @param scheme the scheme of the SecurityScheme
     * @return this SecurityScheme instance
     */
    default SecurityScheme scheme(String scheme) {
        setScheme(scheme);
        return this;
    }

    /**
     * Returns the bearerFormat of the SecurityScheme
     *
     * @return the bearerFormat of the SecurityScheme
     */
    String getBearerFormat();

    /**
     * Sets the bearerFormat of the SecurityScheme
     *
     * @param bearerFormat the bearerFormat of the SecurityScheme
     */
    void setBearerFormat(String bearerFormat);

    /**
     * Sets the bearerFormat of the SecurityScheme
     *
     * @param bearerFormat the bearerFormat of the SecurityScheme
     * @return this SecurityScheme instance
     */
    default SecurityScheme bearerFormat(String bearerFormat) {
        setBearerFormat(bearerFormat);
        return this;
    }

    /**
     * Returns the flows of the SecurityScheme
     *
     * @return the flows of the SecurityScheme
     */
    OAuthFlows getFlows();

    /**
     * Sets the flows of the SecurityScheme
     *
     * @param flows the bearerFormat of the SecurityScheme
     */
    void setFlows(OAuthFlows flows);

    /**
     * Sets the flows of the SecurityScheme
     *
     * @param flows the bearerFormat of the SecurityScheme
     * @return this SecurityScheme instance
     */
    default SecurityScheme flows(OAuthFlows flows) {
        setFlows(flows);
        return this;
    }

    /**
     * Sets the flows of the SecurityScheme
     *
     * @param flows the flows of the SecurityScheme
     * @return this SecurityScheme instance
     */
    default SecurityScheme bearerFormat(OAuthFlows flows) {
        setFlows(flows);
        return this;
    }

    /**
     * Returns the openIdConnectUrl of the SecurityScheme
     *
     * @return the openIdConnectUrl of the SecurityScheme
     */
    String getOpenIdConnectUrl();

    /**
     * Sets the openIdConnectUrl of the SecurityScheme
     *
     * @param openIdConnectUrl the openIdConnectUrl of the SecurityScheme
     */
    void setOpenIdConnectUrl(String openIdConnectUrl);

    /**
     * Sets the openIdConnectUrl of the SecurityScheme
     *
     * @param openIdConnectUrl the openIdConnectUrl of the SecurityScheme
     * @return this SecurityScheme instance
     */
    default SecurityScheme openIdConnectUrl(String openIdConnectUrl) {
        setOpenIdConnectUrl(openIdConnectUrl);
        return this;
    }
}
