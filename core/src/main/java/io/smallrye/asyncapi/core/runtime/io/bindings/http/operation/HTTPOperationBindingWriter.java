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
package io.smallrye.asyncapi.core.runtime.io.bindings.http.operation;

import com.fasterxml.jackson.databind.node.ObjectNode;

import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.io.bindings.OperationBindingsConstants;
import io.smallrye.asyncapi.core.runtime.io.bindings.http.message.HTTPMessageBindingConstant;
import io.smallrye.asyncapi.core.runtime.io.schema.SchemaWriter;
import io.smallrye.asyncapi.spec.models.binding.http.HTTPOperationBinding;

public class HTTPOperationBindingWriter {

    public HTTPOperationBindingWriter() {
    }

    public static void writeHTTPOperationBinding(ObjectNode parent, HTTPOperationBinding model) {
        if (model == null) {
            return;
        }
        ObjectNode node = JsonUtil.objectNode();
        parent.set(OperationBindingsConstants.PROP_HTTP_BINDING, node);

        JsonUtil.stringProperty(node, HTTPOperationBindingConstant.PROP_TYPE, model.getType());
        JsonUtil.stringProperty(node, HTTPOperationBindingConstant.PROP_METHOD, model.getMethod().toString());
        SchemaWriter.writeSchema(node, model.getQuery(), HTTPOperationBindingConstant.PROP_QUERY);
        JsonUtil.stringProperty(node, HTTPMessageBindingConstant.PROP_BINDING_VERSION, model.getBindingVersion());
    }
}
