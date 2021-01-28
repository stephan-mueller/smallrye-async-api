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

package io.smallrye.asyncapi.tck;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.collection.IsMapWithSize.aMapWithSize;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.testng.annotations.Test;

import io.restassured.response.ValidatableResponse;

public class StreetlightsAppTest extends AppTestBase {

    @Deployment(name = "streetlights")
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "streetlights.war")
                .addPackages(true, "io.smallrye.asyncapi.apps.streetlights");
    }

    @RunAsClient
    @Test(dataProvider = "formatProvider")
    public void testVersion(String type) {
        ValidatableResponse vr = callEndpoint(type);
        vr.body("asyncapi", equalTo("2.0.0"));
    }

    @RunAsClient
    @Test(dataProvider = "formatProvider")
    public void testInfo(String type) {
        ValidatableResponse vr = callEndpoint(type);
        vr.body("info.title", equalTo("Streetlights API"));
        vr.body("info.version", equalTo("1.0.0"));
        vr.body("info.description", equalTo(
                "The Smartylighting Streetlights API allows you to remotely manage the city lights. ### Check out its awesome features: * TurnOnOff a specific streetlight on/off * Dim a specific streetlight * Receive real-time information about environmental lighting conditions"));
    }

    @RunAsClient
    @Test(dataProvider = "formatProvider")
    public void testLicense(String type) {
        ValidatableResponse vr = callEndpoint(type);
        vr.body("info.license.name", equalTo("Apache 2.0"));
        vr.body("info.license.url", equalTo("https://www.apache.org/licenses/LICENSE-2.0"));
    }

    @RunAsClient
    @Test(dataProvider = "formatProvider")
    public void testEternalDocs(String type) {
        ValidatableResponse vr = callEndpoint(type);
        vr.body("externalDocs.url", equalTo("https://example.com"));
        vr.body("externalDocs.description", equalTo("Find more info here"));
    }

    @RunAsClient
    @Test(dataProvider = "formatProvider")
    public void testId(String type) {
        ValidatableResponse vr = callEndpoint(type);
        vr.body("id", equalTo("urn:com:smartylighting:streetlights:server"));
    }

    @RunAsClient
    @Test(dataProvider = "formatProvider")
    public void testDefaultContent(String type) {
        ValidatableResponse vr = callEndpoint(type);
        vr.body("defaultContentType", equalTo("application/json"));
    }

    @RunAsClient
    @Test(dataProvider = "formatProvider")
    public void testServers(String type) {
        ValidatableResponse vr = callEndpoint(type);
        vr.body("servers", hasSize(1));

        String url = "api.streetlights.smartylighting.com:{port}";
        String serverPath = "servers.find { it.url == '" + url + "' }";

        vr.body(serverPath + ".description", equalTo("Test broker"));
        vr.body(serverPath + ".variables", aMapWithSize(1));
        vr.body(serverPath + ".variables.port.default", equalTo("1883"));
        vr.body(serverPath + ".variables.port.description", equalTo("Secure connection (TLS) is available through port 8883."));
        vr.body(serverPath + ".variables.port.enum", containsInAnyOrder("1883", "8883"));
        vr.body(serverPath + ".security", hasSize(3));
        vr.body(serverPath + ".security[0].apiKey", hasSize(0));
        vr.body(serverPath + ".security[1].supportedOauthFlows", hasSize(3));
        vr.body(serverPath + ".security[1].supportedOauthFlows",
                containsInAnyOrder("streetlights:on", "streetlights:off", "streetlights:dim"));
        vr.body(serverPath + ".security[2].openIdConnectWellKnown", hasSize(0));
    }

    @RunAsClient
    @Test(dataProvider = "formatProvider")
    public void testChannels(String type) {
        ValidatableResponse vr = callEndpoint(type);

        String topic = "'smartylighting/streetlights/1/0/action/{streetlightId}/dim'";
        vr.body("channels." + topic + ".channel", equalTo(topic.replace("\'", "")));
        vr.body("channels." + topic + ".publish.operationId", equalTo("dimLight"));
        vr.body("channels." + topic + ".publish.traits[0].$ref", equalTo("#/components/operationTraits/kafka"));
        vr.body("channels." + topic + ".publish.message.$ref", equalTo("#/components/messages/dimLight"));
        vr.body("channels." + topic + ".parameters.$ref", equalTo("#/components/parameters/streetlightId"));

        topic = "'smartylighting/streetlights/1/0/action/{streetlightId}/turn/on'";
        vr.body("channels." + topic + ".channel", equalTo(topic.replace("\'", "")));
        vr.body("channels." + topic + ".publish.operationId", equalTo("turnOn"));
        vr.body("channels." + topic + ".publish.traits[0].$ref", equalTo("#/components/operationTraits/kafka"));
        vr.body("channels." + topic + ".publish.message.correlationID.description", equalTo("Default Correlation ID"));
        vr.body("channels." + topic + ".publish.message.correlationID.location", equalTo("$message.header#/correlationId"));
        vr.body("channels." + topic + ".publish.message.name", equalTo("turnOnOff"));
        vr.body("channels." + topic + ".publish.message.title", equalTo("TurnOnOff on/off"));
        vr.body("channels." + topic + ".publish.message.summary",
                equalTo("Command a particular streetlight to turn the lights on or off."));
        vr.body("channels." + topic + ".publish.message.traits[0].headers.type", equalTo("object"));
        vr.body("channels." + topic + ".publish.message.traits[0].headers.properties.'my-app-header'.maximum", equalTo(100));
        vr.body("channels." + topic + ".publish.message.traits[0].headers.properties.'my-app-header'.minimum", equalTo(0));
        vr.body("channels." + topic + ".publish.message.traits[0].headers.properties.'my-app-header'.type", equalTo("integer"));
        vr.body("channels." + topic + ".publish.message.traits[0].contentType", equalTo("application/json"));
        vr.body("channels." + topic + ".publish.message.traits[0].name", equalTo("commonHeaders"));
        vr.body("channels." + topic + ".publish.message.traits[0].description", equalTo("Common Headers"));
        vr.body("channels." + topic + ".publish.message.traits[0].example",
                containsInAnyOrder("{'minimum': 0, 'maximum': 100}", "{'minimum': 10, 'maximum': 50}"));

        topic = "'smartylighting/streetlights/1/0/action/{streetlightId}/turn/off'";
        vr.body("channels." + topic + ".channel", equalTo(topic.replace("\'", "")));
        vr.body("channels." + topic + ".publish.operationId", equalTo("turnOff"));
        vr.body("channels." + topic + ".publish.traits[0].$ref", equalTo("#/components/operationTraits/kafka"));
        vr.body("channels." + topic + ".publish.message.$ref", equalTo("#/components/messages/turnOnOff"));
        vr.body("channels." + topic + ".parameters.$ref", equalTo("#/components/parameters/streetlightId"));

        topic = "'smartylighting/streetlights/1/0/event/{streetlightId}/lighting/measure'";
        vr.body("channels." + topic + ".channel", equalTo(topic.replace("\'", "")));
        vr.body("channels." + topic + ".description",
                equalTo("The topic on which measure values may be produced and consumed."));
        vr.body("channels." + topic + ".subscribe.operationId", equalTo("receiveLightMeasurement"));
        vr.body("channels." + topic + ".subscribe.summary",
                equalTo("Receive information about environmental lighting conditions of a particular streetlight."));
        vr.body("channels." + topic + ".subscribe.traits[0].$ref", equalTo("#/components/operationTraits/kafka"));
        vr.body("channels." + topic + ".subscribe.message.$ref", equalTo("#/components/messages/lightMeasured"));
        vr.body("channels." + topic + ".parameters.$ref", equalTo("#/components/parameters/streetlightId"));
    }

    @RunAsClient
    @Test(dataProvider = "formatProvider")
    public void testComponents(String type) {
        ValidatableResponse vr = callEndpoint(type);
        String messagesPath = "components.messages.";
        vr.body(messagesPath + "dimLight.name", equalTo("dimLight"));
        vr.body(messagesPath + "dimLight.title", equalTo("Dim light"));
        vr.body(messagesPath + "dimLight.summary", equalTo("Command a particular streetlight to dim the lights."));
        vr.body(messagesPath + "dimLight.traits[0].$ref", equalTo("#/components/messageTraits/commonHeaders"));

        vr.body(messagesPath + "lightMeasured.contentType", equalTo("application/json"));
        vr.body(messagesPath + "lightMeasured.name", equalTo("lightMeasured"));
        vr.body(messagesPath + "lightMeasured.title", equalTo("Light measured"));
        vr.body(messagesPath + "lightMeasured.summary",
                equalTo("Inform about environmental lighting conditions for a particular streetlight."));
        vr.body(messagesPath + "lightMeasured.tags[0].name", equalTo("streetlights"));
        vr.body(messagesPath + "lightMeasured.tags[1].name", equalTo("measure"));
        vr.body(messagesPath + "lightMeasured.traits[0].$ref", equalTo("#/components/messageTraits/commonHeaders"));

        String securitySchemesPath = "components.securitySchemes.apiKey.";
        vr.body(securitySchemesPath + "type", equalTo("apiKey"));
        vr.body(securitySchemesPath + "in", equalTo("user"));
        vr.body(securitySchemesPath + "description", equalTo("Provide your API key as the user and leave the password empty."));

        String flowsPath = "components.securitySchemes.supportedOauthFlows.";
        vr.body(flowsPath + "type", equalTo("oauth2"));
        vr.body(flowsPath + "description", equalTo("Flows to support OAuth 2.0"));
        vr.body(flowsPath + "flows.implicit.authorizationUrl", equalTo("https://authserver.example/auth"));
        vr.body(flowsPath + "flows.implicit.scopes.'streetlights:on'", equalTo("Ability to switch lights on"));
        vr.body(flowsPath + "flows.implicit.scopes.'streetlights:off'", equalTo("Ability to switch lights off"));
        vr.body(flowsPath + "flows.implicit.scopes.'streetlights:dim'", equalTo("Ability to dim the lights"));
        vr.body(flowsPath + "flows.password.tokenUrl", equalTo("https://authserver.example/token"));
        vr.body(flowsPath + "flows.password.scopes.'streetlights:on'", equalTo("Ability to switch lights on"));
        vr.body(flowsPath + "flows.password.scopes.'streetlights:off'", equalTo("Ability to switch lights off"));
        vr.body(flowsPath + "flows.password.scopes.'streetlights:dim'", equalTo("Ability to dim the lights"));
        vr.body(flowsPath + "flows.clientCredentials.tokenUrl", equalTo("https://authserver.example/token"));
        vr.body(flowsPath + "flows.clientCredentials.scopes.'streetlights:on'", equalTo("Ability to switch lights on"));
        vr.body(flowsPath + "flows.clientCredentials.scopes.'streetlights:off'", equalTo("Ability to switch lights off"));
        vr.body(flowsPath + "flows.clientCredentials.scopes.'streetlights:dim'", equalTo("Ability to dim the lights"));
        vr.body(flowsPath + "flows.authorizationCode.authorizationUrl", equalTo("https://authserver.example/auth"));
        vr.body(flowsPath + "flows.authorizationCode.tokenUrl", equalTo("https://authserver.example/token"));
        vr.body(flowsPath + "flows.authorizationCode.refreshUrl", equalTo("https://authserver.example/refresh"));
        vr.body(flowsPath + "flows.authorizationCode.scopes.'streetlights:on'", equalTo("Ability to switch lights on"));
        vr.body(flowsPath + "flows.authorizationCode.scopes.'streetlights:off'", equalTo("Ability to switch lights off"));
        vr.body(flowsPath + "flows.authorizationCode.scopes.'streetlights:dim'", equalTo("Ability to dim the lights"));

        String openIdConnectWellKnownPath = "components.securitySchemes.openIdConnectWellKnown.";
        vr.body(openIdConnectWellKnownPath + "type", equalTo("openIdConnect"));
        vr.body(openIdConnectWellKnownPath + "openIdConnectUrl", equalTo("https://authserver.example/.well-known"));

        String parameters = "components.parameters.streetlightId.";
        vr.body(parameters + "description", equalTo("The ID of the streetlight."));
        vr.body(parameters + "schema.type", equalTo("string"));

        String schemas = "components.schemas.";
        vr.body(schemas + "dimLightPayload.required[0]", equalTo("percentage"));
        vr.body(schemas + "dimLightPayload.required[1]", equalTo("sentAt"));
        vr.body(schemas + "dimLightPayload.type", equalTo("object"));
        vr.body(schemas + "dimLightPayload.properties.percentage.format", equalTo("int32"));
        vr.body(schemas + "dimLightPayload.properties.percentage.description",
                equalTo("Percentage to which the light should be dimmed to."));
        vr.body(schemas + "dimLightPayload.properties.percentage.maximum", equalTo(100));
        vr.body(schemas + "dimLightPayload.properties.percentage.minimum", equalTo(0));
        vr.body(schemas + "dimLightPayload.properties.percentage.type", equalTo("integer"));

        vr.body(schemas + "dimLightPayload.properties.sentAt.format", equalTo("date-time"));
        vr.body(schemas + "dimLightPayload.properties.sentAt.description", equalTo("Date and time when the message was sent."));
        vr.body(schemas + "dimLightPayload.properties.sentAt.type", equalTo("string"));

        vr.body(schemas + "turnOnOffPayload.required[0]", equalTo("command"));
        vr.body(schemas + "turnOnOffPayload.required[1]", equalTo("sentAt"));
        vr.body(schemas + "turnOnOffPayload.type", equalTo("object"));
        vr.body(schemas + "turnOnOffPayload.properties.command.$ref", equalTo("#/components/schemas/Command"));
        vr.body(schemas + "turnOnOffPayload.properties.sentAt.format", equalTo("date-time"));
        vr.body(schemas + "turnOnOffPayload.properties.sentAt.type", equalTo("string"));

        vr.body(schemas + "Command.type", equalTo("string"));
        vr.body(schemas + "Command.enumeration[0]", equalTo("OFF"));
        vr.body(schemas + "Command.enumeration[1]", equalTo("ON"));

        vr.body(schemas + "lightMeasuredPayload.type", equalTo("object"));
        vr.body(schemas + "lightMeasuredPayload.required[0]", equalTo("lumens"));
        vr.body(schemas + "lightMeasuredPayload.properties.lumens.format", equalTo("int32"));
        vr.body(schemas + "lightMeasuredPayload.properties.lumens.description", equalTo("Light intensity measured in lumens."));
        vr.body(schemas + "lightMeasuredPayload.properties.lumens.minimum", equalTo(0));
        vr.body(schemas + "lightMeasuredPayload.properties.lumens.type", equalTo("integer"));
        vr.body(schemas + "lightMeasuredPayload.properties.sentAt.$ref", equalTo("#/components/schemas/sentAt"));

    }

    @RunAsClient
    @Test(dataProvider = "formatProvider")
    public void testTags(String type) {
        ValidatableResponse vr = callEndpoint(type);
        vr.body("tags[0].name", equalTo("streetlights"));
    }
}
