package com.genpro.genproprioritas.main;

import android.view.View;

import com.genpro.genproprioritas.model.Bisnis;

import java.io.File;
import java.io.Serializable;
import java.util.List;

public interface MainContract {
    interface View{
        void showUserInfo();
        void showProfileImage(File imageFile);
        void saveUserInfo(String[] dataUmum, String[] dataDomisili, String[] dataKtp);
        void showUserBusinnes(List<Bisnis.BisnisData> bisnisData);
        void initToolbar();
        void showLoading();
        void hideLoading();
        void someThingFailed(String message);
        void goToProfile();
        void goToDetailBisnis(Bisnis.BisnisData bisnisData, int i);
        void logOut();
        void showNetworkError();
        void showPopUpMore(android.view.View view);
        void showPopMoreListBisnis(android.view.View view);
        void refreshData();
        void showBottomSheet();
        void goToBisnis();
        void showIDUser();
        void showProfileImageToImageView();
        void uploadPhotoSucces(String photo);
    }

    interface Presenter {
        void getProfileImage(String userId, File file);
        void getUserInfo(String userId);
        void getBusinnes(String userId);
    }
}
