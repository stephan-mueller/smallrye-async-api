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
package io.quarkus.asyncapi.runtime;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.quarkus.arc.Arc;
import io.smallrye.asyncapi.core.runtime.AsyncApiFormat;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;

/**
 * Handler that serve the AsyncAPI document in either json or yaml format
 */
public class AsyncApiHandler implements Handler<RoutingContext> {

    private volatile AsyncApiDocumentService asyncApiDocumentService;

    private static final String ALLOWED_METHODS = "GET, HEAD, OPTIONS";

    private static final String QUERY_PARAM_FORMAT = "format";

    private static final Map<String, String> RESPONSE_HEADERS = new HashMap<>();

    static {
        RESPONSE_HEADERS.put("Access-Control-Allow-Origin", "*");
        RESPONSE_HEADERS.put("Access-Control-Allow-Credentials", "true");
        RESPONSE_HEADERS.put("Access-Control-Allow-Methods", ALLOWED_METHODS);
        RESPONSE_HEADERS.put("Access-Control-Allow-Headers", "Content-Type, Authorization");
        RESPONSE_HEADERS.put("Access-Control-Max-Age", "86400");
    }

    public AsyncApiHandler() {
    }

    @Override
    public void handle(RoutingContext event) {
        if (event.request().method().equals(HttpMethod.OPTIONS)) {
            event.response().headers().setAll(RESPONSE_HEADERS);
            event.response().headers().set("Allow", ALLOWED_METHODS);
            event.next();
        } else {
            HttpServerRequest req = event.request();
            HttpServerResponse resp = event.response();
            String accept = req.headers().get("Accept");

            List<String> formatParams = event.queryParam(QUERY_PARAM_FORMAT);
            String formatParam = formatParams.isEmpty() ? null : formatParams.get(0);

            // Default content type is YAML
            AsyncApiFormat format = AsyncApiFormat.YAML;

            // Check Accept, then query parameter "format" for JSON; else use YAML.
            if ((accept != null && accept.contains(AsyncApiFormat.JSON.getMimeType())) ||
                    ("JSON".equalsIgnoreCase(formatParam))) {
                format = AsyncApiFormat.JSON;
            }

            resp.headers().setAll(RESPONSE_HEADERS);
            resp.headers().set("Content-Type", format.getMimeType() + ";charset=UTF-8");
            byte[] schemaDocument = getAsyncApiDocumentService().getDocument(format);
            resp.end(Buffer.buffer(schemaDocument));
        }
    }

    private AsyncApiDocumentService getAsyncApiDocumentService() {
        if (this.asyncApiDocumentService == null) {
            this.asyncApiDocumentService = Arc.container().instance(AsyncApiDocumentService.class).get();
        }
        return this.asyncApiDocumentService;
    }
}
