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
package io.smallrye.asyncapi.core.runtime.io.securityrequirement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.AnnotationValue;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import io.smallrye.asyncapi.core.api.models.security.SecurityRequirementImpl;
import io.smallrye.asyncapi.core.runtime.io.IoLogging;
import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.util.JandexUtil;
import io.smallrye.asyncapi.spec.models.security.SecurityRequirement;

/**
 * Lists the required security schemes to execute this operation.
 * The name used for each property MUST correspond to a security scheme declared in the Security Schemes under the Components
 * Object.
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0/#securityRequirementObject"
 */
public class SecurityRequirementReader {

    private SecurityRequirementReader() {
    }

    /**
     * Reads any SecurityRequirement annotations. The annotation value is an array of
     * SecurityRequirement annotations.
     *
     * @param annotationValue Array of {@literal @}SecurityRequirement annotations
     * @return List of SecurityRequirement models
     */
    public static Optional<List<SecurityRequirement>> readSecurityRequirements(final AnnotationValue annotationValue) {
        if (annotationValue != null) {
            IoLogging.logger.annotationsArray("@SecurityRequirement");
            AnnotationInstance[] nestedArray = annotationValue.asNestedArray();
            List<SecurityRequirement> requirements = new ArrayList<>();
            for (AnnotationInstance requirementAnno : nestedArray) {
                SecurityRequirement requirement = readSecurityRequirement(requirementAnno);
                if (requirement != null) {
                    requirements.add(requirement);
                }
            }
            return Optional.of(requirements);
        }
        return Optional.empty();
    }

    /**
     * Reads a list of {@link SecurityRequirement} OpenAPI nodes.
     *
     * @param node the json array
     * @return List of SecurityRequirement models
     */
    public static Optional<List<SecurityRequirement>> readSecurityRequirements(final JsonNode node) {
        if (node != null && node.isArray()) {
            IoLogging.logger.jsonArray("SecurityRequirement");
            List<SecurityRequirement> requirements = new ArrayList<>(node.size());
            ArrayNode arrayNode = (ArrayNode) node;
            for (JsonNode arrayItem : arrayNode) {
                requirements.add(readSecurityRequirement(arrayItem));
            }
            return Optional.of(requirements);
        }
        return Optional.empty();
    }

    /**
     * Reads a single SecurityRequirement annotation.
     *
     * @param annotationInstance the {@literal @}SecurityRequirement annotation
     * @return SecurityRequirement model
     */
    public static SecurityRequirement readSecurityRequirement(AnnotationInstance annotationInstance) {
        String name = JandexUtil.stringValue(annotationInstance, SecurityRequirementConstant.PROP_NAME);
        if (name != null) {
            Optional<List<String>> maybeScopes = JandexUtil.stringListValue(annotationInstance,
                    SecurityRequirementConstant.PROP_VALUES);
            SecurityRequirement requirement = new SecurityRequirementImpl();
            if (maybeScopes.isPresent()) {
                requirement.addScheme(name, maybeScopes.get());
            } else {
                requirement.addScheme(name);
            }
            return requirement;
        }
        return null;
    }

    private static SecurityRequirement readSecurityRequirement(final JsonNode node) {
        if (node != null && node.isObject()) {

            SecurityRequirement requirement = new SecurityRequirementImpl();
            for (Iterator<String> fieldNames = node.fieldNames(); fieldNames.hasNext();) {
                String fieldName = fieldNames.next();
                JsonNode scopesNode = node.get(fieldName);
                Optional<List<String>> maybeScopes = JsonUtil.readStringArray(scopesNode);
                if (maybeScopes.isPresent()) {
                    requirement.addScheme(fieldName, maybeScopes.get());
                } else {
                    requirement.addScheme(fieldName);
                }
            }
            return requirement;
        }
        return null;
    }

}
