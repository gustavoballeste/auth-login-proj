package com.gustavoballeste.authlogin.splash;

import android.content.Context;
import android.util.Log;

import com.gustavoballeste.authlogin.data.dao.AppDatabase;
import com.gustavoballeste.authlogin.data.remote.model.Token;

public class SplashPresenter implements SplashIPresenter {

    private static final String TAG = SplashPresenter.class.getName();
    private SplashIView view;
    private Token token;
    private Context context;

    public SplashPresenter(SplashIView view, Context c) {
        this.view = view;
        context = c;
    }

    public void checkAuthStatus(){

        token = AppDatabase.getAppDatabase(context.getApplicationContext()).tokenDao().get();
        if (token==null) {
            view.startLogin();
        } else {
            view.startDetails();
        }
    }

    @Override
    public void detach() {
        this.view = null;
    }
}
