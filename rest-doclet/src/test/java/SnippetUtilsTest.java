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
        "        \"bArea\": true,\n" +
        "        \"bRoot\": true,\n" +
        "        \"nID\": 1,\n" +
        "        \"sName\": \"Область\",\n" +
        "        \"nOrder\": null\n" +
        " }";
    String jsonResponse =
        " {\n" +
        "    \"code\": \"OK\",\n" +
        "    \"message\": \"Entity was found\"\n" +
        " }";
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
        assertEquals(commentOne,    comments.get(0).content());
        assertEquals(jsonRequest,   comments.get(1).content());
        assertEquals(commentTwo,    comments.get(2).content());
        assertEquals(jsonResponse,  comments.get(3).content());
    }
}