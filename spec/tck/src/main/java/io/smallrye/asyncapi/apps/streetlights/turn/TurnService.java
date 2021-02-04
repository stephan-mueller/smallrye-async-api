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

package io.smallrye.asyncapi.apps.streetlights.turn;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Outgoing;

import io.smallrye.asyncapi.spec.annotations.binding.ChannelBindings;
import io.smallrye.asyncapi.spec.annotations.binding.amqp.AMQPChannelBinding;
import io.smallrye.asyncapi.spec.annotations.binding.amqp.Exchange;
import io.smallrye.asyncapi.spec.annotations.binding.amqp.Queue;
import io.smallrye.asyncapi.spec.annotations.channel.ChannelItem;
import io.smallrye.asyncapi.spec.annotations.message.CorrelationID;
import io.smallrye.asyncapi.spec.annotations.message.Message;
import io.smallrye.asyncapi.spec.annotations.message.MessageTrait;
import io.smallrye.asyncapi.spec.annotations.operation.Operation;
import io.smallrye.asyncapi.spec.annotations.operation.OperationTrait;
import io.smallrye.asyncapi.spec.annotations.parameter.Parameter;
import io.smallrye.asyncapi.spec.annotations.parameter.Parameters;
import io.smallrye.asyncapi.spec.annotations.schema.Schema;
import io.smallrye.asyncapi.spec.annotations.schema.SchemaProperty;
import io.smallrye.asyncapi.spec.annotations.schema.SchemaType;
import io.smallrye.mutiny.Multi;
import io.smallrye.reactive.messaging.mqtt.MqttMessage;

@ApplicationScoped
public class TurnService {

    @ChannelItem(channel = "smartylighting/streetlights/1/0/action/{streetlightId}/turn/on", parameters = @Parameters(value = {
            @Parameter(ref = "#/components/parameters/streetlightId")
    }), bindings = @ChannelBindings(amqpBinding = @AMQPChannelBinding(exchange = @Exchange(), queue = @Queue())), publish = @Operation(operationId = "turnOn", traits = {
            @OperationTrait(ref = "#/components/operationTraits/kafka")
    }, message = @Message(name = "turnOnOff", title = "TurnOnOff on/off", summary = "Command a particular streetlight to turn the lights on or off.", correlationID = @CorrelationID(description = "Default Correlation ID", location = "$message.header#/correlationId"), traits = {
            @MessageTrait(name = "commonHeaders", description = "Common Headers", contentType = "application/json", headers = @Schema(type = SchemaType.OBJECT, properties = @SchemaProperty(name = "my-app-header",type = SchemaType.INTEGER, minimum = "0", maximum = "100")), example = {
                    "{'minimum': 0, 'maximum': 100}", "{'minimum': 10, 'maximum': 50}" })
    }, payload = @Schema(ref = "#/components/schemas/turnOnOffPayload"))))
    @Parameter(name = "streetlightId", description = "The ID of the streetlight.", schema = @Schema(type = SchemaType.STRING))
    @Outgoing("turnOn")
    public Multi<MqttMessage<TurnOnOff>> turnOn() {
        return Multi
                .createFrom()
                .ticks()
                .every(Duration.ofMinutes(1))
                .map(x -> generateMessage(Command.ON));
    }

    @ChannelItem(channel = "smartylighting/streetlights/1/0/action/{streetlightId}/turn/off", parameters = @Parameters(value = {
            @Parameter(name = "streetlightId", ref = "#/components/parameters/streetlightId")
    }), publish = @Operation(operationId = "turnOff", traits = {
            @OperationTrait(ref = "#/components/operationTraits/kafka")
    }, message = @Message(ref = "#/components/messages/turnOnOff")))
    @Outgoing("turnOff")
    public Multi<MqttMessage<TurnOnOff>> turnOff() {
        return Multi
                .createFrom()
                .ticks()
                .every(Duration.ofMinutes(1))
                .map(x -> generateMessage(Command.OFF));
    }

    private static MqttMessage<TurnOnOff> generateMessage(final Command command) {
        TurnOnOff turnOnOff = new TurnOnOff(command, LocalDateTime.now());

        String topic = String.format("smartylighting/streetlights/1/0/action/%d/turnOnOff/%s", new Random().nextInt(1000),
                command.toString());

        System.out.println(String.format("Send message: %s, to topic: %s", turnOnOff.toString(), topic));

        return MqttMessage.of(topic, turnOnOff);
    }

}
