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
package io.smallrye.asyncapi.spec.models.parameter;

import java.util.List;

import io.smallrye.asyncapi.spec.models.Constructible;
import io.smallrye.asyncapi.spec.models.Extensible;

/**
 * Describes a map of parameters included in a channel name.
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0/#a-name-parametersobject-a-parameters-object"
 */
public interface Parameters extends Constructible, Extensible<Parameters> {

    /**
     * Returns the list of parameter
     *
     * @return the list of parameter
     */
    List<Parameter> getValue();

    /**
     * Sets the list of parameter
     *
     * @param value the list of parameter
     */
    void setValue(List<Parameter> value);

    /**
     * Sets the list of parameter
     *
     * @param value the list of parameter
     * @return this Parameters instance
     */
    default Parameters value(List<Parameter> value) {
        setValue(value);
        return this;
    }

    /**
     * Adds the given parameter to this Parameters list of parameters.
     *
     * @param parameter to be added to the Parameters list
     * @return the current Parameters object
     */
    Parameters addParameter(Parameter parameter);

    /**
     * Removes the given parameter to this Parameters list of parameters.
     *
     * @param parameter to be added to the Parameters list
     */
    void removeParameter(Parameter parameter);
}
