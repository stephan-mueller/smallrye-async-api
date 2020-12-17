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
package io.smallrye.asyncapi.core.runtime.io.parameter;

import org.jboss.jandex.DotName;

import io.smallrye.asyncapi.spec.annotations.parameter.Parameter;
import io.smallrye.asyncapi.spec.annotations.parameter.Parameters;

public class ParameterConstant {

    public static final DotName DOTNAME_PARAMETER = DotName.createSimple(Parameter.class.getName());

    public static final DotName DOTNAME_PARAMETERS = DotName.createSimple(Parameters.class.getName());

    public static final String PROP_VALUE = "value";

    public static final String PROP_NAME = "name";

    public static final String PROP_DESCRIPTION = "description";

    public static final String PROP_SCHEMA = "schema";

    public static final String PROP_LOCATION = "location";

    public static final String PROP_REF = "ref";

    private ParameterConstant() {
    }
}
