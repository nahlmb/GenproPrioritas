package com.genpro.genproprioritas.logins.profile;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.genpro.genproprioritas.R;
import com.genpro.genproprioritas.logins.editProfile.EditProfileActivity;
import com.genpro.genproprioritas.main.MainActivity;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class ProfileActivity extends AppCompatActivity implements ProfileContract.View, PopupMenu.OnMenuItemClickListener, EasyPermissions.PermissionCallbacks {

    //tools
    ProfilePresenter presenter;
    SharedPreferences.Editor editorUserInformation;

    SharedPreferences userInformation;
    SharedPreferences newUserInformation;
    Dialog loading;
    SwipeRefreshLayout swLayout;

    //profile Utama
    ImageView profilPic;
    TextView namaUser, emailUser;


    //profile umum
    TextView txtProfileUmum1, txtProfileUmum2,
            txtProfileUmum3, txtProfileUmum4, txtProfileUmum5;

    //profile domisili
    TextView txtProfileDomisili1, txtProfileDomisili2,
            txtProfileDomisili3, txtProfileDomisili4, txtProfileDomisili5, txtProfileDomisili6;

    //profile ktp
    TextView txtProfileKtp1, txtProfileKtp2,
            txtProfileKtp3, txtProfileKtp4, txtProfileKtp5, txtProfileKtp6, txtProfileKtp7, txtProfileKtp8,
            txtProfileKtp9, txtProfileKtp10, txtProfileKtp11, txtProfileKtp12, txtProfileKtp13, txtProfileKtp14;
    //toolbar
    ImageView backIcon, moreIcon;

    //take photo
    private static final int RC_CAMERA = 1;
    static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_CHOOSE_PHOTO = 2;
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 100;
    public static final String ALLOW_KEY = "ALLOWED";
    public static final String CAMERA_PREF = "camera_pref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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

        //profile domisili
        txtProfileDomisili1 = findViewById(R.id.txt_profile_domisili_1);
        txtProfileDomisili2 = findViewById(R.id.txt_profile_domisili_2);
        txtProfileDomisili3 = findViewById(R.id.txt_profile_domisili_3);
        txtProfileDomisili4 = findViewById(R.id.txt_profile_domisili_4);
        txtProfileDomisili5 = findViewById(R.id.txt_profile_domisili_5);
        txtProfileDomisili6 = findViewById(R.id.txt_profile_domisili_6);

        //profile ktp
        txtProfileKtp1 = findViewById(R.id.txt_profile_ktp_1);
        txtProfileKtp2 = findViewById(R.id.txt_profile_ktp_2);
        txtProfileKtp3 = findViewById(R.id.txt_profile_ktp_3);
        txtProfileKtp4 = findViewById(R.id.txt_profile_ktp_4);
        txtProfileKtp5 = findViewById(R.id.txt_profile_ktp_5);
        txtProfileKtp6 = findViewById(R.id.txt_profile_ktp_6);
        txtProfileKtp7 = findViewById(R.id.txt_profile_ktp_7);
        txtProfileKtp8 = findViewById(R.id.txt_profile_ktp_8);
        txtProfileKtp9 = findViewById(R.id.txt_profile_ktp_9);
        txtProfileKtp10 = findViewById(R.id.txt_profile_ktp_10);
        txtProfileKtp11 = findViewById(R.id.txt_profile_ktp_11);
        txtProfileKtp12 = findViewById(R.id.txt_profile_ktp_12);
        txtProfileKtp13 = findViewById(R.id.txt_profile_ktp_13);
        txtProfileKtp14 = findViewById(R.id.txt_profile_ktp_14);

        presenter = new ProfilePresenter(this);
        presenter.getUserInfo(userInformation.getString("userId", ""));

        newUserInformation = getSharedPreferences("userInfo", MODE_PRIVATE);

        //SwipeRefreshLayout
        swLayout = findViewById(R.id.swlayout_profile);
        refreshData();

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

        Log.d("PROFILE", "Yes is on the on create of Profile Activity");
        showAllUserInfo();

    }

    @Override
    public void showLoading() {
        loading.show();

    }

    @Override
    public void hideLoading() {
        loading.hide();

    }

    @Override
    public void showProfileUtamaUser() {
        namaUser.setText(newUserInformation.getString("namaDepan", "") + " " + userInformation.getString("namaBelakang", ""));
        if(userInformation.getString("namaDepan", "").equals("null")){ namaUser.setText("Belum ada nama"); }
        emailUser.setText(userInformation.getString("email", ""));

        if(userInformation.getString("userInfo","") != null){
            Glide.with(this).load(userInformation.getString("picture", "")).into(profilPic);
        }
    }

    @Override
    public void showAllUserInfo() {
        setProfileUmum();
        showProfileUtamaUser();
        setProfileDomisili();
        setProfileKtp();
        Log.d("PROFILE", "SHOW THE DATA");
    }

    @Override
    public void setProfileUmum() {

        //set TextView profile umum
        String strEmail, strNamaDepan,
                strNamaBelakang, strAktifasi, strMasaAktif;

        strNamaDepan = newUserInformation.getString("namaDepan", "");
        strNamaBelakang = newUserInformation.getString("namaBelakang", "");
        strEmail = newUserInformation.getString("email", "");
        strAktifasi = newUserInformation.getString("aktifasi", "");

        String noData = " - ";


        //set TextView
        if(strNamaDepan.length()<=1){
            txtProfileUmum1.setText("Nama depan : "+ noData);
        }else {
            txtProfileUmum1.setText("Nama depan : "+ strNamaDepan);
        }

        if(strNamaBelakang.equals("")){
            txtProfileUmum2.setText("Nama belakang : " + noData);
        }else {
            txtProfileUmum2.setText("Nama belakang : " +strNamaBelakang);
        }

        txtProfileUmum3.setText("Email : " + strEmail);

        strMasaAktif = newUserInformation.getString("masaAktif", "");
        if(strAktifasi.equals("false")){
            strAktifasi = "tidak aktif";
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
        //set TextView profile domisisli
        String strAlamat, strRtRw, strKelurahan, strKecamatan, strProvinsi, strKabupaten;

        strAlamat = newUserInformation.getString("alamatDomisili", "");
        strRtRw = newUserInformation.getString("rtRwDomisili", "");
        strKelurahan = newUserInformation.getString("kelurahanDomisili", "");
        strKecamatan = newUserInformation.getString("kecamatanDomisili", "");
        strProvinsi = newUserInformation.getString("provinsiDomisili", "");
        strKabupaten = newUserInformation.getString("kabupatenDomisili", "");

        String noData = " - ";

        //set TextView
        if (strAlamat.equals("")) {
            txtProfileDomisili1.setText("Alamat : " + noData);
        } else {
            txtProfileDomisili1.setText("Alamat : " + strAlamat);
        }

        if (strRtRw.equals("")) {
            txtProfileDomisili2.setText("RT/RW : " + noData);
        } else {
            txtProfileDomisili2.setText("RT/RW : " + strRtRw);
        }

        if (strKelurahan.equals("")) {
            txtProfileDomisili3.setText("Kelurahan : " + noData);
        } else {
            txtProfileDomisili3.setText("Kelurahan : " + strKelurahan);
        }

        if (strKecamatan.equals("")) {
            txtProfileDomisili4.setText("Kecamatan : " + noData);
        } else {
            txtProfileDomisili4.setText("Kecamatan : " + strKecamatan);
        }

        if (strProvinsi.equals("")) {
            txtProfileDomisili5.setText("Provinsi : " + noData);
        } else {
            txtProfileDomisili5.setText("Provinsi : " + strProvinsi);
        }

        if (strKabupaten.equals("")) {
            txtProfileDomisili6.setText("Kabupaten : " + noData);
        } else {
            txtProfileDomisili6.setText("Kabupaten : " + strKabupaten);
        }
    }

    @Override
    public void setProfileKtp() {
        //set TextView profile ktp
        String strNoKtp, strNama, strTempatLahir, strTanggalLahir, strAgama, strGolonganDarah, strStatus,
                strJenisKelamin, strAlamat, strRtRw, strKelurahan, strKecamatan, strProvinsi, strKabupaten;

        strNoKtp = newUserInformation.getString("noKtp", "");
        strNama = newUserInformation.getString("namaKtp", "");
        strTempatLahir = newUserInformation.getString("tempatLahir", "");
        strTanggalLahir = newUserInformation.getString("tanggalLahir", "");
        strAgama = newUserInformation.getString("agama", "");
        strGolonganDarah = newUserInformation.getString("golonganDarah", "");
        strStatus = newUserInformation.getString("status", "");
        strJenisKelamin = newUserInformation.getString("jenisKelamin", "");
        strAlamat = newUserInformation.getString("alamat", "");
        strRtRw = newUserInformation.getString("rtRwKtp", "");
        strKelurahan = newUserInformation.getString("kelurahanKtp", "");
        strKecamatan = newUserInformation.getString("kecamatanKtp", "");
        strProvinsi = newUserInformation.getString("provinsiKtp", "");
        strKabupaten = newUserInformation.getString("kabupatenKtp", "");

        String noData = " - ";

        //set TextView
        if (strNoKtp.equals("")) {
            txtProfileKtp1.setText("No KTP : " + noData);
        } else {
            txtProfileKtp1.setText("No KTP : " + strNoKtp);
        }

        if (strNama.equals("")) {
            txtProfileKtp2.setText("Nama di KTP : " + noData);
        } else {
            txtProfileKtp2.setText("Nama di KTP : " + strNama);
        }

        if (strTempatLahir.equals("")) {
            txtProfileKtp3.setText("Tempat Lahir : " + noData);
        } else {
            txtProfileKtp3.setText("Tempat Lahir : " + strTempatLahir);
        }

        if (strTanggalLahir.equals("")) {
            txtProfileKtp4.setText("Tanggal Lahir : " + noData);
        } else {
            txtProfileKtp4.setText("Tanggal Lahir : " + strTanggalLahir);
        }

        if (strAgama.equals("")) {
            txtProfileKtp5.setText("Agama : " + noData);
        } else {
            txtProfileKtp5.setText("Agama : " + strAgama);
        }

        if (strGolonganDarah.equals("")) {
            txtProfileKtp6.setText("Golongan Darah : " + noData);
        } else {
            txtProfileKtp6.setText("Golongan Darah : " + strGolonganDarah);
        }

        if (strStatus.equals("")) {
            txtProfileKtp7.setText("Status : " + noData);
        } else {
            txtProfileKtp7.setText("Status : " + strStatus);
        }

        if (strJenisKelamin.equals("")) {
            txtProfileKtp8.setText("Jenis Kelamin : " + noData);
        } else {
            txtProfileKtp8.setText("Jenis Kelamin : " + strJenisKelamin);
        }

        if (strAlamat.equals("")) {
            txtProfileKtp9.setText("Alamat : " + noData);
        } else {
            txtProfileKtp9.setText("Alamat : " + strAlamat);
        }

        if (strRtRw.equals("")) {
            txtProfileKtp10.setText("RT/RW di KTP : " + noData);
        } else {
            txtProfileKtp10.setText("RT/RW di KTP : " + strRtRw);
        }

        if (strKelurahan.equals("")) {
            txtProfileKtp11.setText("Kelurahan di KTP : " + noData);
        } else {
            txtProfileKtp11.setText("Kelurahan di KTP : " + strKelurahan);
        }

        if (strKecamatan.equals("")) {
            txtProfileKtp12.setText("Kecamatan di KTP : " + noData);
        } else {
            txtProfileKtp12.setText("Kecamatan di KTP : " + strKecamatan);
        }

        if (strProvinsi.equals("")) {
            txtProfileKtp13.setText("Provinsi di KTP : " + noData);
        } else {
            txtProfileKtp13.setText("Provinsi di KTP : " + strProvinsi);
        }

        if (strKabupaten.equals("")) {
            txtProfileKtp14.setText("Kabupaten di KTP : " + noData);
        } else {
            txtProfileKtp14.setText("Kabupaten di KTP : " + strKabupaten);
        }
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
        Log.d("PROFILE", "THE DATA IS SAVED!");
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
    public void getPicFromCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
        }
    }

    @Override
    public void getPicFromGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_CHOOSE_PHOTO);

    }

    @Override
    public void showDialogTakeImage() {
        AlertDialog.Builder choosePhoto = new AlertDialog.Builder(this);
        choosePhoto.setTitle("Pilih Gambar");
        choosePhoto.setNegativeButton("Kamera", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getPicFromCamera();
            }
        });
        choosePhoto.setPositiveButton("Galeri", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getPicFromGallery();
            }
        });
        choosePhoto.create();
        choosePhoto.show();
    }

    @Override
    public void pushPhoto(File imageFile) {
        presenter.pushPhoto(userInformation.getString("userId", ""),imageFile);

    }

    @Override
    public void uploadPhotoSucces(String photo) {
        editorUserInformation
                .putString("picture", "http://genprodev.lavenderprograms.com/img/mobile_apps/" + photo)
                .commit();
        recreate();
    }

    @Override
    public void cropImageAutoSelection() {
        CropImage.activity()
                .setAspectRatio(1,1)
                .start(this);

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.change_profile_pic :
                //camera premission
                checkCameraPermission();
                cropImageAutoSelection(); /** the new one! **/

                /** showDialogTakeImage()  this method run old way to pick image**/
                break;

            case R.id.change_profile :
                goToEditProfile();
                break;

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



            pushPhoto(imageFile);

        }else if (requestCode == REQUEST_CHOOSE_PHOTO && resultCode == RESULT_OK){

            Uri uri = data.getData();

            //try crop
            CropImage.activity(uri).setAspectRatio(1,1).start(this);

        }

        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if(resultCode == RESULT_OK ){
                Uri imageUri = result.getUri();
                try {


                Bitmap bitmap = (Bitmap) MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);

                File filesDir = getApplicationContext().getFilesDir();
                File imageFile = new File(filesDir, "image" + ".jpg");

                OutputStream os;
                os = new FileOutputStream(imageFile);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
                os.flush();
                os.close();

                pushPhoto(imageFile);

            } catch (IOException e) {
                Log.e(getClass().getSimpleName(), "Error writing bitmap", e);
                e.printStackTrace();
            }

            }
        }

    }

    @AfterPermissionGranted(RC_CAMERA)
    private void checkCameraPermission() {
        String perm = Manifest.permission.CAMERA;
        if (EasyPermissions.hasPermissions(this, perm)) {
        } else {
            EasyPermissions.requestPermissions(this, "Butuh permission camera", RC_CAMERA, perm);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        for (String permission : perms) {
            if (permission.equals(Manifest.permission.CAMERA)) {
            }
        }

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {


    }
    public void goToMainMenu(View view) {
        Intent go = new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(go);
    }

}
