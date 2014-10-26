package hipchat4j.entities;

import hipchat4j.json.JsonParser;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoomCreateResponseTest {

    RoomCreateResponse rcr;

    @Before
    public void setUp() throws Exception {
        rcr = new RoomCreateResponse("134", new RoomCreateResponse.Links("self"));
    }

    @Test
    public void testGetLinks() throws Exception {
        assertEquals("self", rcr.getLinks().getSelf());
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals("134", rcr.getId());
    }

    @Test
    public void testFromJSON() throws Exception {
        RoomCreateResponse rc = JsonParser.getInstance().fromJson(IOUtils.toString(this.getClass().getResourceAsStream("/create_room_response.json")), RoomCreateResponse.class);
        assertNotNull(rc);
        assertEquals("self",rc.getLinks().getSelf());
        assertEquals("123", rc.getId());
    }
}