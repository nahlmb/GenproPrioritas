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
    }

    interface Presenter{
        void pushProfilePic();
    }

}
