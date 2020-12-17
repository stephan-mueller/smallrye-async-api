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
package io.smallrye.asyncapi.core.api.models.info;

import io.smallrye.asyncapi.core.api.models.ExtensibleImpl;
import io.smallrye.asyncapi.core.api.models.ModelImpl;
import io.smallrye.asyncapi.spec.models.info.Contact;
import io.smallrye.asyncapi.spec.models.info.Info;
import io.smallrye.asyncapi.spec.models.info.License;

/**
 * An implementation of the {@link Info} AsyncAPI model interface.
 */
public class InfoImpl extends ExtensibleImpl<Info> implements Info, ModelImpl {

    private String title;

    private String version;

    private String description;

    private String termsOfService;

    private Contact contact;

    private License license;

    /**
     * @see Info#getTitle()
     */
    @Override
    public String getTitle() {
        return this.title;
    }

    /**
     * @see Info#setTitle(String title)
     */
    @Override
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * @see Info#getVersion()
     */
    @Override
    public String getVersion() {
        return this.version;
    }

    /**
     * @see Info#setVersion(String version)
     */
    @Override
    public void setVersion(final String version) {
        this.version = version;
    }

    /**
     * @see Info#getDescription()
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * @see Info#setDescription(String description)
     */
    @Override
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @see Info#getTermsOfService()
     */
    @Override
    public String getTermsOfService() {
        return this.termsOfService;
    }

    /**
     * @see Info#setTermsOfService(String termsOfService)
     */
    @Override
    public void setTermsOfService(final String termsOfService) {
        this.termsOfService = termsOfService;
    }

    /**
     * @see Info#getContact()
     */
    @Override
    public Contact getContact() {
        return this.contact;
    }

    /**
     * @see Info#setContact(Contact contact)
     */
    @Override
    public void setContact(final Contact contact) {
        this.contact = contact;
    }

    /**
     * @see Info#getLicense()
     */
    @Override
    public License getLicense() {
        return this.license;
    }

    /**
     * @see Info#setLicense(License license)
     */
    @Override
    public void setLicense(final License license) {
        this.license = license;
    }

    @Override
    public String toString() {
        return "InfoImpl{" + "title='" + title + '\'' + ", version='" + version + '\'' + ", description='" + description + '\''
                + ", termsOfService='" + termsOfService + '\'' + ", contact=" + contact + ", license=" + license + '}';
    }
}
