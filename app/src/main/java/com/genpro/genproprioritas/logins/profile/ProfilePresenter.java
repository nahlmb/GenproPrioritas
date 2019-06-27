package com.genpro.genproprioritas.logins.profile;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class ProfilePresenter implements ProfileContract.Presenter {
    ProfileContract.View view;

    public ProfilePresenter(ProfileContract.View view) {
        this.view = view;
    }


    @Override
    public void getUserInfo(String userId) {
        view.showLoading();

        AndroidNetworking.post("http://genprodev.lavenderprograms.com/apigw/users/get_registered_user/")
                .setPriority(Priority.HIGH)
                .addBodyParameter("user_id", userId)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            if (response.getString("error").equals("false")) {
                                //get data profile umum
                                JSONObject profileUmum = response.getJSONObject("data").getJSONObject("umum");
                                String noAnggota = profileUmum.getString("no_anggota");
                                String email = profileUmum.getString("email");
                                String user_name = profileUmum.getString("user_name");
                                String namaDepan = profileUmum.getString("nama_depan");
                                String namaBelakang = profileUmum.getString("nama_belakang");
                                String phone = profileUmum.getString("phone");
                                String bank = profileUmum.getString("bank");
                                String facebook = profileUmum.getString("facebook");
                                String twitter = profileUmum.getString("twitter");
                                String instagram = profileUmum.getString("instagram");
                                String picture = profileUmum.getString("picture");

                                //data aktifasi
                                boolean boleanAktifasi = response.getBoolean("is_active");
                                String aktifasi = String.valueOf(boleanAktifasi);
                                String masaAktif = response.getString("active_date");

                                String[] arrayUmum = {noAnggota, email, user_name, namaDepan, namaBelakang, phone, bank, facebook, twitter, instagram, aktifasi, masaAktif, picture};

                                //get data domisili
                                JSONObject profileDomisili = response.getJSONObject("data").getJSONObject("domisili");
                                String alamatDomisili = profileDomisili.getString("alamat_domisili");
                                String rtRwDomisili = profileDomisili.getString("rt_rw_domisili");
                                String kelurahanDomisili = profileDomisili.getString("kelurahan_domisili");
                                String kecamatanDomisili = profileDomisili.getString("kecamatan_domisili");
                                String provinsiDomisili = profileDomisili.getString("provinsi_domisili");
                                String kabupatenDomisili = profileDomisili.getString("kabupaten_domisili");

                                String[] arrayDomisili = {alamatDomisili, rtRwDomisili, kelurahanDomisili,
                                        kecamatanDomisili, provinsiDomisili, kabupatenDomisili};

                                //get data ktp
                                JSONObject profileKTP = response.getJSONObject("data").getJSONObject("ktp");
                                String noKtp = profileKTP.getString("no_ktp");
                                String namaKtp = profileKTP.getString("nama_ktp");
                                String tanggalLahir = profileKTP.getString("tanggal_lahir");
                                String tempatLahir = profileKTP.getString("tempat_lahir");
                                String agama = profileKTP.getString("agama");
                                String golonganDarah = profileKTP.getString("golongan_darah");
                                String jenisKelamin = profileKTP.getString("jenis_kelamin");
                                String status = profileKTP.getString("status");
                                String alamatKtp = profileKTP.getString("alamat");
                                String rtRwKtp = profileKTP.getString("rt_rw_ktp");
                                String kelurahanKtp = profileKTP.getString("kelurahan_ktp");
                                String kecamatanKtp = profileKTP.getString("kecamatan_ktp");
                                String provinsiKtp = profileKTP.getString("provinsi");
                                String kabupatenKtp = profileKTP.getString("kabupaten");

                                String[] arrayKtp = {noKtp, namaKtp, tanggalLahir, tempatLahir, agama, golonganDarah, jenisKelamin, status,
                                        alamatKtp, rtRwKtp, kelurahanKtp, kecamatanKtp, provinsiKtp, kabupatenKtp};

                                view.hideLoading();
                                view.saveUserInfo(arrayUmum, arrayDomisili, arrayKtp);

                            }else if (response.getString("error").equals("true")){
                                view.hideLoading();
                                view.someThingFailed(response.getString("msg"));

                            }

                        } catch (JSONException e) {
                            view.hideLoading();
                            view.someThingFailed(e.getLocalizedMessage());
                            e.printStackTrace();
                            Log.d("networking", e.getLocalizedMessage());
                            Log.d("networking", e.getMessage());
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        view.hideLoading();
                        view.someThingFailed(anError.getLocalizedMessage());
                        Log.d("networking", anError.getLocalizedMessage());
                        Log.d("netwoking", anError.getErrorBody());
                        Log.d("netwoking", anError.getErrorDetail());
                        Log.d("netwoking", anError.getMessage());

                    }
                });

    }

    @Override
    public void pushPhoto(String userId, File file) {

        AndroidNetworking.upload("http://genprodev.lavenderprograms.com/apigw/users/update_profile_photo/")
                .setPriority(Priority.HIGH)
                .addMultipartFile("pic", file)
                .addMultipartParameter("user_id", userId)
                .build()
                .setUploadProgressListener(new UploadProgressListener() {
                    @Override
                    public void onProgress(long bytesUploaded, long totalBytes) {
                        view.showLoading();

                    }
                }).getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                if(response!=null){
                    try {
                        if(response.getString("error").equals("false")){
                            String pic = response.getJSONObject("data").getString("picture");
                            view.uploadPhotoSucces(pic);
                        }else {
                            view.someThingFailed(response.getString("msg"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        view.someThingFailed(e.getLocalizedMessage());
                    }

                    view.hideLoading();
                }
            }

            @Override
            public void onError(ANError anError) {
                view.someThingFailed(anError.getLocalizedMessage());
            }
        });

    }
}
