package com.ahmadrofiul.siunlimited;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TemanActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;
    private int jumlah;
    ListView listView;
    private String [] nama, nim, tampil;
    private String idmatkul;
    private AlertDialog.Builder builder;
    private LayoutInflater inflater;
    private View dialogView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teman);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading ...");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();

        idmatkul = getIntent().getStringExtra("idmatkul");

        //builder = new AlertDialog.Builder(TemanActivity.this);
        getData();
    }

    private void getData(){
        SharedPreferences share = getSharedPreferences("mahasiswa", MODE_PRIVATE);
        String nim = share.getString("nim","");
        String login = share.getString("login","");
        //Toast.makeText(TemanActivity.this, UtilsActivity.URL_SEKELAS+nim+"&login="+login+"&idmatkul="+idmatkul,Toast.LENGTH_LONG).show();
        final StringRequest request = new StringRequest(Request.Method.GET, UtilsActivity.URL_SEKELAS+nim+"&login="+login+"&idmatkul="+idmatkul,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            iniData(response);
                            mProgressDialog.dismiss();
                        }catch (Exception e){
                            Toast.makeText(TemanActivity.this, "Eror "+e,Toast.LENGTH_LONG).show();
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
            JSONArray jsonArray = jsonObject.getJSONArray("result");
            jumlah = jsonArray.length();
            //Toast.makeText(StokActivity.this,""+jumlah,Toast.LENGTH_SHORT).show();

            nama = new String[jumlah];
            nim = new String[jumlah];


            //looping utk array
            for(int i=0; i<jsonArray.length(); i++){
                //get json berdasarkan banyaknya data (index i)
                JSONObject data = jsonArray.getJSONObject(i);

                nama[i]= data.getString("nama");
                nim[i]= data.getString("nim");
            }

            tampilData(jumlah);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void tampilData(int j){


        listView = (ListView) findViewById(R.id.listView);
        tampil = new String[j];

        for(int i=0; i<j;i++){
            tampil[i]= (i+1)+". "+nama[i];
        }

        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, tampil));
        listView.setSelected(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView arg0, View arg1, final int arg2, long arg3) {
                /*final String tampil = //"id\t\t\t\t\t\t= "+a_id[arg2]+"\n"+
                        *//*"NIM\t\t\t=  "+nim[arg2]+"\n"+
                        "Nama\t=  "+nama[arg2];*//*
                        "N I M\t\t=  "+nim[arg2]+"\n"+
                        "Nama\t=  "+nama[arg2];*/

                //final CharSequence[] dialogitem = {"Lihat Data"};
                String[] n = nim[arg2].split("");
                inflater = getLayoutInflater();
                dialogView = inflater.inflate(R.layout.item_teman, null);
                builder = new AlertDialog.Builder(TemanActivity.this);
                builder.setView(dialogView);

                ImageView imageview=(ImageView)dialogView.findViewById(R.id.imageView);
                Glide.with(TemanActivity.this)
                        .load("http://mahasiswa.dinus.ac.id/images/foto/"+n[1]+"/"+n[1]+n[2]+n[3]+"/"+n[5]+n[6]+n[7]+n[8]+"/"+nim[arg2]+".jpg")
                        .placeholder(R.drawable.foto)
                        .error(R.drawable.foto)
                        .into(imageview);

                TextView namaa = (TextView) dialogView.findViewById(R.id.nama);
                namaa.setText(nama[arg2]);
                TextView nimm = (TextView) dialogView.findViewById(R.id.nim);
                nimm.setText(nim[arg2]);
                builder.setTitle("Detail Mahasiswa");
                //builder.setCancelable(false);
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
