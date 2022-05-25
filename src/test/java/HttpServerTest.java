import httpserver.SimpleHttpServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpServerTest {
    static final String FOLDER_NAME = "init/mysite/";
    static final String FILE_NAME = "index.html";
    static Thread thread;
    static SimpleHttpServer server;

    @BeforeAll
    public static void setUp() throws InterruptedException {

        try {
            File file = new File(FOLDER_NAME);

            if (!file.exists()) {
                file.mkdirs();
            }

            File indexFile = new File(FOLDER_NAME + File.separator + FILE_NAME);
            if (!indexFile.exists()) {

                FileWriter writer = new FileWriter(indexFile);
                writer.write("coucou\n");
                writer.flush();
                writer.close();
            }

        } catch (NullPointerException | IOException e) {
            System.out.println("Error while creating file : " + e.getMessage());
        }

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                server = new SimpleHttpServer(8080, FOLDER_NAME);
                try {
                    server.start();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        });
        thread.start();

        // Wait for the server to start
        Thread.sleep(1000);
    }

    static boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }

    @AfterAll
    public static void clear() {
        // Terminate the thread
        thread.interrupt();
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        // Delete the created directories
        if (!deleteDirectory(new File(FOLDER_NAME)))
            System.out.println("Error while deleting folder");
    }

    @Test
    public void accessRightURL() {
        URL url = assertDoesNotThrow(() -> {
            return new URL("http://localhost:8080/");
        });

        HttpURLConnection connection = assertDoesNotThrow(() -> {
            return (HttpURLConnection) url.openConnection();
        });

        int responseCode = assertDoesNotThrow(connection::getResponseCode);

        assertEquals(HttpURLConnection.HTTP_OK, responseCode);
    }

    @Test
    public void accessWrongURL() {

        URL url = assertDoesNotThrow(() -> {
            return new URL("http://localhost:8080/wrong");
        });

        HttpURLConnection connection = assertDoesNotThrow(() -> {
            return (HttpURLConnection) url.openConnection();
        });

        int responseCode = assertDoesNotThrow(connection::getResponseCode);

        assertEquals(HttpURLConnection.HTTP_NOT_FOUND, responseCode);
    }

    @Test
    public void accessRightURLInSubFolder() {
        String newFile = FOLDER_NAME + "test/" + FILE_NAME;

        try {

            File file = new File(FOLDER_NAME + "test/");

            if (!file.exists()) {
                file.mkdirs();
            }

            File indexFile = new File(newFile);
            if (!indexFile.exists()) {
                FileWriter writer = new FileWriter(indexFile);
                writer.write("coucou\n");
                writer.flush();
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("Error while creating file : " + e.getMessage());
        }

        URL url = assertDoesNotThrow(() -> {
            return new URL("http://localhost:8080/test");
        });

        HttpURLConnection connection = assertDoesNotThrow(() -> {
            return (HttpURLConnection) url.openConnection();
        });

        int responseCode = assertDoesNotThrow(connection::getResponseCode);

        assertEquals(HttpURLConnection.HTTP_OK, responseCode);
    }

}
