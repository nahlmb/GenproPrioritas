package com.genpro.genproprioritas.membership;

import com.genpro.genproprioritas.model.Member;
import com.genpro.genproprioritas.model.Membership;

import java.util.List;

public interface MembershipContract {
    interface View{
        void showLoading();
        void hideLoading();
        void showMembers(List<Member> members);
        void somethingFailed(String msg);
    }
    interface Presenter{
        void getMembers();
    }
}
