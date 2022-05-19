import httpServer.SimpleHttpServer;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpServer {
    static final String FOLDER_NAME = "init/mysite/";
    static final String FILE_NAME = "index.html";

    @BeforeAll
    public static void setUp() {

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

    }

    @After
    public static void deleteFiles() {

    }

    @Test
    public void accessRightURL() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                SimpleHttpServer server = new SimpleHttpServer(8080, FOLDER_NAME);
                assertDoesNotThrow(server::start);
            }
        });
        thread.start();

        URL url = assertDoesNotThrow(() -> {
            return new URL("http://localhost:8080/");
        });

        HttpURLConnection connection = assertDoesNotThrow(() -> {
            return (HttpURLConnection) url.openConnection();
        });

        int responseCode = assertDoesNotThrow(connection::getResponseCode);

        assertEquals(HttpURLConnection.HTTP_OK, responseCode);

        thread.interrupt();
    }

    @Test
    public void accessWrongURL() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                SimpleHttpServer server = new SimpleHttpServer(8080, FOLDER_NAME);
                assertDoesNotThrow(server::start);
            }
        });
        thread.start();

        URL url = assertDoesNotThrow(() -> {
            return new URL("http://localhost:8080/wrong");
        });

        HttpURLConnection connection = assertDoesNotThrow(() -> {
            return (HttpURLConnection) url.openConnection();
        });

        int responseCode = assertDoesNotThrow(connection::getResponseCode);

        assertEquals(HttpURLConnection.HTTP_NOT_FOUND, responseCode);

        thread.interrupt();
    }

    @Test
    public void accessRightURLInSubFolder() {

    }

}
