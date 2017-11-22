package com.gustavoballeste.authlogin.login;

import android.view.View;
import android.widget.EditText;

import com.gustavoballeste.authlogin.data.remote.model.Token;

public interface LoginPresenterContract {

    void startService();
    void submit(EditText usernameEt, EditText passwordEt, View view);
    void detach();
    void register();
    void insertToken(Token token);

}
