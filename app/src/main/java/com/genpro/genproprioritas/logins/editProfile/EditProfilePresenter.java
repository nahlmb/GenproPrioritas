package com.genpro.genproprioritas.logins.editProfile;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.lang.reflect.Array;

public class EditProfilePresenter implements EditProfileContract.Presenter {
    EditProfileContract.View view;
    int SUCCESS_FULL_PUSH_CODE = 1000;
    boolean[] booleans = {false, false, false};

    public EditProfilePresenter(EditProfileContract.View view) {
        this.view = view;
    }

    @Override
    public void pushEditProfile(String[] dataUmum, String[] dataDomisili, String[] dataKtp) {
        view.showLoading();
        pushDataUmum(dataUmum);
        pushDataDomisili(dataDomisili);
        pushDataKtp(dataKtp);
    }

    @Override
    public void pushDataUmum(String[] dataUmum) {
//        view.showLoading();

        AndroidNetworking.post("http://genpro.dfiserver.com/apigw/users/update_profile_umum/")
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
                        if (response != null) {
                            try {
                                if (response.getString("error").equals("false")) {
                                    booleans[0] = true;
                                    if(booleans[0] == true && booleans[1] == true && booleans[2] == true){view.updateSucces();}
                                    Log.d("BOOLEANS" , java.util.Arrays.toString(booleans));
                                } else if (response.getString("error").equals("true")) {
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
//        view.showLoading();
        AndroidNetworking.post("http://genpro.dfiserver.com/apigw/users/update_profile_domisili/")
                .setPriority(Priority.HIGH)
                .addBodyParameter("user_id", dataDomisili[0])
                .addBodyParameter("id_propinsi_domisili", dataDomisili[1])
                .addBodyParameter("id_kabupaten_domisili", dataDomisili[2])
                .addBodyParameter("alamat_domisili", dataDomisili[3])
                .addBodyParameter("rt_rw_domisili", dataDomisili[4])
                .addBodyParameter("kelurahan_domisili", dataDomisili[5])
                .addBodyParameter("kecamatan_domisili", dataDomisili[6])
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {

                    @Override
                    public void onResponse(JSONObject response) {
                        if (response != null) {
                            try {
                                if (response.getString("error").equals("false")) {
                                    booleans[1] = true;
                                    if(booleans[0] == true && booleans[1] == true && booleans[2] == true){view.updateSucces();}
                                    Log.d("BOOLEANS" , java.util.Arrays.toString(booleans));
                                } else if (response.getString("error").equals("true")) {
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
    public void pushDataKtp(String[] dataKtp) {
        AndroidNetworking.post("http://genpro.dfiserver.com/apigw/users/update_profile_ktp/")
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


                        if (response != null) {
                            try {
                                if (response.getString("error").equals("false")) {
//                                    view.isPushKtpSucces();
                                    booleans[2] = true;
                                    if(booleans[0] == true && booleans[1] == true && booleans[2] == true){view.updateSucces();}
                                    Log.d("BOOLEANS" , java.util.Arrays.toString(booleans));
                                } else if (response.getString("error").equals("true")) {
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
    public void pushPhoto(File file) {
        AndroidNetworking.upload("http://genpro.dfiserver.com/apigw/users/update_profile_umum/")
                .setPriority(Priority.HIGH)
                .addMultipartFile("pic", file)
                .build()
                .setUploadProgressListener(new UploadProgressListener() {
                    @Override
                    public void onProgress(long bytesUploaded, long totalBytes) {

                    }
                }).getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {

            }

            @Override
            public void onError(ANError anError) {

            }
        });
    }

}

