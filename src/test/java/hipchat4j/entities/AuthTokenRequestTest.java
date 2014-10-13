package hipchat4j.entities;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class AuthTokenRequestTest {

    @Test
    public void testListToSpaceSeperated() throws Exception {
        List<String> abc = Arrays.asList("A", "B", "C");

        assertEquals("A B C", AuthTokenRequest.listToSpaceSeperated(abc));
    }
}