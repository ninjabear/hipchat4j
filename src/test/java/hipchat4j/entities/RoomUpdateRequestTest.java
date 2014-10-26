package hipchat4j.entities;

import hipchat4j.json.JsonParser;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoomUpdateRequestTest {

    private RoomUpdateRequest r;

    @Before
    public void setUp() throws Exception {
        r = new RoomUpdateRequest("name", "topic", "public", true, true, "123");
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("name", r.getName());
    }

    @Test
    public void testGetPrivacy() throws Exception {
        assertEquals("public", r.getPrivacy());
    }

    @Test
    public void testIsArchived() throws Exception {
        assertEquals(true, r.isArchived());
    }

    @Test
    public void testIsGuestAccessible() throws Exception {
        assertEquals(true, r.isGuestAccessible());
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals("123", r.getOwnerId());
        r = new RoomUpdateRequest("name", "topic", "public", true, true, null);
        assertNull(r.getOwnerId());
    }

    @Test
    public void testGetTopic() throws Exception {
        assertEquals("topic", r.getTopic());
    }

    @Test
    public void testFromExpectedJSON() throws Exception {
        RoomUpdateRequest request = JsonParser.getInstance().fromJson(IOUtils.toString(this.getClass().getResourceAsStream("/roomupdaterequest_expected.json")), RoomUpdateRequest.class);
        assertEquals("name", request.getName());
        assertEquals("private", request.getPrivacy());
        assertEquals(false, request.isGuestAccessible());
        assertEquals(false, request.isArchived());
        assertEquals("mytopic", request.getTopic());
        assertEquals("555", request.getOwnerId());
    }
}