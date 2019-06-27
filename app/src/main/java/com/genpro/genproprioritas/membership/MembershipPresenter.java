package com.genpro.genproprioritas.membership;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.genpro.genproprioritas.model.Member;
import com.genpro.genproprioritas.model.Membership;

import java.util.List;

public class MembershipPresenter implements MembershipContract.Presenter {
    MembershipContract.View view;

    public MembershipPresenter(MembershipContract.View view) {
        this.view = view;
    }

    @Override
    public void getMembers() {
        view.somethingFailed("get members..");
        AndroidNetworking.post("http://genprodev.lavenderprograms.com/apigw/users/get_all_users/")
                .setPriority(Priority.HIGH)
                .build()
                .getAsObject(Membership.class, new ParsedRequestListener<Member>() {


                    @Override
                    public void onResponse(Member response) {
                        view.showMembers(response.getMembers());

                    }

                    @Override
                    public void onError(ANError anError) {
                        view.somethingFailed(anError.getLocalizedMessage());
                        Log.d("membership", anError.getLocalizedMessage());

                    }
                });
    }
}
