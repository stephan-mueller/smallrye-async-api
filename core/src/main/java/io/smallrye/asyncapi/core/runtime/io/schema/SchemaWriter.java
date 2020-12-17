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
package io.smallrye.asyncapi.core.runtime.io.schema;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.io.ObjectWriter;
import io.smallrye.asyncapi.core.runtime.io.Referenceable;
import io.smallrye.asyncapi.core.runtime.io.components.ComponentsConstant;
import io.smallrye.asyncapi.core.runtime.io.extension.ExtensionWriter;
import io.smallrye.asyncapi.core.runtime.io.externaldocs.ExternalDocsWriter;
import io.smallrye.asyncapi.core.runtime.util.StringUtil;
import io.smallrye.asyncapi.spec.models.schema.Schema;
import io.smallrye.asyncapi.spec.models.schema.SchemaProperty;

public class SchemaWriter {

    private SchemaWriter() {
    }

    /**
     * Writes a map of {@link Schema} to the JSON tree.
     *
     * @param parent the parent json node
     * @param schemas map of Schema models
     */
    public static void writeSchemas(ObjectNode parent, Map<String, Schema> schemas) {
        writeSchemas(parent, schemas, ComponentsConstant.PROP_SCHEMAS);
    }

    /**
     * Writes a map of {@link Schema} to the JSON tree.
     *
     * @param parent
     * @param schemas
     */
    private static void writeSchemas(ObjectNode parent, Map<String, Schema> schemas, String propertyName) {
        if (schemas == null) {
            return;
        }
        ObjectNode schemasNode = parent.putObject(propertyName);
        for (Map.Entry<String, Schema> entry : schemas.entrySet()) {
            writeSchema(schemasNode, entry.getValue(), entry.getKey());
        }
    }

    /**
     * Writes a map of {@link Schema} to the JSON tree.
     *
     * @param parent
     * @param schemas
     */
    private static void writeSchemaProperties(ObjectNode parent, Map<String, SchemaProperty> schemas, String propertyName) {
        if (schemas == null) {
            return;
        }

        ObjectNode schemasNode = parent.putObject(propertyName);
        for (Map.Entry<String, SchemaProperty> entry : schemas.entrySet()) {
            SchemaProperty value = entry.getValue();

            String name = value.getName();
            if (name == null) {
                name = "my-app-header";
            }

            writeSchemaProperty(schemasNode, value, name);
        }
    }

    /**
     * Writes a {@link Schema} to the JSON tree.
     *
     * @param parent the parent json node
     * @param model Schema model
     * @param name name of the node
     */
    public static void writeSchema(ObjectNode parent, Schema model, String name) {
        if (model == null) {
            return;
        }
        ObjectNode node = parent.putObject(name);
        writeSchema(node, model);
    }

    /**
     * Writes a {@link Schema} to the JSON tree.
     *
     * @param parent the parent json node
     * @param model Schema model
     * @param name name of the node
     */
    public static void writeSchemaProperty(ObjectNode parent, SchemaProperty model, String name) {
        if (model == null) {
            return;
        }
        ObjectNode node = parent.putObject(name);
        writeSchemaProperty(node, model);
    }

    /**
     * Writes the {@link Schema} model to the given node.
     *
     * @param node
     * @param model
     */
    public static void writeSchema(ObjectNode node, Schema model) {
        if (model == null) {
            return;
        }

        if (StringUtil.isNotEmpty(model.getRef())) {
            JsonUtil.stringProperty(node, Referenceable.PROP_$REF, model.getRef());
        } else {
            JsonUtil.stringProperty(node, SchemaConstant.PROP_FORMAT, model.getFormat());
            JsonUtil.stringProperty(node, SchemaConstant.PROP_TITLE, model.getTitle());
            JsonUtil.stringProperty(node, SchemaConstant.PROP_DESCRIPTION, model.getDescription());
            JsonUtil.bigDecimalProperty(node, SchemaConstant.PROP_MULTIPLE_OF, model.getMultipleOf());
            JsonUtil.bigDecimalProperty(node, SchemaConstant.PROP_MAXIMUM, model.getMaximum());
            JsonUtil.booleanProperty(node, SchemaConstant.PROP_EXCLUSIVE_MAXIMUM, model.getExclusiveMaximum());
            JsonUtil.bigDecimalProperty(node, SchemaConstant.PROP_MINIMUM, model.getMinimum());
            JsonUtil.booleanProperty(node, SchemaConstant.PROP_EXCLUSIVE_MINIMUM, model.getExclusiveMinimum());
            JsonUtil.intProperty(node, SchemaConstant.PROP_MAX_LENGTH, model.getMaxLength());
            JsonUtil.intProperty(node, SchemaConstant.PROP_MIN_LENGTH, model.getMinLength());
            JsonUtil.stringProperty(node, SchemaConstant.PROP_PATTERN, model.getPattern());
            JsonUtil.intProperty(node, SchemaConstant.PROP_MAX_ITEMS, model.getMaxItems());
            JsonUtil.intProperty(node, SchemaConstant.PROP_MIN_ITEMS, model.getMinItems());
            JsonUtil.booleanProperty(node, SchemaConstant.PROP_UNIQUE_ITEMS, model.getUniqueItems());
            JsonUtil.intProperty(node, SchemaConstant.PROP_MAX_PROPERTIES, model.getMaxProperties());
            JsonUtil.intProperty(node, SchemaConstant.PROP_MIN_PROPERTIES, model.getMinProperties());
            ObjectWriter.writeStringArray(node, model.getRequired(), SchemaConstant.PROP_REQUIRED);
            ObjectWriter.writeStringArray(node, model.getEnumeration(), SchemaConstant.PROP_ENUMERATION);
            JsonUtil.enumProperty(node, SchemaConstant.PROP_TYPE, model.getType());
            writeSchemaList(node, model.getAllOf(), SchemaConstant.PROP_ALL_OF);
            writeSchemas(node, model.getProperties(), SchemaConstant.PROP_PROPERTIES);
            JsonUtil.booleanProperty(node, SchemaConstant.PROP_READ_ONLY, model.getReadOnly());
            ExternalDocsWriter.writeExternalDocumentation(node, model.getExternalDocs());
            ObjectWriter.writeObject(node, SchemaConstant.PROP_EXAMPLE, model.getExample());
            writeSchemaList(node, model.getOneOf(), SchemaConstant.PROP_ONE_OF);
            writeSchemaList(node, model.getAnyOf(), SchemaConstant.PROP_ANY_OF);
            writeSchema(node, model.getNot(), SchemaConstant.PROP_NOT);
            JsonUtil.booleanProperty(node, SchemaConstant.PROP_WRITE_ONLY, model.getWriteOnly());
            ExtensionWriter.writeExtensions(node, model);
        }
    }

