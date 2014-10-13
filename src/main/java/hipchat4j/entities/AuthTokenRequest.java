package hipchat4j.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * hipchat4j
 * 13/10/14/19:23
 */
public class AuthTokenRequest {

    private final String username;
    @SerializedName("grant_type")
    private final String grantType;
    private final String code;
    @SerializedName("redirect_uri")
    private final String redirectUri;
    private final String scope;
    private final String password;
    @SerializedName("refresh_token")
    private final String refreshToken;

    public AuthTokenRequest(String username, String grantType, String code, String redirectUri, List<String> scope, String password, String refreshToken) {
        this.username = username;
        this.grantType = grantType;
        this.code = code;
        this.redirectUri = redirectUri;
        this.scope = listToSpaceSeparated(scope);
        this.password = password;
        this.refreshToken = refreshToken;
    }

    public static String listToSpaceSeparated(List<String> list)
    {
        if (list==null||list.size()==0)
            return null;

        if (list.contains(null))
            throw new IllegalArgumentException("list cannot contain null");

        return String.join(" ", list);
    }

    public String getUsername() {
        return username;
    }

    public String getGrantType() {
        return grantType;
    }

    public String getCode() {
        return code;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public String getScope() {
        return scope;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getPassword() {
        return password;
    }

}
