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

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.Set;
import java.util.concurrent.CompletionStage;

import org.jboss.jandex.DotName;
import org.jboss.jandex.Type;

/**
 * Constants from the JDK
 */
public class JDKConstants {

    public static final DotName DOTNAME_DEPRECATED = DotName.createSimple(Deprecated.class.getName());

    public static final DotName DOTNAME_OPTIONAL = DotName.createSimple(Optional.class.getName());

    public static final DotName DOTNAME_OPTIONAL_DOUBLE = DotName.createSimple(OptionalDouble.class.getName());

    public static final DotName DOTNAME_OPTIONAL_INT = DotName.createSimple(OptionalInt.class.getName());

    public static final DotName DOTNAME_OPTIONAL_LONG = DotName.createSimple(OptionalLong.class.getName());

    public static final DotName COMPLETION_STAGE_NAME = DotName.createSimple(CompletionStage.class.getName());

    public static final Set<DotName> DOTNAME_OPTIONALS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList(DOTNAME_OPTIONAL, DOTNAME_OPTIONAL_DOUBLE, DOTNAME_OPTIONAL_INT, DOTNAME_OPTIONAL_LONG)));

    public static final Type COMPLETION_STAGE_TYPE = Type.create(COMPLETION_STAGE_NAME, Type.Kind.CLASS);

    private JDKConstants() {
    }
}
