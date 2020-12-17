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

import java.util.List;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.io.Referenceable;
import io.smallrye.asyncapi.core.runtime.io.bindings.MessageBindingsWriter;
import io.smallrye.asyncapi.core.runtime.io.correlationId.CorrelationIdWriter;
import io.smallrye.asyncapi.core.runtime.io.extension.ExtensionWriter;
import io.smallrye.asyncapi.core.runtime.io.schema.SchemaWriter;
import io.smallrye.asyncapi.core.runtime.io.tag.TagWriter;
import io.smallrye.asyncapi.core.runtime.util.StringUtil;
import io.smallrye.asyncapi.spec.models.message.MessageTrait;

public class MessageTraitWriter {

    public MessageTraitWriter() {
    }

    /**
     * Writes the {@link MessageTrait} model to the JSON tree.
     *
     * @param parent the parent json node
     * @param messageTraits the MessageTrait model
     */
    public static void writeMessageTraits(ObjectNode parent, List<MessageTrait> messageTraits) {
        if (messageTraits == null || messageTraits.size() == 0) {
            return;
        }

        ArrayNode array = parent.putArray(MessageConstant.PROP_TRAITS);

        for (MessageTrait messageTrait : messageTraits) {
            ObjectNode messageTraitNode = array.addObject();

            writeMessageTraitsToNode(messageTraitNode, messageTrait);
        }
    }

    private static void writeMessageTraitsToNode(ObjectNode node, MessageTrait model) {
        if (model == null) {
            return;
        }

        if (StringUtil.isNotEmpty(model.getRef())) {
            JsonUtil.stringProperty(node, Referenceable.PROP_$REF, model.getRef());
            return;
        }

        SchemaWriter.writeSchema(node, model.getHeaders(), MessageConstant.PROP_HEADERS);
        CorrelationIdWriter.writeCorrelationID(node, model.getCorrelationID());
        JsonUtil.stringProperty(node, MessageConstant.PROP_SCHEME_FORMAT, model.getSchemaFormat());
        JsonUtil.stringProperty(node, MessageConstant.PROP_CONTENT_TYPE, model.getContentType());
        JsonUtil.stringProperty(node, MessageConstant.PROP_NAME, model.getName());
        JsonUtil.stringProperty(node, MessageConstant.PROP_TITLE, model.getTitle());
        JsonUtil.stringProperty(node, MessageConstant.PROP_SUMMARY, model.getSummary());
        JsonUtil.stringProperty(node, MessageConstant.PROP_DESCRIPTION, model.getDescription());
        JsonUtil.stringProperty(node, MessageConstant.PROP_REF, model.getRef());

        List<String> examples = model.getExample();
        if (examples != null) {
            ArrayNode exampleArray = node.putArray(MessageConstant.PROP_EXAMPLE);
            for (String exampleValue : examples) {
                exampleArray.add(exampleValue);
            }
        }

        MessageBindingsWriter.writeMessageBindings(node, model.getBindings());
        TagWriter.writeTags(node, model.getTags());
        ExtensionWriter.writeExtensions(node, model);
    }
}
