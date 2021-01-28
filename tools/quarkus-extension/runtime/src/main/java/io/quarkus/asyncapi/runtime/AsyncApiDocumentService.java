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

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

import io.smallrye.asyncapi.core.api.AsyncApiConfig;
import io.smallrye.asyncapi.core.api.AsyncApiConfigImpl;
import io.smallrye.asyncapi.core.api.AsyncApiDocument;
import io.smallrye.asyncapi.core.runtime.AsyncApiFormat;
import io.smallrye.asyncapi.core.runtime.AsyncApiProcessor;
import io.smallrye.asyncapi.core.runtime.AsyncApiStaticFile;
import io.smallrye.asyncapi.core.runtime.io.AsyncApiSerializer;

@ApplicationScoped
public class AsyncApiDocumentService {

    private byte[] jsonDocument;

    private byte[] yamlDocument;

    @PostConstruct
    void create() throws IOException {

        ClassLoader cl = AsyncApiConstants.classLoader == null ? Thread.currentThread().getContextClassLoader()
                : AsyncApiConstants.classLoader;

        try (InputStream is = cl.getResourceAsStream(AsyncApiConstants.BASE_NAME + AsyncApiFormat.JSON)) {
            if (is != null) {
                try (AsyncApiStaticFile staticFile = new AsyncApiStaticFile(is, AsyncApiFormat.JSON)) {
                    Config config = ConfigProvider.getConfig();
                    AsyncApiConfig openApiConfig = new AsyncApiConfigImpl(config);

                    AsyncApiDocument document = AsyncApiDocument.INSTANCE;
                    document.reset();
                    document.config(openApiConfig);
                    document.modelFromStaticFile(AsyncApiProcessor.modelFromStaticFile(staticFile));
                    document.filter(AsyncApiProcessor.getFilter(openApiConfig, cl));
                    document.initialize();

                    this.jsonDocument = AsyncApiSerializer.serialize(document.get(), AsyncApiFormat.JSON)
                            .getBytes(StandardCharsets.UTF_8);
                    this.yamlDocument = AsyncApiSerializer.serialize(document.get(), AsyncApiFormat.YAML)
                            .getBytes(StandardCharsets.UTF_8);
                    document.reset();
                    document = null;
                }
            } else {
                throw new IOException("Could not find [" + AsyncApiConstants.BASE_NAME + AsyncApiFormat.JSON + "]");
            }
        }
    }

    public byte[] getJsonDocument() {
        return jsonDocument;
    }

    public byte[] getYamlDocument() {
        return yamlDocument;
    }

    public byte[] getDocument(AsyncApiFormat format) {
        if (format.equals(AsyncApiFormat.JSON)) {
            return getJsonDocument();
        }
        return getYamlDocument();
    }
}
