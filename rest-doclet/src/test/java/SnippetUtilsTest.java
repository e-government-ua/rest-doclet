import org.calrissian.restdoclet.model.method.description.Snippet;
import org.junit.Test;

import java.util.List;

import static org.calrissian.restdoclet.util.SnippetUtils.description;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author dgroup
 * @since  31.12.2015
 */
public class SnippetUtilsTest {
    String jsonHeader   = "````json";
    String jsonFooter   = "`";
    String jsonRequest  =
            " {\n" +
            "  \"book\": [\n" +
            "     {\n" +
            "      \"id\":\"01\",\n" +
            "      \"language\": \"Java\",\n" +
            "      \"edition\": \"third\",\n" +
            "      \"author\": \"Herbert Schildt\"\n" +
            "     },\n" +
            "   ]\n" +
            " }";
    String jsonResponse =
            " {\n" +
            "  \"bArea\": true,\n" +
            "  \"bRoot\": true,\n" +
            "  \"nID\": 1,\n" +
            "  \"sName\": \"Область\",\n" +
            "  \"nOrder\": null\n" +
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
    String jsonResponseSynHighlighted =
            " <pre style=\"background:rgba(238,238,238,0.92);color:#000\"> {\n" +
            "  <span style=\"color:#093\">\"bArea\"</span>: <span style=\"color:#9700cc\">true</span>,\n" +
            "  <span style=\"color:#093\">\"bRoot\"</span>: <span style=\"color:#9700cc\">true</span>,\n" +
            "  <span style=\"color:#093\">\"nID\"</span>: <span style=\"color:#06f\">1</span>,\n" +
            "  <span style=\"color:#093\">\"sName\"</span>: <span style=\"color:#093\">\"Область\"</span>,\n" +
            "  <span style=\"color:#093\">\"nOrder\"</span>: <span style=\"color:#9700cc\">null</span>\n" +
            " }</pre>";
    String request  = jsonHeader + jsonRequest + jsonFooter;
    String response = jsonHeader + jsonResponse + jsonFooter;

    String commentOne = "This is example of request ";
    String commentTwo = " and response was ";

    @Test
    public void parse(){
        int amountOfLines = 4;
        List<Snippet> comments = description(commentOne + request + commentTwo + response);

        assertNotNull("Lines is not empty", comments);
        assertTrue("There are 4 comments in text", amountOfLines == comments.size());
        assertEquals(commentOne,                    comments.get(0).content());
        assertEquals(jsonRequestSynHighlighted,     comments.get(1).content());
        assertEquals(commentTwo,                    comments.get(2).content());
        assertEquals(jsonResponseSynHighlighted,    comments.get(3).content());
    }
}