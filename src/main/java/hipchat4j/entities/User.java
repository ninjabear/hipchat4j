package hipchat4j.entities;

import com.google.gson.annotations.SerializedName;

/**
 * hipchat4j
 * 09/10/14/20:39
 */
public class User {

    @SerializedName("mention_name")
    public String mentionName;
    public int id;

    public static class Links {
        private String self;

        public Links(String self) {
            this.self = self;
        }

        public String getSelf() {
            return self;
        }
    }

    private Links links;
    private String name;

    public User(String mentionName, int id, Links links, String name) {
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
