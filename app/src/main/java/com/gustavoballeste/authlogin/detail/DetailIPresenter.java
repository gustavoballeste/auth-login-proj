package com.gustavoballeste.authlogin.detail;

import android.widget.EditText;

/**
 * Created by gustavoballeste on 20/11/17.
 */

public interface DetailIPresenter {

    void startService();
    void submit(EditText firstName, EditText lastName, EditText password);
}
