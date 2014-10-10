package hipchat4j.entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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

}

