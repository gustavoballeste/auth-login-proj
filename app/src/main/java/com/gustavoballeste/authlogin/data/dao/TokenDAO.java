package com.gustavoballeste.authlogin.data.dao;

import com.gustavoballeste.authlogin.data.remote.model.Token;

public class TokenDAO {

    private Token token;

    public Token get(){

        if (token==null) {
            token = new Token(null);
        }
        // TODO
        //Get token from SQLite

        token.setValue("NuORxEkCfRxz2Ar4cG&jxG3rj6oFGtxl"); //"username": "user7654321", "password": "anypass"
        return token;
    }

    public void insert(String t){

        if (this.token==null) {
            this.token = new Token(null);
        }
//        this.token.setValue("NuORxEkCfRxz2Ar4cG&jxG3rj6oFGtxl"); //"username": "user7654321", "password": "anypass"
        // TODO
        // Set token in SQLite

        this.token.setValue(t);
    }
}
