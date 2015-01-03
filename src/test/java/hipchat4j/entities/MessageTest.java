package hipchat4j.entities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MessageTest {

    private Message messageStyle1, messageStyle2;

    @Before
    public void setUp() throws Exception {

        Message.From from = new Message.From("@mentionName", 123, new Message.From.Links("selffrom"), "fullfrom");
        Message.MessageLinks messageLinks = new Message.MessageLinks(new Message.MessageLinks.TwitterUser(1, "lonrery", "http://twit", "roney"),
                "http://twitterurl",
                new Message.MessageLinks.Image("imgb64", "myimage.jpg"),
                new Message.MessageLinks.TwitterStatus("statusname", "statuscreated", "http://statusurl", "content", "src", "screenname"),
                new Message.MessageLinks.Video("author", "vidthumb", 2, "myvid"),
                new Message.MessageLinks.Link("linkdesc", "linktitle", "linkheader", "linktext", "http://linkurl", "http://favicon"), 
                "yes"
        );
        Message.File file = new Message.File("http://fileurl", "http://thumburl", "filename", 999);
        Message.Mentions mentions = new Message.Mentions("@mentionuser", 555, new Message.Mentions.Links("mentionsself"), "mention user");

        messageStyle1 = new Message(from,
                                    messageLinks,
                                    file,
                                    mentions,
                                    "MY MESSAGE STYLE 1",
                                    "test",
                                    "ABCDEF"
                                    );

        messageStyle2 = new Message("ninja",
                                    "html",
                                    "red",
                                    "2014-11-01T15:19:20+00:00",
                                    new Message.Mentions("@mention",
                                                        75,
                                                        new Message.Mentions.Links("2self"),
                                                        "mention2"),
                                    "hello world",
                                    "test2",
                                    "ABC");
    }

    @Test
    public void testGetMessageType()
    {
        assertEquals(Message.MessageType.FromAddOn, messageStyle2.getMessageType());
        assertEquals(Message.MessageType.FromUser, messageStyle1.getMessageType());
    }

    @Test
    public void testGetFrom() {
        assertEquals("fullfrom", messageStyle1.getFromName());
        assertEquals("ninja", messageStyle2.getFromName());
    }

    @Test
    public void testGetTwitterUser() {
        Message.MessageLinks.TwitterUser t = messageStyle1.getTwitterUser();
        Message.MessageLinks.TwitterUser t2 = messageStyle2.getTwitterUser();

        assertNull(t2);
        assertNotNull(t);

        assertEquals(1, t.getFollowers());
        assertEquals("lonrery", t.getName());
        assertEquals("http://twit", t.getProfileImageUrl());
        assertEquals("roney", t.getScreenName());
    }
    
    @Test
    public void testGetMessageLinks() {
        assertNull(messageStyle2.getMessageLinks());  
        
        Message.MessageLinks ml = messageStyle1.getMessageLinks();        
        assertNotNull(ml);
        
        Message.MessageLinks.Image i = ml.getImage();
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
        assertNull(messageStyle2.getFile());
                
        assertNotNull(messageStyle1.getFile());        
        Message.File f = messageStyle1.getFile();
        assertEquals("filename", f.getName());
        assertEquals(999, f.getSize());
        assertEquals("http://fileurl", f.getUrl());
        assertEquals("http://thumburl", f.getThumbnailUrl());       
    }
        
    @Test 
    public void testGetMentions() {
        assertEquals("@mentionuser", messageStyle1.getMentions().getMentionName());
        assertEquals(555, messageStyle1.getMentions().getId());
        assertEquals("mention user", messageStyle1.getMentions().getName());
        assertEquals("mentionsself", messageStyle1.getMentions().getLinks().getSelf());
        
        assertEquals("@mention", messageStyle2.getMentions().getMentionName());
        assertEquals(75, messageStyle2.getMentions().getId());
        assertEquals("mention2", messageStyle2.getMentions().getName());
        assertEquals("2self", messageStyle2.getMentions().getLinks().getSelf());
    }
    
    @Test
    public void testGetMessageFormat() {
        assertNull(messageStyle1.getMessageFormat());
        assertEquals("html", messageStyle2.getMessageFormat());        
    }
    
    @Test
    public void testGetColor() {
        assertNull(messageStyle1.getColor());
        assertEquals("red", messageStyle2.getColor());
    }
        
    @Test
    public void testGetId() {
        assertEquals("ABCDEF", messageStyle1.getId());
        assertEquals("ABC", messageStyle2.getId());
    }
    
    @Test
    public void testGetDate() {
        assertNull(messageStyle1.getDate());
        assertEquals("2014-11-01T15:19:20+00:00", messageStyle2.getDate());
    }
    
    @Test
    public void testGetMessage() {
        assertEquals("MY MESSAGE STYLE 1", messageStyle1.getMessage());
        assertEquals("hello world", messageStyle2.getMessage());
    }
    
    @Test
    public void testGetType() {
        assertEquals("test", messageStyle1.getType());
        assertEquals("test2", messageStyle2.getType());                
    }
    
    @Test
    public void testFromJSON() {
        
        // test above but from json expected format(s)
        
    }
    

}