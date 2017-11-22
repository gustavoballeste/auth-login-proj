package com.gustavoballeste.authlogin.register;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

public interface RegisterPresenterContract {

    void startService();
    void submit(EditText usernameEt, EditText passwordEt, Context context, View view);
    void detach();
}
