package com.genpro.genproprioritas.main;

import android.view.View;

import com.genpro.genproprioritas.model.Bisnis;

import java.io.Serializable;
import java.util.List;

public interface MainContract {
    interface View{
        void showUserInfo();
        void saveUserInfo(String[] dataUmum, String[] dataDomisili, String[] dataKtp);
        void showUserBusinnes(List<Bisnis.BisnisData> bisnisData);
        void initToolbar();
        void showLoading();
        void hideLoading();
        void someThingFailed(String message);
        void goToProfile();
        void goToDetailBisnis(Bisnis.BisnisData bisnisData);
        void logOut();
        void showNetworkError();
        void showPopUpMore(android.view.View view);
        void refreshData();
    }

    interface Presenter {
        void getUserInfo(String userId);
        void getBusinnes(String userId);
    }
}
