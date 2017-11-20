package com.gustavoballeste.authlogin.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.gustavoballeste.authlogin.R;
import com.gustavoballeste.authlogin.login.LoginIActivity;

public class SplashActivity extends AppCompatActivity implements SplashIView {

    SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        presenter = new SplashPresenter(this);

        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.checkAuthStatus();
            }
        }, 2000);
    }

    @Override
    public void startLogin() {
        Intent intent = new Intent(SplashActivity.this,
                LoginIActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void startDetails() {
        //TODO
        //Open detail screen

        finish();
    }

    @Override
    public void startRegister() {
        //TODO
        //Open create user screen

        finish();
    }

}
