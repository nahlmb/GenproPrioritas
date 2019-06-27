package com.genpro.genproprioritas.drawer.gallery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.genpro.genproprioritas.R;
import com.genpro.genproprioritas.main.MainActivity;

public class GalleryActivity extends AppCompatActivity implements GalleryInterface.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimary));

    }

    @Override
    public void showGallery() {

    }

    public void goToMainMenu(View view) {
        Intent go = new Intent(GalleryActivity.this, MainActivity.class);
        startActivity(go);
    }
}
