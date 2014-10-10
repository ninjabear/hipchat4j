package hipchat4j.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item {

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
