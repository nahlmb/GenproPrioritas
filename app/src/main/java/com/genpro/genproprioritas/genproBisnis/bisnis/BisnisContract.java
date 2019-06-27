package com.genpro.genproprioritas.genproBisnis.bisnis;

import com.genpro.genproprioritas.model.Bisnis;

import java.util.List;

public interface BisnisContract {
    interface View {
        void showUserBisnis(List<Bisnis.BisnisData> bisnisData);
        void userHaveNoBisnis();
        void addButtonClick();
        void showLoading();
        void hideLoading();
        void someThingFailed(String msg);
        void goToDetailBisnis(Bisnis.BisnisData bisnisData, int i);
        void refreshData();

    }

    interface Presenter {
        void getUserBisnis(String userId);
    }
}
