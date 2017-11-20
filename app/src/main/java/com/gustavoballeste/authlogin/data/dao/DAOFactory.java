package com.gustavoballeste.authlogin.data.dao;

/**
 * Created by gustavoballeste on 20/11/17.
 */

public abstract class DAOFactory {

    public static TokenDAO getTokenDAO() {
        return new TokenDAO();
    }

}
