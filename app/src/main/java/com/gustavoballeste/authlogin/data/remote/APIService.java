package com.gustavoballeste.authlogin.data.remote;

import com.gustavoballeste.authlogin.data.model.User;
import com.gustavoballeste.authlogin.data.remote.model.Message;
import com.gustavoballeste.authlogin.data.remote.model.Token;
import com.gustavoballeste.authlogin.data.remote.model.Login;
import com.gustavoballeste.authlogin.data.remote.model.UpdateFirstName;
import com.gustavoballeste.authlogin.data.remote.model.UpdateLastName;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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

    @POST("/user/update")
    Call<Message> updateFirstName(@Body UpdateFirstName updateFirstName);

    @POST("/user/update")
    Call<Message> updateLastName(@Body UpdateLastName updateLastName);

    @FormUrlEncoded
    @POST("/user/update")
    Call<Message> updatePassword(@Field("token") String token, @Field("password") String password);


}