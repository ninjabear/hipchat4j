package hipchat4j.entities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkShareRequestTest {

    private LinkShareRequest l;

    @Before
    public void setUp() throws Exception {
        l = new LinkShareRequest("message", "link");
    }

    @Test
    public void testGetMessage() throws Exception {
        assertEquals("message", l.getMessage());
    }

    @Test
    public void testGetLink() throws Exception {
        assertEquals("link", l.getLink());
    }
}