package com.genpro.genproprioritas.bisnis;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.genpro.genproprioritas.model.Bisnis;

public class BisnisPresenter implements BisnisContract.Presenter{
    BisnisContract.View view;

    public BisnisPresenter(BisnisContract.View view) {
        this.view = view;
    }

    @Override
    public void getUserBisnis(String userId) {
        AndroidNetworking.post("http://genprodev.lavenderprograms.com/apigw/bisnis_info/getbisnis_info")
                .setPriority(Priority.HIGH)
                .addBodyParameter("user_id", userId)
                .build()
                .getAsObject(Bisnis.class, new ParsedRequestListener<Bisnis>() {

                    @Override
                    public void onResponse(Bisnis response) {
                        if(response != null){
                            view.showUserBisnis(response.getData());
                        }else{
                            view.someThingFailed(response.getMsg());
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        view.someThingFailed(anError.getLocalizedMessage());

                    }
                });

    }
}
