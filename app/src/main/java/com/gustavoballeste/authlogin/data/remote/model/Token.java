package com.gustavoballeste.authlogin.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by gustavoballeste on 19/11/17.
 */

public class Token {

    @SerializedName("token")
    @Expose
    private String token;

    public String getValue() {
        return token;
    }

    public Token(String value) {
        this.token = value;
    }

    public Token() {
    }

    public void setValue(String value) {
        this.token = value;
    }

    @Override
    public String toString() {
        return "Token {" +
                "token='" + token + '\'' +

                '}';
    }
}
