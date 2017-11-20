package com.gustavoballeste.authlogin.data.dao;

import com.gustavoballeste.authlogin.data.remote.model.Token;

public class TokenDAO {

    private Token token = new Token(null);

    public Token getToken(){

        // TODO
        //Get token from SQLite
//        token.setValue("NuORxEkCfRxz2Ar4cG&jxG3rj6oFGtxl"); //"username": "user7654321", "password": "anypass"
        return token;
    }

    public void setToken(String token){

        // TODO
        // Set token in SQLite
        // token.setValue("NuORxEkCfRxz2Ar4cG&jxG3rj6oFGtxl"); //"username": "user7654321", "password": "anypass"
    }
}
