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
package io.smallrye.asyncapi.core.runtime.io.securityscheme;

import com.fasterxml.jackson.databind.node.ObjectNode;

import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.spec.models.security.OAuthFlow;
import io.smallrye.asyncapi.spec.models.security.OAuthFlows;

public class OAuthFlowWriter {

    public OAuthFlowWriter() {
    }

    public static void writeOAuthFlows(ObjectNode parent, OAuthFlows model) {
        if (model == null) {
            return;
        }

        ObjectNode node = parent.putObject(SecuritySchemesConstant.PROP_FLOWS);

        writeOAuthFlowToNode(node, SecuritySchemesConstant.PROP_IMPLICIT, model.getImplicit());
        writeOAuthFlowToNode(node, SecuritySchemesConstant.PROP_PASSWORD, model.getPassword());
        writeOAuthFlowToNode(node, SecuritySchemesConstant.PROP_CLIENT_CREDENTIALS, model.getClientCredentials());
        writeOAuthFlowToNode(node, SecuritySchemesConstant.PROP_AUTHORIZATION_CODE, model.getAuthorizationCode());
    }

    private static void writeOAuthFlowToNode(final ObjectNode parent, final String attribute, final OAuthFlow model) {
        if (model == null) {
            return;
        }

        ObjectNode node = parent.putObject(attribute);

        JsonUtil.stringProperty(node, SecuritySchemesConstant.PROP_AUTHORIZATION_URL, model.getAuthorizationUrl());
        JsonUtil.stringProperty(node, SecuritySchemesConstant.PROP_TOKEN_URL, model.getTokenUrl());
        JsonUtil.stringProperty(node, SecuritySchemesConstant.PROP_REFRESH_URL, model.getRefreshUrl());
        OAuthScopeWriter.writeOAuthScopes(node, model.getScopes());
    }
}
