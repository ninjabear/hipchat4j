package hipchat4j;

import hipchat4j.config.Config;
import hipchat4j.connector.ConnectorMock;
import hipchat4j.entities.Room;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoomsTest {

    private Rooms rooms;
    private ConnectorMock cm;

    @Before
    public void setUp() throws Exception {
        cm = new ConnectorMock(new Config("hello", "world"));
        rooms = new Rooms(cm);
        cm.addResponseMapping("/v2/room/abc", "200", IOUtils.toString(this.getClass().getResourceAsStream("/room_full.json")));
        cm.addResponseMapping("/v2/room/123", "200", IOUtils.toString(this.getClass().getResourceAsStream("/room_full.json")));
    }

    @Test
    public void testGetRoom() throws Exception {
        Room r = rooms.getRoom("abc");
        assertNotNull(r);
        assertEquals("/v2/room/abc", cm.getLastGetRequest());
        assertEquals("roomname", r.getName());
    }

    @Test
    public void testGetRoomInt() throws Exception {
        Room r = rooms.getRoom(123);
        assertNotNull(r);
        assertEquals("/v2/room/123", cm.getLastGetRequest());
        assertEquals("roomname", r.getName());
    }

    @Test
    public void getRooms() throws Exception {

        assertNotNull(rooms.getRooms(true));
        assertNotEquals(0,  rooms.getRooms(true) );
    }
}