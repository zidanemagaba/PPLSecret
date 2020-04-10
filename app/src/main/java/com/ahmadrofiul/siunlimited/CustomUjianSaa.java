package com.ahmadrofiul.siunlimited;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomUjianSaa extends BaseAdapter {
    Context context;
    private String[] id, kode, matkul, sks, kelas, absen, ijin, hari_t, jam_t, ruang_t, nomor_t, praktek;
    int tengah;
    LayoutInflater inflter;

    public CustomUjianSaa(Context applicationContext, String[] id,String[]  kode,String[]  matkul,String[]  sks,String[]  kelas,String[]  absen,String[]  ijin,String[]  hari_t,String[]  jam_t,String[]  ruang_t,String[]  nomor_t,String[]  praktek, int tengah) {
        this.context = context;

        this.id = id;
        this.kode = kode;
        this.matkul = matkul;
        this.sks = sks;
        this.kelas = kelas;
        this.absen = absen;
        this.ijin = ijin;
        this.hari_t = hari_t;
        this.jam_t = jam_t;
        this.ruang_t = ruang_t;
        this.nomor_t = nomor_t;
        this.praktek = praktek;
        this.tengah=tengah;


        // initialize arraylist and add static string for all the materials

        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return id.length/2;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.item_ujiansaa, null);
        // get the reference of TextView and Button's

        TextView kode_ = (TextView) view.findViewById(R.id.kode);
        TextView matkul_ = (TextView) view.findViewById(R.id.matkul);
        TextView kelas_ = (TextView) view.findViewById(R.id.kelas);

        TextView n_tugas_ = (TextView) view.findViewById(R.id.n_tugas);
        TextView n_uts_ = (TextView) view.findViewById(R.id.n_uts);
        TextView ket1_ = (TextView) view.findViewById(R.id.ket1);
        TextView n_uas_ = (TextView) view.findViewById(R.id.n_uas);
        TextView n_akhir_ = (TextView) view.findViewById(R.id.n_akhir);
        TextView ket2_ = (TextView) view.findViewById(R.id.ket2);





        kode_.setText(kode[i+tengah]);
        matkul_.setText(matkul[i+tengah]);
        kelas_.setText(kelas[i+tengah]);

        n_tugas_ .setText(ijin[i+tengah]);
        n_uts_ .setText(hari_t[i+tengah]);
        ket1_ .setText(jam_t[i+tengah]);
        n_uas_ .setText(ruang_t[i+tengah]);
        n_akhir_ .setText(nomor_t[i+tengah]);
        ket2_ .setText(praktek[i+tengah]);





        return view;
    }
}