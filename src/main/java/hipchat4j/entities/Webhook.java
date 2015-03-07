package hipchat4j.entities;

/**
 * hipchat4j / Ed
 * 07/03/2015 17:41
 */
public class Webhook {

    private Room room;
    private User creator;

    private String url;
    private String pattern;
    private String created;
    private String event;
    private String name;

    public Webhook(Room room, User creator, String url, String pattern, String created, String event, String name) {
        this.room = room;
        this.creator = creator;
        this.url = url;
        this.pattern = pattern;
        this.created = created;
        this.event = event;
        this.name = name;
    }

    public Room getRoom() {
        return room;
    }

    public User getCreator() {
        return creator;
    }

    public String getUrl() {
        return url;
    }

    public String getPattern() {
        return pattern;
    }

    public String getCreated() {
        return created;
    }

    public String getEvent() {
        return event;
    }

    public String getName() {
        return name;
    }
}
