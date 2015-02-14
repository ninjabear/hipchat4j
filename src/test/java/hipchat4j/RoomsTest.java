package hipchat4j;

import hipchat4j.config.Config;
import hipchat4j.connector.ConnectorMock;
import hipchat4j.entities.Room;
import hipchat4j.entities.RoomCreateRequest;
import hipchat4j.entities.RoomListPage;
import hipchat4j.entities.RoomUpdateRequest;
import hipchat4j.json.JsonParser;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

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
    public void createRoomShort() throws Exception
    {
        String jsonResponse = IOUtils.toString(this.getClass().getResourceAsStream("/create_room_response.json"));
        cm.addResponseMapping("/v2/room", "200", jsonResponse);
        String resp = rooms.createRoom("aname");

        assertEquals("/v2/room", cm.getLastPostRequest());
        assertEquals("123", resp);
        RoomCreateRequest roomRequest = JsonParser.getInstance().fromJson(cm.getLastPostParam(), RoomCreateRequest.class);

        assertNull(roomRequest.getTopic());
        assertFalse(roomRequest.getIsGuestAccess());
        assertNull(roomRequest.getOwnerUserId());
        assertEquals("public", roomRequest.getPrivacyMode());
        assertEquals("aname", roomRequest.getName());
    }

    @Test
    public void createRoomFull() throws Exception
    {
        cm.addResponseMapping("/v2/room", "200", IOUtils.toString(this.getClass().getResourceAsStream("/create_room_response.json")));

         String resp = rooms.createRoom("topic", true, "aroom", "123", "private" );
         assertEquals("/v2/room", cm.getLastPostRequest());

        assertEquals("123", resp);
        RoomCreateRequest roomRequest = JsonParser.getInstance().fromJson(cm.getLastPostParam(), RoomCreateRequest.class);

        assertEquals("topic", roomRequest.getTopic());
        assertEquals(true, roomRequest.getIsGuestAccess());
        assertEquals("123", roomRequest.getOwnerUserId());
        assertEquals("private", roomRequest.getPrivacyMode());
        assertEquals("aroom", roomRequest.getName());

    }

    @Test
    public void updateRoomInt() throws Exception {
        rooms.updateRoom(123, "myname", "private", false, false, "atopic", "555");
        assertEquals("/v2/room/123", cm.getLastPutRequest());

        String sentRequest = cm.getLastPutParam();
        RoomUpdateRequest roomRequest = JsonParser.getInstance().fromJson(sentRequest, RoomUpdateRequest.class);
        assertEquals("555", roomRequest.getOwnerId());
        assertEquals("myname", roomRequest.getName());
        assertEquals("atopic", roomRequest.getTopic());
        assertEquals("private", roomRequest.getPrivacy());
        assertEquals(false, roomRequest.isArchived());
        assertEquals(false, roomRequest.isGuestAccessible());
    }

    @Test
    public void updateRoom() throws Exception {
        rooms.updateRoom("123", "myname", "private", false, false, "atopic", "555");
        assertEquals("/v2/room/123", cm.getLastPutRequest());

        String sentRequest = cm.getLastPutParam();
        RoomUpdateRequest roomRequest = JsonParser.getInstance().fromJson(sentRequest, RoomUpdateRequest.class);
        assertEquals("555", roomRequest.getOwnerId());
        assertEquals("myname", roomRequest.getName());
        assertEquals("atopic", roomRequest.getTopic());
        assertEquals("private", roomRequest.getPrivacy());
        assertEquals(false, roomRequest.isArchived());
        assertEquals(false, roomRequest.isGuestAccessible());
    }

    @Test
    public void deleteRoomInt() throws Exception {
        rooms.deleteRoom(123);
        assertEquals("/v2/room/123", cm.getLastDeleteRequest());
    }

    @Test
    public void deleteRoom() throws Exception {
        rooms.deleteRoom("123");
        assertEquals("/v2/room/123", cm.getLastDeleteRequest());
    }

    @Test
    public void testGetRecentHistory() throws Exception {
        rooms.getRecentHistory("latest");
    }

    @Test
    public void testGetHistory() throws Exception {
        rooms.getHistory("abc");
    }

    @Test
    public void testGetMessage() throws Exception {
        rooms.getMessage("something", "else");
    }
}