package com.genpro.genproprioritas.main;

import android.view.View;

public interface MainContract {
    interface View{
        void showUserInfo();
        void saveUserInfo(String[] dataUmum, String[] dataDomisili, String[] dataKtp);
        void showUserBusinnes();
        void initToolbar();
        void showLoading();
        void hideLoading();
        void someThingFailed(String message);
        void goToProfile();
        void logOut();
        void showNetworkError();
        void showPopUpMore(android.view.View view);
    }

    interface Presenter {
        void getUserInfo(String userId);
        void getBusinnes();
    }
}
