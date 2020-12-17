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

import org.jboss.jandex.DotName;

import io.smallrye.asyncapi.spec.annotations.extensions.Extension;
import io.smallrye.asyncapi.spec.annotations.extensions.Extensions;

/**
 * Constants related to Extension.
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0#specificationExtensions"
 */
public class ExtensionConstant {

    static final DotName DOTNAME_EXTENSIONS = DotName.createSimple(Extensions.class.getName());

    static final DotName DOTNAME_EXTENSION = DotName.createSimple(Extension.class.getName());

    public static final String PROP_NAME = "name";

    public static final String PROP_VALUE = "value";

    public static final String EXTENSION_PROPERTY_PREFIX = "x-";

    public static final String PROP_PARSE_VALUE = "parseValue";

    public static boolean isExtensionField(String fieldName) {
        return fieldName.toLowerCase()
                .startsWith(ExtensionConstant.EXTENSION_PROPERTY_PREFIX);
    }

    private ExtensionConstant() {
    }
}
