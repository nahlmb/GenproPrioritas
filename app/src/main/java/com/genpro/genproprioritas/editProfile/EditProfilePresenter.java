package com.genpro.genproprioritas.editProfile;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

public class EditProfilePresenter implements EditProfileContract.Presenter{
    EditProfileContract.View view;

    public EditProfilePresenter(EditProfileContract.View view) {
        this.view = view;
    }

    @Override
    public void pushEditProfile(String[] dataUmum, String[] dataDomisili, String[] dataKtp) {
        pushDataUmum(dataUmum);
        pushDataDomisili(dataDomisili);
        pushDataKtp(dataKtp);
    }

    @Override
    public void pushDataUmum(String[] dataUmum) {
        AndroidNetworking.post("http://genprodev.lavenderprograms.com/apigw/users/update_profile_umum/")
                .setPriority(Priority.HIGH)
                .addBodyParameter("user_id", dataUmum[0])
                .addBodyParameter("email_umum", dataUmum[1])
                .addBodyParameter("nama_depan", dataUmum[2])
                .addBodyParameter("nama_belakang", dataUmum[3])
                .addBodyParameter("bank", dataUmum[4])
                .addBodyParameter("phone", dataUmum[5])
                .addBodyParameter("facebook", dataUmum[6])
                .addBodyParameter("instagram", dataUmum[7])
                .addBodyParameter("twitter", dataUmum[8])
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response != null){
                            try {
                                if(response.getString("error").equals("false")){
                                    view.updateSucces();
                                }else if(response.getString("error").equals("true")){
                                    view.updateFailed(response.getString("msg"));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                view.updateFailed(e.getLocalizedMessage());
                            }
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        view.updateFailed(anError.getLocalizedMessage());

                    }
                });

    }

    @Override
    public void pushDataDomisili(String[] dataDomisili) {

    }

    @Override
    public void pushDataKtp(String[] dataKtp) {
        AndroidNetworking.post("http://genprodev.lavenderprograms.com/apigw/users/update_profile_ktp/")
                .setPriority(Priority.HIGH)
                .addBodyParameter("user_id", dataKtp[0])
                .addBodyParameter("no_ktp", dataKtp[1])
                .addBodyParameter("nama_ktp", dataKtp[2])
                .addBodyParameter("tempat_lahir", dataKtp[3])
                .addBodyParameter("tanggal_lahir", dataKtp[4])
                .addBodyParameter("agama", dataKtp[5])
                .addBodyParameter("golongan_darah", dataKtp[6])
                .addBodyParameter("jenis_kelamin", dataKtp[7])
                .addBodyParameter("status", dataKtp[8])
                .addBodyParameter("alamat", dataKtp[9])
                .addBodyParameter("rt_rw_ktp", dataKtp[10])
                .addBodyParameter("kelurahan_ktp", dataKtp[11])
                .addBodyParameter("kecamatan_ktp", dataKtp[12])
                .addBodyParameter("id_propinsi", dataKtp[13])
                .addBodyParameter("id_kabupaten", dataKtp[14])
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response != null){
                            try {
                                if(response.getString("error").equals("false")){
                                    view.updateSucces();
                                }else if(response.getString("error").equals("true")){
                                    view.updateFailed(response.getString("msg"));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                view.updateFailed(e.getLocalizedMessage());
                            }
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        view.updateFailed(anError.getLocalizedMessage());

                    }
                });

    }
}
