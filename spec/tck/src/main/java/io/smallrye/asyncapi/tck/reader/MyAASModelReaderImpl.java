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
package io.smallrye.asyncapi.tck.reader;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import io.smallrye.asyncapi.spec.AASFactory;
import io.smallrye.asyncapi.spec.AASModelReader;
import io.smallrye.asyncapi.spec.annotations.schema.SchemaType;
import io.smallrye.asyncapi.spec.annotations.security.SecuritySchemeIn;
import io.smallrye.asyncapi.spec.annotations.security.SecuritySchemeType;
import io.smallrye.asyncapi.spec.models.AsyncAPI;
import io.smallrye.asyncapi.spec.models.Components;
import io.smallrye.asyncapi.spec.models.channel.ChannelItem;
import io.smallrye.asyncapi.spec.models.channel.Channels;
import io.smallrye.asyncapi.spec.models.info.Info;
import io.smallrye.asyncapi.spec.models.info.License;
import io.smallrye.asyncapi.spec.models.message.Message;
import io.smallrye.asyncapi.spec.models.message.MessageTrait;
import io.smallrye.asyncapi.spec.models.operation.Operation;
import io.smallrye.asyncapi.spec.models.parameter.Parameter;
import io.smallrye.asyncapi.spec.models.parameter.Parameters;
import io.smallrye.asyncapi.spec.models.schema.Schema;
import io.smallrye.asyncapi.spec.models.security.OAuthFlow;
import io.smallrye.asyncapi.spec.models.security.OAuthFlows;
import io.smallrye.asyncapi.spec.models.security.OAuthScope;
import io.smallrye.asyncapi.spec.models.security.SecurityScheme;
import io.smallrye.asyncapi.spec.models.server.Server;
import io.smallrye.asyncapi.spec.models.server.ServerVariable;

public class MyAASModelReaderImpl implements AASModelReader {

