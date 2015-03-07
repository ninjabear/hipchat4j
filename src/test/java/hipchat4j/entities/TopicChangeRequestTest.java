package hipchat4j.entities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TopicChangeRequestTest {

    TopicChangeRequest t;

    @Before
    public void setUp() throws Exception {
        t = new TopicChangeRequest("JESSIE");
    }

    @Test
    public void testGetTopic() throws Exception {
        assertEquals(t.getTopic(), "JESSIE");
    }
}