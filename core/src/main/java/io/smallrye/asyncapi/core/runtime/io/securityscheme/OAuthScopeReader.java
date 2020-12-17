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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.AnnotationValue;

import com.fasterxml.jackson.databind.JsonNode;

import io.smallrye.asyncapi.core.api.models.security.OAuthScopeImpl;
import io.smallrye.asyncapi.core.runtime.io.IoLogging;
import io.smallrye.asyncapi.core.runtime.io.extension.ExtensionConstant;
import io.smallrye.asyncapi.core.runtime.scanner.spi.AnnotationScannerContext;
import io.smallrye.asyncapi.core.runtime.util.JandexUtil;
import io.smallrye.asyncapi.spec.models.security.OAuthScope;

public class OAuthScopeReader {

    public OAuthScopeReader() {
    }

    public static Optional<List<OAuthScope>> readScopes(final AnnotationScannerContext context,
            final AnnotationValue annotationValue) {

        if (annotationValue == null) {
            return Optional.empty();
        }
        IoLogging.logger.annotation("@OAuthScopes");

        AnnotationInstance[] nestedArray = annotationValue.asNestedArray();

        ArrayList<OAuthScope> scopes = new ArrayList<>();
        for (AnnotationInstance scopeAnno : nestedArray) {
            scopes.add(readScope(context, scopeAnno));
        }
        return Optional.of(scopes);
    }

    private static OAuthScope readScope(final AnnotationScannerContext context, final AnnotationInstance annotationInstance) {
        if (annotationInstance == null) {
            return null;
        }
        IoLogging.logger.singleAnnotation("@OAuthScope");

        OAuthScope authScope = new OAuthScopeImpl();
        authScope.setName(JandexUtil.stringValue(annotationInstance, SecuritySchemesConstant.PROP_NAME));
        authScope.setDescription(JandexUtil.stringValue(annotationInstance, SecuritySchemesConstant.PROP_DESCRIPTION));

        return authScope;
    }

    public static Optional<List<OAuthScope>> readScopes(final JsonNode node) {
        if (node == null) {
            return Optional.empty();
        }

        IoLogging.logger.annotation("OAuthScopes");

        ArrayList<OAuthScope> scopes = new ArrayList<>();
        for (Iterator<String> iterator = node.fieldNames(); iterator.hasNext();) {
            String fieldName = iterator.next();
            if (!ExtensionConstant.isExtensionField(fieldName)) {
                JsonNode varNode = node.get(fieldName);
                scopes.add(readScope(fieldName, varNode));
            }
        }

        return Optional.of(scopes);
    }

    private static OAuthScope readScope(final String fieldName, final JsonNode node) {
        if (node == null) {
            return null;
        }

        IoLogging.logger.annotation("OAuthScope");
        OAuthScope authScope = new OAuthScopeImpl();
        authScope.setName(fieldName);
        authScope.setDescription(node.asText());

        return authScope;
    }
}
