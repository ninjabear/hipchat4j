package hipchat4j.entities;

import com.google.gson.annotations.SerializedName;

/**
 * hipchat4j / Ed
 * 07/03/2015 17:14
 */
public class RoomStatistics {

    @SerializedName("messages_sent")
    private int messagesSent;

    @SerializedName("last_active")
    private String lastActive;

    public RoomStatistics(int messagesSent, String lastActive) {
        this.messagesSent = messagesSent;
        this.lastActive = lastActive;
    }

    public int getMessagesSent() {
        return messagesSent;
    }

    public String getLastActive() {
        return lastActive;
    }
}
