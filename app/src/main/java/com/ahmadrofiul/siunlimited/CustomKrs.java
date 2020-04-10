package com.ahmadrofiul.siunlimited;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class CustomKrs extends BaseAdapter  {
    Context context;
    String[] matkul, kode, kelas, jadwal1, jadwal2, jadwal3, absen, dabsen;
    LayoutInflater inflter;

    public CustomKrs(Context applicationContext, String[] matkul, String[] kode, String[] kelas, String[] jadwal1, String[] jadwal2, String[] jadwal3, String[] absen, String[] dabsen) {
        this.context = context;
        this.matkul = matkul;
        this.kode = kode;
        this.kelas= kelas;
        this.jadwal1 = jadwal1;
        this.jadwal2 = jadwal2;
        this.jadwal3 = jadwal3;
        this.absen = absen;
        this.dabsen = dabsen;
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
        view = inflter.inflate(R.layout.item_krs, null);
        // get the reference of TextView and Button's
        TextView matkul_ = (TextView) view.findViewById(R.id.matkul);
        TextView kode_  = (TextView) view.findViewById(R.id.kode);
        TextView kelas_ = (TextView) view.findViewById(R.id.kelas);
        TextView jadwal1_ = (TextView) view.findViewById(R.id.jadwal1);
        TextView jadwal2_ = (TextView) view.findViewById(R.id.jadwal2);
        TextView jadwal3_ = (TextView) view.findViewById(R.id.jadwal3);
        TextView absen_ = (TextView) view.findViewById(R.id.absen);

        if(absen[i].equals("")){

        }else{
            String[] absenn = absen[i].split(" ");
            double absennn = Double.parseDouble(absenn[0]);

            if(absennn>=75){
                absen_.setTextColor(Color.rgb(25, 175, 75)); // HIJAU
            }else if(absennn>=50){
                absen_.setTextColor(Color.rgb(255, 150, 50)); // KUNING
            }else {
                absen_.setTextColor(Color.rgb(200, 30, 30)); // MERAH
            }
        }


        matkul_.setText(matkul[i]);
        kode_.setText(kode[i]);
        kelas_.setText(kelas[i]);
        jadwal1_.setText(jadwal1[i]);
        jadwal2_.setText(jadwal2[i]);
        jadwal3_.setText(jadwal3[i]);
        absen_.setText(absen[i]);



        return view;
    }
}