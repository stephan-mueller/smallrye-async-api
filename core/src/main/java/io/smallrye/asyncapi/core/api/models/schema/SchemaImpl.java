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
package io.smallrye.asyncapi.core.api.models.schema;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.smallrye.asyncapi.core.api.models.ExtensibleImpl;
import io.smallrye.asyncapi.core.api.models.ModelImpl;
import io.smallrye.asyncapi.core.runtime.util.ModelUtil;
import io.smallrye.asyncapi.spec.annotations.schema.SchemaType;
import io.smallrye.asyncapi.spec.models.ExternalDocumentation;
import io.smallrye.asyncapi.spec.models.schema.Schema;

/**
 * An implementation of the {@link Schema} AsyncAPI model interface.
 */
public class SchemaImpl extends ExtensibleImpl<Schema> implements Schema, ModelImpl {

    private String ref;

    private String title;

    private SchemaType type;

    private List<String> required;

    private String description;

    private String format;

    private String name;

    private BigDecimal multipleOf;

    private BigDecimal maximum;

    private Boolean exclusiveMaximum;

    private BigDecimal minimum;

    private Boolean exclusiveMinimum;

    private Integer maxLength;

    private Integer minLength;

    private String pattern;

    private Integer maxItems;

    private Integer minItems;

    private Boolean uniqueItems;

    private Integer maxProperties;

    private Integer minProperties;

    private List<String> enumerations;

    private Schema constant;

    private String example;

    private Boolean readOnly;

    private Boolean writeOnly;

    private Map<String, Schema> properties;

    private Schema additionalPropertiesSchema;

    private Boolean additionalPropertiesSchemaBoolean;

    private Schema items;

    private List<Schema> allOf;

    private List<Schema> oneOf;

    private List<Schema> anyOf;

    private Schema not;

    public SchemaImpl() {
    }

    public SchemaImpl(final String name) {
        this.name = name;
    }

    /**
     * @see Schema#getTitle()
     */
    @Override
    public String getTitle() {
        return this.title;
    }

    /**
     * @see Schema#setTitle(String title)
     */
    @Override
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * @see Schema#getType()
     */
    @Override
    public SchemaType getType() {
        return this.type;
    }

    /**
     * @see Schema#setType(SchemaType schemaType)
     */
    @Override
    public void setType(final SchemaType schemaType) {
        this.type = schemaType;
    }

    /**
     * @see Schema#getRequired()
     */
    @Override
    public List<String> getRequired() {
        return ModelUtil.unmodifiableList(this.required);
    }

    /**
     * @see Schema#setRequired(List<String>)
     */
    @Override
    public void setRequired(final List<String> required) {
        this.required = ModelUtil.replace(required, ArrayList::new);
    }

    /**
     * @see Schema#addRequired(String required)
     */
    @Override
    public Schema addRequired(final String required) {
        this.required = ModelUtil.add(required, this.required, ArrayList::new);
        return this;
    }

    /**
     * @see Schema#removeRequired(String required)
     */
    @Override
    public void removeRequired(final String required) {
        ModelUtil.remove(this.required, required);
    }

    /**
     * @see Schema#getDescription()
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * @see Schema#setDescription(String description)
     */
    @Override
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @see Schema#getFormat()
     */
    @Override
    public String getFormat() {
        return this.format;
    }

    /**
     * @see Schema#getFormat()
     */
    @Override
    public void setFormat(final String format) {
        this.format = format;
    }

    /**
     * @see Schema#getName()
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * @see Schema#setName(String name)
     */
    @Override
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @see Schema#getMultipleOf()
     */
    @Override
    public BigDecimal getMultipleOf() {
        return this.multipleOf;
    }

    /**
     * @see Schema#setMultipleOf(BigDecimal multipleOf)
     */
    @Override
    public void setMultipleOf(final BigDecimal multipleOf) {
        this.multipleOf = multipleOf;
    }

    /**
     * @see Schema#getMaximum()
     */
    @Override
    public BigDecimal getMaximum() {
        return this.maximum;
    }

    /**
     * @see Schema#setMaximum(BigDecimal maximum)
     */
    @Override
    public void setMaximum(final BigDecimal maximum) {
        this.maximum = maximum;
    }

    /**
     * @see Schema#getExclusiveMaximum()
     */
    @Override
    public Boolean getExclusiveMaximum() {
        return this.exclusiveMaximum;
    }

    /**
     * @see Schema#setExclusiveMaximum(Boolean exclusiveMaximum)
     */
    @Override
    public void setExclusiveMaximum(final Boolean exclusiveMaximum) {
        this.exclusiveMaximum = exclusiveMaximum;
    }

    /**
     * @see Schema#getMinimum()
     */
    @Override
    public BigDecimal getMinimum() {
        return minimum;
    }

    /**
     * @see Schema#setMinimum(BigDecimal minimum)
     */
    @Override
    public void setMinimum(final BigDecimal minimum) {
        this.minimum = minimum;
    }

    /**
     * @see Schema#getExclusiveMinimum()
     */
    @Override
    public Boolean getExclusiveMinimum() {
        return this.exclusiveMinimum;
    }

