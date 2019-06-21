package com.genpro.genproprioritas.editProfile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.genpro.genproprioritas.R;
import com.genpro.genproprioritas.profile.ProfileActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class EditProfileActivity extends AppCompatActivity implements EditProfileContract.View {
    EditProfilePresenter presenter;
    SharedPreferences userInformation;

    //Data umum
    EditText umumEmail, umumnamaDepan, umumnamaBelakang, umumNoHp, umumFb, umumIg, umumTwitter;
    Spinner spinnerBank;

    //Data domisili
    EditText editAlamatDomisili, editRtRwDomisili, editKelurahanDomisili, editKecamatanDomisili;
    Spinner provinsiDomisili, kabupatenDomisili;

    //Data ktp
    EditText noKtp, namaKtp, tempatLahirKtp, tanggalLahirKtp, alamatKtp, rtRwKtp, kelurahanKtp, kecamatanKtp;
    Spinner spinnerAgamaDiKtp, golonganDarah, status, jenisKelamin, provinsiKtp, kabupatenKtp;

    ArrayAdapter<CharSequence> adapterSpinnerKabupatenDomisili, adapterSpinnerKabupatenKtp;

    //button
    Button btnUpdateProfile;

    //take photo
    static final int REQUEST_TAKE_PHOTO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        //for Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimary));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //setup
        presenter = new EditProfilePresenter(this);
        userInformation = getSharedPreferences("userInfo", MODE_PRIVATE);

        //setupdata
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
        editAlamatDomisili = findViewById(R.id.edt_domisili_alamat);
        editRtRwDomisili = findViewById(R.id.edt_domisili_rt_rw_);
        editKelurahanDomisili = findViewById(R.id.edt_domisili_kelurahan);
        editKecamatanDomisili = findViewById(R.id.edt_domisili_kecamatan);
        provinsiDomisili = findViewById(R.id.spinner_domisili_provinsi);
        kabupatenDomisili = findViewById(R.id.spinner_domisili_kabupaten);
        //data ktp
        noKtp = findViewById(R.id.edt_ktp_no_ktp);
        namaKtp = findViewById(R.id.edt_ktp_nama);
        tempatLahirKtp = findViewById(R.id.edt_ktp_tempat_lahir);
        tanggalLahirKtp = findViewById(R.id.edt_ktp_tanggal_lahir);
        spinnerAgamaDiKtp = findViewById(R.id.spinner_ktp_agama);
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

        btnUpdateProfile = findViewById(R.id.btn_edit_profile);
        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushEditProfile();
            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent backToProfile = new Intent(EditProfileActivity.this, ProfileActivity.class);
        startActivity(backToProfile);
        finish();
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
            editAlamatDomisili.setText(strAlamatDomisili);
        }

        if (strRtRwDomisili.equals(null)||strRtRwDomisili.equals("")){
        }else {
            editRtRwDomisili.setText(strRtRwDomisili);
        }

        if (strKelurahanDomisili.equals(null)||strKelurahanDomisili.equals("")){
        }else {
            editKelurahanDomisili.setText(strKelurahanDomisili);
        }

        if (strKecamatanDomisili.equals(null)||strKecamatanDomisili.equals("")){
        }else {
            editKecamatanDomisili.setText(strKecamatanDomisili);


        }

        final ArrayAdapter<CharSequence> adapterSpinnerProvinsiDomisili = ArrayAdapter.createFromResource(this, R.array.provinsi, android.R.layout.simple_spinner_item);
        adapterSpinnerProvinsiDomisili.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        provinsiDomisili.setAdapter(adapterSpinnerProvinsiDomisili);

        if (strProvinsiDomisili != null) {
            int spinnerPositionDomisili = adapterSpinnerProvinsiDomisili.getPosition(strProvinsiDomisili);
            provinsiDomisili.setSelection(spinnerPositionDomisili);
            Log.d("kabupaten", strProvinsiDomisili);

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

        adapterSpinnerProvinsiDomisili.notifyDataSetChanged();

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
        strKecamatanKtp = userInformation.getString("kecamatanKtp", "");
        strKabupatenKtp = userInformation.getString("kabupatenKtp", "");

        if(strNoKtp.equals("") || strNoKtp.equals("null")){
        }else {
            noKtp.setText(strNoKtp);
        }

        if(strNamaKtp.equals("") || strNamaKtp.equals("null")){
        }else {
            namaKtp.setText(strNamaKtp);
        }

        if(strTanggalLahirKtp.equals("") || strTanggalLahirKtp.equals("null")){
        }else {
            tanggalLahirKtp.setText(strTanggalLahirKtp);
        }

        if(strTempatLahirKtp.equals("") || strTempatLahirKtp.equals("null")){
        }else {
            tempatLahirKtp.setText(strTempatLahirKtp);
        }

        if(strAlamatKtp.equals("") || strAlamatKtp.equals("null")){
        }else {
            alamatKtp.setText(strAlamatKtp);
        }

        if(strRtRwKtp.equals("") || strRtRwKtp.equals("null")){
        }else {
            rtRwKtp.setText(strRtRwKtp);
        }

        if(strKelurahanKtp.equals("") || strKelurahanKtp.equals("null")){
        }else {
            kelurahanKtp.setText(strKelurahanKtp);
        }


        if(strKecamatanKtp.equals(null) || strKecamatanKtp.equals("") || strKecamatanKtp.equals("null") || strKecamatanKtp.equals("0")){
        }else {
            kecamatanKtp.setText(strKecamatanKtp);
        }

        ArrayAdapter<CharSequence> adapterSpinnerAgama= ArrayAdapter.createFromResource(this, R.array.agama_di_ktp, android.R.layout.simple_spinner_item);
        adapterSpinnerAgama.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAgamaDiKtp.setAdapter(adapterSpinnerAgama);

        if (strAgamaDiKtp != null) {
            int spinnerPositionAgamaDiKtp = adapterSpinnerAgama.getPosition(strAgamaDiKtp);
            spinnerAgamaDiKtp.setSelection(spinnerPositionAgamaDiKtp);
        }

        ArrayAdapter<CharSequence> adapterSpinnerGoldar= ArrayAdapter.createFromResource(this, R.array.golongan_darah, android.R.layout.simple_spinner_item);
        adapterSpinnerAgama.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        golonganDarah.setAdapter(adapterSpinnerGoldar);

        if (strGolonganDarah != null) {
            int spinnerPositionGolonganDarah = adapterSpinnerAgama.getPosition(strGolonganDarah);
            golonganDarah.setSelection(spinnerPositionGolonganDarah);
        }

        ArrayAdapter<CharSequence> adapterSpinnerJenisKelamin = ArrayAdapter.createFromResource(this, R.array.jenis_kelamin, android.R.layout.simple_spinner_item);
        adapterSpinnerAgama.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jenisKelamin.setAdapter(adapterSpinnerJenisKelamin);

        if (strJenisKelamin != null) {
            int spinnerPositionJenisKelamin = adapterSpinnerAgama.getPosition(strJenisKelamin);
            jenisKelamin.setSelection(spinnerPositionJenisKelamin);
        }

        ArrayAdapter<CharSequence> adapterSpinnerStatusNikah = ArrayAdapter.createFromResource(this, R.array.status_nikah, android.R.layout.simple_spinner_item);
        adapterSpinnerAgama.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(adapterSpinnerStatusNikah);

        if (strStatusKtp != null) {
            int spinnerPositionStatus = adapterSpinnerAgama.getPosition(strStatusKtp);
            status.setSelection(spinnerPositionStatus);
        }

        ArrayAdapter<CharSequence> adapterSpinnerKtp = ArrayAdapter.createFromResource(this, R.array.provinsi, android.R.layout.simple_spinner_item);
        adapterSpinnerKtp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        provinsiKtp.setAdapter(adapterSpinnerKtp);

        if (strProvinsiKtp != null) {
            int spinnerPositionKtp = adapterSpinnerKtp.getPosition(strProvinsiKtp);
            provinsiKtp.setSelection(spinnerPositionKtp);
            Log.d("kabupaten", strProvinsiKtp);

        }


        provinsiKtp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Context context = getApplicationContext();
                String strKabupatenDariProvinsiKtp = provinsiKtp.getSelectedItem().toString();
                setKabupaten(strKabupatenDariProvinsiKtp, false, true);
                Log.d("kabupaten", strKabupatenDariProvinsiKtp);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapterSpinnerKtp.notifyDataSetChanged();



    }

    @Override
    public void setKabupaten(String namaProvinsi, boolean domisili, boolean ktp) {
        Context context = getApplicationContext();
        Log.d("kabupaten", namaProvinsi);
        String cek = namaProvinsi;
        Log.d("kabupaten", cek);


        if(domisili && !ktp){
            if (namaProvinsi.equals("Aceh")) {
                adapterSpinnerKabupatenDomisili = ArrayAdapter.createFromResource(context, R.array.ACEH, android.R.layout.simple_spinner_item);
            } else if (namaProvinsi.equals("Sumatera Utara")) {
                adapterSpinnerKabupatenDomisili = ArrayAdapter.createFromResource(context, R.array.SUMATERA_UTARA, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Sumatera Barat")) {
                adapterSpinnerKabupatenDomisili = ArrayAdapter.createFromResource(context, R.array.SULAWESI_BARAT, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Riau")){
                adapterSpinnerKabupatenDomisili = ArrayAdapter.createFromResource(context, R.array.RIAU, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Jambi")){
                adapterSpinnerKabupatenDomisili = ArrayAdapter.createFromResource(context, R.array.JAMBI, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Sumatera Selatan")){
                adapterSpinnerKabupatenDomisili = ArrayAdapter.createFromResource(context, R.array.SUMATERA_SELATAN, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Bengkulu")){
                adapterSpinnerKabupatenDomisili = ArrayAdapter.createFromResource(context, R.array.BENGKULU, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Lampung")){
                adapterSpinnerKabupatenDomisili = ArrayAdapter.createFromResource(context, R.array.LAMPUNG, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Kepulauan Bangka Belitung")){
                adapterSpinnerKabupatenDomisili = ArrayAdapter.createFromResource(context, R.array.KEPULAUAN_BANGKA_BELITUNG, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Kepulauan Riau")){
                adapterSpinnerKabupatenDomisili = ArrayAdapter.createFromResource(context, R.array.KEPULAUAN_RIAU, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("DKI Jakarta")){
                adapterSpinnerKabupatenDomisili = ArrayAdapter.createFromResource(context, R.array.DKI_JAKARTA, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Jawa Barat")){
                adapterSpinnerKabupatenDomisili = ArrayAdapter.createFromResource(context, R.array.JAWA_BARAT, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Jawa Tengah")){
                adapterSpinnerKabupatenDomisili = ArrayAdapter.createFromResource(context, R.array.JAWA_TENGAH, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Jawa Timur")){
                adapterSpinnerKabupatenDomisili = ArrayAdapter.createFromResource(context, R.array.JAWA_TIMUR, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Daerah Istimewa")){
                adapterSpinnerKabupatenDomisili = ArrayAdapter.createFromResource(context, R.array.DAERAH_ISTIMEWA, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Banten")){
                adapterSpinnerKabupatenDomisili = ArrayAdapter.createFromResource(context, R.array.BANTEN, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Bali")){
                adapterSpinnerKabupatenDomisili = ArrayAdapter.createFromResource(context, R.array.BALI, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Nusa Tenggara Barat")){
                adapterSpinnerKabupatenDomisili = ArrayAdapter.createFromResource(context, R.array.NUSA_TENGGARA_BARAT, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Nusa Tenggara Timur")){
                adapterSpinnerKabupatenDomisili = ArrayAdapter.createFromResource(context, R.array.NUSA_TENGGARA_TIMUR, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Kalimantan Barat")){
                adapterSpinnerKabupatenDomisili = ArrayAdapter.createFromResource(context, R.array.KALIMANTAN_BARAT, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Kalimantan Tengah")){
                adapterSpinnerKabupatenDomisili = ArrayAdapter.createFromResource(context, R.array.KALIMANTAN_TENGAH, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Kalimantan Selatan")){
                adapterSpinnerKabupatenDomisili = ArrayAdapter.createFromResource(context, R.array.KALIMANTAN_SELATAN, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Kalimantan Timur")){
                adapterSpinnerKabupatenDomisili = ArrayAdapter.createFromResource(context, R.array.KALIMANTAN_TIMUR, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Kalimantan Utara")){
                adapterSpinnerKabupatenDomisili = ArrayAdapter.createFromResource(context, R.array.KALIMANTAN_UTARA, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Sulawesi Utara")){
                adapterSpinnerKabupatenDomisili = ArrayAdapter.createFromResource(context, R.array.SULAWESI_UTARA, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Sulawesi Tengah")){
                adapterSpinnerKabupatenDomisili = ArrayAdapter.createFromResource(context, R.array.SULAWESI_TENGAH, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Sulawesi Selatan")){
                adapterSpinnerKabupatenDomisili = ArrayAdapter.createFromResource(context, R.array.SULAWESI_SELATAN, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Sulawesi Tenggara")){
                adapterSpinnerKabupatenDomisili = ArrayAdapter.createFromResource(context, R.array.SULAWESI_TENGGARA, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Gorontalo")){
                adapterSpinnerKabupatenDomisili = ArrayAdapter.createFromResource(context, R.array.GORONTALO, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Sulawesi Barat")){
                adapterSpinnerKabupatenDomisili = ArrayAdapter.createFromResource(context, R.array.SULAWESI_BARAT, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Maluku")){
                adapterSpinnerKabupatenDomisili = ArrayAdapter.createFromResource(context, R.array.MALUKU, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Maluku Utara")){
                adapterSpinnerKabupatenDomisili = ArrayAdapter.createFromResource(context, R.array.MALUKU_UTARA, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Papua")){
                adapterSpinnerKabupatenDomisili = ArrayAdapter.createFromResource(context, R.array.PAPUA, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Papua Barat")){
                adapterSpinnerKabupatenDomisili = ArrayAdapter.createFromResource(context, R.array.PAPUA_BARAT, android.R.layout.simple_spinner_item);

            }
            adapterSpinnerKabupatenDomisili.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            kabupatenDomisili.setAdapter(adapterSpinnerKabupatenDomisili);
            adapterSpinnerKabupatenDomisili.notifyDataSetChanged();

            if (userInformation.getString("kabupatenDomisili", "") != null || !userInformation.getString("kabupatenDomisili", "").equals("0")) {
                int spinnerPositionDomisili = adapterSpinnerKabupatenDomisili.getPosition(userInformation.getString("kabupatenDomisili", ""));
                kabupatenDomisili.setSelection(spinnerPositionDomisili);

            }


        }else if(ktp && !domisili){
            if (namaProvinsi.equals("Aceh")) {
                adapterSpinnerKabupatenKtp = ArrayAdapter.createFromResource(context, R.array.ACEH, android.R.layout.simple_spinner_item);
            } else if (namaProvinsi.equals("Sumatera Utara")) {
                adapterSpinnerKabupatenKtp = ArrayAdapter.createFromResource(context, R.array.SUMATERA_UTARA, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Sumatera Barat")) {
                adapterSpinnerKabupatenKtp = ArrayAdapter.createFromResource(context, R.array.SULAWESI_BARAT, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Riau")){
                adapterSpinnerKabupatenKtp = ArrayAdapter.createFromResource(context, R.array.RIAU, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Jambi")){
                adapterSpinnerKabupatenKtp = ArrayAdapter.createFromResource(context, R.array.JAMBI, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Sumatera Selatan")){
                adapterSpinnerKabupatenKtp = ArrayAdapter.createFromResource(context, R.array.SUMATERA_SELATAN, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Bengkulu")){
                adapterSpinnerKabupatenKtp = ArrayAdapter.createFromResource(context, R.array.BENGKULU, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Lampung")){
                adapterSpinnerKabupatenKtp = ArrayAdapter.createFromResource(context, R.array.LAMPUNG, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Kepulauan Bangka Belitung")){
                adapterSpinnerKabupatenKtp = ArrayAdapter.createFromResource(context, R.array.KEPULAUAN_BANGKA_BELITUNG, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Kepulauan Riau")){
                adapterSpinnerKabupatenKtp = ArrayAdapter.createFromResource(context, R.array.KEPULAUAN_RIAU, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("DKI Jakarta")){
                adapterSpinnerKabupatenKtp = ArrayAdapter.createFromResource(context, R.array.DKI_JAKARTA, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Jawa Barat")){
                adapterSpinnerKabupatenKtp = ArrayAdapter.createFromResource(context, R.array.JAWA_BARAT, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Jawa Tengah")){
                adapterSpinnerKabupatenKtp = ArrayAdapter.createFromResource(context, R.array.JAWA_TENGAH, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Jawa Timur")){
                adapterSpinnerKabupatenKtp = ArrayAdapter.createFromResource(context, R.array.JAWA_TIMUR, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Daerah Istimewa")){
                adapterSpinnerKabupatenKtp = ArrayAdapter.createFromResource(context, R.array.DAERAH_ISTIMEWA, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Banten")){
                adapterSpinnerKabupatenKtp = ArrayAdapter.createFromResource(context, R.array.BANTEN, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Bali")){
                adapterSpinnerKabupatenKtp = ArrayAdapter.createFromResource(context, R.array.BALI, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Nusa Tenggara Barat")){
                adapterSpinnerKabupatenKtp = ArrayAdapter.createFromResource(context, R.array.NUSA_TENGGARA_BARAT, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Nusa Tenggara Timur")){
                adapterSpinnerKabupatenKtp = ArrayAdapter.createFromResource(context, R.array.NUSA_TENGGARA_TIMUR, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Kalimantan Barat")){
                adapterSpinnerKabupatenKtp = ArrayAdapter.createFromResource(context, R.array.KALIMANTAN_BARAT, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Kalimantan Tengah")){
                adapterSpinnerKabupatenKtp = ArrayAdapter.createFromResource(context, R.array.KALIMANTAN_TENGAH, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Kalimantan Selatan")){
                adapterSpinnerKabupatenKtp = ArrayAdapter.createFromResource(context, R.array.KALIMANTAN_SELATAN, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Kalimantan Timur")){
                adapterSpinnerKabupatenKtp = ArrayAdapter.createFromResource(context, R.array.KALIMANTAN_TIMUR, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Kalimantan Utara")){
                adapterSpinnerKabupatenKtp = ArrayAdapter.createFromResource(context, R.array.KALIMANTAN_UTARA, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Sulawesi Utara")){
                adapterSpinnerKabupatenKtp = ArrayAdapter.createFromResource(context, R.array.SULAWESI_UTARA, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Sulawesi Tengah")){
                adapterSpinnerKabupatenKtp = ArrayAdapter.createFromResource(context, R.array.SULAWESI_TENGAH, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Sulawesi Selatan")){
                adapterSpinnerKabupatenKtp = ArrayAdapter.createFromResource(context, R.array.SULAWESI_SELATAN, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Sulawesi Tenggara")){
                adapterSpinnerKabupatenKtp = ArrayAdapter.createFromResource(context, R.array.SULAWESI_TENGGARA, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Gorontalo")){
                adapterSpinnerKabupatenKtp = ArrayAdapter.createFromResource(context, R.array.GORONTALO, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Sulawesi Barat")){
                adapterSpinnerKabupatenKtp = ArrayAdapter.createFromResource(context, R.array.SULAWESI_BARAT, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Maluku")){
                adapterSpinnerKabupatenKtp = ArrayAdapter.createFromResource(context, R.array.MALUKU, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Maluku Utara")){
                adapterSpinnerKabupatenKtp = ArrayAdapter.createFromResource(context, R.array.MALUKU_UTARA, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Papua")){
                adapterSpinnerKabupatenKtp = ArrayAdapter.createFromResource(context, R.array.PAPUA, android.R.layout.simple_spinner_item);

            } else if (namaProvinsi.equals("Papua Barat")){
                adapterSpinnerKabupatenKtp = ArrayAdapter.createFromResource(context, R.array.PAPUA_BARAT, android.R.layout.simple_spinner_item);

            }

            adapterSpinnerKabupatenKtp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            kabupatenKtp.setAdapter(adapterSpinnerKabupatenKtp);
            adapterSpinnerKabupatenKtp.notifyDataSetChanged();

            if (userInformation.getString("kabupatenKtp", "") != null || !userInformation.getString("kabupatenDomisili", "").equals("0"))  {
                int spinnerPositionKtp = adapterSpinnerKabupatenKtp.getPosition(userInformation.getString("kabupatenKtp", ""));
                kabupatenKtp.setSelection(spinnerPositionKtp);

            }

        }


    }

    @Override
    public void showLogin() {

    }

    @Override
    public void hideLogin() {

    }

    @Override
    public void updateSucces() {
        onBackPressed();
    }

    @Override
    public void updateFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void pushEditProfile() {
        String userId = userInformation.getString("userId", "");

        String pushUmumEmail = umumEmail.getText().toString();
        String pushUmumNamaDepan = umumnamaDepan.getText().toString();
        String pushUmumNamaBelakang = umumnamaBelakang.getText().toString();
        String pushUmumNoHp = umumNoHp.getText().toString();
        String pushUmumFb = umumFb.getText().toString();
        String pushUmumIg = umumIg.getText().toString();
        String pushUmumTwitter = umumTwitter.getText().toString();
        String pushUmumBank = spinnerBank.getSelectedItem().toString();

        String pushDomisiliAlamat = editAlamatDomisili.getText().toString();
        String pushDomisiliRtRw = editRtRwDomisili.getText().toString();
        String pushDomisiliKelurahan = editKelurahanDomisili.getText().toString();
        String pushDomisiliKecamatan = editKecamatanDomisili.getText().toString();
        String pushDomisiliProvinsi = provinsiDomisili.getSelectedItem().toString();
        String pushDomisiliKabupaten = kabupatenDomisili.getSelectedItem().toString();

        String pushKtpNo = noKtp.getText().toString();
        String pushKtpNama = namaKtp.getText().toString();
        String pushKtpTanggalLahir = tanggalLahirKtp.getText().toString();
        String pushKtpTempatLahir = tempatLahirKtp.getText().toString();
        String pushKtpAlamat = alamatKtp.getText().toString();
        String pushKtpRtRw = rtRwKtp.getText().toString();
        String pushKtpKelurahan = kelurahanKtp.getText().toString();
        String pushKtpKecamatan = kecamatanKtp.getText().toString();
        String pushKtpAgamaDiKtp = spinnerAgamaDiKtp.getSelectedItem().toString();
        String pushKtpGolonganDarah = golonganDarah.getSelectedItem().toString();
        String pushKtpStatus = status.getSelectedItem().toString();
        String pushKtpJenisKelamin = jenisKelamin.getSelectedItem().toString();
        String pushKtpProvinsiKtp = provinsiKtp.getSelectedItem().toString();
        String pushKabupatenKtp = kabupatenKtp.getSelectedItem().toString();


        String[] dataUmum = {userId,pushUmumEmail, pushUmumNamaDepan, pushUmumNamaBelakang, pushUmumBank, pushUmumNoHp,
                pushUmumFb, pushUmumIg, pushUmumTwitter};

        String[] dataDomisili = {userId, pushDomisiliAlamat, pushDomisiliRtRw, pushDomisiliKelurahan,
                pushDomisiliKecamatan, pushDomisiliProvinsi, pushDomisiliKabupaten};

        String[] dataKtp = {userId, pushKtpNo, pushKtpNama, pushKtpTempatLahir, pushKtpTanggalLahir,
                pushKtpAgamaDiKtp, pushKtpGolonganDarah, pushKtpJenisKelamin, pushKtpStatus, pushKtpAlamat, pushKtpRtRw,
                pushKtpKelurahan, pushKtpKecamatan, pushKtpProvinsiKtp, pushKabupatenKtp};

        presenter.pushEditProfile(dataUmum, dataDomisili, dataKtp);

    }

    @Override
    public void pushPhoto(File imageFile) {
        presenter.pushPhoto(imageFile);
    }

    @Override
    public void getPicFromCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            final Bitmap bitmap = (Bitmap) extras.get("data");
            //ByteArrayOutputStream bos = new ByteArrayOutputStream();
            //bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);

            File filesDir = getApplicationContext().getFilesDir();
            File imageFile = new File(filesDir, "image" + ".jpg");

            OutputStream os;
            try {
                os = new FileOutputStream(imageFile);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
                os.flush();
                os.close();
                pushPhoto(imageFile);

            } catch (Exception e) {
                Log.e(getClass().getSimpleName(), "Error writing bitmap", e);
            }
        }


    }

    @Override
    public void recreate() {
        super.recreate();
    }
}
