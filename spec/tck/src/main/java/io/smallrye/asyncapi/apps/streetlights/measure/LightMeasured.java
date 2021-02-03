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

package io.smallrye.asyncapi.apps.streetlights.measure;

import java.time.LocalDateTime;

import io.smallrye.asyncapi.spec.annotations.schema.Schema;
import io.smallrye.asyncapi.spec.annotations.schema.SchemaProperty;
import io.smallrye.asyncapi.spec.annotations.schema.SchemaType;

@Schema(name = "lightMeasuredPayload", type = SchemaType.OBJECT)
public class LightMeasured {

    @Schema(name = "lumens", type = SchemaType.INTEGER, description = "Light intensity measured in lumens.", minimum = "0", required = true)
    private int lumens;

    @Schema(ref = "#/components/schemas/sentAt")
    private LocalDateTime sentAt;

    public LightMeasured() {
    }

    public LightMeasured(final int lumens, final LocalDateTime sentAt) {
        this.lumens = lumens;
        this.sentAt = sentAt;
    }

    public int getLumens() {
        return lumens;
    }

    public void setLumens(final int lumens) {
        this.lumens = lumens;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(final LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    @Override
    public String toString() {
        return "LightMeasured{" + "lumens=" + lumens + ", sentAt=" + sentAt + '}';
    }
}
