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

import static io.smallrye.asyncapi.core.runtime.io.JsonUtil.readObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.AnnotationValue;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import io.smallrye.asyncapi.core.api.models.schema.SchemaImpl;
import io.smallrye.asyncapi.core.api.models.schema.SchemaPropertyImpl;
import io.smallrye.asyncapi.core.runtime.io.IoLogging;
import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.io.Referenceable;
import io.smallrye.asyncapi.core.runtime.io.extension.ExtensionConstant;
import io.smallrye.asyncapi.core.runtime.io.extension.ExtensionReader;
import io.smallrye.asyncapi.core.runtime.io.externaldocs.ExternalDocsConstant;
import io.smallrye.asyncapi.core.runtime.io.externaldocs.ExternalDocsReader;
import io.smallrye.asyncapi.core.runtime.scanner.spi.AnnotationScannerContext;
import io.smallrye.asyncapi.core.runtime.util.JandexUtil;
import io.smallrye.asyncapi.spec.annotations.schema.SchemaType;
import io.smallrye.asyncapi.spec.models.schema.Schema;
import io.smallrye.asyncapi.spec.models.schema.SchemaProperty;

/**
 * Reading the Schema annotation
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0/#a-name-schemaobject-a-schema-object"
 */
public class SchemaReader {

    private SchemaReader() {
    }

    /**
     * Reads a schema type.
     *
     * @param node the json node
     * @return SchemaType enum
     */
    private static SchemaType readSchemaType(final JsonNode node) {
        if (node != null && node.isTextual()) {
            String strval = node.asText();
            return SchemaType.valueOf(strval.toUpperCase());
        }
        return null;
    }

    /**
     * Reads a list of schemas.
     *
     * @param node the json array
     * @return List of Schema models
     */
    private static Optional<List<Schema>> readSchemaArray(final JsonNode node) {
        if (node != null && node.isArray()) {
            List<Schema> rval = new ArrayList<>(node.size());
            ArrayNode arrayNode = (ArrayNode) node;
            for (JsonNode arrayItem : arrayNode) {
                rval.add(readSchema(arrayItem));
            }
            return Optional.of(rval);
        }
        return Optional.empty();
    }

    /**
     * Reads the {@link Schema} AsyncAPI nodes.
     *
     * @param node map of schema json nodes
     * @return Map of Schema model
     */
    public static Optional<Map<String, Schema>> readSchemas(final JsonNode node) {
        if (node != null && node.isObject()) {
            Map<String, Schema> models = new LinkedHashMap<>();
            for (Iterator<String> fieldNames = node.fieldNames(); fieldNames.hasNext();) {
                String fieldName = fieldNames.next();
                JsonNode childNode = node.get(fieldName);
                models.put(fieldName, readSchema(childNode));
            }
            return Optional.of(models);
        }
        return Optional.empty();
    }

    /**
     * Reads a map of Schema annotations.
     *
     * @param context the scanner context
     * @param annotationValue map of {@literal @}Schema annotations
     * @return Map of Schema models
     */
    public static Map<String, Schema> readSchemas(final AnnotationScannerContext context,
            final AnnotationValue annotationValue) {
        if (annotationValue == null) {
            return null;
        }
        IoLogging.logger.annotationsMap("@Schema");
        Map<String, Schema> map = new LinkedHashMap<>();
        AnnotationInstance[] nestedArray = annotationValue.asNestedArray();
        for (AnnotationInstance nested : nestedArray) {
            String name = JandexUtil.stringValue(nested, SchemaConstant.PROP_NAME);

            if (name == null && JandexUtil.isRef(nested)) {
                name = JandexUtil.nameFromRef(nested);
            }

            /*
             * The name is REQUIRED when the schema is defined within
             * {@link org.eclipse.microprofile.openapi.annotations.Components}.
             */
            if (name != null) {
              map.put(name, SchemaFactory.readSchema(context, new SchemaImpl(name), nested, Collections.emptyMap()));
            }
        }
        return map;
    }

