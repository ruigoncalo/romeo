package com.ruigoncalo.romeo.ui.auth;

import com.ruigoncalo.romeo.api.ApiService;
import com.ruigoncalo.romeo.model.Challenge;
import com.ruigoncalo.romeo.model.UserAuth;
import com.ruigoncalo.romeo.ui.Presenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ruigoncalo on 28/02/16.
 */
public class AuthPresenter extends Presenter<AuthPresented> {

    @Override
    public void onStart(AuthPresented presented) {
        super.onStart(presented);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public void requestChallenge(String identifier){
        getPresented().isLoading(true);
        ApiService.getInstance().requestChallenge(identifier, new Callback<Challenge>() {
            @Override
            public void onResponse(Call<Challenge> call, Response<Challenge> response) {
                if(getPresented() != null){
                    getPresented().isLoading(false);
                    getPresented().showEmailSuccess();
                }
            }

            @Override
            public void onFailure(Call<Challenge> call, Throwable t) {
                if(getPresented() != null){
                    getPresented().isLoading(false);
                    getPresented().showEmailError();
                }
            }
        });
    }

    public void requestAuth(String identifier, String secret){
        ApiService.getInstance().requestAuth(identifier, secret, new Callback<UserAuth>() {
            @Override
            public void onResponse(Call<UserAuth> call, Response<UserAuth> response) {
                if(getPresented() != null){
                    getPresented().isLoading(false);
                    getPresented().showCodeSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<UserAuth> call, Throwable t) {
                if(getPresented() != null){
                    getPresented().isLoading(false);
                    getPresented().showCodeError();
                }
            }
        });
    }


}
