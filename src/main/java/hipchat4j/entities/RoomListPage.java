package hipchat4j.entities;

import java.util.List;

/**
 * hipchat4j
 * 23/10/14/19:00
 */
public class RoomListPage {

    private List<Item> items;
    private int startIndex;
    private int maxResults;
    private Links links;

    public RoomListPage(List<Item> items, int startIndex, int maxResults, Links links) {
        this.items = items;
        this.startIndex = startIndex;
        this.maxResults = maxResults;
        this.links = links;
    }

    public List<Item> getItems() {
        return items;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public Links getLinks() {
        return links;
    }

    public int getMaxResults() {
        return maxResults;
    }

    public static class Item {
        private int id;
        private Links links;
        private String name;

        public Item(int id, Links links, String name) {
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

            public String getParticipants() {
                return participants;
            }

            public String getMembers() {
                return members;
            }
        }
    }

    public static class Links {
        private String self;
        private String prev;
        private String next;

        public Links(String self, String prev, String next) {
            this.self = self;
            this.prev = prev;
            this.next = next;
        }

        public String getSelf() {
            return self;
        }

        public String getPrev() {
            return prev;
        }

        public String getNext() {
            return next;
        }
    }
}
