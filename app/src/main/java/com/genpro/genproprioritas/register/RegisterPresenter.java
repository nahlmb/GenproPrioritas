package com.genpro.genproprioritas.register;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterPresenter implements RegisterContract.Presenter {
    RegisterContract.View view;

    public RegisterPresenter(RegisterContract.View view) {
        this.view = view;
    }

    @Override
    public void pushRegister(String userName, String email, String password) {
        view.showLogin();
        AndroidNetworking.post("http://genprodev.lavenderprograms.com/apigw/users/register")
                .setPriority(Priority.HIGH)
                .addBodyParameter("user_name", userName)
                .addBodyParameter("email", email)
                .addBodyParameter("password", password)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        view.hideLogin();
                        try {
                            if(response != null && response.getString("error").equals("false")){
                                view.registerSucces();
                            }else if (response != null && response.getString("error").equals("true")){
                                view.registerFailed(response.getString("msg"));
                            }
                        } catch (JSONException e) {
                            view.registerFailed(e.getLocalizedMessage());
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        view.hideLogin();
                        view.registerFailed(anError.getLocalizedMessage());

                    }
                });

    }
}
