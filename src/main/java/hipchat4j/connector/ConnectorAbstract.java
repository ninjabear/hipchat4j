package hipchat4j.connector;

import hipchat4j.config.Config;

import java.io.File;

/**
 * hipchat4j
 * 09/10/14/19:59
 */
public abstract class ConnectorAbstract {

    /**
     * TODO
     * simulate different reasons for failure and create the exceptions that accompany them
     * https://www.hipchat.com/docs/apiv2/response_codes
     */

    protected final Config config;

    public ConnectorAbstract(Config config) {
        this.config = config;
    }

    public abstract String post(String requestPath, String params);

    public abstract String post(String requestPath, String params, File attach); // multipart

    public abstract String get(String requestPath);

    public abstract String put(String requestPath, String params);

    public abstract String delete(String requestPath);

}
