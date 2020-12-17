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

import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.databind.node.ObjectNode;

import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.io.bindings.ChannelBindingsWriter;
import io.smallrye.asyncapi.core.runtime.io.definition.DefinitionConstant;
import io.smallrye.asyncapi.core.runtime.io.operation.OperationWriter;
import io.smallrye.asyncapi.core.runtime.io.parameter.ParameterWriter;
import io.smallrye.asyncapi.spec.models.channel.ChannelItem;
import io.smallrye.asyncapi.spec.models.channel.Channels;
import io.smallrye.asyncapi.spec.models.info.Contact;

public class ChannelsWriter {

    public ChannelsWriter() {
    }

    /**
     * Writes the {@link Contact} model to the JSON tree.
     *
     * @param node the parent json node
     * @param channels the Channels model
     */
    public static void writeChannel(ObjectNode node, Channels channels) {
        if (channels == null || channels.getChannels() == null) {
            return;
        }
        ObjectNode variablesNode = node.putObject(DefinitionConstant.PROP_CHANNELS);

        Set<Map.Entry<String, ChannelItem>> entrySet = channels.getChannels().entrySet();
        for (Map.Entry<String, ChannelItem> entry : entrySet) {
            writeChannelToNode(variablesNode, entry.getValue());
        }
    }

    private static void writeChannelToNode(ObjectNode parent, ChannelItem model) {
        if (model == null) {
            return;
        }

        ObjectNode node = parent.putObject(model.getChannel());
        JsonUtil.stringProperty(node, ChannelsConstants.PROP_CHANNEL, model.getChannel());
        JsonUtil.stringProperty(node, ChannelsConstants.PROP_DESCRIPTION, model.getDescription());
        OperationWriter.writePublish(node, model.getPublish());
        OperationWriter.writeSubscribe(node, model.getSubscribe());
        ParameterWriter.writeParameters(node, model.getParameters());
        ChannelBindingsWriter.writeChannelBindings(node, model.getBindings());
    }
}
