package com.ahmadrofiul.siunlimited;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomUjian extends BaseAdapter {

    Context context;
    String[]  kode, matkul, kelas, absen, ijin, hari_t, jam_t, ruang_t, nomor_t ,h_p, j_p, r_p, n_p ;
    LayoutInflater inflter;

    public CustomUjian(Context applicationContext, String [] kode, String [] matkul, String [] kelas, String [] absen, String [] ijin, String [] hari_t, String [] jam_t, String [] ruang_t, String [] nomor_t ,String [] h_p, String [] j_p, String [] r_p, String [] n_p ) {
        this.context = context;

        this.kode =kode;
        this.matkul =matkul;
        this.kelas =kelas;
        this.absen =absen;
        this.ijin=ijin;

        this.hari_t =hari_t;
        this.jam_t =jam_t;
        this.ruang_t=ruang_t;
        this.nomor_t=nomor_t;

        this.h_p =h_p;
        this.j_p =j_p;
        this.r_p=r_p;
        this.n_p=n_p;


        // initialize arraylist and add static string for all the materials

        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return kode.length;
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
        view = inflter.inflate(R.layout.item_ujian, null);
        // get the reference of TextView and Button's

        TextView matkul_ = (TextView) view.findViewById(R.id.matkul);
        TextView kode_ = (TextView) view.findViewById(R.id.kode);
        TextView kelas_ = (TextView) view.findViewById(R.id.kelas);
        TextView absen_ = (TextView) view.findViewById(R.id.absen);
        TextView ijin_ = (TextView) view.findViewById(R.id.ijin);

        TextView jadwal1_ = (TextView) view.findViewById(R.id.jadwal1);
        TextView jadwal2_ = (TextView) view.findViewById(R.id.jadwal2);

        matkul_ .setText(matkul[i]);
        kode_ .setText(kode[i]);
        kelas_ .setText(kelas[i]);
        absen_ .setText(absen[i]);
        ijin_ .setText(ijin[i]);

        if(hari_t[i].equals("Jadwal tidak tersedia")) {
            jadwal1_ .setText(hari_t[i]);
        }else {
            jadwal1_ .setText(hari_t[i]+" "+jam_t[i]+" ( "+ruang_t[i]+" / no. "+nomor_t[i]+" )");
        }

        if(h_p[i].equals("Jadwal tidak tersedia")) {
            jadwal2_ .setText(h_p[i]);
        }else {
            jadwal2_ .setText(h_p[i]+" "+j_p[i]+" ( "+r_p[i]+" / no. "+n_p[i]+" )");
        }

        return view;
    }




}
