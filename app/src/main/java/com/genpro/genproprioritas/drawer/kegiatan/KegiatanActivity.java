package com.genpro.genproprioritas.drawer.kegiatan;

import android.content.Intent;
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

import com.genpro.genproprioritas.Error_Handler;
import com.genpro.genproprioritas.R;
import com.genpro.genproprioritas.main.MainActivity;

public class KegiatanActivity extends AppCompatActivity implements KegiatanInterface.View {

    WebView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kegiatan);
        configToolbar();
        showWebView();
    }

    @Override
    public void showWebView() {
        view = (WebView) this.findViewById(R.id.webview_kegiatan);
        view.getSettings().setJavaScriptEnabled(true);
        view.setWebViewClient(new KegiatanActivity.MyBrowser1());
        //ini manggil url web dari webview-nya
        view.loadUrl("http://genprodev.lavenderprograms.com/admpage/kegiatan/mobile");

        if (view.equals("Not Found")) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }

        view.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                view.setVisibility(View.GONE);
                Intent intent = new Intent(KegiatanActivity.this, Error_Handler.class);
                startActivity(intent);
            }
        });
    }

    public void goToMainMenu(View view) {
        Intent go = new Intent(KegiatanActivity.this, MainActivity.class);
        startActivity(go);
    }

    private class MyBrowser1 extends WebViewClient {
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
    public void configToolbar() {

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimary));

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
