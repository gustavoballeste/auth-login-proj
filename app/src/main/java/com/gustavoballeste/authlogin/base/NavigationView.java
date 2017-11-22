package com.gustavoballeste.authlogin.base;

import android.app.Activity;
import android.content.Intent;

public interface NavigationView {

    default void startNewActivity(Activity activity, Class<?> cls) {
        Intent intent = new Intent(activity,
                cls);
        activity.startActivity(intent);
        activity.finish();
    }
}
