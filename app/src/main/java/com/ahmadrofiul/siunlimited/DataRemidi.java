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

public class DataRemidi extends AppCompatActivity {

    ProgressDialog mProgressDialog;
    String[] id, kode, matkul, sks, kelas, jadwal1,jadwal2;
    private int jumlah = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_krs);

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
        final StringRequest request = new StringRequest(Request.Method.GET, UtilsActivity.URL_REMIDI+nim+"&login="+login,
                //final StringRequest request = new StringRequest(Request.Method.GET, UtilsActivity.JAJAL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            //Toast.makeText(KrsActivity.this,UtilsActivity.URL_KRS+"A11.2016.09978&pass=cumlaude",Toast.LENGTH_SHORT).show();
                            iniData(response);
                            mProgressDialog.dismiss();
                        }catch (Exception e){
                            Toast.makeText(DataRemidi.this, "Eror "+e,Toast.LENGTH_LONG).show();
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
                AlertDialog.Builder builder = new AlertDialog.Builder(DataRemidi.this);
                builder.setTitle("Perhatian");
                builder.setMessage("Anda tidak mengambil Remidi");
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
                AlertDialog.Builder builder = new AlertDialog.Builder(DataRemidi.this);
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

                id = new String [jumlah];
                kode = new String [jumlah];
                matkul = new String [jumlah];
                sks = new String [jumlah];
                kelas = new String [jumlah];
                jadwal1 = new String [jumlah];
                jadwal2 = new String [jumlah];

                //looping utk array
                for(int i=0; i<jsonArray.length(); i++){

                    JSONObject data = jsonArray.getJSONObject(i);

                    id[i] = data.getString("id");
                    kode[i] = data.getString("kd_mk");
                    matkul[i] = data.getString("matkul");
                    sks[i] = "( "+data.getString("sks")+" sks )";
                    kelas[i] = data.getString("kelas");
                    jadwal1[i] = data.getString("jadwal1")+"  [ "+data.getString("ruang1")+" ]";

                    if(data.getString("jadwal2").equals("-")){
                        jadwal2[i] = "-";
                    }else {
                        jadwal2[i] = data.getString("jadwal2")+"  [ "+data.getString("ruang2")+" ]";
                    }

                }


                Intent i = new Intent(DataRemidi.this, RemidiActivity.class);
                i.putExtra("id", id);
                i.putExtra("kode", kode);
                i.putExtra("matkul", matkul);
                i.putExtra("sks", sks);
                i.putExtra("kelas", kelas);
                i.putExtra("jadwal1", jadwal1);
                i.putExtra("jadwal2", jadwal2);
                i.putExtra("jumlah", jumlah);
                startActivity(i); finish();
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
