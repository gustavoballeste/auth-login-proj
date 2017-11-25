package com.gustavoballeste.authlogin.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateLastName {

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("lastName")
    @Expose
    private String lastName;

    public UpdateLastName(String token, String lastName) {
        this.token = token;
        this.lastName = lastName;
    }

    public String getToken() {
        return token;
    }
    public String getlastName() {
        return lastName;
    }


}
