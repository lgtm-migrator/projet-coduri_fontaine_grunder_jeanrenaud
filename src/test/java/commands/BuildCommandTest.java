package commands;

import org.junit.jupiter.api.Test;
import picocli.CommandLine;
import websitebuilder.App;

import static org.junit.Assert.assertEquals;

public class BuildCommandTest extends CommandTest {

    @Test
    public void commandOutputTest() {
        App app = new App();
        CommandLine cmd = new CommandLine(app);
        // black box testing
        int exitCode = cmd.execute("build", "./");
        assertEquals(0, exitCode);
        assertEquals("build"+System.lineSeparator()+"."+System.lineSeparator(), out.toString());
    }
}
