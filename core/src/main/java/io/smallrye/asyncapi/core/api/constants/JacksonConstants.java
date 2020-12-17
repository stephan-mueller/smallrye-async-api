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
package io.smallrye.asyncapi.core.api.constants;

import org.jboss.jandex.DotName;

/**
 * Constants related to the Jackson library
 */
public class JacksonConstants {

    public static final DotName JSON_PROPERTY = DotName.createSimple("com.fasterxml.jackson.annotation.JsonProperty");

    public static final DotName JSON_IGNORE = DotName.createSimple("com.fasterxml.jackson.annotation.JsonIgnore");

    public static final DotName JSON_IGNORE_TYPE = DotName.createSimple("com.fasterxml.jackson.annotation.JsonIgnoreType");

    public static final DotName JSON_IGNORE_PROPERTIES = DotName
            .createSimple("com.fasterxml.jackson.annotation.JsonIgnoreProperties");

    public static final DotName JSON_PROPERTY_ORDER = DotName
            .createSimple("com.fasterxml.jackson.annotation.JsonPropertyOrder");

    public static final String PROP_VALUE = "value";

    private JacksonConstants() {
    }
}
