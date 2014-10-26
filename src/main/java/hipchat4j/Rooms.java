package hipchat4j;

import hipchat4j.connector.ConnectorAbstract;
import hipchat4j.entities.*;
import hipchat4j.json.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * hipchat4j
 * 09/10/14/20:09
 */
public class Rooms {

    private final ConnectorAbstract connector;

    public Rooms(ConnectorAbstract c) {
        connector = c;
    }

    public Room getRoom(int id)
    {
        return getRoom(""+id);
    }

    public Room getRoom(String name) {
        String resp = connector.get("/v2/room/"+name);
        return JsonParser.getInstance().fromJson(resp, Room.class);
    }

    public RoomListPage getRooms(int start, int size, boolean includeArchived) {
       return JsonParser.getInstance().fromJson(connector.get("/v2/room/?start-index="+start+"&max-results="+size+"&include-archived="+Boolean.valueOf(includeArchived).toString()),
                                                RoomListPage.class);
    }

    public List<Room> getRooms(boolean includeArchived){

        final int blocksize = 500;
        int currentpage=0;
        RoomListPage running;
        List<Room> rooms = new ArrayList<>();

        do {
            running = getRooms(currentpage*blocksize, blocksize, includeArchived);
            rooms.addAll(convertToRooms(running));
            currentpage+=1;
        } while (running.getItems().size()==blocksize);

        return rooms;
    }

    private List<Room> convertToRooms(RoomListPage rlp)
    {
        ArrayList<Room> rooms = new ArrayList<>();

        for (RoomListPage.Item i : rlp.getItems())
        {
            Room room = new Room(i.getId(), connector);
            rooms.add(room);
        }

        return rooms;

    }

    public String createRoom(String topic, boolean guestAccessPermitted, String name, String ownerUserId, String privacyMode)
    {
        RoomCreateRequest roomCreateRequest;
        roomCreateRequest = new RoomCreateRequest(topic, guestAccessPermitted, name, ownerUserId, privacyMode);
        String postRequest = JsonParser.getInstance().toJson(roomCreateRequest);
        String resp = connector.post("/v2/room", postRequest);
        RoomCreateResponse response = JsonParser.getInstance().fromJson(resp, RoomCreateResponse.class);
        return response.getId();
    }

    public String createRoom(String name) {
        return createRoom(null, false, name, null, "public");
    }


    public void updateRoom(int id, String name, String privacyMode, boolean isArchived, boolean isGuestAccessible, String topic, String ownerId)
    {
        updateRoom(""+id, name, privacyMode, isArchived, isGuestAccessible, topic, ownerId);
    }

    public void updateRoom(String room, String name, String privacyMode, boolean isArchived, boolean isGuestAccessible, String topic, String ownerId)
    {
        RoomUpdateRequest r = new RoomUpdateRequest(name, topic, privacyMode, isArchived, isGuestAccessible, ownerId);
       connector.put("/v2/room/"+room, JsonParser.getInstance().toJson(r));
    }

    public void deleteRoom(int id)
    {
        deleteRoom(""+id);
    }

    public void deleteRoom(String room)
    {
        connector.delete("/v2/room/"+room);
    }

}
