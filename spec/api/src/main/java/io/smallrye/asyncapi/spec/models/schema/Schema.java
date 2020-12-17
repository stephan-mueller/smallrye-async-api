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
package io.smallrye.asyncapi.spec.models.schema;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import io.smallrye.asyncapi.spec.annotations.schema.SchemaType;
import io.smallrye.asyncapi.spec.models.Constructible;
import io.smallrye.asyncapi.spec.models.Extensible;
import io.smallrye.asyncapi.spec.models.ExternalDocumentation;
import io.smallrye.asyncapi.spec.models.Reference;

/**
 * The Schema Object allows the definition of input and output data types.
 *
 * These types can be objects, but also primitives and arrays
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0/#a-name-schemaobject-a-schema-object"
 */
public interface Schema extends Extensible<Schema>, Constructible, Reference<Schema> {

    /**
     * Returns the title of the schema
     *
     * @return the title of the schema
     */
    String getTitle();

    /**
     * Sets the title of the schema
     *
     * @param title the title of the schema
     */
    void setTitle(String title);

    /**
     * Sets the title of the schema
     *
     * @param title the title of the schema
     * @return this Schema instance
     */
    default Schema title(String title) {
        setTitle(title);
        return this;
    }

    /**
     * Returns the type of the schema
     *
     * @return the type of the schema
     */
    SchemaType getType();

    /**
     * Sets the type of the schema
     *
     * @param type the type of the schema
     */
    void setType(SchemaType type);

    /**
     * Sets the type of the schema
     *
     * @param type the type of the schema
     * @return this Schema instance
     */
    default Schema type(SchemaType type) {
        setType(type);
        return this;
    }

    /**
     * Returns the required types for the schema
     *
     * @return the required types for the schema
     */
    List<String> getRequired();

    /**
     * Sets the required types for the schema
     *
     * @param required the required types for the schema
     */
    void setRequired(List<String> required);

    /**
     * Sets the required types for the schema
     *
     * @param required the required types for the schema
     * @return this Schema instance
     */
    default Schema required(List<String> required) {
        setRequired(required);
        return this;
    }

    /**
     * Adds the given required type to the Schema list of required types.
     *
     * @param required the type to add
     * @return the current Schema object
     **/
    Schema addRequired(String required);

    /**
     * Removes the given required types to the Schema list of required types.
     *
     * @param required the type to remove
     */
    void removeRequired(String required);

    /**
     * Returns the description of the schema
     *
     * @return the description of the schema
     */
    String getDescription();

    /**
     * Sets the description of the schema
     *
     * @param description the description of the schema
     */
    void setDescription(String description);

    /**
     * Sets the description of the schema
     *
     * @param description the description of the schema
     * @return this Schema instance
     */
    default Schema description(String description) {
        setDescription(description);
        return this;
    }

    /**
     * Returns the format of the schema
     *
     * @return the format of the schema
     */
    String getFormat();

    /**
     * Sets the format of the schema
     *
     * @param format the format of the schema
     */
    void setFormat(String format);

    /**
     * Sets the format of the schema
     *
     * @param format the format of the schema
     * @return this Schema instance
     */
    default Schema format(String format) {
        setFormat(format);
        return this;
    }

    /**
     * Returns the name of the schema
     *
     * @return the name of the schema
     */
    String getName();

    /**
     * Sets the name of the schema
     *
     * @param name the name of the schema
     */
    void setName(String name);

    /**
     * Sets the name of the schema
     *
     * @param name the name of the schema
     * @return this Schema instance
     */
    default Schema name(String name) {
        setName(name);
        return this;
    }

    /**
     * Returns the multipleOf property from this Schema instance.
     *
     * minimum: 0
     *
     * @return the positive number that restricts the value of the object
     **/
    BigDecimal getMultipleOf();

    /**
     * Sets the multipleOf property of this Schema instance to the value given.
     *
     * @param multipleOf a positive number that restricts the value of objects described by this Schema
     */
    void setMultipleOf(BigDecimal multipleOf);

    /**
     * Sets the multipleOf property of this Schema instance to the value given.
     *
     * @param multipleOf a positive number that restricts the value of objects described by this Schema
     * @return this Schema instance
     */
    default Schema multipleOf(BigDecimal multipleOf) {
        setMultipleOf(multipleOf);
        return this;
    }

