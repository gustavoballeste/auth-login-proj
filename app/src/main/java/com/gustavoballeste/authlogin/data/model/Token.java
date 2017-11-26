package com.gustavoballeste.authlogin.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "token")
public class Token {

    @PrimaryKey @NonNull
    @ColumnInfo(name = "token")
    @SerializedName("token")
    @Expose
    private String token;

    public String getToken() {
        return token;
    }

    public Token(String token) {
        this.token = token;
    }

    public Token() {
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Token {" +
                "token='" + token + '\'' +

                '}';
    }
}
