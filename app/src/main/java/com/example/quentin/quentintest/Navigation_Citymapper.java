package com.example.quentin.quentintest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Navigation_Citymapper extends AppCompatActivity {
    private double latitudeDepart;
    private double longitudeDepart;
    private double latitudeRetour;
    private double longitudeRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation__citymapper);
        latitudeDepart = getIntent().getDoubleExtra("latitudeDepart",0);
        longitudeDepart = getIntent().getDoubleExtra("longitudeDepart",0);
        latitudeRetour = getIntent().getDoubleExtra("latitudeRetour",0);
        longitudeRetour = getIntent().getDoubleExtra("longitudeRetour",0);
        WebView webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl("https://citymapper.com/directions?startcoord="+latitudeDepart +"," + longitudeDepart + "&endcoord="+ latitudeRetour +"," + longitudeRetour);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

    }
}