    /**
     * Returns the maximum property from this Schema instance.
     *
     * @return the maximum value of a numeric object
     **/
    BigDecimal getMaximum();

    /**
     * Sets the maximum property of this Schema instance to the value given.
     *
     * @param maximum specifies the maximum numeric value of objects defined by this Schema
     */
    void setMaximum(BigDecimal maximum);

    /**
     * Sets the maximum property of this Schema instance to the value given.
     *
     * @param maximum specifies the maximum numeric value of objects defined by this Schema
     * @return this Schema instance
     */
    default Schema maximum(BigDecimal maximum) {
        setMaximum(maximum);
        return this;
    }

    /**
     * Returns the exclusiveMaximum property from this Schema instance.
     *
     * @return whether the numeric value of objects must be less than the maximum property
     **/
    Boolean getExclusiveMaximum();

    /**
     * Sets the exclusiveMaximum property of this Schema instance to the value given.
     *
     * @param exclusiveMaximum when true the numeric value of objects defined by this Schema must be less than indicated by the
     *        maximum
     *        property
     */
    void setExclusiveMaximum(Boolean exclusiveMaximum);

    /**
     * Sets the exclusiveMaximum property of this Schema instance to the value given.
     *
     * @param exclusiveMaximum when true the numeric value of objects defined by this Schema must be less than indicated by the
     *        maximum
     *        property
     * @return this Schema instance
     */
    default Schema exclusiveMaximum(Boolean exclusiveMaximum) {
        setExclusiveMaximum(exclusiveMaximum);
        return this;
    }

    /**
     * Returns the minimum property from this Schema instance.
     *
     * @return the minimum value of a numeric object
     **/
    BigDecimal getMinimum();

    /**
     * Sets the minimum property of this Schema instance to the value given.
     *
     * @param minimum specifies the minimum numeric value of objects defined by this Schema
     */
    void setMinimum(BigDecimal minimum);

    /**
     * Sets the minimum property of this Schema instance to the value given.
     *
     * @param minimum specifies the minimum numeric value of objects defined by this Schema
     * @return this Schema instance
     */
    default Schema minimum(BigDecimal minimum) {
        setMinimum(minimum);
        return this;
    }

    /**
     * Returns the exclusiveMinimum property from this Schema instance.
     *
     * @return whether the numeric value of objects must be greater than the minimum property
     **/
    Boolean getExclusiveMinimum();

    /**
     * Sets the exclusiveMinimum property of this Schema instance to the value given.
     *
     * @param exclusiveMinimum when true the numeric value of objects defined by this Schema must be greater than indicated by
     *        the minimum
     *        property
     */
    void setExclusiveMinimum(Boolean exclusiveMinimum);

    /**
     * Sets the exclusiveMinimum property of this Schema instance to the value given.
     *
     * @param exclusiveMinimum when true the numeric value of objects defined by this Schema must be greater than indicated by
     *        the minimum
     *        property
     * @return this Schema instance
     */
    default Schema exclusiveMinimum(Boolean exclusiveMinimum) {
        setExclusiveMinimum(exclusiveMinimum);
        return this;
    }

    /**
     * Returns the maxLength property from this Schema instance.
     *
     * minimum: 0
     *
     * @return the maximum length of objects e.g. strings
     **/
    Integer getMaxLength();

    /**
     * Sets the maxLength property of this Schema instance to the value given.
     *
     * @param maxLength the maximum length of objects defined by this Schema
     */
    void setMaxLength(Integer maxLength);

    /**
     * Sets the maxLength property of this Schema instance to the value given.
     *
     * @param maxLength the maximum length of objects defined by this Schema
     * @return this Schema instance
     */
    default Schema maxLength(Integer maxLength) {
        setMaxLength(maxLength);
        return this;
    }

    /**
     * Returns the minLength property from this Schema instance.
     *
     * minimum: 0
     *
     * @return the minimum length of objects e.g. strings
     **/
    Integer getMinLength();

    /**
     * Sets the minLength property of this Schema instance to the value given.
     *
     * @param minLength the minimum length of objects defined by this Schema
     */
    void setMinLength(Integer minLength);

