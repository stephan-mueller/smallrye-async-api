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

import com.fasterxml.jackson.databind.node.ObjectNode;

import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.io.extension.ExtensionWriter;
import io.smallrye.asyncapi.core.runtime.io.info.InfoConstant;
import io.smallrye.asyncapi.spec.models.info.License;

/**
 * This writes the License to json
 *
 * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0/#licenseObject">License Object</a>
 */
public class LicenseWriter {

    private LicenseWriter() {
    }

    /**
     * Writes the {@link License} model to the JSON tree.
     *
     * @param parent the parent json node
     * @param model the License model
     */
    public static void writeLicense(ObjectNode parent, License model) {
        if (model == null) {
            return;
        }
        ObjectNode node = parent.putObject(InfoConstant.PROP_LICENSE);

        JsonUtil.stringProperty(node, LicenseConstant.PROP_NAME, model.getName());
        JsonUtil.stringProperty(node, LicenseConstant.PROP_URL, model.getUrl());
        ExtensionWriter.writeExtensions(node, model);
    }
}
