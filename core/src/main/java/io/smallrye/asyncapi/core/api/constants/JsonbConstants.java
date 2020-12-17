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
 * Constants related to the JSON-B Specification
 */
public class JsonbConstants {

    public static final DotName JSONB_PROPERTY = DotName.createSimple("javax.json.bind.annotation.JsonbProperty");

    public static final DotName JSONB_TRANSIENT = DotName.createSimple("javax.json.bind.annotation.JsonbTransient");

    public static final DotName JSONB_PROPERTY_ORDER = DotName.createSimple("javax.json.bind.annotation.JsonbPropertyOrder");

    public static final String PROP_VALUE = "value";

    private JsonbConstants() {
    }
}
