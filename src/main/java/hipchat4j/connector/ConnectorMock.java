package hipchat4j.connector;

import hipchat4j.config.Config;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * hipchat4j
 * 09/10/14/20:01
 */
public class ConnectorMock extends ConnectorAbstract {

    private List<String> postrequests = new ArrayList<>();
    private List<Map<String,String>> parammaps = new ArrayList<>();
    private List<String> getrequests = new ArrayList<>();

    private Map<String, Map<String, String>> getresponsemap = new HashMap<>();
    private static final String BODY_KEY = ":body";
    private static final String CODE_KEY = ":resp_code";

    public ConnectorMock(Config config) {
        super(config);
    }

    @Override
    public String post(String requestPath, Map<String, String> paramMap) {
        postrequests.add(requestPath);
        parammaps.add(paramMap);
        return "";
    }

    @Override
    public String get(String requestPath) {
        getrequests.add(requestPath);
        if (getresponsemap.get(requestPath)==null) {
            return "";
        } else {
            Map<String,String> resp = getresponsemap.get(requestPath);
            if (resp.get(CODE_KEY)!="200")
            {
                throw new IllegalStateException("couldn't get result - http response " + resp.get(CODE_KEY));
            }
            return resp.get(BODY_KEY);
        }

    }

    public String getLastPostRequest() {
        return postrequests.get(postrequests.size()-1);
    }

    public Map<String,String> getLastPostParam() {
        return parammaps.get(parammaps.size()-1);
    }

    public String getLastGetRequest() {
        return getrequests.get(getrequests.size()-1);
    }

    public void addGetResponseMapping(String path, String responseCode, String responseBody)
    {
        getresponsemap.put(path, new HashMap<String,String>() {{ put(BODY_KEY, responseBody); put(CODE_KEY, responseCode); }});
    }

}
