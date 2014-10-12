package hipchat4j;

import hipchat4j.connector.ConnectorAbstract;
import hipchat4j.entities.ServerCapability;
import hipchat4j.json.JsonParser;

/**
 * hipchat4j
 * 09/10/14/20:10
 */
public class Capabilities {

    private final ConnectorAbstract connector;

    public Capabilities(ConnectorAbstract connector) {
        this.connector = connector;
    }


    public ServerCapability getCapabilities()
    {
        String capabilitiesJson = connector.get("/v2/capabilities");
        return JsonParser.getInstance().fromJson(capabilitiesJson, ServerCapability.class);
    }

}
