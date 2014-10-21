package hipchat4j;

import hipchat4j.connector.ConnectorAbstract;
import hipchat4j.entities.Room;
import hipchat4j.json.JsonParser;

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


}
