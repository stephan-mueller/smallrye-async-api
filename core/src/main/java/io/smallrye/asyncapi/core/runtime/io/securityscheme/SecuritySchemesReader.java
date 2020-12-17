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

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.AnnotationValue;
import org.jboss.jandex.ClassInfo;

import com.fasterxml.jackson.databind.JsonNode;

import io.smallrye.asyncapi.core.api.models.security.SecuritySchemeImpl;
import io.smallrye.asyncapi.core.runtime.io.IoLogging;
import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.scanner.spi.AnnotationScannerContext;
import io.smallrye.asyncapi.core.runtime.util.JandexUtil;
import io.smallrye.asyncapi.spec.annotations.security.SecuritySchemeIn;
import io.smallrye.asyncapi.spec.annotations.security.SecuritySchemeType;
import io.smallrye.asyncapi.spec.models.security.SecurityScheme;

public class SecuritySchemesReader {

    public SecuritySchemesReader() {
    }

    public static AnnotationInstance getSecuritySchemesAnnotation(final ClassInfo classInfo) {
        return JandexUtil.getClassAnnotation(classInfo, SecuritySchemesConstant.DOTNAME_SECURITY_SCHEMES);
    }

    public static Optional<Map<String, SecurityScheme>> readSecuritySchemes(final AnnotationScannerContext context,
            final AnnotationValue annotationValue) {

        if (annotationValue == null) {
            return Optional.empty();
        }
        IoLogging.logger.annotation("@SecuritySchemes");

        AnnotationInstance[] nestedArray = annotationValue.asNestedArray();

        HashMap<String, SecurityScheme> schemes = new HashMap<>();
        for (AnnotationInstance schemeAnno : nestedArray) {
            SecurityScheme scheme = readSecurityScheme(context, schemeAnno);
            schemes.put(scheme.getType()
                    .toString(), scheme);
        }
        return Optional.of(schemes);
    }

    private static SecurityScheme readSecurityScheme(final AnnotationScannerContext context,
            final AnnotationInstance annotationInstance) {
        if (annotationInstance == null) {
            return null;
        }
        IoLogging.logger.singleAnnotation("@SecurityScheme");

        SecuritySchemeImpl securityScheme = new SecuritySchemeImpl();
        securityScheme
                .setType(JandexUtil.enumValue(annotationInstance, SecuritySchemesConstant.PROP_TYPE, SecuritySchemeType.class));
        securityScheme.setDescription(JandexUtil.stringValue(annotationInstance, SecuritySchemesConstant.PROP_DESCRIPTION));
        securityScheme.setName(JandexUtil.stringValue(annotationInstance, SecuritySchemesConstant.PROP_NAME));
        securityScheme.setIn(JandexUtil.enumValue(annotationInstance, SecuritySchemesConstant.PROP_IN, SecuritySchemeIn.class));
        securityScheme.setScheme(JandexUtil.stringValue(annotationInstance, SecuritySchemesConstant.PROP_SCHEME));
        securityScheme.setBearerFormat(JandexUtil.stringValue(annotationInstance, SecuritySchemesConstant.PROP_BEARER_FORMAT));
        securityScheme.setFlows(
                OAuthFlowReader.readOAuthFlows(context, annotationInstance.value(SecuritySchemesConstant.PROP_FLOWS)));
        securityScheme.setOpenIdConnectUrl(
                JandexUtil.stringValue(annotationInstance, SecuritySchemesConstant.PROP_OPENID_CONNECT_URL));

        return securityScheme;
    }

    public static Optional<Map<String, SecurityScheme>> readSecuritySchemes(final JsonNode node) {
        if (node == null) {
            return Optional.empty();
        }

        IoLogging.logger.annotation("SecuritySchemes");

        HashMap<String, SecurityScheme> schemes = new HashMap<>();
        for (Iterator<String> iterator = node.fieldNames(); iterator.hasNext();) {
            String fieldName = iterator.next();
            JsonNode schemeNode = node.get(fieldName);
            SecurityScheme scheme = readSecurityScheme(schemeNode);
            if (scheme.getType() != null) {
                schemes.put(fieldName, scheme);
            }
        }

        return Optional.of(schemes);
    }

    private static SecurityScheme readSecurityScheme(final JsonNode node) {
        if (node == null || !node.isObject()) {
            return null;
        }
        IoLogging.logger.singleJsonNode("SecurityScheme");

        SecuritySchemeImpl securityScheme = new SecuritySchemeImpl();
        securityScheme.setType(readSecuritySchemeType(node.get(SecuritySchemesConstant.PROP_TYPE)));
        securityScheme.setDescription(JsonUtil.stringProperty(node, SecuritySchemesConstant.PROP_DESCRIPTION));
        securityScheme.setName(JsonUtil.stringProperty(node, SecuritySchemesConstant.PROP_NAME));
        securityScheme.setIn(readSecuritySchemeIn(node.get(SecuritySchemesConstant.PROP_IN)));
        securityScheme.setScheme(JsonUtil.stringProperty(node, SecuritySchemesConstant.PROP_SCHEME));
        securityScheme.setBearerFormat(JsonUtil.stringProperty(node, SecuritySchemesConstant.PROP_BEARER_FORMAT));
        securityScheme.setFlows(OAuthFlowReader.readOAuthFlows(node.get(SecuritySchemesConstant.PROP_FLOWS)));
        securityScheme.setOpenIdConnectUrl(JsonUtil.stringProperty(node, SecuritySchemesConstant.PROP_OPENID_CONNECT_URL));

        return securityScheme;
    }

    private static SecuritySchemeType readSecuritySchemeType(final JsonNode node) {
        if (node == null || !node.isTextual()) {
            return null;
        }
        return SECURITY_SCHEME_TYPE_LOOKUP.get(node.asText());
    }

    private static SecuritySchemeIn readSecuritySchemeIn(final JsonNode node) {
        if (node == null || !node.isTextual()) {
            return null;
        }
        return SECURITY_SCHEME_IN_LOOKUP.get(node.asText());
    }

    private static final Map<String, SecuritySchemeType> SECURITY_SCHEME_TYPE_LOOKUP = new LinkedHashMap<>();

    private static final Map<String, SecuritySchemeIn> SECURITY_SCHEME_IN_LOOKUP = new LinkedHashMap<>();

    static {
        SecuritySchemeType[] typeValues = SecuritySchemeType.values();
        for (SecuritySchemeType type : typeValues) {
            SECURITY_SCHEME_TYPE_LOOKUP.put(type.toString(), type);
        }

        SecuritySchemeIn[] inValues = SecuritySchemeIn.values();
        for (SecuritySchemeIn in : inValues) {
            SECURITY_SCHEME_IN_LOOKUP.put(in.toString(), in);
        }
    }
}
