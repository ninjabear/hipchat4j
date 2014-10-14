package hipchat4j;

import hipchat4j.config.Config;
import hipchat4j.connector.ConnectorMock;
import hipchat4j.entities.Emoticon;
import hipchat4j.json.JsonParser;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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

        cm.addResponseMapping("/v2/emoticon/123", "200", jsonresp);
        cm.addResponseMapping("/v2/emoticon", "200", IOUtils.toString(this.getClass().getResourceAsStream("/emoticons_output.json")));
        cm.addResponseMapping("/v2/emoticon?start-index=100&max-results=100", "200", IOUtils.toString(this.getClass().getResourceAsStream("/emoticons_output_pg2.json")));
        cm.addResponseMapping("/v2/emoticon/260", "200", IOUtils.toString(this.getClass().getResourceAsStream("/emoticon_single.json")));
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
        if ( lst == null || lst.size() == 0)
            fail("no emoticons returned");
        /*
         "id":260,
         "links":{
            "self":"https://api.hipchat.com/v2/emoticon/260"
         },
         "shortcut":"allthethings",
         "url":"https://dujrsrsgsd3nh.cloudfront.net/img/emoticons/allthethings.png"
         */
        Emoticon exp = new Emoticon(260, "260", this.cm);
        assertEquals(exp.getId(), lst.get(0).getId());
        assertEquals(exp.getShortcut(), lst.get(0).getShortcut());
        assertEquals(exp.getHeight(), lst.get(0).getHeight());
        assertEquals(exp.getWidth(), lst.get(0).getWidth());
        assertEquals(exp.getAudioPath(), lst.get(0).getAudioPath());
        assertEquals(exp.getKeyOrId(), lst.get(0).getKeyOrId());

    }
}