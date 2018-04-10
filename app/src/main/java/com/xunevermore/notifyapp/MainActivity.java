package com.xunevermore.notifyapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.web_view);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowContentAccess(true);
        webView.addJavascriptInterface(new To(this),"toastObject");
        settings.setDefaultTextEncodingName("utf-8");//支持中文

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        
        webView.loadUrl("file:///android_asset/test/myhtml.html");
    }

    public void alert(View view) {
        webView.loadUrl("javascript:alertMessage()");
    }


    class To{

        private Context mContext;

        public To(Context mContext) {
            this.mContext = mContext;
        }

        @JavascriptInterface
        public void toastMessage(String message){
            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }else {
        super.onBackPressed();
        }
    }
}
