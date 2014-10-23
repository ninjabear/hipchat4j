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

    public RoomListPage getRooms(int start, int end, boolean includeArchived) {
       return JsonParser.getInstance().fromJson(connector.get("/v2/room/?start-index="+start+"&max-results="+end+"&include-archived="+Boolean.valueOf(includeArchived).toString()),
                                                RoomListPage.class);
    }

    public List<Room> getRooms(boolean includeArchived){

        final int blocksize = 500;

        RoomListPage running = getRooms(0, blocksize, includeArchived);
        List<Room> rooms = new ArrayList<>();

        do {

            rooms.addAll(convertToRooms(running));

        } while (running.getItems().size()==blocksize&&running.getLinks().getNext()!=null);

        return rooms;
    }

    private static List<Room> convertToRooms(RoomListPage rlp)
    {
       return new ArrayList<>();
    }


}
