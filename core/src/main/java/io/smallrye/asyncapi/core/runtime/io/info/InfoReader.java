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
package io.smallrye.asyncapi.core.runtime.io.info;

import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.AnnotationValue;

import com.fasterxml.jackson.databind.JsonNode;

import io.smallrye.asyncapi.core.api.models.info.InfoImpl;
import io.smallrye.asyncapi.core.runtime.io.IoLogging;
import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.io.contact.ContactReader;
import io.smallrye.asyncapi.core.runtime.io.extension.ExtensionReader;
import io.smallrye.asyncapi.core.runtime.io.license.LicenseReader;
import io.smallrye.asyncapi.core.runtime.util.JandexUtil;
import io.smallrye.asyncapi.spec.models.info.Info;

/**
 * This reads the Info from annotations or json
 *
 * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0/#a-name-infoobject-a-info-object">Info Object</a>
 */
public class InfoReader {

    private InfoReader() {
    }

    /**
     * Annotation to Info
     *
     * @param annotationValue the {@literal @}Info annotation
     * @return Info model
     */
    public static Info readInfo(final AnnotationValue annotationValue) {
        if (annotationValue == null) {
            return null;
        }
        IoLogging.logger.annotation("@Info");
        AnnotationInstance nested = annotationValue.asNested();

        Info info = new InfoImpl();
        info.setTitle(JandexUtil.stringValue(nested, InfoConstant.PROP_TITLE));
        info.setDescription(JandexUtil.stringValue(nested, InfoConstant.PROP_DESCRIPTION));
        info.setTermsOfService(JandexUtil.stringValue(nested, InfoConstant.PROP_TERMS_OF_SERVICE));
        info.setContact(ContactReader.readContact(nested.value(InfoConstant.PROP_CONTACT)));
        info.setLicense(LicenseReader.readLicense(nested.value(InfoConstant.PROP_LICENSE)));
        info.setVersion(JandexUtil.stringValue(nested, InfoConstant.PROP_VERSION));
        return info;
    }

    /**
     * Reads an {@link Info} AsyncAPI node.
     *
     * @param node the json node
     * @return Info model
     */
    public static Info readInfo(final JsonNode node) {
        if (node == null) {
            return null;
        }
        IoLogging.logger.singleJsonNode("Info");

        Info info = new InfoImpl();
        info.setTitle(JsonUtil.stringProperty(node, InfoConstant.PROP_TITLE));
        info.setDescription(JsonUtil.stringProperty(node, InfoConstant.PROP_DESCRIPTION));
        info.setTermsOfService(JsonUtil.stringProperty(node, InfoConstant.PROP_TERMS_OF_SERVICE));
        info.setContact(ContactReader.readContact(node.get(InfoConstant.PROP_CONTACT)));
        info.setLicense(LicenseReader.readLicense(node.get(InfoConstant.PROP_LICENSE)));
        info.setVersion(JsonUtil.stringProperty(node, InfoConstant.PROP_VERSION));
        ExtensionReader.readExtensions(node, info);
        return info;
    }

}
