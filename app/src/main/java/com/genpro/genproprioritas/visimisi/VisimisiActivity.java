package com.genpro.genproprioritas.visimisi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.genpro.genproprioritas.R;
import com.genpro.genproprioritas.main.MainActivity;
import com.genpro.genproprioritas.profile.ProfileActivity;

public class VisimisiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visimisi);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimary));

    }

    public void goToMainMenu(View view) {
        Intent go = new Intent(VisimisiActivity.this, MainActivity.class);
        startActivity(go);
    }
}