    /**
     * @see Schema#setExclusiveMinimum(Boolean exclusiveMinimum)
     */
    @Override
    public void setExclusiveMinimum(final Boolean exclusiveMinimum) {
        this.exclusiveMinimum = exclusiveMinimum;
    }

    /**
     * @see Schema#getMaxLength()
     */
    @Override
    public Integer getMaxLength() {
        return this.maxLength;
    }

    /**
     * @see Schema#setMaxLength(Integer maxLength)
     */
    @Override
    public void setMaxLength(final Integer maxLength) {
        this.maxLength = maxLength;
    }

    /**
     * @see Schema#getMinLength()
     */
    @Override
    public Integer getMinLength() {
        return this.minLength;
    }

    /**
     * @see Schema#setMinLength(Integer minLength)
     */
    @Override
    public void setMinLength(final Integer minLength) {
        this.minLength = minLength;
    }

    /**
     * @see Schema#getMinLength()
     */
    @Override
    public String getPattern() {
        return this.pattern;
    }

    /**
     * @see Schema#setPattern(String pattern)
     */
    @Override
    public void setPattern(final String pattern) {
        this.pattern = pattern;
    }

    /**
     * @see Schema#getMaxItems()
     */
    @Override
    public Integer getMaxItems() {
        return this.maxItems;
    }

    /**
     * @see Schema#setMaxItems(Integer maxItems)
     */
    @Override
    public void setMaxItems(final Integer maxItems) {
        this.maxItems = maxItems;
    }

    /**
     * @see Schema#getMinItems()
     */
    @Override
    public Integer getMinItems() {
        return this.minItems;
    }

    /**
     * @see Schema#setMinItems(Integer minItems)
     */
    @Override
    public void setMinItems(final Integer minItems) {
        this.minItems = minItems;
    }

    /**
     * @see Schema#getUniqueItems()
     */
    @Override
    public Boolean getUniqueItems() {
        return this.uniqueItems;
    }

    /**
     * @see Schema#setUniqueItems(Boolean uniqueItems)
     */
    @Override
    public void setUniqueItems(final Boolean uniqueItems) {
        this.uniqueItems = uniqueItems;
    }

    /**
     * @see Schema#getMaxProperties()
     */
    @Override
    public Integer getMaxProperties() {
        return this.maxProperties;
    }

    /**
     * @see Schema#setMaxProperties(Integer maxProperties)
     */
    @Override
    public void setMaxProperties(final Integer maxProperties) {
        this.maxProperties = maxProperties;
    }

    /**
     * @see Schema#getMinProperties()
     */
    @Override
    public Integer getMinProperties() {
        return this.minProperties;
    }

    /**
     * @see Schema#setMinProperties(Integer minProperties)
     */
    @Override
    public void setMinProperties(final Integer minProperties) {
        this.minProperties = minProperties;
    }

    /**
     * @see Schema#getEnumeration()
     */
    @Override
    public List<String> getEnumeration() {
        return ModelUtil.unmodifiableList(this.enumerations);
    }

    /**
     * @see Schema#setEnumeration(List<String>)
     */
    @Override
    public void setEnumeration(final List<String> enumerations) {
        this.enumerations = ModelUtil.replace(enumerations, ArrayList::new);
    }

    /**
     * @see Schema#addEnumeration(String enumeration)
     */
    @Override
    public Schema addEnumeration(final String enumeration) {
        this.enumerations = ModelUtil.add(enumeration, this.enumerations, ArrayList::new);
        return this;
    }

    /**
     * @see Schema#removeEnumeration(String enumeration)
     */
    @Override
    public void removeEnumeration(final String enumeration) {
        ModelUtil.remove(this.enumerations, enumeration);
    }

    /**
     * @see Schema#getConstant()
     */
    @Override
    public Schema getConstant() {
        return this.constant;
    }

    /**
     * @see Schema#setConstant(Schema constant)
     */
    @Override
    public void setConstant(final Schema constant) {
        this.constant = constant;
    }

    /**
     * @see Schema#getExample()
     */
    @Override
    public String getExample() {
        return this.example;
    }

    /**
     * @see Schema#setExample(String example)
     */
    @Override
    public void setExample(final String example) {
        this.example = example;
    }

    /**
     * @see Schema#getReadOnly()
     */
    @Override
    public Boolean getReadOnly() {
        return this.readOnly;
    }

    /**
     * @see Schema#setReadOnly(Boolean readOnly)
     */
    @Override
    public void setReadOnly(final Boolean readOnly) {
        this.readOnly = readOnly;
    }

    /**
     * @see Schema#getWriteOnly()
     */
    @Override
    public Boolean getWriteOnly() {
        return this.writeOnly;
    }

    /**
     * @see Schema#setWriteOnly(Boolean writeOnly)
     */
    @Override
    public void setWriteOnly(final Boolean writeOnly) {
        this.writeOnly = writeOnly;
    }

    /**
     * @see Schema#getProperties()
     */
    @Override
    public Map<String, Schema> getProperties() {
        return this.properties;
    }

