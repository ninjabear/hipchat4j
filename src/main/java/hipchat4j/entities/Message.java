package hipchat4j.entities;

import com.google.gson.annotations.SerializedName;

/**
 * hipchat4j / Ed
 * 14/02/2015 15:01
 */
public class Message {

    @SerializedName("message")
    private MessagePayload messagePayload;

    public Message(MessagePayload messagePayload) {
        this.messagePayload = messagePayload;
    }

    public MessagePayload getMessagePayload() {
        return messagePayload;
    }

    public void setMessagePayload(MessagePayload messagePayload) {
        this.messagePayload = messagePayload;
    }
}
