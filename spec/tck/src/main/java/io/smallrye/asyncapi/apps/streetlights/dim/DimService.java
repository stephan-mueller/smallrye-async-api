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

package io.smallrye.asyncapi.apps.streetlights.dim;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Outgoing;

import io.smallrye.asyncapi.spec.annotations.channel.ChannelItem;
import io.smallrye.asyncapi.spec.annotations.message.Message;
import io.smallrye.asyncapi.spec.annotations.message.MessageTrait;
import io.smallrye.asyncapi.spec.annotations.operation.Operation;
import io.smallrye.asyncapi.spec.annotations.operation.OperationTrait;
import io.smallrye.asyncapi.spec.annotations.parameter.Parameter;
import io.smallrye.asyncapi.spec.annotations.parameter.Parameters;
import io.smallrye.asyncapi.spec.annotations.schema.Schema;
import io.smallrye.mutiny.Multi;
import io.smallrye.reactive.messaging.mqtt.MqttMessage;

@ApplicationScoped
public class DimService {

    @ChannelItem(channel = "smartylighting/streetlights/1/0/action/{streetlightId}/dim", parameters = @Parameters(value = {
            @Parameter(name = "streetlightId", ref = "#/components/parameters/streetlightId")
    }), publish = @Operation(operationId = "dimLight", traits = {
            @OperationTrait(ref = "#/components/operationTraits/kafka")
    }, message = @Message(ref = "#/components/messages/dimLight")))
    @Message(name = "dimLight", title = "Dim light", summary = "Command a particular streetlight to dim the lights.", headers = @Schema(name = "Message Header", description = "A Kafka Message Header"), traits = {
            @MessageTrait(ref = "#/components/messageTraits/commonHeaders")
    }, payload = @Schema(ref = "#/components/schemas/dimLightPayload"))
    @Outgoing("dim")
    public Multi<MqttMessage<Dim>> dim() {
        return Multi
                .createFrom()
                .ticks()
                .every(Duration.ofMinutes(1))
                .map(x -> generateMessage());
    }

    private static MqttMessage<Dim> generateMessage() {
        Random random = new Random();
        Dim dim = new Dim(random.nextInt(100), LocalDateTime.now());

        String topic = String.format("smartylighting/streetlights/1/0/action/%d/dim", random.nextInt(1000));

        System.out.println(String.format("Send message: %s, to topic: %s", dim.toString(), topic));

        return MqttMessage.of(topic, dim);
    }
}
