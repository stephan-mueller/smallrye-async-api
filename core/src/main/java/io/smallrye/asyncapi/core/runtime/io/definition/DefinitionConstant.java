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
package io.smallrye.asyncapi.core.runtime.io.definition;

import org.jboss.jandex.DotName;

import io.smallrye.asyncapi.spec.annotations.AsyncAPI;

public class DefinitionConstant {
    public static final DotName DOTNAME_ASYNC_API = DotName.createSimple(AsyncAPI.class.getName());

    public static final String PROP_INFO = "info";
    public static final String PROP_CONTENT_TYPE = "defaultContentType";
    public static final String PROP_SERVERS = "servers";
    public static final String PROP_IDENTIFIER = "id";
    public static final String PROP_COMPONENTS = "components";
    public static final String PROP_TAGS = "tags";
    public static final String PROP_ASYNCAPI = "asyncapi";

    public static final String PROP_CHANNELS = "channels";

    private DefinitionConstant() {
    }
}
