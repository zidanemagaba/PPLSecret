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

public class DataKhs extends AppCompatActivity {

    ProgressDialog mProgressDialog;
    String[] dosen, matkul, kode, kelas, n_tugas, n_uts, ket1, n_uas, n_akhir, ket2;
    private int  jumlah = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading ...");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();

        jumlah = 190;
        dosen = new String [jumlah];
        matkul = new String [jumlah];
        kode = new String [jumlah];
        kelas = new String [jumlah];
        n_tugas = new String [jumlah];
        n_uts = new String [jumlah];
        ket1 = new String [jumlah];
        n_uas = new String [jumlah];
        n_akhir = new String [jumlah];
        ket2 = new String [jumlah];

        getData();

    }

    private void getData(){
        SharedPreferences share = getSharedPreferences("mahasiswa", MODE_PRIVATE);
        String nim = share.getString("nim","");
        String login = share.getString("login","");
        final StringRequest request = new StringRequest(Request.Method.GET, UtilsActivity.URL_KHS+nim+"&login="+login,
                //final StringRequest request = new StringRequest(Request.Method.GET, UtilsActivity.JAJAL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            iniData(response);
                            mProgressDialog.dismiss();
                        }catch (Exception e){
                            Toast.makeText(DataKhs.this, "Eror "+e,Toast.LENGTH_LONG).show();
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
                AlertDialog.Builder builder = new AlertDialog.Builder(DataKhs.this);
                builder.setTitle("Waktu Habis");
                builder.setMessage("Silahkan Mulai Kembali Aplikasi Ini");
                builder.setCancelable(false);
                builder.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        startActivity(new Intent(DataKhs.this, Login2Activity.class));
                        finish();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }else if(status.equals("2")){
                AlertDialog.Builder builder = new AlertDialog.Builder(DataKhs.this);
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

                //looping utk array
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject data = jsonArray.getJSONObject(i);

                    String a = data.getString("id");
                    String b = data.getString("dosen");
                    String c = data.getString("kd_mk");
                    String d = data.getString("matkul");
                    String e = data.getString("sks");
                    String f = data.getString("kelompok");
                    String g = data.getString("sts");
                    String h = data.getString("tugas");
                    String j = data.getString("uts");
                    String k = data.getString("ket_uts");
                    String l = data.getString("uas");
                    String m = data.getString("akhir");
                    String n = data.getString("ket");


                    dosen[i] = b;
                    matkul[i] = d;
                    kode[i] = c + " \n[" + e + " Sks/" + g + "]";
                    kelas[i] = "" + f;
                    n_tugas[i] = h;
                    n_uts[i] = j;
                    ket1[i] = k;
                    n_uas[i] = l;
                    n_akhir[i] = m;
                    ket2[i] = n;

                }


                Intent i = new Intent(DataKhs.this, KhsActivity.class);
                i.putExtra("dosen", dosen);
                i.putExtra("matkul", matkul);
                i.putExtra("kode", kode);
                i.putExtra("kelas", kelas);
                i.putExtra("n_tugas", n_tugas);
                i.putExtra("n_uts", n_uts);
                i.putExtra("ket1", ket1);
                i.putExtra("n_uas", n_uas);
                i.putExtra("n_akhir", n_akhir);
                i.putExtra("ket2", ket2);
                i.putExtra("jumlah", jumlah);
                startActivity(i);
                finish();

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
