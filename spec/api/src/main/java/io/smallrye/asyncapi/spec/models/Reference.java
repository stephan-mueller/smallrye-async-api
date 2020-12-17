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
package io.smallrye.asyncapi.spec.models;

public interface Reference<T extends Reference<T>> {

    /**
     * Returns the reference property from this Reference instance.
     *
     * @return a reference to a T object in the components in this AsyncAPI document
     **/
    String getRef();

    /**
     * Sets this Reference's reference property to the given string.
     *
     * Normally a full reference string is a JSON pointer
     * <a href="https://tools.ietf.org/html/rfc6901">RFC6901</a> which indicates an object
     * definition in the components section of the current AsyncAPI document. For example the schema of a request body may be
     * #/components/schemas/Payload or the definition of a parameter could be #/components/parameters/Routing.HostName.
     *
     * If you provide a short name then the name will automatically be expanded to the appropriate full reference string. A
     * short name is a
     * string which is not a JSON pointer in that it does not contain a "/".
     *
     * The appropriate full reference is determined by the context. For a parameter the short name will be prefixed by
     * "#/components/parameters/" to create "#/components/parameters/ShortName".
     * 
     * <pre>
     * <code>parameter.setRef("ShortName"); // #/components/parameters/ShortName</code>
     * </pre>
     * 
     * For a response the prefix is "#/components/responses/":
     * 
     * <pre>
     * <code>response.setRef("NotFound"); // #/components/responses/NotFound</code>
     * </pre>
     *
     * This property provides a reference to an object defined elsewhere. This property and all other properties are mutually
     * exclusive. If
     * other properties are defined in addition to the reference property then the result is undefined.
     *
     * @param ref a reference to a T object in the components section of this AsyncAPI document or a JSON pointer to another
     *        document.
     **/
    void setRef(String ref);

    /**
     * Sets this Reference's reference property to the given string.
     *
     * @param ref a reference to a T object in the components in this AsyncAPI document
     * @return the current instance
     **/
    default T ref(String ref) {
        setRef(ref);
        @SuppressWarnings("unchecked")
        T t = (T) this;
        return t;
    }
}
