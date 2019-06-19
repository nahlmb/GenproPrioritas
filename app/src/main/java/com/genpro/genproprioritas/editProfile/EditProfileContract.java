package com.genpro.genproprioritas.editProfile;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

public interface EditProfileContract {
    interface View{
        void setTextDataUmum();
        void setTextDataDomisili();
        void setTextDataKtp();
        void setKabupaten(String namaProvinsi, boolean domisili, boolean ktp);
        void showLogin();
        void hideLogin();
        void updateSucces();
        void updateFailed(String message);
        void pushEditProfile();
    }

    interface Presenter{
        void pushEditProfile(String[] dataUmum, String[] dataDomisili, String[] dataKtp);
        void pushDataUmum(String[] dataUmum);
        void pushDataDomisili(String[] dataDomisili);
        void pushDataKtp(String[] dataKtp);
    }
}
