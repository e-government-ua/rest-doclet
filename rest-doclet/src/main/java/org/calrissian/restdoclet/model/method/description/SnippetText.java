package org.calrissian.restdoclet.model.method.description;

/**
 * @author dgroup
 * @since  31.12.2015
 */
public class SnippetText implements Snippet {
    private String content;

    public SnippetText(String content) {
        this.content = content;
    }

    @Override
    public String content() {
        return content;
    }
}
