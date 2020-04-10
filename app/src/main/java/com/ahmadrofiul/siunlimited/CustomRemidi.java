package com.ahmadrofiul.siunlimited;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomRemidi extends BaseAdapter {
    Context context;
    String[] id, kode, matkul, sks, kelas, jadwal1, jadwal2;
    LayoutInflater inflter;

    public CustomRemidi(Context applicationContext, String[] kode, String[] matkul, String[] sks, String[] kelas, String[] jadwal1, String[] jadwal2) {
        this.context = context;
        this.kode = kode;
        this.matkul = matkul;
        this.sks = sks;
        this.kelas= kelas;
        this.jadwal1 = jadwal1;
        this.jadwal2 = jadwal2;
        // initialize arraylist and add static string for all the materials

        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return matkul.length;
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
        view = inflter.inflate(R.layout.item_remidi, null);
        // get the reference of TextView and Button's
        TextView kode_  = (TextView) view.findViewById(R.id.kode);
        TextView matkul_ = (TextView) view.findViewById(R.id.matkul);
        TextView sks_  = (TextView) view.findViewById(R.id.sks);
        TextView kelas_ = (TextView) view.findViewById(R.id.kelas);
        TextView jadwal1_ = (TextView) view.findViewById(R.id.jadwal1);
        TextView jadwal2_ = (TextView) view.findViewById(R.id.jadwal2);



        kode_.setText(kode[i]);
        matkul_.setText(matkul[i]);
        sks_.setText(sks[i]);
        kelas_.setText(kelas[i]);
        jadwal1_.setText(jadwal1[i]);
        jadwal2_.setText(jadwal2[i]);

        return view;
    }

}
