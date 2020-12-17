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
 * This is the root document object for the API specification. It combines resource listing and API declaration together into
 * one document.
 *
 * Example usage:
 * 
 * <pre>
 * {@literal @}AsyncAPI(
 *      io.smallrye.asyncapi.spec = "2.0.0",
 *        info = {@literal @}Info(
 *            title = "Example app",
 *            version = "1.0.0-SNAPSHOT",
 *            contact = {@literal @}Contact(
 *              name = "John Doe",
 *              email = "johndoe@example.com",
 *              url = "https://example.com"
 *            ),
 *            description = "Example project demonstrating the AsyncAPI Annotations",
 *            license = {@literal @}License(
 *              name = "Apache 2.0",
 *              url = "https://www.apache.org/licenses/LICENSE-2.0.html"
 *            )
 *        )
 *      )
 * </pre>
 */
package io.smallrye.asyncapi.spec.annotations;
