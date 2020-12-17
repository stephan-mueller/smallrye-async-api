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

import com.fasterxml.jackson.databind.node.ObjectNode;

import io.smallrye.asyncapi.core.runtime.io.JsonUtil;
import io.smallrye.asyncapi.core.runtime.io.contact.ContactWriter;
import io.smallrye.asyncapi.core.runtime.io.definition.DefinitionConstant;
import io.smallrye.asyncapi.core.runtime.io.extension.ExtensionWriter;
import io.smallrye.asyncapi.core.runtime.io.license.LicenseWriter;
import io.smallrye.asyncapi.spec.models.info.Info;

/**
 * This write the Info to json
 *
 * @see <a href="https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.3.md#infoObject">infoObject</a>
 */
public class InfoWriter {
    private InfoWriter() {
    }

    /**
     * Writes the {@link Info} model to the JSON tree.
     *
     * @param parent the parent json node
     * @param model the Info model
     */
    public static void writeInfo(ObjectNode parent, Info model) {
        if (model == null) {
            return;
        }
        ObjectNode node = parent.putObject(DefinitionConstant.PROP_INFO);

        JsonUtil.stringProperty(node, InfoConstant.PROP_TITLE, model.getTitle());
        JsonUtil.stringProperty(node, InfoConstant.PROP_DESCRIPTION, model.getDescription());
        JsonUtil.stringProperty(node, InfoConstant.PROP_TERMS_OF_SERVICE, model.getTermsOfService());
        ContactWriter.writeContact(node, model.getContact());
        LicenseWriter.writeLicense(node, model.getLicense());
        JsonUtil.stringProperty(node, InfoConstant.PROP_VERSION, model.getVersion());
        ExtensionWriter.writeExtensions(node, model);
    }
}
