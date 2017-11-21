package com.gustavoballeste.authlogin.detail;

import android.widget.TextView;

import com.gustavoballeste.authlogin.data.model.User;

/**
 * Created by gustavoballeste on 20/11/17.
 */

public interface DetailIView {

    void updateScreen(User user);
    void refreshData(TextView textView, String newValue, String message);
}
