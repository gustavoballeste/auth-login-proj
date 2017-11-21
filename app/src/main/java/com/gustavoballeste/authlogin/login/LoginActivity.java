package com.gustavoballeste.authlogin.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gustavoballeste.authlogin.R;
import com.gustavoballeste.authlogin.detail.DetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by gustavoballeste on 20/11/17.
 */

public class LoginActivity extends AppCompatActivity implements LoginIView {

    private static final String TAG = LoginActivity.class.getName();
    private LoginPresenter presenter;

    @BindView(R.id.et_auth_username) EditText usernameEt;
    @BindView(R.id.et_auth_password) EditText passwordEt;
    @BindView(R.id.btn_auth_submit) Button submitBtn;
    @BindView(R.id.tv_auth_response) TextView mResponseTv; //remover

    @OnClick(R.id.btn_auth_submit)
    public void onClick() {
        presenter.submit(usernameEt, passwordEt);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_auth);
        ButterKnife.bind(this);
        presenter = new LoginPresenter(this);
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
        //TODO

    }

    public void showResponse(String response) {
        if(mResponseTv.getVisibility() == View.GONE) {
            mResponseTv.setVisibility(View.VISIBLE);
        }
        mResponseTv.setText(response);
    }
}
