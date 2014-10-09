package hipchat4j;

import hipchat4j.config.Config;
import hipchat4j.connector.Connector;

/**
 * hipchat4j
 * 09/10/14/21:57
 */
public class HipChatAPI {

    private final Connector connector;
    private final Config config;

    public HipChatAPI(String host, String token) {
        this.config = new Config(host, token);
        this.connector = new Connector(config);

    }
}
