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
import io.smallrye.asyncapi.spec.models.message.MessageTrait;
import io.smallrye.asyncapi.spec.models.schema.Schema;
import io.smallrye.asyncapi.spec.models.tag.Tag;

/**
 * An implementation of the {@link MessageTrait} AsyncAPI model interface.
 */
public class MessageTraitImpl extends ExtensibleImpl<MessageTrait> implements MessageTrait, ModelImpl {

    private Schema headers;

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

    private String ref;

    /**
     * @see MessageTrait#getHeaders()
     */
    @Override
    public Schema getHeaders() {
        return this.headers;
    }

    /**
     * @see MessageTrait#setHeaders(Schema headers)
     */
    @Override
    public void setHeaders(final Schema headers) {
        this.headers = headers;
    }

    /**
     * @see MessageTrait#getCorrelationID()
     */
    @Override
    public CorrelationID getCorrelationID() {
        return this.correlationID;
    }

    /**
     * @see MessageTrait#setCorrelationID(CorrelationID correlationID)
     */
    @Override
    public void setCorrelationID(final CorrelationID correlationID) {
        this.correlationID = correlationID;
    }

    /**
     * @see MessageTrait#getSchemaFormat()
     */
    @Override
    public String getSchemaFormat() {
        return this.schemeFormat;
    }

    /**
     * @see MessageTrait#setSchemaFormat(String format)
     */
    @Override
    public void setSchemaFormat(final String format) {
        this.schemeFormat = format;
    }

    /**
     * @see MessageTrait#getContentType()
     */
    @Override
    public String getContentType() {
        return this.contentType;
    }

    /**
     * @see MessageTrait#setContentType(String contentType)
     */
    @Override
    public void setContentType(final String contentType) {
        this.contentType = contentType;
    }

    /**
     * @see MessageTrait#getName()
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * @see MessageTrait#setName(String name)
     */
    @Override
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @see MessageTrait#getTitle()
     */
    @Override
    public String getTitle() {
        return this.title;
    }

    /**
     * @see MessageTrait#setTitle(String title)
     */
    @Override
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * @see MessageTrait#getSummary()
     */
    @Override
    public String getSummary() {
        return this.summary;
    }

    /**
     * @see MessageTrait#setSummary(String summary)
     */
    @Override
    public void setSummary(final String summary) {
        this.summary = summary;
    }

    /**
     * @see MessageTrait#getDescription()
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * @see MessageTrait#setDescription(String description)
     */
    @Override
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @see MessageTrait#getTags()
     */
    @Override
    public List<Tag> getTags() {
        return ModelUtil.unmodifiableList(this.tags);
    }

    /**
     * @see MessageTrait#setTags(List<Tag> )
     */
    @Override
    public void setTags(final List<Tag> tags) {
        this.tags = ModelUtil.replace(tags, ArrayList::new);
    }

    /**
     * @see MessageTrait#addTag(Tag tag)
     */
    @Override
    public MessageTrait addTag(final Tag tag) {
        this.tags = ModelUtil.add(tag, this.tags, ArrayList::new);
        return this;
    }

    /**
     * @see MessageTrait#removeTag(Tag tag)
     */
    @Override
    public void removeTag(final Tag tag) {
        ModelUtil.remove(this.tags, tag);
    }

    /**
     * @see MessageTrait#getExternalDocumentation()
     */
    @Override
    public ExternalDocumentation getExternalDocumentation() {
        return this.externalDocs;
    }

    /**
     * @see MessageTrait#setExternalDocumentation(ExternalDocumentation externalDocs)
     */
    @Override
    public void setExternalDocumentation(final ExternalDocumentation externalDocs) {
        this.externalDocs = externalDocs;
    }

    /**
     * @see MessageTrait#getBindings()
     */
    @Override
    public MessageBindings getBindings() {
        return this.bindings;
    }

    /**
     * @see MessageTrait#setBindings(MessageBindings bindings)
     */
    @Override
    public void setBindings(final MessageBindings bindings) {
        this.bindings = bindings;
    }

    /**
     * @see MessageTrait#getExample()
     */
    @Override
    public List<String> getExample() {
        return ModelUtil.unmodifiableList(this.examples);
    }

    /**
     * @see MessageTrait#setExample(List<String>)
     */
    @Override
    public void setExample(final List<String> example) {
        this.examples = ModelUtil.replace(example, ArrayList::new);
    }

    /**
     * @see MessageTrait#addExample(String example)
     */
    @Override
    public MessageTrait addExample(final String example) {
        this.examples = ModelUtil.add(example, this.examples, ArrayList::new);
        return this;
    }

    /**
     * @see MessageTrait#removeExample(String example)
     */
    @Override
    public void removeExample(final String example) {
        ModelUtil.remove(this.examples, example);
    }

    /**
     * @see MessageTrait#getRef()
     */
    @Override
    public String getRef() {
        return this.ref;
    }

    /**
     * @see MessageTrait#setRef(String ref)
     */
    @Override
    public void setRef(final String ref) {
        this.ref = ref;
    }

    @Override
    public String toString() {
        return "MessageTraitImpl{" + "headers=" + headers + ", correlationID=" + correlationID + ", schemeFormat='"
                + schemeFormat + '\''
                + ", contentType='" + contentType + '\'' + ", name='" + name + '\'' + ", title='" + title + '\'' + ", summary='"
                + summary + '\''
                + ", description='" + description + '\'' + ", tags=" + tags + ", externalDocs=" + externalDocs + ", bindings="
                + bindings + ", examples="
                + examples + ", ref='" + ref + '\'' + '}';
    }
}
