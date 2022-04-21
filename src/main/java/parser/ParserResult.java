package parser;

import java.util.List;
import java.util.Map;

/**
 * This class represents the result of a parsing operation.
 *
 * @author Luca Coduri
 */
public class ParserResult {
    private final String html;
    private final Map<String, List<String>> headers;

    /**
     *
     * @param html Result of the markdown parsing.
     * @param headers Result of the yaml parsing.
     */
    ParserResult(final String html, final Map<String, List<String>> headers) {
        assert html != null;
        assert headers != null;

        this.html = html;
        this.headers = headers;
    }

    /**
     *
     * @return the html result of the parsing.
     */
    public String getHtml() {
        return html;
    }

    /**
     *
     * @return the headers result of the parsing.
     */
    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    @Override
    public String toString() {
        return "Headers: "  + headers + "\n"
            + "Html:\n" + html;
    }
}
