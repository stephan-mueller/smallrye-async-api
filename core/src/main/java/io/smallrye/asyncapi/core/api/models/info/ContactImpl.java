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

/**
 * An implementation of the {@link Contact} AsyncAPI model interface.
 */
public class ContactImpl extends ExtensibleImpl<Contact> implements Contact, ModelImpl {

    private String name;

    private String url;

    private String email;

    /**
     * @see Contact#getName()
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * @see Contact#setName(String name)
     */
    @Override
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @see Contact#getUrl()
     */
    @Override
    public String getUrl() {
        return this.url;
    }

    /**
     * @see Contact#setUrl(String url)
     */
    @Override
    public void setUrl(final String url) {
        this.url = url;
    }

    /**
     * @see Contact#getEmail()
     */
    @Override
    public String getEmail() {
        return this.email;
    }

    /**
     * @see Contact#setEmail(String email)
     */
    @Override
    public void setEmail(final String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ContactImpl{" + "name='" + name + '\'' + ", url='" + url + '\'' + ", email='" + email + '\'' + '}';
    }
}
