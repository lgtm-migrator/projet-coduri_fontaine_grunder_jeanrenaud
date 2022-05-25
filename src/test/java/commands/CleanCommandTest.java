package commands;

import org.junit.jupiter.api.Test;
import picocli.CommandLine;
import websitebuilder.App;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CleanCommandTest {
    @Test
    public void testCleanCommandWithRootDirectory(){
        App app = new App();
        CommandLine cmd = new CommandLine(app);
        assertNotEquals(0, cmd.execute("clean", "/root"));

    }
}
