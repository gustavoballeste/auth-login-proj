package com.gustavoballeste.authlogin.register;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gustavoballeste.authlogin.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements RegisterViewContract {

    private static final String TAG = RegisterActivity.class.getName();
    private RegisterPresenterContract presenter;

    @BindView(R.id.et_register_username) EditText usernameEt;
    @BindView(R.id.et_register_password) EditText passwordEt;
    @BindView(R.id.btn_register_submit) Button submitBtn;
    @BindView(R.id.tv_register_response) TextView mResponseTv; //remover

    @OnClick(R.id.btn_register_submit)
    public void onClick() {
        presenter.submit(usernameEt, passwordEt, this, this.getCurrentFocus());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        presenter = new RegisterPresenter(this, this);
        presenter.startService();
    }

    @Override
    protected void onDestroy() {
        if (presenter != null) presenter.detach();
        super.onDestroy();
    }

}
