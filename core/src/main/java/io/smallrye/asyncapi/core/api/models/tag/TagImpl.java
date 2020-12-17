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
package io.smallrye.asyncapi.core.api.models.tag;

import io.smallrye.asyncapi.core.api.models.ExtensibleImpl;
import io.smallrye.asyncapi.core.api.models.ModelImpl;
import io.smallrye.asyncapi.spec.models.ExternalDocumentation;
import io.smallrye.asyncapi.spec.models.tag.Tag;

/**
 * An implementation of the {@link Tag} AsyncAPI model interface.
 */
public class TagImpl extends ExtensibleImpl<Tag> implements Tag, ModelImpl {

    private String name;

    private String description;

    private ExternalDocumentation externalDocs;

    /**
     * @see Tag#getName()
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * @see Tag#setName(String name)
     */
    @Override
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @see Tag#getDescription()
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * @see Tag#setDescription(String description)
     */
    @Override
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @see Tag#getExternalDocumentation()
     */
    @Override
    public ExternalDocumentation getExternalDocumentation() {
        return this.externalDocs;
    }

    /**
     * @see Tag#setExternalDocumentation(ExternalDocumentation externalDocs)
     */
    @Override
    public void setExternalDocumentation(final ExternalDocumentation externalDocs) {
        this.externalDocs = externalDocs;
    }
}
