package hipchat4j.connector;

import hipchat4j.config.Config;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * hipchat4j
 * 09/10/14/20:01
 */
public class ConnectorMock extends ConnectorAbstract {

    private static final String BODY_KEY = ":body";
    private static final String CODE_KEY = ":resp_code";
    private List<String> postrequests = new ArrayList<>();
    private List<String> parammaps = new ArrayList<>();
    private List<String> getrequests = new ArrayList<>();
    private List<String> putrequests = new ArrayList<>();
    private List<String> putparams = new ArrayList<>();
    private List<String> deleterequests = new ArrayList<>();
    private List<File>  postRequestAttachments = new ArrayList<File>();
    private Map<String, Map<String, String>> responsemap = new HashMap<>();

    public ConnectorMock(Config config) {
        super(config);
    }


    private String getMappedResponse(String requestPath) {
        if (responsemap.get(requestPath) == null) {
            return "";
        } else {
            Map<String, String> resp = responsemap.get(requestPath);
            if (!resp.get(CODE_KEY).equals("200")) {
                throw new IllegalStateException("couldn't get result - http response " + resp.get(CODE_KEY));
            }
            return resp.get(BODY_KEY);
        }
    }

    @Override
    public String post(String requestPath, String params) {
        postrequests.add(requestPath);
        parammaps.add(params);
        return getMappedResponse(requestPath);
    }

    @Override
    public String post(String requestPath, String params, File attach) {
        postRequestAttachments.add(attach);
        return post(requestPath, params);
    }


    @Override
    public String get(String requestPath) {
        getrequests.add(requestPath);
        return getMappedResponse(requestPath);
    }

    @Override
    public String put(String requestPath, String params) {
        putrequests.add(requestPath);
        putparams.add(params);
        return getMappedResponse(requestPath);
    }

    @Override
    public String delete(String requestPath) {
        deleterequests.add(requestPath);
        return null;
    }


    public String getLastPutRequest() {
        return putrequests.get(putrequests.size() - 1);
    }

    public String getLastPutParam() {
        return putparams.get(putparams.size() - 1);
    }

    public String getLastPostRequest() {
        return postrequests.get(postrequests.size() - 1);
    }

    public File getLastPostAttachment() { return postRequestAttachments.get(postRequestAttachments.size() -1 ); }

    public String getLastPostParam() {
        return parammaps.get(parammaps.size() - 1);
    }

    public String getLastGetRequest() {
        return getrequests.get(getrequests.size() - 1);
    }

    public String getLastDeleteRequest() {
        return deleterequests.get(deleterequests.size() - 1);
    }

    public void addResponseMapping(String path, String responseCode, String responseBody) {
        responsemap.put(path, new HashMap<String, String>() {{
            put(BODY_KEY, responseBody);
            put(CODE_KEY, responseCode);
        }});
    }

}
