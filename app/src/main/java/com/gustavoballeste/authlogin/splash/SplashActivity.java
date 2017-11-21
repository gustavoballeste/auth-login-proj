package com.gustavoballeste.authlogin.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.gustavoballeste.authlogin.R;
import com.gustavoballeste.authlogin.detail.DetailActivity;
import com.gustavoballeste.authlogin.login.LoginActivity;

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
                LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void startDetails() {
        Intent intent = new Intent(SplashActivity.this,
                DetailActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void startRegister() {
        //TODO
        //Open create user screen

        finish();
    }

    @Override
    protected void onDestroy() {
        if (presenter != null) presenter.detach();
        super.onDestroy();
    }
}
