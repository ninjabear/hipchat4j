package hipchat4j.entities;

import hipchat4j.json.JsonParser;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class RoomTest {

    private Room r;

    @Before
    public void setUp() throws Exception {
       r = new Room("xmppjid",
                    new Room.Statistics(new Room.Statistics.Links("stats_self")),
                    new Room.Links("roomself","roomwebhooks", "roommembers", "roomparticipants"),
                    "roomname",
                    false,
                    "createdstr",
                    "privacystr",
                    false,
                    "atopic",
                    Arrays.asList(new Room.Participant("ninjabear", 99, new Room.Participant.Links("selfpart"), "fullname")),
                    new Room.Owner("ninja", 13, new Room.Owner.Links("self"), "ninja test"),
                    555,
                    "http://somewhere/guest"
                   );
    }

    @Test
    public void testGetXmppJid() throws Exception {
        assertEquals("xmppjid", r.getXmppJid());
    }

    @Test
    public void testGetStatistics() throws Exception {
        Room.Statistics statslinks = r.getStatistics();
        assertNotNull(statslinks.getLinks());
        assertEquals("stats_self", statslinks.getLinks().getSelf());
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("roomname", r.getName());
    }

    @Test
    public void testGetLinks() throws Exception {
        Room.Links links = r.getLinks();
        assertNotNull(links);
        assertEquals("roommembers", links.getMembers());
        assertEquals("roomself", links.getSelf());
        assertEquals("roomwebhooks", links.getWebhooks());
        assertEquals("roomparticipants", links.getParticipants());

    }

    @Test
    public void testGetCreated() throws Exception {
        assertEquals("createdstr", r.getCreated());
    }

    @Test
    public void testIsArchived() throws Exception {
        assertEquals(false, r.isArchived());
    }

    @Test
    public void testGetPrivacy() throws Exception {
        assertEquals("privacystr", r.getPrivacy());
    }

    @Test
    public void testGetIsGuestAccessible() throws Exception {
        assertEquals(false, r.getIsGuestAccessible());
    }

    @Test
    public void testGetTopic() throws Exception {
        assertEquals("atopic", r.getTopic());
    }

    @Test
    public void testGetParticipants() throws Exception {
        List<Room.Participant> participants = r.getParticipants();
        assertEquals(1, participants.size());
        assertEquals(99, participants.get(0).getId());
        assertEquals("fullname", participants.get(0).getName());
        assertEquals("ninjabear", participants.get(0).getMentionName());
        assertEquals("selfpart", participants.get(0).getLinks().getSelf());
    }

    @Test
    public void testGetOwner() throws Exception {
        Room.Owner owner = r.getOwner();
        assertNotNull(owner);
        assertEquals(13, owner.getId());
        assertEquals("ninja", owner.getMentionName());
        assertEquals("ninja test", owner.getName());
        assertEquals("self", owner.getLinks().getSelf());
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals(555, r.getId());
    }

    @Test
    public void testGetGuestAccessURL() throws Exception {
        assertEquals("http://somewhere/guest", r.getGuestAccessURL());
    }

    @Test
    public void testFromJson() throws Exception {

        Room r = JsonParser.getInstance().fromJson(IOUtils.toString(this.getClass().getResourceAsStream("/room_full.json")), Room.class);

        assertNotNull(r);

        assertEquals("xmpp_jid", r.getXmppJid());
        assertEquals("statslinksself", r.getStatistics().getLinks().getSelf());
        assertEquals("roomname", r.getName());
        assertEquals("roomself", r.getLinks().getSelf());
        assertEquals("roomwebhooks", r.getLinks().getWebhooks());
        assertEquals("roommembers", r.getLinks().getMembers());
        assertEquals("roomparticipants", r.getLinks().getParticipants());
        assertEquals("createdstamp", r.getCreated());
        assertEquals(true, r.isArchived());
        assertEquals("privacy", r.getPrivacy());
        assertEquals(true, r.getIsGuestAccessible());
        assertEquals("atopic", r.getTopic());

        assertNotNull(r.getParticipants());
        assertEquals(2, r.getParticipants().size());

        assertEquals("mention", r.getParticipants().get(0).getMentionName());
        assertEquals(134, r.getParticipants().get(0).getId());
        assertEquals("selfpart1", r.getParticipants().get(0).getLinks().getSelf());
        assertEquals("mentionfull", r.getParticipants().get(0).getName());

        assertEquals("mention2", r.getParticipants().get(1).getMentionName());
        assertEquals(135, r.getParticipants().get(1).getId());
        assertEquals("selfpart2", r.getParticipants().get(1).getLinks().getSelf());
        assertEquals("mentionfull2", r.getParticipants().get(1).getName());

        assertNotNull(r.getOwner());
        assertEquals("owner", r.getOwner().getMentionName());
        assertEquals(55, r.getOwner().getId());
        assertEquals("ownerself", r.getOwner().getLinks().getSelf());
        assertEquals("ownerfull", r.getOwner().getName());

        assertEquals(999, r.getId());
        assertEquals("http://test/guest", r.getGuestAccessURL());

    }


}