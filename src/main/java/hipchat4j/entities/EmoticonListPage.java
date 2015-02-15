package hipchat4j.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * hipchat4j
 * 10/10/14/18:19
 */
public class EmoticonListPage {

    @SerializedName("items")
    private List<Item> items;
    @SerializedName("links")
    private Link links;
    @SerializedName("maxResults")
    private int maxResults;
    @SerializedName("startIndex")
    private int startIndex;

    public EmoticonListPage(List<Item> items, int startIndex, int maxResults, Link links) {
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

    public Link getLinks() {
        return links;
    }

    public int getMaxResults() {
        return maxResults;
    }

    public static class Item {

        @SerializedName("id")
        private int id;
        @SerializedName("links")
        private Link links;
        @SerializedName("shortcut")
        private String shortcut;
        @SerializedName("url")
        private String url;


        public Item(int id, Link links, String shortcut, String url) {
            this.id = id;
            this.links = links;
            this.shortcut = shortcut;
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        public Link getLinks() {
            return links;
        }

        public int getId() {
            return id;
        }

        public String getShortcut() {
            return shortcut;
        }

    }

    public static class Link {

        @SerializedName("self")
        private String self = "";
        @SerializedName("prev")
        private String prev = "";
        @SerializedName("next")
        private String next = "";

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

}

