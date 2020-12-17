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
package io.smallrye.asyncapi.spec.annotations.schema;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.smallrye.asyncapi.spec.annotations.ExternalDocumentation;

/**
 * The Schema Object allows the definition of input and output data types. These types can be objects, but also primitives and
 * arrays
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0/#a-name-schemaobject-a-schema-object"
 */
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Schema {

    /**
     * A title to explain the purpose of the schema.
     *
     * @return the title of the schema
     **/
    String title() default "";

    /**
     *
     * This property provides a reference to an object defined elsewhere.
     *
     * This property and all other properties are mutually exclusive. If other properties are defined in addition to the ref
     * property then the
     * result is undefined.
     *
     * @return a reference to a schema definition
     **/
    String ref() default "";

    /**
     * Provides an override for the basic type of the schema.
     *
     * Value MUST be a string. Multiple types via an array are not supported. MUST be a valid type per the AsyncAPI
     * Specification.
     *
     * @return the type of this schema
     **/
    SchemaType type() default SchemaType.DEFAULT;

    /**
     * The name of a property in the instance
     *
     * @return array of required types for this schema
     */
    String[] required() default {};

    /**
     * A description of the schema.
     *
     * @return this schema's description
     **/
    String description() default "";

    /**
     * Provides an optional override for the format.
     *
     * If a consumer is unaware of the meaning of the format, they shall fall back to using the basic type without format.
     *
     * For example, if \&quot;type: integer, format: int128\&quot; were used to designate a very large integer, most consumers
     * will not
     * understand how to handle it, and fall back to simply \&quot;type: integer\&quot;
     *
     * @return this schema's format
     **/
    String format() default "";

    /**
     * The name of the schema or property.
     *
     * The name is REQUIRED when the schema is defined within {@link io.smallrye.asyncapi.spec.annotations.Components}.
     *
     * The name will be used as the key to add this schema to the 'schemas' map for reuse.
     *
     * @return the name of the schema
     **/
    String name() default "";

    /**
     * Constrains a value such that when divided by the multipleOf, the remainder must be an integer. Ignored if the value is 0.
     *
     * @return the multiplier constraint of the schema
     **/
    double multipleOf() default 0;

    /**
     * Sets the maximum numeric value for a property. Value must be a valid number. Ignored if the value is an empty string or
     * not a number.
     *
     * @return the maximum value for this schema
     **/
    String maximum() default "";

    /**
     * If true, makes the maximum value exclusive, or a less-than criteria.
     *
     * @return the exclusive maximum value for this schema
     **/
    boolean exclusiveMaximum() default false;

    /**
     * Sets the minimum numeric value for a property. Value must be a valid number. Ignored if the value is an empty string or
     * not a number.
     *
     * @return the minimum value for this schema
     **/
    String minimum() default "";

    /**
     * If true, makes the minimum value exclusive, or a greater-than criteria.
     *
     * @return the exclusive minimum value for this schema
     **/
    boolean exclusiveMinimum() default false;

    /**
     * Sets the maximum length of a string value. Ignored if the value is negative.
     *
     * @return the maximum length of this schema
     **/
    int maxLength() default Integer.MAX_VALUE;

    /**
     * Sets the minimum length of a string value. Ignored if the value is negative.
     *
     * @return the minimum length of this schema
     **/
    int minLength() default 0;

    /**
     * A pattern that the value must satisfy. Ignored if the value is an empty string.
     *
     * @return the pattern of this schema
     **/
    String pattern() default "";

    /**
     * Only applicable if type=array. Sets the maximum number of items in an array.
     *
     * This integer MUST be greater than, or equal to, 0. An array instance is valid against "maxItems" if its size is less
     * than, or equal to,
     * the value of this keyword. Ignored if value is Integer.MIN_VALUE.
     *
     * @return the maximum number of items in this array
     **/
    int maxItems() default Integer.MIN_VALUE;

    /**
     * Only applicable if type=array. Sets the minimum number of items in an array.
     *
     * This integer MUST be greater than, or equal to, 0. An array instance is valid against "minItems" if its size is greater
     * than, or equal
     * to, the value of this keyword. Ignored if value is Integer.MAX_VALUE.
     *
     * @return the minimum number of items in this array
     **/
    int minItems() default Integer.MAX_VALUE;

    /**
     * Only applicable if type=array. Determines if the items in the array SHOULD be unique.
     *
     * If false, the instance validates successfully. If true, the instance validates successfully if all of its elements are
     * unique.
     *
     * @return whether the items in this array are unique
     **/
    boolean uniqueItems() default false;

    /**
     * Constrains the number of arbitrary properties when additionalProperties is defined. Ignored if value is 0.
     *
     * @return the maximum number of properties for this schema
     **/
    int maxProperties() default 0;

    /**
     * Constrains the number of arbitrary properties when additionalProperties is defined. Ignored if value is 0.
     *
     * @return the minimum number of properties for this schema
     **/
    int minProperties() default 0;

    /**
     * Provides a list of enum values. Corresponds to the enum property in the OAS schema and the enumeration property in the
     * schema model.
     *
     * @return a list of allowed schema values
     */
    String[] enumeration() default {};

    /**
     * Equivalent to an "enum" with a single value.
     *
     * @return allowed schema values
     */
    Class<?> constant() default Void.class;

    /**
     * A free-form property to include an example of an instance for this schema.
     *
     * To represent examples that cannot be naturally represented in JSON or YAML, a string value is used to contain the example
     * with escaping
     * where necessary. When associated with a specific media type, the example string shall be parsed by the consumer to be
     * treated as an
     * object or an array.
     *
     * @return an example of this schema
     **/
    String example() default "";

    // TODO: if

    // TODO: then

    // TODO: else

    /**
     * Relevant only for Schema "properties" definitions. Declares the property as "read only".
     *
     * This means that it MAY be sent as part of a response but SHOULD NOT be sent as part of the request. If the property is
     * marked as
     * readOnly being true and is in the required list, the required will take effect on the response only. A property MUST NOT
     * be marked as
     * both readOnly and writeOnly being true.
     *
     * @return whether or not this schema is read only
     **/
    boolean readOnly() default false;

    /**
     * Relevant only for Schema "properties" definitions. Declares the property as "write only".
     *
     * Therefore, it MAY be sent as part of a request but SHOULD NOT be sent as part of the response. If the property is marked
     * as writeOnly
     * being true and is in the required list, the required will take effect on the request only. A property MUST NOT be marked
     * as both
     * readOnly and writeOnly being true.
     *
     * @return whether or not this schema is write only
     **/
    boolean writeOnly() default false;

    /**
     * Provides a list of properties present in this schema.
     *
     * Use of the properties list does not preclude the annotation scanning runtime from also including other properties found
     * in the scan
     * process. For example, if an implementation is also specified, the final set of properties used by the annotation scanner
     * will include
     * properties from both this list and those found from introspection of the implementation class, if any.
     *
     * In the case where properties are specified here in addition to an implementation,
     * the attributes given for a property using a {@link SchemaProperty} will override the same attributes scanned (or derived)
     * from the
     * implementation class.
     *
     * Example:
     * 
     * <pre>
     * {@literal @}Schema(properties = {
     *   {@literal @}SchemaProperty(name = "creditCard", example = "4567100043210001"),
     *   {@literal @}SchemaProperty(name = "departureFlight", description = "The departure flight information."),
     *   {@literal @}SchemaProperty(name = "returningFlight")
     * })
     * </pre>
     *
     * @return a list of defined properties
     **/
    SchemaProperty[] properties() default {};

    // TODO: patternProperties

    // TODO: additionalProperties

    // TODO: additionalItems

    // TODO: items

    // TODO: propertyName

    // TODO: contains

    /**
     * Provides an array of java class implementations which can be used to describe multiple acceptable schemas.
     *
     * If all match, the schema will be considered valid. Inline or referenced schema MUST be of a Schema Object and not a
     * standard JSON
     * Schema.
     *
     * @return the list of classes to match
     **/
    Class<?>[] allOf() default {};

    /**
     * Provides an array of java class implementations which can be used to describe multiple acceptable schemas.
     *
     * If more than one match the derived schemas, a validation error will occur. Inline or referenced schema MUST be of a
     * Schema Object and
     * not a standard JSON Schema.
     *
     * @return the list of possible classes for a single match
     **/
    Class<?>[] oneOf() default {};

    /**
     * Provides an array of java class implementations which can be used to describe multiple acceptable schemas.
     *
     * If any match, the schema will be considered valid. Inline or referenced schema MUST be of a Schema Object and not a
     * standard JSON
     * Schema.
     *
     * @return the list of possible class matches
     **/
    Class<?>[] anyOf() default {};

    /**
     * Provides a java class to be used to disallow matching properties.
     *
     * Inline or referenced schema MUST be of a Schema Object and not a standard JSON Schema.
     *
     * @return a class with disallowed properties
     **/
    Class<?> not() default Void.class;

    /**
     * ExternalDocumentation of the Schema Object
     *
     * @return ExternalDocumentation
     */
    ExternalDocumentation externalDoc() default @ExternalDocumentation(url = "");
}
