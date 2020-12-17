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
package io.smallrye.asyncapi.core.runtime.io;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;

import io.smallrye.asyncapi.core.runtime.AsyncApiFormat;
import io.smallrye.asyncapi.core.runtime.io.definition.DefinitionWriter;
import io.smallrye.asyncapi.spec.models.AsyncAPI;

public class AsyncApiSerializer {

    private AsyncApiSerializer() {
    }

    /**
     * Serializes the given AsyncAPI object into either JSON or YAML and returns it as a string.
     *
     * @param asyncAPI the AsyncAPI object
     * @param format the serialization format
     * @return AsyncAPI object as a String
     * @throws IOException Errors in processing the JSON
     */
    public static final String serialize(AsyncAPI asyncAPI, AsyncApiFormat format) throws IOException {
        try {
            ObjectNode tree = JsonUtil.objectNode();
            DefinitionWriter.writeAsyncAPI(tree, asyncAPI);

            if (format == AsyncApiFormat.JSON) {
                return new ObjectMapper().writerWithDefaultPrettyPrinter()
                        .writeValueAsString(tree);
            }

            YAMLFactory factory = new YAMLFactory();
            factory.enable(YAMLGenerator.Feature.ALWAYS_QUOTE_NUMBERS_AS_STRINGS);
            factory.enable(YAMLGenerator.Feature.SPLIT_LINES);

            return new ObjectMapper(factory).writer()
                    .writeValueAsString(tree);

        } catch (JsonProcessingException e) {
            throw new IOException(e);
        }
    }
}
