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
package io.smallrye.asyncapi.core.runtime.io.externaldocs;

import io.smallrye.asyncapi.spec.models.ExternalDocumentation;

/**
 * An implementation of the {@link ExternalDocumentation} AsyncAPI model interface.
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0#externalDocumentationObject"
 */
public class ExternalDocsConstant {

    public static final String PROP_EXTERNAL_DOCS = "externalDocs";

    public static final String PROP_DESCRIPTION = "description";

    public static final String PROP_URL = "url";

    private ExternalDocsConstant() {
    }
}
