package com.genpro.genproprioritas.main;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.genpro.genproprioritas.R;
import com.genpro.genproprioritas.login.LoginActivity;
import com.genpro.genproprioritas.profile.ProfileActivity;

public class MainActivity extends AppCompatActivity implements MainContract.View, PopupMenu.OnMenuItemClickListener, NavigationView.OnNavigationItemSelectedListener {

    //SharedPrefrences
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editorUserInformation;
    SharedPreferences.Editor editorLogin;

    //Dialog
    Dialog loading, error;
    Button btnBackError;

    TextView namaUser, emailUser, statusUser, masaAktifUser;
    MainPresenter presenter;

    //Toolbars
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ImageView iconProfile, iconMore;
    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();

        //Shared Preference
        sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        editorUserInformation = getSharedPreferences("userInfo", MODE_PRIVATE).edit();
        editorLogin = getSharedPreferences("login", MODE_PRIVATE).edit();

        //Dialog
        loading = new Dialog(this);
        loading.setContentView(R.layout.loading_layout);
        loading.setCancelable(false);
        loading.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        error  = new Dialog(this);
        error.setContentView(R.layout.error_layout);
        error.setCancelable(false);
        error.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        presenter = new MainPresenter(this);
        presenter.getUserInfo(sharedPreferences.getString("userId", ""));


        //Toolbars
        iconProfile = findViewById(R.id.icon_person_main);
        iconProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProfile();
            }
        });
        namaUser = findViewById(R.id.txt_main_nama);
        emailUser = findViewById(R.id.txt_main_email);
        statusUser = findViewById(R.id.txt_main_status);
        masaAktifUser = findViewById(R.id.txt_main_masa_aktif);

        iconMore = findViewById(R.id.icon_more_main);
        iconMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUpMore(v);
            }
        });

    }


    @Override
    public void showUserInfo() {
        namaUser.setText("Nama : " + sharedPreferences.getString("namaDepan", ""));
        emailUser.setText("Email : " + sharedPreferences.getString("email", ""));
        statusUser.setText("Status : " + sharedPreferences.getString("aktifasi", ""));
        masaAktifUser.setText("Masa aktif : " + sharedPreferences.getString("masaAktif", ""));

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

        showUserInfo();
    }

    @Override
    public void showUserBusinnes() {

    }

    @Override
    public void initToolbar() {
        toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

//        ActionBar actionbar = getSupportActionBar();
//        actionbar.setDisplayHomeAsUpEnabled(true);
//        actionbar.setHomeButtonEnabled(true);
//        actionbar.setDisplayShowTitleEnabled(true);
//        actionbar.setHomeAsUpIndicator(R.drawable.ic_more_vert_white_24dp);
//
//
//        drawerLayout = findViewById(R.id.drawer_main);
//        drawerToggle = setupDrawerToggle();
//        drawerToggle.syncState();

        ActionBar actionbar = getSupportActionBar();

        if (actionbar != null){
            actionbar.setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);

        }

        drawerLayout = findViewById(R.id.drawer_main);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerToggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view_main);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        drawerToggle.syncState();
        super.onPostCreate(savedInstanceState);
    }

    private ActionBarDrawerToggle setupDrawerToggle(){
        return new ActionBarDrawerToggle(
                this,
                drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);

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
    public void someThingFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        showNetworkError();

    }

    @Override
    public void goToProfile() {
        Intent goToProfile = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(goToProfile);

    }

    @Override
    public void logOut() {
        editorLogin.remove("login");
        editorLogin.commit();
        Intent logOut = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(logOut);
        finish();

    }

    @Override
    public void showNetworkError() {
        btnBackError = error.findViewById(R.id.btn_error_kembali);
        btnBackError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                error.dismiss();
                recreate();
            }
        });

        error.show();

    }

    @Override
    public void showPopUpMore(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.menu_main);
        popupMenu.show();

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.log_out :
                logOut();
                return true;
        }

        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    @Override
    public void recreate() {
        super.recreate();
    }
}
