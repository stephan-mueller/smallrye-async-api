package org.acme.amqp;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.smallrye.asyncapi.spec.annotations.AsyncAPI;
import io.smallrye.asyncapi.spec.annotations.info.Info;
import io.smallrye.asyncapi.spec.annotations.info.License;

/**
 * A simple resource retrieving the "in-memory" "my-data-stream" and sending the items to a server sent event.
 */
@AsyncAPI(
    asyncapi = "2.0.0",
    defaultContentType = "text/plain",
    info = @Info(
        title = "AMQP Quickstart API",
        version = "1.0-SNAPSHOT",
        description = "In this guide, we are going to generate (random) prices in one component."
            + " These prices are written in a AMQP queue (prices)."
            + " A second component reads from the AMQP prices queue and apply some magic conversion to the price."
            + " The result is sent to an in-memory stream consumed by a JAX-RS resource."
            + " The data is sent to a browser using server-sent events.",
        license = @License(
            name = "Apache 2.0",
            url = "https://www.apache.org/licenses/LICENSE-2.0"
        )
    )
)
@Path("/prices")
public class PriceResource {

    @Inject
    @Channel("my-data-stream")
    Publisher<Double> prices;

    @GET
    @Path("/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Publisher<Double> stream() {
        return prices;
    }
}
