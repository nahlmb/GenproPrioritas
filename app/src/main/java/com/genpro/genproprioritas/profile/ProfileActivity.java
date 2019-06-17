package com.genpro.genproprioritas.profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.genpro.genproprioritas.R;
import com.genpro.genproprioritas.editProfile.EditProfileActivity;

public class ProfileActivity extends AppCompatActivity implements ProfileContract.View, PopupMenu.OnMenuItemClickListener {
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
        showProfileUtamaUser();

        //profile umum
        txtProfileUmum1 = findViewById(R.id.txt_profile_umum_1);
        txtProfileUmum2 = findViewById(R.id.txt_profile_umum_2);
        txtProfileUmum3 = findViewById(R.id.txt_profile_umum_3);
        txtProfileUmum4 = findViewById(R.id.txt_profile_umum_4);
        txtProfileUmum5 = findViewById(R.id.txt_profile_umum_5);

        showAllUserInfo();

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showProfileUtamaUser() {
        SharedPreferences userInformation = getSharedPreferences("userInfo", MODE_PRIVATE);

        namaUser.setText(userInformation.getString("namaDepan", "") + userInformation.getString("namaBelakang", ""));
        emailUser.setText(userInformation.getString("email", ""));

        if(userInformation.getString("userInfo","") != null){
            Glide.with(this).load(userInformation.getString("picture", "")).into(profilPic);
        }
    }

    @Override
    public void showAllUserInfo() {
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
}
