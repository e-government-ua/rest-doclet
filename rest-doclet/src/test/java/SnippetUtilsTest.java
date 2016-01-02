import org.calrissian.restdoclet.model.method.description.Snippet;
import org.junit.Test;

import java.util.List;

import static org.calrissian.restdoclet.util.SnippetUtils.description;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author dgroup
 * @since  31.12.2015
 */
public class SnippetUtilsTest {
    private String request =
        "````json" +
        " {\n" +
        "        \"bArea\": true,\n" +
        "        \"bRoot\": true,\n" +
        "        \"nID\": 1,\n" +
        "        \"sName\": \"Область\",\n" +
        "        \"nOrder\": null\n" +
        " }" +
        "`";

    private String response =
        "````json" +
        "{\n" +
        "    \"code\": \"OK\",\n" +
        "    \"message\": \"Entity was found\"\n" +
        "}" +
        "`";

    private String comment =
        "This is example of request " + request + " and response was " + response;

    @Test
    public void parse(){
        int amountOfLines = 4;
        List<Snippet> lines = description(comment);

        assertNotNull("Lines is not empty", lines);
        assertTrue("There are 4 lines in comment", amountOfLines == lines.size());
    }
}
