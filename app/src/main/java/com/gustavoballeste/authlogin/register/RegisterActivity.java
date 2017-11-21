package com.gustavoballeste.authlogin.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gustavoballeste.authlogin.R;
import com.gustavoballeste.authlogin.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by gustavoballeste on 20/11/17.
 */

public class RegisterActivity extends AppCompatActivity implements RegisterIView {

    private static final String TAG = RegisterActivity.class.getName();
    private RegisterPresenter presenter;

    @BindView(R.id.et_register_username) EditText usernameEt;
    @BindView(R.id.et_register_password) EditText passwordEt;
    @BindView(R.id.btn_register_submit) Button submitBtn;
    @BindView(R.id.tv_register_response) TextView mResponseTv; //remover

    @OnClick(R.id.btn_register_submit)
    public void onClick() {
        presenter.submit(usernameEt, passwordEt);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        presenter = new RegisterPresenter(this);
        presenter.startService();
    }

    @Override
    protected void onDestroy() {
        if (presenter != null) presenter.detach();
        super.onDestroy();
    }

    @Override
    public void startLogin() {
        Intent intent = new Intent(RegisterActivity.this,
                LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void showResponse(String response) {
        if(mResponseTv.getVisibility() == View.GONE) {
            mResponseTv.setVisibility(View.VISIBLE);
        }
        mResponseTv.setText(response);
    }
}
