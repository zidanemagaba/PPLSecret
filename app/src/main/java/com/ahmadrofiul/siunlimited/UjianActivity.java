package com.ahmadrofiul.siunlimited;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ListView;

public class UjianActivity extends AppCompatActivity {

    private ListView simpleList;
    String[]  kode, matkul, kelas, absen, ijin, h_t, j_t, r_t, n_t ,h_p, j_p, r_p, n_p ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ujian);

        int jumlah;

        jumlah = getIntent().getIntExtra("jumlah",0);

        kode = new String [jumlah];
        matkul = new String [jumlah];
        kelas = new String [jumlah];
        absen = new String [jumlah];
        ijin = new String [jumlah];

        h_t = new String[jumlah];
        j_t = new String[jumlah];
        r_t = new String[jumlah];
        n_t = new String[jumlah];

        h_p = new String[jumlah];
        j_p = new String[jumlah];
        r_p = new String[jumlah];
        n_p = new String[jumlah];

        kode= getIntent().getStringArrayExtra("kode");
        matkul= getIntent().getStringArrayExtra("matkul");
        kelas= getIntent().getStringArrayExtra("kelas");
        absen= getIntent().getStringArrayExtra("absen");
        ijin= getIntent().getStringArrayExtra("ijin");

        h_t= getIntent().getStringArrayExtra("h_t");
        j_t= getIntent().getStringArrayExtra("j_t");
        r_t= getIntent().getStringArrayExtra("r_t");
        n_t= getIntent().getStringArrayExtra("n_t");

        h_p= getIntent().getStringArrayExtra("h_p");
        j_p= getIntent().getStringArrayExtra("j_p");
        r_p= getIntent().getStringArrayExtra("r_p");
        n_p= getIntent().getStringArrayExtra("n_p");


        /*for (int i=0;i<jumlah;i++){
            kode[i] = "A11.54603  [ 3 Sks ] ";
            matkul[i] = (i+1)+". INTERAKSI MANUSIA DAN KOMPUTER";
            kelas[i] = "A11.4609";
            absen[i] = "92.86%";
            ijin[i] = "Default";

            h_t[i] = "Selasa, 9 Juli 2019";
            j_t[i] = "12.30-15.00";
            r_t[i] = "H.7.2";
            n_t[i] = "8";

            //if(h_p.equals(" Jadwal Belum Tersedia."))
            h_p[i] = "Jadwal tidak tersedia";
            j_p[i] = " - ";
            r_p[i] = " - ";
            n_p[i] = " - ";
        }*/

        simpleList = (ListView) findViewById(R.id.listView);
        CustomUjian customAdapter = new CustomUjian(getApplicationContext(),kode, matkul, kelas, absen, ijin, h_t, j_t, r_t, n_t ,h_p, j_p, r_p, n_p );
        simpleList.setAdapter(customAdapter);

    }

}
