package com.genpro.genproprioritas.logins.profile;

import java.io.File;

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
        void getPicFromCamera();
        void getPicFromGallery();
        void showDialogTakeImage();
        void pushPhoto(File imageFile);
        void uploadPhotoSucces(String photo);
        void cropImageAutoSelection();
        

    }

    interface Presenter{
        void getUserInfo(String userId);
        void pushPhoto(String userId, File file);
    }

}
