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

import java.util.ArrayList;
import java.util.List;

import io.smallrye.asyncapi.core.api.constants.AsyncApiConstants;
import io.smallrye.asyncapi.core.api.models.AsyncAPIImpl;
import io.smallrye.asyncapi.core.api.models.info.InfoImpl;
import io.smallrye.asyncapi.core.api.util.ConfigUtil;
import io.smallrye.asyncapi.core.api.util.FilterUtil;
import io.smallrye.asyncapi.core.api.util.MergeUtil;
import io.smallrye.asyncapi.spec.AASFilter;
import io.smallrye.asyncapi.spec.models.AsyncAPI;

/**
 * Holds the final AsyncAPI document produced during the startup of the app.
 * <p>
 * Note that the model must be initialized first!
 */
public class AsyncApiDocument {

    public static final AsyncApiDocument INSTANCE = new AsyncApiDocument();

    private transient AsyncApiConfig config;

    public transient AsyncAPI annotationsModel;

    public transient AsyncAPI readerModel;

    public transient AsyncAPI staticFileModel;

    private transient List<AASFilter> filters = new ArrayList<>();

    private transient String archiveName;

    private transient AsyncAPI model;

    public AsyncApiDocument() {
    }

    /**
     * @return the final AsyncAPI document produced during the startup of the app
     * @throws IllegalStateException If the final model is not initialized yet
     */
    public AsyncAPI get() {
        synchronized (INSTANCE) {
            if (this.model == null) {
                throw ApiMessages.msg.modelNotInitialized();
            }
            return this.model;
        }
    }

    /**
     * Set the final AsyncAPI document. This method should only be used for testing.
     *
     * @param model AsyncAPI model instance
     */
    public void set(final AsyncAPI model) {
        synchronized (INSTANCE) {
            this.model = model;
        }
    }

    /**
     * Reset the holder.
     */
    public void reset() {
        synchronized (INSTANCE) {
            this.model = null;
            clear();
        }
    }

    /**
     * @return {@code true} if model initialized
     */
    public boolean isSet() {
        synchronized (INSTANCE) {
            return this.model != null;
        }
    }

    public synchronized void config(final AsyncApiConfig config) {
        set(() -> this.config = config);
    }

    public void modelFromAnnotations(final AsyncAPI model) {
        set(() -> this.annotationsModel = model);
    }

    public void modelFromReader(final AsyncAPI model) {
        set(() -> this.readerModel = model);
    }

    public void modelFromStaticFile(final AsyncAPI model) {
        set(() -> this.staticFileModel = model);
    }

    public void filter(final AASFilter filter) {
        if (filter != null) {
            set(() -> this.filters.add(filter));
        }
    }

    public void archiveName(String archiveName) {
        set(() -> this.archiveName = archiveName);
    }

    public void initialize() {
        synchronized (INSTANCE) {

            if (model != null) {
                throw ApiMessages.msg.modelAlreadyInitialized();
            }
            // Check all the required parts are set
            if (config == null) {
                throw ApiMessages.msg.configMustBeSet();
            }

            // Phase 1: Use AASModelReader
            AsyncAPI merged = readerModel;

            // Phase 2: Merge any static AsyncAPI file packaged in the app
            merged = MergeUtil.mergeObjects(merged, staticFileModel);

            // Phase 3: Merge annotations
            merged = MergeUtil.mergeObjects(merged, annotationsModel);

            // Phase 4: Filter model via AASFilter
            merged = filterModel(merged);

            // Phase 5: Default empty document if model == null
            if (merged == null) {
                merged = new AsyncAPIImpl();
                merged.setAsyncapi(AsyncApiConstants.ASYNC_API_VERSION);
            }

            // Phase 6: Provide missing required elements using defaults
            if (merged.getInfo() == null) {
                merged.setInfo(new InfoImpl());
            }
            if (merged.getInfo().getTitle() == null) {
                merged.getInfo().setTitle((archiveName == null ? "Generated" : archiveName) + " API");
            }
            if (merged.getInfo().getVersion() == null) {
                merged.getInfo().setVersion("1.0");
            }

            if (merged.getDefaultContentType() == null) {
                merged.setDefaultContentType("application/json");
            }

            // Phase 7: Use Config values to add Servers (global, pathItem, operation)
            ConfigUtil.applyConfig(config, merged);

            model = merged;
            clear();
        }
    }

    /**
     * Filter the final model using a {@link AASFilter} configured by the app. If no filter has been configured, this will
     * simply return the model unchanged.
     *
     * @param model
     */
    private AsyncAPI filterModel(AsyncAPI model) {
        if (model == null || filters.isEmpty()) {
            return model;
        }
        for (AASFilter filter : filters) {
            model = FilterUtil.applyFilter(filter, model);
        }
        return model;
    }

    private void set(Runnable action) {
        synchronized (INSTANCE) {
            if (model != null) {
                throw ApiMessages.msg.modelAlreadyInitialized();
            }
            action.run();
        }
    }

    private void clear() {
        this.config = null;
        this.annotationsModel = null;
        this.readerModel = null;
        this.staticFileModel = null;
        this.filters.clear();
        this.archiveName = null;
    }
}
