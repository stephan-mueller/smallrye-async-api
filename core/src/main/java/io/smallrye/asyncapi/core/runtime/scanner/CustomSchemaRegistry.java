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

/**
 * A simple registry that allows users to provide a custom schema for some types.
 */
public interface CustomSchemaRegistry {

    /**
     * Registers types with a custom schema.
     *
     * @param registry Schema registry to add the custom type/schema combinations to.
     */
    void registerCustomSchemas(SchemaRegistry registry);

}
