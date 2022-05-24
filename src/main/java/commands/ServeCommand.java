package commands;

import httpServer.SimpleHttpServer;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@Command (
        name = "serve",
        description = "Serve the website located at the given directory"
)
public class ServeCommand implements Runnable {
    final int PORT = 8080;

    @CommandLine.Parameters(
            paramLabel = "directory name"
    )
    private Path directoryName;

    @Override
    public void run() {
        if (!(new File(directoryName.toString()).exists())) {
            System.out.println("Path not found");
            return;
        }

        SimpleHttpServer server = new SimpleHttpServer(PORT, directoryName.toString());

        try {
            server.start();
        } catch (IOException e) {
            System.out.println("Error occured : " + e.getMessage());
        }

    }
}
