package com.gustavoballeste.authlogin.detail;

import android.widget.TextView;

import com.gustavoballeste.authlogin.data.model.User;

public interface DetailIViewContract {

    void updateScreen(User user);
    void refreshData(TextView textView, String newValue, String message);
}
