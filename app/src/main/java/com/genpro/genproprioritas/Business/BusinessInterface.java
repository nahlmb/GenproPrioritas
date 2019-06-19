package com.genpro.genproprioritas.Business;

public interface BusinessInterface {
    interface View{
        void showBusiness();
        void initToolbar();
    }

    interface Presenter{
        void getBusiness();
    }
}
