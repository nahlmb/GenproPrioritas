package com.genpro.genproprioritas.genproBisnis.detailBisnis;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.genpro.genproprioritas.model.Bisnis;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailBisnisPresenter implements DetailBisnisContract.Presenter {
    DetailBisnisContract.View view;

    public DetailBisnisPresenter(DetailBisnisContract.View view) {
        this.view = view;
    }

    @Override
    public void getBusinnes(String userId, final int i) {
        AndroidNetworking.post("http://genpro.dfiserver.com/apigw/bisnis_info/getbisnis_info")
                .setPriority(Priority.HIGH)
                .addBodyParameter("user_id", userId)
                .build()
                .getAsObject(Bisnis.class, new ParsedRequestListener<Bisnis>() {

                    @Override
                    public void onResponse(Bisnis response) {
                        if(response != null){
                            view.showUserBisnisDetail(response.getData().get(i));
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        view.somethingFailed(anError.getLocalizedMessage());

                    }
                });
    }

    @Override
    public void delateBisnis(String userId, String idBisnisInfo) {
        AndroidNetworking.post("http://genprodev.lavenderprograms.com/apigw/bisnis_info/delete_bisnis")
                .setPriority(Priority.HIGH)
                .addBodyParameter("user_id", userId)
                .addBodyParameter("id_bisnis_info", idBisnisInfo)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getString("error").equals("false")){
                                view.succesDelate();
                            }else{
                                view.somethingFailed(response.getString("msg"));
                            }
                        } catch (JSONException e) {
                            view.somethingFailed(e.getLocalizedMessage());


                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        view.somethingFailed(anError.getLocalizedMessage());

                    }
                });


    }

}
