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
package io.smallrye.asyncapi.spec.annotations.channel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.smallrye.asyncapi.spec.annotations.binding.ChannelBindings;
import io.smallrye.asyncapi.spec.annotations.operation.Operation;
import io.smallrye.asyncapi.spec.annotations.parameter.Parameters;

/**
 * Describes the operations available on a single channel.
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0/#channelsObject"
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Repeatable(Channels.class)
public @interface ChannelItem {

    /**
     * Allows for an external definition of this channel item.
     *
     * The referenced structure MUST be in the format of a Channel Item Object. If there are conflicts between the referenced
     * definition and
     * this Channel Item’s definition, the behavior is undefined.
     *
     * @return the reference of the channel
     */
    String ref() default "";

    /**
     * <strong>Required</strong>. The title of the Channel
     *
     * @return the title of the channel
     */
    String channel();

    /**
     * An optional description of this channel item. CommonMark syntax can be used for rich text representation.
     *
     * @return description of the channel
     */
    String description() default "";

    /**
     * A definition of the SUBSCRIBE operation.
     *
     * @return subscribe operation of the channel
     */
    Operation subscribe() default @Operation();

    /**
     * A definition of the PUBLISH operation.
     *
     * @return publish operation of the channel
     */
    Operation publish() default @Operation();

    /**
     * A map of the parameters included in the channel name. It SHOULD be present only when using channels with expressions.
     *
     * @return parameters of the channel
     */
    Parameters parameters() default @Parameters();

    /**
     * A free-form map where the keys describe the name of the protocol and the values describe protocol-specific definitions
     * for the
     * channel.
     *
     * @return channel bindings of the channel
     */
    ChannelBindings bindings() default @ChannelBindings();
}
