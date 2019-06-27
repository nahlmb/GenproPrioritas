package com.genpro.genproprioritas.genproBisnis.detailBisnis;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.genpro.genproprioritas.R;
import com.genpro.genproprioritas.genproBisnis.bisnis.BisnisActivity;
import com.genpro.genproprioritas.genproBisnis.editBisnis.EditBisnisActivity;
import com.genpro.genproprioritas.model.Bisnis;

public class DetailBisnisActivity extends AppCompatActivity implements DetailBisnisContract.View{

    //tools
    DetailBisnisPresenter presenter;
    SharedPreferences sharedPreferences;


    TextView txtBisnis1, txtBisnis2, txtBisnis3, txtBisnis4, txtBisnis5, txtBisnis6, txtBisnis7,
    txtBisnis8, txtBisnis9, txtBisnis10, txtBisnis11;

    Button btnBisnis, btnEditBisnis;

    //toolbar
    ImageView backIcon;
    ImageButton more;
    SwipeRefreshLayout swLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_bisnis);

        presenter = new DetailBisnisPresenter(this);

        //insialisasi
        txtBisnis1 = findViewById(R.id.txt_bisnis_1);
        txtBisnis2 = findViewById(R.id.txt_bisnis_2);
        txtBisnis3 = findViewById(R.id.txt_bisnis_3);
        txtBisnis4 = findViewById(R.id.txt_bisnis_4);
        txtBisnis5 = findViewById(R.id.txt_bisnis_5);
        txtBisnis6 = findViewById(R.id.txt_bisnis_6);
        txtBisnis7 = findViewById(R.id.txt_bisnis_7);
        txtBisnis8 = findViewById(R.id.txt_bisnis_8);
        txtBisnis9 = findViewById(R.id.txt_bisnis_9);
        txtBisnis10 = findViewById(R.id.txt_bisnis_10);
        txtBisnis11 = findViewById(R.id.txt_bisnis_11);
        btnBisnis = findViewById(R.id.btn_bisnis_detail);

        btnEditBisnis = findViewById(R.id.btn_bisnis_detail);


        //back and more
        backIcon = findViewById(R.id.back_profile);
        more = findViewById(R.id.icon_more_detail_bisnis);
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        //swipe refresh
        swLayout = findViewById(R.id.swlayout_profile);
        refreshData();

        //setup
        int i = getIntent().getIntExtra("BISNIS_POSITION", 0);
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        presenter.getBusinnes(sharedPreferences.getString("userId", ""),i);


    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void somethingFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showUserBisnisDetail(final Bisnis.BisnisData bisnisData) {
        sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);

        txtBisnis1.setText(bisnisData.getNmUsaha());
        txtBisnis2.setText("Nama Lain : " + bisnisData.getNmUsahaLain());
        txtBisnis3.setText("Jenis Bisnis : " + bisnisData.getNmBisnisLain());
        txtBisnis4.setText("Tentang Bisnis : " + bisnisData.getTentangUsaha());
        txtBisnis5.setText("Jumlah Karyawan : " + bisnisData.getJmlKaryawan());
        txtBisnis6.setText("Jumlah Cabang : " + bisnisData.getJmlCabang());
        txtBisnis7.setText("Omset Tahunan : " + bisnisData.getOmsetTahunan());
        txtBisnis8.setText("Merk : " + bisnisData.getMerk());
        txtBisnis9.setText("No Telepon Perusahaan : " + bisnisData.getNoTlp());
        txtBisnis10.setText("Facebook : " + bisnisData.getFacebook());
        txtBisnis11.setText("Instagram : " + bisnisData.getInstagram());


        btnBisnis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToEdit(bisnisData);
            }
        });

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUpMenu(v, sharedPreferences.getString("userId", ""), bisnisData.getIdBisnisInfo());

            }
        });


    }

    @Override
    public void goToEdit(Bisnis.BisnisData bisnisData) {
        Intent goToEdit = new Intent(DetailBisnisActivity.this, EditBisnisActivity.class);
        goToEdit.putExtra("BISNIS_DATA_EDIT", bisnisData);
        startActivity(goToEdit);
    }

    @Override
    public void showPopUpMenu(View view, final String userId, final String idBisnisInfo) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.inflate(R.menu.menu_bisnis);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.delete_bisnis_pop :
                        delateBisnis(userId, idBisnisInfo);
                        break;
                    case  R.id.edit_bisnis_pop :
                        btnBisnis.callOnClick();
                        break;
                }
                return false;
            }
        });
        popupMenu.show();

    }

    @Override
    public void delateBisnis(final String userId, final String idBisnisInfo) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hapus bisnis anda?")
                .setMessage("bisnis akan dihapus dan tidak ditampilkan kembali")
                .setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.delateBisnis(userId, idBisnisInfo);
                    }
                })
                .setNegativeButton("Batalkan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                 })

                .create()
                .show();

    }

    @Override
    public void succesDelate() {
        Intent goToBisnis = new Intent(DetailBisnisActivity.this, BisnisActivity.class);
        startActivity(goToBisnis);
        finish();
    }


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
    public void onBackPressed() {
//        Intent goToMain = new Intent(DetailBisnisActivity.this, MainActivity.class);
//        goToMain.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//        goToMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(goToMain);
        finish();
    }
}
