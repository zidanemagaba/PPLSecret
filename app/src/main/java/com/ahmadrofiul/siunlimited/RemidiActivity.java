package com.ahmadrofiul.siunlimited;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class RemidiActivity extends AppCompatActivity {

    private ListView simpleList;
    String[] id, kode, matkul, sks, kelas, jadwal1, ruang1, jadwal2, ruang2;
    private int jumlah = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remidi);

        id = getIntent().getStringArrayExtra("id");
        kode = getIntent().getStringArrayExtra("kode");
        matkul = getIntent().getStringArrayExtra("matkul");
        sks = getIntent().getStringArrayExtra("sks");
        kelas = getIntent().getStringArrayExtra("kelas");
        jadwal1 = getIntent().getStringArrayExtra("jadwal1");
        jadwal2 = getIntent().getStringArrayExtra("jadwal2");


        simpleList = (ListView) findViewById(R.id.listView);
        CustomRemidi customAdapter = new CustomRemidi(getApplicationContext(),kode, matkul, sks, kelas,jadwal1,jadwal2);
        simpleList.setAdapter(customAdapter);

    }
}
