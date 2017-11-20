package com.gustavoballeste.authlogin.data.remote;

import com.gustavoballeste.authlogin.data.model.User;
import com.gustavoballeste.authlogin.data.remote.model.Message;
import com.gustavoballeste.authlogin.data.remote.model.Token;
import com.gustavoballeste.authlogin.data.remote.model.Login;
import com.gustavoballeste.authlogin.data.remote.model.UserUpdate;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {

    @Headers("Content-type: application/json")
    @POST("/user/getDetails")
    Call<User> getUserDetails(@Body Token token);

    @Headers("Content-type: application/json")
    @POST("/auth/authUser")
    Call<Token> getToken(@Body Login login);

    @Headers("Content-type: application/json")
    @POST("/user/create")
    Call<Message> createUser(@Body Login userRegister);

    @Headers({"Content-type: application/json"})
    @POST("/user/update")
    Call<Message> updateUser(@Body UserUpdate userUpdate);

}