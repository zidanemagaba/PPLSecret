package com.ahmadrofiul.siunlimited;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

public class MatkulActivity extends AppCompatActivity {

    ProgressDialog mProgressDialog;
    private AlertDialog.Builder builder;
    ListView listView;
    String[] id, matkul, dabsen, tampil;
    String pilihan;
    private int jumlah = 0, total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matkul);

        pilihan = getIntent().getStringExtra("pilihan");

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading ...");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();
        builder = new AlertDialog.Builder(MatkulActivity.this);

        jumlah = 100;
        id = new String [jumlah];
        matkul = new String [jumlah];
        dabsen = new String [jumlah];

        getData();
    }

    private void getData(){
        SharedPreferences share = getSharedPreferences("mahasiswa", MODE_PRIVATE);
        String nim = share.getString("nim","");
        String login = share.getString("login","");
        final StringRequest request = new StringRequest(Request.Method.GET, UtilsActivity.URL_KRS+nim+"&login="+login,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            iniData(response);
                            mProgressDialog.dismiss();
                        }catch (Exception e){
                            Toast.makeText(MatkulActivity.this, "Eror "+e,Toast.LENGTH_LONG).show();
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
                AlertDialog.Builder builder = new AlertDialog.Builder(MatkulActivity.this);
                builder.setTitle("Waktu Habis");
                builder.setMessage("Silahkan Mulai Kembali Aplikasi Ini");
                builder.setCancelable(false);
                builder.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        startActivity(new Intent(MatkulActivity.this, LoginActivity.class));
                        finish();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }else if(status.equals("2")){
                AlertDialog.Builder builder = new AlertDialog.Builder(MatkulActivity.this);
                builder.setTitle("Perhatian");
                builder.setMessage("Maaf, menu ini sedang Maintenance");
                builder.setCancelable(false);
                builder.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        startActivity(new Intent(MatkulActivity.this, MainActivity.class));
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
                    String o = data.getString("dabsen");

                    id[i]= a;
                    matkul[i]= c;
                    dabsen[i]=o;

                }

                for (int i=0; i<jsonArray.length(); i++){
                    if((i!=0)&&id[i].equals("1")){
                        total = i;
                        break;
                    }
                }

                tampilData(total);

            }



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void tampilData(int j){


        listView = (ListView) findViewById(R.id.listView);
        tampil = new String[j];

        for(int i=0; i<j;i++){
            tampil[i]=matkul[i];
        }

        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, tampil));
        listView.setSelected(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView arg0, View arg1, final int arg2, long arg3) {
                if(pilihan.equals("1")){
                    final String cek = "Apakah ingin melihat absensi?";
                    //final CharSequence[] dialogitem = {"Lihat Data"};
                    builder = new AlertDialog.Builder(MatkulActivity.this);
                    builder.setTitle(""+matkul[arg2]);
                    builder.setMessage(cek);
                    builder.setCancelable(false);
                    builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(MatkulActivity.this, AbsensiActivity.class);
                            i.putExtra("idmatkul", dabsen[arg2]);
                            startActivity(i);
                        }
                    });
                    builder.setNegativeButton("Kembali", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    builder.create().show();
                }else if(pilihan.equals("2")){
                    final String cek = "Apakah ingin melihat daftar teman?";
                    //final CharSequence[] dialogitem = {"Lihat Data"};
                    builder = new AlertDialog.Builder(MatkulActivity.this);
                    builder.setTitle(""+matkul[arg2]);
                    builder.setMessage(cek);
                    builder.setCancelable(false);
                    builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(MatkulActivity.this, TemanActivity.class);
                            i.putExtra("idmatkul", dabsen[arg2]);
                            startActivity(i);
                        }
                    });
                    builder.setNegativeButton("Kembali", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    builder.create().show();
                }

            }});
        ((ArrayAdapter)listView .getAdapter()).notifyDataSetInvalidated();

    }
}
