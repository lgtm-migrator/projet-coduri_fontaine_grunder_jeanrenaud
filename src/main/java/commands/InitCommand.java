package commands;

import org.codehaus.plexus.util.FileUtils;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Command (  name = "init", mixinStandardHelpOptions = true, version = "init 1.0",
            description = "Initialize a static website in the designed folder." )
public class InitCommand implements Runnable {

    @Parameters(index = "0")
    private Path path;

    private File indexSource  = new File("https://github.com/dil-classroom/projet-coduri_fontaine_grunder_jeanrenaud/blob/main/init/index.md");
    private File configSource = new File("https://github.com/dil-classroom/projet-coduri_fontaine_grunder_jeanrenaud/blob/main/init/config.yaml");


    @Override
    public void run() {
        try {

            Path indexDestPath  = Path.of(path.toString() + "\\index.md");
            Path configDestPath = Path.of(path+"\\config.yaml");

            File indexDest  = new File(path+"\\index.md");
            File configDest = new File(path+"\\config.yaml");

            Files.createDirectories(path);
            Files.createFile(indexDestPath);
            Files.createFile(configDestPath);
            FileUtils.copyDirectory(indexSource,indexDest);
            FileUtils.copyDirectory(configSource,configDest);
            System.out.println("init donne");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

