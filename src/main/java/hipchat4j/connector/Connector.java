package hipchat4j.connector;

import hipchat4j.config.Config;

import java.io.File;

/**
 * hipchat4j
 * 09/10/14/19:56
 */
public class Connector extends ConnectorAbstract {


    public Connector(Config config) {
        super(config);
    }

    @Override
    public String post(String requestPath, String params) {
        return "";
    }

    @Override
    public String post(String requestPath, String params, File attach) {
        return null;
    }

    @Override
    public String get(String requestPath) {
        return "";
    }

    @Override
    public String put(String requestPath, String params) {
        return null;
    }

    @Override
    public String delete(String requestPath) {
        return null;
    }

}
