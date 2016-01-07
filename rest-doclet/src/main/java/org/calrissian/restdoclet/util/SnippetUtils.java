package org.calrissian.restdoclet.util;

import org.calrissian.restdoclet.model.Endpoint;
import org.calrissian.restdoclet.model.method.description.Snippet;
import org.calrissian.restdoclet.model.method.description.SnippetJSON;
import org.calrissian.restdoclet.model.method.description.SnippetText;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.apache.commons.lang.StringUtils.indexOf;
import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.apache.commons.lang.StringUtils.isNotEmpty;

/**
 * @author dgroup
 * @since 31.12.2015
 */
public final class SnippetUtils {

    private SnippetUtils(){
        // no action required
    }

    public static Collection<Snippet> description(Endpoint endpoint){
        return isNotBlank(endpoint.getDescription())
                ? description(endpoint.getDescription())
                : Collections.<Snippet>emptyList();
    }

    public static List<Snippet> description(String methodDescription){
        List<Snippet> comment = new ArrayList<>();
        parse(methodDescription, comment);
        return comment;
    }

    private static void parse(String description, List<Snippet> comment) {
        while(isNotEmpty(description)) {
            // Handle json snippet
            if (description.startsWith("````json")) {
                int start = "````json".length();
                int end   = indexOf(description, '`', start);
                comment.add(new SnippetJSON( description.substring(start, end) ));
                description = description.substring(end + 1);
            } else {
                // Handle text snippet
                int end = indexOf(description, '`');
                comment.add(new SnippetText( description.substring(0, end) ));
                description = description.substring(end);
            }
        }
    }
}