    /**
     * Sets the minLength property of this Schema instance to the value given.
     *
     * @param minLength the minimum length of objects defined by this Schema
     * @return this Schema instance
     */
    default Schema minLength(Integer minLength) {
        setMinLength(minLength);
        return this;
    }

    /**
     * Returns the pattern property from this Schema instance.
     *
     * @return the regular expression which restricts the value of an object e.g. a string
     **/
    String getPattern();

    /**
     * Sets the pattern property of this Schema instance to the string given.
     *
     * @param pattern the regular expression which restricts objects defined by this Schema
     */
    void setPattern(String pattern);

    /**
     * Sets the pattern property of this Schema instance to the string given.
     *
     * @param pattern the regular expression which restricts objects defined by this Schema
     * @return this Schema instance
     */
    default Schema pattern(String pattern) {
        setPattern(pattern);
        return this;
    }

    /**
     * Returns the maxItems property from this Schema instance.
     *
     * minimum: 0
     *
     * @return the maximum number of elements in the object e.g. array elements
     **/
    Integer getMaxItems();

    /**
     * Sets the maxItems property of this Schema instance to the value given.
     *
     * @param maxItems the maximum number of elements in objects defined by this Schema e.g. array elements
     */
    void setMaxItems(Integer maxItems);

    /**
     * Sets the maxItems property of this Schema instance to the value given.
     *
     * @param maxItems the maximum number of elements in objects defined by this Schema e.g. array elements
     * @return this Schema instance
     */
    default Schema maxItems(Integer maxItems) {
        setMaxItems(maxItems);
        return this;
    }

    /**
     * Returns the minItems property from this Schema instance.
     *
     * minimum: 0
     *
     * @return the minimum number of elements in the object e.g. array elements
     **/
    Integer getMinItems();

    /**
     * Sets the minItems property of this Schema instance to the value given.
     *
     * @param minItems the minimum number of elements in objects defined by this Schema e.g. array elements
     */
    void setMinItems(Integer minItems);

    /**
     * Sets the minItems property of this Schema instance to the value given.
     *
     * @param minItems the minimum number of elements in objects defined by this Schema e.g. array elements
     * @return this Schema instance
     */
    default Schema minItems(Integer minItems) {
        setMinItems(minItems);
        return this;
    }

    /**
     * Returns the uniqueItems property from this Schema instance.
     *
     * @return whether to ensure items are unique
     **/
    Boolean getUniqueItems();

    /**
     * Sets the uniqueItems property of this Schema instance to the value given.
     *
     * @param uniqueItems ensure the items (e.g. array elements) are unique in objects defined by this Schema
     */
    void setUniqueItems(Boolean uniqueItems);

    /**
     * Sets the uniqueItems property of this Schema instance to the value given.
     *
     * @param uniqueItems ensure the items (e.g. array elements) are unique in objects defined by this Schema
     * @return this Schema instance
     */
    default Schema uniqueItems(Boolean uniqueItems) {
        setUniqueItems(uniqueItems);
        return this;
    }

    /**
     * Returns the maxProperties property from this Schema instance.
     *
     * minimum: 0
     *
     * @return the maximum number of properties allowed in the object
     **/
    Integer getMaxProperties();

    /**
     * Sets the maxProperties property of this Schema instance to the value given.
     *
     * @param maxProperties limit the number of properties in objects defined by this Schema
     */
    void setMaxProperties(Integer maxProperties);

    /**
     * Sets the maxProperties property of this Schema instance to the value given.
     *
     * @param maxProperties limit the number of properties in objects defined by this Schema
     * @return this Schema instance
     */
    default Schema maxProperties(Integer maxProperties) {
        setMaxProperties(maxProperties);
        return this;
    }

    /**
     * Returns the minProperties property from this Schema instance.
     *
     * minimum: 0
     *
     * @return the minimum number of properties allowed in the object
     **/
    Integer getMinProperties();

    /**
     * Sets the minProperties property of this Schema instance to the value given.
     *
     * @param minProperties limit the number of properties in objects defined by this Schema
     */
    void setMinProperties(Integer minProperties);

    /**
     * Sets the minProperties property of this Schema instance to the value given.
     *
     * @param minProperties limit the number of properties in objects defined by this Schema
     * @return this Schema instance
     */
    default Schema minProperties(Integer minProperties) {
        setMinProperties(minProperties);
        return this;
    }

