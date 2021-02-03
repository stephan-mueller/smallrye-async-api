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

package io.smallrye.asyncapi.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class IndexCreator {

  private static final String VAR_BEGIN = "${";

  private static final String VAR_END = "}";

  private IndexCreator() {
  }

  public static byte[] createIndexHtml(Map<String, String> urls, String urlsPrimaryName, Map<Option, String> options) throws IOException {
    try (InputStream input = IndexCreator.class.getClassLoader()
        .getResourceAsStream("META-INF/resources/template/index.html");
        InputStreamReader streamreader = new InputStreamReader(input, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamreader);
        StringWriter writer = new StringWriter()) {

      String str;
      while ((str = reader.readLine()) != null) {
        str = replace(str, urls, urlsPrimaryName, options);
        if (str != null) {
          writer.write(str + "\n");
        }
      }
      String result = writer.toString();
      return result.getBytes(StandardCharsets.UTF_8);
    }
  }

  private static String replace(String line, Map<String, String> urls, String urlsPrimaryName, Map<Option, String> options) {
    if (line.contains(VAR_BEGIN)) {
      Option variableOption = getVariable(line);
      if (variableOption != null) {
        if (options != null && options.containsKey(variableOption)) {
          String replacement = options.get(variableOption);

          if (replacement == null) {
            // You want to remove this line
            return null;
          } else {
            // Some properties can be boolean or String, if String we need to add '
            replacement = replacement.trim();

            line = line.replace(VAR_BEGIN + variableOption + VAR_END, replacement);
          }
        }
        // Check if there is more variables
        return replace(line, urls, urlsPrimaryName, options);
      }
    }
    return line;
  }

  private static Option getVariable(String line) {
    try {
      String stringValue = line.substring(line.indexOf(VAR_BEGIN) + 2, line.indexOf(VAR_END));
      return Option.valueOf(stringValue);
    } catch (IllegalArgumentException iae) {
      // Quitly fall through (maybe you want the var there ?)
      return null;
    }
  }
}
