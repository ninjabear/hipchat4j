package hipchat4j.entities;

import com.google.gson.annotations.SerializedName;

/**
 * hipchat4j
 * 26/10/14/16:52
 */
public class RoomUpdateRequest {

    private String name;
    private String privacy;
    @SerializedName("is_archived")
    private boolean isArchived;
    @SerializedName("is_guest_accessible")
    private boolean isGuestAccessible;
    private String topic;
    private Owner owner;

    public RoomUpdateRequest(String name, String topic, String privacy, boolean isArchived, boolean isGuestAccessible, String ownerId) {
        this.name = name;
        this.privacy = privacy;
        this.isArchived = isArchived;
        this.topic = topic;
        this.isGuestAccessible = isGuestAccessible;
        this.owner = new Owner(ownerId);
    }

    public String getName() {
        return name;
    }

    public String getPrivacy() {
        return privacy;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public boolean isGuestAccessible() {
        return isGuestAccessible;
    }

    public String getOwnerId() {
        return owner == null ? null : owner.id;
    }

    public String getTopic() {
        return topic;
    }

    public static class Owner {
        private String id;

        public Owner(String id) {
            this.id = id;
        }
    }
}
