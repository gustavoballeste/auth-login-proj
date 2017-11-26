package com.gustavoballeste.authlogin.register;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.gustavoballeste.authlogin.data.remote.APIService;
import com.gustavoballeste.authlogin.data.remote.ApiUtils;
import com.gustavoballeste.authlogin.data.remote.model.Login;
import com.gustavoballeste.authlogin.data.remote.model.Message;
import com.gustavoballeste.authlogin.login.LoginActivity;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter implements RegisterPresenterContract {

    private static final String TAG = RegisterPresenter.class.getName();
    private RegisterViewContract view;
    private APIService mAPIService;

    public RegisterPresenter(RegisterViewContract view, Context c) {
        this.view = view;
    }

    @Override
    public void startService() {
        mAPIService = ApiUtils.getAPIService(null);
    }

    @Override
    public void submit(EditText usernameEt, EditText passwordEt, Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

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
            sendPost(login);
        }
    }

    public void sendPost(Login l) {
        mAPIService.createUser(l).enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if(response.isSuccessful()) {
                    view.startNewActivity((Activity)view, LoginActivity.class);
                    Toast.makeText((Context) view, "User created successfuly", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText((Context) view, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText((Context) view, e.getMessage(), Toast.LENGTH_LONG).show();
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
    public void detach() {
        this.view = null;
    }

}
