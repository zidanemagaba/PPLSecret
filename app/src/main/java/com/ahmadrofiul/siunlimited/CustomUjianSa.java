package com.ahmadrofiul.siunlimited;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomUjianSa extends BaseAdapter {
    Context context;
    private String[] id, kode, matkul, sks, kelas, absen, ijin, hari_t, jam_t, ruang_t, nomor_t, praktek;
    int tengah;
    LayoutInflater inflter;

    public CustomUjianSa(Context applicationContext, String[] id,String[]  kode,String[]  matkul,String[]  sks,String[]  kelas,String[]  absen,String[]  ijin,String[]  hari_t,String[]  jam_t,String[]  ruang_t,String[]  nomor_t,String[]  praktek, int tengah) {
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
        this.tengah = tengah;

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
        view = inflter.inflate(R.layout.item_ujiansa, null);
        // get the reference of TextView and Button's

        TextView kode_ = (TextView) view.findViewById(R.id.kode);
        TextView matkul_ = (TextView) view.findViewById(R.id.matkul);
        TextView kelas_ = (TextView) view.findViewById(R.id.kelas);
        TextView absen_ = (TextView) view.findViewById(R.id.absen);
        TextView ijin_ = (TextView) view.findViewById(R.id.ijin);
        TextView jadwal_ = (TextView) view.findViewById(R.id.jadwal);
        //TextView praktek_ = (TextView) view.findViewById(R.id.praktek);


        kode_.setText(kode[i]);
        matkul_.setText(matkul[i]);
        kelas_.setText(kelas[i]);
        absen_.setText(absen[i]);
        ijin_.setText(ijin[i]);
        jadwal_.setText(hari_t[i]+" - "+jam_t[i]+" - "+ruang_t[i]+"-"+nomor_t[i]);














        return view;
    }
}