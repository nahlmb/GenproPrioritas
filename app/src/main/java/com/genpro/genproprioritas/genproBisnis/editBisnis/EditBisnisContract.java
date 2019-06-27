package com.genpro.genproprioritas.genproBisnis.editBisnis;

public interface EditBisnisContract {
    interface View {
        void setAllBisnisData();
        void updateBisnisDataClick();
        void succesUpdateData();
        void somethingFailed(String msg);
    }

    interface Presenter{
        void updateBisnisData(String[] dataInfoBisnis);

    }
}
