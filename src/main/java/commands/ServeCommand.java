package commands;

import httpServer.SimpleHttpServer;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@Command (
        name = "serve"
)
public class ServeCommand implements Runnable {
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

        SimpleHttpServer server = new SimpleHttpServer(8080, directoryName.toString());

        try {
            server.start();
        } catch (IOException e) {
            System.out.println("Error occured : " + e.getMessage());
        }

    }
}
