package com.genpro.genproprioritas.drawer.membership;

import com.genpro.genproprioritas.model.Membership;

import java.util.List;

public interface MembershipContract {
    interface View{
        void showLoading();
        void hideLoading();
        void showMembers(List<Membership.DataItem> dataItems);
        void somethingFailed(String msg);
    }
    interface Presenter{
        void getMembers();
    }
}
