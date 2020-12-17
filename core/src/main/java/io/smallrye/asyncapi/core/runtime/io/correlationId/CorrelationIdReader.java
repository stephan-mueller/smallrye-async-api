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
package io.smallrye.asyncapi.core.runtime.io.correlationId;

import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.AnnotationValue;

import com.fasterxml.jackson.databind.JsonNode;

import io.smallrye.asyncapi.core.api.models.message.CorrelationIDImpl;
import io.smallrye.asyncapi.core.runtime.io.IoLogging;
import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.util.JandexUtil;
import io.smallrye.asyncapi.spec.models.message.CorrelationID;

public class CorrelationIdReader {

    public CorrelationIdReader() {
    }

    /**
     * Annotation to CorrelationID
     *
     * @param annotationValue the {@literal @}CorrelationID annotation
     * @return CorrelationID model
     */
    public static CorrelationID readCorrelationID(final AnnotationValue annotationValue) {
        if (annotationValue == null) {
            return null;
        }
        IoLogging.logger.annotation("@CorrelationID");
        AnnotationInstance nested = annotationValue.asNested();

        CorrelationID correlationID = new CorrelationIDImpl();
        correlationID.setDescription(JandexUtil.stringValue(nested, CorrelationIdConstant.PROP_DESCRIPTION));
        correlationID.setLocation(JandexUtil.stringValue(nested, CorrelationIdConstant.PROP_LOCATION));
        return correlationID;
    }

    /**
     * Reads an {@link CorrelationID} Message node.
     *
     * @param node the json node
     * @return CorrelationID model
     */
    public static CorrelationID readCorrelationID(final JsonNode node) {
        if (node == null) {
            return null;
        }
        IoLogging.logger.annotation("CorrelationID");

        CorrelationID correlationID = new CorrelationIDImpl();
        correlationID.setDescription(JsonUtil.stringProperty(node, CorrelationIdConstant.PROP_DESCRIPTION));
        correlationID.setLocation(JsonUtil.stringProperty(node, CorrelationIdConstant.PROP_LOCATION));
        return correlationID;
    }
}
