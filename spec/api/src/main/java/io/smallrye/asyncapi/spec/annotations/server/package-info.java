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
 * Annotations to represent servers used for a single API operation or for all operations in an AsyncAPI document, as well as a
 * way to
 * represent variables for server URL template substitution.
 *
 * Example:
 * 
 * <pre>
 * {@literal @}Servers(value = {
 *    {@literal @}Server(
 *        url = "localhost:9080/{deployment}/todo/{id}",
 *        description = "View Todo by id",
 *        variables = {
 *            {@literal @}ServerVariables(
 *                name = "deployment",
 *                variable = @ServerVariable(
 *                    defaultValue = "todo-service",
 *                    description = "Base path of the deployment")
 *            ),
 *            {@literal @}ServerVariables(
 *                name = "id",
 *                variable = @ServerVariable(
 *                    defaultValue = "1",
 *                    description = "id of a todo")
 *            )
 *        },
 *        protocol = "kafka"
 *    )
 * })
 * </pre>
 */
package io.smallrye.asyncapi.spec.annotations.server;
