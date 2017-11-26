package com.gustavoballeste.authlogin.login;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gustavoballeste.authlogin.app.App;
import com.gustavoballeste.authlogin.data.dao.AppDatabase;
import com.gustavoballeste.authlogin.data.remote.APIService;
import com.gustavoballeste.authlogin.data.remote.ApiUtils;
import com.gustavoballeste.authlogin.data.remote.model.Login;
import com.gustavoballeste.authlogin.data.model.Token;
import com.gustavoballeste.authlogin.data.remote.model.util.ObjectDeserializer;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginPresenterContract {

    private static final String TAG = LoginPresenter.class.getName();
    private LoginViewContract view;
    private APIService mAPIService;

    public LoginPresenter(LoginViewContract view, Context c) {
        this.view = view;
    }

    @Override
    public void startService() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Token.class, new ObjectDeserializer()).create();
        mAPIService = ApiUtils.getAPIService(gson);
    }

    @Override
    public void submit(EditText usernameEt, EditText passwordEt, View view) {
        InputMethodManager imm =
                (InputMethodManager) view
                        .getContext()
                        .getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        Log.d(TAG, view.toString());
        String username = usernameEt.getText().toString().trim();
        String password = passwordEt.getText().toString().trim();

        if (username.length() == 0) {
            usernameEt.requestFocus();
            usernameEt.setError("Digite o login");
        } else if (password.length() == 0) {
            passwordEt.requestFocus();
            passwordEt.setError("Digite a senha");
        } else {
            Login login = new Login(username, password);
            sendPost(login, view.getContext());
        }
    }

    public void sendPost(Login l, Context context) {
        mAPIService.getToken(l).enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if(response.isSuccessful()) {
                    Log.d("Insert token", response.body().getToken());
                    insertToken(response.body());
                    view.startDetails();
                    Toast.makeText((Context)view, "User authentication successfully", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText((Context)view, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText((Context)view, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
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
    public void insertToken(Token token) {
        AppDatabase.getAppDatabase(App.getAppContext()).tokenDao().delete();
        AppDatabase.getAppDatabase(App.getAppContext()).tokenDao().insert(token);
        Log.d(TAG, "Insert Token: " + token.getToken());
    }
}
