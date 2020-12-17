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
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import io.smallrye.asyncapi.core.api.models.AsyncAPIImpl;
import io.smallrye.asyncapi.core.runtime.AsyncApiFormat;
import io.smallrye.asyncapi.core.runtime.io.definition.DefinitionReader;
import io.smallrye.asyncapi.core.runtime.io.schema.SchemaReader;
import io.smallrye.asyncapi.spec.models.AsyncAPI;
import io.smallrye.asyncapi.spec.models.schema.Schema;

/**
 * A class used to parse an AsyncAPI document (either YAML or JSON) into a AsyncAPI model tree.
 */
public class AsyncApiParser {

    /**
     * Parses the resource found at the given URL. This method accepts resources either in JSON or YAML format. It will parse
     * the input and, assuming it
     * is valid, return an instance of {@link AsyncAPI}.
     *
     * @param url URL to AsyncAPI document
     * @return AsyncAPIImpl parsed from URL
     * @throws IOException URL parameter is not found
     */
    public static final AsyncAPI parse(URL url) throws IOException {
        try {
            String fname = url.getFile();
            if (fname == null) {
                throw IoMessages.msg.noFileName(url.toURI()
                        .toString());
            }
            int lidx = fname.lastIndexOf('.');
            if (lidx == -1 || lidx >= fname.length()) {
                throw IoMessages.msg.invalidFileName(url.toURI()
                        .toString());
            }
            String ext = fname.substring(lidx + 1);
            boolean isJson = ext.equalsIgnoreCase("json");
            boolean isYaml = ext.equalsIgnoreCase("yaml") || ext.equalsIgnoreCase("yml");
            if (!isJson && !isYaml) {
                throw IoMessages.msg.invalidFileExtension(url.toURI()
                        .toString());
            }

            try (InputStream stream = url.openStream()) {
                return parse(stream, isJson ? AsyncApiFormat.JSON : AsyncApiFormat.YAML);
            }
        } catch (URISyntaxException e) {
            throw new IOException(e);
        }
    }

    /**
     * Parses the resource found at the given stream. The format of the stream must be specified.
     *
     * @param stream InputStream containing an AsyncAPI document
     * @param format Format of the stream
     * @return AsyncAPIImpl parsed from the stream
     * @throws IOException Errors in reading the stream
     */
    public static final AsyncAPI parse(InputStream stream, AsyncApiFormat format) throws IOException {
        ObjectMapper mapper;
        if (format == AsyncApiFormat.JSON) {
            mapper = new ObjectMapper();
        } else {
            mapper = new ObjectMapper(new YAMLFactory());
        }
        JsonNode tree = mapper.readTree(stream);

        AsyncApiParser parser = new AsyncApiParser(tree);

        return parser.parse();
    }

    /**
     * Parses the schema in the provided String. The format of the stream must be JSON.
     *
     * @param schemaJson String containing a JSON formatted schema
     * @return Schema parsed from the String
     * @throws IOException Errors in reading the String
     */
    public static final Schema parseSchema(String schemaJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode tree = mapper.readTree(schemaJson);
        return SchemaReader.readSchema(tree);
    }

    private final JsonNode tree;

    /**
     * Constructor.
     *
     * @param tree JsonNode
     */
    public AsyncApiParser(JsonNode tree) {
        this.tree = tree;
    }

    /**
     * Parses the json tree into an AsyncAPI data model.
     */
    private AsyncAPI parse() {
        AsyncAPI aai = new AsyncAPIImpl();
        DefinitionReader.processDefinition(aai, tree);

        return aai;
    }
}
