package hipchat4j.entities;

import com.google.gson.annotations.SerializedName;

public class Link {

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
