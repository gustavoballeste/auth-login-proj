package com.gustavoballeste.authlogin.detail;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gustavoballeste.authlogin.app.App;
import com.gustavoballeste.authlogin.data.dao.AppDatabase;
import com.gustavoballeste.authlogin.data.model.User;
import com.gustavoballeste.authlogin.data.remote.APIService;
import com.gustavoballeste.authlogin.data.remote.ApiUtils;
import com.gustavoballeste.authlogin.data.remote.model.Message;
import com.gustavoballeste.authlogin.data.remote.model.Token;
import com.gustavoballeste.authlogin.data.remote.model.util.ObjectDeserializer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPresenter implements DetailPresenterContract {

    private static final String TAG = DetailPresenter.class.getName();
    private DetailIViewContract view;
    private APIService mAPIService;
    private Token token;

    public DetailPresenter(DetailIViewContract view, Context c) {
        this.view = view;
        this.token = new Token();
    }

    @Override
    public void startService() {
        Gson gson;
        gson = new GsonBuilder().registerTypeAdapter(User.class, new ObjectDeserializer()).create();
        mAPIService = ApiUtils.getAPIService(gson);
        token = AppDatabase.getAppDatabase(App.getAppContext()).tokenDao().get();
        sendPostRetrieve();
    }

    @Override
    public void sendPostRetrieve() {
        Log.d("sendPostRetrieve ******", token.getToken().toString());
        mAPIService.getUserDetails(token).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()) {
                    Log.d(TAG+" - response:", "post submitted to API." + response.body().toString());
                    User user = new User(response.body().getUsername(),
                                    response.body().getFirstName(),
                                    response.body().getLastName());
                    view.updateScreen(user);
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
            }
        });
    }

    @Override
    public void sendPostUpdateFirstName(String newValue) {
        Log.d("sendPostUpdateFirstName", newValue);
        mAPIService.updateFirstName(token.getToken(), newValue).enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if(response.isSuccessful()) {
                    Log.d(TAG+" - response:", "post submitted to API." + response.body().toString());
                }
            }
            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
            }
        });
    }

    @Override
    public void sendPostUpdateLastName(String newValue) {
        Log.d("sendPostUpdateFirstName", newValue);
        mAPIService.updateLastName(token.getToken(), newValue).enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if(response.isSuccessful()) {
                    Log.d(TAG+" - response:", "post submitted to API." + response.body().toString());
                }
            }
            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
            }
        });
    }

    @Override
    public void sendPostUpdatePassword(String newValue) {
        Log.d("sendPostUpdateFirstName", newValue);
        mAPIService.updatePassword(token.getToken(), newValue).enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if(response.isSuccessful()) {
                    Log.d(TAG+" - response:", "post submitted to API." + response.body().toString());
                    //TODO update token
                }
            }
            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
            }
        });
    }

    @Override
    public void updateValue(String newValue, TextView textView, String name) {
        String message = "";
        if (!newValue.equals(textView.getText().toString())) {
            switch (name) {
                case "firstname":
                    message = "First name updated successfully!";
                    sendPostUpdateFirstName(newValue);
                    break;
                case "lastname":
                    message = "Last name updated successfully!";
                    sendPostUpdateLastName(newValue);
                    break;
                case "password":
                    message = "Password updated successfully!";
                    sendPostUpdatePassword(newValue);
                    break;
            }
            view.refreshData(textView, newValue, message);
        }
    }
}
