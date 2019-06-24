package com.genpro.genproprioritas.editBisnis;

import com.androidnetworking.AndroidNetworking;

public class EditBisnisPresenter implements EditBisnisContract.Presenter{
    EditBisnisContract.View view;

    public EditBisnisPresenter(EditBisnisContract.View view) {
        this.view = view;
    }

    @Override
    public void updateBisnisData(String[] dataBisnisInfo) {
        AndroidNetworking.post("");

    }
}
