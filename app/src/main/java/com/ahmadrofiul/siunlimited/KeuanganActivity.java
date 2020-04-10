package com.ahmadrofiul.siunlimited;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ListView;

public class KeuanganActivity extends AppCompatActivity {

    LayoutInflater inflter;
    private ListView simpleList;
    String [] tahun, semester, tagih_spp, bayar_spp, tgl_spp;
    String [] tagih_sks, bayar_sks, tgl_sks;
    String [] tagih_krg, bayar_krg, tgl_krg;
    String [] a,b,c,d,e,f,g,h,j,k,l,m,n,o;
    int jumlah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keuangan);

        a= getIntent().getStringArrayExtra("tahun");
        b= getIntent().getStringArrayExtra("semester");

        c= getIntent().getStringArrayExtra("tagih_spp");
        d= getIntent().getStringArrayExtra("bayar_spp");
        e= getIntent().getStringArrayExtra("tgl_spp");
        f= getIntent().getStringArrayExtra("oper_spp");

        g= getIntent().getStringArrayExtra("tagih_sks");
        h= getIntent().getStringArrayExtra("bayar_sks");
        j= getIntent().getStringArrayExtra("tgl_sks");
        k= getIntent().getStringArrayExtra("oper_sks");

        l= getIntent().getStringArrayExtra("tagih_krg");
        m= getIntent().getStringArrayExtra("bayar_krg");
        n= getIntent().getStringArrayExtra("tgl_krg");
        o= getIntent().getStringArrayExtra("oper_krg");
        jumlah = getIntent().getIntExtra("jumlah",0);

        tahun = new String [jumlah];
        semester= new String [jumlah];
        tagih_spp= new String [jumlah];
        bayar_spp= new String [jumlah];
        tgl_spp= new String [jumlah];
        tagih_sks= new String [jumlah];
        bayar_sks= new String [jumlah];
        tgl_sks= new String [jumlah];
        tagih_krg= new String [jumlah];
        bayar_krg= new String [jumlah];
        tgl_krg= new String [jumlah];


        for (int i=0;i<jumlah;i++){

            tahun[i]=a[i];
            semester[i]=b[i];

            tagih_spp[i]=c[i];
            bayar_spp[i]=d[i];
            tgl_spp[i]=e[i]+" - "+f[i];

            tagih_sks[i]=g[i];
            bayar_sks[i]=h[i];
            tgl_sks[i]=j[i]+" - "+k[i];

            tagih_krg[i]=l[i];
            bayar_krg[i]=m[i];
            tgl_krg[i]=n[i]+" - "+o[i];

        }

        simpleList = (ListView) findViewById(R.id.listView);
        CustomKeuangan customAdapter = new CustomKeuangan(getApplicationContext(),tahun, semester, tagih_spp, bayar_spp, tgl_spp, tagih_sks, bayar_sks, tgl_sks , tagih_krg, bayar_krg, tgl_krg);
        simpleList.setAdapter(customAdapter);

    }
}
