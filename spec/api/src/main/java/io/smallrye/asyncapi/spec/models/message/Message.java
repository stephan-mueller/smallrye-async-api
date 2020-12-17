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
package io.smallrye.asyncapi.spec.models.message;

import java.util.List;

import io.smallrye.asyncapi.spec.models.Constructible;
import io.smallrye.asyncapi.spec.models.Extensible;
import io.smallrye.asyncapi.spec.models.ExternalDocumentation;
import io.smallrye.asyncapi.spec.models.Reference;
import io.smallrye.asyncapi.spec.models.binding.MessageBindings;
import io.smallrye.asyncapi.spec.models.schema.Schema;
import io.smallrye.asyncapi.spec.models.tag.Tag;

/**
 * Describes a message received on a given channel and operation.
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0/#messageObject"
 */
public interface Message extends Constructible, Extensible<Message>, Reference<Message> {

    /**
     * Returns the headers of the message
     *
     * @return the headers of the message
     */
    Schema getHeaders();

    /**
     * Sets the headers of the message
     *
     * @param headers the headers of the message
     */
    void setHeaders(Schema headers);

    /**
     * Sets the headers of the message
     *
     * @param headers the headers of the message
     * @return this Message instance
     */
    default Message headers(Schema headers) {
        setHeaders(headers);
        return this;
    }

    /**
     * Returns the payload of the message
     *
     * @return the payload of the message
     */
    Schema getPayload();

    /**
     * Sets the payload of the message
     *
     * @param payload the payload of the message
     */
    void setPayload(Schema payload);

    /**
     * Sets the payload of the message
     *
     * @param payload the payload of the message
     * @return this Message instance
     */
    default Message payload(Schema payload) {
        setHeaders(payload);
        return this;
    }

    /**
     * Returns the correlationId of the message
     *
     * @return the correlationId of the message
     */
    CorrelationID getCorrelationID();

    /**
     * Sets the correlationId of the message
     *
     * @param correlationId the correlationId of the message
     */
    void setCorrelationID(CorrelationID correlationId);

    /**
     * Sets the correlationId of the message
     *
     * @param correlationId the correlationId of the message
     * @return this Message instance
     */
    default Message correlationId(CorrelationID correlationId) {
        setCorrelationID(correlationId);
        return this;
    }

    /**
     * Returns the schema format of the message
     *
     * @return the schema format of the message
     */
    String getSchemaFormat();

    /**
     * Sets the schema format of the message
     *
     * @param schemaFormat the schema format of the message
     */
    void setSchemaFormat(String schemaFormat);

    /**
     * Sets the schema format of the message
     *
     * @param schemaFormat the schema format of the message
     * @return this Message instance
     */
    default Message schemaFormat(String schemaFormat) {
        setSchemaFormat(schemaFormat);
        return this;
    }

    /**
     * Returns the content type of the message
     *
     * @return the content type of the message
     */
    String getContentType();

    /**
     * Sets the content type of the message
     *
     * @param contentType the content type of the message
     */
    void setContentType(String contentType);

    /**
     * Sets the content type of the message
     *
     * @param contentType the content type of the message
     * @return this Message instance
     */
    default Message contentType(String contentType) {
        setContentType(contentType);
        return this;
    }

    /**
     * Returns the name of the message
     *
     * @return the name of the message
     */
    String getName();

    /**
     * Sets the name of the message
     *
     * @param name the name of the message
     */
    void setName(String name);

    /**
     * Sets the name of the message
     *
     * @param name the name of the message
     * @return this Message instance
     */
    default Message name(String name) {
        setName(name);
        return this;
    }

    /**
     * Returns the title of the message
     *
     * @return the title of the message
     */
    String getTitle();

    /**
     * Sets the title of the message
     *
     * @param title the title of the message
     */
    void setTitle(String title);

    /**
     * Sets the title of the message
     *
     * @param title the title of the message
     * @return this Message instance
     */
    default Message title(String title) {
        setTitle(title);
        return this;
    }

    /**
     * Returns the summary of the message
     *
     * @return the summary of the message
     */
    String getSummary();

    /**
     * Sets the summary of the message
     *
     * @param summary the summary of the message
     */
    void setSummary(String summary);

