package org.parthapp.android.news.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.android.news.R;

public class DetailNews extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_news);
        webView= (WebView)findViewById(R.id.webView);
        Bundle bundle= getIntent().getExtras();
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(bundle.getString("URL"));
    }
}
