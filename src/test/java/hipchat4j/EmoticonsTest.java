package hipchat4j;

import hipchat4j.config.Config;
import hipchat4j.connector.ConnectorMock;
import hipchat4j.entities.Emoticon;
import hipchat4j.json.JsonParser;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class EmoticonsTest {

    private Emoticons emoticons;
    private ConnectorMock cm;
    private Emoticon resp;

    @Before
    public void setUp() throws Exception {
        Config conf = new Config("abc", "abc");
        cm = new ConnectorMock(conf);

        emoticons = new Emoticons(cm);
        resp = new Emoticon(123, "123", "ashortcut",800, 600);
        String jsonresp = JsonParser.getInstance().toJson(resp);
        cm.addGetResponseMapping("/v2/emoticon/123", "200", jsonresp );
    }

    @Test
    public void testGetEmoticon() throws Exception {
        Emoticon r = emoticons.getEmoticon(123);
        assertEquals("/v2/emoticon/123", cm.getLastGetRequest());
        assertEquals(resp, r);
    }

    @Test
    public void testGetAllEmoticons() throws Exception {
        List<Emoticon> lst = emoticons.getEmoticons();
        assertEquals("/v2/emoticon", cm.getLastGetRequest());
        if ( lst == null )
            fail("no emoticons returned");
        assertEquals(resp, lst.get(0));
    }
}