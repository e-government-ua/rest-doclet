package org.calrissian.restdoclet.model.method.description;

import static org.apache.commons.lang.StringUtils.indexOf;
import static org.apache.commons.lang.StringUtils.isNotEmpty;
import static org.apache.commons.lang.Validate.isTrue;
import static org.apache.commons.lang.Validate.notNull;

/**
 * @author Serhiy Bogoslavsky
 * @since  25.01.2016
 */

public class SyntaxHighlighting {

    private static final String PRE_START   = "<pre style=\"background:rgba(238,238,238,0.92);color:#000\">";
    private static final String PRE_END     = "</pre>";
    private static final String HTML_TEXT       = "<span style=\"color:#093\">";
    private static final String HTML_KEYWORD    = "<span style=\"color:#9700cc\">";
    private static final String HTML_NUMBER     = "<span style=\"color:#06f\">";
    private static final String end = "</span>";

    private String jsonToHtml;
    private String jsonText;


    public SyntaxHighlighting(String jsonText) {
        this(jsonText, 0, 0);
    }

    public SyntaxHighlighting(String jsonText, int start, int end) {
        notNull(jsonText,   "JSON text should be not null");
        isTrue(start > 0,   "Start index should be more that 0");
        isTrue(end   > 0,   "End index should be more that 0");
        isTrue(start <= end,"Start index should be less or equal to end index");
        this.jsonText = start == end? jsonText : jsonText.substring(start, end);
    }

    public String highlightJSON() {
        jsonToHtml = " " + PRE_START;

        while (isNotEmpty(jsonText)) {
            if (jsonText.startsWith("\"")) {
                jsonToHtml += highlightString();
            } else if (jsonText.startsWith(":") || jsonText.startsWith(" :")) {
                jsonToHtml += afterColon();
            } else if (Character.isDigit(jsonText.charAt(0))) {
                jsonToHtml += highlightNumbers();
            } else if (Character.isLetter(jsonText.charAt(0))) {
                jsonToHtml += highlightKeywords();
            } else {
                jsonToHtml += plainText();
            }
        }

        return jsonToHtml + PRE_END;
    }

    private String plainText() {
        int end = indexOf(jsonText, "\"", 0);
        if (end == -1) {
            // end of JSON code
            String temp = jsonText.substring(0, jsonText.length());
            jsonText = jsonText.substring(temp.length());
            return temp;
        } else {
            String temp = jsonText.substring(0, end);
            jsonText = jsonText.substring(temp.length());
            return temp;
        }
    }

    private String afterColon() {
        int endIndex = 1;
        while (Character.isSpaceChar(jsonText.charAt(endIndex))) {
            ++endIndex;
        }
        String temp = jsonText.substring(0, endIndex);
        jsonText = jsonText.substring(temp.length());
        return temp;
    }

    private String highlightString() {
        int endIndex = indexOf(jsonText, "\"", 1);
        String temp = jsonText.substring(0, endIndex + 1);
        jsonText = jsonText.replace(temp, "");
        return (HTML_TEXT + temp + end);
    }

    private String highlightNumbers() {
        int endIndex = 1;
        while (Character.isDigit(jsonText.charAt(endIndex))) {
            ++endIndex;
        }
        String temp = jsonText.substring(0, endIndex);
        jsonText = jsonText.substring(temp.length());
        return (HTML_NUMBER + temp + end);
    }

    private String highlightKeywords() {
        int end = 1;
        while (Character.isLetter(jsonText.charAt(end))) {
            ++end;
        }
        String temp = jsonText.substring(0, end);
        jsonText = jsonText.substring(temp.length());
        return HTML_KEYWORD + temp + SyntaxHighlighting.end;
    }
}