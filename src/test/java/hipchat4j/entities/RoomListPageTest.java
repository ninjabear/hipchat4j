package hipchat4j.entities;

import hipchat4j.json.JsonParser;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static hipchat4j.entities.RoomListPage.Item;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RoomListPageTest {

    private RoomListPage rlp;

    @Before
    public void setUp() throws Exception {
        List<Item> lst = new ArrayList<>();
        lst.add(new Item(55,
                new RoomListPage.Item.Links("itemself", "itempwebhooks", "itemmembers", "itemparticipants"),
                "itemname"));


        rlp = new RoomListPage(lst, 0, 100, new RoomListPage.Links("self", "prev", "next"));
    }

    @Test
    public void testGetItems() throws Exception {
        List<Item> items = rlp.getItems();
        assertNotNull(items);
        assertEquals(1, items.size());
        assertEquals(55, items.get(0).getId());
        assertEquals("itemname", items.get(0).getName());
        assertEquals("itemself", items.get(0).getLinks().getSelf());
        assertEquals("itempwebhooks", items.get(0).getLinks().getWebhooks());
        assertEquals("itemmembers", items.get(0).getLinks().getMembers());
        assertEquals("itemparticipants", items.get(0).getLinks().getParticipants());
    }

    @Test
    public void testGetStartIndex() throws Exception {
        assertEquals(0, rlp.getStartIndex());
    }

    @Test
    public void testGetLinks() throws Exception {
        assertEquals("self", rlp.getLinks().getSelf());
        assertEquals("prev", rlp.getLinks().getPrev());
        assertEquals("next", rlp.getLinks().getNext());
    }

    @Test
    public void testGetMaxResults() throws Exception {
        assertEquals(100, rlp.getMaxResults());
    }

    @Test
    public void testExpectedJSON() throws Exception {

        String json = IOUtils.toString(this.getClass().getResourceAsStream("/getrooms_expected_output.json"));

        RoomListPage r = JsonParser.getInstance().fromJson(json, RoomListPage.class);

        assertNotNull(r);
        List<Item> items = r.getItems();
        assertNotNull(items);
        assertEquals(1, items.size());
        assertEquals(99, items.get(0).getId());
        assertEquals("roomname", items.get(0).getName());
        assertEquals("self", items.get(0).getLinks().getSelf());
        assertEquals("webhooks", items.get(0).getLinks().getWebhooks());
        assertEquals("members", items.get(0).getLinks().getMembers());
        assertEquals("participants", items.get(0).getLinks().getParticipants());
        assertEquals("pageself", r.getLinks().getSelf());
        assertEquals("pageprev", r.getLinks().getPrev());
        assertEquals("pagenext", r.getLinks().getNext());
        assertEquals(99, r.getMaxResults());
        assertEquals(0, r.getStartIndex());

    }
}