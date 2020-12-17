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
package io.smallrye.asyncapi.core.runtime.io.parameter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.AnnotationValue;
import org.jboss.jandex.MethodInfo;

import com.fasterxml.jackson.databind.JsonNode;

import io.smallrye.asyncapi.core.api.models.parameter.ParameterImpl;
import io.smallrye.asyncapi.core.api.models.parameter.ParametersImpl;
import io.smallrye.asyncapi.core.runtime.io.IoLogging;
import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.io.schema.SchemaFactory;
import io.smallrye.asyncapi.core.runtime.io.schema.SchemaReader;
import io.smallrye.asyncapi.core.runtime.scanner.spi.AnnotationScannerContext;
import io.smallrye.asyncapi.core.runtime.util.JandexUtil;
import io.smallrye.asyncapi.spec.models.parameter.Parameter;
import io.smallrye.asyncapi.spec.models.parameter.Parameters;

public class ParameterReader {

    public ParameterReader() {
    }

    /**
     * Reads a map of Parameter annotations.
     *
     * @param context the scanning context
     * @param annotationValue Map of {@literal @}Parameter annotations
     * @return List of Parameter model
     */
    public static Parameters readParametersList(final AnnotationScannerContext context, final AnnotationValue annotationValue) {
        if (annotationValue == null) {
            return null;
        }

        AnnotationValue value = annotationValue.asNested()
                .value(ParameterConstant.PROP_VALUE);

        IoLogging.logger.annotationsList("@Parameter");

        Parameters parameters = new ParametersImpl();
        AnnotationInstance[] nestedArray = value.asNestedArray();
        for (AnnotationInstance nested : nestedArray) {
            Parameter parameter = readParameter(context, nested);
            if (parameter != null) {
                parameters.addParameter(parameter);
            }
        }

        return parameters;
    }

    public static Parameter readParameter(final AnnotationScannerContext context, final AnnotationInstance annotationInstance) {
        if (annotationInstance == null) {
            return null;
        }

        IoLogging.logger.singleAnnotation("@Parameter");

        Parameter parameter = new ParameterImpl();
        parameter.setName(JandexUtil.stringValue(annotationInstance, ParameterConstant.PROP_NAME));
        parameter.setDescription(JandexUtil.stringValue(annotationInstance, ParameterConstant.PROP_DESCRIPTION));
        parameter.setSchema(SchemaFactory.readSchema(context, annotationInstance.value(ParameterConstant.PROP_SCHEMA)));
        parameter.setLocation(JandexUtil.stringValue(annotationInstance, ParameterConstant.PROP_LOCATION));
        parameter.setRef(JandexUtil.refValue(annotationInstance, JandexUtil.RefType.PARAMETER));

        return parameter;
    }

    public static Optional<Map<String, Parameter>> readParametersMap(final JsonNode node) {
        if (node == null) {
            return Optional.empty();
        }

        IoLogging.logger.jsonMap("Parameters");

        Map<String, Parameter> parameters = new HashMap<>();
        for (Iterator<String> fieldNames = node.fieldNames(); fieldNames.hasNext();) {
            String fieldName = fieldNames.next();
            JsonNode varNode = node.get(fieldName);
            Parameter parameter = readParameter(varNode);
            parameters.put(parameter.getName(), parameter);
        }

        return Optional.of(parameters);
    }

    /**
     * Reads the {@link Parameter} OpenAPI nodes.
     *
     * @param node json map of Parameters
     * @return Map of Parameter model
     */
    public static Parameters readParametersList(final JsonNode node) {
        if (node == null) {
            return null;
        }

        IoLogging.logger.jsonMap("Parameters");

        Parameters parameters = new ParametersImpl();
        for (Iterator<String> fieldNames = node.fieldNames(); fieldNames.hasNext();) {
            String fieldName = fieldNames.next();
            JsonNode varNode = node.get(fieldName);
            parameters.addParameter(readParameter(varNode));
        }

        return parameters;
    }

    private static Parameter readParameter(final JsonNode node) {
        if (node == null) {
            return null;
        }

        IoLogging.logger.singleAnnotation("Parameter");

        Parameter parameter = new ParameterImpl();

        if (node.isTextual()) {
            parameter.setRef(node.asText());
            return parameter;
        }

        parameter.setName(JsonUtil.stringProperty(node, ParameterConstant.PROP_NAME));
        parameter.setDescription(JsonUtil.stringProperty(node, ParameterConstant.PROP_DESCRIPTION));
        parameter.setSchema(SchemaReader.readSchema(node.get(ParameterConstant.PROP_SCHEMA)));
        parameter.setLocation(JsonUtil.stringProperty(node, ParameterConstant.PROP_LOCATION));

        return parameter;
    }

    public static AnnotationInstance getParametersAnnotation(final MethodInfo method) {
        return method.annotation(ParameterConstant.DOTNAME_PARAMETER);
    }
}
