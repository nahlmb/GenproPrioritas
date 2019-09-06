package com.genpro.genproprioritas.drawer.membership;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.genpro.genproprioritas.model.Membership;

public class MembershipPresenter implements MembershipContract.Presenter {
    MembershipContract.View view;

    public MembershipPresenter(MembershipContract.View view) {
        this.view = view;
    }

    @Override
    public void getMembers() {
        AndroidNetworking.post("http://genpro.dfiserver.com/users/get_all_users/")
                .setPriority(Priority.HIGH)
                .build()
                .getAsObject(Membership.class, new ParsedRequestListener<Membership>() {

                    @Override
                    public void onResponse(Membership response) {
                        view.showMembers(response.getData());

                    }

                    @Override
                    public void onError(ANError anError) {
                        view.somethingFailed(anError.getLocalizedMessage());
                        Log.d("membership", anError.getLocalizedMessage());

                    }
                });
    }
}
