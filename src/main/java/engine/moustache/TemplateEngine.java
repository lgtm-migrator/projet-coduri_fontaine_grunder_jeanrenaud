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
 * This singleton handles templates and renders them.
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

    /**
     * Render a template with the given parameters.
     * @param context the parameters to render the template with.
     * @param templatePath the path to the template to render.
     * @return the rendered template.
     */
    public String applyMapToTemplate(final Map<String, Object> context, final Path templatePath) {
        String templateFile;
        try {
            templateFile = Files.readString(templatePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Apply all includes in the template
        templateFile = applyIncludes(templateFile);
        String modified;
        try {
            // Apply the map to the template
            Template template = handlebars.compileInline(templateFile);
            modified = template.apply(context);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(modified);
        return modified;
    }

    /**
     * It looks for includes in the template and replaces them with the content of the included file.
     * @implNote Is a recursive function.
     * @param template
     * @return
     */
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
            // Look for matching pattern
            Matcher filenameMatcher = filenamePattern.matcher(matcher.group());
            filenameMatcher.find();
            // Get the file path ex: {% include "path/to/file" }
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
