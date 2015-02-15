package hipchat4j.entities;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InviteToRoomRequestTest {

    private InviteToRoomRequest i;

    @Before
    public void setUp() throws Exception {
        i = new InviteToRoomRequest("please come");
    }

    @Test
    public void testGetReason() throws Exception {
        assertEquals("please come", i.getReason());
    }

    @Test
    public void testFromJson() throws Exception {
        String sampleJson = IOUtils.toString(this.getClass().getResourceAsStream("/invite_request.json"));
    }
}