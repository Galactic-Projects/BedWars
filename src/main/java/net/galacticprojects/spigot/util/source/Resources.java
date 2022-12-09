package net.galacticprojects.spigot.util.source;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.file.PathUtils;

public final class Resources {

    private final File folder;
    private final URI jarUri;
    private final boolean jarFile;

    private final Logger logger;


    public Resources(File folder, File jar, Logger logger) {
        this.logger = logger;
        this.folder = folder;
        this.jarFile = jar.isFile();
        this.jarUri = buildUri(jar);
    }

    public File getExternalRoot() {
        return folder;
    }

    /*
     * Helper
     */

    private URI buildUri(File jar) {
        try {
            return new URI(("jar:file:/" + jar.getAbsolutePath().replace('\\', '/').replace(" ", "%20") + "!/").replace("//", "/"));
        } catch (URISyntaxException exp) {
            logger.log(Level.WARNING, "Failed to build resource uri", exp);
            logger.log(Level.WARNING, "Falling back to jar uri, could cause problems");
            return jar.toURI();
        }
    }

    private Path createFileSystem(final URI uri) throws IOException {
        try {
            return FileSystems.getFileSystem(uri).getPath("/");
        } catch (final Exception exp) {
            return FileSystems.newFileSystem(uri, Collections.emptyMap()).getPath("/");
        }
    }

    private Path copyDirectoryPath(final Path path, final File target) throws Exception {
        if (target.exists()) {
            return target.toPath();
        }
        if (!target.exists() && target.isDirectory()) {
            target.mkdirs();
        }
        try (Stream<Path> walk = java.nio.file.Files.walk(path, 1)) {
            for (final Iterator<Path> iterator = walk.iterator(); iterator.hasNext();) {
                final Path next = iterator.next();
                if (next == path) {
                    continue;
                }
                final File nextTarget = new File(target, next.getName(next.getNameCount() - 1).toString());
                if (PathUtils.isDirectory(next)) {
                    copyDirectoryPath(next, nextTarget);
                    continue;
                }
                copyFilePath(next, nextTarget);
            }
        }
        return target.toPath();
    }

    private Path copyFilePath(final Path path, final File target) throws Exception {
        if (target.exists()) {
            return target.toPath();
        }
        if (!target.exists() && !target.isDirectory()) {
            target.createNewFile();
        }
        try (InputStream input = path.getFileSystem().provider().newInputStream(path, StandardOpenOption.READ)) {
            try (FileOutputStream output = new FileOutputStream(target)) {
                IOUtils.copy(input, output);
            }
        }
        return target.toPath();
    }

    /*
     * Source Builder
     */

    public URLSource url(String url) throws MalformedURLException {
        return url(new URL(url));
    }

    public URLSource url(URI uri) throws MalformedURLException {
        return url(uri.toURL());
    }

    public URLSource url(URL url) {
        return new URLSource(url);
    }

    public PathSource path(String path) {
        return path(Paths.get(path));
    }

    public PathSource path(Path path) {
        return new PathSource(path);
    }

    public FileSource fileData(String directory, String path) {
        return file(new File(folder, directory), path);
    }

    public FileSource fileData(String path) {
        return new FileSource(new File(folder, path));
    }

    public FileSource file(String directory, String path) {
        return file(new File(directory, path));
    }

    public FileSource file(File directory, String path) {
        return file(new File(directory, path));
    }

    public FileSource file(String path) {
        return file(new File(path));
    }

    public FileSource file(File file) {
        return new FileSource(file);
    }
    
    public File folderData(String path) {
        return new File(folder, path);
    }

}
