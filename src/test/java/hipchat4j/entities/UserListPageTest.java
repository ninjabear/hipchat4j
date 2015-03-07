package hipchat4j.entities;

import hipchat4j.json.JsonParser;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class UserListPageTest {

    UserListPage ul;

    @Before
    public void setUp() throws Exception {
        User user = new User("abc", 123, new User.Links("self"), "nom");
        ul = new UserListPage(new ArrayList<User>(){{add(user);}},
                              0,
                              100,
                              new UserListPage.Links("self", "prev", "next"));
    }

    @Test
    public void testGetItems() throws Exception {
        assertNotNull(ul.getUsers().get(0));
        assertEquals(1,ul.getUsers().size());
    }

    @Test
    public void testGetStartIndex() throws Exception {
        assertEquals(0, ul.getStartIndex());
    }

    @Test
    public void testGetMaxResults() throws Exception {
        assertEquals(100, ul.getMaxResults());
    }

    @Test
    public void testGetLinks() throws Exception {
        assertNotNull(ul.getLinks());
        assertEquals("self", ul.getLinks().getSelf());
        assertEquals("prev", ul.getLinks().getPrev());
        assertEquals("next", ul.getLinks().getNext());
    }

    @Test
    public void testFromJson() throws Exception {

        UserListPage ulp = JsonParser.getInstance().fromJson(IOUtils.toString(this.getClass().getResourceAsStream("/user_list_expected.json")), UserListPage.class);

        assertNotNull(ulp);

        assertNotNull(ulp.getUsers().get(0));
        assertEquals(2,ulp.getUsers().size());
        assertEquals(0, ulp.getStartIndex());

        assertEquals(0, ulp.getStartIndex());
        assertEquals(999, ulp.getMaxResults());

        assertNotNull(ulp.getLinks());
        assertEquals("selflinkmain", ulp.getLinks().getSelf());
        assertEquals("prevlink", ulp.getLinks().getPrev());
        assertEquals("nextlink", ulp.getLinks().getNext());

    }
}