    /**
     * Reads a {@link Schema} AsyncAPI node.
     *
     * @param node json node
     * @return Schema model
     */
    public static Schema readSchema(final JsonNode node) {
        if (node == null || !node.isObject()) {
            return null;
        }

        IoLogging.logger.singleJsonObject("Schema");
        String name = JsonUtil.stringProperty(node, SchemaConstant.PROP_NAME);

        Schema schema = new SchemaImpl(name);
        schema.setRef(JsonUtil.stringProperty(node, Referenceable.PROP_$REF));
        schema.setFormat(JsonUtil.stringProperty(node, SchemaConstant.PROP_FORMAT));
        schema.setTitle(JsonUtil.stringProperty(node, SchemaConstant.PROP_TITLE));
        schema.setDescription(JsonUtil.stringProperty(node, SchemaConstant.PROP_DESCRIPTION));
        schema.setMultipleOf(JsonUtil.bigDecimalProperty(node, SchemaConstant.PROP_MULTIPLE_OF));
        schema.setMaximum(JsonUtil.bigDecimalProperty(node, SchemaConstant.PROP_MAXIMUM));
        schema.setExclusiveMaximum(JsonUtil.booleanProperty(node, SchemaConstant.PROP_EXCLUSIVE_MAXIMUM).orElse(null));
        schema.setMinimum(JsonUtil.bigDecimalProperty(node, SchemaConstant.PROP_MINIMUM));
        schema.setExclusiveMinimum(JsonUtil.booleanProperty(node, SchemaConstant.PROP_EXCLUSIVE_MINIMUM).orElse(null));
        schema.setMaxLength(JsonUtil.intProperty(node, SchemaConstant.PROP_MAX_LENGTH));
        schema.setMinLength(JsonUtil.intProperty(node, SchemaConstant.PROP_MIN_LENGTH));
        schema.setPattern(JsonUtil.stringProperty(node, SchemaConstant.PROP_PATTERN));
        schema.setMaxItems(JsonUtil.intProperty(node, SchemaConstant.PROP_MAX_ITEMS));
        schema.setMinItems(JsonUtil.intProperty(node, SchemaConstant.PROP_MIN_ITEMS));
        schema.setUniqueItems(JsonUtil.booleanProperty(node, SchemaConstant.PROP_UNIQUE_ITEMS).orElse(null));
        schema.setMaxProperties(JsonUtil.intProperty(node, SchemaConstant.PROP_MAX_PROPERTIES));
        schema.setMinProperties(JsonUtil.intProperty(node, SchemaConstant.PROP_MIN_PROPERTIES));
        schema.setRequired(JsonUtil.readStringArray(node.get(SchemaConstant.PROP_REQUIRED)).orElse(null));
        schema.setEnumeration(JsonUtil.readStringArray(node.get(SchemaConstant.PROP_ENUMERATION)).orElse(null));
        schema.setType(readSchemaType(node.get(SchemaConstant.PROP_TYPE)));
        schema.setNot(readSchema(node.get(SchemaConstant.PROP_NOT)));
        schema.setAllOf(readSchemaArray(node.get(SchemaConstant.PROP_ALL_OF)).orElse(null));
        schema.setProperties(readSchemas(node.get(SchemaConstant.PROP_PROPERTIES)).orElse(null));
        schema.setReadOnly(JsonUtil.booleanProperty(node, SchemaConstant.PROP_READ_ONLY).orElse(null));
        schema.setExternalDocs(ExternalDocsReader.readExternalDocs(node.get(ExternalDocsConstant.PROP_EXTERNAL_DOCS)));
        schema.setExample((String) readObject(node.get(SchemaConstant.PROP_EXAMPLE)));
        schema.setOneOf(readSchemaArray(node.get(SchemaConstant.PROP_ONE_OF)).orElse(null));
        schema.setAnyOf(readSchemaArray(node.get(SchemaConstant.PROP_ANY_OF)).orElse(null));
        schema.setWriteOnly(JsonUtil.booleanProperty(node, SchemaConstant.PROP_WRITE_ONLY).orElse(null));
        ExtensionReader.readExtensions(node, schema);

        return schema;
    }

