package com.genpro.genproprioritas.insertBisnis;

public interface InsertBisnisContract {
    interface Presenter {
        void pushNewBisnis(String[] dataBisnisBaru);

    }

    interface View {
        void pushNewBisnis(String[] dataBisnisBaru);
        void buttonBuatBisnisBaruClicked();
        void showLoading();
        void hideLoading();
        void success();
        void getArrayDataBisnis();
        void somethingFailed(String msg);

    }
}
