package com.genpro.genproprioritas.genproBisnis.insertBisnis;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

public class InsertBisnisPresenter implements InsertBisnisContract.Presenter {
    InsertBisnisContract.View view;

    public InsertBisnisPresenter(InsertBisnisContract.View view) {
        this.view = view;
    }

    @Override
    public void pushNewBisnis(String[] dataBisnisBaru) {
        AndroidNetworking.post("http://genpro.dfiserver.com/apigw/bisnis_info/insert_bisnis_info")
                .setPriority(Priority.HIGH)
                .addBodyParameter("user_id", dataBisnisBaru[0])
                .addBodyParameter("nm_usaha", dataBisnisBaru[1])
                .addBodyParameter("nm_bisnis_lain", dataBisnisBaru[2])
                .addBodyParameter("tentang_usaha", dataBisnisBaru[3])
                .addBodyParameter("jml_karyawan", dataBisnisBaru[4])
                .addBodyParameter("jml_cabang", dataBisnisBaru[5])
                .addBodyParameter("no_tlp", dataBisnisBaru[6])
                .addBodyParameter("omset_tahunan", dataBisnisBaru[7])
                .addBodyParameter("merk", dataBisnisBaru[8])
                .addBodyParameter("facebook", dataBisnisBaru[9])
                .addBodyParameter("instagram", dataBisnisBaru[10])
                .addBodyParameter("nm_usaha_lain", dataBisnisBaru[11])
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getString("error").equals("false")){
                                view.success();
                            }else{
                                view.somethingFailed(response.getString("emsg"));
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
