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

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import io.smallrye.asyncapi.core.api.constants.JDKConstants;
import io.smallrye.asyncapi.core.runtime.scanner.AnnotationScannerExtension;
import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.AnnotationValue;
import org.jboss.jandex.ArrayType;
import org.jboss.jandex.ClassInfo;
import org.jboss.jandex.ClassType;
import org.jboss.jandex.FieldInfo;
import org.jboss.jandex.ParameterizedType;
import org.jboss.jandex.Type;

import io.smallrye.asyncapi.core.api.constants.AsyncApiConstants;
import io.smallrye.asyncapi.core.api.models.schema.SchemaImpl;
import io.smallrye.asyncapi.core.api.util.MergeUtil;
import io.smallrye.asyncapi.core.runtime.io.CurrentScannerInfo;
import io.smallrye.asyncapi.core.runtime.io.IoLogging;
import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.io.extension.ExtensionReader;
import io.smallrye.asyncapi.core.runtime.io.externaldocs.ExternalDocsConstant;
import io.smallrye.asyncapi.core.runtime.io.externaldocs.ExternalDocsReader;
import io.smallrye.asyncapi.core.runtime.scanner.AsyncApiDataObjectScanner;
import io.smallrye.asyncapi.core.runtime.scanner.SchemaRegistry;
import io.smallrye.asyncapi.core.runtime.scanner.spi.AnnotationScanner;
import io.smallrye.asyncapi.core.runtime.scanner.spi.AnnotationScannerContext;
import io.smallrye.asyncapi.core.runtime.util.JandexUtil;
import io.smallrye.asyncapi.core.runtime.util.TypeUtil;
import io.smallrye.asyncapi.spec.annotations.schema.SchemaType;
import io.smallrye.asyncapi.spec.models.schema.Schema;
import io.smallrye.asyncapi.spec.models.schema.SchemaProperty;

public class SchemaFactory {

    /**
     * Reads a Schema annotation into a model.
     *
     * @param context scanning context
     * @param value the annotation value
     * @return Schema model
     */
    public static Schema readSchema(final AnnotationScannerContext context, AnnotationValue value) {
        if (value == null) {
            return null;
        }

        AnnotationInstance schemaAnnotation = value.asNested();

        return readSchema(context, new SchemaImpl(), schemaAnnotation, Collections.emptyMap());
    }

    /**
     * Populates the schema using the {@link Schema @Schema}
     * on the provided class. If the schema has already been registered (in components), the existing
     * registration will be replaced.
     *
     * @param context scanning context
     * @param schema schema model to populate
     * @param annotation schema annotation to read
     * @param clazz the class annotated with {@link Schema @Schema}
     * @param defaults default values to be set on the schema when not present in the annotation
     * @return the schema, possibly replaced if <code>implementation</code> has been specified in the annotation
     */
    static Schema readSchema(final AnnotationScannerContext context, Schema schema, AnnotationInstance annotation,
            ClassInfo clazz,
            Map<String, Object> defaults) {

        if (annotation == null) {
            return schema;
        }

        schema = readSchema(context, schema, annotation, defaults);
        ClassType clazzType = (ClassType) Type.create(clazz.name(), Type.Kind.CLASS);

        /*
         * The registry may already contain the type from earlier in the scan if the
         * type has been referenced as a field, etc. The schema here is "fuller" as it
         * now contains information gathered from the @Schema annotation on the class.
         *
         * Ignore the reference returned by register, the caller expects the full schema.
         */
        schemaRegistration(context, clazzType, schema);

        return schema;
    }

    /**
     * Register the provided schema in the SchemaRegistry if allowed.
     *
     * @param context scanning context
     * @param type the type of the schema to register
     * @param schema a schema
     * @return a reference to the registered schema or the input schema when registration is not allowed/possible
     */
    public static Schema schemaRegistration(final AnnotationScannerContext context, Type type, Schema schema) {
        SchemaRegistry schemaRegistry = SchemaRegistry.currentInstance();

        if (allowRegistration(context, schemaRegistry, type, schema)) {
            schema = schemaRegistry.register(type, schema);
        } else if (schemaRegistry != null && schemaRegistry.hasRef(type)) {
            schema = schemaRegistry.lookupRef(type);
        }

        return schema;
    }

