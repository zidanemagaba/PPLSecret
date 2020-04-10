package com.ahmadrofiul.siunlimited;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class SaActivity extends AppCompatActivity {

    private ListView simpleList;
    String[] matkul, kode, kelas, jadwal1, jadwal2, jadwal3, absen;
    private int jumlah = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sa);


        String [] a,b,c,d,e,f,g,h;

        a = getIntent().getStringArrayExtra("id");
        b = getIntent().getStringArrayExtra("matkul");
        c = getIntent().getStringArrayExtra("kode");
        d = getIntent().getStringArrayExtra("kelas");
        e = getIntent().getStringArrayExtra("jadwal1");
        f = getIntent().getStringArrayExtra("jadwal2");
        g = getIntent().getStringArrayExtra("jadwal3");
        h = getIntent().getStringArrayExtra("absen");
        jumlah = getIntent().getIntExtra("jumlah",0);

        matkul = new String [jumlah];
        kode = new String [jumlah];
        kelas = new String [jumlah];
        jadwal1 = new String [jumlah];
        jadwal2 = new String [jumlah];
        jadwal3 = new String [jumlah];
        absen = new String [jumlah];

        for(int i=0;i<jumlah;i++){
            matkul[i] = a[i]+". "+b[i] ;
            kode[i] = c[i];
            kelas[i] = d[i];
            jadwal1[i] = e[i];
            jadwal2[i] = f[i];
            jadwal3[i] = g[i];
            absen[i] = h[i];
        }


        simpleList = (ListView) findViewById(R.id.listView);
        CustomSa customAdapter = new CustomSa(getApplicationContext(), matkul, kode, kelas,jadwal1,jadwal2,jadwal3,absen);
        simpleList.setAdapter(customAdapter);

    }



}
