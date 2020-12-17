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
package io.smallrye.asyncapi.core.api.models.message;

import java.util.ArrayList;
import java.util.List;

import io.smallrye.asyncapi.core.api.models.ExtensibleImpl;
import io.smallrye.asyncapi.core.api.models.ModelImpl;
import io.smallrye.asyncapi.core.runtime.util.ModelUtil;
import io.smallrye.asyncapi.spec.models.ExternalDocumentation;
import io.smallrye.asyncapi.spec.models.binding.MessageBindings;
import io.smallrye.asyncapi.spec.models.message.CorrelationID;
import io.smallrye.asyncapi.spec.models.message.Message;
import io.smallrye.asyncapi.spec.models.message.MessageTrait;
import io.smallrye.asyncapi.spec.models.schema.Schema;
import io.smallrye.asyncapi.spec.models.tag.Tag;

/**
 * An implementation of the {@link Message} AsyncAPI model interface.
 */
public class MessageImpl extends ExtensibleImpl<Message> implements Message, ModelImpl {

    private Schema headers;

    private Schema payload;

    private CorrelationID correlationID;

    private String schemeFormat;

    private String contentType;

    private String name;

    private String title;

    private String summary;

    private String description;

    private List<Tag> tags;

    private ExternalDocumentation externalDocs;

    private MessageBindings bindings;

    private List<String> examples;

    private List<MessageTrait> traits;

    private String ref;

    /**
     * @see Message#getHeaders()
     */
    @Override
    public Schema getHeaders() {
        return this.headers;
    }

    /**
     * @see Message#setHeaders(Schema headers)
     */
    @Override
    public void setHeaders(final Schema headers) {
        this.headers = headers;
    }

    /**
     * @see Message#getPayload()
     */
    @Override
    public Schema getPayload() {
        return this.payload;
    }

    /**
     * @see Message#setPayload(Schema payload)
     */
    @Override
    public void setPayload(final Schema payload) {
        this.payload = payload;
    }

    /**
     * @see Message#getCorrelationID()
     */
    @Override
    public CorrelationID getCorrelationID() {
        return this.correlationID;
    }

    /**
     * @see Message#setCorrelationID(CorrelationID correlationID)
     */
    @Override
    public void setCorrelationID(final CorrelationID correlationID) {
        this.correlationID = correlationID;
    }

    /**
     * @see Message#getSchemaFormat()
     */
    @Override
    public String getSchemaFormat() {
        return this.schemeFormat;
    }

    /**
     * @see Message#setSchemaFormat(String format)
     */
    @Override
    public void setSchemaFormat(final String format) {
        this.schemeFormat = format;
    }

    /**
     * @see Message#getContentType()
     */
    @Override
    public String getContentType() {
        return this.contentType;
    }

    /**
     * @see Message#setContentType(String contentType)
     */
    @Override
    public void setContentType(final String contentType) {
        this.contentType = contentType;
    }

    /**
     * @see Message#getName()
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * @see Message#setName(String name)
     */
    @Override
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @see Message#getTitle()
     */
    @Override
    public String getTitle() {
        return this.title;
    }

    /**
     * @see Message#setTitle(String title)
     */
    @Override
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * @see Message#getSummary()
     */
    @Override
    public String getSummary() {
        return this.summary;
    }

    /**
     * @see Message#setSummary(String summary)
     */
    @Override
    public void setSummary(final String summary) {
        this.summary = summary;
    }

    /**
     * @see Message#getDescription()
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * @see Message#setDescription(String description)
     */
    @Override
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @see Message#getTags()
     */
    @Override
    public List<Tag> getTags() {
        return ModelUtil.unmodifiableList(this.tags);
    }

    /**
     * @see Message#setTags(List<Tag> )
     */
    @Override
    public void setTags(final List<Tag> tags) {
        this.tags = ModelUtil.replace(tags, ArrayList::new);
    }

    /**
     * @see Message#addTag(Tag tag)
     */
    @Override
    public Message addTag(final Tag tag) {
        this.tags = ModelUtil.add(tag, this.tags, ArrayList::new);
        return this;
    }

    /**
     * @see Message#removeTag(Tag tag)
     */
    @Override
    public void removeTag(final Tag tag) {
        ModelUtil.remove(this.tags, tag);
    }

    /**
     * @see Message#getExternalDocumentation()
     */
    @Override
    public ExternalDocumentation getExternalDocumentation() {
        return this.externalDocs;
    }

    /**
     * @see Message#setExternalDocumentation(ExternalDocumentation externalDocs)
     */
    @Override
    public void setExternalDocumentation(final ExternalDocumentation externalDocs) {
        this.externalDocs = externalDocs;
    }

    /**
     * @see Message#getBindings()
     */
    @Override
    public MessageBindings getBindings() {
        return this.bindings;
    }

    /**
     * @see Message#setBindings(MessageBindings bindings)
     */
    @Override
    public void setBindings(final MessageBindings bindings) {
        this.bindings = bindings;
    }

    /**
     * @see Message#getExample()
     */
    @Override
    public List<String> getExample() {
        return ModelUtil.unmodifiableList(this.examples);
    }

    /**
     * @see Message#setExample(List<String>)
     */
    @Override
    public void setExample(final List<String> example) {
        this.examples = ModelUtil.replace(example, ArrayList::new);
    }

    /**
     * @see Message#addExample(String example)
     */
    @Override
    public Message addExample(final String example) {
        this.examples = ModelUtil.add(example, this.examples, ArrayList::new);
        return this;
    }

    /**
     * @see Message#removeExample(String example)
     */
    @Override
    public void removeExample(final String example) {
        ModelUtil.remove(this.examples, example);
    }

    /**
     * @see Message#getTraits()
     */
    @Override
    public List<MessageTrait> getTraits() {
        return ModelUtil.unmodifiableList(this.traits);
    }

    /**
     * @see Message#setTraits(List<MessageTrait>)
     */
    @Override
    public void setTraits(final List<MessageTrait> traits) {
        this.traits = ModelUtil.replace(traits, ArrayList::new);
    }

    /**
     * @see Message#addTrait(MessageTrait trait)
     */
    @Override
    public Message addTrait(final MessageTrait trait) {
        this.traits = ModelUtil.add(trait, this.traits, ArrayList::new);
        return this;
    }

    /**
     * @see Message#removeTrait(MessageTrait trait)
     */
    @Override
    public void removeTrait(final MessageTrait trait) {
        ModelUtil.remove(this.traits, trait);
    }

    /**
     * @see Message#getRef()
     */
    @Override
    public String getRef() {
        return this.ref;
    }

    /**
     * @see Message#setRef(String ref)
     */
    @Override
    public void setRef(final String ref) {
        this.ref = ref;
    }

    @Override
    public String toString() {
        return "MessageImpl{" + "headers=" + headers + ", payload=" + payload + ", correlationID=" + correlationID
                + ", schemeFormat='"
                + schemeFormat + '\'' + ", contentType='" + contentType + '\'' + ", name='" + name + '\'' + ", title='" + title
                + '\''
                + ", summary='" + summary + '\'' + ", description='" + description + '\'' + ", tags=" + tags + ", externalDocs="
                + externalDocs
                + ", bindings=" + bindings + ", examples=" + examples + ", traits=" + traits + ", ref='" + ref + '\'' + '}';
    }
}
