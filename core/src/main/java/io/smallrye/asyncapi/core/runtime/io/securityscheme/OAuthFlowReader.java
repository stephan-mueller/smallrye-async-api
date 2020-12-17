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

import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.AnnotationValue;

import com.fasterxml.jackson.databind.JsonNode;

import io.smallrye.asyncapi.core.api.models.security.OAuthFlowImpl;
import io.smallrye.asyncapi.core.api.models.security.OAuthFlowsImpl;
import io.smallrye.asyncapi.core.runtime.io.IoLogging;
import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.scanner.spi.AnnotationScannerContext;
import io.smallrye.asyncapi.core.runtime.util.JandexUtil;
import io.smallrye.asyncapi.spec.models.security.OAuthFlow;
import io.smallrye.asyncapi.spec.models.security.OAuthFlows;

public class OAuthFlowReader {

    public OAuthFlowReader() {
    }

    public static OAuthFlows readOAuthFlows(final AnnotationScannerContext context, final AnnotationValue annotationValue) {
        if (annotationValue == null) {
            return null;
        }

        IoLogging.logger.annotation("@OAuthFlows");
        AnnotationInstance nested = annotationValue.asNested();

        OAuthFlows authFlows = new OAuthFlowsImpl();
        authFlows.setImplicit(readOAuthFlow(context, nested.value(SecuritySchemesConstant.PROP_IMPLICIT)));
        authFlows.setPassword(readOAuthFlow(context, nested.value(SecuritySchemesConstant.PROP_PASSWORD)));
        authFlows.setAuthorizationCode(readOAuthFlow(context, nested.value(SecuritySchemesConstant.PROP_AUTHORIZATION_CODE)));
        authFlows.setClientCredentials(readOAuthFlow(context, nested.value(SecuritySchemesConstant.PROP_CLIENT_CREDENTIALS)));

        return authFlows;
    }

    public static OAuthFlow readOAuthFlow(final AnnotationScannerContext context, final AnnotationValue annotationValue) {
        if (annotationValue == null) {
            return null;
        }

        IoLogging.logger.annotation("@OAuthFlow");
        AnnotationInstance nested = annotationValue.asNested();

        OAuthFlow authFlow = new OAuthFlowImpl();
        authFlow.setAuthorizationUrl(JandexUtil.stringValue(nested, SecuritySchemesConstant.PROP_AUTHORIZATION_URL));
        authFlow.setTokenUrl(JandexUtil.stringValue(nested, SecuritySchemesConstant.PROP_TOKEN_URL));
        authFlow.setRefreshUrl(JandexUtil.stringValue(nested, SecuritySchemesConstant.PROP_REFRESH_URL));
        authFlow.setScopes(
                OAuthScopeReader.readScopes(context, nested.value(SecuritySchemesConstant.PROP_SCOPES)).orElse(null));

        return authFlow;
    }

    public static OAuthFlows readOAuthFlows(final JsonNode node) {
        if (node == null) {
            return null;
        }

        IoLogging.logger.annotation("OAuthFlows");

        OAuthFlows authFlows = new OAuthFlowsImpl();
        authFlows.setImplicit(readOAuthFlow(node.get(SecuritySchemesConstant.PROP_IMPLICIT)));
        authFlows.setPassword(readOAuthFlow(node.get(SecuritySchemesConstant.PROP_PASSWORD)));
        authFlows.setAuthorizationCode(readOAuthFlow(node.get(SecuritySchemesConstant.PROP_AUTHORIZATION_CODE)));
        authFlows.setClientCredentials(readOAuthFlow(node.get(SecuritySchemesConstant.PROP_CLIENT_CREDENTIALS)));

        return authFlows;
    }

    public static OAuthFlow readOAuthFlow(final JsonNode node) {
        if (node == null) {
            return null;
        }

        IoLogging.logger.annotation("OAuthFlow");
        OAuthFlow authFlow = new OAuthFlowImpl();
        authFlow.setAuthorizationUrl(JsonUtil.stringProperty(node, SecuritySchemesConstant.PROP_AUTHORIZATION_URL));
        authFlow.setTokenUrl(JsonUtil.stringProperty(node, SecuritySchemesConstant.PROP_TOKEN_URL));
        authFlow.setRefreshUrl(JsonUtil.stringProperty(node, SecuritySchemesConstant.PROP_REFRESH_URL));
        authFlow.setScopes(OAuthScopeReader.readScopes(node.get(SecuritySchemesConstant.PROP_SCOPES)).orElse(null));

        return authFlow;
    }
}
