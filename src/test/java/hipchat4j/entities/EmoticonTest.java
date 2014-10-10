package hipchat4j.entities;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmoticonTest {

    private Emoticon e,eNoAudio;
    private String sampleJson;

    @Before
    public void setUp() throws Exception {
        e=new Emoticon(123, "keyorid", "ashortcut", 500, 900, "audiopath");
        eNoAudio=new Emoticon(123, "keyorid", "ashortcut", 500, 900);
        sampleJson = IOUtils.toString(this.getClass().getResourceAsStream("/emoticons_output.json"));
    }

    @Test
    public void testGetKeyOrId() throws Exception {
        assertEquals("keyorid", e.getKeyOrId());
    }

    @Test
    public void testGetShortcut() throws Exception {
        assertEquals("ashortcut", e.getShortcut());
    }

    @Test
    public void testGetWidth() throws Exception {
        assertEquals(500, e.getWidth());
    }

    @Test
    public void testGetHeight() throws Exception {
        assertEquals(900, e.getHeight());
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals(123, e.getId());
    }

    @Test
    public void testGetAudioPath() throws Exception {
        assertNull(eNoAudio.getAudioPath());
        assertEquals("audiopath", e.getAudioPath());
    }

    @Test
    public void testFromJson() throws Exception
    {

    }

}