package com.gustavoballeste.authlogin.detail;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.gustavoballeste.authlogin.app.App;
import com.gustavoballeste.authlogin.data.dao.AppDatabase;
import com.gustavoballeste.authlogin.data.model.User;
import com.gustavoballeste.authlogin.data.remote.APIService;
import com.gustavoballeste.authlogin.data.remote.ApiUtils;
import com.gustavoballeste.authlogin.data.remote.model.Message;
import com.gustavoballeste.authlogin.data.model.Token;
import com.gustavoballeste.authlogin.data.remote.model.UpdateFirstName;
import com.gustavoballeste.authlogin.data.remote.model.UpdateLastName;
import com.gustavoballeste.authlogin.data.remote.model.UpdatePassword;
import com.gustavoballeste.authlogin.data.remote.model.util.ObjectDeserializer;

import org.json.JSONObject;

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
        Gson userGson = new GsonBuilder().registerTypeAdapter(User.class, new ObjectDeserializer()).create();
        mAPIService = ApiUtils.getAPIService(userGson);
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
    public void sendPostUpdateFirstName(String newValue, TextView textView, String name, String message) {
        mAPIService.updateFirstName(new UpdateFirstName(token.getToken(), newValue))
            .enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if(response.isSuccessful()) {
                    Toast.makeText((Context)view, "User authentication successfully", Toast.LENGTH_LONG).show();
                    Log.d(TAG+" - response:", "post submitted to API." + response.body().toString());
                    view.refreshData(textView, newValue, message);
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText((Context)view, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                        Log.d(TAG, jObjError.getString("message"));
                    } catch (Exception e) {
                        Toast.makeText((Context)view, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
            }
        });
    }

    @Override
    public void sendPostUpdateLastName(String newValue, TextView textView, String name, String message) {
        mAPIService.updateLastName(new UpdateLastName(token.getToken(), newValue))
            .enqueue(new Callback<Message>() {
                @Override
                public void onResponse(Call<Message> call, Response<Message> response) {
                    if(response.isSuccessful()) {
                        Toast.makeText((Context)view, "User authentication successfully", Toast.LENGTH_LONG).show();
                        Log.d(TAG+" - response:", "post submitted to API." + response.body().toString());
                        view.refreshData(textView, newValue, message);
                    } else {
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            Toast.makeText((Context)view, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                            Log.d(TAG, jObjError.getString("message"));
                        } catch (Exception e) {
                            Toast.makeText((Context)view, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }
                @Override
                public void onFailure(Call<Message> call, Throwable t) {
                    Log.e(TAG, "Unable to submit post to API.");
                }
            });
    }

    @Override
    public void sendPostUpdatePassword(String newValue, TextView textView, String name, String message) {
        mAPIService.updatePassword(new UpdatePassword(token.getToken(), newValue))
            .enqueue(new Callback<Message>() {
                @Override
                public void onResponse(Call<Message> call, Response<Message> response) {
                    if(response.isSuccessful()) {
                        Toast.makeText((Context)view, "Password updated successfully", Toast.LENGTH_LONG).show();
                        Log.d(TAG+" - response:", "post submitted to API." + response.body().toString());
                        AppDatabase.getAppDatabase(App.getAppContext()).tokenDao().delete();
                    } else {
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            Toast.makeText((Context)view, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                            Log.d(TAG, jObjError.getString("message"));
                        } catch (Exception e) {
                            Toast.makeText((Context)view, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }
                @Override
                public void onFailure(Call<Message> call, Throwable t) {
                    Log.e(TAG, "Unable to submit post to API.");
                }
            });
    }

    @Override
    public void updateRemote(String newValue, TextView textView, String name) {
        String message = "";
        if (!newValue.equals(textView.getText().toString())) {
            switch (name) {
                case "firstName":
                    message = "First name updated successfully!";
                    sendPostUpdateFirstName(newValue, textView, name, message);
                    break;
                case "lastName":
                    message = "Last name updated successfully!";
                    sendPostUpdateLastName(newValue, textView, name, message);
                    break;
                case "password":
                    message = "Password updated successfully!";
                    sendPostUpdatePassword(newValue, textView, name, message);
                    break;
            }
        }
    }

    @Override
    public void logout() {
        AppDatabase.getAppDatabase(App.getAppContext()).tokenDao().delete();
        view.backLogin();
    }

}
