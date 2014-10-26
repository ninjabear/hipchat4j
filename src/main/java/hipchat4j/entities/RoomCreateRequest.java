package hipchat4j.entities;

import com.google.gson.annotations.SerializedName;

/**
 * hipchat4j
 * 26/10/14/15:14
 */
public class RoomCreateRequest {

    private String topic;
    @SerializedName("guest_access")
    private Boolean isGuestAccess;
    private String name;
    @SerializedName("owner_user_id")
    private String ownerUserId;
    @SerializedName("privacy")
    private String privacyMode;

    public RoomCreateRequest(String topic, Boolean isGuestAccess, String name, String ownerUserId, String privacyMode) {
        this.topic = topic;
        this.isGuestAccess = isGuestAccess;
        this.name = name;
        this.ownerUserId = ownerUserId;
        this.privacyMode = privacyMode;
    }

    public String getTopic() {
        return topic;
    }

    public Boolean getIsGuestAccess() {
        return isGuestAccess;
    }

    public String getName() {
        return name;
    }

    public String getOwnerUserId() {
        return ownerUserId;
    }

    public String getPrivacyMode() {
        return privacyMode;
    }

}
