package hipchat4j;

import hipchat4j.config.Config;
import hipchat4j.connector.ConnectorMock;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CapabilitiesTest {

    private Capabilities c;
    private ConnectorMock cm;

    @Before
    public void setUp() throws Exception {
        cm = new ConnectorMock(new Config("test", "test"));
        cm.addResponseMapping("/v2/capabilities", "200", IOUtils.toString(this.getClass().getResourceAsStream("/capabilities.json")));
        c = new Capabilities(cm);
    }

    @Test
    public void testGetCapabilities() {
        assertNotNull(c.getCapabilities());
        assertNotNull(c.getCapabilities().getVendor());
        assertEquals("Atlassian", c.getCapabilities().getVendor().getName());
    }
}