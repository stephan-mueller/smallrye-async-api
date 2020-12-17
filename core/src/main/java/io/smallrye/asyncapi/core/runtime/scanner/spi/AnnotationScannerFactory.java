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
package io.smallrye.asyncapi.core.runtime.scanner.spi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * Factory that allows plugging in more scanners.
 */
public class AnnotationScannerFactory {
    private final Map<String, AnnotationScanner> loadedScanners = new HashMap<>();

    public AnnotationScannerFactory(ClassLoader cl) {
        ServiceLoader<AnnotationScanner> loader = ServiceLoader.load(AnnotationScanner.class, cl);
        Iterator<AnnotationScanner> scannerIterator = loader.iterator();
        while (scannerIterator.hasNext()) {
            AnnotationScanner scanner = scannerIterator.next();
            loadedScanners.put(scanner.getName(), scanner);
        }
    }

    public List<AnnotationScanner> getAnnotationScanners() {
        return new ArrayList<>(loadedScanners.values());
    }

    @Override
    public String toString() {
        return "AnnotationScannerFactory{" + "loadedScanners=" + loadedScanners + '}';
    }
}
