package com.genpro.genproprioritas.logins.register;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.genpro.genproprioritas.R;
import com.genpro.genproprioritas.logins.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View {
    RegisterPresenter presenter;
    Dialog loading;
    EditText edtUserName, edtEmail, edtPass;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //Lock Landscape
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        presenter = new RegisterPresenter(this);

        //Loading
        Dialog loading = new Dialog(this);
        loading.setContentView(R.layout.loading_layout);
        loading.setCancelable(false);
        loading.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        edtUserName = findViewById(R.id.edt_register_user_name);
        edtEmail = findViewById(R.id.edt_register_email);
        edtPass = findViewById(R.id.edt_register_password);

        btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUserName = edtUserName.getText().toString();
                String strEmail = edtEmail.getText().toString();
                String strPass = edtPass.getText().toString();
                presenter.pushRegister(strUserName, strEmail, strPass);
            }
        });
    }

    @Override
    public void registerSucces() {
        Toast.makeText(this, "Registrasi berhasil", Toast.LENGTH_SHORT).show();
        Intent registerSucces = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(registerSucces);
        finish();
    }

    @Override
    public void registerFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showLogin() {
        loading = new Dialog(this);
        loading.setContentView(R.layout.loading_layout);
        loading.setCancelable(false);
        loading.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        loading.show();

    }

    @Override
    public void hideLogin() {
        loading.dismiss();

    }
}
