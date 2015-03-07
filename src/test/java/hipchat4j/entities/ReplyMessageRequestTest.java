package hipchat4j.entities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReplyMessageRequestTest {

    ReplyMessageRequest req;

    @Before
    public void setUp() throws Exception {
        req = new ReplyMessageRequest("123", "lol");
    }

    @Test
    public void testGetParentMessageId() throws Exception {
        assertEquals("123", req.getParentMessageId());
    }

    @Test
    public void testGetMessage() throws Exception {
        assertEquals("lol", req.getMessage());
    }
}