    /**
     * Returns the enumerated list of values allowed for objects defined by this Schema.
     *
     * @return a copy List (potentially immutable) of values allowed for objects defined by this Schema
     */
    List<String> getEnumeration();

    /**
     * Sets the enumerated list of values allowed for objects defined by this Schema.
     *
     * @param enumeration a list of values allowed
     */
    void setEnumeration(List<String> enumeration);

    /**
     * Sets the enumerated list of values allowed for objects defined by this Schema.
     *
     * @param enumeration a list of values allowed
     * @return this Schema instance
     */
    default Schema enumeration(List<String> enumeration) {
        setEnumeration(enumeration);
        return this;
    }

    /**
     * Adds the given enumeration to the Schema list of enumerations.
     *
     * @param enumeration the enumeration to add
     * @return the current Schema object
     **/
    Schema addEnumeration(String enumeration);

    /**
     * Removes the given enumeration to the Schema list of enumerations.
     *
     * @param enumeration the enumeration to remove
     */
    void removeEnumeration(String enumeration);

    /**
     * Equivalent to an "enum" with a single value.
     *
     * @return the value allowed for a object defined by this Schema.
     */
    Schema getConstant();

    /**
     * Sets a value allowed for a object defined by this Schema.
     *
     * @param constant the value allowed for a object defined by this Schema.
     */
    void setConstant(Schema constant);

    /**
     * Sets a value allowed for a object defined by this Schema.
     *
     * @param constant the value allowed for a object defined by this Schema.
     * @return this Schema instance
     */
    default Schema constant(Schema constant) {
        setConstant(constant);
        return this;
    }

    /**
     * Returns the example of the schema
     *
     * @return the example of the schema
     */
    String getExample();

    /**
     * Sets the example of the example
     *
     * @param example the example of the schema
     */
    void setExample(String example);

    /**
     * Sets the example of the schema
     *
     * @param example the example of the schema
     * @return this Schema instance
     */
    default Schema example(String example) {
        setExample(example);
        return this;
    }

    /**
     * Returns the readOnly property from this Schema instance.
     *
     * @return indication that the Schema is only valid in a response message
     **/
    Boolean getReadOnly();

    /**
     * Sets the readOnly property of this Schema. Only valid when the Schema is the property in an object.
     *
     * @param readOnly true indicates the Schema should not be sent as part of a request message
     */
    void setReadOnly(Boolean readOnly);

    /**
     * Sets the readOnly property of this Schema. Only valid when the Schema is the property in an object.
     *
     * @param readOnly true indicates the Schema should not be sent as part of a request message
     * @return this Schema instance
     */
    default Schema readOnly(Boolean readOnly) {
        setReadOnly(readOnly);
        return this;
    }

    /**
     * Returns the writeOnly property from this Schema instance.
     *
     * @return indication that the Schema is only valid in a request message
     **/
    Boolean getWriteOnly();

    /**
     * Sets the writeOnly property of this Schema. Only valid when the Schema is the property in an object.
     *
     * @param writeOnly true indicates the Schema should not be sent as part of a response message
     */
    void setWriteOnly(Boolean writeOnly);

    /**
     * Sets the writeOnly property of this Schema. Only valid when the Schema is the property in an object.
     *
     * @param writeOnly true indicates the Schema should not be sent as part of a response message
     * @return this Schema instance
     */
    default Schema writeOnly(Boolean writeOnly) {
        setWriteOnly(writeOnly);
        return this;
    }

    /**
     * Returns the properties from this Schema instance.
     *
     * @return property from this Schema instance
     **/
    Map<String, Schema> getProperties();

    /**
     * Sets the properties from this Schema instance
     *
     * @param properties from this Schema instance
     */
    void setProperties(Map<String, Schema> properties);

    /**
     * Sets the properties from this Schema instance
     *
     * @param properties from this Schema instance
     * @return this Schema instance
     */
    default Schema properties(Map<String, Schema> properties) {
        setProperties(properties);
        return this;
    }

    /**
     * Adds the given schema property to this Schema' map of properties.
     *
     * @param key of the schema
     * @param property to be added to the SchemaProperty
     * @return the current Schema object
     */
    Schema addProperty(String key, Schema property);

    /**
     * Removes the given schema property from this Schema' list of properties.
     *
     * @param key of the schema
     */
    void removeProperty(String key);

