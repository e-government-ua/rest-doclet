package org.calrissian.restdoclet.util;

import org.calrissian.restdoclet.model.Endpoint;
import org.calrissian.restdoclet.model.method.description.Snippet;
import org.calrissian.restdoclet.model.method.description.SnippetText;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.apache.commons.lang.StringUtils.containsIgnoreCase;
import static org.apache.commons.lang.StringUtils.isNotBlank;

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

    private static void parse(String methodDescription, List<Snippet> comment) {
        if (containsIgnoreCase(methodDescription, "````json")) {

        } else
            comment.add(new SnippetText(methodDescription));
    }
}
