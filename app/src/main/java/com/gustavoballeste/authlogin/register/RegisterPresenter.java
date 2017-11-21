package com.gustavoballeste.authlogin.register;

import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gustavoballeste.authlogin.data.dao.DAOFactory;
import com.gustavoballeste.authlogin.data.remote.APIService;
import com.gustavoballeste.authlogin.data.remote.ApiUtils;
import com.gustavoballeste.authlogin.data.remote.model.Login;
import com.gustavoballeste.authlogin.data.remote.model.Message;
import com.gustavoballeste.authlogin.data.remote.model.Token;
import com.gustavoballeste.authlogin.data.remote.model.util.ObjectDeserializer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter implements RegisterIPresenter {

    private static final String TAG = RegisterPresenter.class.getName();
    private RegisterIView view;
    private APIService mAPIService;

    public RegisterPresenter(RegisterIView view) {
        this.view = view;
    }

    @Override
    public void startService() {

        mAPIService = ApiUtils.getAPIService(null);
    }

    @Override
    public void submit(EditText usernameEt, EditText passwordEt) {
        Log.d(TAG, view.toString());
        String username = usernameEt.getText().toString().trim();
        String password = passwordEt.getText().toString().trim();
        if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            Login login = new Login(username, password);
            sendPost(login);
        }
    }

    public void sendPost(Login l) {
        Log.d("sendPost*********", l.getUsername() + ", " + l.getPassword());
        mAPIService.createUser(l).enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {

                if(response.isSuccessful()) {
                    login();
//                    view.showResponse(response.body().getValue());
                    Log.d(TAG, "post submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
            }
        });
    }

    public void login() {
        view.startLogin();
    }

    @Override
    public void detach() {
        this.view = null;
    }

}
