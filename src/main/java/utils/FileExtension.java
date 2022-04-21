package utils;

import java.io.File;
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
}
