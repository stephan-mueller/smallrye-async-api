/*
 * Copyright 2019 Red Hat
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

package test.io.smallrye.asyncapi.tck;

import org.jboss.logging.Logger;
import org.jboss.logging.annotations.LogMessage;
import org.jboss.logging.annotations.Message;
import org.jboss.logging.annotations.MessageLogger;

@MessageLogger(projectCode = "SMAAP", length = 5)
interface TckLogging {

  TckLogging log = Logger.getMessageLogger(TckLogging.class, TckLogging.class.getPackage().getName());

  @LogMessage(level = Logger.Level.DEBUG)
  @Message(id = 12000, value = "Indexing asset: %s from archive: %s")
  void indexing(String archivePath, String archive);
}
