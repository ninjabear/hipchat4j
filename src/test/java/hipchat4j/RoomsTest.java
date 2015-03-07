package hipchat4j;

import hipchat4j.config.Config;
import hipchat4j.connector.ConnectorMock;
import hipchat4j.entities.*;
import hipchat4j.json.JsonParser;
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
        cm.addResponseMapping("/v2/room/something/history/else", "200", IOUtils.toString(this.getClass().getResourceAsStream("/message_style1.json")));
        cm.addResponseMapping("/v2/room/room/history/latest", "200", IOUtils.toString(this.getClass().getResourceAsStream("/message_history_sample.json")));
        cm.addResponseMapping("/v2/room/abc/history/?start-index=0&max-results=100&reverse=true", "200", IOUtils.toString(this.getClass().getResourceAsStream("/message_history_sample.json")));
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
        assertNotEquals(0, roomList.size());
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
    public void createRoomShort() throws Exception {
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
    public void createRoomFull() throws Exception {
        cm.addResponseMapping("/v2/room", "200", IOUtils.toString(this.getClass().getResourceAsStream("/create_room_response.json")));

        String resp = rooms.createRoom("topic", true, "aroom", "123", "private");
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
        rooms.getRecentHistory("room");
        assertEquals("/v2/room/room/history/latest/", cm.getLastGetRequest());
        rooms.getRecentHistory("room", null, null);
        assertEquals("/v2/room/room/history/latest/", cm.getLastGetRequest());

        rooms.getRecentHistory("room", 99, null);
        assertEquals("/v2/room/room/history/latest/?max-results=99", cm.getLastGetRequest());

        rooms.getRecentHistory("room", null, "abc");
        assertEquals("/v2/room/room/history/latest/?not-before=abc", cm.getLastGetRequest());

        rooms.getRecentHistory("room", 99, "abc");
        assertEquals("/v2/room/room/history/latest/?max-results=99&not-before=abc", cm.getLastGetRequest());
    }

    @Test
    public void testGetHistory() throws Exception {
        MessageHistoryPage hist = rooms.getHistory("abc");
        assertEquals("/v2/room/abc/history/?start-index=0&max-results=100&reverse=true", cm.getLastGetRequest());
        assertNotNull(hist);

        rooms.getHistory("abc", null, null, null, true);
        assertEquals("/v2/room/abc/history/?start-index=0&max-results=100&reverse=true", cm.getLastGetRequest());

        rooms.getHistory("abc", null, null, null, false);
        assertEquals("/v2/room/abc/history/?start-index=0&max-results=100&reverse=false", cm.getLastGetRequest());

        rooms.getHistory("abc", null, null, 999, true);
        assertEquals("/v2/room/abc/history/?start-index=0&max-results=999&reverse=true", cm.getLastGetRequest());

        rooms.getHistory("abc", null, 999, null, true);
        assertEquals("/v2/room/abc/history/?start-index=999&max-results=100&reverse=true", cm.getLastGetRequest());

        rooms.getHistory("abc", "isodatehere", null, null, true);
        assertEquals("/v2/room/abc/history/?start-index=0&max-results=100&reverse=true&date=isodatehere", cm.getLastGetRequest());

        rooms.getHistory("abc", "isodatehere", 23, 55, false);
        assertEquals("/v2/room/abc/history/?start-index=23&max-results=55&reverse=false&date=isodatehere", cm.getLastGetRequest());
    }

    @Test
    public void testGetMessage() throws Exception {
        MessagePayload payload = rooms.getMessage("something", "else");
        assertEquals("/v2/room/something/history/else", cm.getLastGetRequest());
        assertNotNull(payload);
    }

    @Test
    public void testInviteUserToRoom() throws Exception {
        rooms.inviteUserToRoom("cool", "ninja", "please");
        assertEquals("/v2/room/cool/invite/ninja", cm.getLastPostRequest());
        InviteToRoomRequest sent = JsonParser.getInstance().fromJson(cm.getLastPostParam(), InviteToRoomRequest.class);
        assertEquals("please", sent.getReason());

        rooms.inviteUserToRoom("cool", "ninjab");
        assertEquals("/v2/room/cool/invite/ninjab", cm.getLastPostRequest());
        InviteToRoomRequest sentNoReason = JsonParser.getInstance().fromJson(cm.getLastPostParam(), InviteToRoomRequest.class);
        assertEquals("", sentNoReason.getReason());
    }

    @Test
    public void testAddMemberToRoom() throws Exception {
        rooms.addMemberToRoom("aroom","auser");
        assertEquals("/v2/room/aroom/member/auser", cm.getLastPutRequest());
        assertNull(cm.getLastPutParam());
    }

    @Test
    public void testDeleteMemberFromRoom() throws Exception {
        rooms.deleteMemberFromRoom("aroom", "auser");
        assertEquals("/v2/room/aroom/member/auser", cm.getLastDeleteRequest());
    }

    @Test
    public void testGetRoomMembers() throws Exception {
        rooms.getMembersInRoom("aroom");
        assertEquals("/v2/room/aroom/member", cm.getLastGetRequest());

        rooms.getMembersInRoom("aroom", 30, 1000);
        assertEquals("/v2/room/aroom/member?start-index=30&max-results=1000", cm.getLastGetRequest());
    }

    @Test
    public void testSendRoomNotification() throws Exception {

        rooms.sendRoomNotification("myroom", "red", "hello world", true, "html");
        assertEquals("/v2/room/myroom/notification", cm.getLastPostRequest());
        assertNotNull(cm.getLastPostParam());

        NotificationRequest nr = new NotificationRequest("red", "hello world", true, "html");
        NotificationRequest sentReq = JsonParser.getInstance().fromJson(cm.getLastPostParam(), NotificationRequest.class);

        assertEquals(nr.getMessage(), sentReq.getMessage());
        assertEquals(nr.getMessageFormat(), sentReq.getMessageFormat());
        assertEquals(nr.getColor(), sentReq.getColor());
        assertEquals(nr.isNotify(), sentReq.isNotify());
        
    }


}