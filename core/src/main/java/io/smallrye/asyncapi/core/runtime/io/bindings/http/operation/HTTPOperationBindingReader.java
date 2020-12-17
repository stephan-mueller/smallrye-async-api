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
package io.smallrye.asyncapi.core.runtime.io.bindings.http.operation;

import java.util.LinkedHashMap;
import java.util.Map;

import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.AnnotationValue;

import com.fasterxml.jackson.databind.JsonNode;

import io.smallrye.asyncapi.core.api.models.binding.http.HTTPOperationBindingImpl;
import io.smallrye.asyncapi.core.runtime.io.IoLogging;
import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.io.schema.SchemaFactory;
import io.smallrye.asyncapi.core.runtime.io.schema.SchemaReader;
import io.smallrye.asyncapi.core.runtime.util.JandexUtil;
import io.smallrye.asyncapi.spec.annotations.binding.http.Method;
import io.smallrye.asyncapi.spec.models.binding.http.HTTPOperationBinding;

public class HTTPOperationBindingReader {

    public static HTTPOperationBinding readOperationBinding(final AnnotationValue annotationValue) {
        if (annotationValue == null) {
            return null;
        }

        IoLogging.logger.singleAnnotation("@HTTPOperationBinding");
        AnnotationInstance annotationInstance = annotationValue.asNested();

        HTTPOperationBinding binding = new HTTPOperationBindingImpl();
        binding.setType(JandexUtil.stringValue(annotationInstance, HTTPOperationBindingConstant.PROP_TYPE));
        binding.setMethod(JandexUtil.enumValue(annotationInstance, HTTPOperationBindingConstant.PROP_METHOD, Method.class));
        binding.setQuery(SchemaFactory.readSchema(null, annotationInstance.value(HTTPOperationBindingConstant.PROP_QUERY)));
        binding.setBindingVersion(
                JandexUtil.stringValue(annotationInstance, HTTPOperationBindingConstant.PROP_BINDING_VERSION));

        return binding;
    }

    public static HTTPOperationBinding readOperationBinding(final JsonNode node) {
        if (node == null) {
            return null;
        }

        IoLogging.logger.singleJsonNode("HTTPOperationBinding");

        HTTPOperationBinding binding = new HTTPOperationBindingImpl();
        binding.setType(JsonUtil.stringProperty(node, HTTPOperationBindingConstant.PROP_TYPE));
        binding.setMethod(readMethod(node.get(HTTPOperationBindingConstant.PROP_METHOD)));
        binding.setQuery(SchemaReader.readSchema(node.get(HTTPOperationBindingConstant.PROP_QUERY)));
        binding.setBindingVersion(JsonUtil.stringProperty(node, HTTPOperationBindingConstant.PROP_BINDING_VERSION));

        return binding;
    }

    private static Method readMethod(final JsonNode node) {
        if (node == null || !node.isTextual()) {
            return null;
        }
        return METHOD_LOOKUP.get(node.asText());
    }

    private static final Map<String, Method> METHOD_LOOKUP = new LinkedHashMap<>();

    static {
        Method[] methodValues = Method.values();
        for (Method type : methodValues) {
            METHOD_LOOKUP.put(type.toString(), type);
        }
    }
}
