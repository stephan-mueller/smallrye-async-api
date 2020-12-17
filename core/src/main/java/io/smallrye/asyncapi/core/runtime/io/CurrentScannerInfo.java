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
package io.smallrye.asyncapi.core.runtime.io;

import io.smallrye.asyncapi.core.runtime.scanner.spi.AnnotationScanner;

public class CurrentScannerInfo {
    private static final ThreadLocal<CurrentScannerInfo> current = new ThreadLocal<>();

    public static void register(AnnotationScanner annotationScanner) {
        CurrentScannerInfo registry = new CurrentScannerInfo(annotationScanner);
        current.set(registry);
    }

    public static AnnotationScanner getCurrentAnnotationScanner() {
        return current.get().annotationScanner;
    }

    public static void setCurrentConsumes(final String[] currentConsumes) {
        current.get().currentConsumes = currentConsumes;
    }

    public static String[] getCurrentConsumes() {
        return current.get().currentConsumes;
    }

    public static void setCurrentProduces(final String[] currentProduces) {
        current.get().currentProduces = currentProduces;
    }

    public static String[] getCurrentProduces() {
        return current.get().currentProduces;
    }

    public static void remove() {
        current.remove();
    }

    private String[] currentConsumes;

    private String[] currentProduces;

    private final AnnotationScanner annotationScanner;

    private CurrentScannerInfo(final AnnotationScanner annotationScanner) {
        this.annotationScanner = annotationScanner;
        this.currentConsumes = null;
        this.currentProduces = null;
    }
}
