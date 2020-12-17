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

import io.smallrye.asyncapi.spec.models.Constructible;
import io.smallrye.asyncapi.spec.models.Extensible;

/**
 * An object that specifies an identifier at design time that can used for message tracing and correlation.
 *
 * For specifying and computing the location of a Correlation ID, a runtime expression is used.
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0/#correlationIdObject"
 */
public interface CorrelationID extends Constructible, Extensible<CorrelationID> {

    /**
     * Returns the description of the CorrelationID
     *
     * @return the description of the CorrelationID
     */
    String getDescription();

    /**
     * Sets the description of the CorrelationID
     *
     * @param description the description of the CorrelationID
     */
    void setDescription(String description);

    /**
     * Sets the description of the message
     *
     * @param description the description of the CorrelationID
     * @return this CorrelationID instance
     */
    default CorrelationID description(String description) {
        setDescription(description);
        return this;
    }

    /**
     * Returns the location of the CorrelationID
     *
     * @return the location of the CorrelationID
     */
    String getLocation();

    /**
     * Sets the location of the CorrelationID
     *
     * @param location the location of the CorrelationID
     */
    void setLocation(String location);

    /**
     * Sets the location of the message
     *
     * @param location the location of the CorrelationID
     * @return this CorrelationID instance
     */
    default CorrelationID location(String location) {
        setLocation(location);
        return this;
    }
}
