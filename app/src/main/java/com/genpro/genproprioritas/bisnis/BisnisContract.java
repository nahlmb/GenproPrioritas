package com.genpro.genproprioritas.bisnis;

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

    }

    interface Presenter {
        void getUserBisnis(String userId);
    }
}
