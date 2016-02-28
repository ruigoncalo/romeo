package com.ruigoncalo.romeo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ruigoncalo on 20/02/16.
 */
public class UserAuth {

    public final static String ACCESS_TOKEN = "access_token";
    public final static String EXPIRES_IN = "expires_in";
    public final static String REFRESH_TOKEN = "refresh_token";
    public final static String TOKEN_TYPE = "token_type";
    public final static String STATUS = "status";

    public UserAuth(){

    }

    public UserAuth(String accessToken, int expiresIn, String refreshToken, String tokenType, Account account){
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.refreshToken = refreshToken;
        this.tokenType = tokenType;
        this.account = account;
    }

    @SerializedName(ACCESS_TOKEN)
    private String accessToken;

    @SerializedName(EXPIRES_IN)
    private int expiresIn;

    @SerializedName(REFRESH_TOKEN)
    private String refreshToken;

    @SerializedName(TOKEN_TYPE)
    private String tokenType;

    private Account account;

    private String status;

    private List<Stream> streams;

    private String cursor;


    public String getAccessToken() {
        return accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public Account getAccount() {
        return account;
    }

    public String getStatus() {
        return status;
    }

    public List<Stream> getStreams() {
        return streams;
    }

    public String getCursor() {
        return cursor;
    }

    public boolean isValid(){
        return accessToken != null && !accessToken.isEmpty() &&
                refreshToken != null && !refreshToken.isEmpty() &&
                expiresIn != 0 && tokenType != null && !tokenType.isEmpty() &&
                account.isValid();
    }
}
