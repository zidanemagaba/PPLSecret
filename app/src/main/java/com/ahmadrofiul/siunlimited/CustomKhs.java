package com.ahmadrofiul.siunlimited;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomKhs extends BaseAdapter {

    Context context;
    String[] dosen ,kode, matkul, kelas, n_tugas, n_uts, ket1, n_uas, n_akhir, ket2;
    LayoutInflater inflter;

    public CustomKhs(Context applicationContext, String [] dosen ,String [] kode, String [] matkul, String [] kelas, String [] n_tugas,String [] n_uts,String [] ket1,String [] n_uas,String [] n_akhir,String [] ket2) {
        this.context = context;

        this.dosen = dosen;
        this.kode =kode;
        this.matkul =matkul;
        this.kelas =kelas;
        this.n_tugas =n_tugas;
        this.n_uts=n_uts;
        this.ket1 =ket1;
        this.n_uas =n_uas;
        this.n_akhir =n_akhir;
        this.ket2 =ket2;

        // initialize arraylist and add static string for all the materials

        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return dosen.length;
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
        view = inflter.inflate(R.layout.item_khs, null);
        // get the reference of TextView and Button's

        TextView dosen_ = (TextView) view.findViewById(R.id.dosen);
        TextView kode_ = (TextView) view.findViewById(R.id.kode);
        TextView matkul_ = (TextView) view.findViewById(R.id.matkul);
        TextView kelas_ = (TextView) view.findViewById(R.id.kelas);
        TextView n_tugas_ = (TextView) view.findViewById(R.id.n_tugas);
        TextView n_uts_ = (TextView) view.findViewById(R.id.n_uts);
        TextView ket1_ = (TextView) view.findViewById(R.id.ket1);
        TextView n_uas_ = (TextView) view.findViewById(R.id.n_uas);
        TextView n_akhir_ = (TextView) view.findViewById(R.id.n_akhir);
        TextView ket2_ = (TextView) view.findViewById(R.id.ket2);


        dosen_ .setText(dosen[i]);
        kode_ .setText(kode[i]);
        matkul_ .setText(matkul[i]);
        kelas_ .setText(kelas[i]);
        n_tugas_ .setText(n_tugas[i]);
        n_uts_ .setText(n_uts[i]);
        ket1_ .setText(ket1[i]);
        n_uas_ .setText(n_uas[i]);
        n_akhir_ .setText(n_akhir[i]);
        ket2_ .setText(ket2[i]);


        return view;
    }
}
