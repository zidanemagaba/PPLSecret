package com.ahmadrofiul.siunlimited;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Download2Activity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;
    String link, login ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download2);

        link = getIntent().getStringExtra("link");


        SharedPreferences share = getSharedPreferences("mahasiswa", MODE_PRIVATE);
        login = share.getString("login","");

        //Toast.makeText(Download2Activity.this,link+" - "+login,Toast.LENGTH_SHORT).show();

        /*WebView web = (WebView) findViewById(R.id.web_view);
        web.loadUrl(""+UtilsActivity.URL_CETAK+"pilihan="+link+"login="+login);
        web.getSettings().setBuiltInZoomControls(true);
        web.setWebViewClient(new WebViewClient());
        web.getSettings().setJavaScriptEnabled(true);*/

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading ...");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();

        getData();

    }


    private void getData(){
        final StringRequest request = new StringRequest(Request.Method.GET, UtilsActivity.URL_CETAK+"pilihan="+link+"&login="+login,
                //final StringRequest request = new StringRequest(Request.Method.GET, UtilsActivity.JAJAL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            //Toast.makeText(LoginActivity.this, Konfigurasi.URL_GET_ADMIN+username+"&password="+password,Toast.LENGTH_LONG).show();
                            iniData(response);
                            mProgressDialog.dismiss();
                        }catch (Exception e){
                            Toast.makeText(Download2Activity.this, "Eror "+e,Toast.LENGTH_LONG).show();
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
            link = jsonObject.getString("result");

            String url = link;
            Uri webpage = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(intent);
            finish();


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
