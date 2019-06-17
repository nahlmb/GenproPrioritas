package com.genpro.genproprioritas.editProfile;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.genpro.genproprioritas.R;

import java.util.ArrayList;

public class EditProfileActivity extends AppCompatActivity implements EditProfileContract.View {
    SharedPreferences userInformation;

    //Data umum
    EditText umumEmail, umumnamaDepan, umumnamaBelakang, umumNoHp, umumFb, umumIg, umumTwitter;
    Spinner spinnerBank;

    //Data domisili
    EditText EditAlamatDomisili, EditRtRwDomisili, EditKelurahanDomisili, EditKecamatanDomisili;
    Spinner provinsiDomisili, kabupatenDomisili;

    //Data ktp
    EditText noKtp, namaKtp, tempatLahirKtp, tanggalLahirKtp, alamatKtp, rtRwKtp, kelurahanKtp, kecamatanKtp;
    Spinner agama, golonganDarah, status, jenisKelamin, provinsiKtp, kabupatenKtp;

    ArrayAdapter<CharSequence> adapterSpinnerKabupaten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        userInformation = getSharedPreferences("userInfo", MODE_PRIVATE);

        //data umum
        umumEmail = findViewById(R.id.edt_umum_email);
        umumnamaDepan= findViewById(R.id.edt_umum_nama_depan);
        umumnamaBelakang = findViewById(R.id.edt_umum_nama_belakang);
        umumNoHp = findViewById(R.id.edt_umum_no_hp);
        umumFb = findViewById(R.id.edt_umum_fb);
        umumIg = findViewById(R.id.edt_umum_ig);
        umumTwitter = findViewById(R.id.edt_umum_twitter);
        spinnerBank = findViewById(R.id.spinner_umum_bank);

        //data domisili
        EditAlamatDomisili = findViewById(R.id.edt_domisili_alamat);
        EditRtRwDomisili = findViewById(R.id.edt_domisili_rt_rw_);
        EditKelurahanDomisili = findViewById(R.id.edt_domisili_kelurahan);
        EditKecamatanDomisili = findViewById(R.id.edt_domisili_kecamatan);
        provinsiDomisili = findViewById(R.id.spinner_domisili_provinsi);
        kabupatenDomisili = findViewById(R.id.spinner_domisili_kabupaten);

        //data ktp
        noKtp = findViewById(R.id.edt_ktp_no_ktp);
        namaKtp = findViewById(R.id.edt_ktp_nama);
        tempatLahirKtp = findViewById(R.id.edt_ktp_tempat_lahir);
        tanggalLahirKtp = findViewById(R.id.edt_ktp_tanggal_lahir);
        agama = findViewById(R.id.spinner_ktp_agama);
        golonganDarah = findViewById(R.id.spinner_ktp_golongan_darah);
        status = findViewById(R.id.spinner_ktp_status_pernikahan);
        jenisKelamin = findViewById(R.id.spinner_ktp_jenis_kelamin);
        alamatKtp = findViewById(R.id.edt_ktp_alamat);
        rtRwKtp = findViewById(R.id.edt_ktp_rt_rw);
        kelurahanKtp = findViewById(R.id.edt_ktp_kelurahan);
        kecamatanKtp = findViewById(R.id.edt_ktp_kecamatan);
        provinsiKtp = findViewById(R.id.spinner_ktp_provinsi);
        kabupatenKtp = findViewById(R.id.spinner_ktp_kabupaten);

