package hipchat4j;

import hipchat4j.connector.Connector;
import hipchat4j.connector.ConnectorAbstract;
import hipchat4j.entities.Emoticon;
import hipchat4j.entities.EmoticonListPage;
import hipchat4j.json.JsonParser;

import java.util.List;

/**
 * hipchat4j
 * 09/10/14/20:10
 */
public class Emoticons {

    private final ConnectorAbstract connector;

    public Emoticons(ConnectorAbstract c)
    {
        connector = c;
    }


    public Emoticon getEmoticon(int id)
    {
        String jsonback = connector.get("/v2/emoticon/" + id);
        return JsonParser.getInstance().fromJson(jsonback, Emoticon.class);
    }

    public List<Emoticon> getEmoticons()
    {
      String jsonback = connector.get("/v2/emoticon");
      EmoticonListPage p = JsonParser.getInstance().fromJson(jsonback, EmoticonListPage.class);
      return null;
    }

}
