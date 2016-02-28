package com.ruigoncalo.romeo.providers;

import android.content.Context;

import com.ruigoncalo.romeo.cache.LocalCacheManager;
import com.ruigoncalo.romeo.model.UserAuth;

/**
 * Created by ruigoncalo on 20/02/16.
 */
public class DataProvider {

    private static DataProvider ourInstance = new DataProvider();
    private UserAuth userAuth;

    public static DataProvider getInstance() {
        return ourInstance;
    }

    private DataProvider() {
    }

    public UserAuth getUserAuth(Context context){
        if(userAuth == null){
            userAuth = LocalCacheManager.getInstance().getUserAuth(context);
        }

        return userAuth;
    }

    public void saveUserAuth(Context context, UserAuth userAuth){
        LocalCacheManager.getInstance().saveUserAuth(context, userAuth);
    }
}
