package hipchat4j;

import hipchat4j.config.Config;
import hipchat4j.connector.ConnectorMock;
import hipchat4j.entities.Room;
import hipchat4j.entities.RoomListPage;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

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
        cm.addResponseMapping("/v2/room/?start-index=0&max-results=500&include-archived=true", "200", IOUtils.toString(this.getClass().getResourceAsStream("/getrooms_500_rooms_fullpage.json")));
        cm.addResponseMapping("/v2/room/?start-index=500&max-results=500&include-archived=true", "200", IOUtils.toString(this.getClass().getResourceAsStream("/getrooms_expected_output_pg2.json")));

        List<Room> roomList = rooms.getRooms(true);
        assertEquals("/v2/room/?start-index=500&max-results=500&include-archived=true", cm.getLastGetRequest()); // page 2

        assertNotNull(roomList);
        assertNotEquals(0,  roomList.size() );
        assertEquals(1, roomList.get(0).getId());
        assertEquals(501, roomList.size());
    }

    @Test
    public void getRoomPage() throws Exception {

        cm.addResponseMapping("/v2/room/?start-index=0&max-results=500&include-archived=false", "200", IOUtils.toString(this.getClass().getResourceAsStream("/getrooms_500_rooms_fullpage.json")));
        RoomListPage rlp = rooms.getRooms(0, 500, false);
        assertNotNull(rlp);
        assertEquals(500, rlp.getItems().size());
        assertNotEquals("/v2/room/?start-index=500&max-results=500&include-archived=true", cm.getLastGetRequest()); // don't get the next page

    }

    @Test
    public void createRoomShort()
    {
        int resp = rooms.createRoom("aname");
        assertTrue(resp>=0);
        assertEquals("/v2/room", cm.getLastGetRequest());
    }

    @Test
    public void createRoomFull()
    {
        int resp = rooms.createRoom("topic", true, "aroom", "123", "private" );
        assertTrue(resp>=0);
        assertEquals("/v2/room", cm.getLastGetRequest());
    }

}