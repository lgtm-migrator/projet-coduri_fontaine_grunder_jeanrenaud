package httpServer;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class SimpleHttpServer {
    private final int PORT;
    private HttpServer server;
    private final String BASEDIR;

    public SimpleHttpServer(int port, String baseDirectory) {
        this.PORT = port;
        this.BASEDIR = baseDirectory;
    }

    public void start() throws IOException {
        server = HttpServer.create(new InetSocketAddress(PORT), 0);

        server.createContext("/", new StaticFileHandler(BASEDIR));

        server.start();
    }

    public void stop() {
        server.stop(0);
    }
}
