package com.gustavoballeste.authlogin.detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.gustavoballeste.authlogin.R;
import com.gustavoballeste.authlogin.data.model.User;
import com.gustavoballeste.authlogin.login.LoginActivity;

import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.OnClick;

public class DetailActivity extends AppCompatActivity implements DetailIViewContract {

    private DetailPresenterContract presenter;
    private Toast toast;

    @BindView(R.id.first_name_text_view) TextView firstNameTv;
    @BindView(R.id.last_name_text_view) TextView lastNameTv;
    @BindView(R.id.password_text_view) TextView passwordTv;

    @OnClick(R.id.btn_logout)
    public void onClick() {
        presenter.logout();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        presenter = new DetailPresenter(this, this);
        presenter.startService();
    }

    @Override
    public void updateScreen(User user) {
        firstNameTv.setText(user.getFirstName());
        lastNameTv.setText(user.getLastName());
    }

    @Override
    public void refreshData(TextView textView, String newValue, String message) {
        textView.setText(newValue);
//        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
//        toast.show();
    }

    @Override
    public void backLogin() {
        Intent intent = new Intent(DetailActivity.this,
                LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.first_name_card_view)
    public void showFirstNameDialog() {
        new MaterialDialog.Builder(this)
                .title(R.string.first_name_dialog_title)
                .content(R.string.first_name_dialog_label)
                .inputType(
                        InputType.TYPE_CLASS_TEXT
                                | InputType.TYPE_TEXT_VARIATION_PERSON_NAME
                                | InputType.TYPE_TEXT_FLAG_CAP_WORDS)
                .inputRange(2, 30)
                .positiveText(R.string.submit)
                .input(
                        null,
                        this.firstNameTv.getText(),
                        false,
                        (dialog, input) -> this.presenter.updateRemote(input.toString(), firstNameTv,"firstName"))
                .show();
    }

    @OnClick(R.id.last_name_card_view)
    public void showLastNameDialog() {
        new MaterialDialog.Builder(this)
                .title(R.string.last_name_dialog_title)
                .content(R.string.last_name_dialog_label)
                .inputType(
                        InputType.TYPE_CLASS_TEXT
                                | InputType.TYPE_TEXT_VARIATION_PERSON_NAME
                                | InputType.TYPE_TEXT_FLAG_CAP_WORDS)
                .inputRange(2, 30)
                .positiveText(R.string.submit)
                .input(
                        null,
                        this.lastNameTv.getText(),
                        false,
                        (dialog, input) -> this.presenter.updateRemote(input.toString(), lastNameTv,"lastName"))
                .show();
    }

    @OnClick(R.id.password_card_view)
    public void showPasswordDialog() {
        new MaterialDialog.Builder(this)
                .title(R.string.password_dialog_title)
                .content(R.string.password_dialog_label)
                .inputType(
                        InputType.TYPE_CLASS_TEXT
                                | InputType.TYPE_TEXT_VARIATION_PERSON_NAME
                                | InputType.TYPE_TEXT_FLAG_CAP_WORDS)
                .inputRange(2, 30)
                .positiveText(R.string.submit)
                .input(
                        null,
                        this.passwordTv.getText(),
                        false,
                        (dialog, input) -> this.presenter.updateRemote(input.toString(), passwordTv,"password"))
                .show();
    }

}
