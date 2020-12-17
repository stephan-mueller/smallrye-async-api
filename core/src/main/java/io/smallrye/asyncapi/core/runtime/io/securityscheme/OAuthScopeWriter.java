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

import java.util.List;

import com.fasterxml.jackson.databind.node.ObjectNode;

import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.spec.models.security.OAuthScope;

public class OAuthScopeWriter {

    public OAuthScopeWriter() {
    }

    public static void writeOAuthScopes(ObjectNode parent, List<OAuthScope> model) {
        if (parent == null || model == null) {
            return;
        }

        ObjectNode node = parent.putObject(SecuritySchemesConstant.PROP_SCOPES);
        for (OAuthScope scope : model) {
            writeOAuthScopeToNode(node, scope);
        }
    }

    private static void writeOAuthScopeToNode(final ObjectNode node, final OAuthScope model) {
        if (node == null || model == null) {
            return;
        }

        JsonUtil.stringProperty(node, model.getName(), model.getDescription());
    }
}
