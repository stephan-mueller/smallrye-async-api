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

import java.util.ArrayList;
import java.util.List;

import io.smallrye.asyncapi.core.api.models.ExtensibleImpl;
import io.smallrye.asyncapi.core.api.models.ModelImpl;
import io.smallrye.asyncapi.core.runtime.util.ModelUtil;
import io.smallrye.asyncapi.spec.models.security.OAuthFlow;
import io.smallrye.asyncapi.spec.models.security.OAuthScope;

/**
 * An implementation of the {@link OAuthFlow} AsyncAPI model interface.
 */
public class OAuthFlowImpl extends ExtensibleImpl<OAuthFlow> implements OAuthFlow, ModelImpl {

    private String authorizationUrl;

    private String tokenUrl;

    private String refreshUrl;

    private List<OAuthScope> scopes;

    /**
     * @see OAuthFlow#getAuthorizationUrl()
     */
    @Override
    public String getAuthorizationUrl() {
        return this.authorizationUrl;
    }

    /**
     * @see OAuthFlow#setAuthorizationUrl(String authorizationUrl)
     */
    @Override
    public void setAuthorizationUrl(final String authorizationUrl) {
        this.authorizationUrl = authorizationUrl;
    }

    /**
     * @see OAuthFlow#getTokenUrl()
     */
    @Override
    public String getTokenUrl() {
        return this.tokenUrl;
    }

    /**
     * @see OAuthFlow#setTokenUrl(String tokenUrl)
     */
    @Override
    public void setTokenUrl(final String tokenUrl) {
        this.tokenUrl = tokenUrl;
    }

    /**
     * @see OAuthFlow#getRefreshUrl()
     */
    @Override
    public String getRefreshUrl() {
        return this.refreshUrl;
    }

    /**
     * @see OAuthFlow#setRefreshUrl(String refreshUrl)
     */
    @Override
    public void setRefreshUrl(final String refreshUrl) {
        this.refreshUrl = refreshUrl;
    }

    /**
     * @see OAuthFlow#getScopes()
     */
    @Override
    public List<OAuthScope> getScopes() {
        return ModelUtil.unmodifiableList(this.scopes);
    }

    /**
     * @see OAuthFlow#setScopes(List<OAuthScope>)
     */
    @Override
    public void setScopes(final List<OAuthScope> scopes) {
        this.scopes = ModelUtil.replace(scopes, ArrayList::new);
    }

    /**
     * @see OAuthFlow#addScope(OAuthScope oAuthScope)
     */
    @Override
    public OAuthFlow addScope(final OAuthScope oAuthScope) {
        this.scopes = ModelUtil.add(oAuthScope, this.scopes, ArrayList::new);
        return this;
    }

    /**
     * @see OAuthFlow#removeScope(OAuthScope oAuthScope)
     */
    @Override
    public void removeScope(final OAuthScope oAuthScope) {
        ModelUtil.remove(this.scopes, oAuthScope);
    }

    @Override
    public String toString() {
        return "OAuthFlowImpl{" + "authorizationUrl='" + authorizationUrl + '\'' + ", tokenUrl='" + tokenUrl + '\''
                + ", refreshUrl='" + refreshUrl + '\''
                + ", scopes=" + scopes + '}';
    }
}