    /**
     * Determines if the give schema may be registered. Schemas may only be registered
     * if non-null; the type is allowed for registration; and a schema for the given type
     * is not already in the registry or the schema being registered is not already a
     * reference that has been registered.
     *
     * @param context scanning context
     * @param registry
     * @param type
     * @param schema
     * @return true if the schema may be registered, otherwise false
     */
    static boolean allowRegistration(final AnnotationScannerContext context, SchemaRegistry registry, Type type,
            Schema schema) {
        if (schema == null || registry == null || !TypeUtil.allowRegistration(context, type)) {
            return false;
        }

        /*
         * Only register if the type is not already registered
         */
        return !registry.hasSchema(type);
    }

    /**
     * Convert a Jandex enum class type to a {@link Schema} model.Adds each enum constant name to the list of the given schema's
     * enumeration list.
     * <p>
     * The given type must be found in the index.
     *
     * @param context scanning context
     * @param enumType type containing Java Enum constants
     * @return Schema model
     * @see java.lang.reflect.Field#isEnumConstant()
     */
    public static Schema enumToSchema(final AnnotationScannerContext context, Type enumType) {
        IoLogging.logger.enumProcessing(enumType);
        final int ENUM = 0x00004000; // see java.lang.reflect.Modifier#ENUM
        ClassInfo enumKlazz = context.getIndex()
                .getClassByName(TypeUtil.getName(enumType));
        AnnotationInstance schemaAnnotation = enumKlazz.classAnnotation(SchemaConstant.DOTNAME_SCHEMA);
        Schema enumSchema = new SchemaImpl();
        List<String> enumeration = enumKlazz.fields()
                .stream()
                .filter(field -> (field.flags() & ENUM) != 0)
                .map(FieldInfo::name)
                .sorted() // Make the order determinate
                .collect(Collectors.toList());

        if (schemaAnnotation != null) {
            Map<String, Object> defaults = new HashMap<>(2);
            defaults.put(SchemaConstant.PROP_TYPE, SchemaType.STRING);
            defaults.put(SchemaConstant.PROP_ENUMERATION, enumeration);

            enumSchema = readSchema(context, enumSchema, schemaAnnotation, enumKlazz, defaults);
        } else {
            enumSchema.setType(SchemaType.STRING);
            enumSchema.setEnumeration(enumeration);
        }

        return enumSchema;
    }

    /**
     * Populates the schema using the {@link Schema @Schema}
     * on the provided class. If the schema has already been registered (in components), the existing
     * registration will be replaced.
     *
     * @param context scanning context
     * @param schema schema model to populate
     * @param annotation schema annotation to read
     * @param clazz the class annotated with {@link Schema @Schema}
     * @return the schema, possibly replaced if <code>implementation</code> has been specified in the annotation
     */
    public static Schema readSchema(final AnnotationScannerContext context, Schema schema, AnnotationInstance annotation,
            ClassInfo clazz) {
        return readSchema(context, schema, annotation, clazz, Collections.emptyMap());
    }

