package com.ahmadrofiul.siunlimited;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomKeuangan extends BaseAdapter {
    Context context;
    String [] tahun, semester, tagih_spp, bayar_spp, tgl_spp;
    String [] tagih_sks, bayar_sks, tgl_sks;
    String [] tagih_krg, bayar_krg, tgl_krg;
    LayoutInflater inflter;

    public CustomKeuangan(Context applicationContext,String [] tahun,String [] semester,String [] tagih_spp,String [] bayar_spp,String [] tgl_spp,String [] tagih_sks,String [] bayar_sks,String [] tgl_sks ,String [] tagih_krg,String [] bayar_krg,String [] tgl_krg) {
        this.context = applicationContext;
        this.tahun = tahun;
        this.semester = semester;

        this.tagih_spp = tagih_spp;
        this.bayar_spp = bayar_spp;
        this.tgl_spp= tgl_spp;

        this.tagih_sks = tagih_sks;
        this.bayar_sks = bayar_sks;
        this.tgl_sks= tgl_sks;

        this.tagih_krg = tagih_krg;
        this.bayar_krg = bayar_krg;
        this.tgl_krg= tgl_krg;


        // initialize arraylist and add static string for all the materials

        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return tahun.length;
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
        view = inflter.inflate(R.layout.item_keuangan, null);
        // get the reference of TextView and Button's
        TextView tahun_ = (TextView) view.findViewById(R.id.tahun);
        TextView semester_ = (TextView) view.findViewById(R.id.semester);

        TextView tagih_spp_  = (TextView) view.findViewById(R.id.tagih_spp);
        TextView bayar_spp_  = (TextView) view.findViewById(R.id.bayar_spp);
        TextView tgl_spp_  = (TextView) view.findViewById(R.id.tgl_spp);

        TextView tagih_sks_  = (TextView) view.findViewById(R.id.tagih_sks);
        TextView bayar_sks_  = (TextView) view.findViewById(R.id.bayar_sks);
        TextView tgl_sks_  = (TextView) view.findViewById(R.id.tgl_sks);

        TextView tagih_krg_  = (TextView) view.findViewById(R.id.tagih_krg);
        TextView bayar_krg_  = (TextView) view.findViewById(R.id.bayar_krg);
        TextView tgl_krg_  = (TextView) view.findViewById(R.id.tgl_krg);


        tahun_.setText(tahun[i]);
        semester_.setText(semester[i]);

        tagih_spp_.setText(tagih_spp[i]);
        bayar_spp_.setText(bayar_spp[i]);
        tgl_spp_.setText(tgl_spp[i]);

        tagih_sks_.setText(tagih_sks[i]);
        bayar_sks_.setText(bayar_sks[i]);
        tgl_sks_.setText(tgl_sks[i]);

        tagih_krg_.setText(tagih_krg[i]);
        bayar_krg_.setText(bayar_krg[i]);
        tgl_krg_.setText(tgl_krg[i]);




        return view;
    }
}