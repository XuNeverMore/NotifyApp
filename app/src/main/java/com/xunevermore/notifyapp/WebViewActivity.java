package com.xunevermore.notifyapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {

    private static final String TAG = "WebViewActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);


        WebView webView = (WebView) findViewById(R.id.web_view);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);


        Intent intent = getIntent();
        Uri data = intent.getData();
        String host = data.getHost();
        Log.i(TAG, "host: "+host);

        String scheme = data.getScheme();
        Log.i(TAG, "scheme: "+scheme);

        Log.i(TAG, "getPath: "+data.getPath());
        webView.loadUrl("https://www.baidu.com");


    }

    public void finish(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,MainActivity.class));
    }
}
