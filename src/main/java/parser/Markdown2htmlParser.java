package parser;

import org.commonmark.Extension;
import org.commonmark.ext.front.matter.YamlFrontMatterExtension;
import org.commonmark.ext.front.matter.YamlFrontMatterVisitor;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is used to parse markdown strings containing headers into html.
 * It uses the CommonMark library to parse the markdown file.
 * @author Luca Coduri
 */
public final class Markdown2htmlParser {
    private static Markdown2htmlParser instance;
    private Markdown2htmlParser() { }

    /**
     * This method is used to get the instance of the class.
     * @return a singleton.
     */
    public static Markdown2htmlParser getInstance() {
        if (instance == null) {
            instance = new Markdown2htmlParser();
        }
        return instance;
    }

    /**
     * This method is used to parse a markdown string into html.
     * @param markdown the markdown string to be parsed.
     * @return an object containing the parsed markdown string and the headers.
     */
    public ParserResult convertMarkdownToHTML(final String markdown) {
        List<Extension> extensions = List.of(YamlFrontMatterExtension.create());
        Parser parser = Parser.builder().extensions(extensions).build();
        Node document = parser.parse(markdown);

        final YamlFrontMatterVisitor visitor = new YamlFrontMatterVisitor();
        document.accept(visitor);
        HtmlRenderer htmlRenderer = HtmlRenderer.builder().extensions(extensions).build();

        return new ParserResult(htmlRenderer.render(document), simplifyMap(visitor.getData()));
    }

    /**
     * This method is used to parse a markdown Reader into html.
     * @param markdown the markdown Reader to be parsed.
     * @return an object containing the parsed markdown string and the headers.
     */
    public ParserResult convertMarkdownToHTML(final Reader markdown) throws IOException {
        final List<Extension> extensions = List.of(YamlFrontMatterExtension.create());
        final Parser parser = Parser.builder().extensions(extensions).build();
        final Node document = parser.parseReader(markdown);

        final YamlFrontMatterVisitor visitor = new YamlFrontMatterVisitor();
        document.accept(visitor);
        final HtmlRenderer htmlRenderer = HtmlRenderer.builder().extensions(extensions).build();

        return new ParserResult(htmlRenderer.render(document), simplifyMap(visitor.getData()));
    }

    private Map<String, String> simplifyMap(final Map<String, List<String>> map) {
        final Map<String, String> newMap = new HashMap<>();

        map.forEach((key, value) -> {
            newMap.put(key, value.get(0));
        });

        return newMap;
    }
}
