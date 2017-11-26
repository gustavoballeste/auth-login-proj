package com.gustavoballeste.authlogin.data.remote;

import com.gustavoballeste.authlogin.data.model.User;
import com.gustavoballeste.authlogin.data.remote.model.Message;
import com.gustavoballeste.authlogin.data.model.Token;
import com.gustavoballeste.authlogin.data.remote.model.Login;
import com.gustavoballeste.authlogin.data.remote.model.UpdateFirstName;
import com.gustavoballeste.authlogin.data.remote.model.UpdateLastName;
import com.gustavoballeste.authlogin.data.remote.model.UpdatePassword;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIService {

    @POST("/user/getDetails")
    Call<User> getUserDetails(@Body Token token);

    @POST("/auth/authUser")
    Call<Token> getToken(@Body Login login);

    @POST("/user/create")
    Call<Message> createUser(@Body Login userRegister);

    @POST("/user/update")
    Call<Message> updateFirstName(@Body UpdateFirstName updateFirstName);

    @POST("/user/update")
    Call<Message> updateLastName(@Body UpdateLastName updateLastName);

    @POST("/user/update")
    Call<Message> updatePassword(@Body UpdatePassword updatePassword);


}