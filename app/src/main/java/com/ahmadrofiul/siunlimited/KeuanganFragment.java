package com.ahmadrofiul.siunlimited;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;

public class KeuanganFragment extends AppCompatActivity {

    ProgressDialog mProgressDialog;
    private AlertDialog.Builder builder;
    private TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9,textView10;
    private TextView textView11,textView12,textView13,textView14,textView15,textView16,textView17,textView18,textView19,textView20,textView21;
    private ListView listView;
    String gedung_skr, gedung_lalu, spp_skr, spp_lalu, poliklinik_skr, poliklinik_lalu, sks_skr, sks_lalu;
    String modul_skr, modul_lalu, bk_skr, bk_lalu, kewajiban, kekurangan, total, totalbayar;
    String terbayar, kurang, status, tercatat, dicatat;
    String [] bayar, ket, tampil;
    int jumlah;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keuangan_fragment);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading ...");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();

        builder = new AlertDialog.Builder(this);

        textView1 = (TextView)findViewById(R.id.textView1);
        textView2 = (TextView)findViewById(R.id.textView2);
        textView3 = (TextView)findViewById(R.id.textView3);
        textView4 = (TextView)findViewById(R.id.textView4);
        textView5 = (TextView)findViewById(R.id.textView5);
        textView6 = (TextView)findViewById(R.id.textView6);
        textView7 = (TextView)findViewById(R.id.textView7);
        textView8 = (TextView)findViewById(R.id.textView8);
        textView9 = (TextView)findViewById(R.id.textView9);
        textView10 = (TextView)findViewById(R.id.textView10);
        textView11 = (TextView)findViewById(R.id.textView11);
        textView12 = (TextView)findViewById(R.id.textView12);
        textView13 = (TextView)findViewById(R.id.textView13);
        textView14 = (TextView)findViewById(R.id.textView14);
        textView15 = (TextView)findViewById(R.id.textView15);
        textView16 = (TextView)findViewById(R.id.textView16);
        textView17 = (TextView)findViewById(R.id.textView17);
        textView18 = (TextView)findViewById(R.id.textView18);
        textView19 = (TextView)findViewById(R.id.textView19);
        textView20 = (TextView)findViewById(R.id.textView20);
        textView21 = (TextView)findViewById(R.id.textView21);

        listView = (ListView) findViewById(R.id.listView);

        getData();

    }

    private void getData(){
        SharedPreferences share = getSharedPreferences("mahasiswa", MODE_PRIVATE);
        String nim = share.getString("nim","");
        String login = share.getString("login","");
        final StringRequest request = new StringRequest(Request.Method.GET, UtilsActivity.URL_TAGIHAN+nim+"&login="+login,
        //final StringRequest request = new StringRequest(Request.Method.GET, UtilsActivity.JAJAL1,
                //final StringRequest request = new StringRequest(Request.Method.GET, UtilsActivity.JAJAL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            iniData(response);
                            mProgressDialog.dismiss();
                        }catch (Exception e){
                            Toast.makeText(KeuanganFragment.this, "Eror "+e,Toast.LENGTH_LONG).show();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(KeuanganFragment.this);
        requestQueue.add(request);
    }

    private void iniData(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            // ini utk mengambil attribute array yg ada di json (yaitu attribute data)

            String statuss = jsonObject.getString("status");

            if(statuss.equals("1")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(KeuanganFragment.this);
                    builder.setTitle("Waktu Habis");
                    builder.setMessage("Silahkan Mulai Kembali Aplikasi Ini");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int j) {
                            startActivity(new Intent(KeuanganFragment.this, LoginActivity.class));
                            //finish();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                }else if(statuss.equals("2")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Perhatian");
                    builder.setMessage("Maaf, menu ini sedang Maintenance");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int j) {
                            startActivity(new Intent(KeuanganFragment.this, MainActivity.class));
                            //finish();
                        }
                    });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }else {

                JSONArray jsonArray = jsonObject.getJSONArray("result");
                jumlah = jsonArray.length();
                //Toast.makeText(DataKrs.this,""+jumlah,Toast.LENGTH_LONG).show();

                //looping utk array
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject data = jsonArray.getJSONObject(i);

                    gedung_skr = data.getString("gedung_skr");
                    gedung_lalu = data.getString("gedung_lalu");
                    spp_skr = data.getString("spp_skr");
                    spp_lalu = data.getString("spp_lalu");
                    poliklinik_skr = data.getString("poliklinik_skr");
                    poliklinik_lalu = data.getString("poliklinik_lalu");
                    sks_skr = data.getString("sks_skr");
                    sks_lalu = data.getString("sks_lalu");
                    modul_skr = data.getString("modul_skr");
                    modul_lalu = data.getString("modul_lalu");
                    bk_skr = data.getString("bk_skr");
                    bk_lalu = data.getString("bk_lalu");
                    kewajiban = data.getString("kewajiban");
                    kekurangan = data.getString("kekurangan");
                    total = data.getString("total");
                    terbayar = data.getString("terbayar");
                    kurang = data.getString("kurang");
                    status = data.getString("status");
                    tercatat = data.getString("tercatat");
                    dicatat = data.getString("dicatat");
                    totalbayar = data.getString("tot");

                }

                JSONArray jsonArray2 = jsonObject.getJSONArray("result2");
                //jumlah = jsonArray2.length();
                bayar = new String [jsonArray2.length()];
                ket = new String [jsonArray2.length()];

                //looping utk array
                for (int i = 0; i < jsonArray2.length(); i++) {

                    JSONObject data = jsonArray2.getJSONObject(i);

                    bayar[i] = data.getString("bayar");
                    ket[i] = data.getString("ket");

                }

                textView1.setText(gedung_skr);
                textView2.setText(gedung_lalu);
                textView3.setText(spp_skr);
                textView4.setText(spp_lalu);
                textView5.setText(poliklinik_skr);
                textView6.setText(poliklinik_lalu);
                textView7.setText(sks_skr);
                textView8.setText(sks_lalu);
                textView9.setText(modul_skr);
                textView10.setText(modul_lalu);
                textView11.setText(bk_skr);
                textView12.setText(bk_lalu);
                textView13.setText(kewajiban);
                textView14.setText(kekurangan);
                textView15.setText(total);
                textView16.setText(terbayar);
                textView17.setText(kurang);
                textView18.setText(status);
                textView19.setText(tercatat);
                textView20.setText(dicatat);
                textView21.setText(totalbayar);


                tampilData(jsonArray2.length());

                //Toast.makeText(getActivity(), spp_skr+" = "+sks_skr+" = "+poliklinik_skr+" = "+kewajiban+" = "+status+" = "+dicatat,Toast.LENGTH_LONG).show();
                //textsetText(spp_skr+" = "+sks_skr+" = "+poliklinik_skr+" = "+kewajiban+" = "+status+" = "+dicatat);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void tampilData(int j){

        /*for(int i=0;i<jumlah;i++){
            matkul[i]="PEMROGRAMAN PERANGKAT BERGERAK"; kode[i]="N201702"; sks[i]="3"; nilai[i]="A";
        }*/

        tampil = new String[j];

        for(int i=0; i<j;i++){
            //tampil[i]=bayar[i]+"\n"+ket[i];
            tampil[i]=bayar[i];
        }

        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, tampil));
        listView.setSelected(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView arg0, View arg1, final int arg2, long arg3) {
                final String tampil = //"id\t\t\t\t\t\t= "+a_id[arg2]+"\n"+
                        "Bayar & Tanggal : \n"+bayar[arg2]+"\n\n"+
                        "Keterangan : \n"+ket[arg2];
                //final CharSequence[] dialogitem = {"Lihat Data"};
                builder = new AlertDialog.Builder(KeuanganFragment.this);
                builder.setTitle("Detail Pembayaran");
                builder.setMessage(tampil);
                builder.setCancelable(false);
                builder.setPositiveButton("Kembali", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.create().show();
            }});
        ((ArrayAdapter)listView .getAdapter()).notifyDataSetInvalidated();

    }



}