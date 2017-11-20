package com.gustavoballeste.authlogin.data.remote.model.util;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.gustavoballeste.authlogin.data.remote.model.Token;

import java.lang.reflect.Type;

public class AuthDeserializer implements com.google.gson.JsonDeserializer<Token> {

    private static final String TAG = AuthDeserializer.class.getName();

    @Override
    public Token deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonElement userJson = json.getAsJsonObject();

        if (json.getAsJsonObject().get("session") != null) {
            userJson = json.getAsJsonObject().get("session");
            Log.d(TAG, userJson.toString());
        }

        Token token = new Gson().fromJson(userJson, Token.class);

        return token;
    }
}
