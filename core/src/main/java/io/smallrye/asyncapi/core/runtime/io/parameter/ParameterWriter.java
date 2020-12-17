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
package io.smallrye.asyncapi.core.runtime.io.parameter;

import java.util.Map;

import com.fasterxml.jackson.databind.node.ObjectNode;

import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.io.Referenceable;
import io.smallrye.asyncapi.core.runtime.io.channels.ChannelsConstants;
import io.smallrye.asyncapi.core.runtime.io.schema.SchemaWriter;
import io.smallrye.asyncapi.core.runtime.util.StringUtil;
import io.smallrye.asyncapi.spec.models.parameter.Parameter;
import io.smallrye.asyncapi.spec.models.parameter.Parameters;

public class ParameterWriter {

    public ParameterWriter() {
    }

    public static void writeParameters(ObjectNode parent, Map<String, Parameter> models) {
        if (models == null) {
            return;
        }
        ObjectNode node = parent.putObject(ChannelsConstants.PROP_PARAMETERS);

        for (Map.Entry<String, Parameter> entry : models.entrySet()) {
            writeParameter(node, entry.getValue());
        }
    }

    /**
     * Writes a list of {@link Parameter} to the JSON tree.
     *
     * @param parent the parent json node
     * @param models list of Parameter models
     */
    public static void writeParameters(ObjectNode parent, Parameters models) {
        if (models == null || models.getValue() == null) {
            return;
        }
        ObjectNode node = parent.putObject(ChannelsConstants.PROP_PARAMETERS);

        for (Parameter model : models.getValue()) {
            writeParameter(node, model);
        }
    }

    /**
     * Writes a {@link Parameter} into the JSON parent.
     *
     * @param parent
     * @param model
     */
    private static void writeParameter(ObjectNode parent, Parameter model) {
        if (model == null) {
            return;
        }

        if (StringUtil.isNotEmpty(model.getRef())) {
            JsonUtil.stringProperty(parent, Referenceable.PROP_$REF, model.getRef());
            return;
        }

        ObjectNode node = parent.putObject(model.getName());

        JsonUtil.stringProperty(node, ParameterConstant.PROP_NAME, model.getName());
        JsonUtil.stringProperty(node, ParameterConstant.PROP_DESCRIPTION, model.getDescription());
        SchemaWriter.writeSchema(node.putObject(ParameterConstant.PROP_SCHEMA), model.getSchema());
        JsonUtil.stringProperty(node, ParameterConstant.PROP_LOCATION, model.getLocation());
        JsonUtil.stringProperty(node, ParameterConstant.PROP_REF, model.getRef());
    }
}
