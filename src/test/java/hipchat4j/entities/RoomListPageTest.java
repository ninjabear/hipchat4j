package hipchat4j.entities;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static hipchat4j.entities.RoomListPage.Item;
import static org.junit.Assert.*;

public class RoomListPageTest {

    private RoomListPage rlp;

    @Before
    public void setUp() throws Exception {
        List<Item> lst = new ArrayList<>();
        lst.add(new Item(55,
                        new RoomListPage.Item.Links("itemself", "itempwebhooks", "itemmembers", "itemparticipants"),
                        "itemname") );



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
}