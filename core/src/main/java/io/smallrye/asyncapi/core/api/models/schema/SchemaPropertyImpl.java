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
import java.util.List;

import io.smallrye.asyncapi.core.api.models.ExtensibleImpl;
import io.smallrye.asyncapi.core.api.models.ModelImpl;
import io.smallrye.asyncapi.core.runtime.util.ModelUtil;
import io.smallrye.asyncapi.spec.annotations.schema.SchemaType;
import io.smallrye.asyncapi.spec.models.schema.Schema;
import io.smallrye.asyncapi.spec.models.schema.SchemaProperty;

/**
 * An implementation of the {@link SchemaProperty} AsyncAPI model interface.
 */
public class SchemaPropertyImpl extends ExtensibleImpl<SchemaProperty> implements SchemaProperty, ModelImpl {

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

    private List<Schema> allOf;

    private List<Schema> oneOf;

    private List<Schema> anyOf;

    private List<Schema> not;

    /**
     * @see SchemaProperty#getTitle()
     */
    @Override
    public String getTitle() {
        return this.title;
    }

    /**
     * @see SchemaProperty#setTitle(String title)
     */
    @Override
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * @see SchemaProperty#getType()
     */
    @Override
    public SchemaType getType() {
        return this.type;
    }

    /**
     * @see SchemaProperty#setType(SchemaType schemaType)
     */
    @Override
    public void setType(final SchemaType schemaType) {
        this.type = schemaType;
    }

    /**
     * @see SchemaProperty#getRequired()
     */
    @Override
    public List<String> getRequired() {
        return ModelUtil.unmodifiableList(this.required);
    }

    /**
     * @see SchemaProperty#setRequired(List<String>)
     */
    @Override
    public void setRequired(final List<String> required) {
        this.required = ModelUtil.replace(required, ArrayList::new);
    }

    /**
     * @see SchemaProperty#addRequired(String required)
     */
    @Override
    public SchemaProperty addRequired(final String required) {
        this.required = ModelUtil.add(required, this.required, ArrayList::new);
        return this;
    }

    /**
     * @see SchemaProperty#removeRequired(String required)
     */
    @Override
    public void removeRequired(final String required) {
        ModelUtil.remove(this.required, required);
    }

    /**
     * @see SchemaProperty#getDescription()
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * @see SchemaProperty#setDescription(String description)
     */
    @Override
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @see SchemaProperty#getFormat()
     */
    @Override
    public String getFormat() {
        return this.format;
    }

    /**
     * @see SchemaProperty#getFormat()
     */
    @Override
    public void setFormat(final String format) {
        this.format = format;
    }

    /**
     * @see SchemaProperty#getName()
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * @see SchemaProperty#setName(String name)
     */
    @Override
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @see SchemaProperty#getMultipleOf()
     */
    @Override
    public BigDecimal getMultipleOf() {
        return this.multipleOf;
    }

    /**
     * @see SchemaProperty#setMultipleOf(BigDecimal multipleOf)
     */
    @Override
    public void setMultipleOf(final BigDecimal multipleOf) {
        this.multipleOf = multipleOf;
    }

    /**
     * @see SchemaProperty#getMaximum()
     */
    @Override
    public BigDecimal getMaximum() {
        return this.maximum;
    }

    /**
     * @see SchemaProperty#setMaximum(BigDecimal maximum)
     */
    @Override
    public void setMaximum(final BigDecimal maximum) {
        this.maximum = maximum;
    }

    /**
     * @see SchemaProperty#getExclusiveMaximum()
     */
    @Override
    public Boolean getExclusiveMaximum() {
        return this.exclusiveMaximum;
    }

    /**
     * @see SchemaProperty#setExclusiveMaximum(Boolean exclusiveMaximum)
     */
    @Override
    public void setExclusiveMaximum(final Boolean exclusiveMaximum) {
        this.exclusiveMaximum = exclusiveMaximum;
    }

    /**
     * @see SchemaProperty#getMinimum()
     */
    @Override
    public BigDecimal getMinimum() {
        return minimum;
    }

    /**
     * @see SchemaProperty#setMinimum(BigDecimal minimum)
     */
    @Override
    public void setMinimum(final BigDecimal minimum) {
        this.minimum = minimum;
    }

    /**
     * @see SchemaProperty#getExclusiveMinimum()
     */
    @Override
    public Boolean getExclusiveMinimum() {
        return this.exclusiveMinimum;
    }

    /**
     * @see SchemaProperty#setExclusiveMinimum(Boolean exclusiveMinimum)
     */
    @Override
    public void setExclusiveMinimum(final Boolean exclusiveMinimum) {
        this.exclusiveMinimum = exclusiveMinimum;
    }

    /**
     * @see SchemaProperty#getMaxLength()
     */
    @Override
    public Integer getMaxLength() {
        return this.maxLength;
    }

