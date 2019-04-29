package com.example.webviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = (WebView) findViewById(R.id.web_view);
        // getSettings()设置浏览器的属性，这里设置WebView支持JavaScript脚本
        webView.getSettings().setJavaScriptEnabled(true);
        // 当需要存一个网页跳转到另一个网页时，目标网页仍然显示在WebView中，而不是打开系统浏览器
        // webView.setWebViewClient(new WebViewClient());
        // setWebChromeClient可以在WebView中弹窗等复杂功能
        webView.setWebChromeClient(new WebChromeClient());
        // 使滚动条不占位
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl("https://www.baidu.com");
    }
}
