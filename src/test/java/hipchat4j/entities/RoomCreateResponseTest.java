package hipchat4j.entities;

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
}