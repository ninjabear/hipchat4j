package hipchat4j.connector;

import hipchat4j.config.Config;

import java.net.URL;
import java.util.Map;

/**
 * hipchat4j
 * 09/10/14/19:59
 */
public abstract class ConnectorAbstract {

    protected final Config config;

    public ConnectorAbstract(Config config)
    {
        this.config=config;
    }


    public abstract String post(String requestPath, Map<String, String> paramMap);
    public abstract String get(String requestPath);

}
