package com.ahmadrofiul.siunlimited;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class UjiansaActivity extends AppCompatActivity {

    private ListView simpleList, simpleListt;
    private String[] id, kode, matkul, sks, kelas, absen, ijin, hari_t, jam_t, ruang_t, nomor_t, praktek;
    private int jumlah = 0, tengah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ujiansa);

        jumlah = getIntent().getIntExtra("jumlah",0);

        id = new String [jumlah];
        kode = new String [jumlah];
        matkul = new String [jumlah];
        sks = new String [jumlah];
        kelas = new String [jumlah];
        absen = new String [jumlah];
        ijin = new String [jumlah];
        hari_t = new String [jumlah];
        jam_t = new String [jumlah];
        ruang_t = new String [jumlah];
        nomor_t = new String [jumlah];
        praktek = new String [jumlah];

        tengah=jumlah/2;

        id= getIntent().getStringArrayExtra("id");
        kode= getIntent().getStringArrayExtra("kode");
        matkul= getIntent().getStringArrayExtra("matkul");
        sks= getIntent().getStringArrayExtra("sks");
        kelas= getIntent().getStringArrayExtra("kelas");
        absen= getIntent().getStringArrayExtra("absen");
        ijin= getIntent().getStringArrayExtra("ijin");
        hari_t= getIntent().getStringArrayExtra("hari_t");
        jam_t= getIntent().getStringArrayExtra("jam_t");
        ruang_t= getIntent().getStringArrayExtra("ruang_t");
        nomor_t= getIntent().getStringArrayExtra("nomor_t");
        praktek= getIntent().getStringArrayExtra("praktek");



        simpleList = (ListView) findViewById(R.id.listView);
        CustomUjianSa customAdapter = new CustomUjianSa(getApplicationContext(),id, kode, matkul, sks, kelas, absen, ijin, hari_t, jam_t, ruang_t, nomor_t, praktek,tengah);
        simpleList.setAdapter(customAdapter);

        simpleListt = (ListView) findViewById(R.id.listVieww);
        CustomUjianSaa customAdapterr = new CustomUjianSaa(getApplicationContext(),id, kode, matkul, sks, kelas, absen, ijin, hari_t, jam_t, ruang_t, nomor_t, praktek,tengah);
        simpleListt.setAdapter(customAdapterr);






    }
}
