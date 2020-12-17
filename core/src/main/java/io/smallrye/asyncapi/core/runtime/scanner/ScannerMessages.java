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
package io.smallrye.asyncapi.core.runtime.scanner;

import java.util.NoSuchElementException;

import org.jboss.jandex.DotName;
import org.jboss.logging.Messages;
import org.jboss.logging.annotations.Cause;
import org.jboss.logging.annotations.Message;
import org.jboss.logging.annotations.MessageBundle;

@MessageBundle(projectCode = "SROAP", length = 5)
interface ScannerMessages {
    ScannerMessages msg = Messages.getBundle(ScannerMessages.class);

    @Message(id = 5000, value = "Failed to create instance of custom schema registry: %s")
    RuntimeException failedCreateInstance(String schemaRegistry, @Cause Throwable throwable);

    @Message(id = 5001, value = "Class schema not registered: %s")
    NoSuchElementException notRegistered(DotName schema);
}
