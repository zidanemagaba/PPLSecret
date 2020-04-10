package com.ahmadrofiul.siunlimited;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class InfoFragment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_fragment);

        WebView web = (WebView)findViewById(R.id.web_view);
        web.loadUrl("http://dinus.ac.id/kalenderakademik");
        web.getSettings().setBuiltInZoomControls(true);
        web.setWebViewClient(new WebViewClient());
    }
}