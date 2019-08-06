package com.genpro.genproprioritas.main;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.genpro.genproprioritas.drawer.askbod.AskBodActivity;
import com.genpro.genproprioritas.genproBisnis.bisnis.BisnisActivity;
import com.genpro.genproprioritas.R;
import com.genpro.genproprioritas.genproBisnis.detailBisnis.DetailBisnisActivity;
import com.genpro.genproprioritas.drawer.gallery.GalleryActivity;
import com.genpro.genproprioritas.drawer.gmbgenpro.GMBActivity;
import com.genpro.genproprioritas.drawer.kegiatan.KegiatanActivity;
import com.genpro.genproprioritas.logins.login.LoginActivity;
import com.genpro.genproprioritas.drawer.membership.MembershipActivity;
import com.genpro.genproprioritas.model.Bisnis;
import com.genpro.genproprioritas.logins.profile.ProfileActivity;
import com.genpro.genproprioritas.drawer.search.SearchActivity;
import com.genpro.genproprioritas.drawer.sejarah.SejarahActivity;
import com.genpro.genproprioritas.drawer.visimisi.VisimisiActivity;
import com.genpro.genproprioritas.model.Membership;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View, PopupMenu.OnMenuItemClickListener, NavigationView.OnNavigationItemSelectedListener {

    static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_CHOOSE_PHOTO = 2;

    // Swipe
    SwipeRefreshLayout swLayout;
    DrawerLayout dLayout;

    //SharedPrefrences
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editorUserInformation;
    SharedPreferences.Editor editorLogin;

    //Dialog
    Dialog loading, error;
    Button btnBackError;

    TextView namaUser, emailUser, statusUser, masaAktifUser, IDUser;
    CardView cardProfileUser;
    MainPresenter presenter;

    //Toolbars
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ImageView iconProfile, iconMore;
    ActionBarDrawerToggle drawerToggle;

    //Bisnis
    ImageView moreListBisnis, mainImg;
    RecyclerView recyclerViewBisnis;
    LinearLayout linearBisnisWarning;
    Button btnSelengkapnya;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();

        mainImg = findViewById(R.id.img_account_profile);
        IDUser = findViewById(R.id.tv_id_genpro);

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
        presenter.getBusinnes(sharedPreferences.getString("userId", ""));


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

        //List Bisnis
        recyclerViewBisnis = findViewById(R.id.rv_list_bisnis_main);
        linearBisnisWarning = findViewById(R.id.linear_peringatan_belum_ada_bisnis);
        btnSelengkapnya = findViewById(R.id.btn_selengkapnya);
        moreListBisnis = findViewById(R.id.more_list_bisnis);
        btnSelengkapnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToBisnis();
            }
        });
        moreListBisnis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopMoreListBisnis(v);
            }
        });


        //swipe refresh
        swLayout = findViewById(R.id.swlayout);
        refreshData();

        //buttomSheet
        cardProfileUser = findViewById(R.id.card_profile_anggota_main);
        cardProfileUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheet();
            }
        });
        showProfileImageToImageView();
        showIDUser();
    }


    @Override
    public void showUserInfo() {
        namaUser.setText("Nama : " + sharedPreferences.getString("namaDepan", ""));
        emailUser.setText("Email : " + sharedPreferences.getString("email", ""));
        statusUser.setText("Status : " + sharedPreferences.getString("aktifasi", ""));
        masaAktifUser.setText("Masa aktif : " + sharedPreferences.getString("masaAktif", ""));

        if (sharedPreferences.getString("namaDepan","").equals("null")) {
            namaUser.setText("Nama : -");
        }

        if (sharedPreferences.getString("aktifasi", "").equals("false")){
            statusUser.setText("Status : Tidak Aktif");
        } else {
            statusUser.setText("Status : Aktif");
        }

        if (sharedPreferences.getString("masaAktif", "").equals("null")){
            masaAktifUser.setText("Masa aktif : Tidak Aktif");
        }

        }

    @Override
    public void showProfileImage(File imageFile) {
        presenter.getProfileImage(sharedPreferences.getString("userId", ""),imageFile);
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

            } catch (Exception e) {
                Log.e(getClass().getSimpleName(), "Error writing bitmap", e);
            }

            showProfileImage(imageFile);

        }else if (requestCode == REQUEST_CHOOSE_PHOTO && resultCode == RESULT_OK){

            try {
                Uri uri = data.getData();
                Bitmap bitmap = (Bitmap) MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);

                //ByteArrayOutputStream bos = new ByteArrayOutputStream();
                //bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);

                File filesDir = getApplicationContext().getFilesDir();
                File imageFile = new File(filesDir, "image" + ".jpg");

                OutputStream os;
                os = new FileOutputStream(imageFile);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
                os.flush();
                os.close();

                /*try {
                    os = new FileOutputStream(imageFile);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
                    os.flush();
                    os.close();

                } catch (Exception e) {
                    Log.e(getClass().getSimpleName(), "Error writing bitmap", e);
                }*/

                showProfileImage(imageFile);
            } catch (IOException e) {
                Log.e(getClass().getSimpleName(), "Error writing bitmap", e);
                e.printStackTrace();
            }

        }
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
    public void showUserBusinnes(List<Bisnis.BisnisData> bisnisData) {

        if(bisnisData != null && bisnisData.size()>0){
            AdapterListBisnis adapterListBisnis = new AdapterListBisnis(this, bisnisData, this);
            recyclerViewBisnis.setAdapter(adapterListBisnis);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            recyclerViewBisnis.setLayoutManager(linearLayoutManager);
            recyclerViewBisnis.setNestedScrollingEnabled(true);
            adapterListBisnis.notifyDataSetChanged();
            recyclerViewBisnis.setVisibility(View.VISIBLE);
            linearBisnisWarning.setVisibility(View.GONE);
        }else {
            recyclerViewBisnis.setAdapter(null);
            linearBisnisWarning.setVisibility(View.VISIBLE);
            recyclerViewBisnis.setVisibility(View.GONE);
        }
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
    public void goToDetailBisnis(Bisnis.BisnisData bisnisData, int i) {
        Intent goToDetailBisnis = new Intent(MainActivity.this, DetailBisnisActivity.class);
        goToDetailBisnis.putExtra("BISNIS_POSITION", i);
        startActivity(goToDetailBisnis);
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
    public void showPopMoreListBisnis(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.inflate(R.menu.menu_bisnis_main);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.selengkapnya_pop :
                        goToBisnis();
                        break;
                }
                return false;
            }
        });
        popupMenu.show();

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
    public void showBottomSheet() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View sheetView = this.getLayoutInflater().inflate(R.layout.buttom_qrcode_layout, null);
        bottomSheetDialog.setContentView(sheetView);

        //set image and barcode
        ImageView profilePicBs = sheetView.findViewById(R.id.img_profile_in_bs);
        ImageView qrCode = sheetView.findViewById(R.id.img_barcode);
        if(sharedPreferences.getString("picture", "") != null){
            Glide.with(this).load(sharedPreferences.getString("picture", "")).into(profilePicBs);
        }

        String qrCodeString = sharedPreferences.getString("noAnggota", "");

        if(qrCodeString != null && qrCodeString.length()>2){
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            try {
                BitMatrix bitMatrix = multiFormatWriter.encode(qrCodeString, BarcodeFormat.QR_CODE, 500, 500);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);

                qrCode.setImageBitmap(bitmap);

            } catch (WriterException e) {
                e.printStackTrace();
                Log.d("barcode", e.getLocalizedMessage());
            }
        }

        bottomSheetDialog.show();

    }

    @Override
    public void goToBisnis() {
        Intent goToBisnis = new Intent(MainActivity.this, BisnisActivity.class);
        startActivity(goToBisnis);

    }

    @Override
    public void showIDUser() {
        IDUser.setText("genpro - " + sharedPreferences.getString("noAnggota",""));
    }

    @Override
    public void showProfileImageToImageView() {
        if(sharedPreferences.getString("userInfo","") != null){
            Glide.with(this).load(sharedPreferences.getString("picture", "")).into(mainImg);
        }
    }

    @Override
    public void uploadPhotoSucces(String photo) {

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.log_out :
                logOut();
                break;
        }

        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_search :
                Intent goToSearch = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(goToSearch);
                break;
            case R.id.nav_business :
                Intent goToBusiness = new Intent(MainActivity.this, BisnisActivity.class);
                startActivity(goToBusiness);
                break;
            case  R.id.nav_profile :
                Intent goToProfile = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(goToProfile);
                break;
            case R.id.nav_gmb_genpro :
                Intent goToGMB = new Intent(MainActivity.this, GMBActivity.class);
                startActivity(goToGMB);
                break;
            case R.id.nav_membership :
                Intent goToMembership = new Intent(MainActivity.this, MembershipActivity.class);
                startActivity(goToMembership);
                break;
            case R.id.nav_kegiatan :
                Intent goToKegiatan = new Intent(MainActivity.this, KegiatanActivity.class);
                startActivity(goToKegiatan);
                break;
            case R.id.nav_gallery :
                Intent goToGallery = new Intent(MainActivity.this, GalleryActivity.class);
                startActivity(goToGallery);
                break;
            case R.id.nav_visi_misi :
                Intent goToVisimisi = new Intent(MainActivity.this, VisimisiActivity.class);
                startActivity(goToVisimisi);
                break;
            case  R.id.nav_sejarah :
                Intent goToSejarah = new Intent(MainActivity.this, SejarahActivity.class);
                startActivity(goToSejarah);
                break;
            case R.id.nav_ask_bod :
                Intent goToAskBod = new Intent(MainActivity.this, AskBodActivity.class);
                startActivity(goToAskBod);
                break;
        }

        return false;
    }


    @Override
    public void recreate() {
        super.recreate();
    }

//    public void search(MenuItem item) {
//        Intent goToSearch = new Intent(MainActivity.this, SearchActivity.class);
//        startActivity(goToSearch);
//    }
//
//    public void business(MenuItem item) {
//        Intent goToBusiness = new Intent(MainActivity.this, BusinessActivity.class);
//        startActivity(goToBusiness);
//    }
//
//    public void profile(MenuItem item) {
//        Intent goToProfile = new Intent(MainActivity.this, ProfileActivity.class);
//        startActivity(goToProfile);
//    }
//
//    public void gmb(MenuItem item) {
//        Intent goToGMB = new Intent(MainActivity.this, GMBActivity.class);
//        startActivity(goToGMB);
//    }
//
//    public void membership(MenuItem item) {
//        Intent goToMembership = new Intent(MainActivity.this, MembershipActivity.class);
//        startActivity(goToMembership);
//    }

}
