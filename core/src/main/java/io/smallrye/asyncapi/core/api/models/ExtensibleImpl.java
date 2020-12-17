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
package io.smallrye.asyncapi.core.api.models;

import java.util.LinkedHashMap;
import java.util.Map;

import io.smallrye.asyncapi.core.runtime.util.ModelUtil;
import io.smallrye.asyncapi.spec.models.Extensible;

/**
 * Implementation of the {@link Extensible} AsyncAPI model interface. Base class for many of the AsyncAPI models.
 */
public abstract class ExtensibleImpl<T extends Extensible<T>> implements Extensible<T>, ModelImpl {

    private Map<String, Object> extensions;

    /**
     * @see Extensible#getExtensions()
     */
    @Override
    public Map<String, Object> getExtensions() {
        return ModelUtil.unmodifiableMap(this.extensions);
    }

    /**
     * @see Extensible#addExtension(String, Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    public T addExtension(String name, Object value) {
        this.extensions = ModelUtil.add(name, value, this.extensions, LinkedHashMap<String, Object>::new);
        return (T) this;
    }

    /**
     * @see Extensible#removeExtension(String)
     */
    @Override
    public void removeExtension(String name) {
        ModelUtil.remove(this.extensions, name);
    }

    /**
     * @see Extensible#setExtensions(Map)
     */
    @Override
    public void setExtensions(Map<String, Object> extensions) {
        this.extensions = ModelUtil.replace(extensions, LinkedHashMap::new);
    }

}
