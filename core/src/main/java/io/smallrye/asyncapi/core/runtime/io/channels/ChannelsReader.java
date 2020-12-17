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
package io.smallrye.asyncapi.core.runtime.io.channels;

import java.util.Iterator;

import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.AnnotationValue;
import org.jboss.jandex.MethodInfo;

import com.fasterxml.jackson.databind.JsonNode;

import io.smallrye.asyncapi.core.api.models.channel.ChannelItemImpl;
import io.smallrye.asyncapi.core.api.models.channel.ChannelsImpl;
import io.smallrye.asyncapi.core.runtime.io.IoLogging;
import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.io.bindings.ChannelBindingsReader;
import io.smallrye.asyncapi.core.runtime.io.extension.ExtensionConstant;
import io.smallrye.asyncapi.core.runtime.io.operation.OperationReader;
import io.smallrye.asyncapi.core.runtime.io.parameter.ParameterReader;
import io.smallrye.asyncapi.core.runtime.scanner.spi.AnnotationScannerContext;
import io.smallrye.asyncapi.core.runtime.util.JandexUtil;
import io.smallrye.asyncapi.spec.models.channel.ChannelItem;
import io.smallrye.asyncapi.spec.models.channel.Channels;

/**
 * Reading the ChannelItems annotation and json node
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0#channelsObject"
 */
public class ChannelsReader {

    public ChannelsReader() {
    }

    /**
     * Reads any ChannelItems annotations.The annotation value is an Array of ChannelItems annotations.
     *
     * @param annotationValue an Array of {@literal @}ChannelItems annotations
     * @return a Channels model
     */
    public static Channels readChannels(final AnnotationScannerContext context, final AnnotationValue annotationValue) {
        if (annotationValue != null) {
            IoLogging.logger.annotationsArray("Channels");

            AnnotationInstance[] nestedArray = annotationValue.asNestedArray();

            ChannelsImpl channels = new ChannelsImpl();
            for (AnnotationInstance serverAnno : nestedArray) {
                ChannelItem channelItem = readChannelItems(context, serverAnno);
                channels.addChannel(channelItem.getChannel(), channelItem);
            }
            return channels;
        }

        return null;
    }

    /**
     * Reads a single ChannelItem annotation.
     *
     * @param context the scanning context
     * @param annotationInstance the annotation
     * @return
     */
    public static ChannelItem readChannelItems(final AnnotationScannerContext context,
            final AnnotationInstance annotationInstance) {
        if (annotationInstance == null) {
            return null;
        }

        IoLogging.logger.singleAnnotation("@ChannelItem");

        ChannelItem channelItem = new ChannelItemImpl();
        channelItem.setChannel(JandexUtil.stringValue(annotationInstance, ChannelsConstants.PROP_CHANNEL));
        channelItem.setDescription(JandexUtil.stringValue(annotationInstance, ChannelsConstants.PROP_DESCRIPTION));
        channelItem
                .setPublish(OperationReader.readOperation(context, annotationInstance.value(ChannelsConstants.PROP_PUBLISH)));
        channelItem.setSubscribe(
                OperationReader.readOperation(context, annotationInstance.value(ChannelsConstants.PROP_SUBSCRIBE)));
        channelItem.setParameters(
                ParameterReader.readParametersList(context, annotationInstance.value(ChannelsConstants.PROP_PARAMETERS)));
        channelItem.setBindings(
                ChannelBindingsReader.readChannelBindings(context, annotationInstance.value(ChannelsConstants.PROP_BINDING)));

        return channelItem;
    }

    /**
     * Reads a list of {@link ChannelItem} AsyncAPI nodes.
     *
     * @param node the json array
     * @return a ChannelItem model
     */
    public static Channels readChannelItems(final JsonNode node) {
        if (node == null) {
            return null;
        }

        IoLogging.logger.jsonNodeMap("ChannelItem");

        Channels channels = new ChannelsImpl();
        for (Iterator<String> iterator = node.fieldNames(); iterator.hasNext();) {
            String fieldName = iterator.next();
            if (!ExtensionConstant.isExtensionField(fieldName)) {
                JsonNode varNode = node.get(fieldName);
                channels.addChannel(fieldName, readChannelItem(varNode));
            }
        }

        return channels;
    }

    private static ChannelItem readChannelItem(JsonNode node) {
        if (node == null) {
            return null;
        }

        IoLogging.logger.singleJsonNode("ChannelItem");

        ChannelItem channelItem = new ChannelItemImpl();

        channelItem.setChannel(JsonUtil.stringProperty(node, ChannelsConstants.PROP_CHANNEL));
        channelItem.setDescription(JsonUtil.stringProperty(node, ChannelsConstants.PROP_DESCRIPTION));
        channelItem.setSubscribe(OperationReader.readOperation(node.get(ChannelsConstants.PROP_SUBSCRIBE)));
        channelItem.setPublish(OperationReader.readOperation(node.get(ChannelsConstants.PROP_PUBLISH)));
        channelItem.setParameters(ParameterReader.readParametersList(node.get(ChannelsConstants.PROP_PARAMETERS)));
        channelItem.setBindings(ChannelBindingsReader.readChannelBindings(node.get(ChannelsConstants.PROP_BINDING)));

        return channelItem;
    }

    public static AnnotationInstance getChannelItemAnnotation(final MethodInfo method) {
        return method.annotation(ChannelsConstants.DOTNAME_CHANNEL_ITEM);
    }

}
