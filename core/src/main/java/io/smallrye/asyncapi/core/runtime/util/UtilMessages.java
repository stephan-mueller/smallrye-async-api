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
package io.smallrye.asyncapi.core.runtime.util;

import org.jboss.jandex.PrimitiveType;
import org.jboss.logging.Messages;
import org.jboss.logging.annotations.Message;
import org.jboss.logging.annotations.MessageBundle;

@MessageBundle(projectCode = "OKAAP", length = 5)
interface UtilMessages {
    UtilMessages msg = Messages.getBundle(UtilMessages.class);

    @Message(id = 8000, value = "RefType must not be null")
    NullPointerException refTypeNotNull();

    @Message(id = 8001, value = "Unknown primitive: %s")
    IllegalArgumentException unknownPrimitive(PrimitiveType primitive);
}
