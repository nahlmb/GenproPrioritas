package com.genpro.genproprioritas.drawer.search;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.genpro.genproprioritas.Error_Handler;
import com.genpro.genproprioritas.R;
import com.genpro.genproprioritas.main.MainActivity;

public class SearchActivity extends AppCompatActivity implements SearchContract.View {

    private WebView view;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        showWebview();
    }

    @Override
    public void showWebview() {
        view = (WebView) this.findViewById(R.id.search);
        progressBar = findViewById(R.id.progress_bar);
        view.getSettings().setJavaScriptEnabled(true);
        view.setWebViewClient(new SearchActivity.MyBrowser1());
        //ini manggil url web dari webview-nya
        view.loadUrl("http://genprodev.lavenderprograms.com/bisnis_info/");
        view.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
                setTitle("Loading...");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
                setTitle(view.getTitle());
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

                view.setVisibility(View.GONE);
                Intent intent = new Intent(SearchActivity.this, Error_Handler.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showLoading() {

    }

    public void goToMainMenu(View view) {
        Intent go = new Intent(SearchActivity.this, MainActivity.class);
        startActivity(go);
    }

    public class MyBrowser1 extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
