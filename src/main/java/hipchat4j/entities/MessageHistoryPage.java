package hipchat4j.entities;

import java.util.List;

/**
 * hipchat4j / Ed
 * 15/02/2015 02:10
 */
public class MessageHistoryPage {

    public static class Link {

        private String self;
        private String prev;
        private String next;

        public Link(String self, String prev, String next) {
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

    private List<MessagePayload> items;
    private int startIndex;
    private int maxResults;
    private Link links;

    public MessageHistoryPage(List<MessagePayload> items, int startIndex, int maxResults, Link links) {
        this.items = items;
        this.startIndex = startIndex;
        this.maxResults = maxResults;
        this.links = links;
    }

    public List<MessagePayload> getItems() {
        return items;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getMaxResults() {
        return maxResults;
    }

    public Link getLinks() {
        return links;
    }

}
