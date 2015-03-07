package hipchat4j.entities;

import hipchat4j.json.JsonParser;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserTest {

    private User user;

    @Before
    public void setUp() throws Exception {
        user = new User("mentionname", 123, new User.Links("hello world"), "name");
    }

    @Test
    public void testGetMentionName() throws Exception {
        assertEquals("mentionname", user.getMentionName());
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals(123, user.getId());
    }

    @Test
    public void testGetLinks() throws Exception {
        assertNotNull(user.getLinks());
        assertEquals("hello world", user.getLinks().getSelf());
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("name", user.getName());
    }

    @Test
    public void testFromJSON() throws Exception {

        UserListPage ulp = JsonParser.getInstance().fromJson(IOUtils.toString(this.getClass().getResourceAsStream("/user_list_expected.json")), UserListPage.class);

        List<User> lu = ulp.getUsers();

        assertNotNull(lu);
        User one = lu.get(0);
        User two = lu.get(1);

        assertEquals("mention_name", one.getMentionName());
        assertEquals(125, one.getId());
        assertEquals("selflink", one.getLinks().getSelf());
        assertEquals("ninja", one.getName());

        assertEquals("mention2", two.getMentionName());
        assertEquals(127, two.getId());
        assertEquals("selflink2", two.getLinks().getSelf());
        assertEquals("bear", two.getName());


    }
}