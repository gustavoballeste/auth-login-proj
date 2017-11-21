package com.gustavoballeste.authlogin.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.gustavoballeste.authlogin.R;
import com.gustavoballeste.authlogin.data.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends AppCompatActivity implements DetailIView {

    private DetailPresenter presenter;
    private Toast toast;

    @BindView(R.id.first_name_text_view) TextView firstNameTv;
    @BindView(R.id.last_name_text_view) TextView lastNameTv;
    @BindView(R.id.password_text_view) TextView passwordTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        presenter = new DetailPresenter(this);
        presenter.startService();
    }

    @Override
    public void updateScreen(User user) {
        firstNameTv.setText(user.getFirstName());
        lastNameTv.setText(user.getLastName());
    }

    @OnClick(R.id.first_name_card_view)
    public void showFirstNameDialog() {
        new MaterialDialog.Builder(this)
                .title(R.string.first_name_input)
                .content(R.string.first_name_input_content)
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
                        (dialog, input) -> updateValue("First name updated successfully!",
                                input.toString(),
                                firstNameTv,
                                "firstname"))
                .show();
    }

    @OnClick(R.id.last_name_card_view)
    public void showLastNameDialog() {
        new MaterialDialog.Builder(this)
                .title(R.string.last_name_input)
                .content(R.string.last_name_input_content)
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
                        (dialog, input) -> updateValue("Last name updated successfully!",
                                input.toString(),
                                lastNameTv,
                                "lastname"))
                .show();
    }

    @OnClick(R.id.password_card_view)
    public void showPasswordDialog() {
        new MaterialDialog.Builder(this)
                .title(R.string.password_input)
                .content(R.string.password_input_content)
                .inputType(
                        InputType.TYPE_CLASS_TEXT
                                | InputType.TYPE_TEXT_VARIATION_PERSON_NAME
                                | InputType.TYPE_TEXT_FLAG_CAP_WORDS)
                .inputRange(6, 30)
                .positiveText(R.string.submit)
                .input(
                        null,
                        null,
                        false,
                        (dialog, input) -> updateValue("Password updated successfully!",
                                input.toString(),
                                passwordTv,
                                "password")
                )
                .show();
    }

    private void updateValue(String message, String newValue, TextView textView, String name) {

        if (!newValue.equals(textView.getText().toString())) {
            switch (name) {
                case "firstname":
                    presenter.sendPostUpdateFirstName(newValue);
                    break;
                case "lastname":
                    presenter.sendPostUpdateLastName(newValue);
                    break;
                case "password":
                    presenter.sendPostUpdatePassword(newValue);
                    break;
            }

            textView.setText(newValue);
            if (toast != null) {
                toast.cancel();
                toast = null;
            }
            toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
