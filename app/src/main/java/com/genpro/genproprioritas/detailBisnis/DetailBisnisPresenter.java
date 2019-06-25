package com.genpro.genproprioritas.detailBisnis;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.genpro.genproprioritas.model.Bisnis;

public class DetailBisnisPresenter implements DetailBisnisContract.Presenter {
    DetailBisnisContract.View view;

    public DetailBisnisPresenter(DetailBisnisContract.View view) {
        this.view = view;
    }

    @Override
    public void getBusinnes(String userId, final int i) {
        AndroidNetworking.post("http://genprodev.lavenderprograms.com/apigw/bisnis_info/getbisnis_info")
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

                    }
                });
    }

}
