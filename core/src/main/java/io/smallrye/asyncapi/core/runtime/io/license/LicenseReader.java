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
package io.smallrye.asyncapi.core.runtime.io.license;

import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.AnnotationValue;

import com.fasterxml.jackson.databind.JsonNode;

import io.smallrye.asyncapi.core.api.models.info.LicenseImpl;
import io.smallrye.asyncapi.core.runtime.io.IoLogging;
import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.io.extension.ExtensionReader;
import io.smallrye.asyncapi.core.runtime.util.JandexUtil;
import io.smallrye.asyncapi.spec.models.info.License;

/**
 * This reads the License from annotations or json
 *
 * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0/#licenseObject">License Object</a>
 */
public class LicenseReader {

    private LicenseReader() {
    }

    /**
     * Reads an License annotation.
     *
     * @param annotationValue the {@literal @}License annotation
     * @return License model
     */
    public static License readLicense(final AnnotationValue annotationValue) {
        if (annotationValue == null) {
            return null;
        }
        IoLogging.logger.singleAnnotation("@License");
        AnnotationInstance nested = annotationValue.asNested();
        License license = new LicenseImpl();
        license.setName(JandexUtil.stringValue(nested, LicenseConstant.PROP_NAME));
        license.setUrl(JandexUtil.stringValue(nested, LicenseConstant.PROP_URL));
        return license;
    }

    /**
     * Reads an {@link License} AsyncAPI node.
     *
     * @param node the json node
     * @return License model
     */
    public static License readLicense(final JsonNode node) {
        if (node == null) {
            return null;
        }
        IoLogging.logger.singleJsonNode("License");
        License license = new LicenseImpl();
        license.setName(JsonUtil.stringProperty(node, LicenseConstant.PROP_NAME));
        license.setUrl(JsonUtil.stringProperty(node, LicenseConstant.PROP_URL));
        ExtensionReader.readExtensions(node, license);
        return license;
    }
}
