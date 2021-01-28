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

package test.io.smallrye.asyncapi.tck;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Map;
import java.util.Set;

import org.eclipse.microprofile.config.ConfigProvider;
import org.jboss.jandex.DotName;
import org.jboss.jandex.IndexView;
import org.jboss.jandex.Indexer;
import org.jboss.logging.Logger;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePath;
import org.jboss.shrinkwrap.api.Node;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.classloader.ShrinkWrapClassLoader;
import org.jboss.shrinkwrap.api.importer.ZipImporter;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

import io.smallrye.asyncapi.core.api.AsyncApiConfig;
import io.smallrye.asyncapi.core.api.AsyncApiConfigImpl;
import io.smallrye.asyncapi.core.api.constants.AsyncApiConstants;
import io.smallrye.asyncapi.core.runtime.AsyncApiFormat;
import io.smallrye.asyncapi.core.runtime.AsyncApiStaticFile;
import io.smallrye.asyncapi.core.runtime.scanner.AsyncApiAnnotationScanner;
import io.smallrye.asyncapi.core.runtime.scanner.FilteredIndexView;

/**
 * Some useful methods for creating stuff from ShrinkWrap {@link Archive}s.
 */
public class ArchiveUtil {
  private static final Logger LOG = Logger.getLogger(ArchiveUtil.class);

  private ArchiveUtil() {
  }

  /**
   * Creates an {@link AsyncApiConfig} instance from the given ShrinkWrap archive.
   *
   * @param archive Shrinkwrap Archive instance
   * @return AsyncApiConfig
   */
  public static AsyncApiConfig archiveToConfig(Archive<?> archive) {
    try (ShrinkWrapClassLoader cl = new ShrinkWrapClassLoader(archive)) {
      return new AsyncApiConfigImpl(ConfigProvider.getConfig(cl));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Finds the static AsyncAPI file located in the deployment and, if it exists, returns it as an {@link AsyncApiStaticFile}.
   * If not found, returns
   * null. The static file (when not null) contains an {@link InputStream} to the contents of the static file. The caller is
   * responsible for closing
   * this stream.
   *
   * @param archive Shrinkwrap Archive instance
   * @return AsyncApiStaticFile
   */
  public static AsyncApiStaticFile archiveToStaticFile(Archive<?> archive) {
    AsyncApiStaticFile rval = new AsyncApiStaticFile();
    rval.setFormat(AsyncApiFormat.YAML);

    // Check for the file in both META-INF and WEB-INF/classes/META-INF
    Node node = archive.get("/META-INF/asyncapi.yaml");
    if (node == null) {
      node = archive.get("/WEB-INF/classes/META-INF/asyncapi.yaml");
    }
    if (node == null) {
      node = archive.get("/META-INF/asyncapi.yml");
    }
    if (node == null) {
      node = archive.get("/WEB-INF/classes/META-INF/asyncapi.yml");
    }
    if (node == null) {
      node = archive.get("/META-INF/asyncapi.json");
      rval.setFormat(AsyncApiFormat.JSON);
    }
    if (node == null) {
      node = archive.get("/WEB-INF/classes/META-INF/asyncapi.json");
      rval.setFormat(AsyncApiFormat.JSON);
    }

    if (node == null) {
      return null;
    }

    rval.setContent(node.getAsset().openStream());

    return rval;
  }

  /**
   * Index the ShrinkWrap archive to produce a jandex index.
   *
   * @param config AsyncApiConfig
   * @param archive Shrinkwrap Archive
   * @return indexed classes in Archive
   */
  public static IndexView archiveToIndex(AsyncApiConfig config, Archive<?> archive) {
    if (archive == null) {
      throw TckMessages.msg.nullArchive();
    }

    Indexer indexer = new Indexer();
    index(indexer, "io/smallrye/asyncapi/core/runtime/scanner/CollectionStandin.class");
    index(indexer, "io/smallrye/asyncapi/core/runtime/scanner/IterableStandin.class");
    index(indexer, "io/smallrye/asyncapi/core/runtime/scanner/MapStandin.class");
    indexArchive(config, indexer, archive);
    return indexer.complete();
  }

  private static void index(Indexer indexer, String resName) {
    ClassLoader cl = AsyncApiAnnotationScanner.class.getClassLoader();
    try (InputStream klazzStream = cl.getResourceAsStream(resName)) {
      indexer.index(klazzStream);
    } catch (IOException ioe) {
      throw new UncheckedIOException(ioe);
    }
  }

  /**
   * Indexes the given archive.
   *
   * @param config
   * @param indexer
   * @param archive
   */
  private static void indexArchive(AsyncApiConfig config, Indexer indexer, Archive<?> archive) {
    FilteredIndexView filter = new FilteredIndexView(null, config);
    Map<ArchivePath, Node> c = archive.getContent();
    try {
      for (Map.Entry<ArchivePath, Node> each : c.entrySet()) {
        ArchivePath archivePath = each.getKey();
        if (archivePath.get()
            .endsWith(AsyncApiConstants.CLASS_SUFFIX) && acceptClassForScanning(filter, archivePath.get())) {
          try (InputStream contentStream = each.getValue()
              .getAsset()
              .openStream()) {
            TckLogging.log.indexing(archivePath.get(), archive.getName());
            indexer.index(contentStream);
          }
          continue;
        }
        if (archivePath.get()
            .endsWith(AsyncApiConstants.JAR_SUFFIX) && acceptJarForScanning(config, archivePath.get())) {
          try (InputStream contentStream = each.getValue()
              .getAsset()
              .openStream()) {
            JavaArchive jarArchive = ShrinkWrap.create(JavaArchive.class, archivePath.get())
                .as(ZipImporter.class)
                .importFrom(contentStream)
                .as(JavaArchive.class);
            indexArchive(config, indexer, jarArchive);
          }
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Returns true if the given JAR archive (dependency) should be cracked open and indexed along with the rest of the
   * deployment's classes.
   *
   * @param config
   * @param jarName
   */
  private static boolean acceptJarForScanning(AsyncApiConfig config, String jarName) {
    if (config.scanDependenciesDisable()) {
      return false;
    }
    Set<String> scanDependenciesJars = config.scanDependenciesJars();
    String nameOnly = new File(jarName).getName();
    return scanDependenciesJars.isEmpty() || scanDependenciesJars.contains(nameOnly);
  }

  /**
   * Returns true if the class represented by the given archive path should be included in the annotation index.
   *
   * @param filter
   * @param archivePath
   */
  private static boolean acceptClassForScanning(FilteredIndexView filter, String archivePath) {
    if (archivePath == null) {
      return false;
    }

    if (archivePath.startsWith(AsyncApiConstants.WEB_ARCHIVE_CLASS_PREFIX)) {
      archivePath = archivePath.substring(AsyncApiConstants.WEB_ARCHIVE_CLASS_PREFIX.length());
    }

    String fqcn = archivePath.replaceAll("/", ".")
        .substring(0, archivePath.lastIndexOf(AsyncApiConstants.CLASS_SUFFIX));

    if (fqcn.startsWith(".")) {
      fqcn = fqcn.substring(1);
    }

    return filter.accepts(DotName.createSimple(fqcn));
  }

}
