package hipchat4j.entities;

import com.google.gson.annotations.SerializedName;

/**
 * hipchat4j
 * 14/10/14/20:32
 */
public class AuthTokenResponse {

    @SerializedName("access_token")
    private String accessToken = "";
    @SerializedName("expires_in")
    private int expiresIn = 0;
    @SerializedName("group_name")
    private String groupName = "";
    @SerializedName("token_type")
    private String tokenType = "bearer";
    private String scope = "";
    @SerializedName("group_id")
    private int groupId;

    public AuthTokenResponse(String accessToken, int expiresIn, String groupName, String tokenType, String scope, int groupId) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.groupName = groupName;
        this.tokenType = tokenType;
        this.scope = scope;
        this.groupId = groupId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getScope() {
        return scope;
    }

    public int getGroupId() {
        return groupId;
    }

}
