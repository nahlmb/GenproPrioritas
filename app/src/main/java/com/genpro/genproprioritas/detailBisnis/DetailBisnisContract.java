package com.genpro.genproprioritas.detailBisnis;

import com.genpro.genproprioritas.model.Bisnis;

public interface DetailBisnisContract {

    interface View {
        void showLoading();
        void hideLoading();
        void showUserBisnisDetail(Bisnis.BisnisData bisnisData);
        void goToEdit(Bisnis.BisnisData bisnisData);
    }

    interface Presenter {
        void getBusinnes(String userId, int i);
    }
}
