package hipchat4j.config;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConfigTest {

    private Config c;

    @Before
    public void setUp() throws Exception {
        c = new Config("abcdefg", "ahost");
    }

    @Test
    public void testGetToken() throws Exception {
        assertEquals("abcdefg", c.getToken());
    }

    @Test
    public void testGetHost() throws Exception {
        assertEquals("ahost", c.getHost());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testNullToken() throws Exception {
        new Config(null, "ahost");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullHost() throws Exception {
        new Config("atoken", null);
    }
}