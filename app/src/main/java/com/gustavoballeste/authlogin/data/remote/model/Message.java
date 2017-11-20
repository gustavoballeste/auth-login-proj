package com.gustavoballeste.authlogin.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Message {

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @SerializedName("message")
    @Expose
    private String message;


    @Override
    public String toString() {
        return "Message {" +
                "message='" + message +
                '}';
    }
}
