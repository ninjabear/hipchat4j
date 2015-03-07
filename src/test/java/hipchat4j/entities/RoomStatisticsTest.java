package hipchat4j.entities;

import hipchat4j.json.JsonParser;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoomStatisticsTest {

    RoomStatistics s;

    @Before
    public void setUp() throws Exception {
        s = new RoomStatistics(123, "some unix time");
    }

    @Test
    public void testGetMessagesSent() throws Exception {
        assertEquals(123, s.getMessagesSent());
    }

    @Test
    public void testGetLastActive() throws Exception {
        assertEquals("some unix time", s.getLastActive());
    }

    @Test
    public void testFromJson() throws Exception {
        RoomStatistics rs = JsonParser.getInstance().fromJson(IOUtils.toString(this.getClass().getResourceAsStream("/room_statistics_example.json")), RoomStatistics.class);
        assertEquals(123, rs.getMessagesSent());
        assertEquals("sometime", rs.getLastActive());
    }
}