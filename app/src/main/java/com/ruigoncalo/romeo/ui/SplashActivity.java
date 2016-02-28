package com.ruigoncalo.romeo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ruigoncalo.romeo.model.UserAuth;
import com.ruigoncalo.romeo.providers.DataProvider;
import com.ruigoncalo.romeo.ui.auth.AuthActivity;
import com.ruigoncalo.romeo.ui.streams.StreamsActivity;

/**
 * Created by ruigoncalo on 28/02/16.
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        navigateToNextScreen();
    }

    private void navigateToNextScreen(){
        UserAuth userAuth = DataProvider.getInstance().getUserAuth(this);
        if(userAuth.isValid()){
            Intent intent = new Intent(this, StreamsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, AuthActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        finish();
    }

}

