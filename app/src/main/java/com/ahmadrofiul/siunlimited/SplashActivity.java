package com.ahmadrofiul.siunlimited;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //ngecek apakah sudah pernah login
                //mengambil nilai di shared preferences
                boolean sudah_login = PreferenceManager.getDefaultSharedPreferences(SplashActivity.this).getBoolean("login", false);
                if(!sudah_login){
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }
                else {
                    //kalau sudah langsung masuk dashboard
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }

                /*startActivity(new Intent(SplashActivity.this, BazengActivity.class));
                finish();*/

            }
        }, 2000);

    }
}
