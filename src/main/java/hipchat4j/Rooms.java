package hipchat4j;

import hipchat4j.connector.ConnectorAbstract;
import hipchat4j.entities.Room;
import hipchat4j.entities.RoomListPage;
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

    public int createRoom(String topic, boolean guestAccessPermitted, String name, String ownerUserId, String privacyMode)
    {
        return -1;
    }

    public int createRoom(String name) {
        return createRoom(null, false, name, null, "public");
    }


}
