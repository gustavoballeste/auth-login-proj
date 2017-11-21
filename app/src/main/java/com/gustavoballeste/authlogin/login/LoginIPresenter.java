package com.gustavoballeste.authlogin.login;

import android.view.View;
import android.widget.EditText;

import com.gustavoballeste.authlogin.data.remote.model.Token;

/**
 * Created by gustavoballeste on 20/11/17.
 */

public interface LoginIPresenter {

    void startService();
    void submit(EditText usernameEt, EditText passwordEt);
    void detach();
    void register();
    void insertDb(Token token);

}
