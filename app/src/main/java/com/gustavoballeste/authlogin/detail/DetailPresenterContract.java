package com.gustavoballeste.authlogin.detail;

import android.widget.TextView;

public interface DetailPresenterContract {

    void startService();
    void sendPostRetrieve();
    void sendPostUpdateFirstName(String newValue, TextView textView, String name, String message);
    void sendPostUpdateLastName(String newValue, TextView textView, String name, String message);
    void sendPostUpdatePassword(String passwordName, TextView textView, String name, String message);
    void updateRemote(String newValue, TextView textView, String name);
    void logout();
}
