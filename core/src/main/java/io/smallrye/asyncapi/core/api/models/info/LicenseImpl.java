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
import io.smallrye.asyncapi.spec.models.info.License;

/**
 * An implementation of the {@link License} AsyncAPI model interface.
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0#licenseObject"
 */
public class LicenseImpl extends ExtensibleImpl<License> implements License, ModelImpl {

    private String name;

    private String url;

    /**
     * @see License#getName()
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * @see License#setName(String)
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @see License#getUrl()
     */
    @Override
    public String getUrl() {
        return this.url;
    }

    /**
     * @see License#setUrl(String)
     */
    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "LicenseImpl{" + "name='" + name + '\'' + ", url='" + url + '\'' + '}';
    }
}
