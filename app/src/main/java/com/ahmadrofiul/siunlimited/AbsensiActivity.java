package com.ahmadrofiul.siunlimited;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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

public class AbsensiActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;
    private int jumlah;
    ListView listView;
    private String [] absen, tampil;
    private String idmatkul;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absensi);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading ...");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();

        idmatkul = getIntent().getStringExtra("idmatkul");

        getData();

    }

    private void getData(){
        SharedPreferences share = getSharedPreferences("mahasiswa", MODE_PRIVATE);
        String nim = share.getString("nim","");
        String login = share.getString("login","");
        //Toast.makeText(TemanActivity.this, UtilsActivity.URL_SEKELAS+nim+"&login="+login+"&idmatkul="+idmatkul,Toast.LENGTH_LONG).show();
        final StringRequest request = new StringRequest(Request.Method.GET, UtilsActivity.URL_ABSENSI+nim+"&login="+login+"&idmatkul="+idmatkul,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            iniData(response);
                            mProgressDialog.dismiss();
                        }catch (Exception e){
                            Toast.makeText(AbsensiActivity.this, "Eror "+e,Toast.LENGTH_LONG).show();
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

            absen = new String[jumlah];


            //looping utk array
            for(int i=0; i<jsonArray.length(); i++){
                //get json berdasarkan banyaknya data (index i)
                JSONObject data = jsonArray.getJSONObject(i);

                absen[i]= data.getString("absen");
            }

            if(jumlah>3){
                tampilData(jumlah);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void tampilData(int jumlah){


        listView = (ListView) findViewById(R.id.listView);

        if(jumlah==5){
            tampil = new String[1];
        }else {
            tampil = new String[jumlah+1];
        }

        if(jumlah>5){
            for(int i=0; i<(jumlah);i++){

                //tampil[i]=absen[i];

                if(i>(jumlah-5)){
                    tampil[i]= absen[i-1];
                }else if(i==(jumlah-5)){
                    tampil[i]="";
                }else {
                    tampil[i]= absen[i];
                }
            }

            tampil[jumlah]=absen[jumlah-1];
        }else {
            tampil[0]="Maaf Absen masih Kosong";
        }


        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, tampil));
        /*listView.setSelected(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView arg0, View arg1, final int arg2, long arg3) {
                final String tampil = //"id\t\t\t\t\t\t= "+a_id[arg2]+"\n"+
                        "NIM\t\t\t=  "+
                                "Nama\t=  ";
                //final CharSequence[] dialogitem = {"Lihat Data"};
                builder = new AlertDialog.Builder(AbsensiActivity.this);
                builder.setTitle("Detail Mahasiswa");
                builder.setMessage(tampil);
                builder.setCancelable(false);
                builder.setPositiveButton("Kembali", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.create().show();
            }});*/
        ((ArrayAdapter)listView .getAdapter()).notifyDataSetInvalidated();

    }
}
