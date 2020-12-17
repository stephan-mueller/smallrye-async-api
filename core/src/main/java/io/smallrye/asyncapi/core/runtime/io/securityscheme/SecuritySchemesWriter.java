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

import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.databind.node.ObjectNode;

import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.io.components.ComponentsConstant;
import io.smallrye.asyncapi.spec.models.security.SecurityScheme;

public class SecuritySchemesWriter {

    public SecuritySchemesWriter() {
    }

    public static void writeSecuritySchemes(ObjectNode parent, Map<String, SecurityScheme> securitySchemes) {
        if (securitySchemes == null) {
            return;
        }
        ObjectNode node = parent.putObject(ComponentsConstant.PROP_SECURITY_SCHEMES);

        Set<Map.Entry<String, SecurityScheme>> entrySet = securitySchemes.entrySet();
        for (Map.Entry<String, SecurityScheme> entry : entrySet) {
            writeSecuritySchemeToNode(node, entry.getValue());
        }
    }

    private static void writeSecuritySchemeToNode(ObjectNode parent, SecurityScheme model) {
        if (model == null) {
            return;
        }
        ObjectNode node;
        if (model.getName() != null && model.getName() != "") {
            node = parent.putObject(model.getName());
        } else {
            node = parent.putObject(model.getType().toString());
        }

        if (model.getType() != null) {
            JsonUtil.stringProperty(node, SecuritySchemesConstant.PROP_TYPE, model.getType().toString());
        }
        JsonUtil.stringProperty(node, SecuritySchemesConstant.PROP_DESCRIPTION, model.getDescription());
        if (model.getIn() != null && model.getIn().toString() != "") {
            JsonUtil.stringProperty(node, SecuritySchemesConstant.PROP_IN, model.getIn().toString());
        }
        JsonUtil.stringProperty(node, SecuritySchemesConstant.PROP_SCHEME, model.getScheme());
        JsonUtil.stringProperty(node, SecuritySchemesConstant.PROP_BEARER_FORMAT, model.getBearerFormat());
        JsonUtil.stringProperty(node, SecuritySchemesConstant.PROP_OPENID_CONNECT_URL, model.getOpenIdConnectUrl());
        OAuthFlowWriter.writeOAuthFlows(node, model.getFlows());
    }
}
