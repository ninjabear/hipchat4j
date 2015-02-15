package hipchat4j.entities;

import hipchat4j.json.JsonParser;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AuthTokenRequestTest {

    private String expectedJsonOutput, getExpectedJsonOutputMin;

    @Before
    public void setUp() throws IOException {
        expectedJsonOutput = IOUtils.toString(this.getClass().getResourceAsStream("/authrequest_expected_full.json"));
        getExpectedJsonOutputMin = IOUtils.toString(this.getClass().getResourceAsStream("/authrequest_expected_min.json"));
    }

    @Test
    public void testListToSpaceSeparated() throws Exception {
        List<String> abc = Arrays.asList("A", "B", "C");
        assertEquals("A B C", AuthTokenRequest.listToSpaceSeparated(abc));

        List<String> abspace = Arrays.asList(" ", "B", "C");
        assertEquals("  B C", AuthTokenRequest.listToSpaceSeparated(abspace)); // space space b c

        List<String> abemptyd = Arrays.asList("A", "B", "", "D");
        assertEquals("A B  D", AuthTokenRequest.listToSpaceSeparated(abemptyd)); // a b space d
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListToSpaceSeparatedNullElement() throws Exception {
        List<String> abc = Arrays.asList("A", "B", null, "D");
        AuthTokenRequest.listToSpaceSeparated(abc);
    }

    @Test
    public void testListToSpaceSeparatedNoList() throws Exception {
        assertNull(AuthTokenRequest.listToSpaceSeparated(null));
        assertNull(AuthTokenRequest.listToSpaceSeparated(new ArrayList<>()));
    }

    @Test
    public void testFromJson() {
        AuthTokenRequest atr = JsonParser.getInstance().fromJson(expectedJsonOutput, AuthTokenRequest.class);
        assertEquals("ausername", atr.getUsername());
        assertEquals("authorization_code", atr.getGrantType());
        assertEquals("acode", atr.getCode());
        assertEquals("http://somewhere.com/?", atr.getRedirectUri());
        assertEquals("a scope here", atr.getScope());
        assertEquals("sexorgod", atr.getPassword());
        assertEquals("arefreshtoken", atr.getRefreshToken());

    }


    @Test
    public void testFromJsonMin() {
        AuthTokenRequest atr = JsonParser.getInstance().fromJson(getExpectedJsonOutputMin, AuthTokenRequest.class);
        assertNull(atr.getUsername());
        assertEquals("password", atr.getGrantType());
        assertNull(atr.getCode());
        assertNull(atr.getRedirectUri());
        assertNull(atr.getScope());
        assertNull(atr.getPassword());
        assertNull(atr.getRefreshToken());

    }

    @Test
    public void testConstructorAndGetters() {
        AuthTokenRequest r = new AuthTokenRequest("a", "b", "c", "d", Arrays.asList("e"), "f", "h");

        assertEquals("a", r.getUsername());
        assertEquals("b", r.getGrantType());
        assertEquals("c", r.getCode());
        assertEquals("d", r.getRedirectUri());
        assertEquals("e", r.getScope());
        assertEquals("f", r.getPassword());
        assertEquals("h", r.getRefreshToken());

    }
}