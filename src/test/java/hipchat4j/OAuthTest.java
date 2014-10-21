package hipchat4j;

import hipchat4j.config.Config;
import hipchat4j.connector.ConnectorAbstract;
import hipchat4j.connector.ConnectorMock;
import hipchat4j.entities.AuthTokenRequest;
import hipchat4j.entities.AuthTokenResponse;
import hipchat4j.entities.Session;
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
        assertEquals("/v2/oauth/token", cm.getLastPostRequest());
        AuthTokenRequest atr = JsonParser.getInstance().fromJson(cm.getLastPostParam(), AuthTokenRequest.class);
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
        AuthTokenResponse r = oauth.generateToken(OAuth.GrantRequestType.Personal);
        assertEquals("anaccesstoken", r.getAccessToken());
    }

    @Test
    public void testGetSession() throws Exception {
        cm.addResponseMapping("/v2/oauth/token/123", "200", IOUtils.toString(this.getClass().getResourceAsStream("/session_expected_full.json")));
        Session session = oauth.getSession("123");
        assertEquals("/v2/oauth/token/123", cm.getLastGetRequest());
        assertNotNull(session);
        assertEquals("atoken", session.getAccessToken()); // tested in more detail on session entity
    }

    @Test
    public void testDeleteSession() throws Exception {
        oauth.deleteSession("123");
        assertEquals("/v2/oauth/token/123", cm.getLastDeleteRequest());
    }

}