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
package io.smallrye.asyncapi.core.runtime.scanner.spi;

import java.util.HashMap;
import java.util.Map;

import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.ClassInfo;
import org.jboss.jandex.MethodInfo;

import io.smallrye.asyncapi.core.runtime.io.channels.ChannelsReader;
import io.smallrye.asyncapi.core.runtime.io.definition.DefinitionReader;
import io.smallrye.asyncapi.core.runtime.io.message.MessageReader;
import io.smallrye.asyncapi.core.runtime.io.parameter.ParameterReader;
import io.smallrye.asyncapi.core.runtime.io.securityscheme.SecuritySchemesConstant;
import io.smallrye.asyncapi.core.runtime.io.securityscheme.SecuritySchemesReader;
import io.smallrye.asyncapi.core.runtime.util.ModelUtil;
import io.smallrye.asyncapi.spec.models.AsyncAPI;
import io.smallrye.asyncapi.spec.models.Components;
import io.smallrye.asyncapi.spec.models.channel.ChannelItem;
import io.smallrye.asyncapi.spec.models.channel.Channels;
import io.smallrye.asyncapi.spec.models.message.Message;
import io.smallrye.asyncapi.spec.models.parameter.Parameter;
import io.smallrye.asyncapi.spec.models.security.SecurityScheme;

public interface AnnotationScanner {

    String getName();

    // Scan using this scanner
    AsyncAPI scan(final AnnotationScannerContext annotationScannerContext, AsyncAPI aai);

    void setContextRoot(String path);

    /**
     * Process a certain class for AsyncAPI annotations.
     *
     * @param context the scanning context
     * @param targetClass the class that contain the server annotation
     * @param asyncAPI the current AsyncApi model being created
     */
    default void processDefinitionAnnotation(final AnnotationScannerContext context, final ClassInfo targetClass,
            AsyncAPI asyncAPI) {
        AnnotationInstance definitionAnnotation = DefinitionReader.getDefinitionAnnotation(targetClass);
        if (definitionAnnotation != null) {
            DefinitionReader.processDefinition(context, asyncAPI, definitionAnnotation);
        }
    }

    default void processChannelItem(final AnnotationScannerContext context, final MethodInfo method, Channels channels) {
        AnnotationInstance channelItemAnnotation = ChannelsReader.getChannelItemAnnotation(method);

        ChannelItem channelItem = ChannelsReader.readChannelItems(context, channelItemAnnotation);
        channels.addChannel(channelItem.getChannel(), channelItem);
    }

    default void processMessages(final AnnotationScannerContext context, final MethodInfo method, AsyncAPI asyncAPI) {
        AnnotationInstance messageAnnotation = MessageReader.getMessageAnnotation(method);

        Message message = MessageReader.readMessage(context, messageAnnotation);
        Components components = ModelUtil.components(asyncAPI);
        components.addMessage(message.getName(), message);
    }

    default void processParameters(final AnnotationScannerContext context, final MethodInfo method, AsyncAPI asyncAPI) {
        AnnotationInstance parametersAnnotation = ParameterReader.getParametersAnnotation(method);

        Parameter parameter = ParameterReader.readParameter(context, parametersAnnotation);
        Components components = ModelUtil.components(asyncAPI);
        components.addParameter(parameter.getName(), parameter);
    }

    default void processSecuritySchemes(final AnnotationScannerContext context, final ClassInfo classInfo, AsyncAPI asyncAPI) {
        AnnotationInstance securitySchemesAnnotation = SecuritySchemesReader.getSecuritySchemesAnnotation(classInfo);

        Map<String, SecurityScheme> securitySchemes = SecuritySchemesReader
                .readSecuritySchemes(context, securitySchemesAnnotation.value(SecuritySchemesConstant.PROP_VALUES))
                .orElse(new HashMap<>());

        Components components = ModelUtil.components(asyncAPI);
        securitySchemes.entrySet()
                .forEach(securityScheme -> components.addSecurityScheme(securityScheme.getKey(), securityScheme.getValue()));
    }
}
