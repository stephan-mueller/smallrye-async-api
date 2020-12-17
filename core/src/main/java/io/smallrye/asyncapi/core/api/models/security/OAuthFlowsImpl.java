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
import io.smallrye.asyncapi.spec.models.security.OAuthFlow;
import io.smallrye.asyncapi.spec.models.security.OAuthFlows;

/**
 * An implementation of the {@link OAuthFlows} AsyncAPI model interface.
 */
public class OAuthFlowsImpl extends ExtensibleImpl<OAuthFlows> implements OAuthFlows, ModelImpl {

    private OAuthFlow implicit;

    private OAuthFlow password;

    private OAuthFlow clientCredentials;

    private OAuthFlow authorizationCode;

    /**
     * @see OAuthFlows#getImplicit()
     */
    @Override
    public OAuthFlow getImplicit() {
        return this.implicit;
    }

    /**
     * @see OAuthFlows#setImplicit(OAuthFlow oAuthFlow)
     */
    @Override
    public void setImplicit(final OAuthFlow implicit) {
        this.implicit = implicit;
    }

    /**
     * @see OAuthFlows#getPassword()
     */
    @Override
    public OAuthFlow getPassword() {
        return this.password;
    }

    /**
     * @see OAuthFlows#setPassword(OAuthFlow password)
     */
    @Override
    public void setPassword(final OAuthFlow password) {
        this.password = password;
    }

    /**
     * @see OAuthFlows#getClientCredentials()
     */
    @Override
    public OAuthFlow getClientCredentials() {
        return this.clientCredentials;
    }

    /**
     * @see OAuthFlows#setClientCredentials(OAuthFlow clientCredentials)
     */
    @Override
    public void setClientCredentials(final OAuthFlow clientCredentials) {
        this.clientCredentials = clientCredentials;
    }

    /**
     * @see OAuthFlows#getAuthorizationCode()
     */
    @Override
    public OAuthFlow getAuthorizationCode() {
        return this.authorizationCode;
    }

    /**
     * @see OAuthFlows#setAuthorizationCode(OAuthFlow authorizationCode)
     */
    @Override
    public void setAuthorizationCode(final OAuthFlow authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    @Override
    public String toString() {
        return "OAuthFlowsImpl{" + "implicit=" + implicit + ", password=" + password + ", clientCredentials="
                + clientCredentials + ", authorizationCode="
                + authorizationCode + '}';
    }
}
