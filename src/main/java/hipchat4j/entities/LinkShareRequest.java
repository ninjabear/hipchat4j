package hipchat4j.entities;

/**
 * hipchat4j / Ed
 * 07/03/2015 17:06
 */
public class LinkShareRequest {

    private String message;
    private String link;

    public LinkShareRequest(String message, String link) {
        this.message = message;
        this.link = link;
    }

    public String getMessage() {
        return message;
    }

    public String getLink() {
        return link;
    }
}
