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

/**
 * Constants related to Info
 *
 * @see <a href="https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.3.md#infoObject">infoObject</a>
 */
public class InfoConstant {

    public static final String PROP_TERMS_OF_SERVICE = "termsOfService";
    public static final String PROP_TITLE = "title";
    public static final String PROP_VERSION = "version";
    public static final String PROP_DESCRIPTION = "description";
    public static final String PROP_LICENSE = "license";
    public static final String PROP_CONTACT = "contact";

    private InfoConstant() {
    }
}
