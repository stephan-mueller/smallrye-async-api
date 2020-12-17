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
 * Describes a message received on a given channel and operation.
 *
 * Example:
 * 
 * <pre>
 * {@literal @}Message(
 *     description = "A longer description of the message",
 *     payload = {@literal @}Schema(
 *         type = SchemaType.OBJECT,
 *         properties = @SchemaProperty(
 *             ref = @Reference(ref = "#/components/schemas/todo")
 *         )
 *     )
 * )
 * </pre>
 * 
 * or:
 * 
 * <pre>
 * {@literal @}Schema(
 *     title = "Todo",
 *     type = SchemaType.OBJECT,
 *     required = {"id","title"}
 *  )
 *  private class Todo{
 *
 *     private Long id;
 *     private String title;
 *     private String description;
 *
 *     ...
 *   }
 * </pre>
 */
package io.smallrye.asyncapi.spec.annotations.message;
