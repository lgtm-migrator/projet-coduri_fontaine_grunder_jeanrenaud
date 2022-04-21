package engine.moustache;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * It handles templates and renders them.
 * @author Luca Coduri
 */
public final class TemplateEngine {
    private final Handlebars handlebars;

    private static TemplateEngine instance;

    /**
     * Get the singleton instance of the TemplateEngine.
     * @return the singleton instance of the TemplateEngine.
     */
    public static TemplateEngine getInstance() {
        if (instance == null) {
            instance = new TemplateEngine();
        }
        return instance;
    }

    private TemplateEngine() {
        handlebars = new Handlebars();
    }

    String applyMapToTemplate(final Map<String, Object> context, final Path templatePath) {
        String templateFile;
        try {
            templateFile =
                    Files.readString(templatePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        templateFile = applyIncludes(templateFile);
        String modified;
        try {
            Template template = handlebars.compileInline(templateFile);
            modified = template.apply(context);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(modified);
        return modified;
    }

    private String applyIncludes(final String template) {
        Pattern filenamePattern = Pattern.compile("(?<=\\{% {0,5}include {1,5})[^\\s]*(?= *})");
        Pattern includePattern = Pattern.compile("\\{% +include +[^\\s]* +}");
        Matcher matcher = includePattern.matcher(template);
        StringBuilder sb = new StringBuilder();

        if (!matcher.find()) {
            return template;
        }
        // for each include statement in the template string
        do {
            Matcher filenameMatcher = filenamePattern.matcher(matcher.group());
            filenameMatcher.find();
            final String filename = filenameMatcher.group();
            String fileToInclude = "";

            try {
                fileToInclude =
                        Files.readString(Paths.get("." + File.separator + "templates" + File.separator + filename));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                matcher.appendReplacement(sb, fileToInclude);
            }

        } while (matcher.find());
        matcher.appendTail(sb);

        return applyIncludes(sb.toString());
    }
}
