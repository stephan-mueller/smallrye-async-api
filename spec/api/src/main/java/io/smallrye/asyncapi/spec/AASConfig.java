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
package io.smallrye.asyncapi.spec;

/**
 * Configurable properties in AsyncAPI
 */
public final class AASConfig {

    /**
     * Configuration property to specify the fully qualified name of the AASModelReader implementation.
     *
     * @see AASModelReader
     */
    public static final String MODEL_READER = "mp.asyncapi.model.reader";
    /**
     * Configuration property to specify the fully qualified name of the AASFilter implementation.
     *
     * @see AASFilter
     */
    public static final String FILTER = "mp.asyncapi.filter";
    /**
     * Configuration property to disable annotation scanning.
     */
    public static final String SCAN_DISABLE = "mp.asyncapi.scan.disable";
    /**
     * Configuration property to specify the list of packages to scan.
     */
    public static final String SCAN_PACKAGES = "mp.asyncapi.scan.packages";
    /**
     * Configuration property to specify the list of classes to scan.
     */
    public static final String SCAN_CLASSES = "mp.asyncapi.scan.classes";
    /**
     * Configuration property to specify the list of packages to exclude from scans.
     */
    public static final String SCAN_EXCLUDE_PACKAGES = "mp.asyncapi.scan.exclude.packages";
    /**
     * Configuration property to specify the list of classes to exclude from scans.
     */
    public static final String SCAN_EXCLUDE_CLASSES = "mp.asyncapi.scan.exclude.classes";
    /**
     * Configuration property to specify the list of global servers that provide connectivity information.
     */
    public static final String SERVERS = "mp.asyncapi.servers";
    /**
     * Prefix of the configuration property to specify an alternative list of servers to service all operations in a path.
     */
    public static final String SERVERS_PATH_PREFIX = "mp.asyncapi.servers.path.";
    /**
     * Prefix of the configuration property to specify an alternative list of servers to service an operation.
     */
    public static final String SERVERS_OPERATION_PREFIX = "mp.asyncapi.servers.operation.";
    /**
     * Prefix of the configuration property to specify a schema for a specific class, in JSON format.
     */
    public static final String SCHEMA_PREFIX = "mp.asyncapi.schema.";
    /**
     * Recommended prefix for vendor specific configuration properties.
     */
    public static final String EXTENSIONS_PREFIX = "mp.asyncapi.extensions.";

    private AASConfig() {
    }
}
