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

import io.smallrye.asyncapi.core.api.models.ExtensibleImpl;
import io.smallrye.asyncapi.core.api.models.ModelImpl;
import io.smallrye.asyncapi.spec.models.parameter.Parameter;
import io.smallrye.asyncapi.spec.models.schema.Schema;

/**
 * An implementation of the {@link Parameter} AsyncAPI model interface.
 */
public class ParameterImpl extends ExtensibleImpl<Parameter> implements Parameter, ModelImpl {

    private String name;

    private String description;

    private Schema schema;

    private String location;

    private String ref;

    /**
     * @see Parameter#getName()
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @see Parameter#setName(String name)
     */
    @Override
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @see Parameter#getDescription()
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * @see Parameter#setDescription(String description)
     */
    @Override
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @see Parameter#getSchema()
     */
    @Override
    public Schema getSchema() {
        return schema;
    }

    /**
     * @see Parameter#setSchema(Schema schema)
     */
    @Override
    public void setSchema(final Schema schema) {
        this.schema = schema;
    }

    /**
     * @see Parameter#getLocation()
     */
    @Override
    public String getLocation() {
        return location;
    }

    /**
     * @see Parameter#setLocation(String location)
     */
    @Override
    public void setLocation(final String location) {
        this.location = location;
    }

    /**
     * @see Parameter#getRef()
     */
    @Override
    public String getRef() {
        return ref;
    }

    /**
     * @see Parameter#setRef(String ref)
     */
    @Override
    public void setRef(final String ref) {
        this.ref = ref;
    }

    @Override
    public String toString() {
        return "ParameterImpl{" + "name='" + name + '\'' + ", description='" + description + '\'' + ", schema=" + schema
                + ", location='" + location
                + '\'' + ", ref='" + ref + '\'' + '}';
    }
}
