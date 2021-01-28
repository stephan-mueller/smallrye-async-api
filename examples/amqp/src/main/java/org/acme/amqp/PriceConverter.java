package org.acme.amqp;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Acknowledgment;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import io.smallrye.asyncapi.spec.annotations.channel.ChannelItem;
import io.smallrye.asyncapi.spec.annotations.message.Message;
import io.smallrye.asyncapi.spec.annotations.operation.Operation;
import io.smallrye.asyncapi.spec.annotations.schema.Schema;
import io.smallrye.asyncapi.spec.annotations.schema.SchemaType;
import io.smallrye.reactive.messaging.annotations.Broadcast;

/**
 * A bean consuming data from the "prices" AMQP queue and applying some conversion.
 * The result is pushed to the "my-data-stream" stream which is an in-memory stream.
 */
@ApplicationScoped
public class PriceConverter {

    private static final double CONVERSION_RATE = 0.88;

    @ChannelItem(
        channel = "prices",
        subscribe = @Operation(
            operationId = "prices",
            message = @Message(
                contentType = "text/plain",
                summary = "A random price",
                payload = @Schema(
                    type = SchemaType.NUMBER,
                    name = "price",
                    minimum = "0",
                    maximum = "100"
                ),
                example = {"42.24", "17.6", "87.12"}
            )
        )
    )
    @Incoming("prices")
    @Outgoing("my-data-stream")
    @Broadcast
    @Acknowledgment(Acknowledgment.Strategy.PRE_PROCESSING)
    public double process(int priceInUsd) {
        return priceInUsd * CONVERSION_RATE;
    }
}
