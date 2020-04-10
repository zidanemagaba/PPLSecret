package com.ahmadrofiul.siunlimited;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class KrsActivity extends AppCompatActivity {

    private ListView simpleList;
    String[] matkul, kode, kelas, jadwal1, jadwal2, jadwal3, absen, dabsen;
    private int jumlah = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_krs);

        String [] a,b,c,d,e,f,g,h,j;

        a = getIntent().getStringArrayExtra("id");
        b = getIntent().getStringArrayExtra("matkul");
        c = getIntent().getStringArrayExtra("kode");
        d = getIntent().getStringArrayExtra("kelas");
        e = getIntent().getStringArrayExtra("jadwal1");
        f = getIntent().getStringArrayExtra("jadwal2");
        g = getIntent().getStringArrayExtra("jadwal3");
        h = getIntent().getStringArrayExtra("absen");
        j = getIntent().getStringArrayExtra("dabsen");
        jumlah = getIntent().getIntExtra("jumlah",0);

        matkul = new String [jumlah];
        kode = new String [jumlah];
        kelas = new String [jumlah];
        jadwal1 = new String [jumlah];
        jadwal2 = new String [jumlah];
        jadwal3 = new String [jumlah];
        absen = new String [jumlah];
        dabsen = new String [jumlah];

        for(int i=0;i<jumlah;i++){
            matkul[i] = a[i]+". "+b[i] ;
            kode[i] = c[i];
            kelas[i] = d[i];
            jadwal1[i] = e[i];
            jadwal2[i] = f[i];
            jadwal3[i] = g[i];
            absen[i] = h[i];
            dabsen[i] = j[i];

        }


        simpleList = (ListView) findViewById(R.id.listView);
        CustomKrs customAdapter = new CustomKrs(getApplicationContext(), matkul, kode, kelas,jadwal1,jadwal2,jadwal3,absen, dabsen);
        simpleList.setAdapter(customAdapter);

    }

}
