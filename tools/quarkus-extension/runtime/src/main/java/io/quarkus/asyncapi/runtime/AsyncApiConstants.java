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
package io.quarkus.asyncapi.runtime;

public class AsyncApiConstants {

    public static volatile ClassLoader classLoader;

    public static final String GENERATED_DOC_BASE = "quarkus-generated-asyncapi-doc.";

    public static final String BASE_NAME = "META-INF/" + GENERATED_DOC_BASE;

    public static final String META_INF_ASYNCAPI_YAML = "META-INF/asyncapi.yaml";

    public static final String WEB_INF_CLASSES_META_INF_ASYNCAPI_YAML = "WEB-INF/classes/META-INF/asyncapi.yaml";

    public static final String META_INF_ASYNCAPI_YML = "META-INF/asyncapi.yml";

    public static final String WEB_INF_CLASSES_META_INF_ASYNCAPI_YML = "WEB-INF/classes/META-INF/asyncapi.yml";

    public static final String META_INF_ASYNCAPI_JSON = "META-INF/asyncapi.json";

    public static final String WEB_INF_CLASSES_META_INF_ASYNCAPI_JSON = "WEB-INF/classes/META-INF/asyncapi.json";

    private AsyncApiConstants() {
    }

}
