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

import java.util.List;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.io.Referenceable;
import io.smallrye.asyncapi.core.runtime.io.bindings.OperationBindingWriter;
import io.smallrye.asyncapi.core.runtime.io.extension.ExtensionWriter;
import io.smallrye.asyncapi.core.runtime.io.externaldocs.ExternalDocsWriter;
import io.smallrye.asyncapi.core.runtime.io.message.MessageConstant;
import io.smallrye.asyncapi.core.runtime.io.message.MessageWriter;
import io.smallrye.asyncapi.core.runtime.io.tag.TagWriter;
import io.smallrye.asyncapi.core.runtime.util.StringUtil;
import io.smallrye.asyncapi.spec.models.operation.OperationTrait;

public class OperationTraitWriter {

    public OperationTraitWriter() {
    }

    public static void writeOperationTraits(ObjectNode parent, List<OperationTrait> operationTraits) {
        if (operationTraits == null || operationTraits.size() == 0) {
            return;
        }

        ArrayNode array = parent.putArray(MessageConstant.PROP_TRAITS);

        for (OperationTrait operationTrait : operationTraits) {
            ObjectNode operationTraitNode = array.addObject();

            writeOperationTraitToNode(operationTraitNode, operationTrait);
        }
    }

    public static void writeOperationTraitToNode(ObjectNode node, OperationTrait model) {
        if (model == null) {
            return;
        }

        if (StringUtil.isNotEmpty(model.getRef())) {
            JsonUtil.stringProperty(node, Referenceable.PROP_$REF, model.getRef());
            return;
        }

        JsonUtil.stringProperty(node, OperationConstant.PROP_OPERATION_ID, model.getOperationId());
        JsonUtil.stringProperty(node, OperationConstant.PROP_SUMMARY, model.getSummary());
        JsonUtil.stringProperty(node, OperationConstant.PROP_DESCRIPTION, model.getDescription());
        TagWriter.writeTags(node, model.getTags());
        OperationBindingWriter.writeOperationBindings(node, model.getBindings());
        MessageWriter.writeMessage(node, model.getMessage());
        ExternalDocsWriter.writeExternalDocumentation(node, model.getExternalDocumentation());

        ExtensionWriter.writeExtensions(node, model);
    }

}
