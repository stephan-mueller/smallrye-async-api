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

import io.smallrye.asyncapi.spec.models.Constructible;
import io.smallrye.asyncapi.spec.models.Extensible;
import io.smallrye.asyncapi.spec.models.Reference;
import io.smallrye.asyncapi.spec.models.schema.Schema;

/**
 * Describes a parameter included in a channel name.
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0/#a-name-parameterobject-a-parameter-object"
 */
public interface Parameter extends Constructible, Extensible<Parameter>, Reference<Parameter> {

    /**
     * Returns the name of the parameter
     *
     * @return the name of the parameter
     */
    String getName();

    /**
     * Sets the name of the parameter
     *
     * @param name the name of the parameter
     */
    void setName(String name);

    /**
     * Sets the name of the parameter
     *
     * @param name the name of the parameter
     * @return this Parameter instance
     */
    default Parameter name(String name) {
        setName(name);
        return this;
    }

    /**
     * Returns the description of the parameter
     *
     * @return the description of the parameter
     */
    String getDescription();

    /**
     * Sets the description of the parameter
     *
     * @param description the description of the parameter
     */
    void setDescription(String description);

    /**
     * Sets the description of the parameter
     *
     * @param description the description of the parameter
     * @return this Parameter instance
     */
    default Parameter description(String description) {
        setDescription(description);
        return this;
    }

    /**
     * Returns the schema of the parameter
     *
     * @return the schema of the parameter
     */
    Schema getSchema();

    /**
     * Sets the schema of the parameter
     *
     * @param schema the schema of the parameter
     */
    void setSchema(Schema schema);

    /**
     * Sets the schema of the parameter
     *
     * @param schema the schema of the parameter
     * @return this Parameter instance
     */
    default Parameter schema(Schema schema) {
        setSchema(schema);
        return this;
    }

    /**
     * Returns the location of the parameter
     *
     * @return the location of the parameter
     */
    String getLocation();

    /**
     * Sets the location of the parameter
     *
     * @param location the location of the parameter
     */
    void setLocation(String location);

    /**
     * Sets the location of the parameter
     *
     * @param location the location of the parameter
     * @return this Parameter instance
     */
    default Parameter location(String location) {
        setLocation(location);
        return this;
    }
}
