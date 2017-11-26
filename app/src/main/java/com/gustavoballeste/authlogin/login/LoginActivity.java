package com.gustavoballeste.authlogin.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gustavoballeste.authlogin.R;
import com.gustavoballeste.authlogin.detail.DetailActivity;
import com.gustavoballeste.authlogin.register.RegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginViewContract {

    private static final String TAG = LoginActivity.class.getName();
    private LoginPresenterContract presenter;

    @BindView(R.id.et_register_username) EditText usernameEt;
    @BindView(R.id.et_register_password) EditText passwordEt;
    @BindView(R.id.btn_register) Button submitBtn;
    @BindView(R.id.tv_register_response) TextView mResponseTv; //remover

    @OnClick(R.id.btn_register)
    public void onClick() {
        presenter.submit(usernameEt, passwordEt, this.getCurrentFocus());
    }

    @OnClick(R.id.btn_login_create)
    public void onClickCreate() {
        presenter.register();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter = new LoginPresenter(this, this);
        presenter.startService();
    }

    @Override
    protected void onDestroy() {
        if (presenter != null) presenter.detach();
        super.onDestroy();
    }

    @Override
    public void startDetails() {
        Intent intent = new Intent(LoginActivity.this,
                DetailActivity.class);
        startActivity(intent);
        Log.d(TAG, "startDetails() ******");
        finish();
    }

    @Override
    public void startRegister() {
        Intent intent = new Intent(LoginActivity.this,
                RegisterActivity.class);
        startActivity(intent);
        Log.d(TAG, "startRegister() ******");
    }
}
