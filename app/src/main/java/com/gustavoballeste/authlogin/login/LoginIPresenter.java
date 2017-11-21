package com.gustavoballeste.authlogin.login;

import android.widget.EditText;

/**
 * Created by gustavoballeste on 20/11/17.
 */

public interface LoginIPresenter {

    void startService();
    void submit(EditText usernameEt, EditText passwordEt);
    void detach();
}
