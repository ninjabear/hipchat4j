package hipchat4j.json;

import com.google.gson.Gson;

/**
 * hipchat4j
 * 09/10/14/23:06
 */
public class JsonParser {

    private static JsonParser me;
    private Gson gson = new Gson();


    private JsonParser() {}

    public static JsonParser getInstance()
    {
        if (null==me)
            me = new JsonParser();

        return me;
    }


    public String toJson(Object val)
    {
        return gson.toJson(val);
    }

    public <T> T fromJson(String json, Class<T> type)
    {
        return gson.fromJson(json, type);
    }


}
