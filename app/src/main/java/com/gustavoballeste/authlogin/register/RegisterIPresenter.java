package com.gustavoballeste.authlogin.register;

import android.widget.EditText;

/**
 * Created by gustavoballeste on 20/11/17.
 */

public interface RegisterIPresenter {

    void startService();
    void submit(EditText usernameEt, EditText passwordEt);
    void detach();
}
