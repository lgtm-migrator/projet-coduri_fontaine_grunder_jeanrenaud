package httpServer;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.Files;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Handler for static file that
 *
 * Copyright rememberjava.com. Licensed under GPL 3. See http://rememberjava.com/license
 *
 * @author Chloé Fontaine
 */
@SuppressWarnings("restriction")
public class StaticFileHandler implements HttpHandler {
    /**
     * Directory that contains the html pages to serve
     */
    private final String baseDir;

    /**
     * Constructor that create a StaticFileHandler.
     * @param baseDir Directory that contains the html pages to serve
     */
    public StaticFileHandler(String baseDir) {
        this.baseDir = baseDir;
    }

    /**
     * Handle an HTTP request and send a response
     * @param ex The exchange containing the request from the
     *           client and used to send the response
     * @throws IOException
     */
    @Override
    public void handle(HttpExchange ex) throws IOException {
        URI uri = ex.getRequestURI();
        String name = new File(uri.getPath()).getName();

        // Does not offer the possibility of having a website icon
        if (name.equals("/favicon.ico"))
            ex.sendResponseHeaders(404, 0);

        // If the given URL is empty or contains a directory, redirect to index.html
        if (name.equals("") || !new File(name).isFile()) {
            name += "/index.html";
        }

        // Create the path for the index.html file
        File path = new File(baseDir, name);

        Headers h = ex.getResponseHeaders();
        h.add("Content-Type", "text/html");

        OutputStream out = ex.getResponseBody();

        if (path.exists()) {
            // Return file
            ex.sendResponseHeaders(200, path.length());
            out.write(Files.readAllBytes(path.toPath()));
        } else {
            System.err.println("File not found: " + path.getAbsolutePath());

            ex.sendResponseHeaders(404, 0);
            out.write("404 File not found.".getBytes());
        }

        out.close();
    }

}
