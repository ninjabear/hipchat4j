package hipchat4j.connector;

import hipchat4j.config.Config;

import java.net.URL;
import java.util.Map;

/**
 *  hipchat4j
 *  09/10/14/19:56
 */
public class Connector extends ConnectorAbstract {


    public Connector(Config config) {
        super(config);
    }

    @Override
    public String post(String requestPath, Map<String, String> paramMap)
    {
        return "";
    }

    @Override
    public String get(String requestPath)
    {
        return "";
    }

}
