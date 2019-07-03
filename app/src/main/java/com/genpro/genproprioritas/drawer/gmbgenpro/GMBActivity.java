package com.genpro.genproprioritas.drawer.gmbgenpro;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.genpro.genproprioritas.Error_Handler;
import com.genpro.genproprioritas.R;
import com.genpro.genproprioritas.main.MainActivity;

public class GMBActivity extends AppCompatActivity implements GMBInterface.View {

    private WebView view;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmb);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimary));

        showWebView();
    }

    @Override
    public void showWebView() {
        view = (WebView) this.findViewById(R.id.gmb);
        progressBar = findViewById(R.id.progress_bar);
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
                Intent intent = new Intent(GMBActivity.this, Error_Handler.class);
                startActivity(intent);
            }
        });
    }

    public void goToMainMenu(View view) {
        Intent go = new Intent(GMBActivity.this, MainActivity.class);
        startActivity(go);
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
    public boolean onCreateOptionsMenu(Menu menu) { //this method is used for adding menu items to the Activity
// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_webview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //this method is used for handling menu items' events
// Handle item selection
        switch (item.getItemId()) {

            case R.id.goBack:
                if(view.canGoBack()) {
                    view.goBack();
                }
                return true;

            case R.id.goForward:
                if(view.canGoForward()) {
                    view.goForward();
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
