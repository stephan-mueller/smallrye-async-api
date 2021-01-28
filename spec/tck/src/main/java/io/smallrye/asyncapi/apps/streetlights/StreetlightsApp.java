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

package io.smallrye.asyncapi.apps.streetlights;

import io.smallrye.asyncapi.spec.annotations.AsyncAPI;
import io.smallrye.asyncapi.spec.annotations.ExternalDocumentation;
import io.smallrye.asyncapi.spec.annotations.info.Info;
import io.smallrye.asyncapi.spec.annotations.info.License;
import io.smallrye.asyncapi.spec.annotations.security.OAuthFlow;
import io.smallrye.asyncapi.spec.annotations.security.OAuthFlows;
import io.smallrye.asyncapi.spec.annotations.security.OAuthScope;
import io.smallrye.asyncapi.spec.annotations.security.SecurityRequirement;
import io.smallrye.asyncapi.spec.annotations.security.SecurityScheme;
import io.smallrye.asyncapi.spec.annotations.security.SecuritySchemeIn;
import io.smallrye.asyncapi.spec.annotations.security.SecuritySchemeType;
import io.smallrye.asyncapi.spec.annotations.security.SecuritySchemes;
import io.smallrye.asyncapi.spec.annotations.server.Server;
import io.smallrye.asyncapi.spec.annotations.server.ServerVariable;
import io.smallrye.asyncapi.spec.annotations.tag.Tag;

@AsyncAPI(asyncapi = "2.0.0", defaultContentType = "application/json", info = @Info(title = "Streetlights API", version = "1.0.0", description = "The Smartylighting Streetlights API allows you to remotely manage the city lights. ### Check out its awesome features: * TurnOnOff a specific streetlight on/off * Dim a specific streetlight * Receive real-time information about environmental lighting conditions", license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0")), externalDocs = @ExternalDocumentation(url = "https://example.com", description = "Find more info here"), id = "urn:com:smartylighting:streetlights:server", servers = {
        @Server(url = "api.streetlights.smartylighting.com:{port}", protocol = "mqtt", description = "Test broker", variables = {
                @ServerVariable(name = "port", description = "Secure connection (TLS) is available through port 8883.", defaultValue = "1883", enumeration = {
                        "1883", "8883" })
        }, security = {
                @SecurityRequirement(name = "apiKey"),
                @SecurityRequirement(name = "supportedOauthFlows", values = { "streetlights:on", "streetlights:off",
                        "streetlights:dim" }),
                @SecurityRequirement(name = "openIdConnectWellKnown")
        })
}, tags = {
        @Tag(name = "streetlights")
})
@SecuritySchemes(values = {
        @SecurityScheme(name = "apiKey", type = SecuritySchemeType.APIKEY, in = SecuritySchemeIn.USER, description = "Provide your API key as the user and leave the password empty."),
        @SecurityScheme(name = "supportedOauthFlows", type = SecuritySchemeType.OAUTH2, in = SecuritySchemeIn.DEFAULT, description = "Flows to support OAuth 2.0", flows = @OAuthFlows(implicit = @OAuthFlow(authorizationUrl = "https://authserver.example/auth", scopes = {
                @OAuthScope(name = "streetlights:on", description = "Ability to switch lights on"),
                @OAuthScope(name = "streetlights:off", description = "Ability to switch lights off"),
                @OAuthScope(name = "streetlights:dim", description = "Ability to dim the lights")
        }), password = @OAuthFlow(tokenUrl = "https://authserver.example/token", scopes = {
                @OAuthScope(name = "streetlights:on", description = "Ability to switch lights on"),
                @OAuthScope(name = "streetlights:off", description = "Ability to switch lights off"),
                @OAuthScope(name = "streetlights:dim", description = "Ability to dim the lights")
        }), clientCredentials = @OAuthFlow(tokenUrl = "https://authserver.example/token", scopes = {
                @OAuthScope(name = "streetlights:on", description = "Ability to switch lights on"),
                @OAuthScope(name = "streetlights:off", description = "Ability to switch lights off"),
                @OAuthScope(name = "streetlights:dim", description = "Ability to dim the lights")
        }), authorizationCode = @OAuthFlow(authorizationUrl = "https://authserver.example/auth", tokenUrl = "https://authserver.example/token", refreshUrl = "https://authserver.example/refresh", scopes = {
                @OAuthScope(name = "streetlights:on", description = "Ability to switch lights on"),
                @OAuthScope(name = "streetlights:off", description = "Ability to switch lights off"),
                @OAuthScope(name = "streetlights:dim", description = "Ability to dim the lights")
        }))),
        @SecurityScheme(name = "openIdConnectWellKnown", type = SecuritySchemeType.OPENIDCONNECT, in = SecuritySchemeIn.DEFAULT, openIdConnectUrl = "https://authserver.example/.well-known")
})
public class StreetlightsApp {
}
