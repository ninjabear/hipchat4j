package hipchat4j.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * hipchat4j
 * 20/10/14/18:55
 */
public class Session {

    private List<String> scopes;
    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("expires_in")
    private int expiresIn;
    private Client client;
    private Owner owner;
    @SerializedName("owner_type")
    private String ownerType;

    public Session(Client client, String accessToken, List<String> scopes, int expiresIn, Owner owner, String ownerType) {
        this.client = client;
        this.accessToken = accessToken;
        this.scopes = scopes;
        this.expiresIn = expiresIn;
        this.owner = owner;
        this.ownerType = ownerType;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public Client getClient() {
        return client;
    }

    public Owner getOwner() {
        return owner;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public static class Owner {

        @SerializedName("mention_name")
        private String mentionName;
        private int id;
        private Links links;
        private String name;

        public Owner(String mentionName, int id, String name, Links links) {
            this.mentionName = mentionName;
            this.id = id;
            this.name = name;
            this.links = links;
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

        public static class Links {
            private String self;

            public Links(String self) {
                this.self = self;
            }

            public String getSelf() {
                return self;
            }
        }
    }

    public static class Client {

        private Room room;
        private String id;
        @SerializedName("allowed_scopes")
        private List<String> allowedScopes;
        private String name;

        public Client(Room room, String id, List<String> allowedScopes, String name) {
            this.room = room;
            this.id = id;
            this.allowedScopes = allowedScopes;
            this.name = name;
        }

        public Room getRoom() {
            return room;
        }

        public String getId() {
            return id;
        }

        public List<String> getAllowedScopes() {
            return allowedScopes;
        }

        public String getName() {
            return name;
        }

        public static class Room {

            private int id;
            private String name;
            private Links links;

            public Room(int id, String name, Links links) {
                this.id = id;
                this.name = name;
                this.links = links;
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
        }

    }
}
