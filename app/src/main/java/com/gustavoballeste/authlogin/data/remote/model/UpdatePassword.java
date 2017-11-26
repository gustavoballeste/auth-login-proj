package com.gustavoballeste.authlogin.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdatePassword {

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("password")
    @Expose
    private String password;

    public UpdatePassword(String token, String firstName) {
        this.token = token;
        this.password = firstName;
    }

    public String getToken() {
        return token;
    }
    public String getfirstName() {
        return password;
    }

}
