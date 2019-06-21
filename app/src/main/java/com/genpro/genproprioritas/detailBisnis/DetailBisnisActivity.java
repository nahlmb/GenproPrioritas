package com.genpro.genproprioritas.detailBisnis;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.genpro.genproprioritas.R;
import com.genpro.genproprioritas.editBisnis.EditBisnisActivity;
import com.genpro.genproprioritas.main.MainActivity;
import com.genpro.genproprioritas.model.Bisnis;

public class DetailBisnisActivity extends AppCompatActivity {

    TextView txtBisnis1, txtBisnis2, txtBisnis3, txtBisnis4, txtBisnis5, txtBisnis6, txtBisnis7,
            txtBisnis8, txtBisnis9, txtBisnis10, txtBisnis11;

    Button btnBisnis, btnEditBisnis;

    //toolbar
    ImageView backIcon, moreIcon;

    SwipeRefreshLayout swLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_bisnis);

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
        moreIcon = findViewById(R.id.icon_more_profile);

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        swLayout = findViewById(R.id.swlayout_profile);
        refreshData();

        Intent intent = getIntent();
        final Bisnis.BisnisData  bisnisData = (Bisnis.BisnisData) intent.getSerializableExtra("BISNIS_DATA");
        showUserBisnisDetail(bisnisData);


        btnEditBisnis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindahKuy = new Intent(DetailBisnisActivity.this, EditBisnisActivity.class);
                pindahKuy.putExtra("BISNIS_DATA_EDIT", bisnisData);
                startActivity(pindahKuy);
                finish();
            }
        });
    }

    private void showUserBisnisDetail(Bisnis.BisnisData bisnisData) {
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
        Intent goToMain = new Intent(DetailBisnisActivity.this, MainActivity.class);
        goToMain.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        goToMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(goToMain);
        finish();
    }
}
