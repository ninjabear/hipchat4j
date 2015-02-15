package hipchat4j.entities;

import hipchat4j.json.JsonParser;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ServerCapabilityTest {

    private String jsonFromHipchatMain,
            jsonFullCrafted;
    private ServerCapability capabilityFromHipchatMain;

    @Before
    public void setUp() throws Exception {
        jsonFromHipchatMain = IOUtils.toString(this.getClass().getResourceAsStream("/capabilities.json"));
        jsonFullCrafted = null;

        capabilityFromHipchatMain = JsonParser.getInstance().fromJson(jsonFromHipchatMain, ServerCapability.class);

    }

    @Test
    public void testGetVendor() throws Exception {
        ServerCapability.Vendor v = capabilityFromHipchatMain.getVendor();
        assertNotNull(v);

        //fail (JsonParser.getInstance().toJson(new ServerCapability()));
    }

    @Test
    public void testGetName() throws Exception {

    }

    @Test
    public void testGetLinks() throws Exception {

    }

    @Test
    public void testGetCapabilities() throws Exception {

    }

    @Test
    public void testGetDescription() throws Exception {

    }

    @Test
    public void testGetKey() throws Exception {

    }
}