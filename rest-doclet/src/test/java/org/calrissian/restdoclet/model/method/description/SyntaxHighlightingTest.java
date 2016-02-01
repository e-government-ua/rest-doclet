package org.calrissian.restdoclet.model.method.description;

import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * @author dgroup
 * @since  27.01.2016
 */
public class SyntaxHighlightingTest {
    String jsonText  =
            "  {\n" +
                    "  \"book\": [\n" +
                    "     {\n" +
                    "      \"id\":\"01\",\n" +
                    "      \"language\": \"Java\",\n" +
                    "      \"edition\": \"third\",\n" +
                    "      \"author\": \"Herbert Schildt\"\n" +
                    "     },\n" +
                    "   ]\n" +
                    " }";
    String jsonRequestSynHighlighted =
            " <pre style=\"background:rgba(238,238,238,0.92);color:#000\"> {\n" +
                    "  <span style=\"color:#093\">\"book\"</span>: [\n" +
                    "     {\n" +
                    "      <span style=\"color:#093\">\"id\"</span>:<span style=\"color:#093\">\"01\"</span>,\n" +
                    "      <span style=\"color:#093\">\"language\"</span>: <span style=\"color:#093\">\"Java\"</span>,\n" +
                    "      <span style=\"color:#093\">\"edition\"</span>: <span style=\"color:#093\">\"third\"</span>,\n" +
                    "      <span style=\"color:#093\">\"author\"</span>: <span style=\"color:#093\">\"Herbert Schildt\"</span>\n" +
                    "     },\n" +
                    "   ]\n" +
                    " }</pre>";

    @Test(expected = IllegalArgumentException.class)
    public void nullToSingleArgumentConstructor(){
        new SyntaxHighlighting(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void singleArgumentConstructor(){
        new SyntaxHighlighting(jsonText);
    }

    @Test(expected = IllegalArgumentException.class)
    public void badParametersToConstructorWithMultiplyArguments(){
        new SyntaxHighlighting(jsonText, 0, 0);
    }

    @Test
    public void highlightJSON(){
        SyntaxHighlighting html = new SyntaxHighlighting(jsonText, 1, jsonText.length());
        assertEquals(jsonRequestSynHighlighted, html.highlightJSON());
    }
}