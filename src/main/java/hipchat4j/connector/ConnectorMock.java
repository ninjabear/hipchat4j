package hipchat4j.connector;

import java.net.URL;
import java.util.Map;

/**
 * hipchat4j
 * 09/10/14/20:01
 */
public class ConnectorMock implements ConnectorInterface {

    @Override
    public String post(URL to, Map<String, String> paramMap) {
        return null;
    }

    @Override
    public String get(URL to) {
        return null;
    }
}
