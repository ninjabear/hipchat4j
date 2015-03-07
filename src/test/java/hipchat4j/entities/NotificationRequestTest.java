package hipchat4j.entities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotificationRequestTest {

    NotificationRequest nr;

    @Before
    public void setUp() throws Exception {
        nr = new NotificationRequest("yellow", "hello", true, "html");
    }

    @Test
    public void testGetColor() throws Exception {
        assertEquals("yellow", nr.getColor());
    }

    @Test
    public void testGetMessage() throws Exception {
        assertEquals("hello", nr.getMessage());
    }

    @Test
    public void testIsNotify() throws Exception {
        assertEquals(true, nr.isNotify());
    }

    @Test
    public void testGetMessageFormat() throws Exception {
        assertEquals("html", nr.getMessageFormat());
    }
}