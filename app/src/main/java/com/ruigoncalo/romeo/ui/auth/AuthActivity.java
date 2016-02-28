package com.ruigoncalo.romeo.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ruigoncalo.romeo.R;
import com.ruigoncalo.romeo.model.UserAuth;
import com.ruigoncalo.romeo.providers.DataProvider;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ruigoncalo on 28/02/16.
 */
public class AuthActivity extends AppCompatActivity {

    @Bind(R.id.text_email) EditText textEmail;
    @Bind(R.id.text_code) EditText textCode;
    @Bind(R.id.button_request_code) Button buttonRequestCode;
    @Bind(R.id.button_submit) Button buttonSubmit;

    private RegisterPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        ButterKnife.bind(this);
        presenter = new RegisterPresenter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart(this);
    }

    @Override
    protected void onStop() {
        presenter.onStop();
        super.onStop();
    }

    @OnClick(R.id.button_request_code)
    public void onButtonRequestCodeClick() {
        if (isValid(textEmail)) {
            presenter.requestChallenge(textEmail.getText().toString());
        }
    }

    @OnClick(R.id.button_submit)
    public void onButtonSubmitClick() {
        if(isValid(textCode)){
            presenter.requestAuth(
                    textEmail.getText().toString(),
                    textCode.getText().toString());
        }
    }

    private boolean isValid(EditText editText) {
        return textEmail.getText().length() > 0;
    }

    @Override
    public void isLoading(boolean loading) {
        buttonRequestCode.setEnabled(!loading);
        buttonSubmit.setEnabled(!loading);
    }

    @Override
    public void showEmailSuccess() {
        Toast.makeText(getApplicationContext(), "Check your email and introduce the code", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showEmailError() {
        Toast.makeText(getApplicationContext(), "Invalid email", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showCodeSuccess(UserAuth userAuth) {
        if(userAuth != null && userAuth.isValid()){
            DataProvider.getInstance().saveUserAuth(this, userAuth);
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Error on saving user", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void showCodeError() {
        Toast.makeText(getApplicationContext(), "Invalid code", Toast.LENGTH_LONG).show();
    }
}
