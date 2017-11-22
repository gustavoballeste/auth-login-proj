package com.gustavoballeste.authlogin.splash;

import android.app.Activity;
import android.content.Context;

import com.gustavoballeste.authlogin.app.App;
import com.gustavoballeste.authlogin.data.dao.AppDatabase;
import com.gustavoballeste.authlogin.data.remote.model.Token;
import com.gustavoballeste.authlogin.detail.DetailActivity;
import com.gustavoballeste.authlogin.login.LoginActivity;

public class SplashPresenter implements SplashPresenterContract {

    private static final String TAG = SplashPresenter.class.getName();
    private SplashViewContract view;
    private Token token;

    public SplashPresenter(SplashViewContract view, Context c) {
        this.view = view;
    }

    public void checkAuthStatus(){

        token = AppDatabase.getAppDatabase(App.getAppContext()).tokenDao().get();
        if (token==null) {
            view.startNewActivity((Activity)view, LoginActivity.class);
        } else {
            view.startNewActivity((Activity)view, DetailActivity.class);
        }
    }

    @Override
    public void detach() {
        this.view = null;
    }
}
