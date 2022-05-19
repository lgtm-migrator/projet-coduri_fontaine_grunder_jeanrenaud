package commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;
import websitebuilder.App;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BuildCommandTest extends CommandTest {

    @BeforeEach
    public void deleteBuildFolder(){
        File buildFolder = new File("." + File.separator + "test" + File.separator + "build");
        if (buildFolder.exists()){
            deleteDir(buildFolder);
        }
    }

    public static void deleteDir(File dirFile) {
        if (dirFile.isDirectory()) {
            File[] dirs = dirFile.listFiles();
            for (File dir: dirs) {
                deleteDir(dir);
            }
        }
        dirFile.delete();
    }

    @Test
    public void commandOutputTest() throws IOException {
        App app = new App();
        CommandLine cmd = new CommandLine(app);
        final String testFolder = "." + File.separator + "test";
        // black box testing
        int exitCode = cmd.execute("build", testFolder);

        File buildFolder = new File("." + File.separator + "test" + File.separator + "build");

        assertEquals(0, exitCode);
        assertEquals("", err.toString());
        assertTrue(buildFolder.exists());
        assertTrue(Files.readString(buildFolder.toPath().resolve("index.html")).contains("<h1>Mon premier article</h1>\n"));
        assertTrue(Files.readString(buildFolder.toPath().resolve("index.html")).contains("THE WEBSITE"));
        assertTrue(Files.readString(buildFolder.toPath().resolve("index.html")).contains("Mon beau site"));
    }
}
