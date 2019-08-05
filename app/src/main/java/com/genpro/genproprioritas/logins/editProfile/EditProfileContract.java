package com.genpro.genproprioritas.logins.editProfile;

import java.io.File;

public interface EditProfileContract {
    interface View{
        void setTextDataUmum();
        void setTextDataDomisili();
        void setTextDataKtp();
        void setKabupaten(String namaProvinsi, boolean domisili, boolean ktp);
        void showLoading();
        void hideLoading();
        void updateSucces();
        void updateFailed(String message);
        void pushEditProfile();
        void pushPhoto(File imageFile);


    }

    interface Presenter{
        void pushEditProfile(String[] dataUmum, String[] dataDomisili, String[] dataKtp);
        void pushDataUmum(String[] dataUmum);
        void pushDataDomisili(String[] dataDomisili);
        void pushDataKtp(String[] dataKtp);
        void pushPhoto(File file);
    }
}
