package hipchat4j.entities;

import hipchat4j.config.Config;
import hipchat4j.connector.ConnectorMock;
import hipchat4j.json.JsonParser;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WebhookTest {

    private Webhook wh;

    @Before
    public void setUp() throws Exception {
        Webhook.RoomInfo r = new Webhook.RoomInfo(123, new Webhook.RoomInfo.Links("self", "hook", "member", "part"), "name");
        User u = new User("mention", 555, new User.Links("self"), "nombre");

        wh = new Webhook(r,u, "testurl", "**", "a long time ago in a galaxy far far away", "room_exit", "nombre hook");
    }

    @Test
    public void testGetRoom() throws Exception {
        assertNotNull(wh.getRoom());
        Webhook.RoomInfo ri = wh.getRoom();
        assertEquals(123, ri.getId());
        assertEquals("name", ri.getName());
        assertEquals("self", ri.getLinks().getSelf());
        assertEquals("hook", ri.getLinks().getWebhooks());
        assertEquals("member", ri.getLinks().getMembers());
        assertEquals("part", ri.getLinks().getParticipants());
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
        String json = IOUtils.toString(this.getClass().getResourceAsStream("/webhook_full_expected.json"));
        Webhook h = JsonParser.getInstance().fromJson(json, Webhook.class);

        assertNotNull(h.getRoom());
        Webhook.RoomInfo ri = h.getRoom();
        assertEquals(999, ri.getId());
        assertEquals("aroom", ri.getName());
        assertEquals("self", ri.getLinks().getSelf());
        assertEquals("webhooks", ri.getLinks().getWebhooks());
        assertEquals("members", ri.getLinks().getMembers());
        assertEquals("participants", ri.getLinks().getParticipants());

        assertNotNull(h.getCreator());
        assertEquals("http://yes", h.getUrl());
        assertEquals(".*", h.getPattern());
        assertEquals("vader", h.getCreated());
        assertEquals("room_enter", h.getEvent());
        assertEquals("my hooks", h.getName());
    }
}