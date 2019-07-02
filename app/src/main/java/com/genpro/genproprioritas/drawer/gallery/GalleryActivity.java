package com.genpro.genproprioritas.drawer.gallery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.genpro.genproprioritas.R;
import com.genpro.genproprioritas.main.MainActivity;
import com.genpro.genproprioritas.model.ResponseGallery;

import java.util.List;

public class GalleryActivity extends AppCompatActivity implements GalleryContract.View {
    GalleryPresenter presenter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        recyclerView = findViewById(R.id.rv_gallery);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimary));

        presenter = new GalleryPresenter(this);
        presenter.getGallery();
    }

    public void goToMainMenu(View view) {
        Intent go = new Intent(GalleryActivity.this, MainActivity.class);
        startActivity(go);
    }


    @Override
    public void showGallery(List<ResponseGallery.DataItem> dataItems) {
        GalleryAdapter adapter = new GalleryAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter.setDataItems(dataItems);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(true);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void somethingFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }
}
