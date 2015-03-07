package hipchat4j.entities;

import hipchat4j.config.Config;
import hipchat4j.connector.ConnectorMock;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WebhookTest {

    private Webhook wh;

    @Before
    public void setUp() throws Exception {
        Room r = new Room(123,  new ConnectorMock(  new Config("abcdefg", "ahost") ) );
        User u = new User("mention", 555, new User.Links("self"), "nombre");

        wh = new Webhook(r,u, "testurl", "**", "a long time ago in a galaxy far far away", "room_exit", "nombre hook");

    }

    @Test
    public void testGetRoom() throws Exception {
        assertNotNull(wh.getRoom());
    }

    @Test
    public void testGetCreator() throws Exception {
        assertNotNull(wh.getCreator());
    }

    @Test
    public void testGetUrl() throws Exception {
        assertEquals("testurl", wh.getUrl());
    }

    @Test
    public void testGetPattern() throws Exception {
        assertEquals("**", wh.getPattern());
    }

    @Test
    public void testGetCreated() throws Exception {
        assertEquals("a long time ago in a galaxy far far away", wh.getCreated());
    }

    @Test
    public void testGetEvent() throws Exception {
        assertEquals("room_exit", wh.getEvent());
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("nombre hook", wh.getName());
    }

    @Test
    public void testWebhjookFromJson() throws Exception {
        fail("todo");
    }
}