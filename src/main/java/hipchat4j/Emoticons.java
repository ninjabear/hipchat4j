package hipchat4j;

import hipchat4j.connector.ConnectorAbstract;
import hipchat4j.entities.Emoticon;
import hipchat4j.entities.EmoticonListPage;
import hipchat4j.json.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * hipchat4j
 * 09/10/14/20:10
 */
public class Emoticons {

    private final ConnectorAbstract connector;

    public Emoticons(ConnectorAbstract c) {
        connector = c;
    }


    public Emoticon getEmoticon(int id) {
        String jsonback = connector.get("/v2/emoticon/" + id);
        return JsonParser.getInstance().fromJson(jsonback, Emoticon.class);
    }

    public List<Emoticon> getEmoticons() {
        String jsonback = connector.get("/v2/emoticon");
        List<Emoticon> emoticons = new ArrayList<>();
        EmoticonListPage p = JsonParser.getInstance().fromJson(jsonback, EmoticonListPage.class);

        while (p.getLinks().getNext() != null) {
            emoticons.addAll(parsePageForEmoticons(p));
            String nextPageJson = connector.get(p.getLinks().getNext());
            p = JsonParser.getInstance().fromJson(nextPageJson, EmoticonListPage.class);
        }

        return emoticons;
    }

    private List<Emoticon> parsePageForEmoticons(EmoticonListPage page)
    {
        final List<Emoticon> acc = new ArrayList<>();
        //(int id, String keyOrId, String shortcut, int width,  int height, String audioPath)
        for (EmoticonListPage.Item i : page.getItems())
        {
            Emoticon e = new Emoticon(i.getId(),
                    Integer.toString(i.getId()),
                                     connector);
            acc.add(e);
        }
        return acc;
    }

}
