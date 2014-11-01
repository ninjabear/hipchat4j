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
                new Message.MessageLinks.Link("linkdesc", "linktitle", "linkheader", "linktext", "http://linkurl", "http://favicon"), "yes"
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

}