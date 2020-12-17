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
package io.smallrye.asyncapi.core.runtime.util;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import io.smallrye.asyncapi.core.api.models.ComponentsImpl;
import io.smallrye.asyncapi.spec.models.AsyncAPI;
import io.smallrye.asyncapi.spec.models.Components;

/**
 * Class with some convenience methods useful for working with the AAI data model.
 */
public class ModelUtil {

    public ModelUtil() {
    }

    /**
     * Gets the {@link Components} from the OAI model. If it doesn't exist, creates it.
     *
     * @param asyncAPI AsyncAPI
     * @return Components
     */
    public static Components components(AsyncAPI asyncAPI) {
        if (asyncAPI.getComponents() == null) {
            asyncAPI.setComponents(new ComponentsImpl());
        }
        return asyncAPI.getComponents();
    }

    /**
     * Returns the name component of the ref.
     *
     * @param ref String
     * @return Name
     */
    public static String nameFromRef(String ref) {
        String[] split = ref.split("/");
        return split[split.length - 1];
    }

    public static <V> Map<String, V> unmodifiableMap(Map<String, V> map) {
        return map != null ? Collections.unmodifiableMap(map) : null;
    }

    public static <V> Map<String, V> replace(Map<String, V> modified, UnaryOperator<Map<String, V>> factory) {
        final Map<String, V> replacement;

        if (modified == null) {
            replacement = null;
        } else {
            replacement = factory.apply(modified);
        }

        return replacement;
    }

    public static <V> Map<String, V> add(String key, V value, Map<String, V> map, Supplier<Map<String, V>> factory) {
        if (value != null) {
            if (map == null) {
                map = factory.get();
            }
            map.put(key, value);
        }
        return map;
    }

    public static <V> void remove(Map<String, V> map, String key) {
        if (map != null) {
            map.remove(key);
        }
    }

    public static <V> List<V> unmodifiableList(List<V> list) {
        return list != null ? Collections.unmodifiableList(list) : null;
    }

    public static <V> List<V> replace(List<V> modified, UnaryOperator<List<V>> factory) {
        final List<V> replacement;

        if (modified == null) {
            replacement = null;
        } else {
            replacement = factory.apply(modified);
        }

        return replacement;
    }

    public static <V> List<V> add(V value, List<V> list, Supplier<List<V>> factory) {
        if (value != null) {
            if (list == null) {
                list = factory.get();
            }
            list.add(value);
        }
        return list;
    }

    public static <V> void remove(List<V> list, V value) {
        if (list != null) {
            list.remove(value);
        }
    }
}
