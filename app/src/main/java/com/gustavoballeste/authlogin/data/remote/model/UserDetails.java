package com.gustavoballeste.authlogin.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.gustavoballeste.authlogin.data.model.User;

public class UserDetails {

    @SerializedName("user")
    @Expose
    private User user;

    public UserDetails(User user) {
        this.user = user;
    }

    public UserDetails() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "username='" + user.getUsername() + '\'' +
                ", fistName='" + user.getFirstName() + '\'' +
                ", lastName=" + user.getLastName() +
                '}';
    }

}