    // TODO: patternProperties

    /**
     * Returns the value of the "additionalProperties" setting, which indicates whether properties not otherwise defined are
     * allowed.
     *
     * This setting MUST either be a {@link Boolean} or {@link Schema}, they can not be set both at the same time.
     *
     * This method returns a {@link Schema}, for the {@link Boolean} getter use {@link #getAdditionalPropertiesBoolean()}
     * <ul>
     * <li>If "additionalProperties" is a Schema, then additional properties are allowed but
     * should conform to the Schema.</li>
     * </ul>
     *
     * @return this Schema's additionalProperties property (as {@link Schema})
     */
    Schema getAdditionalPropertiesSchema();

    /**
     * Sets the Schema which defines additional properties not defined by "properties" or "patternProperties".
     *
     * See the javadoc for {@link Schema#getAdditionalPropertiesSchema()} for more details on this setting. Note that this
     * version of the
     * setter is mutually exclusive with the {@link Boolean} variants (see {@link #setAdditionalPropertiesBoolean(Boolean)}).
     *
     * @param additionalProperties a Schema which defines additional properties
     */
    void setAdditionalPropertiesSchema(Schema additionalProperties);

    /**
     * Returns the value of the "additionalProperties" setting, which indicates whether properties not otherwise defined are
     * allowed.
     *
     * This setting MUST either be a {@link Boolean} or {@link Schema}, they can not be set both at the same time.
     *
     * This method returns a {@link Boolean}, for the {@link Schema} getter use {@link #getAdditionalPropertiesSchema()}
     * <ul>
     * <li>If "additionalProperties" is true, then any additional properties are allowed.</li>
     *
     * <li>If "additionalProperties" is false, then only properties covered by the "properties"
     * and "patternProperties" are allowed.</li>
     * </ul>
     *
     * @return this Schema's additionalProperties property (as {@link Boolean})
     */
    Boolean getAdditionalPropertiesBoolean();

    /**
     * Sets the value of "additionalProperties" to either True or False.
     *
     * See the javadoc for {@link Schema#getAdditionalPropertiesBoolean()} for more details on this setting. Note that this
     * version of the
     * setter is mutually exclusive with the {@link Schema} variants (see {@link #setAdditionalPropertiesSchema(Schema)}).
     *
     * @param additionalProperties a Schema which defines additional properties
     */
    void setAdditionalPropertiesBoolean(Boolean additionalProperties);

    /**
     * Sets the Schema which defines additional properties not defined by "properties" or "patternProperties".
     *
     * See the javadoc for {@link Schema#getAdditionalPropertiesSchema()} for more details on this setting. Note that this
     * version of the
     * setter is mutually exclusive with the {@link Boolean} variants (see {@link #additionalPropertiesBoolean(Boolean)}).
     *
     * @param additionalProperties a Schema which defines additional properties
     * @return the current Schema instance
     */
    default Schema additionalPropertiesSchema(Schema additionalProperties) {
        setAdditionalPropertiesSchema(additionalProperties);
        return this;
    }

    /**
     * Sets the value of "additionalProperties" to either True or False.
     *
     * See the javadoc for {@link Schema#getAdditionalPropertiesBoolean()} for more details on this setting. Note that this
     * version of the
     * setter is mutually exclusive with the {@link Schema} variants (see {@link #additionalPropertiesSchema(Schema)}).
     *
     * @param additionalProperties a Schema which defines additional properties
     * @return the current Schema instance
     */
    default Schema additionalPropertiesBoolean(Boolean additionalProperties) {
        setAdditionalPropertiesBoolean(additionalProperties);
        return this;
    }

    // TODO: additionalItems

    /**
     * Returns the Schema used for all the elements of an array typed Schema.
     *
     * @return the Schema used for all the elements
     **/
    Schema getItems();

    /**
     * Set the Schema used for all the elements of an array typed Schema.
     *
     * @param items the Schema used by this array
     */
    void setItems(Schema items);

    /**
     * Set the Schema used for all the elements of an array typed Schema.
     *
     * @param items the Schema used by this array
     * @return the current Schema instance
     */
    default Schema items(Schema items) {
        setItems(items);
        return this;
    }
    // TODO: propertyName

    // TODO: contains

