package com.genpro.genproprioritas.membership;

public interface MembershipInterface {
    interface View{
        void initToolbar();
        void showMembers();
    }
    interface Contract{
        void getMembers();
    }
}
