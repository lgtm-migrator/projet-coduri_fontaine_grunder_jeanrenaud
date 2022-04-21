package engine.moustache;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TemplateEngineTest {
    @Test
    public void noErrors() {
        String output = TemplateEngine.getInstance().applyMapToTemplate(new HashMap<>() {
            {
                put("site", new HashMap<String, Object>() {
                    {
                        put("title", "SITE TITLE");
                    }
                });
                put("page", new HashMap<String, Object>() {
                    {
                        put("title", "PAGE TITLE");
                    }
                });
                put("content", "contenu");
            }
        }, Paths.get("." + File.separator + "templates" + File.separator + "template.html"));

        assertNotEquals("", output);
    }
}