    public static Schema readSchema(final AnnotationScannerContext context, Schema schema, AnnotationInstance annotation,
            Map<String, Object> defaults) {

        if (annotation == null) {
            return schema;
        }

        schema.setNot(SchemaFactory.<Type, Schema> readAttr(annotation, SchemaConstant.PROP_NOT,
                type -> readClassSchema(context, type, true), defaults));
        schema.setOneOf(SchemaFactory.<Type[], List<Schema>> readAttr(annotation, SchemaConstant.PROP_ONE_OF,
                type -> readClassSchemas(context, type), defaults));
        schema.setAnyOf(SchemaFactory.<Type[], List<Schema>> readAttr(annotation, SchemaConstant.PROP_ANY_OF,
                type -> readClassSchemas(context, type), defaults));
        schema.setAllOf(SchemaFactory.<Type[], List<Schema>> readAttr(annotation, SchemaConstant.PROP_ALL_OF,
                type -> readClassSchemas(context, type), defaults));
        schema.setTitle(readAttr(annotation, SchemaConstant.PROP_TITLE, defaults));
        schema.setMultipleOf(SchemaFactory.<Double, BigDecimal> readAttr(annotation, SchemaConstant.PROP_MULTIPLE_OF,
                BigDecimal::valueOf, defaults));
        schema.setMaximum(SchemaFactory.<String, BigDecimal> readAttr(annotation, SchemaConstant.PROP_MAXIMUM, BigDecimal::new,
                defaults));
        schema.setMinimum(SchemaFactory.<String, BigDecimal> readAttr(annotation, SchemaConstant.PROP_MINIMUM, BigDecimal::new,
                defaults));
        schema.setExclusiveMaximum(readAttr(annotation, SchemaConstant.PROP_EXCLUSIVE_MAXIMUM, defaults));
        schema.setExclusiveMinimum(readAttr(annotation, SchemaConstant.PROP_EXCLUSIVE_MINIMUM, defaults));
        schema.setMaxLength(readAttr(annotation, SchemaConstant.PROP_MAX_LENGTH, defaults));
        schema.setMinLength(readAttr(annotation, SchemaConstant.PROP_MIN_LENGTH, defaults));
        schema.setPattern(readAttr(annotation, SchemaConstant.PROP_PATTERN, defaults));
        schema.setMaxProperties(readAttr(annotation, SchemaConstant.PROP_MAX_PROPERTIES, defaults));
        schema.setMinProperties(readAttr(annotation, SchemaConstant.PROP_MIN_PROPERTIES, defaults));
        schema.setRequired(readAttr(annotation, SchemaConstant.PROP_REQUIRED_PROPERTIES, defaults));
        schema.setDescription(readAttr(annotation, SchemaConstant.PROP_DESCRIPTION, defaults));
        schema.setFormat(readAttr(annotation, SchemaConstant.PROP_FORMAT, defaults));
        schema.setRef(readAttr(annotation, AsyncApiConstants.REF, defaults));
        schema.setReadOnly(readAttr(annotation, SchemaConstant.PROP_READ_ONLY, defaults));
        schema.setWriteOnly(readAttr(annotation, SchemaConstant.PROP_WRITE_ONLY, defaults));
        AnnotationInstance externalDocsAnnotation = JandexUtil.value(annotation, ExternalDocsConstant.PROP_EXTERNAL_DOCS);
        schema.setExternalDocs(ExternalDocsReader.readExternalDocs(context, externalDocsAnnotation));
        final SchemaType schemaType = SchemaFactory.<String, SchemaType> readAttr(annotation, SchemaConstant.PROP_TYPE,
                value -> JandexUtil.enumValue(value, SchemaType.class), defaults);
        schema.setType(schemaType);
        schema.setExample((String) parseSchemaAttr(annotation, SchemaConstant.PROP_EXAMPLE, defaults, schemaType));
        schema.setMaxItems(readAttr(annotation, SchemaConstant.PROP_MAX_ITEMS, defaults));
        schema.setMinItems(readAttr(annotation, SchemaConstant.PROP_MIN_ITEMS, defaults));
        schema.setUniqueItems(readAttr(annotation, SchemaConstant.PROP_UNIQUE_ITEMS, defaults));
        schema.setExtensions(ExtensionReader.readExtensions(context, annotation));

        schema.setProperties(SchemaFactory.<AnnotationInstance[], Map<String, Schema>> readAttr(annotation,
            SchemaConstant.PROP_PROPERTIES, properties -> {
                if (properties == null || properties.length == 0) {
                    return null;
                }
                Map<String, Schema> propertySchemas = new LinkedHashMap<>(properties.length);
                for (AnnotationInstance propAnnotation : properties) {
                    String key = JandexUtil.value(propAnnotation, SchemaConstant.PROP_NAME);
                    Schema value = readSchema(context, new SchemaImpl(), propAnnotation, Collections.emptyMap());
                    propertySchemas.put(key, value);
                }

                return propertySchemas;
            }, defaults));

        List<String> enumeration = readAttr(annotation, SchemaConstant.PROP_ENUMERATION, defaults);

        if (enumeration != null && !enumeration.isEmpty()) {
            schema.setEnumeration(enumeration);
        }

        if (JandexUtil.isSimpleClassSchema(annotation)) {
            Schema implSchema = readClassSchema(context, JandexUtil.value(annotation, SchemaConstant.PROP_IMPLEMENTATION),
                    true);
            schema = MergeUtil.mergeObjects(implSchema, schema);
        } else if (JandexUtil.isSimpleArraySchema(annotation)) {
            Schema implSchema = readClassSchema(context, JandexUtil.value(annotation, SchemaConstant.PROP_IMPLEMENTATION),
                    true);
            // If the @Schema annotation indicates an array type, then use the Schema
            // generated from the implementation Class as the "items" for the array.
            schema.setItems(implSchema);
        } else {
            Schema implSchema = readClassSchema(context, JandexUtil.value(annotation, SchemaConstant.PROP_IMPLEMENTATION),
                    false);

            if (schema.getType() == SchemaType.ARRAY && implSchema != null) {
                // If the @Schema annotation indicates an array type, then use the Schema
                // generated from the implementation Class as the "items" for the array.
                schema.setItems(implSchema);
            } else if (implSchema != null) {
                // If there is an impl class - merge the @Schema properties *onto* the schema
                // generated from the Class so that the annotation properties override the class
                // properties (as required by the MP+OAI spec).
                schema = MergeUtil.mergeObjects(implSchema, schema);
            }
        }

        return schema;
    }

