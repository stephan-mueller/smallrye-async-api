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

package io.smallrye.asyncapi.tck.bindings;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.testng.annotations.Test;

import io.restassured.response.ValidatableResponse;
import io.smallrye.asyncapi.tck.AppTestBase;

public class KafkaServiceTest extends AppTestBase {

    @Deployment(name = "kafka")
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "kafka.war")
                .addPackages(true, "io.smallrye.asyncapi.bindings.kafka");
    }

    @RunAsClient
    @Test(dataProvider = "formatProvider")
    public void testVersion(String type) {
        ValidatableResponse vr = callEndpoint(type);
        vr.body("asyncapi", equalTo("2.0.0"));
    }

    @RunAsClient
    @Test(dataProvider = "formatProvider")
    public void testMessageBinding(String type) {
        ValidatableResponse vr = callEndpoint(type);

        String kafkaBinding = "channels.kafka-test1.subscribe.message.bindings.kafkaBinding.";
        vr.body(kafkaBinding + "key.enumeration", containsInAnyOrder("myKey"));
        vr.body(kafkaBinding + "key.type", equalTo("string"));
        vr.body(kafkaBinding + "bindingVersion", equalTo("0.1.0"));
    }

    @RunAsClient
    @Test(dataProvider = "formatProvider")
    public void testOperationBinding(String type) {
        ValidatableResponse vr = callEndpoint(type);

        String kafkaBinding = "channels.kafka-test2.subscribe.bindings.kafkaBinding.";
        vr.body(kafkaBinding + "groupId.enumeration", containsInAnyOrder("myGroupId"));
        vr.body(kafkaBinding + "groupId.type", equalTo("string"));
        vr.body(kafkaBinding + "clientId.enumeration", containsInAnyOrder("myClientId"));
        vr.body(kafkaBinding + "clientId.type", equalTo("string"));
        vr.body(kafkaBinding + "bindingVersion", equalTo("0.1.0"));
    }
}
