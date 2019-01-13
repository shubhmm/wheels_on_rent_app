package com.shubhammm.wheelsonrent.Splash_and_Home_Screen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.shubhammm.wheelsonrent.R;

/**
 * Created by Shubhamm Arora on 27-09-2018.
 */

public class terms extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webt);
        WebView webView=(WebView)findViewById(R.id.webt);
        WebSettings webSetting=webView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webView.loadUrl("https://wheelsonrentallahabad.wordpress.com/terms-and-conditions/");
    }
}
