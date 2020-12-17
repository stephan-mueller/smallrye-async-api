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

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Configuration details for a supported OAuth Flow
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0/#a-name-oauthflowobject-a-oauth-flow-object"
 */
@Target({})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface OAuthFlow {

    /**
     * <strong>REQUIRED</strong>. The authorization URL to be used for this flow. This MUST be in the form of a URL.
     *
     * @return authorization URL for this flow
     **/
    String authorizationUrl() default "";

    /**
     * <strong>REQUIRED</strong>. The token URL to be used for this flow. This MUST be in the form of a URL.
     *
     * @return token URL for this flow
     **/
    String tokenUrl() default "";

    /**
     * The URL to be used for obtaining refresh tokens.
     *
     * This MUST be in the form of a URL. Applies to oauth2 type.
     *
     * @return URL for obtaining refresh tokens
     **/
    String refreshUrl() default "";

    /**
     * <strong>REQUIRED</strong>. The available scopes for the OAuth2 security scheme.
     *
     * A map between the scope name and a short description for it.
     *
     * @return scopes available for this security scheme
     **/
    OAuthScope[] scopes() default {};
}
