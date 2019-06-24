package com.genpro.genproprioritas.editBisnis;

import com.genpro.genproprioritas.model.Bisnis;

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
