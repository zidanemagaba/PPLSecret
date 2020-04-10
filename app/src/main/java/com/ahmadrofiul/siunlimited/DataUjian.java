package com.ahmadrofiul.siunlimited;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class DataUjian extends AppCompatActivity {

    ProgressDialog mProgressDialog;
    String[] matkul, kode, kelas, ijin, absen;
    String[] h_t, j_t, r_t, n_t, h_p, j_p, r_p, n_p ;

    private int jumlah = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_krs);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading ...");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();

        jumlah = 1000;

        getData();

    }

    private void getData(){
        SharedPreferences share = getSharedPreferences("mahasiswa", MODE_PRIVATE);
        String nim = share.getString("nim","");
        String login = share.getString("login","");
        final StringRequest request = new StringRequest(Request.Method.GET, UtilsActivity.URL_UJIAN+nim+"&login="+login,
                //final StringRequest request = new StringRequest(Request.Method.GET, UtilsActivity.JAJAL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            //Toast.makeText(KrsActivity.this,UtilsActivity.URL_KRS+"A11.2016.09978&pass=cumlaude",Toast.LENGTH_SHORT).show();
                            iniData(response);
                            mProgressDialog.dismiss();
                        }catch (Exception e){
                            Toast.makeText(DataUjian.this, "Eror "+e,Toast.LENGTH_LONG).show();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void iniData(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            // ini utk mengambil attribute array yg ada di json (yaitu attribute data)
            //String status = jsonObject.getString("status");

            String status = jsonObject.getString("status");

            if(status.equals("1")){
                AlertDialog.Builder builder = new AlertDialog.Builder(DataUjian.this);
                builder.setTitle("Perhatian");
                builder.setMessage("Maaf, jadwal ujian anda belum tersedia");
                builder.setCancelable(false);
                builder.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        finish();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }else if(status.equals("2")){
                AlertDialog.Builder builder = new AlertDialog.Builder(DataUjian.this);
                builder.setTitle("Perhatian");
                builder.setMessage("Maaf, menu ini sedang Maintenance");
                builder.setCancelable(false);
                builder.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        finish();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }else {

                JSONArray jsonArray = jsonObject.getJSONArray("result");
                jumlah = jsonArray.length();
                //Toast.makeText(DataKrs.this,""+jumlah,Toast.LENGTH_LONG).show();

                kode = new String [jumlah];
                matkul = new String [jumlah];
                kelas = new String [jumlah];
                absen = new String [jumlah];
                ijin = new String [jumlah];

                h_t = new String [jumlah];
                j_t = new String [jumlah];
                r_t = new String [jumlah];
                n_t = new String [jumlah];

                h_p = new String [jumlah];
                j_p = new String [jumlah];
                r_p = new String [jumlah];
                n_p = new String [jumlah];


                //looping utk array
                for(int i=0; i<jsonArray.length(); i++){

                    JSONObject data = jsonArray.getJSONObject(i);

                    String a = data.getString("id");
                    String b = data.getString("kd_mk");
                    String c = data.getString("matkul");
                    String d = data.getString("sks");
                    String e = data.getString("kelas");
                    String f = data.getString("absen");
                    String g = data.getString("ijin");

                    String h = data.getString("hari_t");
                    String j = data.getString("jam_t");
                    String k = data.getString("ruang_t");
                    String l = data.getString("nomor_t");

                    String m = data.getString("hari_p");
                    String n = data.getString("jam_p");
                    String o = data.getString("ruang_p");
                    String p = data.getString("nomor_p");

                    if(h.equals("-")){ h = "Jadwal tidak tersedia"; }
                    if(m.equals("-")){ m = "Jadwal tidak tersedia"; }


                    kode[i]= b+" [ "+d+" Sks ]";
                    matkul[i]= a+". "+c;
                    kelas[i]= e;
                    absen[i] = f;
                    ijin[i] = g;

                    h_t[i] = h;
                    j_t[i] = j;
                    r_t[i] = k;
                    n_t[i] = l;

                    h_p[i] = m;
                    j_p[i] = n;
                    r_p[i] = o;
                    n_p[i] = p;

                }


                Intent i = new Intent(DataUjian.this, UjianActivity.class);
                i.putExtra("kode", kode);
                i.putExtra("matkul", matkul);
                i.putExtra("kelas", kelas);
                i.putExtra("absen", absen);
                i.putExtra("ijin", ijin);

                i.putExtra("h_t", h_t);
                i.putExtra("j_t", j_t);
                i.putExtra("r_t", r_t);
                i.putExtra("n_t", n_t);

                i.putExtra("h_p", h_p);
                i.putExtra("j_p", j_p);
                i.putExtra("r_p", r_p);
                i.putExtra("n_p", n_p);

                i.putExtra("jumlah", jumlah);


                startActivity(i); finish();

            }



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}