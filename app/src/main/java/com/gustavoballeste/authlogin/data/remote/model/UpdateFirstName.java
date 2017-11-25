package com.gustavoballeste.authlogin.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateFirstName {

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("firstName")
    @Expose
    private String firstName;

    public UpdateFirstName(String token, String firstName) {
        this.token = token;
        this.firstName = firstName;
    }

    public String getToken() {
        return token;
    }
    public String getfirstName() {
        return firstName;
    }


}
