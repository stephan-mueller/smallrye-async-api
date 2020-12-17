/*
 * Copyright 2019 Red Hat, Inc, and individual contributors.
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
package io.smallrye.asyncapi.core.api;

import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.Converter;

import io.smallrye.asyncapi.core.api.constants.AsyncApiConstants;
import io.smallrye.asyncapi.spec.AASConfig;

public class AsyncApiConfigImpl implements AsyncApiConfig {

    private Config config;

    private String modelReader;

    private String filter;

    private Boolean scanDisable;

    private Pattern scanPackages;

    private Pattern scanClasses;

    private Pattern scanExcludePackages;

    private Pattern scanExcludeClasses;

    private Set<String> servers;

    private Boolean scanDependenciesDisable;

    private Set<String> scanDependenciesJars;

    private String customSchemaRegistryClass;

    private Boolean applicationPathDisable;

    private Map<String, String> schemas;

    private String version;

    private String infoTitle;

    private String infoVersion;

    private String infoDescription;

    private String infoTermsOfService;

    private String infoContactEmail;

    private String infoContactName;

    private String infoContactUrl;

    private String infoLicenseName;

    private String infoLicenseUrl;

    private OperationIdStrategy operationIdStrategy;

    public static AsyncApiConfig fromConfig(Config config) {
        return new AsyncApiConfigImpl(config);
    }

    /**
     * Constructor.
     *
     * @param config MicroProfile Config instance
     */
    public AsyncApiConfigImpl(Config config) {
        this.config = config;
    }

    /**
     * @return the MP config instance
     */
    protected Config getConfig() {
        // We cannot use ConfigProvider.getConfig() as the archive is not deployed yet - TCCL cannot be set
        return config;
    }

    /**
     * @see AsyncApiConfig#modelReader()
     */
    @Override
    public String modelReader() {
        if (modelReader == null) {
            modelReader = getStringConfigValue(AASConfig.MODEL_READER);
        }
        return modelReader;
    }

    /**
     * @see AsyncApiConfig#filter()
     */
    @Override
    public String filter() {
        if (filter == null) {
            filter = getStringConfigValue(AASConfig.FILTER);
        }
        return filter;
    }

    /**
     * @see AsyncApiConfig#scanDisable()
     */
    @Override
    public boolean scanDisable() {
        if (scanDisable == null) {
            scanDisable = getConfig().getOptionalValue(AASConfig.SCAN_DISABLE, Boolean.class)
                    .orElse(false);
        }
        return scanDisable;
    }

    /**
     * @see AsyncApiConfig#scanPackages()
     */
    @Override
    public Pattern scanPackages() {
        if (scanPackages == null) {
            scanPackages = patternOf(getStringConfigValue(AASConfig.SCAN_PACKAGES));
        }
        return scanPackages;
    }

    /**
     * @see AsyncApiConfig#scanClasses()
     */
    @Override
    public Pattern scanClasses() {
        if (scanClasses == null) {
            scanClasses = patternOf(getStringConfigValue(AASConfig.SCAN_CLASSES));
        }
        return scanClasses;
    }

    /**
     * @see AsyncApiConfig#scanExcludePackages()
     */
    @Override
    public Pattern scanExcludePackages() {
        if (scanExcludePackages == null) {
            scanExcludePackages = patternOf(getStringConfigValue(AASConfig.SCAN_EXCLUDE_PACKAGES),
                    AsyncApiConstants.NEVER_SCAN_PACKAGES);
        }
        return scanExcludePackages;
    }

    /**
     * @see AsyncApiConfig#scanExcludeClasses()
     */
    @Override
    public Pattern scanExcludeClasses() {
        if (scanExcludeClasses == null) {
            scanExcludeClasses = patternOf(getStringConfigValue(AASConfig.SCAN_EXCLUDE_CLASSES),
                    AsyncApiConstants.NEVER_SCAN_CLASSES);
        }
        return scanExcludeClasses;
    }

    /**
     * @see AsyncApiConfig#servers()
     */
    @Override
    public Set<String> servers() {
        if (servers == null) {
            String theServers = getStringConfigValue(AASConfig.SERVERS);
            servers = asCsvSet(theServers);
        }
        return servers;
    }

    /**
     * @see AsyncApiConfig#pathServers(String)
     */
    @Override
    public Set<String> pathServers(String path) {
        String pathServers = getStringConfigValue(AASConfig.SERVERS_PATH_PREFIX + path);
        return asCsvSet(pathServers);
    }

    /**
     * @see AsyncApiConfig#operationServers(String)
     */
    @Override
    public Set<String> operationServers(String operationId) {
        String opServers = getStringConfigValue(AASConfig.SERVERS_OPERATION_PREFIX + operationId);
        return asCsvSet(opServers);
    }

    /**
     * @see AsyncApiConfig#scanDependenciesDisable()
     */
    @Override
    public boolean scanDependenciesDisable() {
        if (scanDependenciesDisable == null) {
            scanDependenciesDisable = getConfig()
                    .getOptionalValue(AsyncApiConstants.OPENKNOWLEDGE_SCAN_DEPENDENCIES_DISABLE, Boolean.class)
                    .orElse(getConfig().getOptionalValue(AsyncApiConstants.SCAN_DEPENDENCIES_DISABLE, Boolean.class)
                            .orElse(false));
        }
        return scanDependenciesDisable;
    }

    /**
     * @see AsyncApiConfig#scanDependenciesJars()
     */
    @Override
    public Set<String> scanDependenciesJars() {
        if (scanDependenciesJars == null) {
            String classes = getStringConfigValue(AsyncApiConstants.OPENKNOWLEDGE_SCAN_DEPENDENCIES_JARS);
            if (classes == null) {
                classes = getStringConfigValue(AsyncApiConstants.SCAN_DEPENDENCIES_JARS);
            }
            scanDependenciesJars = asCsvSet(classes);
        }
        return scanDependenciesJars;
    }

    @Override
    public String customSchemaRegistryClass() {
        if (customSchemaRegistryClass == null) {
            customSchemaRegistryClass = getStringConfigValue(AsyncApiConstants.OPENKNOWLEDGE_CUSTOM_SCHEMA_REGISTRY_CLASS);
            if (customSchemaRegistryClass == null) {
                customSchemaRegistryClass = getStringConfigValue(AsyncApiConstants.CUSTOM_SCHEMA_REGISTRY_CLASS);
            }
        }
        return customSchemaRegistryClass;
    }

    @Override
    public boolean applicationPathDisable() {
        if (applicationPathDisable == null) {
            applicationPathDisable = getConfig()
                    .getOptionalValue(AsyncApiConstants.OPENKNOWLEDGE_APP_PATH_DISABLE, Boolean.class)
                    .orElse(getConfig().getOptionalValue(AsyncApiConstants.APP_PATH_DISABLE, Boolean.class)
                            .orElse(false));
        }
        return applicationPathDisable;
    }

    @Override
    public Map<String, String> getSchemas() {
        if (schemas == null) {
            schemas = StreamSupport.stream(config.getPropertyNames()
                    .spliterator(), false)
                    .filter(name -> name.startsWith("mp.async.schema.") || name.startsWith("MP_ASYNCAPI_SCHEMA_"))
                    .collect(Collectors.toMap(name -> name.substring("mp.async.schema.".length()),
                            name -> config.getValue(name, String.class)));
        }
        return schemas;
    }

    @Override
    public String getAsyncApiVersion() {
        if (version == null) {
            version = getStringConfigValue(AsyncApiConstants.VERSION);
        }
        return version;
    }

    @Override
    public String getInfoTitle() {
        if (infoTitle == null) {
            infoTitle = getStringConfigValue(AsyncApiConstants.INFO_TITLE);
        }
        return infoTitle;
    }

    @Override
    public String getInfoVersion() {
        if (infoVersion == null) {
            infoVersion = getStringConfigValue(AsyncApiConstants.INFO_VERSION);
        }
        return infoVersion;
    }

    @Override
    public String getInfoDescription() {
        if (infoDescription == null) {
            infoDescription = getStringConfigValue(AsyncApiConstants.INFO_DESCRIPTION);
        }
        return infoDescription;
    }

    @Override
    public String getInfoTermsOfService() {
        if (infoTermsOfService == null) {
            infoTermsOfService = getStringConfigValue(AsyncApiConstants.INFO_TERMS);
        }
        return infoTermsOfService;
    }

    @Override
    public String getInfoContactEmail() {
        if (infoContactEmail == null) {
            infoContactEmail = getStringConfigValue(AsyncApiConstants.INFO_CONTACT_EMAIL);
        }
        return infoContactEmail;
    }

    @Override
    public String getInfoContactName() {
        if (infoContactName == null) {
            infoContactName = getStringConfigValue(AsyncApiConstants.INFO_CONTACT_NAME);
        }
        return infoContactName;
    }

    @Override
    public String getInfoContactUrl() {
        if (infoContactUrl == null) {
            infoContactUrl = getStringConfigValue(AsyncApiConstants.INFO_CONTACT_URL);
        }
        return infoContactUrl;
    }

    @Override
    public String getInfoLicenseName() {
        if (infoLicenseName == null) {
            infoLicenseName = getStringConfigValue(AsyncApiConstants.INFO_LICENSE_NAME);
        }
        return infoLicenseName;
    }

    @Override
    public String getInfoLicenseUrl() {
        if (infoLicenseUrl == null) {
            infoLicenseUrl = getStringConfigValue(AsyncApiConstants.INFO_LICENSE_URL);
        }
        return infoLicenseUrl;
    }

    @Override
    public OperationIdStrategy getOperationIdStrategy() {
        if (operationIdStrategy == null) {
            String strategy = getStringConfigValue(AsyncApiConstants.OPERATION_ID_STRAGEGY);
            if (strategy != null) {
                return OperationIdStrategy.valueOf(strategy);
            }
        }
        return null;
    }

    /**
     * getConfig().getOptionalValue(key) can return "" if optional {@link Converter}s are used. Enforce a null value if
     * we get an empty string back.
     */
    String getStringConfigValue(String key) {
        return getConfig().getOptionalValue(key, String.class)
                .map(v -> "".equals(v.trim()) ? null : v)
                .orElse(null);
    }

    @Override
    public String toString() {
        return "AsyncApiConfigImpl{" + "config=" + config + ", modelReader='" + modelReader + '\'' + ", filter='" + filter
                + '\'' + ", scanDisable="
                + scanDisable + ", scanPackages=" + scanPackages + ", scanClasses=" + scanClasses + ", scanExcludePackages="
                + scanExcludePackages
                + ", scanExcludeClasses=" + scanExcludeClasses + ", servers=" + servers + ", scanDependenciesDisable="
                + scanDependenciesDisable
                + ", scanDependenciesJars=" + scanDependenciesJars + ", customSchemaRegistryClass='" + customSchemaRegistryClass
                + '\''
                + ", applicationPathDisable=" + applicationPathDisable + ", schemas=" + schemas + ", version='" + version + '\''
                + ", infoTitle='" + infoTitle
                + '\'' + ", infoVersion='" + infoVersion + '\'' + ", infoDescription='" + infoDescription + '\''
                + ", infoTermsOfService='"
                + infoTermsOfService + '\'' + ", infoContactEmail='" + infoContactEmail + '\'' + ", infoContactName='"
                + infoContactName + '\''
                + ", infoContactUrl='" + infoContactUrl + '\'' + ", infoLicenseName='" + infoLicenseName + '\''
                + ", infoLicenseUrl='" + infoLicenseUrl + '\''
                + ", operationIdStrategy=" + operationIdStrategy + '}';
    }
}
