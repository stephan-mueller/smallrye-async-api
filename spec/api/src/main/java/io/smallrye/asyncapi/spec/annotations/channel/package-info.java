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
 * Describes the operations available on channels.
 *
 * Example:
 * 
 * <pre>
 * {@literal @}Channels(
 *   value ={@literal @}ChannelItem(
 *             description = "This channel is used to exchange messages about todos",
 *             publish = @Operation(
 *                 summary = "A todo is created",
 *                 message = {@literal @}Message(
 *                     description = "A longer description of the message",
 *                     payload = {@literal @}Schema(
 *                         type = SchemaType.OBJECT,
 *                         properties = {@literal @}SchemaProperty(
 *                             ref = {@literal @}Reference(ref = "#/components/schemas/todo")
 *                         )
 *                     )
 *                 )
 *             ),
 *             parameters = {@literal @}Parameters(
 *                 value = {
 *                     {@literal @}Parameter(
 *                         description = "ID of the user",
 *                         schema = {@literal @}Schema(
 *                             type = SchemaType.STRING
 *                         ),
 *                         location = "$message.payload#/user/id"
 *                     ),
 *                     {@literal @}Parameter(
 *                         ref = {@literal @}Reference(ref = "#/components/schemas/todo")
 *                     )
 *                 }
 *             )
 *         )
 * )
 * </pre>
 */
package io.smallrye.asyncapi.spec.annotations.channel;
