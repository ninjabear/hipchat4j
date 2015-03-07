package hipchat4j;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FileShareMessageRequestTest {

    FileShareMessageRequest m;

    @Before
    public void setUp() throws Exception {
        m = new FileShareMessageRequest("messagesss");
    }

    @Test
    public void testGetMessage() throws Exception {
        assertEquals( "messagesss", m.getMessage() );
    }
}