package com.genpro.genproprioritas.editBisnis;

public interface EditBisnisContract {
    interface View {
        void setAllBisnisData();
        void updateBisnisDataClick();
    }

    interface Presenter{
        void updateBisnisData(String[] dataInfoBisnis);

    }
}
