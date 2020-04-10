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

public class DataSA extends AppCompatActivity {

    ProgressDialog mProgressDialog;
    String[] id, matkul, kode, kelas, jadwal1, jadwal2, jadwal3, absen;
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
        id = new String [jumlah];
        matkul = new String [jumlah];
        kode = new String [jumlah];
        kelas = new String [jumlah];
        jadwal1 = new String [jumlah];
        jadwal2 = new String [jumlah];
        jadwal3 = new String [jumlah];
        absen = new String [jumlah];


        getData();

    }

    private void getData(){
        SharedPreferences share = getSharedPreferences("mahasiswa", MODE_PRIVATE);
        String nim = share.getString("nim","");
        String login = share.getString("login","");
        final StringRequest request = new StringRequest(Request.Method.GET, UtilsActivity.URL_SA+nim+"&login="+login,
                //final StringRequest request = new StringRequest(Request.Method.GET, UtilsActivity.JAJAL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            //Toast.makeText(KrsActivity.this,UtilsActivity.URL_KRS+"A11.2016.09978&pass=cumlaude",Toast.LENGTH_SHORT).show();
                            iniData(response);
                            mProgressDialog.dismiss();
                        }catch (Exception e){
                            Toast.makeText(DataSA.this, "Eror "+e,Toast.LENGTH_LONG).show();
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

            if(status.equals("0")){
                AlertDialog.Builder builder = new AlertDialog.Builder(DataSA.this);
                builder.setTitle("Waktu Habis");
                builder.setMessage("Silahkan Mulai Kembali Aplikasi Ini");
                builder.setCancelable(false);
                builder.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        startActivity(new Intent(DataSA.this, Login2Activity.class));
                        finish();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }else if(status.equals("1")){
                AlertDialog.Builder builder = new AlertDialog.Builder(DataSA.this);
                builder.setTitle("Perhatian");
                builder.setMessage("Maaf, Anda tidak mengambil Semester Antara");
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
                AlertDialog.Builder builder = new AlertDialog.Builder(DataSA.this);
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
                for(int i=0; i<jsonArray.length(); i++){

                    JSONObject data = jsonArray.getJSONObject(i);

                    String a = data.getString("id");
                    String b = data.getString("kd_mk");
                    String c = data.getString("matkul");
                    String d = data.getString("sks");
                    String e = data.getString("kelompok");
                    String f = data.getString("sts");
                    String g = data.getString("jadwal_1");
                    String h = data.getString("ruang_1");
                    String j = data.getString("jadwal_2");
                    String k = data.getString("ruang_2");
                    String l = data.getString("jadwal_3");
                    String m = data.getString("ruang_3");
                    String n = data.getString("absen");

                    id[i]= a;
                    matkul[i]= c;
                    kode[i]= b+" ["+d+" Sks/"+f+"]";
                    kelas[i]= "Kelompok "+e;
                    jadwal1[i]= g+" ("+h+")";
                    if(j.equals("")||j.equals("-")){ jadwal2[i]= j; }else{ jadwal2[i]= j+" ("+k+")"; }
                    if(l.equals("")||l.equals("-")){ jadwal3[i]= l; }else{ jadwal3[i]= l+" ("+m+")"; }
                    /*jadwal2[i]= j+" ("+k+")";
                    jadwal3[i]= l+" ("+m+")";*/
                    absen[i]= n;


                }


                Intent i = new Intent(DataSA.this, SaActivity.class);
                i.putExtra("id", id);
                i.putExtra("matkul", matkul);
                i.putExtra("kode", kode);
                i.putExtra("kelas", kelas);
                i.putExtra("jadwal1", jadwal1);
                i.putExtra("jadwal2", jadwal2);
                i.putExtra("jadwal3", jadwal3);
                i.putExtra("absen", absen);
                i.putExtra("jumlah", jumlah);
                startActivity(i); finish();

            }



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}