package com.gustavoballeste.authlogin.data.remote.model.util;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.gustavoballeste.authlogin.data.model.User;

import java.lang.reflect.Type;

/**
 * Created by gustavoballeste on 19/11/17.
 */


public class UserDeserializer implements com.google.gson.JsonDeserializer<User> {

    private static final String TAG = UserDeserializer.class.getName();

    @Override
    public User deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonElement userJson = json.getAsJsonObject();

        if (json.getAsJsonObject().get("user") != null) {
            userJson = json.getAsJsonObject().get("user");
            Log.d(TAG, userJson.toString());
        }

        User user = new Gson().fromJson(userJson, User.class);

        return user;
    }
}
