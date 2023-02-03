package com.barebrains.leciel19;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class RegisterWebView extends AppCompatActivity {
    private WebView webView;
    private String url;
    private Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_webview);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        btn = findViewById(R.id.backbuttona);
        webView = findViewById(R.id.reg_webview);
        url = getIntent().getStringExtra("url");
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(url);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
