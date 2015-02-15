package hipchat4j.entities;

import hipchat4j.json.JsonParser;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.apache.commons.io.IOUtils.readFully;
import static org.junit.Assert.*;

public class MessageHistoryTest {

    private MessageHistory mh;

    @Before
    public void setUp() throws Exception {

        MessagePayload m1 = JsonParser.getInstance().fromJson(IOUtils.toString(this.getClass().getResourceAsStream("/message_style1.json")), Message.class).getMessagePayload();
        MessagePayload m2 = JsonParser.getInstance().fromJson(IOUtils.toString(this.getClass().getResourceAsStream("/message_style2.json")), Message.class).getMessagePayload();

        ArrayList<MessagePayload> items = new ArrayList<>();
        items.add(m1);
        items.add(m2);

        mh = new MessageHistory(items, 0, 100, new MessageHistory.Link("self", "prev", "next"));
    }

    @Test
    public void testGetItems() throws Exception {
        assertEquals(2, mh.getItems().size());
        assertNotNull(mh.getItems().get(0).getFromName());
        assertNotNull(mh.getItems().get(1).getFromName());
    }

    @Test
    public void testGetStartIndex() throws Exception {
        assertEquals(0, mh.getStartIndex());
    }

    @Test
    public void testGetMaxResults() throws Exception {
        assertEquals(100, mh.getMaxResults());
    }

    @Test
    public void testGetLinks() throws Exception {
        assertNotNull(mh.getLinks());
        assertEquals("next", mh.getLinks().getNext());
        assertEquals("self", mh.getLinks().getSelf());
        assertEquals("prev", mh.getLinks().getPrev());
    }

}