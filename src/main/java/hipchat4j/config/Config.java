package hipchat4j.config;

/**
 * hipchat4j
 * 09/10/14/21:22
 */
public class Config {

    private final String token,
                         host;

    public Config(String token, String host) {
        if (token==null)
            throw new IllegalArgumentException("invalid token: null");

        if (host==null)
            throw new IllegalArgumentException("invalid host: null");

        this.token = token;
        this.host = host;
    }

    public String getToken() {
        return token;
    }

    public String getHost() {
        return host;
    }
}
