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

import org.jboss.jandex.DotName;

import io.smallrye.asyncapi.spec.annotations.schema.Schema;

public class SchemaConstant {

    public static final DotName DOTNAME_SCHEMA = DotName.createSimple(Schema.class.getName());

    public static final String PROP_NAME = "name";

    public static final String PROP_XML = "xml";

    public static final String PROP_REQUIRED = "required";

    public static final String PROP_IMPLEMENTATION = "implementation";

    public static final String PROP_TYPE = "type";

    public static final String PROP_FORMAT = "format";

    public static final String PROP_PATTERN = "pattern";

    public static final String PROP_EXAMPLE = "example";

    public static final String PROP_MIN_PROPERTIES = "minProperties";

    public static final String PROP_ALL_OF = "allOf";

    public static final String PROP_MAX_ITEMS = "maxItems";

    public static final String PROP_EXCLUSIVE_MINIMUM = "exclusiveMinimum";

    public static final String PROP_DEFAULT_VALUE = "defaultValue";

    public static final String PROP_DISCRIMINATOR_MAPPING = "discriminatorMapping";

    public static final String PROP_ANY_OF = "anyOf";

    public static final String PROP_MULTIPLE_OF = "multipleOf";

    public static final String PROP_DEPRECATED = "deprecated";

    public static final String PROP_MINIMUM = "minimum";

    public static final String PROP_DISCRIMINATOR_PROPERTY = "discriminatorProperty";

    public static final String PROP_MAXIMUM = "maximum";

    public static final String PROP_READ_ONLY = "readOnly";

    public static final String PROP_TITLE = "title";

    public static final String PROP_NULLABLE = "nullable";

    public static final String PROP_UNIQUE_ITEMS = "uniqueItems";

    public static final String PROP_DESCRIPTION = "description";

    public static final String PROP_MIN_LENGTH = "minLength";

    // for annotations (reserved words in Java)
    public static final String PROP_ENUMERATION = "enumeration";

    public static final String PROP_MAX_LENGTH = "maxLength";

    public static final String PROP_EXCLUSIVE_MAXIMUM = "exclusiveMaximum";

    public static final String PROP_WRITE_ONLY = "writeOnly";

    public static final String PROP_VALUE = "value";

    public static final String PROP_MIN_ITEMS = "minItems";

    public static final String PROP_ONE_OF = "oneOf";

    public static final String PROP_MAX_PROPERTIES = "maxProperties";

    // Only in SchemaFactory ?
    public static final String PROP_REQUIRED_PROPERTIES = "requiredProperties";

    public static final String PROP_PROPERTIES = "properties";

    public static final String PROP_NOT = "not";

    private SchemaConstant() {
    }

}
