package com.gustavoballeste.authlogin.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gustavoballeste.authlogin.R;
import com.gustavoballeste.authlogin.data.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity implements DetailIView {

    private DetailPresenter presenter;

    @BindView(R.id.first_name_text_view) TextView firstNameEt;
    @BindView(R.id.last_name_text_view) TextView lastNameEt;
    @BindView(R.id.username_text_view) TextView usernameEt;
    @BindView(R.id.password_text_view) TextView passwordEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        presenter = new DetailPresenter(this);
        presenter.startService();
    }

    @Override
    public void updateFiels(User user) {
        firstNameEt.setText(user.getFirstName());
        lastNameEt.setText(user.getLastName());
        usernameEt.setText(user.getUsername());
    }
}
