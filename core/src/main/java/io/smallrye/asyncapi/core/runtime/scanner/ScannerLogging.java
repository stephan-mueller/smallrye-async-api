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
package io.smallrye.asyncapi.core.runtime.scanner;

import org.jboss.jandex.ClassInfo;
import org.jboss.jandex.DotName;
import org.jboss.jandex.Type;
import org.jboss.logging.BasicLogger;
import org.jboss.logging.Logger;
import org.jboss.logging.annotations.LogMessage;
import org.jboss.logging.annotations.Message;
import org.jboss.logging.annotations.MessageLogger;

@MessageLogger(projectCode = "SMAAP", length = 5)
public interface ScannerLogging extends BasicLogger {

    ScannerLogging logger = Logger.getMessageLogger(ScannerLogging.class, ScannerLogging.class.getPackage().getName());

    @LogMessage(level = Logger.Level.DEBUG)
    @Message(id = 4000, value = "Scanning deployment for %s Annotations.")
    void scanning(String annotationType);

    @LogMessage(level = Logger.Level.DEBUG)
    @Message(id = 4001, value = "Starting processing with root: %s")
    void startProcessing(DotName root);

    @LogMessage(level = Logger.Level.DEBUG)
    @Message(id = 4002, value = "Getting all fields for: %s in class: %s")
    void gettingFields(Type type, ClassInfo classInfo);

    @LogMessage(level = Logger.Level.WARN)
    @Message(id = 4003, value = "Configured schema for %s could not be parsed")
    void errorParsingSchema(String className);

    @LogMessage(level = Logger.Level.INFO)
    @Message(id = 4004, value = "Configured schema for %s has been registered")
    void configSchemaRegistered(String className);

    @LogMessage(level = Logger.Level.INFO)
    @Message(id = 99999, value = "LOG: %s")
    void log(String msg);
}
