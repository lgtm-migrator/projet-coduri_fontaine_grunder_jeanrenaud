import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class SimpleHttpServer {
    private final int PORT;
    private SimpleHttpServer server;

    public SimpleHttpServer(int port) {
        this.PORT = port;
    }

    void start() throws IOException {
        server = HttpServer.create(new InetSocketAddress(PORT), 0);

        server.createContext("/static", new StaticFileHandler(BASEDIR));

        server.start();
    }

    public void stop() {
        server.stop(0);
    }
}
