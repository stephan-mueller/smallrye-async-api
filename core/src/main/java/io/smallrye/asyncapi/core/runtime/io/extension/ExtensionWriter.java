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
package io.smallrye.asyncapi.core.runtime.io.extension;

import java.util.Map;

import com.fasterxml.jackson.databind.node.ObjectNode;

import io.smallrye.asyncapi.core.runtime.io.ObjectWriter;
import io.smallrye.asyncapi.spec.models.Extensible;

public class ExtensionWriter {

    private ExtensionWriter() {
    }

    /**
     * Writes extensions to the JSON tree.
     *
     * @param node the json node
     * @param model the Extensible model
     */
    public static void writeExtensions(ObjectNode node, Extensible<?> model) {
        Map<String, Object> extensions = model.getExtensions();
        if (extensions == null || extensions.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : extensions.entrySet()) {
            String key = entry.getKey();
            if (!ExtensionConstant.isExtensionField(key)) {
                key = ExtensionConstant.EXTENSION_PROPERTY_PREFIX + key;
            }
            Object value = entry.getValue();
            ObjectWriter.writeObject(node, key, value);
        }
    }

}
