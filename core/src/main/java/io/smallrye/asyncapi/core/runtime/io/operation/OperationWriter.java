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
package io.smallrye.asyncapi.core.runtime.io.operation;

import com.fasterxml.jackson.databind.node.ObjectNode;

import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.io.bindings.OperationBindingWriter;
import io.smallrye.asyncapi.core.runtime.io.channels.ChannelsConstants;
import io.smallrye.asyncapi.core.runtime.io.extension.ExtensionWriter;
import io.smallrye.asyncapi.core.runtime.io.externaldocs.ExternalDocsWriter;
import io.smallrye.asyncapi.core.runtime.io.message.MessageWriter;
import io.smallrye.asyncapi.core.runtime.io.tag.TagWriter;
import io.smallrye.asyncapi.spec.models.operation.Operation;

public class OperationWriter {

    public OperationWriter() {
    }

    public static void writeSubscribe(ObjectNode parent, Operation model) {
        if (model == null) {
            return;
        }
        ObjectNode node = parent.putObject(ChannelsConstants.PROP_SUBSCRIBE);

        writeOperation(model, node);
    }

    public static void writePublish(ObjectNode parent, Operation model) {
        if (model == null) {
            return;
        }
        ObjectNode node = parent.putObject(ChannelsConstants.PROP_PUBLISH);

        writeOperation(model, node);
    }

    private static void writeOperation(final Operation model, final ObjectNode node) {
        JsonUtil.stringProperty(node, OperationConstant.PROP_OPERATION_ID, model.getOperationId());
        JsonUtil.stringProperty(node, OperationConstant.PROP_SUMMARY, model.getSummary());
        JsonUtil.stringProperty(node, OperationConstant.PROP_DESCRIPTION, model.getDescription());
        TagWriter.writeTags(node, model.getTags());
        OperationBindingWriter.writeOperationBindings(node, model.getBindings());
        OperationTraitWriter.writeOperationTraits(node, model.getOperationTraits());
        MessageWriter.writeMessage(node, model.getMessage());
        ExternalDocsWriter.writeExternalDocumentation(node, model.getExternalDocumentation());
        ExtensionWriter.writeExtensions(node, model);
    }
}
