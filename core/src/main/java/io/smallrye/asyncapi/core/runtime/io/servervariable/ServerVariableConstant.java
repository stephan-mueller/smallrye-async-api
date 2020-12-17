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
package io.smallrye.asyncapi.core.runtime.io.servervariable;

/**
 * Constants related to Server
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0#serverVariableObject"
 */
public class ServerVariableConstant {

    public static final String PROP_ENUM = "enum";

    public static final String PROP_NAME = "name";

    public static final String PROP_DEFAULT_VALUE = "defaultValue";

    public static final String PROP_DEFAULT = "default";

    public static final String PROP_DESCRIPTION = "description";

    public static final String PROP_EXAMPLES = "examples";

    public static final String PROP_ENUMERATION = "enumeration";

    private ServerVariableConstant() {
    }
}
