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

package io.smallrye.asyncapi.bindings.http;

import io.smallrye.asyncapi.spec.annotations.binding.MessageBindings;
import io.smallrye.asyncapi.spec.annotations.binding.OperationBindings;
import io.smallrye.asyncapi.spec.annotations.binding.http.HTTPMessageBinding;
import io.smallrye.asyncapi.spec.annotations.binding.http.HTTPOperationBinding;
import io.smallrye.asyncapi.spec.annotations.binding.http.Method;
import io.smallrye.asyncapi.spec.annotations.channel.ChannelItem;
import io.smallrye.asyncapi.spec.annotations.message.Message;
import io.smallrye.asyncapi.spec.annotations.operation.Operation;
import io.smallrye.asyncapi.spec.annotations.schema.Schema;
import io.smallrye.asyncapi.spec.annotations.schema.SchemaProperty;
import io.smallrye.asyncapi.spec.annotations.schema.SchemaType;

public class HTTPService {

    @ChannelItem(channel = "http-test1", subscribe = @Operation(bindings = @OperationBindings(httpBinding = @HTTPOperationBinding(type = "request", method = Method.GET, query = @Schema(type = SchemaType.OBJECT, properties = {
                    @SchemaProperty(name = "companyId", minimum = "1", description = "The Id of the company")
            }), bindingVersion = "0.1.0")))

    )
    public void httpTest1() {
    }

    @ChannelItem(channel = "http-test2", publish = @Operation(message = @Message(bindings = @MessageBindings(httpBinding = @HTTPMessageBinding(headers = @Schema(type = SchemaType.OBJECT), bindingVersion = "0.1.0")))))
    public void httpTest2() {
    }
}
