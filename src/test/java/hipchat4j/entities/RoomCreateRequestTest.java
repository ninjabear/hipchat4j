package hipchat4j.entities;

import hipchat4j.json.JsonParser;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RoomCreateRequestTest {

    private RoomCreateRequest rcm;

    @Before
    public void setUp() throws Exception {
        rcm = new RoomCreateRequest("topic", true, "name", "owner", "private");
    }

    @Test
    public void testGetTopic() throws Exception {
        assertEquals("topic", rcm.getTopic());
    }

    @Test
    public void testGetIsGuestAccess() throws Exception {
        assertEquals(true, rcm.getIsGuestAccess().booleanValue());
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("name", rcm.getName());
    }

    @Test
    public void testGetOwnerUserId() throws Exception {
        assertEquals("owner", rcm.getOwnerUserId());
    }

    @Test
    public void testGetPrivacyMode() throws Exception {
        assertEquals("private", rcm.getPrivacyMode());
    }

    @Test
    public void testFromExpectedJSON() throws Exception {
        RoomCreateRequest rcm = JsonParser.getInstance().fromJson(IOUtils.toString(this.getClass().getResourceAsStream("/create_room_request.json")), RoomCreateRequest.class);
        assertNotNull(rcm);
        assertEquals("topic", rcm.getTopic());
        assertEquals("name", rcm.getName());
        assertEquals("public", rcm.getPrivacyMode());
        assertEquals(true, rcm.getIsGuestAccess().booleanValue());
        assertEquals("123", rcm.getOwnerUserId());
    }
}