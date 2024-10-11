import az.arvilo.PlayFair;
import az.arvilo.exception.EdgeCaseException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlayFairTest {

    @Test
    public void testPlayFairEncoding() {
        PlayFair playFair = new PlayFair();
        playFair.setKey("PLAYFAIR");

        try {
            assertEquals("RSKZEZUEBYNDQO", playFair.encode("COMMUNICATION"));
            assertEquals("UZUZ", playFair.encode("ZZ"));
            assertEquals("", playFair.encode(""));
            assertEquals("IENUVZ", playFair.encode("pienu"));
            assertThrows(EdgeCaseException.class, () -> playFair.encode("xxxx"));
            assertEquals("FQCU", playFair.encode("ati"));
            assertEquals("FQCU", playFair.encode("atj"));
            assertEquals("FQCESY", playFair.encode("atjkk"));
            assertEquals("FQNCSYSY", playFair.encode("atsjkk"));
            assertEquals("CYCY", playFair.encode("yy"));
            assertEquals("", playFair.encode("123(("));
        } catch (EdgeCaseException e) {
            throw new RuntimeException(e);
        }
    }


}

