package hipchat4j.connector;

import java.net.URL;
import java.util.Map;

/**
 * hipchat4j
 * 09/10/14/19:59
 */
public interface ConnectorInterface {

    public String post(URL to, Map<String, String> paramMap);
    public String get(URL to);

}