    private static Optional<Map<String, SchemaProperty>> readSchemaProperties(final JsonNode node) {
        if (node == null) {
            return Optional.empty();
        }

        Map properties = new HashMap<String, SchemaProperty>();
        for (Iterator<String> iterator = node.fieldNames(); iterator.hasNext();) {
            String fieldName = iterator.next();
            if (!ExtensionConstant.isExtensionField(fieldName)) {
                JsonNode varNode = node.get(fieldName);
                properties.put(fieldName, readSchemaProperty(varNode));
            }
        }

        return Optional.of(properties);
    }

    private static SchemaProperty readSchemaProperty(final JsonNode node) {
        if (node == null || !node.isObject()) {
            return null;
        }

        IoLogging.logger.singleJsonObject("Schema");

        SchemaProperty schema = new SchemaPropertyImpl();
        schema.setRef(JsonUtil.stringProperty(node, Referenceable.PROP_$REF));
        schema.setFormat(JsonUtil.stringProperty(node, SchemaConstant.PROP_FORMAT));
        schema.setTitle(JsonUtil.stringProperty(node, SchemaConstant.PROP_TITLE));
        schema.setDescription(JsonUtil.stringProperty(node, SchemaConstant.PROP_DESCRIPTION));
        schema.setMultipleOf(JsonUtil.bigDecimalProperty(node, SchemaConstant.PROP_MULTIPLE_OF));
        schema.setMaximum(JsonUtil.bigDecimalProperty(node, SchemaConstant.PROP_MAXIMUM));
        schema.setExclusiveMaximum(JsonUtil.booleanProperty(node, SchemaConstant.PROP_EXCLUSIVE_MAXIMUM).orElse(null));
        schema.setMinimum(JsonUtil.bigDecimalProperty(node, SchemaConstant.PROP_MINIMUM));
        schema.setExclusiveMinimum(JsonUtil.booleanProperty(node, SchemaConstant.PROP_EXCLUSIVE_MINIMUM).orElse(null));
        schema.setMaxLength(JsonUtil.intProperty(node, SchemaConstant.PROP_MAX_LENGTH));
        schema.setMinLength(JsonUtil.intProperty(node, SchemaConstant.PROP_MIN_LENGTH));
        schema.setPattern(JsonUtil.stringProperty(node, SchemaConstant.PROP_PATTERN));
        schema.setMaxItems(JsonUtil.intProperty(node, SchemaConstant.PROP_MAX_ITEMS));
        schema.setMinItems(JsonUtil.intProperty(node, SchemaConstant.PROP_MIN_ITEMS));
        schema.setUniqueItems(JsonUtil.booleanProperty(node, SchemaConstant.PROP_UNIQUE_ITEMS).orElse(null));
        schema.setMaxProperties(JsonUtil.intProperty(node, SchemaConstant.PROP_MAX_PROPERTIES));
        schema.setMinProperties(JsonUtil.intProperty(node, SchemaConstant.PROP_MIN_PROPERTIES));
        schema.setRequired(JsonUtil.readStringArray(node.get(SchemaConstant.PROP_REQUIRED)).orElse(null));
        schema.setEnumeration(JsonUtil.readStringArray(node.get(SchemaConstant.PROP_ENUMERATION)).orElse(null));
        schema.setType(readSchemaType(node.get(SchemaConstant.PROP_TYPE)));
        //schema.setNot(readSchema(node.get(SchemaConstant.PROP_NOT)));
        schema.setAllOf(readSchemaArray(node.get(SchemaConstant.PROP_ALL_OF)).orElse(null));
        schema.setReadOnly(JsonUtil.booleanProperty(node, SchemaConstant.PROP_READ_ONLY).orElse(null));
        schema.setExample((String) readObject(node.get(SchemaConstant.PROP_EXAMPLE)));
        schema.setOneOf(readSchemaArray(node.get(SchemaConstant.PROP_ONE_OF)).orElse(null));
        schema.setAnyOf(readSchemaArray(node.get(SchemaConstant.PROP_ANY_OF)).orElse(null));
        schema.setWriteOnly(JsonUtil.booleanProperty(node, SchemaConstant.PROP_WRITE_ONLY).orElse(null));
        ExtensionReader.readExtensions(node, schema);

        return schema;
    }
}
