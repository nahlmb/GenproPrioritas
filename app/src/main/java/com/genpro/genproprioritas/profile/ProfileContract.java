package com.genpro.genproprioritas.profile;

import android.view.View;

public interface ProfileContract {
    interface View{
        void showLoading();
        void hideLoading();
        void showProfileUtamaUser();
        void showAllUserInfo();
        void setProfileUmum();
        void setProfileDomisili();
        void setProfileKtp();
        void showPopUpMore(android.view.View view);
        void goToEditProfile();
        void saveUserInfo(String[] dataUmum, String[] dataDomisili, String[] dataKtp);
        void someThingFailed(String message);
        void refreshData();
    }

    interface Presenter{
        void pushProfilePic();
        void getUserInfo(String userId);
    }

}
