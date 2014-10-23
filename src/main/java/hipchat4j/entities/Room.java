package hipchat4j.entities;

import com.google.gson.annotations.SerializedName;
import hipchat4j.connector.ConnectorAbstract;
import hipchat4j.json.JsonParser;

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
    private Boolean isArchived;
    private String privacy;
    @SerializedName("is_guest_accessible")
    private Boolean isGuestAccessible;
    private String topic;
    private List<Participant> participants;
    private Owner owner;
    private int id;
    @SerializedName("guest_access_url")
    private String guestAccessURL;

    private ConnectorAbstract connector=null;

    public Room(int id, ConnectorAbstract lazyloader)
    {
        this.id = id;
        connector=lazyloader;
    }

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

    private void lazyLoadSelf()
    {
        Room r = JsonParser.getInstance().fromJson(connector.get("/v2/room/" + id), Room.class);

        this.xmppJid = r.getXmppJid();
        this.statistics = r.getStatistics();
        this.links = r.getLinks();
        this.name = r.getName();
        this.isArchived = r.isArchived();
        this.created = r.getCreated();
        this.privacy = r.getPrivacy();
        this.isGuestAccessible = r.isGuestAccessible();
        this.topic = r.getTopic();
        this.participants = r.getParticipants();
        this.owner = r.getOwner();
        this.guestAccessURL = r.getGuestAccessURL();

    }

    public String getXmppJid() {

        if (xmppJid==null)
            lazyLoadSelf();

        return xmppJid;
    }

    public Statistics getStatistics() {

        if (statistics==null)
            lazyLoadSelf();

        return statistics;
    }

    public String getName() {

        if (name==null)
            lazyLoadSelf();

        return name;
    }

    public Links getLinks() {

        if (links==null)
            lazyLoadSelf();

        return links;
    }

    public String getCreated() {
        if (created==null)
            lazyLoadSelf();

        return created;
    }

    public boolean isArchived()
     {
         if (isArchived==null)
             lazyLoadSelf();
        return isArchived;
    }

    public String getPrivacy() {

        if (privacy==null)
            lazyLoadSelf();

        return privacy;
    }

    public boolean isGuestAccessible()
    {
        if (isGuestAccessible==null)
            lazyLoadSelf();

        return isGuestAccessible;
    }

    public String getTopic() {

        if (topic==null)
            lazyLoadSelf();

        return topic;
    }

    public List<Participant> getParticipants() {

        if (participants==null)
            lazyLoadSelf();

        return participants;
    }

    public Owner getOwner() {

        if (owner==null)
            lazyLoadSelf();

        return owner;
    }

    public int getId() {
        return id;
    }

    public String getGuestAccessURL() {

        if (guestAccessURL==null)
            lazyLoadSelf();

        return guestAccessURL;
    }
}
