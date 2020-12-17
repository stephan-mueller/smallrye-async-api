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

/**
 * Describes a publish or a subscribe operation.
 *
 * This provides a place to document how and why messages are sent and received. For example, an operation might describe a chat
 * application
 * use case where a user sends a text message to a group.
 *
 * Example:
 * 
 * <pre>
 * {@literal @}Operation(
 *     summary = "A todo is created",
 *     message = @Message(
 *         description = "A longer description of the message",
 *         payload = {@literal @}Schema(
 *             type = SchemaType.OBJECT,
 *             properties = @SchemaProperty(
 *                 ref = @Reference(ref = "#/components/schemas/todo")
 *             )
 *         )
 *     )
 *   )
 * </pre>
 */
package io.smallrye.asyncapi.spec.annotations.operation;
