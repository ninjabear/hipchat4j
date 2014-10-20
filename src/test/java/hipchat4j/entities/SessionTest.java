package hipchat4j.entities;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SessionTest {

    private Session exampleSession;

    @Before
    public void setUp() throws Exception {

        Session.Client.Room r = new Session.Client.Room(55, "AROOM", new Session.Client.Room.Links("self", "webhooks", "members", "participants"));
        Session.Client c = new Session.Client(r, "clientid", Arrays.asList("12","13"), "clientname");
        Session.Owner o = new Session.Owner("mentionanme", 32, "a realname", new Session.Owner.Links("self"));

        exampleSession = new Session(c, "token", Arrays.asList("scope1", "scope2"), 123, o, "type");
    }

    @Test
    public void testGetScopes() throws Exception {
        List<String> savedScopes = exampleSession.getScopes();
        assertEquals("scope1", savedScopes.get(0));
        assertEquals("scope2", savedScopes.get(1));
    }

    @Test
    public void testGetAccessToken() throws Exception {
        assertEquals("token", exampleSession.getAccessToken());
    }

    @Test
    public void testGetExpiresIn() throws Exception {
        assertEquals(123, exampleSession.getExpiresIn());
    }

    @Test
    public void testGetClient() throws Exception {
        Session.Client client = exampleSession.getClient();
        Session.Client.Room room = client.getRoom();

        assertNotNull(client);
        assertNotNull(room);

        assertEquals("clientid", client.getId());
        assertEquals(2, client.getAllowedScopes().size());
        assertEquals("12", client.getAllowedScopes().get(0));
        assertEquals("13", client.getAllowedScopes().get(1));
        assertEquals("clientname", client.getName());

        assertEquals(55, room.getId());
        assertEquals("AROOM", room.getName());

        assertNotNull(room.getLinks());
        assertEquals("self", room.getLinks().getSelf());
        assertEquals("members", room.getLinks().getMembers());
        assertEquals("participants", room.getLinks().getParticipants());
        assertEquals("webhooks", room.getLinks().getWebhooks());
    }


    @Test
    public void testGetOwner() throws Exception {
        Session.Owner owner = exampleSession.getOwner();

        assertNotNull(owner);
        assertEquals("mentionanme", owner.getMentionName());
        assertEquals(32, owner.getId());
        assertEquals("a realname", owner.getName());

        assertNotNull(owner.getLinks());
        assertEquals("self", owner.getLinks().getSelf());
    }

    @Test
    public void testGetOwnerType() throws Exception {
       assertEquals("type", exampleSession.getOwnerType());
    }
}