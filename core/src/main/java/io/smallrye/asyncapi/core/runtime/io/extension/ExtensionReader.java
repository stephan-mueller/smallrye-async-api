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
package io.smallrye.asyncapi.core.runtime.io.extension;

import static io.smallrye.asyncapi.core.runtime.io.JsonUtil.readObject;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.AnnotationTarget;
import org.jboss.jandex.AnnotationValue;

import com.fasterxml.jackson.databind.JsonNode;

import io.smallrye.asyncapi.core.runtime.io.IoLogging;
import io.smallrye.asyncapi.core.runtime.scanner.AnnotationScannerExtension;
import io.smallrye.asyncapi.core.runtime.scanner.spi.AnnotationScannerContext;
import io.smallrye.asyncapi.core.runtime.util.JandexUtil;
import io.smallrye.asyncapi.spec.models.Extensible;

/**
 * Reading the Extension annotation
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0#specificationExtensions"
 */
public class ExtensionReader {

    private ExtensionReader() {
    }

    /**
     * Reads an array of Extension annotations. The AnnotationValue in this case is
     * an array of Extension annotations. These must be read and converted into a Map.
     *
     * @param context the scanning context
     * @param annotationValue map of {@literal @}Extension annotations
     * @return Map of Objects
     */
    public static Map<String, Object> readExtensions(final AnnotationScannerContext context,
            final AnnotationValue annotationValue) {
        if (annotationValue == null) {
            return null;
        }
        IoLogging.logger.annotationsMap("@Extension");
        Map<String, Object> e = new LinkedHashMap<>();
        AnnotationInstance[] nestedArray = annotationValue.asNestedArray();
        for (AnnotationInstance annotation : nestedArray) {
            String extName = JandexUtil.stringValue(annotation, ExtensionConstant.PROP_NAME);
            e.put(extName, readExtensionValue(context, extName, annotation));
        }
        return e;
    }

    /**
     * Reads a List of Extension annotations. These must be read and converted into a Map.
     *
     * @param context the scanning context
     * @param extensions List of {@literal @}Extension annotations
     * @return Map of Objects
     */
    public static Map<String, Object> readExtensions(final AnnotationScannerContext context,
            final List<AnnotationInstance> extensions) {
        IoLogging.logger.annotationsMap("@Extension");
        Map<String, Object> e = new LinkedHashMap<>();
        for (AnnotationInstance annotation : extensions) {
            String extName = JandexUtil.stringValue(annotation, ExtensionConstant.PROP_NAME);
            e.put(extName, readExtensionValue(context, extName, annotation));
        }
        return e;
    }

    public static Map<String, Object> readExtensions(final AnnotationScannerContext context,
            final AnnotationInstance extensible) {
        AnnotationTarget target = extensible.target();
        // target may be null - checked by JandexUtil methods
        List<AnnotationInstance> extensions = JandexUtil.getRepeatableAnnotation(target, ExtensionConstant.DOTNAME_EXTENSION,
                ExtensionConstant.DOTNAME_EXTENSIONS);
        return extensions.isEmpty() ? null : readExtensions(context, extensions);
    }

    /**
     * Reads a single Extension annotation. If the value must be parsed (as indicated by the
     * 'parseValue' attribute of the annotation), the parsing is delegated to the extensions
     * currently set in the scanner. The default value will parse the string using Jackson.
     *
     * @param context the scanning context
     * @param name the name of the extension
     * @param annotationInstance {@literal @}Extension annotation
     * @return a Java representation of the 'value' property, either a String or parsed value
     */
    public static Object readExtensionValue(final AnnotationScannerContext context, final String name,
            final AnnotationInstance annotationInstance) {
        IoLogging.logger.annotation("@Extension");
        String extValue = JandexUtil.stringValue(annotationInstance, ExtensionConstant.PROP_VALUE);
        boolean parseValue = JandexUtil.booleanValueWithDefault(annotationInstance, ExtensionConstant.PROP_PARSE_VALUE);
        Object parsedValue = extValue;
        if (parseValue) {
            for (AnnotationScannerExtension e : context.getExtensions()) {
                parsedValue = e.parseExtension(name, extValue);
                if (parsedValue != null) {
                    break;
                }
            }
        }
        return parsedValue;
    }

    /**
     * Reads model extensions.
     *
     * @param node the json object
     * @param model the model to read to
     */
    public static void readExtensions(final JsonNode node, final Extensible<?> model) {
        for (Iterator<String> iterator = node.fieldNames(); iterator.hasNext();) {
            String fieldName = iterator.next();
            if (fieldName.toLowerCase()
                    .startsWith(ExtensionConstant.EXTENSION_PROPERTY_PREFIX)) {
                Object value = readObject(node.get(fieldName));
                model.addExtension(fieldName, value);
            }
        }
    }

    // helper methods for scanners

    public static List<AnnotationInstance> getExtensionsAnnotations(final AnnotationTarget target) {
        return JandexUtil.getRepeatableAnnotation(target, ExtensionConstant.DOTNAME_EXTENSION,
                ExtensionConstant.DOTNAME_EXTENSIONS);
    }

    public static String getExtensionName(final AnnotationInstance annotation) {
        return JandexUtil.stringValue(annotation, ExtensionConstant.PROP_NAME);
    }
}