    /**
     * @see Schema#setProperties(Map<String,Schema>)
     */
    @Override
    public void setProperties(final Map<String, Schema> properties) {
        this.properties = properties;
    }

    /**
     * @see Schema#addProperty(String key, Schema property)
     */
    @Override
    public Schema addProperty(final String key, final Schema property) {
        this.properties = ModelUtil.add(key, property, this.properties, LinkedHashMap<String, Schema>::new);
        return this;
    }

    /**
     * @see Schema#removeProperty(String key)
     */
    @Override
    public void removeProperty(final String key) {
        ModelUtil.remove(this.properties, key);
    }

    /**
     * @see Schema#getAdditionalPropertiesSchema()
     */
    @Override
    public Schema getAdditionalPropertiesSchema() {
        return this.additionalPropertiesSchema;
    }

    /**
     * @see Schema#setAdditionalPropertiesSchema(Schema additionalPropertiesSchema)
     */
    @Override
    public void setAdditionalPropertiesSchema(final Schema additionalPropertiesSchema) {
        this.additionalPropertiesSchema = additionalPropertiesSchema;
    }

    /**
     * @see Schema#getAdditionalPropertiesBoolean()
     */
    @Override
    public Boolean getAdditionalPropertiesBoolean() {
        return this.additionalPropertiesSchemaBoolean;
    }

    /**
     * @see Schema#setAdditionalPropertiesBoolean(Boolean additionalPropertiesBoolean)
     */
    @Override
    public void setAdditionalPropertiesBoolean(final Boolean additionalPropertiesBoolean) {
        this.additionalPropertiesSchemaBoolean = additionalPropertiesBoolean;
    }

    /**
     * @see Schema#getItems()
     */
    @Override
    public Schema getItems() {
        return this.items;
    }

    /**
     * @see Schema#setItems(Schema items)
     */
    @Override
    public void setItems(final Schema items) {
        this.items = items;
    }

    /**
     * @see Schema#getAllOf()
     */
    @Override
    public List<Schema> getAllOf() {
        return ModelUtil.unmodifiableList(this.allOf);
    }

    /**
     * @see Schema#setAllOf(List<Schema>)
     */
    @Override
    public void setAllOf(final List<Schema> allOf) {
        this.allOf = ModelUtil.replace(allOf, ArrayList::new);
    }

    /**
     * @see Schema#addAllOf(Schema allOf)
     */
    @Override
    public Schema addAllOf(final Schema allOf) {
        this.allOf = ModelUtil.add(allOf, this.allOf, ArrayList::new);
        return this;
    }

    @Override
    public void removeAllOf(final Schema allOf) {

    }

    /**
     * @see Schema#getOneOf()
     */
    @Override
    public List<Schema> getOneOf() {
        return ModelUtil.unmodifiableList(this.oneOf);
    }

    /**
     * @see Schema#setOneOf(List<Schema>)
     */
    @Override
    public void setOneOf(final List<Schema> oneOf) {
        this.oneOf = ModelUtil.replace(oneOf, ArrayList::new);
    }

    /**
     * @see Schema#addOneOf(Schema oneOf)
     */
    @Override
    public Schema addOneOf(final Schema oneOf) {
        this.oneOf = ModelUtil.add(oneOf, this.oneOf, ArrayList::new);
        return this;
    }

    /**
     * @see Schema#removeOneOf(Schema oneOf)
     */
    @Override
    public void removeOneOf(final Schema oneOf) {
        ModelUtil.remove(this.oneOf, oneOf);
    }

    /**
     * @see Schema#getAnyOf()
     */
    @Override
    public List<Schema> getAnyOf() {
        return ModelUtil.unmodifiableList(this.anyOf);
    }

    /**
     * @see Schema#setAnyOf(List<Schema>)
     */
    @Override
    public void setAnyOf(final List<Schema> anyOf) {
        this.anyOf = ModelUtil.replace(anyOf, ArrayList::new);
    }

    /**
     * @see Schema#addAnyOf(Schema oneOf)
     */
    @Override
    public Schema addAnyOf(final Schema anyOf) {
        this.anyOf = ModelUtil.add(anyOf, this.anyOf, ArrayList::new);
        return this;
    }

    /**
     * @see Schema#removeAnyOf(Schema anyOf)
     */
    @Override
    public void removeAnyOf(final Schema anyOf) {
        ModelUtil.remove(this.anyOf, anyOf);
    }

    /**
     * @see Schema#getNot()
     */
    @Override
    public Schema getNot() {
        return this.not;
    }

    /**
     * @see Schema#setNot(Schema not)
     */
    @Override
    public void setNot(final Schema not) {
        this.not = not;
    }

    @Override
    public ExternalDocumentation getExternalDocs() {
        return null;
    }

    @Override
    public void setExternalDocs(final ExternalDocumentation externalDocumentation) {

    }

    /**
     * @see Schema#getRef()
     */
    @Override
    public String getRef() {
        return this.ref;
    }

    /**
     * @see Schema#setRef(String ref)
     */
    @Override
    public void setRef(final String ref) {
        this.ref = ref;
    }
}
