package com.genpro.genproprioritas.genproBisnis.detailBisnis;

import android.view.View;

import com.genpro.genproprioritas.model.Bisnis;

public interface DetailBisnisContract {

    interface View {
        void showLoading();
        void hideLoading();
        void somethingFailed(String msg);
        void showUserBisnisDetail(Bisnis.BisnisData bisnisData);
        void goToEdit(Bisnis.BisnisData bisnisData);
        void showPopUpMenu(android.view.View view, String userId, String idBisnisInfo);
        void delateBisnis(String userId, String idBisnisInfo);
        void succesDelate();
    }

    interface Presenter {
        void getBusinnes(String userId, int i);
        void delateBisnis(String userId, String idBisnisInfo);
    }
}
