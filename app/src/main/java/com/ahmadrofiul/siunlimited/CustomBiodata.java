package com.ahmadrofiul.siunlimited;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomBiodata extends BaseAdapter {
    Context context;
    String[] nama, biodata;
    LayoutInflater inflter;

    public CustomBiodata(Context applicationContext, String[] nama, String[] biodata) {
        this.context = applicationContext;
        this.nama = nama;
        this.biodata = biodata;

        // initialize arraylist and add static string for all the materials

        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return nama.length;
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
        view = inflter.inflate(R.layout.item_biodata, null);
        // get the reference of TextView and Button's
        TextView nama_ = (TextView) view.findViewById(R.id.nama);
        TextView biodata_ = (TextView) view.findViewById(R.id.biodata);


        nama_.setText(nama[i]);
        biodata_.setText(biodata[i]);

        return view;
    }

}