        setTextDataUmum();
        setTextDataKtp();
        setTextDataDomisili();



    }

    @Override
    public void setTextDataUmum() {
        String strEmail, strNamaDepan, strNamaBelakang, strNoHp, strFb, strIg, strTwitter;

        strEmail = userInformation.getString("email", "");
        strNamaDepan = userInformation.getString("namaDepan", "");
        strNamaBelakang = userInformation.getString("namaBelakang", "");
        strNoHp = userInformation.getString("phone", "");
        strFb = userInformation.getString("facebook", "");
        strIg = userInformation.getString("instagram", "");
        strTwitter = userInformation.getString("twitter", "");

        umumEmail.setText(strEmail);
        if(strNamaDepan.equals("null")||strNamaDepan.equals("")){
        }else {
            umumnamaDepan.setText(strNamaDepan);
        }

        if(strNamaBelakang.equals("null")||strNamaBelakang.equals("")){
        }else {
            umumnamaBelakang.setText(strNamaBelakang);
        }

        if(strNoHp.equals("null")||strNoHp.equals("")){
        }else {
            umumNoHp.setText(strNoHp);
        }

        if(strFb.equals("null")||strFb.equals("")){
        }else {
            umumFb.setText(strFb);
        }

        if(strIg.equals("null")||strIg.equals("")){
        }else {
            umumIg.setText(strIg);
        }

        if(strTwitter.equals("null")||strTwitter.equals("")){
        }else {
            umumTwitter.setText(strTwitter);
        }

        String bankTerpilih = userInformation.getString("bank", "");
        ArrayAdapter<CharSequence> adapterSpinnerBank = ArrayAdapter.createFromResource(this, R.array.bank, android.R.layout.simple_spinner_item);
        adapterSpinnerBank.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBank.setAdapter(adapterSpinnerBank);

        if (bankTerpilih != null) {
            int spinnerPosition = adapterSpinnerBank.getPosition(bankTerpilih);
            spinnerBank.setSelection(spinnerPosition);
        }


    }

    @Override
    public void setTextDataDomisili() {
        //set TextView data domisili
        String strAlamatDomisili, strRtRwDomisili, strKelurahanDomisili, strKecamatanDomisili, strProvinsiDomisili, strKabupatenDomisili;
        strAlamatDomisili = userInformation.getString("alamatDomisili" , " ");
        strRtRwDomisili = userInformation.getString("rtRwDomisili", "");
        strKelurahanDomisili = userInformation.getString("kelurahanDomisili", "");
        strKecamatanDomisili = userInformation.getString("kecamatanDomisili", "");
        strProvinsiDomisili = userInformation.getString("provinsiDomisili", "");
        strKabupatenDomisili = userInformation.getString("kabupatenDomisili", "");


        if(strAlamatDomisili.equals(null) || strAlamatDomisili.equals("")){
        }else {
            EditAlamatDomisili.setText(strAlamatDomisili);
        }

        if (strRtRwDomisili.equals(null)||strRtRwDomisili.equals("")){
        }else {
            EditRtRwDomisili.setText(strRtRwDomisili);
        }

        if (strKelurahanDomisili.equals(null)||strKelurahanDomisili.equals("")){
        }else {
            EditKelurahanDomisili.setText(strKelurahanDomisili);
        }

        if (strKecamatanDomisili.equals(null)||strKecamatanDomisili.equals("")){
        }else {
            EditKecamatanDomisili.setText(strKecamatanDomisili);


        }

        ArrayAdapter<CharSequence> adapterSpinnerProvinsiDomisili = ArrayAdapter.createFromResource(this, R.array.provinsi, android.R.layout.simple_spinner_item);
        adapterSpinnerProvinsiDomisili.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        provinsiDomisili.setAdapter(adapterSpinnerProvinsiDomisili);

        if (strProvinsiDomisili != null) {
            int spinnerPosition = adapterSpinnerProvinsiDomisili.getPosition(strProvinsiDomisili);
            provinsiDomisili.setSelection(spinnerPosition);

        }


        provinsiDomisili.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Context context = getApplicationContext();
                String strKabupatenDariProvinsi = provinsiDomisili.getSelectedItem().toString();
                Log.d("kabupaten", strKabupatenDariProvinsi);
                setKabupaten(strKabupatenDariProvinsi, true, false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void setTextDataKtp() {
        String strNoKtp, strNamaKtp, strTanggalLahirKtp, strTempatLahirKtp,
                strAgamaDiKtp,strGolonganDarah, strJenisKelamin, strStatusKtp,
                strAlamatKtp, strRtRwKtp, strKelurahanKtp, strKecamatanKtp, strProvinsiKtp, strKabupatenKtp;

        strNoKtp = userInformation.getString("noKtp", "");
        strNamaKtp = userInformation.getString("namaKtp","");
        strTanggalLahirKtp = userInformation.getString("tanggalLahir", "");
        strTempatLahirKtp = userInformation.getString("tempatLahir", "");
        strAgamaDiKtp = userInformation.getString("agama", "");
        strGolonganDarah = userInformation.getString("golonganDarah", "");
        strAlamatKtp = userInformation.getString("alamat", "");
        strRtRwKtp = userInformation.getString("rtRwKtp", "");
        strJenisKelamin = userInformation.getString("jenisKelamin", "");
        strStatusKtp = userInformation.getString("status", "");
        strKelurahanKtp = userInformation.getString("kelurahanKtp", "");
        strProvinsiKtp = userInformation.getString("provinsiKtp", "");
        strKecamatanKtp = userInformation.getString("provinsiKtp", "");
        strKabupatenKtp = userInformation.getString("kabupatenKtp", "");

        if(strNoKtp.equals(null) || strNoKtp.equals("") || strNoKtp.equals("null")){
        }else {
            noKtp.setText(strNoKtp);
        }

        if(strNamaKtp.equals(null) || strNamaKtp.equals("") || strNamaKtp.equals("null")){
        }else {
            namaKtp.setText(strNamaKtp);
        }

        if(strTanggalLahirKtp.equals(null) || strTanggalLahirKtp.equals("") || strTanggalLahirKtp.equals("null")){
        }else {
            tanggalLahirKtp.setText(strTanggalLahirKtp);
        }

        if(strTempatLahirKtp.equals(null) || strTempatLahirKtp.equals("") || strTempatLahirKtp.equals("null")){
        }else {
            tempatLahirKtp.setText(strTempatLahirKtp);
        }

        if(strAlamatKtp.equals(null) || strAlamatKtp.equals("") || strAlamatKtp.equals("null")){
        }else {
            alamatKtp.setText(strAlamatKtp);
        }

        if(strRtRwKtp.equals(null) || strRtRwKtp.equals("") || strRtRwKtp.equals("null")){
        }else {
            rtRwKtp.setText(strRtRwKtp);
        }

        if(strKelurahanKtp.equals(null) || strKelurahanKtp.equals("") || strKelurahanKtp.equals("null")){
        }else {
            kelurahanKtp.setText(strKelurahanKtp);
        }


        if(strKecamatanKtp.equals(null) || strKecamatanKtp.equals("") || strKecamatanKtp.equals("null") || strKecamatanKtp.equals("0")){
        }else {
            kecamatanKtp.setText(strKecamatanKtp);
        }

    }

    @Override
    public void setKabupaten(String namaProvinsi, boolean domisili, boolean ktp) {
        Context context = getApplicationContext();
        Log.d("kabupaten", namaProvinsi);
        String cek = namaProvinsi;
        Log.d("kabupaten", cek);

        if (namaProvinsi.equals("Aceh")) {
            adapterSpinnerKabupaten = ArrayAdapter.createFromResource(context, R.array.ACEH, android.R.layout.simple_spinner_item);
        } else if (namaProvinsi.equals("Sumatera Utara")) {
            adapterSpinnerKabupaten = ArrayAdapter.createFromResource(context, R.array.SUMATERA_UTARA, android.R.layout.simple_spinner_item);

        } else if (namaProvinsi.equals("Sumatera Barat")) {
            adapterSpinnerKabupaten = ArrayAdapter.createFromResource(context, R.array.SULAWESI_BARAT, android.R.layout.simple_spinner_item);

        } else if (namaProvinsi.equals("Riau")){
            adapterSpinnerKabupaten = ArrayAdapter.createFromResource(context, R.array.RIAU, android.R.layout.simple_spinner_item);

        } else if (namaProvinsi.equals("Jambi")){
            adapterSpinnerKabupaten = ArrayAdapter.createFromResource(context, R.array.JAMBI, android.R.layout.simple_spinner_item);

        } else if (namaProvinsi.equals("Sumatera Selatan")){
            adapterSpinnerKabupaten = ArrayAdapter.createFromResource(context, R.array.SUMATERA_SELATAN, android.R.layout.simple_spinner_item);

        } else if (namaProvinsi.equals("Bengkulu")){
            adapterSpinnerKabupaten = ArrayAdapter.createFromResource(context, R.array.BENGKULU, android.R.layout.simple_spinner_item);

        } else if (namaProvinsi.equals("Lampung")){
            adapterSpinnerKabupaten = ArrayAdapter.createFromResource(context, R.array.LAMPUNG, android.R.layout.simple_spinner_item);

        } else if (namaProvinsi.equals("Kepulauan Bangka Belitung")){
            adapterSpinnerKabupaten = ArrayAdapter.createFromResource(context, R.array.KEPULAUAN_BANGKA_BELITUNG, android.R.layout.simple_spinner_item);

        } else if (namaProvinsi.equals("Kepulauan Riau")){
            adapterSpinnerKabupaten = ArrayAdapter.createFromResource(context, R.array.KEPULAUAN_RIAU, android.R.layout.simple_spinner_item);

        } else if (namaProvinsi.equals("DKI Jakarta")){
            adapterSpinnerKabupaten = ArrayAdapter.createFromResource(context, R.array.DKI_JAKARTA, android.R.layout.simple_spinner_item);

        } else if (namaProvinsi.equals("Jawa Barat")){
            adapterSpinnerKabupaten = ArrayAdapter.createFromResource(context, R.array.JAWA_BARAT, android.R.layout.simple_spinner_item);

        } else if (namaProvinsi.equals("Jawa Tengah")){
            adapterSpinnerKabupaten = ArrayAdapter.createFromResource(context, R.array.JAWA_TENGAH, android.R.layout.simple_spinner_item);

        } else if (namaProvinsi.equals("Jawa Timur")){
            adapterSpinnerKabupaten = ArrayAdapter.createFromResource(context, R.array.JAWA_TIMUR, android.R.layout.simple_spinner_item);

        } else if (namaProvinsi.equals("Daerah Istimewa")){
            adapterSpinnerKabupaten = ArrayAdapter.createFromResource(context, R.array.DAERAH_ISTIMEWA, android.R.layout.simple_spinner_item);

        } else if (namaProvinsi.equals("Banten")){
            adapterSpinnerKabupaten = ArrayAdapter.createFromResource(context, R.array.BANTEN, android.R.layout.simple_spinner_item);

        } else if (namaProvinsi.equals("Bali")){
            adapterSpinnerKabupaten = ArrayAdapter.createFromResource(context, R.array.BALI, android.R.layout.simple_spinner_item);

        } else if (namaProvinsi.equals("Nusa Tenggara Barat")){
            adapterSpinnerKabupaten = ArrayAdapter.createFromResource(context, R.array.NUSA_TENGGARA_BARAT, android.R.layout.simple_spinner_item);

        } else if (namaProvinsi.equals("Nusa Tenggara Timur")){
            adapterSpinnerKabupaten = ArrayAdapter.createFromResource(context, R.array.NUSA_TENGGARA_TIMUR, android.R.layout.simple_spinner_item);

        } else if (namaProvinsi.equals("Kalimantan Barat")){
            adapterSpinnerKabupaten = ArrayAdapter.createFromResource(context, R.array.KALIMANTAN_BARAT, android.R.layout.simple_spinner_item);

        } else if (namaProvinsi.equals("Kalimantan Tengah")){
            adapterSpinnerKabupaten = ArrayAdapter.createFromResource(context, R.array.KALIMANTAN_TENGAH, android.R.layout.simple_spinner_item);

        } else if (namaProvinsi.equals("Kalimantan Selatan")){
            adapterSpinnerKabupaten = ArrayAdapter.createFromResource(context, R.array.KALIMANTAN_SELATAN, android.R.layout.simple_spinner_item);

        } else if (namaProvinsi.equals("Kalimantan Timur")){
            adapterSpinnerKabupaten = ArrayAdapter.createFromResource(context, R.array.KALIMANTAN_TIMUR, android.R.layout.simple_spinner_item);

        } else if (namaProvinsi.equals("Kalimantan Utara")){
            adapterSpinnerKabupaten = ArrayAdapter.createFromResource(context, R.array.KALIMANTAN_UTARA, android.R.layout.simple_spinner_item);

        } else if (namaProvinsi.equals("Sulawesi Utara")){
            adapterSpinnerKabupaten = ArrayAdapter.createFromResource(context, R.array.SULAWESI_UTARA, android.R.layout.simple_spinner_item);

        } else if (namaProvinsi.equals("Sulawesi Tengah")){
            adapterSpinnerKabupaten = ArrayAdapter.createFromResource(context, R.array.SULAWESI_TENGAH, android.R.layout.simple_spinner_item);

        } else if (namaProvinsi.equals("Sulawesi Selatan")){
            adapterSpinnerKabupaten = ArrayAdapter.createFromResource(context, R.array.SULAWESI_SELATAN, android.R.layout.simple_spinner_item);

        } else if (namaProvinsi.equals("Sulawesi Tenggara")){
            adapterSpinnerKabupaten = ArrayAdapter.createFromResource(context, R.array.SULAWESI_TENGGARA, android.R.layout.simple_spinner_item);

        } else if (namaProvinsi.equals("Gorontalo")){
            adapterSpinnerKabupaten = ArrayAdapter.createFromResource(context, R.array.GORONTALO, android.R.layout.simple_spinner_item);

        } else if (namaProvinsi.equals("Sulawesi Barat")){
            adapterSpinnerKabupaten = ArrayAdapter.createFromResource(context, R.array.SULAWESI_BARAT, android.R.layout.simple_spinner_item);

        } else if (namaProvinsi.equals("Maluku")){
            adapterSpinnerKabupaten = ArrayAdapter.createFromResource(context, R.array.MALUKU, android.R.layout.simple_spinner_item);

        } else if (namaProvinsi.equals("Maluku Utara")){
            adapterSpinnerKabupaten = ArrayAdapter.createFromResource(context, R.array.MALUKU_UTARA, android.R.layout.simple_spinner_item);

        } else if (namaProvinsi.equals("Papua")){
            adapterSpinnerKabupaten = ArrayAdapter.createFromResource(context, R.array.PAPUA, android.R.layout.simple_spinner_item);

        } else if (namaProvinsi.equals("Papua Barat")){
            adapterSpinnerKabupaten = ArrayAdapter.createFromResource(context, R.array.PAPUA_BARAT, android.R.layout.simple_spinner_item);

        }


        adapterSpinnerKabupaten.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kabupatenDomisili.setAdapter(adapterSpinnerKabupaten);
        kabupatenKtp.setAdapter(adapterSpinnerKabupaten);



    }

    @Override
    public void showLogin() {

    }

    @Override
    public void hideLogin() {

    }

    @Override
    public void updateSucces() {

    }

    @Override
    public void updateFailed(String message) {

    }



}
