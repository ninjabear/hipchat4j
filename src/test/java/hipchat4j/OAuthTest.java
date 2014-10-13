package hipchat4j;

import hipchat4j.config.Config;
import hipchat4j.connector.ConnectorMock;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OAuthTest {


    private OAuth oauth;

    @Before
    public void setUp()
    {
        oauth=new OAuth(new ConnectorMock(new Config("hello", "world")));
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
}