    /**
     * Returns the schemas used by the allOf property.
     *
     * @return a copy List (potentially immutable) of schemas used by the allOf property
     **/
    List<Schema> getAllOf();

    /**
     * Sets the schemas used by the allOf property of this Schema.
     *
     * @param allOf the list of schemas used by the allOf property
     */
    void setAllOf(List<Schema> allOf);

    /**
     * Sets the schemas used by the allOf property of this Schema.
     *
     * @param allOf the list of schemas used by the allOf property
     * @return this Schema instance
     */
    default Schema allOf(List<Schema> allOf) {
        setAllOf(allOf);
        return this;
    }

    /**
     * Adds the given schema to the Schema list of allOf schemas.
     *
     * @param allOf the schema to add
     * @return the current Schema object
     **/
    Schema addAllOf(Schema allOf);

    /**
     * Removes the given schema to the Schema list of allOf schemas.
     *
     * @param allOf allOf the schema to remove
     */
    void removeAllOf(Schema allOf);

    /**
     * Returns the schemas used by the oneOf property.
     *
     * @return a copy List (potentially immutable) of schemas used by the oneOf property
     **/
    List<Schema> getOneOf();

    /**
     * Sets the schemas used by the oneOf property of this Schema.
     *
     * @param oneOf the list of schemas used by the oneOf property
     */
    void setOneOf(List<Schema> oneOf);

    /**
     * Sets the schemas used by the oneOf property of this Schema.
     *
     * @param oneOf the list of schemas used by the oneOf property
     * @return this Schema instance
     */
    default Schema oneOf(List<Schema> oneOf) {
        setOneOf(oneOf);
        return this;
    }

    /**
     * Adds the given schema to the Schema list of oneOf schemas.
     *
     * @param oneOf the schema to add
     * @return the current Schema object
     **/
    Schema addOneOf(Schema oneOf);

    /**
     * Removes the given schema to the Schema list of oneOf schemas.
     *
     * @param oneOf oneOf the schema to remove
     */
    void removeOneOf(Schema oneOf);

    /**
     * Returns the schemas used by the anyOf property.
     *
     * @return a copy List (potentially immutable) of schemas used by the anyOf property
     **/
    List<Schema> getAnyOf();

    /**
     * Sets the schemas used by the anyOf property of this Schema.
     *
     * @param anyOf the list of schemas used by the anyOf property
     */
    void setAnyOf(List<Schema> anyOf);

    /**
     * Sets the schemas used by the anyOf property of this Schema.
     *
     * @param anyOf the list of schemas used by the anyOf property
     * @return this Schema instance
     */
    default Schema anyOf(List<Schema> anyOf) {
        setAnyOf(anyOf);
        return this;
    }

    /**
     * Adds the given schema to the Schema list of anyOf schemas.
     *
     * @param anyOf the schema to add
     * @return the current Schema object
     **/
    Schema addAnyOf(Schema anyOf);

    /**
     * Removes the given schema to the Schema list of anyOf schemas.
     *
     * @param anyOf anyOf the schema to remove
     */
    void removeAnyOf(Schema anyOf);

    /**
     * Returns a Schema which describes properties not allowed in objects defined by the current schema.
     *
     * @return the not property's schema
     **/
    Schema getNot();

    /**
     * Sets the not property to a Schema which describes properties not allowed in objects defined by the current schema.
     *
     * @param not the Schema which describes properties not allowed
     */
    void setNot(Schema not);

    /**
     * Sets the not property to a Schema which describes properties not allowed in objects defined by the current schema.
     *
     * @param not the Schema which describes properties not allowed
     * @return this Schema instance
     */
    default Schema not(Schema not) {
        setNot(not);
        return this;
    }

    /**
     * Returns the documentation of the Schema Object
     *
     * @return the documentation of the Schema Object
     */
    ExternalDocumentation getExternalDocs();

    /**
     * Sets the documentation of the Schema Object
     *
     * @param externalDocs the documentation of the Schema Object
     */
    void setExternalDocs(ExternalDocumentation externalDocs);

    /**
     * Sets the documentation of the Schema Object
     *
     * @param externalDocs the documentation of the Schema Object
     * @return this Schema instance
     */
    default Schema externalDocs(ExternalDocumentation externalDocs) {
        setExternalDocs(externalDocs);
        return this;
    }
}
