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
package io.smallrye.asyncapi.core.runtime.io.securityscheme;

import org.jboss.jandex.DotName;

import io.smallrye.asyncapi.spec.annotations.security.SecuritySchemes;

public class SecuritySchemesConstant {

    static final DotName DOTNAME_SECURITY_SCHEMES = DotName.createSimple(SecuritySchemes.class.getName());

    public static final String PROP_VALUES = "values";

    public static final String PROP_TYPE = "type";

    public static final String PROP_DESCRIPTION = "description";

    public static final String PROP_NAME = "name";

    public static final String PROP_IN = "in";

    public static final String PROP_SCHEME = "scheme";

    public static final String PROP_BEARER_FORMAT = "bearerFormat";

    public static final String PROP_OPENID_CONNECT_URL = "openIdConnectUrl";

    public static final String PROP_FLOWS = "flows";

    public static final String PROP_IMPLICIT = "implicit";

    public static final String PROP_PASSWORD = "password";

    public static final String PROP_CLIENT_CREDENTIALS = "clientCredentials";

    public static final String PROP_AUTHORIZATION_CODE = "authorizationCode";

    public static final String PROP_AUTHORIZATION_URL = "authorizationUrl";

    public static final String PROP_TOKEN_URL = "tokenUrl";

    public static final String PROP_REFRESH_URL = "refreshUrl";

    public static final String PROP_SCOPES = "scopes";

    public SecuritySchemesConstant() {
    }
}
