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
package io.smallrye.asyncapi.core.runtime.io.server;

import java.util.List;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.io.bindings.ServerBindingsWriter;
import io.smallrye.asyncapi.core.runtime.io.definition.DefinitionConstant;
import io.smallrye.asyncapi.core.runtime.io.extension.ExtensionWriter;
import io.smallrye.asyncapi.core.runtime.io.securityrequirement.SecurityRequirementWriter;
import io.smallrye.asyncapi.core.runtime.io.servervariable.ServerVariableWriter;
import io.smallrye.asyncapi.spec.models.server.Server;

/**
 * Writing the Server to json
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0#serverObject"
 */
public class ServerWriter {

    private ServerWriter() {
    }

    /**
     * Writes the {@link Server} model array to the JSON tree.
     *
     * @param node the json node
     * @param servers list of Server models
     */
    public static void writeServers(ObjectNode node, List<Server> servers) {
        if (servers == null) {
            return;
        }
        ArrayNode array = node.putArray(DefinitionConstant.PROP_SERVERS);
        for (Server server : servers) {
            ObjectNode serverNode = array.addObject();
            writeServerToNode(serverNode, server);
        }
    }

    private static void writeServerToNode(ObjectNode node, Server model) {
        JsonUtil.stringProperty(node, ServerConstant.PROP_URL, model.getUrl());
        JsonUtil.stringProperty(node, ServerConstant.PROP_DESCRIPTION, model.getDescription());
        ServerVariableWriter.writeServerVariables(node, model.getVariables());
        SecurityRequirementWriter.writeSecurityRequirements(node, model.getSecurityRequirements());
        ServerBindingsWriter.writeServerBindings(node, model.getServerBindings());
        ExtensionWriter.writeExtensions(node, model);
    }
}
