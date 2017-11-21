package com.gustavoballeste.authlogin.detail;

import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gustavoballeste.authlogin.data.dao.DAOFactory;
import com.gustavoballeste.authlogin.data.model.User;
import com.gustavoballeste.authlogin.data.remote.APIService;
import com.gustavoballeste.authlogin.data.remote.ApiUtils;
import com.gustavoballeste.authlogin.data.remote.model.Message;
import com.gustavoballeste.authlogin.data.remote.model.Token;
import com.gustavoballeste.authlogin.data.remote.model.UserUpdate;
import com.gustavoballeste.authlogin.data.remote.model.util.ObjectDeserializer;

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
        this.token = new Token();
    }

    @Override
    public void startService() {

        Gson gson;
        gson = new GsonBuilder().registerTypeAdapter(User.class, new ObjectDeserializer()).create();
        mAPIService = ApiUtils.getAPIService(gson);

        token = DAOFactory.getTokenDAO().get();
        submitRetrieve();
    }

    @Override
    public void submitRetrieve() {
        if (token==null) {
            startService();
        }
        Log.d("submitRetrieve() ******", token.getValue().toString());
        sendPostRetrieve();
    }

    @Override
    public void submitUpdate(EditText firstName, EditText lastName, EditText username, EditText password) {
        String f = firstName.getText().toString().trim();
        String l = lastName.getText().toString().trim();
        String u = username.getText().toString().trim();
        String p = password.getText().toString().trim();

        // TODO
        // Validate if changed
        if(!(TextUtils.isEmpty(f) && TextUtils.isEmpty(l) && TextUtils.isEmpty(u) && TextUtils.isEmpty(p))) {
            UserUpdate userUpdate = new UserUpdate(token.getValue(), f, l); //TODO falta a senha, criar objeto genérico
            sendPostUpdate(userUpdate);
        }
    }

    @Override
    public void sendPostRetrieve() {
        Log.d("sendPostRetrieve ******", token.getValue().toString());

        mAPIService.getUserDetails(token).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if(response.isSuccessful()) {
                    Log.d(TAG+" - response:", "post submitted to API." + response.body().toString());
                    User user = new User(response.body().getUsername(),
                                    response.body().getFirstName(),
                                    response.body().getLastName());

                    view.updateFiels(user);
                    //TODO
                    //When update password, get new token
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
            }
        });
    }

    @Override
    public void sendPostUpdate(UserUpdate up) {
        Log.d("sendPostUpdate ******", up.getFirstName() + ", " + up.getLastName() + ", " + up.getToken());

        mAPIService.updateUser(up).enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {

                if(response.isSuccessful()) {
                    Log.d(TAG+" - response:", "post submitted to API." + response.body().toString());
                    //TODO
                    //Get new token
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
            }
        });
    }
}
