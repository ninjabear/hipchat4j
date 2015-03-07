package hipchat4j.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * hipchat4j / Ed
 * 07/03/2015 14:52
 */
public class UserListPage {

    @SerializedName("items")
    private List<User> users;
    private int startIndex;
    private int maxResults;

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

    private Links links;

    public UserListPage(List<User> users, int startIndex, int maxResults, Links links) {
        this.users = users;
        this.startIndex = startIndex;
        this.maxResults = maxResults;
        this.links = links;
    }

    public List<User> getUsers() {
        return users;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getMaxResults() {
        return maxResults;
    }

    public Links getLinks() {
        return links;
    }

}
