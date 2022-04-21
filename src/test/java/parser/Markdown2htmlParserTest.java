package parser;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Markdown2htmlParserTest {
    @Test
    public void parseMarkdownWithOutYML() {
        final String markdownValue = "# heading h1\n"
                + "## heading h2\n"
                + "### heading h3\n"
                + "#### heading h4\n"
                + "---";
        final String expectedOutput = "<h1>heading h1</h1>\n"
                + "<h2>heading h2</h2>\n"
                + "<h3>heading h3</h3>\n"
                + "<h4>heading h4</h4>\n"
                + "<hr />\n";

        final ParserResult result = Markdown2htmlParser.getInstance().convertMarkdownToHTML(markdownValue);

        assertEquals(expectedOutput, result.getHtml());
    }

    @Test
    public void parseMarkdownWithYML() {
        final String markdownValue = "---\n"
                + "auteur: Bertil Chapuis\n"
                + "date: 2021-03-10\n"
                + "---\n"
                + "# Mon premier article\n"
                + "## Mon sous-titre\n"
                + "Le contenu de mon article.";

        final String expectedOutput = "<h1>Mon premier article</h1>\n"
                + "<h2>Mon sous-titre</h2>\n"
                + "<p>Le contenu de mon article.</p>\n";

        final ParserResult result = Markdown2htmlParser.getInstance().convertMarkdownToHTML(markdownValue);

        assertEquals(expectedOutput, result.getHtml());
    }

    @Test
    public void YMLValues() {
        final String markdownValue = "---\n"
                + "auteur: Bertil Chapuis\n"
                + "date: 2021-03-10\n"
                + "---\n"
                + "# Mon premier article\n"
                + "## Mon sous-titre\n"
                + "Le contenu de mon article.";

        final Map<String, List<String>> expectedOutput = new HashMap<>();
        expectedOutput.put("auteur", List.of("Bertil Chapuis"));
        expectedOutput.put("date", List.of("2021-03-10"));

        final ParserResult result = Markdown2htmlParser.getInstance().convertMarkdownToHTML(markdownValue);

        assertEquals(expectedOutput, result.getHeaders());
    }
}
