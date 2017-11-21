package com.gustavoballeste.authlogin.login;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gustavoballeste.authlogin.data.dao.AppDatabase;
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
    private Context context;
    private Toast toast;

    public LoginPresenter(LoginIView view) {
        this.view = view;
    }

    @Override
    public void startService() {

        Gson gson = new GsonBuilder().registerTypeAdapter(Token.class, new ObjectDeserializer()).create();
        mAPIService = ApiUtils.getAPIService(gson);
    }

    @Override
    public void submit(EditText usernameEt, EditText passwordEt) {
        Log.d(TAG, view.toString());
        String username = usernameEt.getText().toString().trim();
        String password = passwordEt.getText().toString().trim();
        if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            Login login = new Login(username, password);
            sendPost(login, this.context);
        }
    }

    public void sendPost(Login l, Context context) {
        Log.d("sendPost*********", l.getUsername() + ", " + l.getPassword());
        mAPIService.getToken(l).enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {

                if(response.isSuccessful()) {
                    insertDb(response.body());

                    view.startDetails();
                    Log.d(TAG, "post submitted to API." + response.body().toString());
                } else {
                    //TODO
                    view.showToast("User not found!");
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
            }
        });
    }

    @Override
    public void register() {
        view.startRegister();
    }

    @Override
    public void detach() {
        this.view = null;
    }

    @Override
    public void insertDb(Token token) {
        AppDatabase.getAppDatabase(context).tokenDao().update(token);
        AppDatabase.getAppDatabase(context).tokenDao().insert(token);
    }
}
