package hipchat4j.entities;

/**
 * hipchat4j / Ed
 * 07/03/2015 15:50
 */
public class NotificationRequest {

    private String color = "yellow";
    private String message = "";
    private boolean notify = false;
    private String messageFormat = "html";

    public NotificationRequest(String color, String message, boolean notify, String messageFormat) {
        this.color = color;
        this.message = message;
        this.notify = notify;
        this.messageFormat = messageFormat;
    }

    public String getColor() {
        return color;
    }

    public String getMessage() {
        return message;
    }

    public boolean isNotify() {
        return notify;
    }

    public String getMessageFormat() {
        return messageFormat;
    }
}
