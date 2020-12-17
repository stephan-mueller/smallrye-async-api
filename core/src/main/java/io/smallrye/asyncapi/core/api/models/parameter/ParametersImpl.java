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
package io.smallrye.asyncapi.core.api.models.parameter;

import java.util.ArrayList;
import java.util.List;

import io.smallrye.asyncapi.core.api.models.ExtensibleImpl;
import io.smallrye.asyncapi.core.api.models.ModelImpl;
import io.smallrye.asyncapi.core.runtime.util.ModelUtil;
import io.smallrye.asyncapi.spec.models.parameter.Parameter;
import io.smallrye.asyncapi.spec.models.parameter.Parameters;

/**
 * An implementation of the {@link Parameters} AsyncAPI model interface.
 */
public class ParametersImpl extends ExtensibleImpl<Parameters> implements Parameters, ModelImpl {

    private List<Parameter> parameters;

    /**
     * @see Parameters#getValue()
     */
    @Override
    public List<Parameter> getValue() {
        return ModelUtil.unmodifiableList(this.parameters);
    }

    /**
     * @see Parameters#setValue(List<Parameter>)
     */
    @Override
    public void setValue(final List<Parameter> parameters) {
        this.parameters = ModelUtil.replace(parameters, ArrayList::new);
    }

    /**
     * @see Parameters#addParameter(Parameter parameter)
     */
    @Override
    public Parameters addParameter(final Parameter parameter) {
        this.parameters = ModelUtil.add(parameter, this.parameters, ArrayList::new);
        return this;
    }

    /**
     * @see Parameters#removeParameter(Parameter parameter)
     */
    @Override
    public void removeParameter(final Parameter parameter) {
        ModelUtil.remove(this.parameters, parameter);
    }

    @Override
    public String toString() {
        return "ParametersImpl{" + "parameters=" + parameters + '}';
    }
}
