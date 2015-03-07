package hipchat4j.entities;

/**
 * hipchat4j / Ed
 * 07/03/2015 17:25
 */
public class TopicChangeRequest {

    private String topic;

    public TopicChangeRequest(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }
}
