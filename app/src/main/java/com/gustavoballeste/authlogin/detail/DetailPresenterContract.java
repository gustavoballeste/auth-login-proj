package com.gustavoballeste.authlogin.detail;

import android.widget.TextView;

public interface DetailPresenterContract {

    void startService();
    void sendPostRetrieve();
    void sendPostUpdateFirstName(String firstName);
    void sendPostUpdateLastName(String lastName);
    void sendPostUpdatePassword(String passwordName);
    void updateValue(String newValue, TextView textView, String name);
}
