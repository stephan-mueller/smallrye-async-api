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
import io.smallrye.asyncapi.spec.models.operation.Operation;
import io.smallrye.asyncapi.spec.models.operation.OperationTrait;
import io.smallrye.asyncapi.spec.models.tag.Tag;

/**
 * An implementation of the {@link Operation} AsyncAPI model interface.
 */
public class OperationImpl extends ExtensibleImpl<Operation> implements Operation, ModelImpl {

    private String operationId;

    private String summary;

    private String description;

    private List<Tag> tags;

    private ExternalDocumentation externalDocs;

    private OperationBindings bindings;

    private List<OperationTrait> traits;

    private Message message;

    /**
     * @see Operation#getOperationId() ()
     */
    @Override
    public String getOperationId() {
        return this.operationId;
    }

    /**
     * @see Operation#setOperationId(String operationID)
     */
    @Override
    public void setOperationId(final String operationID) {
        this.operationId = operationID;
    }

    /**
     * @see Operation#getSummary()
     */
    @Override
    public String getSummary() {
        return this.summary;
    }

    /**
     * @see Operation#setSummary(String summary)
     */
    @Override
    public void setSummary(final String summary) {
        this.summary = summary;
    }

    /**
     * @see Operation#getDescription()
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * @see Operation#setDescription(String description)
     */
    @Override
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @see Operation#getTags()
     */
    @Override
    public List<Tag> getTags() {
        return ModelUtil.unmodifiableList(this.tags);
    }

    /**
     * @see Operation#setTags(List<Tag>)
     */
    @Override
    public void setTags(final List<Tag> tags) {
        this.tags = ModelUtil.replace(tags, ArrayList::new);
    }

    /**
     * @see Operation#addTag(Tag tag)
     */
    @Override
    public Operation addTag(final Tag tag) {
        this.tags = ModelUtil.add(tag, this.tags, ArrayList::new);
        return this;
    }

    /**
     * @see Operation#removeTag(Tag tag)
     */
    @Override
    public void removeTag(final Tag tag) {
        ModelUtil.remove(this.tags, tag);
    }

    /**
     * @see Operation#getExternalDocumentation()
     */
    @Override
    public ExternalDocumentation getExternalDocumentation() {
        return this.externalDocs;
    }

    /**
     * @see Operation#setExternalDocumentation(ExternalDocumentation externalDocs)
     */
    @Override
    public void setExternalDocumentation(final ExternalDocumentation externalDocs) {
        this.externalDocs = externalDocs;
    }

    /**
     * @see Operation#getBindings()
     */
    @Override
    public OperationBindings getBindings() {
        return this.bindings;
    }

    /**
     * @see Operation#setBindings(OperationBindings bindings)
     */
    @Override
    public void setBindings(final OperationBindings bindings) {
        this.bindings = bindings;
    }

    /**
     * @see Operation#getOperationTraits()
     */
    @Override
    public List<OperationTrait> getOperationTraits() {
        return ModelUtil.unmodifiableList(this.traits);
    }

    /**
     * @see Operation#setOperationTraits(List<OperationTrait>)
     */
    @Override
    public void setOperationTraits(final List<OperationTrait> traits) {
        this.traits = ModelUtil.replace(traits, ArrayList::new);
    }

    /**
     * @see Operation#addOperationTrait(OperationTrait trait)
     */
    @Override
    public Operation addOperationTrait(final OperationTrait trait) {
        this.traits = ModelUtil.add(trait, this.traits, ArrayList::new);
        return this;
    }

    /**
     * @see Operation#removeOperationTrait(OperationTrait trait)
     */
    @Override
    public void removeOperationTrait(final OperationTrait trait) {
        ModelUtil.remove(this.traits, trait);
    }

    /**
     * @see Operation#getMessage()
     */
    @Override
    public Message getMessage() {
        return this.message;
    }

    /**
     * @see Operation#setMessage(Message message)
     */
    @Override
    public void setMessage(final Message message) {
        this.message = message;
    }
}
