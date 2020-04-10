package com.ahmadrofiul.siunlimited;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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

public class RepositoryActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;
    private int jumlah;
    ListView listView;
    private String [] nama, url;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading ...");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();

        builder = new AlertDialog.Builder(RepositoryActivity.this);
        getData();

    }

    private void getData(){
        SharedPreferences share = getSharedPreferences("mahasiswa", MODE_PRIVATE);
        String nim = share.getString("nim","");
        String login = share.getString("login","");
        final StringRequest request = new StringRequest(Request.Method.GET, UtilsActivity.URL_REPOSITORY+nim+"&login="+login,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            iniData(response);
                            mProgressDialog.dismiss();
                        }catch (Exception e){
                            Toast.makeText(RepositoryActivity.this, "Eror "+e,Toast.LENGTH_LONG).show();
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

            String status = jsonObject.getString("status");

            if(status.equals("0")){
                AlertDialog.Builder builder = new AlertDialog.Builder(RepositoryActivity.this);
                builder.setTitle("Perhatian");
                builder.setMessage("Maaf, Repository tidak tersedia");
                builder.setCancelable(false);
                builder.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        //startActivity(new Intent(NilaiActivity.this, LoginActivity.class));
                        finish();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }else if(status.equals("2")){
                AlertDialog.Builder builder = new AlertDialog.Builder(RepositoryActivity.this);
                builder.setTitle("Perhatian");
                builder.setMessage("Maaf, menu ini sedang Maintenance");
                //builder.setCancelable(false);
                builder.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        finish();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }else{

                JSONArray jsonArray = jsonObject.getJSONArray("result");
                jumlah = jsonArray.length();

                nama = new String [jumlah];
                url = new String [jumlah];

                //looping utk array
                for(int i=0; i<jsonArray.length(); i++){
                    //get json berdasarkan banyaknya data (index i)
                    JSONObject data = jsonArray.getJSONObject(i);

                    nama[i]= data.getString("nama");
                    url[i]= data.getString("url");
                }

                tampilData(jumlah);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void tampilData(int j){

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, nama));
        listView.setSelected(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView arg0, View arg1, final int arg2, long arg3) {

                    final String tampil = nama[arg2];

                    //final CharSequence[] dialogitem = {"Lihat Data"};
                    builder = new AlertDialog.Builder(RepositoryActivity.this);
                    builder.setTitle("Apakah ingin mendownload?");
                    builder.setMessage(tampil);
                    builder.setCancelable(false);
                    builder.setNegativeButton("Kembali", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.setPositiveButton("Download", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            String link = url[arg2];
                            Uri webpage = Uri.parse(link);
                            Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                            startActivity(intent);
                            finish();

                        }
                    });
                    builder.create().show();

            }});
        ((ArrayAdapter)listView .getAdapter()).notifyDataSetInvalidated();

    }
}
