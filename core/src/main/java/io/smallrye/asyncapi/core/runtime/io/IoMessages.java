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
package io.smallrye.asyncapi.core.runtime.io;

import java.io.IOException;

import org.jboss.logging.Messages;
import org.jboss.logging.annotations.Message;
import org.jboss.logging.annotations.MessageBundle;

@MessageBundle(projectCode = "OKAAP", length = 5)
interface IoMessages {
    IoMessages msg = Messages.getBundle(IoMessages.class);

    @Message(id = 3000, value = "No file name for URL: %s")
    IOException noFileName(String url);

    @Message(id = 3001, value = "Invalid file name for URL: %s")
    IOException invalidFileName(String url);

    @Message(id = 3002, value = "Invalid file extension for URL (expected json, yaml, or yml): %s")
    IOException invalidFileExtension(String url);
}
