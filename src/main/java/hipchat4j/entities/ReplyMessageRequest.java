package hipchat4j.entities;

/**
 * hipchat4j / Ed
 * 07/03/2015 16:19
 */
public class ReplyMessageRequest {

    private String parentMessageId;
    private String message;

    public ReplyMessageRequest(String parentMessageId, String message) {
        this.parentMessageId = parentMessageId;
        this.message = message;
    }

    public String getParentMessageId() {
        return parentMessageId;
    }

    public String getMessage() {
        return message;
    }

}
