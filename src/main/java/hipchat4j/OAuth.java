package hipchat4j;

import hipchat4j.connector.ConnectorAbstract;
import hipchat4j.entities.AuthTokenRequest;
import hipchat4j.entities.AuthTokenResponse;
import hipchat4j.entities.Session;
import hipchat4j.json.JsonParser;

import java.util.List;

/**
 * hipchat4j
 * 09/10/14/20:11
 */
public class OAuth {

    private final ConnectorAbstract connector;

    public OAuth(ConnectorAbstract connector) {
        this.connector = connector;
    }

    ;

    public AuthTokenResponse generateToken(String username,
                                           GrantRequestType grantType,
                                           String code,
                                           String redirectUri,
                                           List<String> scopes,
                                           String password,
                                           String refresh_token) {
        if (grantType == null)
            throw new IllegalArgumentException("grantType is required");

        boolean authCodeSet = code != null,
                redirectUriSet = redirectUri != null,
                notAuthorizationGrant = !grantType.equals(GrantRequestType.AuthorizationCode);

        if (authCodeSet && notAuthorizationGrant)
            throw new IllegalArgumentException("cannot use auth code with anything but AuthorizationCode type");

        if (redirectUriSet && notAuthorizationGrant)
            throw new IllegalArgumentException("redirect uri only valid with AuthorizationCode type");


        AuthTokenRequest atr = new AuthTokenRequest(username,
                grantType.toString(),
                code,
                redirectUri,
                scopes,
                password,
                refresh_token);

        return JsonParser.getInstance().fromJson(connector.post("/v2/oauth/token", JsonParser.getInstance().toJson(atr)), AuthTokenResponse.class);

    }

    public AuthTokenResponse generateToken(GrantRequestType grantType) {
        return generateToken(null,
                grantType,
                null,
                null,
                null,
                null,
                null);
    }

    public Session getSession(String sessionToken) {
        return JsonParser.getInstance().fromJson(connector.get("/v2/oauth/token/" + sessionToken), Session.class);
    }

    public void deleteSession(String sessionToken) {
        connector.delete("/v2/oauth/token/" + sessionToken);
    }

    public enum GrantRequestType {
        AuthorizationCode,
        RefreshToken,
        Password,
        ClientCredentials,
        Personal,
        RoomNotification;

        public static String convertToAPI(GrantRequestType type) {
            if (type == null)
                throw new IllegalArgumentException("null is not a request type");

            switch (type) {
                case AuthorizationCode:
                    return "authorization_code";
                case RefreshToken:
                    return "refresh_token";
                case Password:
                    return "password";
                case ClientCredentials:
                    return "client_credentials";
                case Personal:
                    return "personal";
                case RoomNotification:
                    return "room_notification";
                default:
                    throw new IllegalArgumentException("cannot figure out state");
            }
        }

        @Override
        public String toString() {
            return convertToAPI(this);
        }
    }

}