    /**
     * Converts a Jandex type to a {@link Schema} model.
     *
     * @param context scanning context
     * @param type the implementation type of the item to scan
     * @param extensions list of AnnotationScannerExtensions
     * @return Schema model
     */
    public static Schema typeToSchema(final AnnotationScannerContext context, Type type,
        List<AnnotationScannerExtension> extensions) {
        Schema schema = null;

        if (TypeUtil.isOptional(type)) {
            // Recurse using the optional's type
            return typeToSchema(context, TypeUtil.getOptionalType(type), extensions);
        } else if (CurrentScannerInfo.isWrapperType(type)) {
            // Recurse using the wrapped type
            return typeToSchema(context, CurrentScannerInfo.getCurrentAnnotationScanner().unwrapType(type), extensions);
        } else if (type.kind() == Type.Kind.ARRAY) {
            schema = new SchemaImpl().type(SchemaType.ARRAY);
            ArrayType array = type.asArrayType();
            int dimensions = array.dimensions();
            Type componentType = array.component();

            if (dimensions > 1) {
                // Recurse using a new array type with dimensions decremented
                schema.items(typeToSchema(context, ArrayType.create(componentType, dimensions - 1), extensions));
            } else {
                // Recurse using the type of the array elements
                schema.items(typeToSchema(context, componentType, extensions));
            }
        } else if (type.kind() == Type.Kind.CLASS) {
            schema = introspectClassToSchema(context, type.asClassType(), true);
        } else if (type.kind() == Type.Kind.PRIMITIVE) {
            schema = AsyncApiDataObjectScanner.process(type.asPrimitiveType());
        } else {
            schema = otherTypeToSchema(context, type, extensions);
        }

        return schema;
    }



    /**
     * Reads the attribute named by propertyName from annotation, and parses it to identified type. If no value was specified,
     * an optional default value is retrieved from the defaults map using the propertyName as
     * they key. Array-typed annotation values will be converted to List.
     *
     * @param annotation the annotation to read
     * @param propertyName the name of the attribute to read
     * @param defaults map of default values
     * @param schemaType related schema type for this attribute
     * @return the annotation attribute value, a default value, or null
     */
    static Object parseSchemaAttr(AnnotationInstance annotation, String propertyName, Map<String, Object> defaults,
            SchemaType schemaType) {
        return readAttr(annotation, propertyName, value -> {
            if (!(value instanceof String)) {
                return value;
            }
            String stringValue = ((String) value);
            if (schemaType != SchemaType.STRING) {
                return JsonUtil.parseValue(stringValue);
            }
            return stringValue;
        }, defaults);
    }

    /**
     * Introspect into the given Class to generate a Schema model. The boolean indicates
     * whether this class type should be turned into a reference.
     *
     * @param context scanning context
     * @param type the implementation type of the item to scan
     * @param schemaReferenceSupported
     */
    static Schema readClassSchema(final AnnotationScannerContext context, Type type, boolean schemaReferenceSupported) {
        if (type == null) {
            return null;
        }
        Schema schema;
        if (type.kind() == Type.Kind.ARRAY) {
            schema = new SchemaImpl().type(SchemaType.ARRAY);
            ArrayType array = type.asArrayType();
            int dimensions = array.dimensions();
            Type componentType = array.component();

            if (dimensions > 1) {
                // Recurse using a new array type with dimensions decremented
                schema.items(
                        readClassSchema(context, ArrayType.create(componentType, dimensions - 1), schemaReferenceSupported));
            } else {
                // Recurse using the type of the array elements
                schema.items(readClassSchema(context, componentType, schemaReferenceSupported));
            }
        } else if (type.kind() == Type.Kind.PRIMITIVE) {
            schema = AsyncApiDataObjectScanner.process(type.asPrimitiveType());
        } else {
            schema = introspectClassToSchema(context, type.asClassType(), schemaReferenceSupported);
        }
        return schema;
    }

