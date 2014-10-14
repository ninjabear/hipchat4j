package hipchat4j.entities;

import hipchat4j.json.JsonParser;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AuthTokenResponseTest {

    private String responseJson;

    @Before
    public void setUp() throws Exception {
        responseJson = IOUtils.toString(getClass().getResourceAsStream("/authrequest_response.json"));
    }

    @Test
    public void testConstructor() throws Exception
    {
        AuthTokenResponse atresp = new AuthTokenResponse("accesstoken",
                1234,
                "mygroupname",
                "bearer_default",
                "myscope",
                4321);

        assertEquals("accesstoken", atresp.getAccessToken());
        assertEquals(1234, atresp.getExpiresIn());
        assertEquals("mygroupname", atresp.getGroupName());
        assertEquals("bearer_default", atresp.getTokenType());
        assertEquals("myscope", atresp.getScope());
        assertEquals(4321, atresp.getGroupId());
    }


    @Test
    public void testAgainstExpectedJson() throws Exception {
        AuthTokenResponse resp = JsonParser.getInstance().fromJson(responseJson, AuthTokenResponse.class);
        String andagain = JsonParser.getInstance().toJson(resp);
        resp = JsonParser.getInstance().fromJson(andagain, AuthTokenResponse.class);

        assertEquals("anaccesstoken", resp.getAccessToken());
        assertEquals(999, resp.getExpiresIn());
        assertEquals("breezy badgers", resp.getGroupName());
        assertEquals("bearer", resp.getTokenType());
        assertEquals("admin", resp.getScope());
        assertEquals(234, resp.getGroupId());
    }

}