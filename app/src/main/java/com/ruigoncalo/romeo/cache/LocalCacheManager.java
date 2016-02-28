package com.ruigoncalo.romeo.cache;

import android.content.Context;
import android.content.SharedPreferences;

import com.ruigoncalo.romeo.model.Account;
import com.ruigoncalo.romeo.model.UserAuth;

/**
 * Created by ruigoncalo on 20/02/16.
 */
public class LocalCacheManager {

    private static final String USER_FILE = "com.ruigoncalo.romeo.USER_AUTH_PREFERENCE";

    private static LocalCacheManager ourInstance = new LocalCacheManager();

    public static LocalCacheManager getInstance() {
        return ourInstance;
    }

    private LocalCacheManager() {

    }

    public void saveUserAuth(Context context, UserAuth userAuth){
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Account.ID, userAuth.getAccount().getId());
        editor.putString(Account.USERNAME, userAuth.getAccount().getUsername());
        editor.putString(Account.DISPLAY_NAME, userAuth.getAccount().getDisplayName());
        editor.putString(Account.IDENTIFIER, userAuth.getAccount().getIdentifiers().get(0));
        editor.putString(Account.IMAGE_URL, userAuth.getAccount().getImageUrl());
        editor.putString(UserAuth.ACCESS_TOKEN, userAuth.getAccessToken());
        editor.putInt(UserAuth.EXPIRES_IN, userAuth.getExpiresIn());
        editor.putString(UserAuth.REFRESH_TOKEN, userAuth.getRefreshToken());
        editor.putString(UserAuth.TOKEN_TYPE, userAuth.getTokenType());
        editor.apply();
    }

    public UserAuth getUserAuth (Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_FILE, Context.MODE_PRIVATE);
        String id = sharedPreferences.getString(Account.ID, "");
        String username = sharedPreferences.getString(Account.USERNAME, "");
        String displayName = sharedPreferences.getString(Account.DISPLAY_NAME, "");
        String identifier = sharedPreferences.getString(Account.IDENTIFIER, "");
        String imageUrl = sharedPreferences.getString(Account.IMAGE_URL, "");
        String accessToken = sharedPreferences.getString(UserAuth.ACCESS_TOKEN, "");
        String refreshToken = sharedPreferences.getString(UserAuth.REFRESH_TOKEN, "");
        int expiresIn = sharedPreferences.getInt(UserAuth.EXPIRES_IN, 0);
        String tokenType = sharedPreferences.getString(UserAuth.TOKEN_TYPE, "");

        Account account = new Account(id, username, identifier, displayName, imageUrl);
        return new UserAuth(accessToken, expiresIn, refreshToken, tokenType, account);
    }

}
