/*
 * Copyright 2019 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.quarkus.asyncapi.deployment;

import java.util.Collection;

import org.jboss.jandex.ClassInfo;

import io.smallrye.asyncapi.core.runtime.scanner.AnnotationScannerExtension;
import io.smallrye.asyncapi.core.runtime.scanner.spi.AnnotationScanner;

/**
 * This adds support for the quarkus.http.root-path config option
 */
public class CustomPathExtension implements AnnotationScannerExtension {

    private final String defaultPath;

    public CustomPathExtension(String defaultPath) {
        this.defaultPath = defaultPath;
    }

    @Override
    public void processScannerApplications(AnnotationScanner scanner, Collection<ClassInfo> applications) {
        scanner.setContextRoot(defaultPath);
    }
}
