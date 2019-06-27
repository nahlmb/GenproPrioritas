package com.genpro.genproprioritas.genproBisnis.bisnis;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.genpro.genproprioritas.R;
import com.genpro.genproprioritas.genproBisnis.detailBisnis.DetailBisnisActivity;
import com.genpro.genproprioritas.genproBisnis.insertBisnis.InsertBisnisActivity;
import com.genpro.genproprioritas.main.MainActivity;
import com.genpro.genproprioritas.model.Bisnis;

import java.util.List;

public class BisnisActivity extends AppCompatActivity implements BisnisContract.View {
    BisnisPresenter presenter;
    SharedPreferences sprefUserInfo;
    Dialog loading;

    RecyclerView recyclerViewBisnisActivity;
    SwipeRefreshLayout swLayout;

    //tools
    ImageView add, backEditBisnis;

    //tidak ada bisnis
    CardView cardNoBisnis;
    Button btnTambahBisnis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bisnis);

        //setup
        presenter = new BisnisPresenter(this);
        recyclerViewBisnisActivity = findViewById(R.id.rv_list_bisnis_bisnis);
        sprefUserInfo = getSharedPreferences("userInfo", MODE_PRIVATE);

        //toolbars
        add = findViewById(R.id.icon_add_bisnis);
        backEditBisnis = findViewById(R.id.back_bisnis);
        backEditBisnis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addButtonClick();
            }
        });

        //tidak ada bisnis
        cardNoBisnis = findViewById(R.id.card_bisnis_tidak_ada);
        btnTambahBisnis = findViewById(R.id.btn_tambah_bisnis_dan_usaha);
        btnTambahBisnis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addButtonClick();
            }
        });


        //loading
        loading = new Dialog(this);
        loading.setContentView(R.layout.loading_layout);
        loading.setCancelable(false);
        loading.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //swipe refresh
        swLayout = findViewById(R.id.swr_bisnis);
        refreshData();

        //get user info
        presenter.getUserBisnis(sprefUserInfo.getString("userId", ""));



    }

    @Override
    public void showUserBisnis(List<Bisnis.BisnisData> bisnisData) {
        if(bisnisData != null && bisnisData.size()>0){
            AdapterListBisnis2 adapterListBisnis2 = new AdapterListBisnis2(this, bisnisData, this);
            recyclerViewBisnisActivity.setAdapter(adapterListBisnis2);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            recyclerViewBisnisActivity.setLayoutManager(linearLayoutManager);
            recyclerViewBisnisActivity.setNestedScrollingEnabled(true);

            adapterListBisnis2.notifyDataSetChanged();
            cardNoBisnis.setVisibility(View.GONE);

        }else {
            recyclerViewBisnisActivity.setAdapter(null);
            cardNoBisnis.setVisibility(View.VISIBLE);
            userHaveNoBisnis();
        }

    }

    @Override
    public void userHaveNoBisnis() {

    }

    @Override
    public void addButtonClick() {
        Intent addBisnisIntent = new Intent(BisnisActivity.this, InsertBisnisActivity.class);
        startActivity(addBisnisIntent);
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
    public void someThingFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void goToDetailBisnis(Bisnis.BisnisData bisnisData, int i) {
        Intent goToDetailBisnis = new Intent(BisnisActivity.this, DetailBisnisActivity.class);
        goToDetailBisnis.putExtra("BISNIS_POSITION", i);
        startActivity(goToDetailBisnis);
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
    public void recreate() {
        super.recreate();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent backToMain = new Intent(BisnisActivity.this, MainActivity.class);
        backToMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(backToMain);
        finish();
    }
}
