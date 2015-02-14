package hipchat4j.entities;

import hipchat4j.json.JsonParser;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MessagePayloadTest {

    private MessagePayload messagePayloadStyle1, messagePayloadStyle2;

    @Before
    public void setUp() throws Exception {

        MessagePayload.From from = new MessagePayload.From("@mentionName", 123, new MessagePayload.From.Links("selffrom"), "fullfrom");
        MessagePayload.MessageLinks messageLinks = new MessagePayload.MessageLinks(new MessagePayload.MessageLinks.TwitterUser(1, "lonrery", "http://twit", "roney"),
                "http://twitterurl",
                new MessagePayload.MessageLinks.Image("imgb64", "myimage.jpg"),
                new MessagePayload.MessageLinks.TwitterStatus("statusname", "statuscreated", "http://statusurl", "content", "src", "screenname"),
                new MessagePayload.MessageLinks.Video("author", "vidthumb", 2, "myvid"),
                new MessagePayload.MessageLinks.Link("linkdesc", "linktitle", "linkheader", "linktext", "http://linkurl", "http://favicon"),
                "yes"
        );
        MessagePayload.File file = new MessagePayload.File("http://fileurl", "http://thumburl", "filename", 999);
        MessagePayload.Mentions mentions = new MessagePayload.Mentions("@mentionuser", 555, new MessagePayload.Mentions.Links("mentionsself"), "mention user");

        String date = "2014-11-01T15:19:20+00:00";

        messagePayloadStyle1 = new MessagePayload(from,
                                    messageLinks,
                                    file,
                                    date,
                                    mentions,
                                    "MY MESSAGE STYLE 1",
                                    "test",
                                    "ABCDEF"
                                    );

        messagePayloadStyle2 = new MessagePayload("ninja",
                                    "html",
                                    "red",
                                    date,
                                    new MessagePayload.Mentions("@mention",
                                                        75,
                                                        new MessagePayload.Mentions.Links("2self"),
                                                        "mention2"),
                                    "hello world",
                                    "test2",
                                    "ABC");
    }

    @Test
    public void testGetMessageType()
    {
        assertEquals(MessagePayload.MessageType.FromAddOn, messagePayloadStyle2.getMessageType());
        assertEquals(MessagePayload.MessageType.FromUser, messagePayloadStyle1.getMessageType());
    }

    @Test
    public void testGetFrom() {
        assertEquals("fullfrom", messagePayloadStyle1.getFromName());
        assertEquals("ninja", messagePayloadStyle2.getFromName());
    }

    @Test
    public void testGetTwitterUser() {
        MessagePayload.MessageLinks.TwitterUser t = messagePayloadStyle1.getTwitterUser();
        MessagePayload.MessageLinks.TwitterUser t2 = messagePayloadStyle2.getTwitterUser();

        assertNull(t2);
        assertNotNull(t);

        assertEquals(1, t.getFollowers());
        assertEquals("lonrery", t.getName());
        assertEquals("http://twit", t.getProfileImageUrl());
        assertEquals("roney", t.getScreenName());
    }
    
    @Test
    public void testGetMessageLinks() {
        assertNull(messagePayloadStyle2.getMessageLinks());
        
        MessagePayload.MessageLinks ml = messagePayloadStyle1.getMessageLinks();
        assertNotNull(ml);
        
        MessagePayload.MessageLinks.Image i = ml.getImage();
        assertNotNull(i);
        assertEquals("imgb64", i.getImage());
        assertEquals("myimage.jpg", i.getName());
        
        assertNotNull(ml.getTwitterUser()); // this is the same pretty much as above        
        assertEquals(1, ml.getTwitterUser().getFollowers());
        assertEquals("lonrery", ml.getTwitterUser().getName());
        assertEquals("http://twit", ml.getTwitterUser().getProfileImageUrl());
        assertEquals("roney", ml.getTwitterUser().getScreenName());
        
        assertNotNull(ml.getTwitterStatus());
        assertEquals("statusname", ml.getTwitterStatus().getName());
        assertEquals("statuscreated", ml.getTwitterStatus().getCreated());
        assertEquals("http://statusurl", ml.getTwitterStatus().getProfileImageUrl());
        assertEquals("content", ml.getTwitterStatus().getText());
        assertEquals("src", ml.getTwitterStatus().getSource());
        assertEquals("screenname", ml.getTwitterStatus().getScreenName());
        
        assertNotNull(ml.getVideo());
        assertEquals("author", ml.getVideo().getAuthor());
        assertEquals("vidthumb", ml.getVideo().getThumbnailUrl());
        assertEquals(2, ml.getVideo().getViews());
        assertEquals("myvid", ml.getVideo().getTitle());
        
        assertNotNull(ml.getLink());
        assertEquals("linkdesc", ml.getLink().getDescription());
        assertEquals("linktitle", ml.getLink().getTitle());
        assertEquals("linkheader", ml.getLink().getHeaderText());
        assertEquals("linktext", ml.getLink().getLinkText());
        assertEquals("http://linkurl", ml.getLink().getFullUrl());
        assertEquals("http://favicon", ml.getLink().getFaviconUrl());
        
        assertEquals("yes", ml.getType());
        assertEquals("http://twitterurl", ml.getUrl());        
    }
    
    @Test
    public void testGetFile() {   
        assertNull(messagePayloadStyle2.getFile());
                
        assertNotNull(messagePayloadStyle1.getFile());
        MessagePayload.File f = messagePayloadStyle1.getFile();
        assertEquals("filename", f.getName());
        assertEquals(999, f.getSize());
        assertEquals("http://fileurl", f.getUrl());
        assertEquals("http://thumburl", f.getThumbnailUrl());       
    }
        
    @Test 
    public void testGetMentions() {
        assertEquals("@mentionuser", messagePayloadStyle1.getMentions().getMentionName());
        assertEquals(555, messagePayloadStyle1.getMentions().getId());
        assertEquals("mention user", messagePayloadStyle1.getMentions().getName());
        assertEquals("mentionsself", messagePayloadStyle1.getMentions().getLinks().getSelf());
        
        assertEquals("@mention", messagePayloadStyle2.getMentions().getMentionName());
        assertEquals(75, messagePayloadStyle2.getMentions().getId());
        assertEquals("mention2", messagePayloadStyle2.getMentions().getName());
        assertEquals("2self", messagePayloadStyle2.getMentions().getLinks().getSelf());
    }
    
    @Test
    public void testGetMessageFormat() {
        assertNull(messagePayloadStyle1.getMessageFormat());
        assertEquals("html", messagePayloadStyle2.getMessageFormat());
    }
    
    @Test
    public void testGetColor() {
        assertNull(messagePayloadStyle1.getColor());
        assertEquals("red", messagePayloadStyle2.getColor());
    }
        
    @Test
    public void testGetId() {
        assertEquals("ABCDEF", messagePayloadStyle1.getId());
        assertEquals("ABC", messagePayloadStyle2.getId());
    }
    
    @Test
    public void testGetDate() {
        assertEquals("2014-11-01T15:19:20+00:00", messagePayloadStyle1.getDate());
        assertEquals("2014-11-01T15:19:20+00:00", messagePayloadStyle2.getDate());
    }
    
    @Test
    public void testGetMessage() {
        assertEquals("MY MESSAGE STYLE 1", messagePayloadStyle1.getMessage());
        assertEquals("hello world", messagePayloadStyle2.getMessage());
    }
    
    @Test
    public void testGetType() {
        assertEquals("test", messagePayloadStyle1.getType());
        assertEquals("test2", messagePayloadStyle2.getType());
    }
    
    @Test
    public void testMessageStyle1FromJSON() throws Exception {

        MessagePayload m = JsonParser.getInstance().fromJson(IOUtils.toString(this.getClass().getResourceAsStream("/message_style1.json")), Message.class).getMessagePayload();

        assertNotNull(m);
        assertEquals(MessagePayload.MessageType.FromUser, m.getMessageType());
        assertEquals("fullfrom", m.getFromName());

        MessagePayload.MessageLinks.TwitterUser t = m.getTwitterUser();
        assertNotNull(t);
        assertEquals(1, t.getFollowers());
        assertEquals("lonrery", t.getName());
        assertEquals("http://twit", t.getProfileImageUrl());
        assertEquals("roney", t.getScreenName());

        MessagePayload.MessageLinks ml = m.getMessageLinks();
        assertNotNull(ml);

        MessagePayload.MessageLinks.Image i = ml.getImage();
        assertNotNull(i);
        assertEquals("imgb64", i.getImage());
        assertEquals("myimage.jpg", i.getName());

        assertNotNull(ml.getTwitterUser()); // this is the same pretty much as above
        assertEquals(1, ml.getTwitterUser().getFollowers());
        assertEquals("lonrery", ml.getTwitterUser().getName());
        assertEquals("http://twit", ml.getTwitterUser().getProfileImageUrl());
        assertEquals("roney", ml.getTwitterUser().getScreenName());

        assertNotNull(ml.getTwitterStatus());
        assertEquals("statusname", ml.getTwitterStatus().getName());
        assertEquals("statuscreated", ml.getTwitterStatus().getCreated());
        assertEquals("http://statusurl", ml.getTwitterStatus().getProfileImageUrl());
        assertEquals("content", ml.getTwitterStatus().getText());
        assertEquals("src", ml.getTwitterStatus().getSource());
        assertEquals("screenname", ml.getTwitterStatus().getScreenName());

        assertNotNull(ml.getVideo());
        assertEquals("author", ml.getVideo().getAuthor());
        assertEquals("vidthumb", ml.getVideo().getThumbnailUrl());
        assertEquals(2, ml.getVideo().getViews());
        assertEquals("myvid", ml.getVideo().getTitle());

        assertNotNull(ml.getLink());
        assertEquals("linkdesc", ml.getLink().getDescription());
        assertEquals("linktitle", ml.getLink().getTitle());
        assertEquals("linkheader", ml.getLink().getHeaderText());
        assertEquals("linktext", ml.getLink().getLinkText());
        assertEquals("http://linkurl", ml.getLink().getFullUrl());
        assertEquals("http://favicon", ml.getLink().getFaviconUrl());

        assertEquals("yes", ml.getType());
        assertEquals("http://twitterurl", ml.getUrl());

        assertNotNull(m.getFile());
        MessagePayload.File f = m.getFile();
        assertEquals("filename", f.getName());
        assertEquals(999, f.getSize());
        assertEquals("http://fileurl", f.getUrl());
        assertEquals("http://thumburl", f.getThumbnailUrl());

        assertEquals("@mentionuser", m.getMentions().getMentionName());
        assertEquals(555, m.getMentions().getId());
        assertEquals("mention user", m.getMentions().getName());
        assertEquals("mentionsself", m.getMentions().getLinks().getSelf());

        assertNull(m.getMessageFormat());
        assertNull(m.getColor());
        assertEquals("ABCDEF", m.getId());
        assertEquals("2014-11-01T15:19:20+00:00", m.getDate());
        assertEquals("MY MESSAGE STYLE 1", m.getMessage());
        assertEquals("test", m.getType());
    }

    @Test
    public void testMessageStyle2FromJSON() throws Exception {
        String json = IOUtils.toString(this.getClass().getResourceAsStream("/message_style2.json"));
        Message container = JsonParser.getInstance().fromJson(json, Message.class);
        MessagePayload m = container.getMessagePayload();

        assertEquals(MessagePayload.MessageType.FromAddOn, m.getMessageType());
        assertEquals("ninja", m.getFromName());
        assertNull( m.getTwitterUser() );
        assertNull(m.getMessageLinks());
        assertNull(m.getFile());

        assertEquals("@mention", m.getMentions().getMentionName());
        assertEquals(75, m.getMentions().getId());
        assertEquals("mention2", m.getMentions().getName());
        assertEquals("2self", m.getMentions().getLinks().getSelf());

        assertEquals("html", m.getMessageFormat());
        assertEquals("red", m.getColor());
        assertEquals("ABC", m.getId());
        assertEquals("2014-11-01T15:19:20+00:00", m.getDate());
        assertEquals("hello world", m.getMessage());
        assertEquals("test2", m.getType());
    }


}