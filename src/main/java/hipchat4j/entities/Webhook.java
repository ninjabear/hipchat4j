package hipchat4j.entities;

/**
 * hipchat4j / Ed
 * 07/03/2015 17:41
 */
public class Webhook {

    public static class RoomInfo {
        private int id;

        public static class Links {
            private String self,
                           webhooks,
                           members,
                           participants;

            public Links(String self, String webhooks, String members, String participants) {
                this.self = self;
                this.webhooks = webhooks;
                this.members = members;
                this.participants = participants;
            }

            public String getSelf() {
                return self;
            }

            public String getWebhooks() {
                return webhooks;
            }

            public String getMembers() {
                return members;
            }

            public String getParticipants() {
                return participants;
            }
        }

        private Links links;
        private String name;

        public RoomInfo(int id, Links links, String name) {
            this.id = id;
            this.links = links;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public Links getLinks() {
            return links;
        }

        public String getName() {
            return name;
        }
    }

    private RoomInfo room;
    private User creator;

    private String url;
    private String pattern;
    private String created;
    private String event;
    private String name;

    public Webhook(RoomInfo room, User creator, String url, String pattern, String created, String event, String name) {
        this.room = room;
        this.creator = creator;
        this.url = url;
        this.pattern = pattern;
        this.created = created;
        this.event = event;
        this.name = name;
    }

    public RoomInfo getRoom() {
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
