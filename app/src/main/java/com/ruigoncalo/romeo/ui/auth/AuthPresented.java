package com.ruigoncalo.romeo.ui.auth;

import com.ruigoncalo.romeo.model.UserAuth;

/**
 * Created by ruigoncalo on 28/02/16.
 */
public interface AuthPresented {
    void isLoading(boolean loading);
    void showEmailSuccess();
    void showEmailError();
    void showCodeSuccess(UserAuth userAuth);
    void showCodeError();
}