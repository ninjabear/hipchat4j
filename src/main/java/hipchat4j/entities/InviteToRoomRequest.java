package hipchat4j.entities;

/**
 * Created by Ed on 15/02/2015.
 */
public class InviteToRoomRequest {

    private String reason;

    public InviteToRoomRequest(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

}
