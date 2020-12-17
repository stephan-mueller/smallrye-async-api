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
package io.smallrye.asyncapi.core.runtime.io.contact;

import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.AnnotationValue;

import com.fasterxml.jackson.databind.JsonNode;

import io.smallrye.asyncapi.core.api.models.info.ContactImpl;
import io.smallrye.asyncapi.core.runtime.io.IoLogging;
import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.io.extension.ExtensionReader;
import io.smallrye.asyncapi.core.runtime.util.JandexUtil;
import io.smallrye.asyncapi.spec.models.info.Contact;

/**
 * This reads the Contact from annotations or json
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0/#contactObject"
 */
public class ContactReader {

    private ContactReader() {
    }

    /**
     * Reads an Contact annotation.
     *
     * @param annotationValue the {@literal @}Contact annotation
     * @return Contact model
     */
    public static Contact readContact(final AnnotationValue annotationValue) {
        if (annotationValue == null) {
            return null;
        }
        IoLogging.logger.singleAnnotation("@Contact");
        AnnotationInstance nested = annotationValue.asNested();
        Contact contact = new ContactImpl();
        contact.setName(JandexUtil.stringValue(nested, ContactConstant.PROP_NAME));
        contact.setUrl(JandexUtil.stringValue(nested, ContactConstant.PROP_URL));
        contact.setEmail(JandexUtil.stringValue(nested, ContactConstant.PROP_EMAIL));
        return contact;
    }

    /**
     * Reads an {@link Contact} AsyncAPI node.
     *
     * @param node the json node
     * @return Contact model
     */
    public static Contact readContact(final JsonNode node) {
        if (node == null) {
            return null;
        }
        IoLogging.logger.singleJsonNode("Contact");
        Contact contact = new ContactImpl();
        contact.setName(JsonUtil.stringProperty(node, ContactConstant.PROP_NAME));
        contact.setUrl(JsonUtil.stringProperty(node, ContactConstant.PROP_URL));
        contact.setEmail(JsonUtil.stringProperty(node, ContactConstant.PROP_EMAIL));
        ExtensionReader.readExtensions(node, contact);
        return contact;
    }

}
