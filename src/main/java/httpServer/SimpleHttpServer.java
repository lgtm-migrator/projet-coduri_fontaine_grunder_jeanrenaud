package httpServer;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class SimpleHttpServer {
    private final int PORT;
    private HttpServer server;

    public SimpleHttpServer(int port) {
        this.PORT = port;
    }

    public void start() throws IOException {
        server = HttpServer.create(new InetSocketAddress(PORT), 0);

        server.createContext("/static");

        server.start();
    }

    public void stop() {
        server.stop(0);
    }
}
