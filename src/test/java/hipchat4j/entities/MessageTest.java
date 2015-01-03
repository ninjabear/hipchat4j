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
                                    "aformat",
                                    "red",
                                    "2014-11-01T15:19:20+00:00",
                                    new Message.Mentions("@mention",
                                                        75,
                                                        new Message.Mentions.Links("2self"),
                                                        "mention2"),
                                    "hello world",
                                    "test",
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

}