package com.genpro.genproprioritas.logins.forgotPassword;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.genpro.genproprioritas.Error_Handler;
import com.genpro.genproprioritas.R;
import com.genpro.genproprioritas.drawer.gmbgenpro.GMBActivity;
import com.genpro.genproprioritas.main.MainActivity;

public class ForgotPasswordActivity extends AppCompatActivity implements ForgotPasswordInterface.View {

    private WebView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        //Lock Landscape
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initToolbar();
        showWebView();
    }

    public void goToMainMenu(View view) {
        Intent intent = new Intent(ForgotPasswordActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void showWebView() {

        view = (WebView) this.findViewById(R.id.Forgot);
        view.getSettings().setJavaScriptEnabled(true);
        view.setWebViewClient(new ForgotPasswordActivity.MyBrowser());
        //ini manggil url web dari webview-nya
        view.loadUrl("http://genprodev.lavenderprograms.com/auth/forget_password");

        if (view.equals("Not Found")) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }

        view.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

                view.setVisibility(View.GONE);
                Intent intent = new Intent(ForgotPasswordActivity.this, Error_Handler.class);
                startActivity(intent);
            }
        });

//        view.setWebChromeClient(new WebChromeClient() {
//            private ProgressBar progressBar;
//            public void onProgressChanged(WebView view, int progress) {
//                getWindow().setTitle("Loading...");
//                progressBar.setProgress(progress * 100);
//
//                if(progress == 100)
//                    activity.setTitle(R.string.app_name);
//            }
//        });

//        view.setWebChromeClient(new WebChromeClient() {
//            private ProgressDialog mProgress;
//
//            @Override
//            public void onProgressChanged(WebView view, int progress) {
//                if (mProgress == null) {
//                    mProgress = new ProgressDialog(ForgotPasswordActivity.this);
//                    mProgress.show();
//                }
//                mProgress.setMessage("Loading " + String.valueOf(progress) + "%");
//                if (progress == 100) {
//                    mProgress.dismiss();
//                    mProgress = null;
//                }
//            }
//        });

    }



    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (view.canGoBack()) {
                        view.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void initToolbar() {
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimary));


    }
}
