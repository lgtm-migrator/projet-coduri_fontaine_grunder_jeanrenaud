package httpServer;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Class that create an HTTP server to serve a website located at a given
 * directory.
 *
 * @author Chloé Fontaine
 */
public class SimpleHttpServer {
    /**
     * Port on which http server will be served
     */
    private final int PORT;
    private HttpServer server;
    /**
     * Directory that contains the html pages to serve
     */
    private final String BASEDIR;

    /**
     * Constructor that create an HTTP server.
     * @param port Port on which the website will be served
     * @param baseDirectory Directory that contains the files of the website
     *                      (should contain one file named "index.html")
     */
    public SimpleHttpServer(int port, String baseDirectory) {
        this.PORT = port;
        this.BASEDIR = baseDirectory;
    }

    /**
     * Create a new server and serve the files located in the directory.
     * @throws IOException
     */
    public void start() throws IOException {
        if ((server = HttpServer.create(new InetSocketAddress(PORT), 0)) == null)
            throw new IOException("Could not create server");
        System.out.println("Server created");

        server.createContext("/", new StaticFileHandler(BASEDIR));
        System.out.println("Context created");

        server.start();
        System.out.println("Server started on port " + PORT);

        // Appel de la méthode stop lors de l'arrêt forcé (CTRL+C)
        Thread printingHook = new Thread(this::stop);
        Runtime.getRuntime().addShutdownHook(printingHook);

         // Server is running until
        while (true);
    }

    /**
     * Stop the running server.
     */
    public void stop() {
        server.stop(0);
        System.out.println("Server stopped");
    }
}
