package hipchat4j.entities;

import org.junit.Before;
import org.junit.Test;

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
}