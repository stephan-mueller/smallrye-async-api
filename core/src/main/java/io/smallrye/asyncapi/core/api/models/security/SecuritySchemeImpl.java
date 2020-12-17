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
package io.smallrye.asyncapi.core.api.models.security;

import io.smallrye.asyncapi.core.api.models.ExtensibleImpl;
import io.smallrye.asyncapi.core.api.models.ModelImpl;
import io.smallrye.asyncapi.spec.annotations.security.SecuritySchemeIn;
import io.smallrye.asyncapi.spec.annotations.security.SecuritySchemeType;
import io.smallrye.asyncapi.spec.models.security.OAuthFlows;
import io.smallrye.asyncapi.spec.models.security.SecurityScheme;

/**
 * An implementation of the {@link SecurityScheme} AsyncAPI model interface.
 */
public class SecuritySchemeImpl extends ExtensibleImpl<SecurityScheme> implements SecurityScheme, ModelImpl {

    private SecuritySchemeType type;

    private String description;

    private String name;

    private SecuritySchemeIn in;

    private String scheme;

    private String bearerFormat;

    private OAuthFlows flows;

    private String openIdConnectUrl;

    /**
     * @see SecurityScheme#getType()
     */
    @Override
    public SecuritySchemeType getType() {
        return type;
    }

    /**
     * @see SecurityScheme#setType(SecuritySchemeType type)
     */
    @Override
    public void setType(final SecuritySchemeType type) {
        this.type = type;
    }

    /**
     * @see SecurityScheme#getDescription()
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * @see SecurityScheme#setDescription(String description)
     */
    @Override
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @see SecurityScheme#getName()
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * @see SecurityScheme#setName(String name)
     */
    @Override
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @see SecurityScheme#getIn()
     */
    @Override
    public SecuritySchemeIn getIn() {
        return this.in;
    }

    /**
     * @see SecurityScheme#setIn(SecuritySchemeIn in)
     */
    @Override
    public void setIn(final SecuritySchemeIn in) {
        this.in = in;
    }

    /**
     * @see SecurityScheme#getScheme()
     */
    @Override
    public String getScheme() {
        return this.scheme;
    }

    /**
     * @see SecurityScheme#setScheme(String scheme)
     */
    @Override
    public void setScheme(final String scheme) {
        this.scheme = scheme;
    }

    /**
     * @see SecurityScheme#getBearerFormat()
     */
    @Override
    public String getBearerFormat() {
        return this.bearerFormat;
    }

    /**
     * @see SecurityScheme#setBearerFormat(String bearerFormat)
     */
    @Override
    public void setBearerFormat(final String bearerFormat) {
        this.bearerFormat = bearerFormat;
    }

    /**
     * @see SecurityScheme#getFlows()
     */
    @Override
    public OAuthFlows getFlows() {
        return this.flows;
    }

    /**
     * @see SecurityScheme#setFlows(OAuthFlows flows)
     */
    @Override
    public void setFlows(final OAuthFlows flows) {
        this.flows = flows;
    }

    /**
     * @see SecurityScheme#getOpenIdConnectUrl()
     */
    @Override
    public String getOpenIdConnectUrl() {
        return this.openIdConnectUrl;
    }

    /**
     * @see SecurityScheme#setOpenIdConnectUrl(String openIdConnectUrl)
     */
    @Override
    public void setOpenIdConnectUrl(final String openIdConnectUrl) {
        this.openIdConnectUrl = openIdConnectUrl;
    }

    @Override
    public String toString() {
        return "SecuritySchemeImpl{" + "type=" + type + ", description='" + description + '\'' + ", name='" + name + '\''
                + ", in=" + in + ", scheme='"
                + scheme + '\'' + ", bearerFormat='" + bearerFormat + '\'' + ", flows=" + flows + ", openIdConnectUrl='"
                + openIdConnectUrl + '\'' + '}';
    }
}