    /**
     * @see SchemaProperty#setMaxLength(Integer maxLength)
     */
    @Override
    public void setMaxLength(final Integer maxLength) {
        this.maxLength = maxLength;
    }

    /**
     * @see SchemaProperty#getMinLength()
     */
    @Override
    public Integer getMinLength() {
        return this.minLength;
    }

    /**
     * @see SchemaProperty#setMinLength(Integer minLength)
     */
    @Override
    public void setMinLength(final Integer minLength) {
        this.minLength = minLength;
    }

    /**
     * @see SchemaProperty#getMinLength()
     */
    @Override
    public String getPattern() {
        return this.pattern;
    }

    /**
     * @see SchemaProperty#setPattern(String pattern)
     */
    @Override
    public void setPattern(final String pattern) {
        this.pattern = pattern;
    }

    /**
     * @see SchemaProperty#getMaxItems()
     */
    @Override
    public Integer getMaxItems() {
        return this.maxItems;
    }

    /**
     * @see SchemaProperty#setMaxItems(Integer maxItems)
     */
    @Override
    public void setMaxItems(final Integer maxItems) {
        this.maxItems = maxItems;
    }

    /**
     * @see SchemaProperty#getMinItems()
     */
    @Override
    public Integer getMinItems() {
        return this.minItems;
    }

    /**
     * @see SchemaProperty#setMinItems(Integer minItems)
     */
    @Override
    public void setMinItems(final Integer minItems) {
        this.minItems = minItems;
    }

    /**
     * @see SchemaProperty#getUniqueItems()
     */
    @Override
    public Boolean getUniqueItems() {
        return this.uniqueItems;
    }

    /**
     * @see SchemaProperty#setUniqueItems(Boolean uniqueItems)
     */
    @Override
    public void setUniqueItems(final Boolean uniqueItems) {
        this.uniqueItems = uniqueItems;
    }

    /**
     * @see SchemaProperty#getMaxProperties()
     */
    @Override
    public Integer getMaxProperties() {
        return this.maxProperties;
    }

    /**
     * @see SchemaProperty#setMaxProperties(Integer maxProperties)
     */
    @Override
    public void setMaxProperties(final Integer maxProperties) {
        this.maxProperties = maxProperties;
    }

    /**
     * @see SchemaProperty#getMinProperties()
     */
    @Override
    public Integer getMinProperties() {
        return this.minProperties;
    }

    /**
     * @see SchemaProperty#setMinProperties(Integer minProperties)
     */
    @Override
    public void setMinProperties(final Integer minProperties) {
        this.minProperties = minProperties;
    }

    /**
     * @see SchemaProperty#getEnumeration()
     */
    @Override
    public List<String> getEnumeration() {
        return ModelUtil.unmodifiableList(this.enumerations);
    }

    /**
     * @see SchemaProperty#setEnumeration(List<String>)
     */
    @Override
    public void setEnumeration(final List<String> enumerations) {
        this.enumerations = ModelUtil.replace(enumerations, ArrayList::new);
    }

    /**
     * @see SchemaProperty#addEnumeration(String enumeration)
     */
    @Override
    public SchemaProperty addEnumeration(final String enumeration) {
        this.enumerations = ModelUtil.add(enumeration, this.enumerations, ArrayList::new);
        return this;
    }

    /**
     * @see SchemaProperty#removeEnumeration(String enumeration)
     */
    @Override
    public void removeEnumeration(final String enumeration) {
        ModelUtil.remove(this.enumerations, enumeration);
    }

    /**
     * @see SchemaProperty#getConstant()
     */
    @Override
    public Schema getConstant() {
        return this.constant;
    }

    /**
     * @see SchemaProperty#setConstant(Schema constant)
     */
    @Override
    public void setConstant(final Schema constant) {
        this.constant = constant;
    }

    /**
     * @see SchemaProperty#getExample()
     */
    @Override
    public String getExample() {
        return this.example;
    }

    /**
     * @see SchemaProperty#setExample(String example)
     */
    @Override
    public void setExample(final String example) {
        this.example = example;
    }

    /**
     * @see SchemaProperty#getReadOnly()
     */
    @Override
    public Boolean getReadOnly() {
        return this.readOnly;
    }

    /**
     * @see SchemaProperty#setReadOnly(Boolean readOnly)
     */
    @Override
    public void setReadOnly(final Boolean readOnly) {
        this.readOnly = readOnly;
    }

    /**
     * @see SchemaProperty#getWriteOnly()
     */
    @Override
    public Boolean getWriteOnly() {
        return this.writeOnly;
    }

    /**
     * @see SchemaProperty#setWriteOnly(Boolean writeOnly)
     */
    @Override
    public void setWriteOnly(final Boolean writeOnly) {
        this.writeOnly = writeOnly;
    }

