package com.genpro.genproprioritas.gmbgenpro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.genpro.genproprioritas.Error_Handler;
import com.genpro.genproprioritas.R;
import com.genpro.genproprioritas.search.SearchActivity;

public class GMBActivity extends AppCompatActivity implements GMBInterface.View {

    private WebView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmb);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimary));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        showWebView();
    }

    @Override
    public void showWebView() {
        view = (WebView) this.findViewById(R.id.gmb);
        view.getSettings().setJavaScriptEnabled(true);
        view.setWebViewClient(new GMBActivity.MyBrowser());
        //ini manggil url web dari webview-nya
        view.loadUrl("https://m.gmbgenpro.com/login");

        if (view.equals("Not Found")) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }

        view.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

                view.setVisibility(View.GONE);
                Intent intent = new Intent(GMBActivity.this, Error_Handler.class);
            }
        });
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
