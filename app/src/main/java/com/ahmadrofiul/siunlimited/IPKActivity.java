package com.ahmadrofiul.siunlimited;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class IPKActivity extends AppCompatActivity {

    String hasil, nim;
    TextView textView;
    EditText editText;
    Button button;
    private ProgressDialog mProgressDialog;
    private AlertDialog.Builder builder;
    private LayoutInflater inflater;
    private View dialogView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipk);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading ...");
        mProgressDialog.setCanceledOnTouchOutside(false);

        textView = (TextView)findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressDialog.show();
                nim = editText.getText().toString();
                getData();
            }
        });

    }

    private void getData(){
        final StringRequest request = new StringRequest(Request.Method.GET, UtilsActivity.URL_IPK+nim,
                //final StringRequest request = new StringRequest(Request.Method.GET, UtilsActivity.JAJAL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            //Toast.makeText(LoginActivity.this, Konfigurasi.URL_GET_ADMIN+username+"&password="+password,Toast.LENGTH_LONG).show();
                            iniData(response);
                            mProgressDialog.dismiss();
                        }catch (Exception e){
                            Toast.makeText(IPKActivity.this, "Eror "+e,Toast.LENGTH_LONG).show();
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
            hasil = jsonObject.getString("result");

            if(hasil.equals("1")){
                Toast.makeText(IPKActivity.this,"Nim tidak ditemukan",Toast.LENGTH_SHORT).show();
            }else{
                String[] m;
                m = hasil.split("  ");
                //textView.setText(m[0]+"\n"+m[1]);

                String[] n = nim.split("");
                inflater = getLayoutInflater();
                dialogView = inflater.inflate(R.layout.item_teman, null);
                builder = new AlertDialog.Builder(IPKActivity.this);
                builder.setView(dialogView);

                ImageView imageview=(ImageView)dialogView.findViewById(R.id.imageView);
                //imageview.setImageResource(R.drawable.foto);
                Glide.with(IPKActivity.this)
                        .load("http://mahasiswa.dinus.ac.id/images/foto/"+n[1]+"/"+n[1]+n[2]+n[3]+"/"+n[5]+n[6]+n[7]+n[8]+"/"+nim+".jpg")
                        .placeholder(R.drawable.foto)
                        .error(R.drawable.foto)
                        .into(imageview);

                TextView namaa = (TextView) dialogView.findViewById(R.id.nama);
                namaa.setText(m[0]+"\n"+m[1]);
                builder.setTitle("Detail Mahasiswa");
                //builder.setCancelable(false);
                builder.setPositiveButton("Kembali", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.create().show();


            }



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