    /**
     * @see SchemaProperty#getAllOf()
     */
    @Override
    public List<Schema> getAllOf() {
        return ModelUtil.unmodifiableList(this.allOf);
    }

    /**
     * @see SchemaProperty#setAllOf(List<Schema>)
     */
    @Override
    public void setAllOf(final List<Schema> allOf) {
        this.allOf = ModelUtil.replace(allOf, ArrayList::new);
    }

    /**
     * @see SchemaProperty#addAllOf(Schema allOf)
     */
    @Override
    public SchemaProperty addAllOf(final Schema allOf) {
        this.allOf = ModelUtil.add(allOf, this.allOf, ArrayList::new);
        return this;
    }

    @Override
    public void removeAllOf(final Schema allOf) {

    }

    /**
     * @see SchemaProperty#getOneOf()
     */
    @Override
    public List<Schema> getOneOf() {
        return ModelUtil.unmodifiableList(this.oneOf);
    }

    /**
     * @see SchemaProperty#setOneOf(List<SchemaProperty>)
     */
    @Override
    public void setOneOf(final List<Schema> oneOf) {
        this.oneOf = ModelUtil.replace(oneOf, ArrayList::new);
    }

    /**
     * @see SchemaProperty#addOneOf(Schema oneOf)
     */
    @Override
    public SchemaProperty addOneOf(final Schema oneOf) {
        this.oneOf = ModelUtil.add(oneOf, this.oneOf, ArrayList::new);
        return this;
    }

    /**
     * @see SchemaProperty#removeOneOf(Schema oneOf)
     */
    @Override
    public void removeOneOf(final Schema oneOf) {
        ModelUtil.remove(this.oneOf, oneOf);
    }

    /**
     * @see SchemaProperty#getAnyOf()
     */
    @Override
    public List<Schema> getAnyOf() {
        return ModelUtil.unmodifiableList(this.anyOf);
    }

    /**
     * @see SchemaProperty#setAnyOf(List<Schema>)
     */
    @Override
    public void setAnyOf(final List<Schema> anyOf) {
        this.anyOf = ModelUtil.replace(anyOf, ArrayList::new);
    }

    /**
     * @see Schema#addAnyOf(Schema oneOf)
     */
    @Override
    public SchemaProperty addAnyOf(final Schema anyOf) {
        this.anyOf = ModelUtil.add(anyOf, this.anyOf, ArrayList::new);
        return this;
    }

    /**
     * @see SchemaProperty#removeAnyOf(Schema anyOf)
     */
    @Override
    public void removeAnyOf(final Schema anyOf) {
        ModelUtil.remove(this.anyOf, anyOf);
    }

    /**
     * @see SchemaProperty#getNot()
     */
    @Override
    public List<Schema> getNot() {
        return ModelUtil.unmodifiableList(this.not);
    }

    /**
     * @see SchemaProperty#setNot(List<Schema>)
     */
    @Override
    public void setNot(final List<Schema> not) {
        this.not = ModelUtil.replace(not, ArrayList::new);
    }

    /**
     * @see SchemaProperty#addNot(Schema not)
     */
    @Override
    public SchemaProperty addNot(final Schema not) {
        this.not = ModelUtil.add(not, this.not, ArrayList::new);
        return this;
    }

    /**
     * @see SchemaProperty#removeNot(Schema not)
     */
    @Override
    public void removeNot(final Schema not) {
        ModelUtil.remove(this.not, not);
    }

    /**
     * @see SchemaProperty#getRef()
     */
    @Override
    public String getRef() {
        return this.ref;
    }

    /**
     * @see SchemaProperty#setRef(String ref)
     */
    @Override
    public void setRef(final String ref) {
        this.ref = ref;
    }

    @Override
    public String toString() {
        return "SchemaPropertyImpl{" + "ref='" + ref + '\'' + ", title='" + title + '\'' + ", type=" + type + ", required="
                + required + ", description='"
                + description + '\'' + ", format='" + format + '\'' + ", name='" + name + '\'' + ", multipleOf=" + multipleOf
                + ", maximum=" + maximum
                + ", exclusiveMaximum=" + exclusiveMaximum + ", minimum=" + minimum + ", exclusiveMinimum=" + exclusiveMinimum
                + ", maxLength=" + maxLength
                + ", minLength=" + minLength + ", pattern='" + pattern + '\'' + ", maxItems=" + maxItems + ", minItems="
                + minItems + ", uniqueItems="
                + uniqueItems + ", maxProperties=" + maxProperties + ", minProperties=" + minProperties + ", enumerations="
                + enumerations + ", constant="
                + constant + ", example='" + example + '\'' + ", readOnly=" + readOnly + ", writeOnly=" + writeOnly + ", allOf="
                + allOf + ", oneOf=" + oneOf
                + ", anyOf=" + anyOf + ", not=" + not + '}';
    }
}
