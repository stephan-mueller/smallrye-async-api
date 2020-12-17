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
package io.smallrye.asyncapi.core.runtime.io.bindings.http.message;

import com.fasterxml.jackson.databind.node.ObjectNode;

import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.io.bindings.MessageBindingsConstants;
import io.smallrye.asyncapi.core.runtime.io.schema.SchemaWriter;
import io.smallrye.asyncapi.spec.models.binding.http.HTTPMessageBinding;

public class HTTPMessageBindingWriter {

    public HTTPMessageBindingWriter() {
    }

    public static void writeHTTPMessageBinding(ObjectNode parent, HTTPMessageBinding model) {
        if (model == null) {
            return;
        }
        ObjectNode node = JsonUtil.objectNode();
        parent.set(MessageBindingsConstants.PROP_HTTP_BINDING, node);

        SchemaWriter.writeSchema(node, model.getHeaders(), HTTPMessageBindingConstant.PROP_HEADERS);
        JsonUtil.stringProperty(node, HTTPMessageBindingConstant.PROP_BINDING_VERSION, model.getBindingVersion());
    }
}
