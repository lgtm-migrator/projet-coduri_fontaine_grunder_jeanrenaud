import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import org.apache.commons.io.FileUtils;

@CommandLine.Command (
        name = "clean"
)
public class CleanCommand implements Runnable {
    @CommandLine.Parameters
    private Path directoryName;

    @Override
    public void run() {
        File buildDir = new File(directoryName + "/build/");
        try {
            FileUtils.deleteDirectory(buildDir);
        } catch (IOException e){
            System.out.println("Error occured : " + e.getMessage());
        }

    }
}
