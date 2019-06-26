package com.genpro.genproprioritas.bisnis;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.genpro.genproprioritas.R;
import com.genpro.genproprioritas.insertBisnis.InsertBisnisActivity;
import com.genpro.genproprioritas.insertBisnis.InsertBisnisContract;
import com.genpro.genproprioritas.main.AdapterListBisnis;
import com.genpro.genproprioritas.model.Bisnis;

import java.util.List;

public class BisnisActivity extends AppCompatActivity implements BisnisContract.View {
    BisnisPresenter presenter;
    RecyclerView recyclerViewBisnisActivity;
    SharedPreferences sprefUserInfo;
    ImageView add, backEditBisnis;

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
        }else {
            recyclerViewBisnisActivity.setAdapter(null);
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

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void someThingFailed(String msg) {

    }

    @Override
    public void recreate() {
        super.recreate();
    }
}
