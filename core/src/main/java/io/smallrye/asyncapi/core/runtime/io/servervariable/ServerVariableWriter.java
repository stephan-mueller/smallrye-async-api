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
package io.smallrye.asyncapi.core.runtime.io.servervariable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.io.extension.ExtensionWriter;
import io.smallrye.asyncapi.core.runtime.io.server.ServerConstant;
import io.smallrye.asyncapi.spec.models.server.ServerVariable;

/**
 * Writing the ServerVariable to json
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0#serverVariableObject"
 */
public class ServerVariableWriter {

    private ServerVariableWriter() {
    }

    /**
     * Writes the {@link ServerVariable} model to the JSON tree.
     *
     * @param serverNode the json node
     * @param variables map of ServerVariable models
     */
    public static void writeServerVariables(ObjectNode serverNode, Map<String, ServerVariable> variables) {
        if (variables == null) {
            return;
        }
        ObjectNode variablesNode = serverNode.putObject(ServerConstant.PROP_VARIABLES);

        Set<Map.Entry<String, ServerVariable>> entrySet = variables.entrySet();
        for (Map.Entry<String, ServerVariable> entry : entrySet) {
            writeServerVariable(variablesNode, entry.getValue(), entry.getKey());
        }
    }

    /**
     * Writes a {@link ServerVariable} to the JSON tree.
     *
     * @param parent the parent json node
     * @param model the ServerVariable model
     * @param variableName the node name
     */
    public static void writeServerVariable(ObjectNode parent, ServerVariable model, String variableName) {
        if (model == null) {
            return;
        }
        ObjectNode node = parent.putObject(variableName);
        JsonUtil.stringProperty(node, ServerVariableConstant.PROP_DEFAULT, model.getDefaultValue());
        JsonUtil.stringProperty(node, ServerVariableConstant.PROP_DESCRIPTION, model.getDescription());

        List<String> enumeration = model.getEnumeration();
        if (enumeration != null) {
            ArrayNode enumArray = node.putArray(ServerVariableConstant.PROP_ENUM);
            for (String enumValue : enumeration) {
                enumArray.add(enumValue);
            }
        }

        List<String> examples = model.getExamples();
        if (examples != null) {
            ArrayNode exampleArray = node.putArray(ServerVariableConstant.PROP_EXAMPLES);
            for (String exampleValue : examples) {
                exampleArray.add(exampleValue);
            }
        }
        ExtensionWriter.writeExtensions(node, model);
    }
}
