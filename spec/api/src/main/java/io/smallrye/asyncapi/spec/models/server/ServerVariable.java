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
package io.smallrye.asyncapi.spec.models.server;

import java.util.List;

import io.smallrye.asyncapi.spec.models.Constructible;
import io.smallrye.asyncapi.spec.models.Extensible;

public interface ServerVariable extends Constructible, Extensible<ServerVariable> {

    /**
     * Returns the name of the ServerVariable
     *
     * @return the name
     */
    String getName();

    /**
     * Sets the name of the ServerVariable
     *
     * @param name default value of the ServerVariable
     */
    void setName(String name);

    /**
     * Sets the name of the ServerVariable
     *
     * @param name name of the ServerVariable
     * @return the current ServerVariable object
     */
    default ServerVariable name(String name) {
        setName(name);
        return this;
    }

    /**
     * Returns the string values of the substitution options
     *
     * @return the enumerations
     */
    List<String> getEnumeration();

    /**
     * Sets the string values of the substitution options
     *
     * @param enumeration all enumerations of the server
     */
    void setEnumeration(List<String> enumeration);

    /**
     * Sets this ServerVariable instance's enumeration
     *
     * @param enumeration all enumerations of the server
     * @return the current ServerVariable object
     */
    default ServerVariable enumeration(List<String> enumeration) {
        setEnumeration(enumeration);
        return this;
    }

    /**
     * This method adds a string item to enumeration list of a ServerVariable instance and returns the instance.
     *
     * If the enumeration list is null, this method should create a new ArrayList and add the item.
     *
     * @param enumeration an item to be added to enum list
     * @return ServerVariable instance with the added enum item.
     */
    ServerVariable addEnumeration(String enumeration);

    /**
     * This method removes a string item to enumeration list of a ServerVariable instance.
     *
     * @param enumeration an item to be removed to enum list
     */
    void removeEnumeration(String enumeration);

    /**
     * Returns the default value of the ServerVariable
     *
     * @return the default value
     */
    String getDefaultValue();

    /**
     * Sets the default value of the ServerVariable
     *
     * @param defaultValue default value of the ServerVariable
     */
    void setDefaultValue(String defaultValue);

    /**
     * Sets the default value of the ServerVariable
     *
     * @param defaultValue default value of the ServerVariable
     * @return the current ServerVariable object
     */
    default ServerVariable defaultValue(String defaultValue) {
        setDefaultValue(defaultValue);
        return this;
    }

    /**
     * Returns the description of the ServerVariable
     *
     * @return the description of the ServerVariable
     */
    String getDescription();

    /**
     * Sets the description of the ServerVariable
     *
     * @param description the description of the ServerVariable
     */
    void setDescription(String description);

    /**
     * Sets the description of the ServerVariable
     *
     * @param description description of the ServerVariable
     * @return the current ServerVariable object
     */
    default ServerVariable description(String description) {
        setDescription(description);
        return this;
    }

    /**
     * Returns the example values of the ServerVariable
     *
     * @return the example values of the ServerVariable
     */
    List<String> getExamples();

    /**
     * Sets the example values of the ServerVariable
     *
     * @param examples example values of the ServerVariable
     */
    void setExamples(List<String> examples);

    /**
     * Sets the example values of the ServerVariable
     *
     * @param examples example values of the ServerVariable
     * @return the current ServerVariable object
     */
    default ServerVariable examples(List<String> examples) {
        setExamples(examples);
        return this;
    }

    /**
     * This method adds a string item to examples list of a ServerVariable instance and returns the instance.
     *
     * @param example an item to be added to examples list
     * @return ServerVariable instance with the added examples item.
     */
    ServerVariable addExample(String example);

    /**
     * This method removes a string item to examples list of a ServerVariable instance.
     *
     * @param example an item to be removed to examples list
     */
    void removeExample(String example);
}
