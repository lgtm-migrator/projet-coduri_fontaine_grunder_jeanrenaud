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

        if ((server = HttpServer.create(new InetSocketAddress(PORT), 0)) == null)
            throw new IOException("Could not create server");
        System.out.println("Server created");

        server.createContext("/", new StaticFileHandler(BASEDIR));
        System.out.println("Context created");

        server.start();
        System.out.println("Server started on port " + PORT);

        Thread printingHook = new Thread(this::stop);
        Runtime.getRuntime().addShutdownHook(printingHook);

        while (true);
    }

    public void stop() {
        server.stop(0);
        System.out.println("Server stopped");
    }
}
