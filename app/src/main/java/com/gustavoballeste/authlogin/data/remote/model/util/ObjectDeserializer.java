package com.gustavoballeste.authlogin.data.remote.model.util;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.gustavoballeste.authlogin.data.model.User;
import com.gustavoballeste.authlogin.data.remote.model.Token;

import java.lang.reflect.Type;

/**
 * Created by gustavoballeste on 19/11/17.
 */

public class ObjectDeserializer implements com.google.gson.JsonDeserializer<Object> {

    private static final String TAG = ObjectDeserializer.class.getName();

    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonElement userJson = json.getAsJsonObject();

        if (typeOfT.equals(User.class)) {
            userJson = json.getAsJsonObject().get("user");
            User user = new Gson().fromJson(userJson, User.class);
            return user;
        } else if (typeOfT.equals(Token.class)) {
            userJson = json.getAsJsonObject().get("session");
            Token token = new Gson().fromJson(userJson, Token.class);
            return token;
        } else {
            Log.e(TAG, "Unknown type");
        }

        return null;
    }
}
