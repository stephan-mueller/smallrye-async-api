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
package io.smallrye.asyncapi.spec.spi;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ServiceLoader;

import io.smallrye.asyncapi.spec.models.Constructible;

public abstract class AASFactoryResolver {

    private static volatile AASFactoryResolver instance = null;

    /**
     * Create a new instance of a constructible element from the AsyncAPI model tree.
     *
     * @param <T> describes the type parameter
     * @param clazz represents a model which extends the Constructible interface
     * @return a new instance of the requested model class
     * @throws NullPointerException if the specified class is null
     * @throws IllegalArgumentException if an instance could not be created, most likely, due to an illegal or inappropriate
     *         class
     */
    public abstract <T extends Constructible> T createObject(Class<T> clazz);

    /**
     * Creates an AASFactoryResolver object. Only used internally from within {@link io.smallrye.asyncapi.spec.AASFactory}
     *
     * @return an instance of AASFactoryResolver
     */
    public static AASFactoryResolver instance() {
        if (instance == null) {
            synchronized (AASFactoryResolver.class) {
                if (instance != null) {
                    return instance;
                }

                ClassLoader cl = AccessController
                        .doPrivileged((PrivilegedAction<ClassLoader>) () -> Thread.currentThread().getContextClassLoader());
                if (cl == null) {
                    cl = AASFactoryResolver.class.getClassLoader();
                }

                AASFactoryResolver newInstance = loadSpi(cl);

                if (newInstance == null) {
                    throw new IllegalStateException("No AASFactoryResolver implementation found!");
                }

                instance = newInstance;
            }
        }

        return instance;
    }

    private static AASFactoryResolver loadSpi(ClassLoader cl) {
        if (cl == null) {
            return null;
        }

        AASFactoryResolver instance = loadSpi(cl.getParent());

        if (instance == null) {
            ServiceLoader<AASFactoryResolver> sl = ServiceLoader.load(AASFactoryResolver.class, cl);
            for (AASFactoryResolver spi : sl) {
                if (instance != null) {
                    throw new IllegalStateException("Multiple AASFactoryResolver implementations found: "
                            + spi.getClass().getName()
                            + " and " + instance.getClass().getName());
                }

                else {
                    instance = spi;
                }
            }
        }
        return instance;
    }

    /**
     * Set the instance. It is used by OSGi environment while service loader pattern is not supported.
     *
     * @param factory set the instance.
     */
    public static void setInstance(AASFactoryResolver factory) {
        instance = factory;
    }
}
