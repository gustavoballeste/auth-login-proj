package com.gustavoballeste.authlogin.splash;

import android.util.Log;

import com.gustavoballeste.authlogin.data.dao.DAOFactory;
import com.gustavoballeste.authlogin.data.remote.model.Token;

public class SplashPresenter implements SplashIPresenter {

    private static final String TAG = SplashPresenter.class.getName();
    private SplashIView view;
    private Token token;

    public SplashPresenter(SplashIView view) {
        this.view = view;
    }

    public void checkAuthStatus(){

        token = DAOFactory.getTokenDAO().get();

        if (token.getValue()==null) {
            Log.d(TAG, "***** No Token *****");
            view.startLogin();
        } else {
            Log.d(TAG, "***** Has Token *****");
            view.startDetails();
        }
    }

    @Override
    public void detach() {
        this.view = null;
    }
}
