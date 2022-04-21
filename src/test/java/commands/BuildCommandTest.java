package commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;
import websitebuilder.App;
import java.io.File;

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
    public void commandOutputTest() {
        App app = new App();
        CommandLine cmd = new CommandLine(app);
        final String testFolder = "." + File.separator + "test";
        // black box testing
        int exitCode = cmd.execute("build", testFolder);

        File buildFolder = new File("." + File.separator + "test" + File.separator + "build");

        assertEquals(0, exitCode);
        assertEquals("", err.toString());
        assertTrue(buildFolder.exists());

    }
}
