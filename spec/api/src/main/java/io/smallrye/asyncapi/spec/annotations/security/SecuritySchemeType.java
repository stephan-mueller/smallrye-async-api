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
package io.smallrye.asyncapi.spec.annotations.security;

public enum SecuritySchemeType {

    DEFAULT(""),

    USERPASSWORD("userPassword"),

    APIKEY("apiKey"),

    X509("X509"),

    SYMCENCRYPT("symmetricEncryption"),

    ASYMCENCRYPT("asymmetricEncryption"),

    HTTPAPIKEY("httpApiKey"),

    HTTP("http"),

    OAUTH2("oauth2"),

    OPENIDCONNECT("openIdConnect");

    private String value;

    SecuritySchemeType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
