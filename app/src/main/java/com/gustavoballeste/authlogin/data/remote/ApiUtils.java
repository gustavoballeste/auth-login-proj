package com.gustavoballeste.authlogin.data.remote;

import com.google.gson.Gson;

public class ApiUtils {

    private ApiUtils() {}
    public static final String BASE_URL = "http://104.131.189.211:8085/";

    public static APIService getAPIService(Gson gson) {

        return RetrofitClient.getClient(BASE_URL, gson).create(APIService.class);
    }
}