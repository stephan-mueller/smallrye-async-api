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

public class AMQPServiceTest extends AppTestBase {

    @Deployment(name = "amqp")
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "amqp.war")
                .addPackages(true, "io.smallrye.asyncapi.bindings.amqp");
    }

    @RunAsClient
    @Test(dataProvider = "formatProvider")
    public void testVersion(String type) {
        ValidatableResponse vr = callEndpoint(type);
        vr.body("asyncapi", equalTo("2.0.0"));
    }

    @RunAsClient
    @Test(dataProvider = "formatProvider")
    public void testChannelBinding(String type) {
        ValidatableResponse vr = callEndpoint(type);
        String channelAMQPPath = "channels.amqp-test1.bindings.amqpBinding.";
        vr.body(channelAMQPPath + "is", equalTo("queue"));
        vr.body(channelAMQPPath + "queue.name", equalTo("amqp-test"));
        vr.body(channelAMQPPath + "queue.durable", equalTo(false));
        vr.body(channelAMQPPath + "queue.exclusive", equalTo(false));
        vr.body(channelAMQPPath + "queue.autoDelete", equalTo(false));

        channelAMQPPath = "channels.amqp-test2.bindings.amqpBinding.";
        vr.body(channelAMQPPath + "is", equalTo("routingKey"));
        vr.body(channelAMQPPath + "exchange.name", equalTo("amqp-test"));
        vr.body(channelAMQPPath + "exchange.durable", equalTo(false));
        vr.body(channelAMQPPath + "exchange.autoDelete", equalTo(false));
    }

    @RunAsClient
    @Test(dataProvider = "formatProvider")
    public void testMessageBinding(String type) {
        ValidatableResponse vr = callEndpoint(type);
        String messageAMQPPath = "channels.amqp-test2.publish.message.bindings.amqpBinding.";

        vr.body(messageAMQPPath + "contentEncoding", equalTo("gzip"));
        vr.body(messageAMQPPath + "messageType", equalTo("test.amqp"));
        vr.body(messageAMQPPath + "bindingVersion", equalTo("0.1.0"));
    }

    @RunAsClient
    @Test(dataProvider = "formatProvider")
    public void testOperationBinding(String type) {
        ValidatableResponse vr = callEndpoint(type);
        String operationAMQPPath = "channels.amqp-test2.publish.bindings.amqpBinding.";

        vr.body(operationAMQPPath + "cc", containsInAnyOrder("test.amqp"));
        vr.body(operationAMQPPath + "priority", equalTo(10));
        vr.body(operationAMQPPath + "deliveryMode", equalTo(2));
        vr.body(operationAMQPPath + "mandatory", equalTo(false));
        vr.body(operationAMQPPath + "bcc", containsInAnyOrder("test.amqp"));
        vr.body(operationAMQPPath + "replyTo", equalTo("test.amqp"));
        vr.body(operationAMQPPath + "timeStamp", equalTo(true));
        vr.body(operationAMQPPath + "ack", equalTo(false));
        vr.body(operationAMQPPath + "bindingVersion", equalTo("0.1.0"));
    }
}
