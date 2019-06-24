package com.genpro.genproprioritas.editBisnis;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

public class EditBisnisPresenter implements EditBisnisContract.Presenter{
    EditBisnisContract.View view;

    public EditBisnisPresenter(EditBisnisContract.View view) {
        this.view = view;
    }

    @Override
    public void updateBisnisData(String[] dataBisnisInfo) {
        AndroidNetworking.post("http://genprodev.lavenderprograms.com/apigw/bisnis_info/update_bisnis_info")
                .setPriority(Priority.HIGH)
                .addBodyParameter("userId", dataBisnisInfo[0])
                .addBodyParameter("id_bisnis_info", dataBisnisInfo[0])
                .addBodyParameter("nm_usaha", dataBisnisInfo[0])
                .addBodyParameter("nm_bisnis_lain", dataBisnisInfo[0])
                .addBodyParameter("tentang_usaha", dataBisnisInfo[0])
                .addBodyParameter("jml_karyawan", dataBisnisInfo[0])
                .addBodyParameter("jml_cabang", dataBisnisInfo[0])
                .addBodyParameter("no_tlp", dataBisnisInfo[0])
                .addBodyParameter("omset_tahunan", dataBisnisInfo[0])
                .addBodyParameter("merk", dataBisnisInfo[0])
                .addBodyParameter("facebook", dataBisnisInfo[0])
                .addBodyParameter("instagram", dataBisnisInfo[0])
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //todo handle it!
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });


    }
}
