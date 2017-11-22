package com.gustavoballeste.authlogin.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
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

    @Delete
    void delete(Token token);

    @Update
    void update(Token token);
}
