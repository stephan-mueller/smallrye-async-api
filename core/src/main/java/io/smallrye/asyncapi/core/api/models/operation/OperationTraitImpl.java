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
package io.smallrye.asyncapi.core.api.models.operation;

import java.util.ArrayList;
import java.util.List;

import io.smallrye.asyncapi.core.api.models.ExtensibleImpl;
import io.smallrye.asyncapi.core.api.models.ModelImpl;
import io.smallrye.asyncapi.core.runtime.util.ModelUtil;
import io.smallrye.asyncapi.spec.models.ExternalDocumentation;
import io.smallrye.asyncapi.spec.models.binding.OperationBindings;
import io.smallrye.asyncapi.spec.models.message.Message;
import io.smallrye.asyncapi.spec.models.operation.OperationTrait;
import io.smallrye.asyncapi.spec.models.tag.Tag;

/**
 * An implementation of the {@link OperationTrait} AsyncAPI model interface.
 */
public class OperationTraitImpl extends ExtensibleImpl<OperationTrait> implements OperationTrait, ModelImpl {

    private String operationId;

    private String summary;

    private String description;

    private List<Tag> tags;

    private ExternalDocumentation externalDocs;

    private OperationBindings bindings;

    private Message message;

    private String ref;

    /**
     * @see OperationTrait#getOperationId()
     */
    @Override
    public String getOperationId() {
        return this.operationId;
    }

    /**
     * @see OperationTrait#setOperationId(String operationId)
     */
    @Override
    public void setOperationId(final String operationId) {
        this.operationId = operationId;
    }

    /**
     * @see OperationTrait#getSummary()
     */
    @Override
    public String getSummary() {
        return this.summary;
    }

    /**
     * @see OperationTrait#setSummary(String summary)
     */
    @Override
    public void setSummary(final String summary) {
        this.summary = summary;
    }

    /**
     * @see OperationTrait#getDescription()
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * @see OperationTrait#getDescription()
     */
    @Override
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @see OperationTrait#getTags()
     */
    @Override
    public List<Tag> getTags() {
        return ModelUtil.unmodifiableList(this.tags);
    }

    /**
     * @see OperationTrait#setTags(List<Tag>)
     */
    @Override
    public void setTags(final List<Tag> tags) {
        this.tags = ModelUtil.replace(tags, ArrayList::new);
    }

    /**
     * @see OperationTrait#addTag(Tag tag)
     */
    @Override
    public OperationTrait addTag(final Tag tag) {
        this.tags = ModelUtil.add(tag, this.tags, ArrayList::new);
        return this;
    }

    /**
     * @see OperationTrait#removeTag(Tag tag)
     */
    @Override
    public void removeTag(final Tag tag) {
        ModelUtil.remove(this.tags, tag);
    }

    /**
     * @see OperationTrait#getExternalDocumentation()
     */
    @Override
    public ExternalDocumentation getExternalDocumentation() {
        return this.externalDocs;
    }

    /**
     * @see OperationTrait#setExternalDocumentation(ExternalDocumentation externalDocs)
     */
    @Override
    public void setExternalDocumentation(final ExternalDocumentation externalDocs) {
        this.externalDocs = externalDocs;
    }

    /**
     * @see OperationTrait#getBindings()
     */
    @Override
    public OperationBindings getBindings() {
        return this.bindings;
    }

    /**
     * @see OperationTrait#setBindings(OperationBindings bindings)
     */
    @Override
    public void setBindings(final OperationBindings bindings) {
        this.bindings = bindings;
    }

    /**
     * @see OperationTrait#getRef()
     */
    @Override
    public String getRef() {
        return this.ref;
    }

    /**
     * @see OperationTrait#setRef(String ref)
     */
    @Override
    public void setRef(final String ref) {
        this.ref = ref;
    }

    /**
     * @see OperationTrait#getMessage()
     */
    @Override
    public Message getMessage() {
        return message;
    }

    /**
     * @see OperationTrait#setMessage(Message message)
     */
    @Override
    public void setMessage(final Message message) {
        this.message = message;
    }
}
