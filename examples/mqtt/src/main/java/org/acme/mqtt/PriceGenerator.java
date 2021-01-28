package org.acme.mqtt;

import org.eclipse.microprofile.reactive.messaging.Outgoing;

import java.time.Duration;
import java.util.Random;

import javax.enterprise.context.ApplicationScoped;

import io.smallrye.asyncapi.spec.annotations.channel.ChannelItem;
import io.smallrye.asyncapi.spec.annotations.message.Message;
import io.smallrye.asyncapi.spec.annotations.operation.Operation;
import io.smallrye.asyncapi.spec.annotations.schema.Schema;
import io.smallrye.asyncapi.spec.annotations.schema.SchemaType;
import io.smallrye.mutiny.Multi;

/**
 * A bean producing random prices every 5 seconds.
 * The prices are written to a MQTT topic (prices). The MQTT configuration is specified in the application configuration.
 */
@ApplicationScoped
public class PriceGenerator {

    private Random random = new Random();

    @ChannelItem(
        channel = "prices",
        publish = @Operation(
            operationId = "generated-price",
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
    @Outgoing("generated-price")
    public Multi<Integer> generate() {
        return Multi.createFrom().ticks().every(Duration.ofSeconds(5))
                .onOverflow().drop()
                .map(tick -> random.nextInt(100));
    }
}
