import httpServer.SimpleHttpServer;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpServer {

    @Test
    public void accessRightURL() {
        SimpleHttpServer server = new SimpleHttpServer(8080, "index.html");

        assertDoesNotThrow(server::start);
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
        SimpleHttpServer server = new SimpleHttpServer(8080, "index.html");

        assertDoesNotThrow(server::start);
        URL url = assertDoesNotThrow(() -> {
            return new URL("http://localhost:8080/wrong");
        });

        HttpURLConnection connection = assertDoesNotThrow(() -> {
            return (HttpURLConnection) url.openConnection();
        });

        int responseCode = assertDoesNotThrow(connection::getResponseCode);

        assertEquals(HttpURLConnection.HTTP_NOT_FOUND, responseCode);
    }

}
