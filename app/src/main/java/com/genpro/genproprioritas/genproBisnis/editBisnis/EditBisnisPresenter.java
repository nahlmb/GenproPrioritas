package com.genpro.genproprioritas.genproBisnis.editBisnis;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

public class EditBisnisPresenter implements EditBisnisContract.Presenter{
    EditBisnisContract.View view;

    public EditBisnisPresenter(EditBisnisContract.View view) {
        this.view = view;
    }

    @Override
    public void updateBisnisData(String[] dataBisnisInfo) {
        AndroidNetworking.post("http://genpro.dfiserver.com/apigw/bisnis_info/update_bisnis_info")
                .setPriority(Priority.HIGH)
                .addBodyParameter("user_id", dataBisnisInfo[0])
                .addBodyParameter("id_bisnis_info", dataBisnisInfo[1])
                .addBodyParameter("nm_usaha", dataBisnisInfo[2])
                .addBodyParameter("nm_bisnis_lain", dataBisnisInfo[3])
                .addBodyParameter("tentang_usaha", dataBisnisInfo[4])
                .addBodyParameter("jml_karyawan", dataBisnisInfo[5])
                .addBodyParameter("jml_cabang", dataBisnisInfo[6])
                .addBodyParameter("no_tlp", dataBisnisInfo[7])
                .addBodyParameter("omset_tahunan", dataBisnisInfo[8])
                .addBodyParameter("merk", dataBisnisInfo[9])
                .addBodyParameter("facebook", dataBisnisInfo[10])
                .addBodyParameter("instagram", dataBisnisInfo[11])
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getString("error").equals("false")){
                                view.succesUpdateData();

                            }else {
                                view.somethingFailed(response.getString("msg"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            view.somethingFailed(e.getLocalizedMessage());
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        view.somethingFailed(anError.getLocalizedMessage());

                    }
                });
    }
}
