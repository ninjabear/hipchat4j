package hipchat4j.entities;

import hipchat4j.json.JsonParser;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static java.nio.file.Paths.*;
import static org.junit.Assert.*;

public class EmoticonListPageTest {

    private String testJson;

    @Before
    public void setUp() throws Exception {
        URL file = this.getClass().getResource("/emoticons_output.json");
        Path resPath = get(file.toURI());
        testJson = new String(Files.readAllBytes(resPath), "UTF8");
    }

    @Test
    public void testFieldsSetup() throws Exception {
        EmoticonListPage elp = JsonParser.getInstance().fromJson(testJson, EmoticonListPage.class);
        assertEquals(0, elp.getStartIndex());
        assertEquals(100, elp.getMaxResults());
    }

    @Test
    public void toJsonSample() throws  Exception {

        ArrayList<Item> a = new ArrayList<>();
        a.add(new Item(123, new Link("self", "", ""), "shortcut", "url"));
        EmoticonListPage elp = new EmoticonListPage(a, 0, 100, new Link("self","prev", "next"));
        String jsonout = JsonParser.getInstance().toJson(elp);
        EmoticonListPage back = JsonParser.getInstance().fromJson(jsonout, EmoticonListPage.class);

        assertEquals(elp.getItems().size(), back.getItems().size());
        assertEquals(elp.getMaxResults(), back.getMaxResults());
        assertEquals(elp.getStartIndex(), back.getStartIndex());
        assertEquals(elp.getLinks().getNext(), back.getLinks().getNext());
        assertEquals(elp.getLinks().getPrev(), back.getLinks().getPrev());
        assertEquals(elp.getLinks().getNext(), back.getLinks().getNext());
        assertEquals(elp.getItems().get(0).getId(), back.getItems().get(0).getId());
        assertEquals(elp.getItems().get(0).getShortcut(), back.getItems().get(0).getShortcut());
        assertEquals(elp.getItems().get(0).getUrl(), back.getItems().get(0).getUrl());
        assertEquals(elp.getItems().get(0).getLinks().getNext(), back.getItems().get(0).getLinks().getNext());
        assertEquals(elp.getItems().get(0).getLinks().getPrev(), back.getItems().get(0).getLinks().getPrev());
        assertEquals(elp.getItems().get(0).getLinks().getSelf(), back.getItems().get(0).getLinks().getSelf());
    }

}