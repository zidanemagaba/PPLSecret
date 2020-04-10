package com.ahmadrofiul.siunlimited;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Login2Activity extends AppCompatActivity {

    ProgressDialog mProgressDialog;
    private AlertDialog.Builder builder;
    private String status = "0", nama = "MAHASISWA UDINUS", studi;
    private String nim, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading ...");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();

        builder = new AlertDialog.Builder(Login2Activity.this);

        SharedPreferences share = getSharedPreferences("mahasiswa", MODE_PRIVATE);
        nim = share.getString("nim","");
        password = share.getString("pass","");

        getData();
    }


    private void getData(){
        final StringRequest request = new StringRequest(Request.Method.GET, UtilsActivity.URL_LOGIN+nim+"&pass="+password+"&ver="+UtilsActivity.VERSION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            iniData(response);
                            mProgressDialog.dismiss();
                        }catch (Exception e){
                            Toast.makeText(Login2Activity.this, "Eror "+e,Toast.LENGTH_LONG).show();
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
            status = jsonObject.getString("result");
            nama = jsonObject.getString("nama");
            studi = jsonObject.getString("studi");

            if(status.equals("passwordsalah")){
                builder.setTitle("Login Gagal");
                builder.setMessage("Nim / Password salah");
                builder.setCancelable(false);
                builder.setPositiveButton("Kembali", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        startActivity(new Intent(Login2Activity.this, LoginActivity.class));
                        finish();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }else if(status.equals("maintenance")){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Perhatian");
                builder.setMessage("Maaf Aplikasi ini sedang Maintenance :) ");
                builder.setCancelable(false);
                builder.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(Login2Activity.this).edit();
                        editor.putBoolean("login", false);
                        editor.apply();

                        SharedPreferences share = getSharedPreferences("mahasiswa", MODE_PRIVATE);
                        SharedPreferences.Editor save = share.edit();
                        save.putString("nim", "");
                        save.putString("pass", "");
                        save.apply();

                        startActivity(new Intent(Login2Activity.this, LoginActivity.class));
                        finish();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }else if(status.equals("update")){
                builder.setTitle("Perhatian");
                builder.setMessage("Silahkan Update Aplikasi ini untuk Kenyamanan :)");
                builder.setCancelable(false);
                builder.setPositiveButton("Update Sekarang", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(Login2Activity.this).edit();
                        editor.putBoolean("login", false);
                        editor.apply();

                        SharedPreferences share = getSharedPreferences("mahasiswa", MODE_PRIVATE);
                        SharedPreferences.Editor save = share.edit();
                        save.putString("nim", "");
                        save.putString("pass", "");
                        save.apply();

                        String url = "https://play.google.com/store/apps/details?id=com.ahmadrofiul.siunlimited";
                        Uri webpage = Uri.parse(url);
                        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                        startActivity(intent);
                        finish();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }else if(status.equals("official")){
                builder.setTitle("Perhatian");
                builder.setMessage("Maaf Aplikasi ini tidak dapat digunakan lagi, karena sudah ada official resmi\nTerimakasih atas dukungannya :) ");
                builder.setCancelable(false);
                builder.setNegativeButton("Kembali", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(Login2Activity.this).edit();
                        editor.putBoolean("login", false);
                        editor.apply();

                        SharedPreferences share = getSharedPreferences("mahasiswa", MODE_PRIVATE);
                        SharedPreferences.Editor save = share.edit();
                        save.putString("nim", "");
                        save.putString("pass", "");
                        save.apply();

                        startActivity(new Intent(Login2Activity.this, LoginActivity.class));
                        finish();
                    }
                });
                builder.setPositiveButton("Go To Official", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        String url = UtilsActivity.DOMAIN+"/official.php";
                        Uri webpage = Uri.parse(url);
                        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                        startActivity(intent);
                        finish();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }else{
                //mengubah nilai login nya jadi true
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(Login2Activity.this).edit();
                editor.putBoolean("login", true);
                editor.apply();

                SharedPreferences share = getSharedPreferences("mahasiswa", MODE_PRIVATE);
                SharedPreferences.Editor save = share.edit();
                save.putString("nama", nama);
                save.putString("nim", nim);
                save.putString("pass", password);
                save.putString("login", status);
                save.putString("studi", studi);
                save.apply();

                startActivity(new Intent(Login2Activity.this, MainActivity.class));
                finish();
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
