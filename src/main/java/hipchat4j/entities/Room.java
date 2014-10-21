package hipchat4j.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * hipchat4j
 * 09/10/14/20:12
 */
public class Room {

    public static class Statistics {
        public static class Links {
            private String self;

            public Links(String self) {
                this.self=self;
            }

            public String getSelf() {
                return self;
            }
        }
        private Links links;

        public Statistics(Links links) {
            this.links = links;
        }

        public Links getLinks() {
            return links;
        }
    }

    public static class Links {
        private String self;
        private String webhooks;
        private String members;
        private String participants;

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

    public static class Participant {

        public static class Links {
            private String self;

            public Links(String self) {
                this.self = self;
            }

            public String getSelf() {
                return self;
            }
        }

        @SerializedName("mention_name")
        private String mentionName;
        private int id;
        private Links links;
        private String name;

        public Participant(String mentionName, int id, Links links, String name) {
            this.mentionName = mentionName;
            this.id = id;
            this.links = links;
            this.name = name;
        }

        public String getMentionName() {
            return mentionName;
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

    public static class Owner {

        public static class Links {
            private String self;

            public Links(String self) {
                this.self = self;
            }

            public String getSelf() {
                return self;
            }
        }

        @SerializedName("mention_name")
        private String mentionName;
        private int id;
        private Links links;
        private String name;

        public Owner(String mentionName, int id, Links links, String name) {
            this.mentionName = mentionName;
            this.id = id;
            this.links = links;
            this.name = name;
        }

        public String getMentionName() {
            return mentionName;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Links getLinks() {
            return links;
        }
    }

    @SerializedName("xmpp_jid")
    private String xmppJid;
    private Statistics statistics;
    private String name;
    private Links links;
    private String created;
    @SerializedName("is_archived")
    private boolean isArchived;
    private String privacy;
    @SerializedName("is_guest_accessible")
    private boolean isGuestAccessible;
    private String topic;
    private List<Participant> participants;
    private Owner owner;
    private int id;
    @SerializedName("guest_access_url")
    private String guestAccessURL;

    public Room(String xmppJid,
                Statistics statistics,
                Links links,
                String name,
                boolean isArchived,
                String created,
                String privacy,
                boolean isGuestAccessible,
                String topic,
                List<Participant> participants,
                Owner owner,
                int id,
                String guestAccessURL)
    {
        this.xmppJid = xmppJid;
        this.statistics = statistics;
        this.links = links;
        this.name = name;
        this.isArchived = isArchived;
        this.created = created;
        this.privacy = privacy;
        this.isGuestAccessible = isGuestAccessible;
        this.topic = topic;
        this.participants = participants;
        this.owner = owner;
        this.id = id;
        this.guestAccessURL = guestAccessURL;
    }

    public String getXmppJid() {
        return xmppJid;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public String getName() {
        return name;
    }

    public Links getLinks() {
        return links;
    }

    public String getCreated() {
        return created;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public String getPrivacy() {
        return privacy;
    }

    public boolean getIsGuestAccessible() {
        return isGuestAccessible;
    }

    public String getTopic() {
        return topic;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public Owner getOwner() {
        return owner;
    }

    public int getId() {
        return id;
    }

    public String getGuestAccessURL() {
        return guestAccessURL;
    }
}
