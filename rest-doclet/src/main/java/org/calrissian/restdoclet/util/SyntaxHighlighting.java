package org.calrissian.restdoclet.util;

import static org.apache.commons.lang.StringUtils.isNotEmpty;
import static org.apache.commons.lang.StringUtils.indexOf;

/**
 * @author Serhiy Bogoslavsky
 * @since  25.01.2016
 */

public class SyntaxHighlighting {
    private String jsonResult;
    private String inputJSONCode;
    private String startDoc = "<pre style=\"background:rgba(238,238,238,0.92);color:#000\">";
    private String endDoc = "</pre>";
    private String string = "<span style=\"color:#093\">";
    private String keyword = "<span style=\"color:#9700cc\">";
    private String number = "<span style=\"color:#06f\">";
    private String end = "</span>";


    public SyntaxHighlighting(String inputJSONCode) {
        this.inputJSONCode = inputJSONCode;
    }

    public String makeSyntaxHighlightingJSON() {
        jsonResult = " " + startDoc;

        while (isNotEmpty(inputJSONCode)) {
            if (inputJSONCode.startsWith("\"")) {
                jsonResult += highlightString();
            } else if (inputJSONCode.startsWith(":") || inputJSONCode.startsWith(" :")) {
                jsonResult += afterColon();
            } else if (Character.isDigit(inputJSONCode.charAt(0))) {
                jsonResult += highlightNumbers();
            } else if (Character.isLetter(inputJSONCode.charAt(0))) {
                jsonResult += highlightKeywords();
            } else {
                jsonResult += plainText();
            }
        }

        return jsonResult + endDoc;
    }

    private String plainText() {
        int endIndex = indexOf(inputJSONCode, "\"", 0);
        if (endIndex == -1) {
            // end of JSON code
            String temp = inputJSONCode.substring(0, inputJSONCode.length());
            inputJSONCode = inputJSONCode.substring(temp.length());
            return temp;
        } else {
            String temp = inputJSONCode.substring(0, endIndex);
            inputJSONCode = inputJSONCode.substring(temp.length());
            return temp;
        }
    }

    private String afterColon() {
        int endIndex = 1;
        while (Character.isSpaceChar(inputJSONCode.charAt(endIndex))) {
            ++endIndex;
        }
        String temp = inputJSONCode.substring(0, endIndex);
        inputJSONCode = inputJSONCode.substring(temp.length());
        return temp;
    }

    private String highlightString() {
        int endIndex = indexOf(inputJSONCode, "\"", 1);
        String temp = inputJSONCode.substring(0, endIndex + 1);
        inputJSONCode = inputJSONCode.replace(temp, "");
        return (string + temp + end);
    }

    private String highlightNumbers() {
        int endIndex = 1;
        while (Character.isDigit(inputJSONCode.charAt(endIndex))) {
            ++endIndex;
        }
        String temp = inputJSONCode.substring(0, endIndex);
        inputJSONCode = inputJSONCode.substring(temp.length());
        return (number + temp + end);
    }

    private String highlightKeywords() {
        int endIndex = 1;
        while (Character.isLetter(inputJSONCode.charAt(endIndex))) {
            ++endIndex;
        }
        String temp = inputJSONCode.substring(0, endIndex);
        inputJSONCode = inputJSONCode.substring(temp.length());
        return (keyword + temp + end);
    }
}
