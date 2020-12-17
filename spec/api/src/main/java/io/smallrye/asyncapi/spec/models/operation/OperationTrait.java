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
import io.smallrye.asyncapi.spec.models.Reference;
import io.smallrye.asyncapi.spec.models.binding.OperationBindings;
import io.smallrye.asyncapi.spec.models.message.Message;
import io.smallrye.asyncapi.spec.models.tag.Tag;

/**
 * Describes a trait that MAY be applied to an Operation Object.
 *
 * This object MAY contain any property from the Operation Object, except message and traits.
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0/#operationTraitObject"
 */
public interface OperationTrait extends Constructible, Extensible<OperationTrait>, Reference<OperationTrait> {

    /**
     * Returns the id of the operation trait
     *
     * @return the id of the operation trait
     */
    String getOperationId();

    /**
     * Sets the id of the operation trait
     *
     * @param operationId the id of the operation trait
     */
    void setOperationId(String operationId);

    /**
     * Sets the id of the operation trait
     *
     * @param operationId the id of the operation trait
     * @return this OperationTrait instance
     */
    default OperationTrait operationId(String operationId) {
        setOperationId(operationId);
        return this;
    }

    /**
     * Returns the summary of the operation trait
     *
     * @return the summary of the operation trait
     */
    String getSummary();

    /**
     * Sets the summary of the operation trait
     *
     * @param summary the summary of the operation trait
     */
    void setSummary(String summary);

    /**
     * Sets the summary of the operation trait
     *
     * @param summary the summary of the operation trait
     * @return this OperationTrait instance
     */
    default OperationTrait summary(String summary) {
        setSummary(summary);
        return this;
    }

    /**
     * Returns the description of the operation trait
     *
     * @return the description of the operation trait
     */
    String getDescription();

    /**
     * Sets the description of the operation trait
     *
     * @param description the description of the operation trait
     */
    void setDescription(String description);

    /**
     * Sets the description of the operation
     *
     * @param description the description of the operation trait
     * @return this OperationTrait instance
     */
    default OperationTrait description(String description) {
        setDescription(description);
        return this;
    }

    /**
     * Returns the tags of the operation trait
     *
     * @return the tags of the operation trait
     */
    List<Tag> getTags();

    /**
     * Sets the tags of the operation trait
     *
     * @param tags the tags of the operation trait
     */
    void setTags(List<Tag> tags);

    /**
     * Adds the given tag to the OperationTrait's map of tags.
     *
     * @param tag the tag to add
     * @return the current OperationTrait object
     **/
    OperationTrait addTag(Tag tag);

    /**
     * Removes the given tag to the OperationTrait's map of tags.
     *
     * @param tag the tag to remove
     */
    void removeTag(Tag tag);

    /**
     * Sets the tags of the operation trait
     *
     * @param tags the tags of the operation trait
     * @return this OperationTrait instance
     */
    default OperationTrait tags(List<Tag> tags) {
        setTags(tags);
        return this;
    }

    /**
     * Returns the external documentation of the operation trait
     *
     * @return the external documentation of the operation trait
     */
    ExternalDocumentation getExternalDocumentation();

    /**
     * Sets the external documentation of the operation trait
     *
     * @param externalDocumentation the external documentation of the operation trait
     */
    void setExternalDocumentation(ExternalDocumentation externalDocumentation);

    /**
     * Sets the external documentation of the operation trait
     *
     * @param externalDocumentation the external documentation of the operation trait
     * @return this OperationTrait instance
     */
    default OperationTrait externalDocumentation(ExternalDocumentation externalDocumentation) {
        setExternalDocumentation(externalDocumentation);
        return this;
    }

    /**
     * Returns the bindings of the operation trait
     *
     * @return the bindings of the operation trait
     */
    OperationBindings getBindings();

    /**
     * Sets the bindings of the operation trait
     *
     * @param bindings the bindings of the operation trait
     */
    void setBindings(OperationBindings bindings);

    /**
     * Sets the bindings of the operation trait
     *
     * @param bindings the bindings of the operation trait
     * @return this OperationTrait instance
     */
    default OperationTrait bindings(OperationBindings bindings) {
        setBindings(bindings);
        return this;
    }

    /**
     * Returns the message of the operation trait
     *
     * @return the message of the operation trait
     */
    Message getMessage();

    /**
     * Sets the message of the operation trait
     *
     * @param message the message of the operation trait
     */
    void setMessage(Message message);

    /**
     * Sets the message of the operation trait
     *
     * @param message the message of the operation trait
     * @return this OperationTrait instance
     */
    default OperationTrait traits(Message message) {
        setMessage(message);
        return this;
    }
}
