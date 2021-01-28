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

package io.smallrye.asyncapi.apps.streetlights.measure;

import java.util.concurrent.CompletionStage;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import io.smallrye.asyncapi.spec.annotations.channel.ChannelItem;
import io.smallrye.asyncapi.spec.annotations.message.Message;
import io.smallrye.asyncapi.spec.annotations.message.MessageTrait;
import io.smallrye.asyncapi.spec.annotations.operation.Operation;
import io.smallrye.asyncapi.spec.annotations.operation.OperationTrait;
import io.smallrye.asyncapi.spec.annotations.parameter.Parameter;
import io.smallrye.asyncapi.spec.annotations.parameter.Parameters;
import io.smallrye.asyncapi.spec.annotations.schema.Schema;
import io.smallrye.asyncapi.spec.annotations.tag.Tag;
import io.smallrye.reactive.messaging.mqtt.MqttMessage;

@ApplicationScoped
public class MeasuredService {

    @ChannelItem(channel = "smartylighting/streetlights/1/0/event/{streetlightId}/lighting/measure", description = "The topic on which measure values may be produced and consumed.", parameters = @Parameters(value = {
            @Parameter(name = "streetlightId", ref = "#/components/parameters/streetlightId") }), subscribe = @Operation(summary = "Receive information about environmental lighting conditions of a particular streetlight.", operationId = "receiveLightMeasurement", traits = {
                    @OperationTrait(ref = "#/components/operationTraits/kafka") }, message = @Message(ref = "#/components/messages/lightMeasured")))
    @Message(name = "lightMeasured", title = "Light measured", summary = "Inform about environmental lighting conditions for a particular streetlight.", contentType = "application/json", traits = {
            @MessageTrait(ref = "#/components/messageTraits/commonHeaders") }, payload = @Schema(ref = "#/components/schemas/lightMeasuredPayload"), tags = {
                    @Tag(name = "streetlights"), @Tag(name = "measure") })
    @Incoming("measure")
    public CompletionStage<Void> consume(final org.eclipse.microprofile.reactive.messaging.Message<byte[]> message) {
        MqttMessage mqttMessage = message.unwrap(MqttMessage.class);

        System.out.println(
                String.format("Send message: %s, to topic: %s", new String(message.getPayload()), mqttMessage.getTopic()));

        return message.ack();
    }
}
