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
package io.smallrye.asyncapi.core.api.util;

import io.smallrye.asyncapi.core.api.AsyncApiConfig;
import io.smallrye.asyncapi.core.api.models.info.ContactImpl;
import io.smallrye.asyncapi.core.api.models.info.LicenseImpl;
import io.smallrye.asyncapi.spec.models.AsyncAPI;

public class ConfigUtil {
    private ConfigUtil() {
    }

    public static final void applyConfig(AsyncApiConfig config, AsyncAPI aai) {
        configureInfo(config, aai);
    }

    protected static final void configureInfo(AsyncApiConfig config, AsyncAPI aai) {
        if (config.getInfoTitle() != null) {
            aai.getInfo().setTitle(config.getInfoTitle());
        }
        if (config.getInfoVersion() != null) {
            aai.getInfo().setVersion(config.getInfoVersion());
        }
        if (config.getInfoDescription() != null) {
            aai.getInfo().setDescription(config.getInfoDescription());
        }
        if (config.getInfoTermsOfService() != null) {
            aai.getInfo().setTermsOfService(config.getInfoTermsOfService());
        }

        // Contact
        if (aai.getInfo().getContact() == null && (config.getInfoContactEmail() != null || config.getInfoContactName() != null
                || config.getInfoContactUrl() != null)) {
            aai.getInfo().setContact(new ContactImpl());
        }
        if (config.getInfoContactEmail() != null) {
            aai.getInfo().getContact().setEmail(config.getInfoContactEmail());
        }
        if (config.getInfoContactName() != null) {
            aai.getInfo().getContact().setName(config.getInfoContactName());
        }
        if (config.getInfoContactUrl() != null) {
            aai.getInfo().getContact().setUrl(config.getInfoContactUrl());
        }

        // License
        if (aai.getInfo().getLicense() == null && (config.getInfoLicenseName() != null || config.getInfoLicenseUrl() != null)) {
            aai.getInfo().setLicense(new LicenseImpl());
        }
        if (config.getInfoLicenseName() != null) {
            aai.getInfo().getLicense().setName(config.getInfoLicenseName());
        }
        if (config.getInfoLicenseUrl() != null) {
            aai.getInfo().getLicense().setUrl(config.getInfoLicenseUrl());
        }
    }
}
