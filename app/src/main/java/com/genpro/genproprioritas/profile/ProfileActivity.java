package com.genpro.genproprioritas.profile;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.genpro.genproprioritas.R;
import com.genpro.genproprioritas.editProfile.EditProfileActivity;
import com.genpro.genproprioritas.main.MainActivity;

public class ProfileActivity extends AppCompatActivity implements ProfileContract.View, PopupMenu.OnMenuItemClickListener {

    //tools
    ProfilePresenter presenter;
    SharedPreferences.Editor editorUserInformation;
    SharedPreferences userInformation;
    Dialog loading;
    SwipeRefreshLayout swLayout;


    //profile Utama
    ImageView profilPic;
    TextView namaUser, emailUser;


    //profile umum
    TextView txtProfileUmum1, txtProfileUmum2,
            txtProfileUmum3, txtProfileUmum4, txtProfileUmum5;
    //toolbar
    ImageView backIcon, moreIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        presenter = new ProfilePresenter(this);

        //Shared pref
        editorUserInformation = getSharedPreferences("userInfo", MODE_PRIVATE).edit();
        userInformation = getSharedPreferences("userInfo", MODE_PRIVATE);

        //Dialog
        loading = new Dialog(this);
        loading.setContentView(R.layout.loading_layout);
        loading.setCancelable(false);
        loading.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //back and more
        backIcon = findViewById(R.id.back_profile);
        moreIcon = findViewById(R.id.icon_more_profile);

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        moreIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUpMore(v);
            }
        });

        //profile utama
        profilPic = findViewById(R.id.img_profile_pic);
        namaUser = findViewById(R.id.txt_profile_nama_user);
        emailUser = findViewById(R.id.txt_profile_email_user);

        //profile umum
        txtProfileUmum1 = findViewById(R.id.txt_profile_umum_1);
        txtProfileUmum2 = findViewById(R.id.txt_profile_umum_2);
        txtProfileUmum3 = findViewById(R.id.txt_profile_umum_3);
        txtProfileUmum4 = findViewById(R.id.txt_profile_umum_4);
        txtProfileUmum5 = findViewById(R.id.txt_profile_umum_5);

        //SwipeRefreshLayout
        swLayout = findViewById(R.id.swlayout_profile);
        refreshData();

        presenter.getUserInfo(userInformation.getString("userId", ""));

    }

    @Override
    public void showLoading() {
        loading.show();
    }

    @Override
    public void hideLoading() {
        loading.dismiss();
    }

    @Override
    public void showProfileUtamaUser() {
        SharedPreferences userInformation = getSharedPreferences("userInfo", MODE_PRIVATE);

        namaUser.setText(userInformation.getString("namaDepan", "") + " " + userInformation.getString("namaBelakang", ""));
        emailUser.setText(userInformation.getString("email", ""));

        if(userInformation.getString("userInfo","") != null){
            Glide.with(this).load(userInformation.getString("picture", "")).into(profilPic);
        }
    }

    @Override
    public void showAllUserInfo() {
        showProfileUtamaUser();
        setProfileUmum();
        setProfileDomisili();
        setProfileKtp();
    }

    @Override
    public void setProfileUmum() {
        SharedPreferences userInformation = getSharedPreferences("userInfo", MODE_PRIVATE);


        //set TextView profile umum
        String strEmail, strNamaDepan,
                strNamaBelakang, strAktifasi, strMasaAktif;

        strNamaDepan = userInformation.getString("namaDepan", "");
        strNamaBelakang = userInformation.getString("namaBelakang", "");
        strEmail = userInformation.getString("email", "");
        strAktifasi = userInformation.getString("aktifasi", "");

        String noData = " - ";


        //set TextView
        if(strNamaDepan.equals("")){
            txtProfileUmum1.setText("Nama depan : "+ noData);
        }else {
            txtProfileUmum1.setText("Nama depan : "+ strNamaDepan);
        }

        if(strNamaBelakang.equals("")){
            txtProfileUmum2.setText("Nama belakang" + noData);
        }else {
            txtProfileUmum2.setText("Nama belakang" +strNamaDepan);
        }

        txtProfileUmum3.setText("Email : " + strEmail);

        strMasaAktif = userInformation.getString("masaAktif", "");
        if(strAktifasi.equals("false")){
            strAktifasi = "Status : " + "tidak aktif";
            txtProfileUmum4.setText("Status : " + strAktifasi);
            txtProfileUmum5.setText("Masa Aktif : " + "tidak aktif");
        }else if (strAktifasi.equals("true")){
            strAktifasi = "aktif";
            txtProfileUmum4.setText("Status : " + strAktifasi);
            txtProfileUmum5.setText("Masa Aktif : " + strMasaAktif);
        }

    }

    @Override
    public void setProfileDomisili() {

    }

    @Override
    public void setProfileKtp() {

    }

    @Override
    public void showPopUpMore(android.view.View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.menu_profile);
        popupMenu.show();


    }

    @Override
    public void goToEditProfile() {
        Intent goToEditProfile = new Intent(ProfileActivity.this, EditProfileActivity.class);
        startActivity(goToEditProfile);

    }

    @Override
    public void saveUserInfo(String[] dataUmum, String[] dataDomisili, String[] dataKtp) {
        //save data umum
        editorUserInformation.putString("noAnggota", dataUmum[0]);
        editorUserInformation.putString("email", dataUmum[1]);
        editorUserInformation.putString("userName", dataUmum[2]);
        editorUserInformation.putString("namaDepan", dataUmum[3]);
        editorUserInformation.putString("namaBelakang", dataUmum[4]);
        editorUserInformation.putString("phone", dataUmum[5]);
        editorUserInformation.putString("bank", dataUmum[6]);
        editorUserInformation.putString("facebook", dataUmum[7]);
        editorUserInformation.putString("twitter", dataUmum[8]);
        editorUserInformation.putString("instagram", dataUmum[9]);
        editorUserInformation.putString("aktifasi", dataUmum[10]);
        editorUserInformation.putString("masaAktif", dataUmum[11]);
        editorUserInformation.putString("picture", dataUmum[12]);

        //save data domisili
        editorUserInformation.putString("alamatDomisili", dataDomisili[0]);
        editorUserInformation.putString("rtRwDomisili", dataDomisili[1]);
        editorUserInformation.putString("kelurahanDomisili", dataDomisili[2]);
        editorUserInformation.putString("kecamatanDomisili", dataDomisili[3]);
        editorUserInformation.putString("provinsiDomisili", dataDomisili[4]);
        editorUserInformation.putString("kabupatenDomisili", dataDomisili[5]);

        //save data ktp
        editorUserInformation.putString("noKtp", dataKtp[0]);
        editorUserInformation.putString("namaKtp", dataKtp[1]);
        editorUserInformation.putString("tanggalLahir", dataKtp[2]);
        editorUserInformation.putString("tempatLahir", dataKtp[3]);
        editorUserInformation.putString("agama", dataKtp[4]);
        editorUserInformation.putString("golonganDarah", dataKtp[5]);
        editorUserInformation.putString("jenisKelamin", dataKtp[6]);
        editorUserInformation.putString("status", dataKtp[7]);
        editorUserInformation.putString("alamat", dataKtp[8]);
        editorUserInformation.putString("rtRwKtp", dataKtp[9]);
        editorUserInformation.putString("kelurahanKtp", dataKtp[10]);
        editorUserInformation.putString("kecamatanKtp", dataKtp[11]);
        editorUserInformation.putString("provinsiKtp", dataKtp[12]);
        editorUserInformation.putString("kabupatenKtp", dataKtp[13]);

        editorUserInformation.commit();
        showAllUserInfo();

    }

    @Override
    public void someThingFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void refreshData() {
        swLayout.setColorSchemeResources(R.color.colorAccent,R.color.colorPrimary);
        swLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recreate();
                        swLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.change_profile_pic :
                return true;

            case R.id.change_profile :
                goToEditProfile();
                return true;

        }
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent goToMain = new Intent(ProfileActivity.this, MainActivity.class);
        goToMain.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        goToMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(goToMain);
        finish();
    }

    @Override
    public void recreate() {
        super.recreate();
    }
}
