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
package io.smallrye.asyncapi.core.runtime.io.bindings.ws;

import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.AnnotationValue;

import com.fasterxml.jackson.databind.JsonNode;

import io.smallrye.asyncapi.core.api.models.binding.ws.WebSocketChannelBindingImpl;
import io.smallrye.asyncapi.core.runtime.io.IoLogging;
import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.io.schema.SchemaFactory;
import io.smallrye.asyncapi.core.runtime.io.schema.SchemaReader;
import io.smallrye.asyncapi.core.runtime.util.JandexUtil;
import io.smallrye.asyncapi.spec.models.binding.ws.WebSocketChannelBinding;

public class WebSocketChannelBindingReader {

    public WebSocketChannelBindingReader() {
    }

    public static WebSocketChannelBinding readChannelBinding(final AnnotationValue annotationValue) {
        if (annotationValue == null) {
            return null;
        }

        IoLogging.logger.singleAnnotation("@WebSocketChannelBinding");

        AnnotationInstance annotationInstance = annotationValue.asNested();

        WebSocketChannelBinding binding = new WebSocketChannelBindingImpl();

        binding.setMethod(JandexUtil.stringValue(annotationInstance, WebSocketChannelBindingConstant.PROP_METHOD));
        binding.setQuery(SchemaFactory.readSchema(null, annotationInstance.value(WebSocketChannelBindingConstant.PROP_QUERY)));
        binding.setHeaders(
                SchemaFactory.readSchema(null, annotationInstance.value(WebSocketChannelBindingConstant.PROP_HEADERS)));
        binding.setBindingVersion(
                JandexUtil.stringValue(annotationInstance, WebSocketChannelBindingConstant.PROP_BINDING_VERSION));

        return binding;
    }

    public static WebSocketChannelBinding readChannelBinding(final JsonNode node) {
        if (node == null) {
            return null;
        }

        IoLogging.logger.singleJsonNode("WebSocketChannelBinding");

        WebSocketChannelBinding binding = new WebSocketChannelBindingImpl();
        binding.setMethod(JsonUtil.stringProperty(node, WebSocketChannelBindingConstant.PROP_METHOD));
        binding.setQuery(SchemaReader.readSchema(node.get(WebSocketChannelBindingConstant.PROP_QUERY)));
        binding.setHeaders(SchemaReader.readSchema(node.get(WebSocketChannelBindingConstant.PROP_HEADERS)));
        binding.setBindingVersion(JsonUtil.stringProperty(node, WebSocketChannelBindingConstant.PROP_BINDING_VERSION));

        return binding;
    }
}
