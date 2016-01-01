package org.calrissian.restdoclet.model.method.description;

/**
 * @author dgroup
 * @since  31.12.2015
 */
public class SnippetJSON implements Snippet {
    private String content;

    public SnippetJSON(String content) {
        this.content = content;
    }

    @Override
    public String content() {
        // Parse string

        return content;
    }
}
