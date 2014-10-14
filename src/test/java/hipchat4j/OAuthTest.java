package hipchat4j;

import hipchat4j.config.Config;
import hipchat4j.connector.ConnectorAbstract;
import hipchat4j.connector.ConnectorMock;
import hipchat4j.entities.AuthTokenRequest;
import hipchat4j.json.JsonParser;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class OAuthTest {


    private OAuth oauth;
    private ConnectorMock cm;
    private String responseJson;

    @Before
    public void setUp() throws IOException {
        cm = new ConnectorMock(new Config("hello", "world"));
        oauth=new OAuth(cm);
        responseJson = IOUtils.toString(getClass().getResourceAsStream("/authrequest_response.json"));
    }


    @Test
    public void testGrantTypes() throws Exception {

        assertEquals("authorization_code", OAuth.GrantRequestType.AuthorizationCode.toString());
        assertEquals("refresh_token", OAuth.GrantRequestType.RefreshToken.toString());
        assertEquals("password", OAuth.GrantRequestType.Password.toString());
        assertEquals("client_credentials", OAuth.GrantRequestType.ClientCredentials.toString());
        assertEquals("personal", OAuth.GrantRequestType.Personal.toString());
        assertEquals("room_notification", OAuth.GrantRequestType.RoomNotification.toString());

    }


    @Test ( expected = IllegalArgumentException.class )
    public void testGenerateTokenNoType() throws Exception {
        oauth.generateToken(null,null,null,null,null,null,null);
    }


    @Test ( expected = IllegalArgumentException.class )
    public void testGenerateTokenNoTypeShort() throws Exception {
        oauth.generateToken(null);
    }

    @Test ( expected = IllegalArgumentException.class )
    public void testGenerateAuthCodeNoAuthType() throws Exception {
        oauth.generateToken(null,
                            OAuth.GrantRequestType.Personal,
                            "acode",
                            null,
                            null,
                            null,
                            null);
    }

    @Test ( expected = IllegalArgumentException.class )
    public void testGenerateRedirectURINoAuthType() throws Exception {
        oauth.generateToken(null,
                            OAuth.GrantRequestType.Personal,
                            null,
                            "http://somewhere.com/?",
                            null,
                            null,
                            null);
    }

    @Test
    public void testCorrectApiPathType() throws Exception {
        oauth.generateToken(OAuth.GrantRequestType.Personal);
        assertEquals("/v2/oauth/token", cm.getLastPutRequest());
        AuthTokenRequest atr = JsonParser.getInstance().fromJson(cm.getLastPutParam(), AuthTokenRequest.class);
        assertEquals(OAuth.GrantRequestType.Personal.toString(), atr.getGrantType());
    }

    @Test (expected = IllegalArgumentException.class )
    public void testGrantRequestTypeNull() throws Exception
    {
        OAuth.GrantRequestType.convertToAPI(null);
    }

    @Test
    public void testGetTokenResponse() throws Exception
    {
        cm.addResponseMapping("/v2/oauth/token", "200", responseJson);
        String resp = oauth.generateToken(OAuth.GrantRequestType.Personal);
        assertEquals(responseJson, resp);
    }
}