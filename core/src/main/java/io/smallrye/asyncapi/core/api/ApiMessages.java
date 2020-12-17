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
package io.smallrye.asyncapi.core.api;

import org.jboss.logging.Messages;
import org.jboss.logging.annotations.Message;
import org.jboss.logging.annotations.MessageBundle;

@MessageBundle(projectCode = "SMAAP", length = 5)
public interface ApiMessages {

    ApiMessages msg = Messages.getBundle(ApiMessages.class);

    @Message(id = 0, value = "Model not initialized yet")
    IllegalStateException modelNotInitialized();

    @Message(id = 1, value = "Model already initialized")
    IllegalStateException modelAlreadyInitialized();

    @Message(id = 2, value = "OpenApiConfig must be set before init")
    IllegalStateException configMustBeSet();
}