    @Override
    public AsyncAPI buildModel() {
        return AASFactory.createObject(AsyncAPI.class)
                .defaultContentType("application/json")
                .identifier("urn:com:smartylighting:streetlights:server")
                .info(AASFactory.createObject(Info.class)
                        .title("Streetlights API")
                        .version("1.0.0")
                        .description("The Smartylighting Streetlights API allows you to remotely manage the city lights.\n"
                                + "\n"
                                + "    ### Check out its awesome features:\n" + "\n"
                                + "    * Turn a specific streetlight on/off \uD83C\uDF03\n"
                                + "    * Dim a specific streetlight \uD83D\uDE0E\n"
                                + "    * Receive real-time information about environmental lighting conditions \uD83D\uDCC8")
                        .license(AASFactory.createObject(License.class)
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")))
                .servers(new ArrayList<>())
                .addServer(AASFactory.createObject(Server.class)
                        .url("api.streetlights.smartylighting.com:{port}")
                        .description("Test broker")
                        .addVariable("port", AASFactory.createObject(ServerVariable.class)
                                .name("port")
                                .description("Secure connection (TLS) is available through port 8883.")
                                .defaultValue("1883")
                                .enumeration(new ArrayList<>())
                                .addEnumeration("1883")
                                .addEnumeration("8883")))
                .channels(AASFactory.createObject(Channels.class))
                .addChannelItem("smartylighting/streetlights/1/0/event/{streetlightId}/lighting/measured", AASFactory
                        .createObject(ChannelItem.class)
                        .channel("smartylighting/streetlights/1/0/event/{streetlightId}/lighting/measured")
                        .description("The topic on which measure values may be produced and consumed.")
                        .parameters(AASFactory.createObject(Parameters.class)
                                .value(new ArrayList<>()))
                        .subscribe(AASFactory.createObject(Operation.class)
                                .summary(
                                        "Receive information about environmental lighting conditions of a particular streetlight.")
                                .operationId("receiveLightMeasurement")
                                .operationTraits(new ArrayList<>())
                                .message(AASFactory.createObject(Message.class)
                                        .ref("#/components/messages/lightMeasured"))))
                .addChannelItem("smartylighting/streetlights/1/0/action/{streetlightId}/turn/on",
                        AASFactory.createObject(ChannelItem.class)
                                .channel("smartylighting/streetlights/1/0/action/{streetlightId}/turn/on")
                                .parameters(AASFactory.createObject(Parameters.class)
                                        .value(new ArrayList<>()))
                                .publish(AASFactory.createObject(Operation.class)
                                        .operationId("turnOn")
                                        .operationTraits(new ArrayList<>())
                                        .message(AASFactory.createObject(Message.class)
                                                .ref("#/components/messages/turnOnOff"))))
                .addChannelItem("smartylighting/streetlights/1/0/action/{streetlightId}/turn/off",
                        AASFactory.createObject(ChannelItem.class)
                                .channel("smartylighting/streetlights/1/0/action/{streetlightId}/turn/off")
                                .parameters(AASFactory.createObject(Parameters.class)
                                        .value(new ArrayList<>()))
                                .publish(AASFactory.createObject(Operation.class)
                                        .operationId("turnOff")
                                        .operationTraits(new ArrayList<>())
                                        .message(AASFactory.createObject(Message.class)
                                                .ref("#/components/messages/turnOnOff"))))
                .addChannelItem("smartylighting/streetlights/1/0/action/{streetlightId}/dim",
                        AASFactory.createObject(ChannelItem.class)
                                .channel("smartylighting/streetlights/1/0/action/{streetlightId}/dim")
                                .parameters(AASFactory.createObject(Parameters.class)
                                        .value(new ArrayList<>()))
                                .publish(AASFactory.createObject(Operation.class)
                                        .operationId("dimLight")
                                        .operationTraits(new ArrayList<>())
                                        .message(AASFactory.createObject(Message.class)
                                                .ref("#/components/messages/dimLight"))))
                .components(AASFactory.createObject(Components.class)
                        .messages(new HashMap<>())
                        .addMessage("lightMeasured", AASFactory.createObject(Message.class)
                                .name("lightMeasured")
                                .title("Light measure")
                                .summary("Inform about environmental lighting conditions for a particular streetlight.")
                                .contentType("application/json")
                                .traits(new ArrayList<>())
                                .payload(AASFactory.createObject(Schema.class)
                                        .ref("#/components/schemas/dimLightPayload")))
                        .addMessage("turnOnOff", AASFactory.createObject(Message.class)
                                .name("turnOnOff")
                                .title("TurnOnOff on/off")
                                .summary("Command a particular streetlight to turn the lights on or off.")
                                .traits(new ArrayList<>())
                                .payload(AASFactory.createObject(Schema.class)
                                        .ref("#/components/schemas/turnOnOffPayload")))
                        .addMessage("dimLight", AASFactory.createObject(Message.class)
                                .name("dimLight")
                                .title("Dim light")
                                .summary("Command a particular streetlight to dim the lights.")
                                .traits(new ArrayList<>())
                                .payload(AASFactory.createObject(Schema.class)
                                        .ref("#/components/schemas/dimLightPayload")))
                        .schemas(new HashMap<>())
                        .addSchema("lightMeasuredPayload", AASFactory.createObject(Schema.class)
                                .type(SchemaType.OBJECT)
                                .properties(new LinkedHashMap<>())
                                .addProperty("", AASFactory.createObject(Schema.class)
                                        .name("lumens")
                                        .minimum(new BigDecimal(0))
                                        .description("Light intensity measure in lumens."))
                                .addProperty("", AASFactory.createObject(Schema.class)
                                        .name("sentAt")
                                        .ref("#/components/schemas/sentAt")))
                        .addSchema("turnOnOffPayload", AASFactory.createObject(Schema.class)
                                .type(SchemaType.OBJECT)
                                .properties(new LinkedHashMap<>())
                                .addProperty("", AASFactory.createObject(Schema.class)
                                        .name("command")
                                        .type(SchemaType.STRING)
                                        .enumeration(new ArrayList<>())
                                        .addEnumeration("on")
                                        .addEnumeration("off")
                                        .description("Whether to turn on or off the light."))
                                .addProperty("", AASFactory.createObject(Schema.class)
                                        .name("sentAt")
                                        .ref("#/components/schemas/sentAt")))
                        .addSchema("dimLightPayload", AASFactory.createObject(Schema.class)
                                .type(SchemaType.OBJECT)
                                .properties(new LinkedHashMap<>())
                                .addProperty("", AASFactory.createObject(Schema.class)
                                        .name("percentage")
                                        .type(SchemaType.INTEGER)
                                        .description("Percentage to which the light should be dimmed to.")
                                        .minimum(new BigDecimal(0))
                                        .maximum(new BigDecimal(100)))
                                .addProperty("", AASFactory.createObject(Schema.class)
                                        .ref("#/components/schemas/sentAt")))
                        .addSchema("sentAt", AASFactory.createObject(Schema.class)
                                .type(SchemaType.STRING)
                                .format("date-time")
                                .description("Date and time when the message was sent."))
                        .securitySchemes(new HashMap<>())
                        .addSecurityScheme("apiKey", AASFactory.createObject(SecurityScheme.class)
                                .type(SecuritySchemeType.APIKEY)
                                .in(SecuritySchemeIn.USER)
                                .description("Provide your API key as the user and leave the password empty."))
                        .addSecurityScheme("supportedOauthFlows", AASFactory.createObject(SecurityScheme.class)
                                .type(SecuritySchemeType.OAUTH2)
                                .description("Flows to support OAuth 2.0")
                                .flows(AASFactory.createObject(OAuthFlows.class)
                                        .implicit(AASFactory.createObject(OAuthFlow.class)
                                                .authorizationUrl("https://authserver.example/auth")
                                                .scopes(new ArrayList<>())
                                                .addScope(AASFactory.createObject(OAuthScope.class)
                                                        .name("streetlights:on")
                                                        .description("Ability to switch lights on"))
                                                .addScope(AASFactory.createObject(OAuthScope.class)
                                                        .name("streetlights:off")
                                                        .description("Ability to switch lights off"))
                                                .addScope(AASFactory.createObject(OAuthScope.class)
                                                        .name("streetlights:dim")
                                                        .description("Ability to dim the lights")))
                                        .password(AASFactory.createObject(OAuthFlow.class)
                                                .tokenUrl("https://authserver.example/token")
                                                .scopes(new ArrayList<>())
                                                .addScope(AASFactory.createObject(OAuthScope.class)
                                                        .name("streetlights:on")
                                                        .description("Ability to switch lights on"))
                                                .addScope(AASFactory.createObject(OAuthScope.class)
                                                        .name("streetlights:off")
                                                        .description("Ability to switch lights off"))
                                                .addScope(AASFactory.createObject(OAuthScope.class)
                                                        .name("streetlights:dim")
                                                        .description("Ability to dim the lights")))
                                        .clientCredentials(AASFactory.createObject(OAuthFlow.class)
                                                .tokenUrl("https://authserver.example/token")
                                                .scopes(new ArrayList<>())
                                                .addScope(AASFactory.createObject(OAuthScope.class)
                                                        .name("streetlights:on")
                                                        .description("Ability to switch lights on"))
                                                .addScope(AASFactory.createObject(OAuthScope.class)
                                                        .name("streetlights:off")
                                                        .description("Ability to switch lights off"))
                                                .addScope(AASFactory.createObject(OAuthScope.class)
                                                        .name("streetlights:dim")
                                                        .description("Ability to dim the lights")))
                                        .authorizationCode(AASFactory.createObject(OAuthFlow.class)
                                                .authorizationUrl("https://authserver.example/auth")
                                                .tokenUrl("https://authserver.example/token")
                                                .refreshUrl("https://authserver.example/refresh")
                                                .scopes(new ArrayList<>())
                                                .addScope(AASFactory.createObject(OAuthScope.class)
                                                        .name("streetlights:on")
                                                        .description("Ability to switch lights on"))
                                                .addScope(AASFactory.createObject(OAuthScope.class)
                                                        .name("streetlights:off")
                                                        .description("Ability to switch lights off"))
                                                .addScope(AASFactory.createObject(OAuthScope.class)
                                                        .name("streetlights:dim")
                                                        .description("Ability to dim the lights")))))
                        .addSecurityScheme("openIdConnectWellKnown", AASFactory.createObject(SecurityScheme.class)
                                .type(SecuritySchemeType.OPENIDCONNECT)
                                .openIdConnectUrl("https://authserver.example/.well-known"))
                        .parameters(new HashMap<>())
                        .addParameter("streetlightId", AASFactory.createObject(Parameter.class)
                                .description("The ID of the streetlight.")
                                .schema(AASFactory.createObject(Schema.class)
                                        .type(SchemaType.STRING)))
                        .messageTraits(new HashMap<>())
                        .addMessageTrait("commonHeaders", AASFactory.createObject(MessageTrait.class)
                                .headers(AASFactory.createObject(Schema.class)
                                        .type(SchemaType.OBJECT)
                                        .properties(new LinkedHashMap<>())
                                        .addProperty("", AASFactory.createObject(Schema.class)
                                                .name("my-app-header")
                                                .type(SchemaType.INTEGER)
                                                .minimum(new BigDecimal(0))
                                                .maximum(new BigDecimal(100))))));
    }
}
