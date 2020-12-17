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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import io.smallrye.asyncapi.core.api.constants.AsyncApiConstants;

public interface AsyncApiConfig {

    default String modelReader() {
        return null;
    }

    default String filter() {
        return null;
    }

    default boolean scanDisable() {
        return false;
    }

    default Pattern scanPackages() {
        return null;
    }

    default Pattern scanClasses() {
        return null;
    }

    default Pattern scanExcludePackages() {
        return Pattern.compile("(" + AsyncApiConstants.NEVER_SCAN_PACKAGES.stream()
                .map(Pattern::quote)
                .collect(Collectors.joining("|")) + ")");
    }

    default Pattern scanExcludeClasses() {
        return Pattern.compile("(" + AsyncApiConstants.NEVER_SCAN_CLASSES.stream()
                .map(Pattern::quote)
                .collect(Collectors.joining("|")) + ")");
    }

    default Set<String> servers() {
        return new HashSet<>();
    }

    default Set<String> pathServers(String path) {
        return new HashSet<>();
    }

    default Set<String> operationServers(String operationId) {
        return new HashSet<>();
    }

    default boolean scanDependenciesDisable() {
        return false;
    }

    default Set<String> scanDependenciesJars() {
        return new HashSet<>();
    }

    default String customSchemaRegistryClass() {
        return null;
    }

    default boolean applicationPathDisable() {
        return false;
    }

    default Map<String, String> getSchemas() {
        return new HashMap<>();
    }

    default String getAsyncApiVersion() {
        return null;
    }

    default String getInfoTitle() {
        return null;
    }

    default String getInfoVersion() {
        return null;
    }

    default String getInfoDescription() {
        return null;
    }

    default String getInfoTermsOfService() {
        return null;
    }

    default String getInfoContactEmail() {
        return null;
    }

    default String getInfoContactName() {
        return null;
    }

    default String getInfoContactUrl() {
        return null;
    }

    default String getInfoLicenseName() {
        return null;
    }

    default String getInfoLicenseUrl() {
        return null;
    }

    default OperationIdStrategy getOperationIdStrategy() {
        return null;
    }

    enum OperationIdStrategy {
        METHOD,
        CLASS_METHOD,
        PACKAGE_CLASS_METHOD
    }

    default Pattern patternOf(String configValue) {
        return patternOf(configValue, null);
    }

    default Pattern patternOf(String configValue, Set<String> buildIn) {
        Pattern pattern = null;

        if (configValue != null && (configValue.startsWith("^") || configValue.endsWith("$"))) {
            pattern = Pattern.compile(configValue);
        } else {
            Set<String> literals = asCsvSet(configValue);
            if (buildIn != null && !buildIn.isEmpty()) {
                literals.addAll(buildIn);
            }
            if (literals.isEmpty()) {
                return Pattern.compile("", Pattern.LITERAL);
            } else {
                pattern = Pattern.compile("(" + literals.stream()
                        .map(Pattern::quote)
                        .collect(Collectors.joining("|")) + ")");
            }
        }

        return pattern;
    }

    default Set<String> asCsvSet(String items) {
        Set<String> rval = new HashSet<>();
        if (items != null) {
            String[] split = items.split(",");
            for (String item : split) {
                rval.add(item.trim());
            }
        }
        return rval;
    }

}
