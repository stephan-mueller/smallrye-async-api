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
 * Describes a trait that MAY be applied to a Message Object.
 *
 * This object MAY contain any property from the Message Object, except payload and traits.
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0/#messageTraitObject"
 */
public interface MessageTrait extends Constructible, Extensible<MessageTrait>, Reference<MessageTrait> {

    /**
     * Returns the headers of the message trait
     *
     * @return the headers of the message trait
     */
    Schema getHeaders();

    /**
     * Sets the headers of the message trait
     *
     * @param headers the headers of the message trait
     */
    void setHeaders(Schema headers);

    /**
     * Sets the headers of the message trait
     *
     * @param headers the headers of the message trait
     * @return this MessageTrait instance
     */
    default MessageTrait headers(Schema headers) {
        setHeaders(headers);
        return this;
    }

    /**
     * Returns the correlationId of the message trait
     *
     * @return the correlationId of the message trait
     */
    CorrelationID getCorrelationID();

    /**
     * Sets the correlationId of the message trait
     *
     * @param correlationId the correlationId of the message trait
     */
    void setCorrelationID(CorrelationID correlationId);

    /**
     * Sets the correlationId of the message trait
     *
     * @param correlationId the correlationId of the message trait
     * @return this MessageTrait instance
     */
    default MessageTrait correlationId(CorrelationID correlationId) {
        setCorrelationID(correlationId);
        return this;
    }

    /**
     * Returns the schema format of the message trait
     *
     * @return the schema format of the message trait
     */
    String getSchemaFormat();

    /**
     * Sets the schema format of the message trait
     *
     * @param schemaFormat the schema format of the message trait
     */
    void setSchemaFormat(String schemaFormat);

    /**
     * Sets the schema format of the message trait
     *
     * @param schemaFormat the schema format of the message trait
     * @return this MessageTrait instance
     */
    default MessageTrait schemaFormat(String schemaFormat) {
        setSchemaFormat(schemaFormat);
        return this;
    }

    /**
     * Returns the content type of the message trait
     *
     * @return the content type of the message trait
     */
    String getContentType();

    /**
     * Sets the content type of the message trait
     *
     * @param contentType the content type of the message trait
     */
    void setContentType(String contentType);

    /**
     * Sets the content type of the message trait
     *
     * @param contentType the content type of the message trait
     * @return this MessageTrait instance
     */
    default MessageTrait contentType(String contentType) {
        setContentType(contentType);
        return this;
    }

    /**
     * Returns the name of the message trait
     *
     * @return the name of the message trait
     */
    String getName();

    /**
     * Sets the name of the message trait
     *
     * @param name the name of the message trait
     */
    void setName(String name);

    /**
     * Sets the name of the message trait
     *
     * @param name the name of the message trait
     * @return this MessageTrait instance
     */
    default MessageTrait name(String name) {
        setName(name);
        return this;
    }

    /**
     * Returns the title of the message trait
     *
     * @return the title of the message trait
     */
    String getTitle();

    /**
     * Sets the title of the message trait
     *
     * @param title the title of the message trait
     */
    void setTitle(String title);

    /**
     * Sets the title of the message trait
     *
     * @param title the title of the message trait
     * @return this MessageTrait instance
     */
    default MessageTrait title(String title) {
        setTitle(title);
        return this;
    }

    /**
     * Returns the summary of the message trait
     *
     * @return the summary of the message trait
     */
    String getSummary();

    /**
     * Sets the summary of the message trait
     *
     * @param summary the summary of the message trait
     */
    void setSummary(String summary);

    /**
     * Sets the summary of the message trait
     *
     * @param summary the summary of the message trait
     * @return this MessageTrait instance
     */
    default MessageTrait summary(String summary) {
        setSummary(summary);
        return this;
    }

    /**
     * Returns the description of the message trait
     *
     * @return the description of the message trait
     */
    String getDescription();

    /**
     * Sets the description of the message trait
     *
     * @param description the description of the message trait
     */
    void setDescription(String description);

    /**
     * Sets the description of the message trait
     *
     * @param description the description of the message trait
     * @return this MessageTrait instance
     */
    default MessageTrait description(String description) {
        setDescription(description);
        return this;
    }

    /**
     * Returns the tags of the message trait
     *
     * @return the tags of the message trait
     */
    List<Tag> getTags();

    /**
     * Sets the tags of the message trait
     *
     * @param tags the tags of the message trait
     */
    void setTags(List<Tag> tags);

    /**
     * Sets the tags of the message trait
     *
     * @param tags the tags of the message trait
     * @return this MessageTrait instance
     */
    default MessageTrait tags(List<Tag> tags) {
        setTags(tags);
        return this;
    }

    /**
     * Adds the given tag to the MessageTrait map of tags.
     *
     * @param tag the tag to add
     * @return the current Operation object
     **/
    MessageTrait addTag(Tag tag);

    /**
     * Removes the given tag to the MessageTrait map of tags.
     *
     * @param tag the tag to remove
     */
    void removeTag(Tag tag);

    /**
     * Returns the external documentation of the message trait
     *
     * @return the external documentation of the message trait
     */
    ExternalDocumentation getExternalDocumentation();

    /**
     * Sets the external documentation of the message trait
     *
     * @param externalDocumentation the external documentation of the message trait
     */
    void setExternalDocumentation(ExternalDocumentation externalDocumentation);

    /**
     * Sets the external documentation of the message trait
     *
     * @param externalDocumentation the external documentation of the message trait
     * @return this MessageTrait instance
     */
    default MessageTrait externalDocumentation(ExternalDocumentation externalDocumentation) {
        setExternalDocumentation(externalDocumentation);
        return this;
    }

    /**
     * Returns the bindings of the message trait
     *
     * @return the bindings of the message trait
     */
    MessageBindings getBindings();

    /**
     * Sets the bindings of the message trait
     *
     * @param bindings the bindings of the message trait
     */
    void setBindings(MessageBindings bindings);

    /**
     * Sets the bindings of the message trait
     *
     * @param bindings the bindings of the message trait
     * @return this MessageTrait instance
     */
    default MessageTrait bindings(MessageBindings bindings) {
        setBindings(bindings);
        return this;
    }

    /**
     * Returns the examples of the message trait
     *
     * @return the examples of the message trait
     */
    List<String> getExample();

    /**
     * Sets the examples of the message trait
     *
     * @param example the examples of the message trait
     */
    void setExample(List<String> example);

    /**
     * Sets the examples of the message trait
     *
     * @param example the examples of the message trait
     * @return this MessageTrait instance
     */
    default MessageTrait example(List<String> example) {
        setExample(example);
        return this;
    }

    /**
     * This method adds a string item to examples list of a MessageTrait instance and returns the instance.
     *
     * @param example an item to be added to examples list
     * @return MessageTrait instance with the added examples item.
     */
    MessageTrait addExample(String example);

    /**
     * This method removes a string item to examples list of a MessageTrait instance.
     *
     * @param example an item to be removed to examples list
     */
    void removeExample(String example);
}
