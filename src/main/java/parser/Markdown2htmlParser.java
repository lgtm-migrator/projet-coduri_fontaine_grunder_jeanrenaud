package parser;

import org.commonmark.Extension;
import org.commonmark.ext.front.matter.YamlFrontMatterExtension;
import org.commonmark.ext.front.matter.YamlFrontMatterVisitor;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * this Singleton class is used to parse markdown strings containing headers into html.
 * It uses the CommonMark library to parse the markdown file.
 * @author Luca Coduri
 */
public final class Markdown2htmlParser {
    private static Markdown2htmlParser instance;
    private final Parser parser;
    private final List<Extension> extensions;
    private Markdown2htmlParser() {
        // This extension is used to parse the front matter.
        extensions = List.of(YamlFrontMatterExtension.create());
        parser = Parser.builder().extensions(extensions).build();

    }

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
        Node document = parser.parse(markdown);
        return convertDocumentToHTML(document);
    }

    /**
     * This method is used to parse a markdown Reader into html.
     * @param markdown the markdown Reader to be parsed.
     * @return an object containing the parsed markdown string and the headers.
     */
    public ParserResult convertMarkdownToHTML(final Reader markdown) throws IOException {
        Node document = parser.parseReader(markdown);
        return convertDocumentToHTML(document);
    }

    /**
     * This method is used to parse a CommonMark Node into html.
     * @param document
     * @return an object containing the parsed markdown string and the headers.
     */
    private ParserResult convertDocumentToHTML(final Node document) {
        final YamlFrontMatterVisitor visitor = new YamlFrontMatterVisitor();
        document.accept(visitor);
        HtmlRenderer htmlRenderer = HtmlRenderer.builder().extensions(extensions).build();

        return new ParserResult(htmlRenderer.render(document), visitor.getData());
    }
}
