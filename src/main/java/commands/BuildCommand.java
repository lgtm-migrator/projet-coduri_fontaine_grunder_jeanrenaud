package commands;

import picocli.CommandLine.Parameters;
import picocli.CommandLine.Command;

import java.nio.file.Path;

@Command (
        name = "build",
        description = "Build the website using the specified folder."
)
public class BuildCommand implements Runnable {
    @Parameters(index = "0", description = "The folder to build the website from.")
    private Path folderPath;

    @Override
    public void run() {
        System.out.println("build");
        System.out.println(folderPath);
    }
}
