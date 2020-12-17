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
package io.smallrye.asyncapi.spec.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.smallrye.asyncapi.spec.annotations.binding.ChannelBindings;
import io.smallrye.asyncapi.spec.annotations.binding.MessageBindings;
import io.smallrye.asyncapi.spec.annotations.binding.OperationBindings;
import io.smallrye.asyncapi.spec.annotations.binding.ServerBindings;
import io.smallrye.asyncapi.spec.annotations.message.CorrelationID;
import io.smallrye.asyncapi.spec.annotations.message.Message;
import io.smallrye.asyncapi.spec.annotations.message.MessageTrait;
import io.smallrye.asyncapi.spec.annotations.operation.OperationTrait;
import io.smallrye.asyncapi.spec.annotations.parameter.Parameter;
import io.smallrye.asyncapi.spec.annotations.schema.Schema;
import io.smallrye.asyncapi.spec.annotations.security.SecurityScheme;

/**
 * Holds a set of reusable objects for different aspects of the AsyncAPI specification.
 *
 * All objects defined within the components object will have no effect on the API unless they are explicitly referenced from
 * properties
 * outside the components object.
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0/#componentsObject"
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Components {

    /**
     * An object to hold reusable Schema Objects.
     *
     * @return the reusable Schema objects.
     */
    Schema[] schemas() default {};

    /**
     * An object to hold reusable Message Objects.
     *
     * @return the reusable Message objects.
     */
    Message[] messages() default {};

    /**
     * An object to hold reusable Security Scheme Objects.
     *
     * @return the reusable SecurityScheme objects.
     */
    SecurityScheme[] securitySchemes() default {};

    /**
     * An object to hold reusable Parameter Objects.
     *
     * @return the reusable Parameter objects.
     */
    Parameter[] parameters() default {};

    /**
     * An object to hold reusable Correlation ID Objects.
     *
     * @return the reusable CorrelationID objects.
     */
    CorrelationID[] correlationIds() default {};

    /**
     * An object to hold reusable Operation Trait Objects.
     *
     * @return the reusable OperationTraits objects.
     */
    OperationTrait[] operationTraits() default {};

    /**
     * An object to hold reusable Message Trait Objects.
     *
     * @return the reusable Message Trait Objects.
     */
    MessageTrait[] messageTraits() default {};

    /**
     * An object to hold reusable Server Binding Objects.
     *
     * @return the reusable Server Binding Objects.
     */
    ServerBindings serverBindings() default @ServerBindings();

    /**
     * An object to hold reusable Channel Binding Objects.
     *
     * @return the reusable Channel Binding Objects.
     */
    ChannelBindings channelBindings() default @ChannelBindings();

    /**
     * An object to hold reusable Operation Binding Objects.
     *
     * @return the reusable Operation Binding Objects.
     */
    OperationBindings operationBindings() default @OperationBindings();

    /**
     * An object to hold reusable Message Binding Objects.
     *
     * @return the reusable Message Binding Objects.
     */
    MessageBindings messageBindings() default @MessageBindings();
}
