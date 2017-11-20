package com.gustavoballeste.authlogin.detail;

import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import com.gustavoballeste.authlogin.data.dao.DAOFactory;
import com.gustavoballeste.authlogin.data.remote.APIService;
import com.gustavoballeste.authlogin.data.remote.ApiUtils;
import com.gustavoballeste.authlogin.data.remote.model.Message;
import com.gustavoballeste.authlogin.data.remote.model.Token;
import com.gustavoballeste.authlogin.data.remote.model.UserUpdate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gustavoballeste on 20/11/17.
 */

public class DetailPresenter implements DetailIPresenter {

    private static final String TAG = DetailPresenter.class.getName();
    private DetailIView view;
    private APIService mAPIService;
    private Token token;

    public DetailPresenter(DetailIView view) {
        this.view = view;
    }

    @Override
    public void startService() {
        mAPIService = ApiUtils.getAPIService(null);
        token = DAOFactory.getTokenDAO().getToken();
    }

    @Override
    public void submit(EditText firstName, EditText lastName, EditText username) {
        String f = firstName.getText().toString().trim();
        String l = lastName.getText().toString().trim();
        String u = username.getText().toString().trim();
        if(!(TextUtils.isEmpty(f) && TextUtils.isEmpty(l) && TextUtils.isEmpty(u))) {
            UserUpdate userUpdate = new UserUpdate(token.getValue(), f, l);
            sendPost(userUpdate);
        }
    }

    public void sendPost(UserUpdate up) {
        Log.d("sendPost ******", up.getFirstName() + ", " + up.getLastName() + ", " + up.getToken());

        mAPIService.updateUser(up).enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {

                if(response.isSuccessful()) {
                    Log.d(TAG+" - response:", "post submitted to API." + response.body().toString());
                    //Carregar os campos
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
            }
        });
    }
}
