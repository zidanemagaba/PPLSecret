package com.ahmadrofiul.siunlimited;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ListView;

public class KhsActivity extends AppCompatActivity {

    LayoutInflater inflter;
    private ListView simpleList;
    String[] dosen ,kode, matkul, kelas, n_tugas, n_uts, ket1, n_uas, n_akhir, ket2;
    int jumlah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khs);

        String [] a,b,c,d,e,f,g,h,j,k;

        a = getIntent().getStringArrayExtra("dosen");
        b = getIntent().getStringArrayExtra("matkul");
        c = getIntent().getStringArrayExtra("kode");
        d = getIntent().getStringArrayExtra("kelas");
        e = getIntent().getStringArrayExtra("n_tugas");
        f = getIntent().getStringArrayExtra("n_uts");
        g = getIntent().getStringArrayExtra("ket1");
        h = getIntent().getStringArrayExtra("n_uas");
        j = getIntent().getStringArrayExtra("n_akhir");
        k = getIntent().getStringArrayExtra("ket2");
        jumlah = getIntent().getIntExtra("jumlah",0);

        dosen = new String [jumlah];
        kode = new String [jumlah];
        matkul = new String [jumlah];
        kelas = new String [jumlah];
        n_tugas = new String [jumlah];
        n_uts = new String [jumlah];
        ket1 = new String [jumlah];
        n_uas = new String [jumlah];
        n_akhir = new String [jumlah];
        ket2 = new String [jumlah];

        for (int i=0;i<jumlah;i++){
            dosen[i] = a[i];
            matkul[i] = b[i];
            kode[i] = c[i];  //kode[i] = c[i];
            kelas[i] = d[i]; //kelas[i] = d[i];
            n_tugas[i] = e[i];
            n_uts[i] = f[i];
            ket1[i] = g[i];
            n_uas[i] = h[i];
            n_akhir[i] = j[i];
            ket2[i] = k[i];
        }

        simpleList = (ListView) findViewById(R.id.listView);
        CustomKhs customAdapter = new CustomKhs(getApplicationContext(),dosen ,kode, matkul, kelas, n_tugas, n_uts, ket1, n_uas, n_akhir, ket2);
        simpleList.setAdapter(customAdapter);

    }
}
