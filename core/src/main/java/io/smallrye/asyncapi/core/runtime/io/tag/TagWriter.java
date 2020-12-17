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
package io.smallrye.asyncapi.core.runtime.io.tag;

import java.util.List;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.io.definition.DefinitionConstant;
import io.smallrye.asyncapi.core.runtime.io.extension.ExtensionWriter;
import io.smallrye.asyncapi.core.runtime.io.externaldocs.ExternalDocsWriter;
import io.smallrye.asyncapi.spec.models.tag.Tag;

/**
 * Writing the Tag to json
 */
public class TagWriter {

    private TagWriter() {
    }

    /**
     * Writes the {@link Tag} model array to the JSON tree.
     *
     * @param node the json node
     * @param tags list of Tag models
     */
    public static void writeTags(ObjectNode node, List<Tag> tags) {
        if (tags == null) {
            return;
        }
        ArrayNode array = node.putArray(DefinitionConstant.PROP_TAGS);
        for (Tag tag : tags) {
            ObjectNode tagNode = array.addObject();
            JsonUtil.stringProperty(tagNode, TagConstant.PROP_NAME, tag.getName());
            JsonUtil.stringProperty(tagNode, TagConstant.PROP_DESCRIPTION, tag.getDescription());
            ExternalDocsWriter.writeExternalDocumentation(tagNode, tag.getExternalDocumentation());
            ExtensionWriter.writeExtensions(tagNode, tag);
        }
    }
}
