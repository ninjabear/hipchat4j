package hipchat4j.entities;

import hipchat4j.json.JsonParser;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

    @Test
    public void testGetMessagePayload() throws Exception {
        Message m = JsonParser.getInstance().fromJson(IOUtils.toString(this.getClass().getResourceAsStream("/message_style1.json")), Message.class);
        MessagePayload mp = m.getMessagePayload();
        assertNotNull(mp);
        assertNotNull(new Message(mp).getMessagePayload());
    }

    @Test
    public void testSetMessagePayload() throws Exception {
        Message m = JsonParser.getInstance().fromJson(IOUtils.toString(this.getClass().getResourceAsStream("/message_style1.json")), Message.class);
        Message my = new Message(null);
        assertNull(my.getMessagePayload());
        my.setMessagePayload(m.getMessagePayload());
        assertNotNull(my.getMessagePayload());
    }
}