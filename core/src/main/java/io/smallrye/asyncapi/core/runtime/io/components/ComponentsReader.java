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
package io.smallrye.asyncapi.core.runtime.io.components;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.AnnotationValue;

import com.fasterxml.jackson.databind.JsonNode;

import io.smallrye.asyncapi.core.api.models.ComponentsImpl;
import io.smallrye.asyncapi.core.runtime.io.IoLogging;
import io.smallrye.asyncapi.core.runtime.io.message.MessageReader;
import io.smallrye.asyncapi.core.runtime.io.parameter.ParameterReader;
import io.smallrye.asyncapi.core.runtime.io.securityscheme.SecuritySchemesReader;
import io.smallrye.asyncapi.core.runtime.scanner.spi.AnnotationScannerContext;
import io.smallrye.asyncapi.spec.models.Components;
import io.smallrye.asyncapi.spec.models.security.SecurityScheme;

public class ComponentsReader {

    public ComponentsReader() {
    }

    /**
     * Annotation to Components
     *
     * @param annotationValue the {@literal @}Components annotation
     * @return Components model
     */
    public static Components readComponents(final AnnotationScannerContext context, final AnnotationValue annotationValue) {
        if (annotationValue == null) {
            return null;
        }
        IoLogging.logger.annotation("@Components");
        AnnotationInstance nested = annotationValue.asNested();

        Components components = new ComponentsImpl();
        components
                .setMessages(MessageReader.readMessages(context, nested.value(ComponentsConstant.PROP_MESSAGES)).orElse(null));
        components.setSecuritySchemes(SecuritySchemesReader
                .readSecuritySchemes(context, nested.value(ComponentsConstant.PROP_SECURITY_SCHEMES)).orElse(null));

        return components;
    }

    public static Components readComponents(JsonNode node) {
        if (node == null) {
            return null;
        }

        IoLogging.logger.singleJsonNode("Components");

        Components components = new ComponentsImpl();
        components.setMessages(MessageReader.readMessages(node.get(ComponentsConstant.PROP_MESSAGES)));
        components.setSecuritySchemes(
                SecuritySchemesReader.readSecuritySchemes(node.get(ComponentsConstant.PROP_SECURITY_SCHEMES)).orElse(null));
        components.setParameters(ParameterReader.readParametersMap(node.get(ComponentsConstant.PROP_PARAMETERS)).orElse(null));

        return components;
    }

    private static Map<String, SecurityScheme> listToMap(final List<SecurityScheme> list) {
        if (list == null) {
            return new HashMap<>();
        }

        HashMap<String, SecurityScheme> map = new HashMap<>();

        list.stream()
                .filter(Objects::nonNull)
                .forEach(o -> map.put(o.getType().toString(), o));

        return map;
    }
}
