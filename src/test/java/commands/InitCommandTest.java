package commands;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;
import websitebuilder.App;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InitCommandTest {

    Path testPath;

    @BeforeEach
    public void setUp() throws IOException {
        testPath = Files.createTempDirectory(Paths.get(".") , "test");
    }

    @AfterEach
    public void tearDown() throws IOException {
        if(Files.exists(testPath)) {
            Files.walk(testPath).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
        }
    }

    @Test
    public void testInitCommand(){
        App app = new App();
        CommandLine cmd = new CommandLine(app);
        int exitCode = cmd.execute("init", testPath.toString());
        assertEquals(0, exitCode);
        System.out.println(testPath.toString());
        System.out.println(testPath.toAbsolutePath());
        assertTrue(Files.exists(testPath.resolve("config.yaml")));
        assertTrue(Files.exists(testPath.resolve("index.md")));
    }
}
