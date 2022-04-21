package engine.moustache;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.FileTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        TemplateLoader templateLoader = new FileTemplateLoader("templates", ".html");
        handlebars = new Handlebars(templateLoader);
    }

    String applyMapToTemplate(final Map<String, Object> context) {
        String templateFile;
        try {
            templateFile = Files.readString(Paths.get("." + File.separator + "templates" + File.separator + "template.html"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        templateFile = applyIncludes(templateFile);
        String modified;
        try {
            Template template = handlebars.compileInline(templateFile); // TODO modifier compile par compileInline
            modified = template.apply(context);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return modified;
    }

    private String applyIncludes(final String template) {
        Pattern filenamePattern = Pattern.compile("(?<=\\{% {0,5}include {1,5})[^\\s]*(?= *})");
        Pattern includePattern = Pattern.compile("\\{% +include +[^\\s]* +}");
        Matcher matcher = includePattern.matcher(template);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
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

        }
        matcher.appendTail(sb);

        return sb.toString();
    }

    public static void main(final String[] args) {
        System.out.println(new TemplateEngine().applyMapToTemplate(new HashMap<String, Object>() {
            {
                put("title", "titre");
                put("content", "contenu");
                put("subtitle", "mdr");
                put("author", "Luca Coduri");
            }
        }));
    }
}
