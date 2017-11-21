package com.gustavoballeste.authlogin.detail;

import android.widget.EditText;
import com.gustavoballeste.authlogin.data.remote.model.UserUpdate;

/**
 * Created by gustavoballeste on 20/11/17.
 */

public interface DetailIPresenter {

    void startService();
    void submitRetrieve();
    void sendPostRetrieve();
    void sendPostUpdateFirstName(String firstName);
    void sendPostUpdateLastName(String lastName);
    void sendPostUpdatePassword(String passwordName);
}
