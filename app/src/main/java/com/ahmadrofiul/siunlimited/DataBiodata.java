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

public class DataBiodata extends AppCompatActivity {

    ProgressDialog mProgressDialog;
    String nim, nama, jalur, status, ttl, prov_a, alamat_a, kodepos_a, telepon_a, alamat_s, telepon_s, kodepos_s;
    String hp_mhs, email_mhs, email_lain, warga, agama, goldar, marital, kelas, waldos, alamat_o, ttl_o, prov_o, telepon_o;

    private int  jumlah = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading ...");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();

        getData();

    }

    private void getData(){
        SharedPreferences share = getSharedPreferences("mahasiswa", MODE_PRIVATE);
        String nim = share.getString("nim","");
        String login = share.getString("login","");
        final StringRequest request = new StringRequest(Request.Method.GET, UtilsActivity.URL_BIODATA+nim+"&login="+login,
                //final StringRequest request = new StringRequest(Request.Method.GET, UtilsActivity.JAJAL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            iniData(response);
                            mProgressDialog.dismiss();
                        }catch (Exception e){
                            Toast.makeText(DataBiodata.this, "Eror "+e,Toast.LENGTH_LONG).show();
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
                AlertDialog.Builder builder = new AlertDialog.Builder(DataBiodata.this);
                builder.setTitle("Waktu Habis");
                builder.setMessage("Silahkan Mulai Kembali Aplikasi Ini");
                builder.setCancelable(false);
                builder.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        startActivity(new Intent(DataBiodata.this, Login2Activity.class));
                        finish();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }else if(status.equals("2")){
                AlertDialog.Builder builder = new AlertDialog.Builder(DataBiodata.this);
                builder.setTitle("Perhatian");
                builder.setMessage("Maaf, menu ini sedang Maintenance");
                builder.setCancelable(false);
                builder.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        //startActivity(new Intent(DataBiodata.this, MainActivity.class));
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

                    nim = data.getString("nim");
                    nama = data.getString("nama");
                    jalur = data.getString("jalur");
                    status = data.getString("status");
                    ttl = data.getString("ttl");
                    prov_a = data.getString("prov_a");
                    alamat_a = data.getString("alamat_a");
                    kodepos_a = data.getString("kodepos_a");
                    telepon_a = data.getString("telepon_a");
                    alamat_s = data.getString("alamat_s");
                    kodepos_s = data.getString("kodepos_s");
                    telepon_s = data.getString("telepon_s");
                    hp_mhs = data.getString("hp_mhs");
                    email_mhs = data.getString("email_mhs");
                    email_lain = data.getString("email_lain");
                    warga = data.getString("warga");
                    agama = data.getString("agama");
                    goldar = data.getString("goldar");
                    marital = data.getString("marital");
                    kelas = data.getString("kelas");
                    waldos = data.getString("waldos");
                    alamat_o = data.getString("alamat_o");
                    ttl_o = data.getString("ttl_o");
                    prov_o = data.getString("prov");
                    telepon_o = data.getString("telepon_o");
                }


                Intent i = new Intent(DataBiodata.this, BiodataActivity.class);
                i.putExtra("nim", nim);
                i.putExtra("nama", nama);
                i.putExtra("jalur", jalur);
                i.putExtra("status", status);
                i.putExtra("ttl", ttl);
                i.putExtra("prov_a", prov_a);
                i.putExtra("alamat_a", alamat_a);
                i.putExtra("kodepos_a", kodepos_a);
                i.putExtra("telepon_a", telepon_a);
                i.putExtra("alamat_s", alamat_s);
                i.putExtra("kodepos_s", kodepos_s);
                i.putExtra("telepon_s", telepon_s);
                i.putExtra("hp_mhs", hp_mhs);
                i.putExtra("email_mhs", email_mhs);
                i.putExtra("email_lain", email_lain);
                i.putExtra("warga", warga);
                i.putExtra("agama", agama);
                i.putExtra("goldar", goldar);
                i.putExtra("marital", marital);
                i.putExtra("kelas", kelas);
                i.putExtra("waldos", waldos);
                i.putExtra("alamat_o", alamat_o);
                i.putExtra("ttl_o", ttl_o);
                i.putExtra("prov_o", prov_o);
                i.putExtra("telepon_o", telepon_o);
                startActivity(i);
                finish();
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
