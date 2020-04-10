package com.ahmadrofiul.siunlimited;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class DataKeuangan extends AppCompatActivity {

    ProgressDialog mProgressDialog;
    String [] tahun, semester, tagih_spp, bayar_spp, tgl_spp, oper_spp;
    String [] tagih_sks, bayar_sks, tgl_sks, oper_sks;
    String [] tagih_krg, bayar_krg, tgl_krg, oper_krg;

    private int  jumlah = 100;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading ...");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();

        tahun = new String [jumlah];
        semester= new String [jumlah];
        tagih_spp= new String [jumlah];
        bayar_spp= new String [jumlah];
        tgl_spp= new String [jumlah];
        oper_spp= new String [jumlah];
        tagih_sks= new String [jumlah];
        bayar_sks= new String [jumlah];
        tgl_sks= new String [jumlah];
        oper_sks = new String [jumlah];
        tagih_krg= new String [jumlah];
        bayar_krg= new String [jumlah];
        tgl_krg= new String [jumlah];
        oper_krg = new String [jumlah];

        getData();

    }

    private void getData(){
        SharedPreferences share = getSharedPreferences("mahasiswa", MODE_PRIVATE);
        String nim = share.getString("nim","");
        String login = share.getString("login","");
        final StringRequest request = new StringRequest(Request.Method.GET, UtilsActivity.URL_KEUANGAN+nim+"&login="+login,
                //final StringRequest request = new StringRequest(Request.Method.GET, UtilsActivity.JAJAL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            iniData(response);
                            mProgressDialog.dismiss();
                        }catch (Exception e){
                            Toast.makeText(DataKeuangan.this, "Eror "+e,Toast.LENGTH_LONG).show();
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
                AlertDialog.Builder builder = new AlertDialog.Builder(DataKeuangan.this);
                builder.setTitle("Waktu Habis");
                builder.setMessage("Silahkan Mulai Kembali Aplikasi Ini");
                builder.setCancelable(false);
                builder.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        startActivity(new Intent(DataKeuangan.this, Login2Activity.class));
                        finish();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }else if(status.equals("2")){
                AlertDialog.Builder builder = new AlertDialog.Builder(DataKeuangan.this);
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

                    tahun[i] = data.getString("tahun");
                    semester[i] = data.getString("semester");
                    tagih_spp[i] = data.getString("tagih_spp");
                    bayar_spp[i] = data.getString("bayar_spp");
                    tgl_spp[i] = data.getString("tgl_spp");
                    oper_spp[i] = data.getString("operator_spp");
                    tagih_sks[i] = data.getString("tagih_sks");
                    bayar_sks[i] = data.getString("bayar_sks");
                    tgl_sks[i] = data.getString("tgl_sks");
                    oper_sks[i] = data.getString("operator_sks");
                    tagih_krg[i] = data.getString("kurang");
                    bayar_krg[i] = data.getString("bayar_kurang");
                    tgl_krg[i] = data.getString("tgl_kurang");
                    oper_krg[i] = data.getString("operator_kurang");

                }


                Intent i = new Intent(DataKeuangan.this, KeuanganActivity.class);
                i.putExtra("tahun", tahun);
                i.putExtra("semester", semester);

                i.putExtra("tagih_spp", tagih_spp);
                i.putExtra("bayar_spp", bayar_spp);
                i.putExtra("tgl_spp", tgl_spp);
                i.putExtra("oper_spp", oper_spp);

                i.putExtra("tagih_sks", tagih_sks);
                i.putExtra("bayar_sks", bayar_sks);
                i.putExtra("tgl_sks", tgl_sks);
                i.putExtra("oper_sks", oper_sks);

                i.putExtra("tagih_krg", tagih_krg);
                i.putExtra("bayar_krg", bayar_krg);
                i.putExtra("tgl_krg", tgl_krg);
                i.putExtra("oper_krg", oper_krg);
                i.putExtra("jumlah", jumlah);

                startActivity(i);
                finish();

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
