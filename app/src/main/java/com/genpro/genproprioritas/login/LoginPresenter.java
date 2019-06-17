package com.genpro.genproprioritas.login;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginPresenter implements LoginContract.Presenter {
   LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void loginPush(String email, String password) {
        view.showLoading();
        AndroidNetworking.post("http://genprodev.lavenderprograms.com/apigw/auth/")
                .setPriority(Priority.HIGH)
                .addBodyParameter("email", email)
                .addBodyParameter("password", password)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        view.hideLoading();
                        try {
                            if(response.getString("error").equals("false")){
                                view.loginSucces(response.getString("user_id"));
                            }else if(response.getString("error").equals("true")){
                                view.loginFailed(response.getString("msg"));

                            }else{
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            view.hideLoading();
                            view.loginFailed(e.getLocalizedMessage());
                            Log.d("login", e.getLocalizedMessage());
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        view.hideLoading();

                    }
                });

    }
}
