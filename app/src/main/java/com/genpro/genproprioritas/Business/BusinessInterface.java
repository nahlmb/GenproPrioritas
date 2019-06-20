package com.genpro.genproprioritas.Business;

public interface BusinessInterface {
    interface View{
        void showBusiness();
        void initToolbar();
        void showFab();
    }

    interface Presenter{
        void getBusiness();
    }
}
