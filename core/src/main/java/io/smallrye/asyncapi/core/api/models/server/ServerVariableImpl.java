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
package io.smallrye.asyncapi.core.api.models.server;

import java.util.ArrayList;
import java.util.List;

import io.smallrye.asyncapi.core.api.models.ExtensibleImpl;
import io.smallrye.asyncapi.core.api.models.ModelImpl;
import io.smallrye.asyncapi.core.runtime.util.ModelUtil;
import io.smallrye.asyncapi.spec.models.server.ServerVariable;

/**
 * An implementation of the {@link ServerVariable} AsyncAPI model interface.
 */
public class ServerVariableImpl extends ExtensibleImpl<ServerVariable> implements ServerVariable, ModelImpl {

    private String name;

    private List<String> enumeration;

    private String defaultValue;

    private String description;

    private List<String> examples;

    /**
     * @see ServerVariable#getName()
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * @see ServerVariable#setName(String name)
     */
    @Override
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @see ServerVariable#getEnumeration()
     */
    @Override
    public List<String> getEnumeration() {
        return ModelUtil.unmodifiableList(this.enumeration);
    }

    /**
     * @see ServerVariable#setEnumeration(List<String>)
     */
    @Override
    public void setEnumeration(final List<String> enumeration) {
        this.enumeration = ModelUtil.replace(enumeration, ArrayList::new);
    }

    /**
     * @see ServerVariable#addEnumeration(String enumeration)
     */
    @Override
    public ServerVariable addEnumeration(final String enumeration) {
        this.enumeration = ModelUtil.add(enumeration, this.enumeration, ArrayList::new);
        return this;
    }

    /**
     * @see ServerVariable#removeEnumeration(String enumeration)
     */
    @Override
    public void removeEnumeration(final String enumeration) {
        ModelUtil.remove(this.enumeration, enumeration);
    }

    /**
     * @see ServerVariable#getDefaultValue()
     */
    @Override
    public String getDefaultValue() {
        return this.defaultValue;
    }

    /**
     * @see ServerVariable#setDefaultValue(String defaultValue)
     */
    @Override
    public void setDefaultValue(final String defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * @see ServerVariable#getDescription()
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * @see ServerVariable#setDescription(String description)
     */
    @Override
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @see ServerVariable#getExamples()
     */
    @Override
    public List<String> getExamples() {
        return ModelUtil.unmodifiableList(this.examples);
    }

    /**
     * @see ServerVariable#setExamples(List<String>)
     */
    @Override
    public void setExamples(final List<String> examples) {
        this.examples = ModelUtil.replace(examples, ArrayList::new);
    }

    /**
     * @see ServerVariable#addExample(String example)
     */
    @Override
    public ServerVariable addExample(final String example) {
        this.examples = ModelUtil.add(example, this.examples, ArrayList::new);
        return this;
    }

    /**
     * @see ServerVariable#removeExample(String example)
     */
    @Override
    public void removeExample(final String example) {
        ModelUtil.remove(this.examples, example);
    }
}
