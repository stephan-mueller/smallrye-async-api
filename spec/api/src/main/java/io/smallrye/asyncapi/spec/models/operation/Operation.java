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
package io.smallrye.asyncapi.spec.models.operation;

import java.util.List;

import io.smallrye.asyncapi.spec.models.Constructible;
import io.smallrye.asyncapi.spec.models.Extensible;
import io.smallrye.asyncapi.spec.models.ExternalDocumentation;
import io.smallrye.asyncapi.spec.models.binding.OperationBindings;
import io.smallrye.asyncapi.spec.models.message.Message;
import io.smallrye.asyncapi.spec.models.tag.Tag;

/**
 * Describes a publish or a subscribe operation.
 *
 * This provides a place to document how and why messages are sent and received. For example, an operation might describe a chat
 * application
 * use case where a user sends a text message to a group.
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0/#operationObject"
 */
public interface Operation extends Constructible, Extensible<Operation> {

    /**
     * Returns the id of the operation
     *
     * @return the id of the operation
     */
    String getOperationId();

    /**
     * Sets the id of the operation
     *
     * @param operationId the id of the operation
     */
    void setOperationId(String operationId);

    /**
     * Sets the id of the operation
     *
     * @param operationId the id of the operation
     * @return this Operation instance
     */
    default Operation operationId(String operationId) {
        setOperationId(operationId);
        return this;
    }

    /**
     * Returns the summary of the operation
     *
     * @return the summary of the operation
     */
    String getSummary();

    /**
     * Sets the summary of the operation
     *
     * @param summary the summary of the operation
     */
    void setSummary(String summary);

    /**
     * Sets the summary of the operation
     *
     * @param summary the summary of the operation
     * @return this Operation instance
     */
    default Operation summary(String summary) {
        setSummary(summary);
        return this;
    }

    /**
     * Returns the description of the operation
     *
     * @return the description of the operation
     */
    String getDescription();

    /**
     * Sets the description of the operation
     *
     * @param description the description of the operation
     */
    void setDescription(String description);

    /**
     * Sets the description of the operation
     *
     * @param description the description of the operation
     * @return this Operation instance
     */
    default Operation description(String description) {
        setDescription(description);
        return this;
    }

    /**
     * Returns the tags of the operation
     *
     * @return the tags of the operation
     */
    List<Tag> getTags();

    /**
     * Sets the tags of the operation
     *
     * @param tags the tags of the operation
     */
    void setTags(List<Tag> tags);

    /**
     * Sets the tags of the operation
     *
     * @param tags the tags of the operation
     * @return this Operation instance
     */
    default Operation tags(List<Tag> tags) {
        setTags(tags);
        return this;
    }

    /**
     * Adds the given tag to the Operation's map of tags.
     *
     * @param tag the tag to add
     * @return the current Operation object
     **/
    Operation addTag(Tag tag);

    /**
     * Removes the given tag to the Operation's map of tags.
     *
     * @param tag the tag to remove
     */
    void removeTag(Tag tag);

    /**
     * Returns the external documentation of the operation
     *
     * @return the external documentation of the operation
     */
    ExternalDocumentation getExternalDocumentation();

    /**
     * Sets the external documentation of the operation
     *
     * @param externalDocumentation the external documentation of the operation
     */
    void setExternalDocumentation(ExternalDocumentation externalDocumentation);

    /**
     * Sets the external documentation of the operation
     *
     * @param externalDocumentation the external documentation of the operation
     * @return this Operation instance
     */
    default Operation externalDocumentation(ExternalDocumentation externalDocumentation) {
        setExternalDocumentation(externalDocumentation);
        return this;
    }

    /**
     * Returns the bindings of the operation
     *
     * @return the bindings of the operation
     */
    OperationBindings getBindings();

    /**
     * Sets the bindings of the operation
     *
     * @param bindings the bindings of the operation
     */
    void setBindings(OperationBindings bindings);

    /**
     * Sets the bindings of the operation
     *
     * @param bindings the bindings of the operation
     * @return this Operation instance
     */
    default Operation bindings(OperationBindings bindings) {
        setBindings(bindings);
        return this;
    }

    /**
     * Returns the operation traits of the operation
     *
     * @return the operation traits of the operation
     */
    List<OperationTrait> getOperationTraits();

    /**
     * Sets the operation traits of the operation
     *
     * @param operationTraits the operation traits of the operation
     */
    void setOperationTraits(List<OperationTrait> operationTraits);

    /**
     * Sets the operation traits of the operation
     *
     * @param operationTraits the operation traits of the operation
     * @return this Operation instance
     */
    default Operation operationTraits(List<OperationTrait> operationTraits) {
        setOperationTraits(operationTraits);
        return this;
    }

    /**
     * Adds the given OperationTrait to the Operation's map of OperationTraits.
     *
     * @param operationTraits the OperationTrait to add
     * @return the current Operation object
     **/
    Operation addOperationTrait(OperationTrait operationTraits);

    /**
     * Removes the given OperationTrait to the Operation's map of OperationTraits.
     *
     * @param operationTraits the OperationTrait to remove
     */
    void removeOperationTrait(OperationTrait operationTraits);

    /**
     * Returns the message of the operation
     *
     * @return the message of the operation
     */
    Message getMessage();

    /**
     * Sets the message of the operation
     *
     * @param message the message of the operation
     */
    void setMessage(Message message);

    /**
     * Sets the message of the operation
     *
     * @param message the message of the operation
     * @return this Operation instance
     */
    default Operation message(Message message) {
        setMessage(message);
        return this;
    }
}
