package commands;

import org.codehaus.plexus.util.FileUtils;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 *Command to create a folder with most things needed for a static website.
 *
 * @author Alice Grunder
 * @version 1.0
 */
@Command (name = "init", mixinStandardHelpOptions = true, version = "init 1.0",
          description = "Initialize a static website in the designed folder.")
public class InitCommand implements Runnable {

    @Parameters(index = "0")
    private String destPathString;

    private File sourcePath;

    {
        try {
            sourcePath = new File(this.getClass().getClassLoader().getResource("init").toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        File destPath = new File(System.getProperty("user.dir") + destPathString);
        try {
            FileUtils.copyDirectory(sourcePath, destPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

