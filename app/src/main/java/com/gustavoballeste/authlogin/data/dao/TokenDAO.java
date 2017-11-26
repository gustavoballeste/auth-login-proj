package com.gustavoballeste.authlogin.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.gustavoballeste.authlogin.data.remote.model.Token;

@Dao
public interface TokenDao {

    @Query("SELECT * FROM token")
    Token get();

    @Insert
    void insert(Token token);

    @Query("DELETE FROM token")
    void delete();

    @Update
    void update(Token token);
}
