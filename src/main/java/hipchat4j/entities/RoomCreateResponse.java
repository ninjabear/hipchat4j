package hipchat4j.entities;

/**
 * hipchat4j
 * 26/10/14/15:26
 */
public class RoomCreateResponse {

    public static class Links {
        private String self;

        public Links(String self) {
            this.self = self;
        }

        public String getSelf() {
            return self;
        }
    }

    private String id;
    private Links links;

    public RoomCreateResponse(String id, Links links) {
        this.links = links;
        this.id = id;
    }

    public Links getLinks() {
        return links;
    }

    public String getId() {
        return id;
    }
}
