package commands;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import parser.ConfigModel;
import parser.Markdown2htmlParser;
import parser.ParserResult;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Command;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.regex.Pattern;

import static utils.FileExtension.copyDirectory;
import static utils.FileExtension.forEachFileInDirectory;

@Command (
        name = "build",
        description = "Build the website using the specified folder."
)
public class BuildCommand implements Runnable {
    private static final String CONFIG_FILE = "config.yml";
    private File buildFolder;
    private ConfigModel configModel;

    @Parameters(index = "0", description = "The folder to build the website from.")
    private Path folderPath;

    @Override
    public void run() {
        if (!folderPath.toFile().exists()) {
            throw new RuntimeException("The specified folder does not exist.");
        }

        try {
            buildFolder = Files.createTempDirectory("build").toFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (!buildFolder.exists() && !buildFolder.mkdir()) {
            throw new RuntimeException("Could not create a temp build folder.");
        }

        loadConfigFile();

        // Parse all file in path and sub-folders
        forEachFileInDirectory(folderPath.toString(), this::parseAndCreateFile);

        // Move the temp build folder to the specified folder
        try {
            copyDirectory(buildFolder.getAbsolutePath().toString(),
                    Path.of(folderPath.toString() + File.separator + "build").toAbsolutePath().toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadConfigFile() {
        try (InputStream configFile =
                     new BufferedInputStream(new FileInputStream(folderPath + File.separator + CONFIG_FILE))) {
            Yaml yaml = new Yaml(new Constructor(ConfigModel.class));
            configModel = yaml.load(configFile);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void parseAndCreateFile(final File file) {
        final Pattern p = Pattern.compile("(\\.[mM][dD])$");
        String relativizedPath =
                folderPath.toFile().getAbsoluteFile().toURI().relativize(file.getAbsoluteFile().toURI()).getPath();

        // If the file is not a markdown file, copy it to the build folder and return.
        if (!p.matcher(file.getPath()).find()) {
            try {
                Files.copy(file.toPath().toAbsolutePath(),
                        Path.of(buildFolder.getAbsolutePath() + File.separator + relativizedPath).toAbsolutePath(),
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        // Replace .md || .Md || .mD || .MD with .html
        relativizedPath = p.matcher(relativizedPath).replaceAll(".html");


        try (FileReader reader = new FileReader(file)) {
            Markdown2htmlParser parser = Markdown2htmlParser.getInstance();
            ParserResult result = parser.convertMarkdownToHTML(reader);
            File newFile = new File(buildFolder.getAbsolutePath() + File.separator + relativizedPath);
            newFile.getParentFile().mkdirs();
            newFile.createNewFile();

            try (FileWriter writer = new FileWriter(newFile)) {
                writer.write(result.getHtml());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }



}
