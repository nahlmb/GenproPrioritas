package com.genpro.genproprioritas.logins.login;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.genpro.genproprioritas.main.MainActivity;
import com.genpro.genproprioritas.logins.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    Dialog loading;
    LoginPresenter presenter;
    EditText email, pass;
    Button buttonLogin, btnGoToRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Lock Landscape
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        SharedPreferences isLogin = getSharedPreferences("login", MODE_PRIVATE);
        if(isLogin.getBoolean("login", false)){
            goToMain();
        }

        //Edit Text;
        email = findViewById(R.id.edt_email_login);
        pass = findViewById(R.id.edt_password_login);

        //Loading
        Dialog loading = new Dialog(this);
        loading.setContentView(R.layout.loading_layout);
        loading.setCancelable(false);
        loading.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        presenter = new LoginPresenter(this);
        buttonLogin = findViewById(R.id.btn_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strEmail = email.getText().toString();
                String strPass = pass.getText().toString();
                presenter.loginPush(strEmail, strPass);
            }
        });

        //go to register
        btnGoToRegister = findViewById(R.id.btn_go_to_register);
        btnGoToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegister();
            }
        });
    }

    @Override
    public void showLoading() {
        loading = new Dialog(this);
        loading.setContentView(R.layout.loading_layout);
        loading.setCancelable(false);
        loading.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        loading.show();

    }

    @Override
    public void hideLoading() {
        loading.dismiss();
    }

    @Override
    public void loginSucces(String userId) {
        SharedPreferences.Editor editorSpUser= getSharedPreferences("userInfo", MODE_PRIVATE).edit();
        SharedPreferences.Editor editorSpLogin= getSharedPreferences("login", MODE_PRIVATE).edit();
        editorSpUser.putString("userId", userId);
        editorSpLogin.putBoolean("login", true);
        editorSpLogin.commit();
        editorSpUser.commit();
        goToMain();
        finish();

    }

    @Override
    public void loginFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }


    @Override
    public void goToRegister() {
        Intent goToRegister = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(goToRegister);
    }

    @Override
    public void goToMain() {
        Intent goToMain = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(goToMain);
        finish();

    }
}
