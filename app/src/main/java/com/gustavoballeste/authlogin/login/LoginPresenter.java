package com.gustavoballeste.authlogin.login;

import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gustavoballeste.authlogin.data.dao.DAOFactory;
import com.gustavoballeste.authlogin.data.remote.APIService;
import com.gustavoballeste.authlogin.data.remote.ApiUtils;
import com.gustavoballeste.authlogin.data.remote.model.Login;
import com.gustavoballeste.authlogin.data.remote.model.Token;
import com.gustavoballeste.authlogin.data.remote.model.util.ObjectDeserializer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginIPresenter {

    private static final String TAG = LoginPresenter.class.getName();
    private LoginIView view;
    private APIService mAPIService;
    Gson gson;

    public LoginPresenter(LoginIView view) {
        this.view = view;
    }

    @Override
    public void startService() {

        gson = new GsonBuilder().registerTypeAdapter(Token.class, new ObjectDeserializer()).create();
        mAPIService = ApiUtils.getAPIService(gson);
        gson = null;
    }

    @Override
    public void submit(EditText usernameEt, EditText passwordEt) {
        String username = usernameEt.getText().toString().trim();
        String password = passwordEt.getText().toString().trim();
        if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            Login login = new Login(username, password);
            sendPost(login);
        }
    }

    public void sendPost(Login l) {
        Log.d("sendPost*********", l.getUsername() + ", " + l.getPassword());
        mAPIService.getToken(l).enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {

                if(response.isSuccessful()) {
                    DAOFactory.getTokenDAO().insert(response.body().getValue());
                    view.startDetails();
//                    view.showResponse(response.body().getValue());
                    Log.d(TAG, "post submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
            }
        });
    }

    @Override
    public void detach() {
        this.view = null;
    }

}