    /**
     * Writes the {@link SchemaProperty} model to the given node.
     *
     * @param node
     * @param model
     */
    public static void writeSchemaProperty(ObjectNode node, SchemaProperty model) {
        if (StringUtil.isNotEmpty(model.getRef())) {
            JsonUtil.stringProperty(node, Referenceable.PROP_$REF, model.getRef());
        } else {
            JsonUtil.stringProperty(node, SchemaConstant.PROP_FORMAT, model.getFormat());
            JsonUtil.stringProperty(node, SchemaConstant.PROP_TITLE, model.getTitle());
            JsonUtil.stringProperty(node, SchemaConstant.PROP_DESCRIPTION, model.getDescription());
            JsonUtil.bigDecimalProperty(node, SchemaConstant.PROP_MULTIPLE_OF, model.getMultipleOf());
            JsonUtil.bigDecimalProperty(node, SchemaConstant.PROP_MAXIMUM, model.getMaximum());
            JsonUtil.booleanProperty(node, SchemaConstant.PROP_EXCLUSIVE_MAXIMUM, model.getExclusiveMaximum());
            JsonUtil.bigDecimalProperty(node, SchemaConstant.PROP_MINIMUM, model.getMinimum());
            JsonUtil.booleanProperty(node, SchemaConstant.PROP_EXCLUSIVE_MINIMUM, model.getExclusiveMinimum());
            JsonUtil.intProperty(node, SchemaConstant.PROP_MAX_LENGTH, model.getMaxLength());
            JsonUtil.intProperty(node, SchemaConstant.PROP_MIN_LENGTH, model.getMinLength());
            JsonUtil.stringProperty(node, SchemaConstant.PROP_PATTERN, model.getPattern());
            JsonUtil.intProperty(node, SchemaConstant.PROP_MAX_ITEMS, model.getMaxItems());
            JsonUtil.intProperty(node, SchemaConstant.PROP_MIN_ITEMS, model.getMinItems());
            JsonUtil.booleanProperty(node, SchemaConstant.PROP_UNIQUE_ITEMS, model.getUniqueItems());
            JsonUtil.intProperty(node, SchemaConstant.PROP_MAX_PROPERTIES, model.getMaxProperties());
            JsonUtil.intProperty(node, SchemaConstant.PROP_MIN_PROPERTIES, model.getMinProperties());
            ObjectWriter.writeStringArray(node, model.getRequired(), SchemaConstant.PROP_REQUIRED);
            JsonUtil.enumProperty(node, SchemaConstant.PROP_TYPE, model.getType());
            writeSchemaList(node, model.getAllOf(), SchemaConstant.PROP_ALL_OF);
            ObjectWriter.writeStringArray(node, model.getEnumeration(), SchemaConstant.PROP_ENUMERATION);
            JsonUtil.booleanProperty(node, SchemaConstant.PROP_READ_ONLY, model.getReadOnly());
            ObjectWriter.writeObject(node, SchemaConstant.PROP_EXAMPLE, model.getExample());
            writeSchemaList(node, model.getOneOf(), SchemaConstant.PROP_ONE_OF);
            writeSchemaList(node, model.getAnyOf(), SchemaConstant.PROP_ANY_OF);
            //writeSchema(node, model.getNot(), SchemaConstant.PROP_NOT);
            JsonUtil.booleanProperty(node, SchemaConstant.PROP_WRITE_ONLY, model.getWriteOnly());
            ExtensionWriter.writeExtensions(node, model);
        }
    }

    /**
     * Writes a list of {@link Schema} to the JSON tree.
     *
     * @param parent
     * @param models
     * @param propertyName
     */
    private static void writeSchemaList(ObjectNode parent, List<Schema> models, String propertyName) {
        if (models == null) {
            return;
        }
        ArrayNode schemasNode = parent.putArray(propertyName);
        for (Schema schema : models) {
            writeSchema(schemasNode.addObject(), schema);
        }
    }
}
