/*
 * Copyright 2019 Red Hat
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.smallrye.asyncapi.core.api.constants;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import io.smallrye.asyncapi.spec.AASConfig;

public final class AsyncApiConstants {

    public static final String VENDOR_NAME = "smallrye.";

    public static final String SUFFIX_SCAN_DEPENDENCIES_DISABLE = "scan-dependencies.disable";

    public static final String SUFFIX_SCAN_DEPENDENCIES_JARS = "scan-dependencies.jars";

    public static final String SUFFIX_CUSTOM_SCHEMA_REGISTRY_CLASS = "custom-schema-registry.class";

    public static final String SUFFIX_APP_PATH_DISABLE = "application-path.disable";

    public static final String SCAN_DEPENDENCIES_DISABLE = AASConfig.EXTENSIONS_PREFIX + SUFFIX_SCAN_DEPENDENCIES_DISABLE;

    public static final String SCAN_DEPENDENCIES_JARS = AASConfig.EXTENSIONS_PREFIX + SUFFIX_SCAN_DEPENDENCIES_JARS;

    public static final String CUSTOM_SCHEMA_REGISTRY_CLASS = AASConfig.EXTENSIONS_PREFIX + SUFFIX_CUSTOM_SCHEMA_REGISTRY_CLASS;

    public static final String APP_PATH_DISABLE = AASConfig.EXTENSIONS_PREFIX + SUFFIX_APP_PATH_DISABLE;

    public static final String OPENKNOWLEDGE_SCAN_DEPENDENCIES_DISABLE = AASConfig.EXTENSIONS_PREFIX + VENDOR_NAME
            + SUFFIX_SCAN_DEPENDENCIES_DISABLE;

    public static final String OPENKNOWLEDGE_SCAN_DEPENDENCIES_JARS = AASConfig.EXTENSIONS_PREFIX + VENDOR_NAME
            + SUFFIX_SCAN_DEPENDENCIES_JARS;

    public static final String OPENKNOWLEDGE_CUSTOM_SCHEMA_REGISTRY_CLASS = AASConfig.EXTENSIONS_PREFIX + VENDOR_NAME
            + SUFFIX_CUSTOM_SCHEMA_REGISTRY_CLASS;

    public static final String OPENKNOWLEDGE_APP_PATH_DISABLE = AASConfig.EXTENSIONS_PREFIX + VENDOR_NAME
            + SUFFIX_APP_PATH_DISABLE;

    public static final String VERSION = AASConfig.EXTENSIONS_PREFIX + VENDOR_NAME + "asyncapi";

    public static final String INFO_TITLE = AASConfig.EXTENSIONS_PREFIX + VENDOR_NAME + "info.title";

    public static final String INFO_VERSION = AASConfig.EXTENSIONS_PREFIX + VENDOR_NAME + "info.version";

    public static final String INFO_DESCRIPTION = AASConfig.EXTENSIONS_PREFIX + VENDOR_NAME + "info.description";

    public static final String INFO_TERMS = AASConfig.EXTENSIONS_PREFIX + VENDOR_NAME + "info.termsOfService";

    public static final String INFO_CONTACT_EMAIL = AASConfig.EXTENSIONS_PREFIX + VENDOR_NAME + "info.contact.email";

    public static final String INFO_CONTACT_NAME = AASConfig.EXTENSIONS_PREFIX + VENDOR_NAME + "info.contact.name";

    public static final String INFO_CONTACT_URL = AASConfig.EXTENSIONS_PREFIX + VENDOR_NAME + "info.contact.url";

    public static final String INFO_LICENSE_NAME = AASConfig.EXTENSIONS_PREFIX + VENDOR_NAME + "info.license.name";

    public static final String INFO_LICENSE_URL = AASConfig.EXTENSIONS_PREFIX + VENDOR_NAME + "info.license.url";

    public static final String OPERATION_ID_STRAGEGY = AASConfig.EXTENSIONS_PREFIX + VENDOR_NAME + "operationIdStrategy";

    /**
     * Set of classes which should never be scanned, regardless of user configuration.
     */
    public static final Set<String> NEVER_SCAN_CLASSES = Collections.unmodifiableSet(new HashSet<>(Arrays.asList()));

    /**
     * Set of packages which should never be scanned, regardless of user configuration.
     */
    public static final Set<String> NEVER_SCAN_PACKAGES = Collections
            .unmodifiableSet(new HashSet<>(Arrays.asList("java.lang")));

    public static final String CLASS_SUFFIX = ".class";

    public static final String JAR_SUFFIX = ".jar";

    public static final String WEB_ARCHIVE_CLASS_PREFIX = "/WEB-INF/classes/";

    public static final String ASYNC_API_VERSION = "2.0.0";

    public static final String REF_PREFIX_SCHEMA = "#/components/schemas/";

    public static final String VALUE = "value";

    public static final String REF = "ref";

    public AsyncApiConstants() {
    }
}