    private static Schema otherTypeToSchema(final AnnotationScannerContext context, Type type,
        List<AnnotationScannerExtension> extensions) {

        Type asyncType = resolveAsyncType(context, type, extensions);
        return schemaRegistration(context, asyncType, AsyncApiDataObjectScanner.process(context, asyncType));
    }

    static Type resolveAsyncType(final AnnotationScannerContext context, Type type,
        List<AnnotationScannerExtension> extensions) {
        if (type.kind() == Type.Kind.PARAMETERIZED_TYPE) {
            ParameterizedType pType = type.asParameterizedType();
            if (pType.arguments().size() == 1 && TypeUtil.isA(context, type, JDKConstants.COMPLETION_STAGE_TYPE)) {
                return pType.arguments().get(0);
            }
        }
        for (AnnotationScannerExtension extension : extensions) {
            Type asyncType = extension.resolveAsyncType(type);
            if (asyncType != null)
                return asyncType;
        }
        return type;
    }

    /**
     * Introspect the given class type to generate a Schema model. The boolean indicates
     * whether this class type should be turned into a reference.
     *
     * @param context scanning context
     * @param ctype
     * @param schemaReferenceSupported
     */
    private static Schema introspectClassToSchema(final AnnotationScannerContext context, ClassType ctype,
            boolean schemaReferenceSupported) {
        AnnotationScanner annotationScanner = CurrentScannerInfo.getCurrentAnnotationScanner();

        SchemaRegistry schemaRegistry = SchemaRegistry.currentInstance();

        if (schemaReferenceSupported && schemaRegistry.hasRef(ctype)) {
            return schemaRegistry.lookupRef(ctype);
        } else if (!schemaReferenceSupported && schemaRegistry != null && schemaRegistry.hasSchema(ctype)) {
            // Clone the schema from the registry using mergeObjects
            return MergeUtil.mergeObjects(new SchemaImpl(), schemaRegistry.lookupSchema(ctype));
        } else {
            Schema schema = AsyncApiDataObjectScanner.process(context, ctype);
            if (schemaReferenceSupported) {
                return schemaRegistration(context, ctype, schema);
            } else {
                return schema;
            }
        }
    }

    /**
     * Reads an array of Class annotations to produce a list of {@link Schema} models.
     *
     * @param context scanning context
     * @param types the implementation types of the items to scan, never null
     */
    private static List<Schema> readClassSchemas(final AnnotationScannerContext context, Type[] types) {
        IoLogging.logger.annotationsList("schema Class");

        return Arrays.stream(types)
                .map(type -> readClassSchema(context, type, true))
                .collect(Collectors.toList());
    }

    /**
     * Reads the attribute named by propertyName from annotation and converts a non-null value using the
     * provided converter function. If no value was specified, an optional default value is retrieved
     * from the defaults map using the propertyName as they key.
     *
     * @param <R> the type of the annotation attribute value
     * @param <T> the type into which the annotation is to be converted
     * @param annotation the annotation to read
     * @param propertyName the name of the attribute to read
     * @param defaults map of default values
     * @param converter function used to convert from the raw attribute value to the desired type
     * @return the converted annotation attribute value, a default value, or null
     */
    @SuppressWarnings("unchecked")
    static <R, T> T readAttr(AnnotationInstance annotation, String propertyName, Function<R, T> converter,
            Map<String, Object> defaults) {
        R rawValue = JandexUtil.value(annotation, propertyName);
        T value;

        if (rawValue == null) {
            value = (T) defaults.get(propertyName);
        } else {
            value = converter.apply(rawValue);
        }

        return value;
    }

    /**
     * Reads the attribute named by propertyName from annotation. If no value was specified,
     * an optional default value is retrieved from the defaults map using the propertyName as
     * they key. Array-typed annotation values will be converted to List.
     *
     * @param <T> the type of the annotation attribute value
     * @param annotation the annotation to read
     * @param propertyName the name of the attribute to read
     * @param defaults map of default values
     * @return the annotation attribute value, a default value, or null
     */
    @SuppressWarnings("unchecked")
    static <T> T readAttr(AnnotationInstance annotation, String propertyName, Map<String, Object> defaults) {
        Object value = JandexUtil.value(annotation, propertyName);

        if (value == null) {
            value = defaults.get(propertyName);
        } else if (value.getClass()
                .isArray()) {
            value = Arrays.stream((T[]) value)
                    .collect(Collectors.toList());
        }

        return (T) value;
    }
}
