package com.example.geekmvp.mygeek.ui.gank.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.geekmvp.mygeek.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GankWebDetailsActivity extends AppCompatActivity {

    @BindView(R.id.web_view_gank)
    WebView webViewGank;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gank_web_details);
        ButterKnife.bind(this);
        context = this;
        setOut();
    }

    private void setOut() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        //1.找到WebView控件
        //2.加载Url网址
        webViewGank.loadUrl(url);

        //3.设置webview客户端
        webViewGank.setWebViewClient(new WebViewClient());
        webViewGank.setWebChromeClient(new WebChromeClient());
    }
}
