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


    public void getRecentHistory(String room) {
        getRecentHistory(room, null, null);
    }

    public MessageHistory getRecentHistory(String room, Integer maxResults, String notBefore) {
        String qry = "/v2/room/"+room+"/history/latest/";

        if (maxResults!=null)
            qry += "?max-results="+maxResults;

        if (notBefore!=null)
        {
            if (maxResults!=null)
            {
                qry += "&not-before="+notBefore;
            } else {
                qry += "?not-before="+notBefore;
            }

        }

        String resp = connector.get(qry);
        return JsonParser.getInstance().fromJson(resp, MessageHistory.class);
    }

    public MessageHistory getHistory(String room)
    {
        return getHistory(room, null, null, null, true);
    }

    public MessageHistory getHistory(String room, String fromDate, Integer startIndex, Integer maxResults, boolean reverse)
    {
         int defaultedStartIndex = startIndex==null?0:startIndex,
                 defaultedMaxResults = maxResults==null?100:maxResults;

        String qry = "/v2/room/"+room+"/history/?start-index="+defaultedStartIndex+"&max-results="+defaultedMaxResults+"&reverse="+(reverse?"true":"false");

        if (fromDate!=null)
            qry += "&date="+fromDate;

        return JsonParser.getInstance().fromJson(connector.get(qry), MessageHistory.class);
    }

    public MessagePayload getMessage(String room, String messageId)
    {
        String resp = connector.get("/v2/room/"+room+"/history/"+messageId);
        MessagePayload payload = JsonParser.getInstance().fromJson(resp, Message.class).getMessagePayload();
        return payload;
    }

}
