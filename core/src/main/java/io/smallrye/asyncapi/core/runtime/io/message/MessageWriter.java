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
package io.smallrye.asyncapi.core.runtime.io.message;

import java.util.Map;

import com.fasterxml.jackson.databind.node.ObjectNode;

import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.io.Referenceable;
import io.smallrye.asyncapi.core.runtime.io.bindings.MessageBindingsWriter;
import io.smallrye.asyncapi.core.runtime.io.components.ComponentsConstant;
import io.smallrye.asyncapi.core.runtime.io.correlationId.CorrelationIdWriter;
import io.smallrye.asyncapi.core.runtime.io.extension.ExtensionWriter;
import io.smallrye.asyncapi.core.runtime.io.operation.OperationConstant;
import io.smallrye.asyncapi.core.runtime.io.schema.SchemaWriter;
import io.smallrye.asyncapi.core.runtime.io.tag.TagWriter;
import io.smallrye.asyncapi.core.runtime.util.StringUtil;
import io.smallrye.asyncapi.spec.models.message.Message;

public class MessageWriter {

    public MessageWriter() {
    }

    public static void writeMessages(ObjectNode parent, Map<String, Message> messages) {
        if (messages == null || messages.size() == 0) {
            return;
        }

        ObjectNode node = parent.putObject(ComponentsConstant.PROP_MESSAGES);

        for (Map.Entry<String, Message> entry : messages.entrySet()) {
            writeMessageToNode(node, entry.getValue());
        }
    }

    /**
     * Writes the {@link Message} model to the JSON tree.
     *
     * @param parent the parent json node
     * @param model the Message model
     */
    private static void writeMessageToNode(ObjectNode parent, Message model) {
        if (model == null) {
            return;
        }
        ObjectNode node = parent.putObject(model.getName());

        if (StringUtil.isNotEmpty(model.getRef())) {
            JsonUtil.stringProperty(node, Referenceable.PROP_$REF, model.getRef());
            return;
        }
        fillMessage(model, node);
    }

    public static void writeMessage(ObjectNode parent, Message model) {
        if (model == null) {
            return;
        }

        ObjectNode node = parent.putObject(OperationConstant.PROP_MESSAGE);

        if (StringUtil.isNotEmpty(model.getRef())) {
            JsonUtil.stringProperty(node, Referenceable.PROP_$REF, model.getRef());
            return;
        }
        fillMessage(model, node);
    }

    private static void fillMessage(final Message model, final ObjectNode node) {
        SchemaWriter.writeSchema(node, model.getHeaders(), MessageConstant.PROP_HEADERS);
        SchemaWriter.writeSchema(node, model.getPayload(), MessageConstant.PROP_PAYLOAD);
        CorrelationIdWriter.writeCorrelationID(node, model.getCorrelationID());
        JsonUtil.stringProperty(node, MessageConstant.PROP_SCHEME_FORMAT, model.getSchemaFormat());
        JsonUtil.stringProperty(node, MessageConstant.PROP_CONTENT_TYPE, model.getContentType());
        JsonUtil.stringProperty(node, MessageConstant.PROP_NAME, model.getName());
        JsonUtil.stringProperty(node, MessageConstant.PROP_TITLE, model.getTitle());
        JsonUtil.stringProperty(node, MessageConstant.PROP_SUMMARY, model.getSummary());
        JsonUtil.stringProperty(node, MessageConstant.PROP_DESCRIPTION, model.getDescription());
        TagWriter.writeTags(node, model.getTags());
        MessageBindingsWriter.writeMessageBindings(node, model.getBindings());
        MessageTraitWriter.writeMessageTraits(node, model.getTraits());
        JsonUtil.stringProperty(node, MessageConstant.PROP_REF, model.getRef());
        ExtensionWriter.writeExtensions(node, model);
    }
}