    /**
     * Sets the summary of the message
     *
     * @param summary the summary of the message
     * @return this Message instance
     */
    default Message summary(String summary) {
        setSummary(summary);
        return this;
    }

    /**
     * Returns the description of the message
     *
     * @return the description of the message
     */
    String getDescription();

    /**
     * Sets the description of the message
     *
     * @param description the description of the message
     */
    void setDescription(String description);

    /**
     * Sets the description of the message
     *
     * @param description the description of the message
     * @return this Message instance
     */
    default Message description(String description) {
        setDescription(description);
        return this;
    }

    /**
     * Returns the tags of the message
     *
     * @return the tags of the message
     */
    List<Tag> getTags();

    /**
     * Sets the tags of the message
     *
     * @param tags the tags of the message
     */
    void setTags(List<Tag> tags);

    /**
     * Sets the tags of the message
     *
     * @param tags the tags of the message
     * @return this Message instance
     */
    default Message tags(List<Tag> tags) {
        setTags(tags);
        return this;
    }

    /**
     * Adds the given tag to the Message map of tags.
     *
     * @param tag the tag to add
     * @return the current Message object
     **/
    Message addTag(Tag tag);

    /**
     * Removes the given tag to the Message map of tags.
     *
     * @param tag the tag to remove
     */
    void removeTag(Tag tag);

    /**
     * Returns the external documentation of the message
     *
     * @return the external documentation of the message
     */
    ExternalDocumentation getExternalDocumentation();

    /**
     * Sets the external documentation of the message
     *
     * @param externalDocumentation the external documentation of the message
     */
    void setExternalDocumentation(ExternalDocumentation externalDocumentation);

    /**
     * Sets the external documentation of the message
     *
     * @param externalDocumentation the external documentation of the message
     * @return this Message instance
     */
    default Message externalDocumentation(ExternalDocumentation externalDocumentation) {
        setExternalDocumentation(externalDocumentation);
        return this;
    }

    /**
     * Returns the bindings of the message
     *
     * @return the bindings of the message
     */
    MessageBindings getBindings();

    /**
     * Sets the bindings of the message
     *
     * @param bindings the bindings of the message
     */
    void setBindings(MessageBindings bindings);

    /**
     * Sets the bindings of the message
     *
     * @param bindings the bindings of the message
     * @return this Message instance
     */
    default Message bindings(MessageBindings bindings) {
        setBindings(bindings);
        return this;
    }

    /**
     * Returns the examples of the message
     *
     * @return the examples of the message
     */
    List<String> getExample();

    /**
     * Sets the examples of the message
     *
     * @param example the examples of the message
     */
    void setExample(List<String> example);

    /**
     * Sets the examples of the message
     *
     * @param example the examples of the message
     * @return this Message instance
     */
    default Message example(List<String> example) {
        setExample(example);
        return this;
    }

    /**
     * This method adds a string item to examples list of a Message instance and returns the instance.
     *
     * @param example an item to be added to examples list
     * @return Message instance with the added examples item.
     */
    Message addExample(String example);

    /**
     * This method removes a string item to examples list of a Message instance.
     *
     * @param example an item to be removed to examples list
     */
    void removeExample(String example);

    /**
     * Returns the messageTraits of the message
     *
     * @return the messageTraits of the message
     */
    List<MessageTrait> getTraits();

    /**
     * Sets the messageTraits of the message
     *
     * @param messageTraits the messageTraits of the message
     */
    void setTraits(List<MessageTrait> messageTraits);

    /**
     * Sets the messageTraits of the message
     *
     * @param messageTraits the messageTraits of the message
     * @return this Message instance
     */
    default Message traits(List<MessageTrait> messageTraits) {
        setTraits(messageTraits);
        return this;
    }

    /**
     * This method adds a MessageTrait item to the MessageTrait list of a Message instance and returns the instance.
     *
     * @param messageTrait an item to be added to MessageTrait list
     * @return Message instance with the added MessageTrait item.
     */
    Message addTrait(MessageTrait messageTrait);

    /**
     * This method removes a string item to the MessageTrait list of a Message instance.
     *
     * @param messageTrait an item to be removed to MessageTrait list
     */
    void removeTrait(MessageTrait messageTrait);
}
