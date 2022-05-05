import httpServer.SimpleHttpServer;
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

    @BeforeAll
    public static void setUp() {
        String path = "init/mysite/";
        String fileName = "index.html";

        try {
            File file = new File(path);

            if (!file.exists()) {
                file.mkdirs();
            }

            File indexFile = new File(path + File.separator + fileName);
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

    @Test
    public void accessRightURL() {
        SimpleHttpServer server = new SimpleHttpServer(8080, "./init/mysite");

        assertDoesNotThrow(server::start);
        URL url = assertDoesNotThrow(() -> {
            return new URL("http://localhost:8080/");
        });

        HttpURLConnection connection = assertDoesNotThrow(() -> {
            return (HttpURLConnection) url.openConnection();
        });

        int responseCode = assertDoesNotThrow(connection::getResponseCode);

        assertEquals(HttpURLConnection.HTTP_OK, responseCode);

        server.stop();
    }

    @Test
    public void accessWrongURL() {
        SimpleHttpServer server = new SimpleHttpServer(8080, "/init/mysite");

        assertDoesNotThrow(server::start);
        URL url = assertDoesNotThrow(() -> {
            return new URL("http://localhost:8080/wrong");
        });

        HttpURLConnection connection = assertDoesNotThrow(() -> {
            return (HttpURLConnection) url.openConnection();
        });

        int responseCode = assertDoesNotThrow(connection::getResponseCode);

        assertEquals(HttpURLConnection.HTTP_NOT_FOUND, responseCode);

        server.stop();
    }

}
