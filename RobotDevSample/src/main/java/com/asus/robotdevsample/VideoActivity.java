package com.asus.robotdevsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale);

        WebView webview = new WebView(this);
        webview.getSettings().setJavaScriptEnabled(true);
        setContentView(webview);
        webview.loadUrl("https://www.youtube.com/watch?v=E7M2r6rU4oA");
    }
}
