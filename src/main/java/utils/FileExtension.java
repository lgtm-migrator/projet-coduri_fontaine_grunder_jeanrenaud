package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;

public final class FileExtension {
    private FileExtension() { }

    /**
     * Run trough the files in the directory and subdirectories and execute the consumer for each file.
     * @param directoryName
     * @param consumer
     */
    public static void forEachFileInDirectory(final String directoryName, final Consumer<File> consumer) {
        File directory = new File(directoryName);

        // get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isFile()) {
                consumer.accept(file);
            } else if (file.isDirectory()) {
                forEachFileInDirectory(file.getAbsolutePath(), consumer);
            }
        }
    }

    /**
     * Copy the file to the destination directory.
     * @param sourceDirectoryLocation
     * @param destinationDirectoryLocation
     * @throws IOException
     */
    public static void copyDirectory(final String sourceDirectoryLocation, final String destinationDirectoryLocation)
            throws IOException {
        Files.walk(Paths.get(sourceDirectoryLocation))
                .forEach(source -> {
                    Path destination = Paths.get(destinationDirectoryLocation, source.toString()
                            .substring(sourceDirectoryLocation.length()));
                    try {
                        Files.copy(source, destination);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
}
